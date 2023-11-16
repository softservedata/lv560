package com.softserve.teachua.service.impl;

import com.softserve.teachua.dto.version.VersionDto;
import com.softserve.teachua.service.VersionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class VersionServiceImpl implements VersionService {

    public VersionDto getVersion() {
        VersionDto versionDto = VersionDto.builder()
                .commitNumber("commitNumber")
                .commitDate("commitNumber")
                .buildDate("buildDate")
                .build();
        log.info("*** VersionService = " + versionDto);
        return versionDto;
    }

}
