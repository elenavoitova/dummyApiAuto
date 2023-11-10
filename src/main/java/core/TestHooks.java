package core;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;

import static io.restassured.filter.log.LogDetail.*;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeSuite;
import settings.Config;


public abstract class TestHooks {

    @BeforeSuite
    public void setUp() {
        RestAssured.baseURI = Config.baseURL;
        RestAssured.filters(RequestLoggingFilter.with(METHOD, URI, BODY), new ResponseLoggingFilter(BODY));
        RestAssured.config = RestAssuredConfig.config().sslConfig(SSLConfig.sslConfig().allowAllHostnames().relaxedHTTPSValidation());
    }

    //	@AfterSuite
    public void tearDown() {

    }
}
