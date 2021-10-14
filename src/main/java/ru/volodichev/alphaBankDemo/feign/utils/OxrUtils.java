package ru.volodichev.alphaBankDemo.feign.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Утилитарный класс для взаимодействия c OXR
 */
@Component
public class OxrUtils {

    @Autowired
    private FeignOxrUtils feignOxrUtils;
    @Autowired
    private GiphyUtils giphyUtils;
    @Autowired
    private ObjectMapper mapper;

    @Value("${oxr.key}")
    String key;
    @Value("${oxr.base}")
    String base;

    public byte[] compareRates(String currency) throws IOException {

        double todayRate = getRates(currency, null);
        double yesterday = getRates(currency, LocalDate.now().minusDays(1).toString());

        byte[] gif;

        if (todayRate >= yesterday) {
            gif = giphyUtils.getGif("rich");
        } else {
            gif = giphyUtils.getGif("broken");
        }

        return gif;
    }

    /**
     * Получает курс переданной валюты
     *
     * @param currency валюта
     * @param date     дата курса валют
     * @return курс валюты относительно заданной в application.properties(если null, то по умолчанию USD)
     **/
    private double getRates(String currency, @Nullable String date) throws JsonProcessingException {

        String json = feignOxrUtils.getRates(key, currency, (date == null || date.isBlank()) ? LocalDate.now().toString() : date, base);

        JsonNode root = mapper.readTree(json);
        JsonNode rates = root.at("/rates");
        return Double.parseDouble(rates.get(currency).asText());
    }


}
