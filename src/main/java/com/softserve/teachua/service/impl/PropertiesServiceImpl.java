package com.softserve.teachua.service.impl;

import com.softserve.teachua.dto.version.VersionDto;
import com.softserve.teachua.dto.version.VersionEnum;
import com.softserve.teachua.service.PropertiesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
@Service
public class PropertiesServiceImpl implements PropertiesService {
    public static final String PATH_SEPARATOR = "/";
    public static final String PATH_MAIN_CLASS = "/../classes/";
    public static final String LOCAL_DATE_TIME_TEMPLATE = "HH:mm:ss dd.MM.yyyy";
    public static final String LOCAL_DATE_TEMPLATE = "dd.MM.yyyy";
    private final String BUILD_DATE = " Build Date is ";

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

    public void writeProperties(String fileName, String commitName, int commitDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_TEMPLATE);
        LocalDateTime localDateTime = LocalDateTime.now();
        String currentDateTime = localDateTime.format(formatter);
        //
        // /*
        //long commitMilliseconds = 1000L * commitDateTime;
        LocalDateTime commitLocalDateTime = LocalDateTime.ofEpochSecond(commitDateTime,
                0, ZoneOffset.ofHours(2));
        String commitDate = commitLocalDateTime.format(DateTimeFormatter.ofPattern(LOCAL_DATE_TEMPLATE));
        log.info("***commitDate = " + commitDate);
        // */
        /*
        LocalDate commitLocalDate = LocalDate.ofEpochDay(commitDateTime);
        String commitDate = commitLocalDate.format(DateTimeFormatter.ofPattern(LOCAL_DATE_TEMPLATE));
        log.info("***commitDate = " + commitDate + "  commitLocalDate = " + commitLocalDate);
        */
        //
        Properties appProps = new Properties();
        appProps.setProperty(VersionEnum.BACKEND_COMMIT_NUMBER.getFieldName(), commitName);
        appProps.setProperty(VersionEnum.BACKEND_COMMIT_DATE.getFieldName(), commitDate);
        appProps.setProperty(VersionEnum.BUILD_DATE.getFieldName(), currentDateTime);
        //
        try (FileOutputStream fileOutputStream = new FileOutputStream(getClassPath() + fileName)) {
            appProps.store(fileOutputStream, BUILD_DATE + currentDateTime);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("***appProps = " + appProps + " saved");
    }

    private String getFullPath(String fileName) {
        String fullPath = this.getClass().getResource(PATH_SEPARATOR + fileName).getPath();
        log.info("***fullPath = " + fullPath);
        return fullPath;
    }

    private String getClassPath() {
        String fullPath = PropertiesServiceImpl.class.getResource(PATH_MAIN_CLASS).getPath();
        log.info("***fullPath = " + fullPath);
        return fullPath;
    }
}
