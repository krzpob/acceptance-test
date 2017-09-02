package com.example.acceptancetest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
class VersionController {

    public static class Version{
        private String version = "1.0";

        public String getVersion() {
            return version;
        }


    }

    @GetMapping(produces = APPLICATION_JSON_VALUE,value = "/version")
    Version version(){
        return new Version();
    }
}
