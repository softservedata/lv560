package com.softserve.teachua.service.impl;

import com.softserve.teachua.service.PropertiesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
@Service
public class PropertiesServiceImpl implements PropertiesService {
    public static final String PATH_SEPARATOR = "/";

    public Map<String, String> readProperties(String fileName) {
        Map<String, String> propertiesMap = new HashMap<>();
        Properties appProps = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(getFullPath(fileName))) {
            appProps.load(fileInputStream);
            for (Map.Entry<Object, Object> entry : appProps.entrySet()) {
                propertiesMap.put((String) entry.getKey(), (String) entry.getValue());
            }
            log.info("***Map<String, String> = " + propertiesMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return propertiesMap;
    }

    private String getFullPath(String fileName) {
        String fullPath = this.getClass().getResource(PATH_SEPARATOR + fileName).getPath();
        log.info("***fullPath = " + fullPath);
        return fullPath;
    }

}
