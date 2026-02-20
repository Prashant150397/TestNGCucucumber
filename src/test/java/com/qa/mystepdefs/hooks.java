package com.qa.mystepdefs;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import pages.DriverFactory;
import util.ConfigReader;

public class hooks {

    @Before(order=-1)
    public void setup() {
        ConfigReader.init();
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("url"));
    }

    @After
    public void tearDown(Scenario scenario) {

        WebDriver driver = DriverFactory.getDriver();

        if (scenario.isFailed()) {

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotName = scenario.getName().replaceAll(" ", "_")
                    + "_" + timeStamp + ".png";

            try {
                // Save screenshot to target/screenshots
                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                File dest = new File("target/screenshots/" + screenshotName);
                dest.getParentFile().mkdirs();
                FileUtils.copyFile(src, dest);

                // Attach screenshot to Cucumber report
                byte[] screenshotBytes =
                        ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshotBytes, "image/png", "Failed Screenshot");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        DriverFactory.removeDriver();
    }
}
