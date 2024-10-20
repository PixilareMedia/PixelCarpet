package com.pixilaremedia.pixel_carpet.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

public class PixelCarpetTranslations {
    public static Map<String, String> getTranslationFromResourcePath(String lang) {
        String jsonData;
        InputStream langFile = PixelCarpetTranslations.class.getClassLoader().getResourceAsStream("assets/pixel_carpet/lang/%s.json".formatted(new Object[] { lang }));
        if (langFile == null) {
            return Collections.emptyMap();
        }
        try {
            jsonData = IOUtils.toString(langFile, StandardCharsets.UTF_8);
        } catch (IOException e) {
            return Collections.emptyMap();
        }
        Gson gson = (new GsonBuilder()).setLenient().create();
        return (Map<String, String>)gson.fromJson(jsonData, (new TypeToken<Map<String, String>>() {}).getType());
    }
}
