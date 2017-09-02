package com.example.acceptancetest;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.boot.test.context.SpringBootTest.*;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MyStepdefs {

    @Autowired
    TestRestTemplate testRestTemplate;

    private ResponseEntity<VersionController.Version> versionResponseEntity;

    @Given("rest client")
    public void defineRestClient(){
        // Do nothing - autowired
    }


    @When("^the client calls /version$")
    public void theClientCallsVersion() throws Throwable {
        versionResponseEntity =
                testRestTemplate.getForEntity("/version", VersionController.Version.class);
    }

    @Then("^the client receives status code of (\\d+)$")
    public void theClientReceivesStatusCodeOf(int statusCode) throws Throwable {
        then(versionResponseEntity.getStatusCode().value()).isEqualTo(statusCode);
    }

    @And("^the client receives server version (.+)$")
    public void theClientReceivesServerVersion(String version) throws Throwable {
        then(versionResponseEntity.getBody().getVersion()).isEqualTo(version);
    }
}
