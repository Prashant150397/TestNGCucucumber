package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.Scenario;
import pages.DriverFactory;

public class ConfigReader {

	private static Properties prop;

	public static void init() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config.properties");
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static String get(String key) {
		return prop.getProperty(key);
	}

	public static void attachScreenshot(Scenario scenario, String screenshotTitle) {

		try {
			WebDriver driver = DriverFactory.getDriver();

			byte[] screenshot =
					((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.BYTES);

			scenario.attach(screenshot, "image/png", screenshotTitle);

			// Optional: Save locally
			String timeStamp =
					new SimpleDateFormat("yyyyMMdd_HHmmss")
					.format(new Date());

			File src =
					((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);

			File dest = new File("target/screenshots/"
					+ scenario.getName().replaceAll(" ", "_")
					+ "_" + timeStamp + ".png");

			dest.getParentFile().mkdirs();
			FileUtils.copyFile(src, dest);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
