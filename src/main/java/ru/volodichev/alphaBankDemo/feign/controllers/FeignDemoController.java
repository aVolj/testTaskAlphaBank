package ru.volodichev.alphaBankDemo.feign.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.volodichev.alphaBankDemo.feign.utils.OxrUtils;

import java.io.IOException;

/**
 * todo Document type FeignDemoController
 */
@RestController
@RequestMapping("/demo")
public class FeignDemoController {

    @Autowired
    private OxrUtils oxrUtils;

    @GetMapping(
        value = "/get-random",
        produces = MediaType.IMAGE_GIF_VALUE)
    public @ResponseBody
    byte[] getRandomGif(@RequestParam("currency") String currency) throws IOException {
        return oxrUtils.compareRates(currency);
    }
}
