package com.example.acceptancetest;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static com.example.acceptancetest.VersionSteps.RESPONSE;
import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.boot.test.context.SpringBootTest.*;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MyStepdefs {

    public static final String REST_TEMPLATE = "restTemplate";

    @Autowired
    TestRestTemplate testRestTemplate;

    @Steps
    VersionSteps steps;

    @Given("rest client")
    public void defineRestClient(){
        Serenity.setSessionVariable(REST_TEMPLATE).to(testRestTemplate);
    }


    @When("^the client calls /version$")
    public void theClientCallsVersion() throws Throwable {
        steps.callVersiionEndpoint();
    }

    @Then("^the client receives status code of (\\d+)$")
    public void theClientReceivesStatusCodeOf(int statusCode) throws Throwable {
        ResponseEntity<VersionController.Version> versionResponseEntity = Serenity.sessionVariableCalled(RESPONSE);
        then(versionResponseEntity.getStatusCode().value()).isEqualTo(statusCode);
    }

    @And("^the client receives server version (.+)$")
    public void theClientReceivesServerVersion(String version) throws Throwable {
        ResponseEntity<VersionController.Version> versionResponseEntity = Serenity.sessionVariableCalled(RESPONSE);
        then(versionResponseEntity.getBody().getVersion()).isEqualTo(version);
    }
}
