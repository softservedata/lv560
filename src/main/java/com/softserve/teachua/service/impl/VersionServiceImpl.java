package com.softserve.teachua.service.impl;

import com.softserve.teachua.dto.version.VersionDto;
import com.softserve.teachua.dto.version.VersionEnum;
import com.softserve.teachua.service.PropertiesService;
import com.softserve.teachua.service.VersionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Slf4j
@Service
public class VersionServiceImpl implements VersionService {

    @Value("${version.file.name}")
    private String versionFileName;
    private PropertiesService propertiesService;

    @Autowired
    public VersionServiceImpl(PropertiesService propertiesService) {
        this.propertiesService = propertiesService;
    }

    public VersionDto getVersion() {
        Map<String, String> versionProperties = propertiesService.readProperties(versionFileName);
        VersionDto versionDto = VersionDto.builder()
                .commitNumber(versionProperties.get(VersionEnum.COMMIT_NUMBER.getFieldName()))
                .commitDate(versionProperties.get(VersionEnum.COMMIT_DATE.getFieldName()))
                .buildDate(versionProperties.get(VersionEnum.BUILD_DATE.getFieldName()))
                .build();
        log.info("*** VersionService = " + versionDto);
        return versionDto;
    }

}
