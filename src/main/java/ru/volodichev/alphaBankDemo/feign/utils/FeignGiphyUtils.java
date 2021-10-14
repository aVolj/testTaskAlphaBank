package ru.volodichev.alphaBankDemo.feign.utils;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Класс для взаимодействия с внешней системой "giphy"
 */
@FeignClient(name = "demo", url="${giphy.url}")
public interface FeignGiphyUtils {

    @GetMapping("/random")
    String getRandom(@RequestParam("api_key") String key, @RequestParam("tag") String tag);

}
