package ru.volodichev.alphaBankDemo.feign.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Утилитарный класс для взаимодействия
 * с giphy
 *
 */
@Component
public class GiphyUtils {
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private FeignGiphyUtils feignGiphyUtils;
    @Autowired
    private Environment env;

    public String getId(String json) throws JsonProcessingException {

        JsonNode root = mapper.readTree(json);
        JsonNode original = root.at("/data");

        return original.get("id").textValue();
    }

    public byte[] getGif(String tag) throws IOException {
        String json = feignGiphyUtils.getRandom(env.getProperty("giphy.apikey"), tag);
        String idGif = getId(json);

        File output = new File("ru/volodichev/alphaBankDemo/temp", "current" + idGif + ".gif");
        FileUtils.copyURLToFile(
            new URL("https://i.giphy.com/media/" + idGif + "/giphy.webp"), output);

        return FileUtils.readFileToByteArray(output);
    }
}
