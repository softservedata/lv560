package com.pattern.builder.tools;

import org.etsi.uri.x01903.v13.impl.SignerRoleTypeImpl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesReader {
    public final String PATH_SEPARATOR = "/";
    private String name;
    private String path;
    private Properties appProps;

    public PropertiesReader(String name)
    {
        this.name = name;
        this.path = this.getClass().getResource(PATH_SEPARATOR + name).getPath(); //.substring(1);
        appProps = new Properties();
    }

    public PropertiesReader()
    {
        this("application.properties");
    }

    public Map<String, String> getProperties() {
        Map<String, String> resultMap =new HashMap<>();
        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            appProps.load(fileInputStream);
            for (Map.Entry<Object, Object> entry : appProps.entrySet()) {
                resultMap.put((String)  entry.getKey(), (String) entry.getValue());
            }
            System.out.println("***Map<String, String> = " + resultMap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return resultMap;
    }
}
