package Runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/functionalTests",
        glue={"stepDefinitions"},
        plugin = { "pretty", "json:target/cucumber/report.json",
        		   "junit:target/cucumber/report.xml",
        		   "html:target/cucumber/report.html"},
    	monochrome = true
    	)

public class TestRunner {

}
