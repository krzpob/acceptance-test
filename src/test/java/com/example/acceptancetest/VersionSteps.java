package com.example.acceptancetest;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

public class VersionSteps extends ScenarioSteps{

    public static final String RESPONSE = "response";

    @Step
    public void callVersiionEndpoint(){
        TestRestTemplate testRestTemplate = Serenity.sessionVariableCalled(MyStepdefs.REST_TEMPLATE);
        System.out.println("RestTemplate: "+testRestTemplate);
        ResponseEntity<VersionController.Version> versionResponseEntity =
                testRestTemplate.getForEntity("/version", VersionController.Version.class);
        Serenity.setSessionVariable(RESPONSE).to(versionResponseEntity);
    }


}
