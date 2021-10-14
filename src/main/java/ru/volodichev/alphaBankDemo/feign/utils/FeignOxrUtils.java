package ru.volodichev.alphaBankDemo.feign.utils;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * todo Document type OpenexChangeratesUtils
 */
@FeignClient(name = "oxr", url="${oxr.url}")
public interface FeignOxrUtils {

    @GetMapping("/historical/{date}.json")
    String getRates(@RequestParam("app_id") String appId,
        @RequestParam("symbols") String symbols,
        @PathVariable("date") String date,
        @RequestParam(value = "base", required = false, defaultValue = "${oxr.base}") String base);
}
