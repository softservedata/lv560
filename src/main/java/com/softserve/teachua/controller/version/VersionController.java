package com.softserve.teachua.controller.version;

import com.softserve.teachua.controller.marker.Api;
import com.softserve.teachua.dto.version.VersionDto;
import com.softserve.teachua.service.VersionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class VersionController implements Api {

    private VersionService versionService;

    @Autowired
    public VersionController(VersionService versionService) {
        this.versionService = versionService;
    }

    @GetMapping("/version")
    public VersionDto getDate() {
        log.info("*** VersionController start");
        return versionService.getVersion();
    }

}
