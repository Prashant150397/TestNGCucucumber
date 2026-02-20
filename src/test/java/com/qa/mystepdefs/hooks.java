package com.qa.mystepdefs;

import io.cucumber.java.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import pages.DriverFactory;
import util.ConfigReader;
import util.DBManager;


public class hooks {

	private static long suiteStartTime;
	private long scenarioStartTime;

	// ==============================
	// ===== SUITE LEVEL HOOKS =====
	// ==============================

	// Executes a method before all scenario
	@BeforeAll
	public static void beforeAll() {
		suiteStartTime = System.currentTimeMillis();
		System.out.println("========== TEST EXECUTION STARTED ==========");
		//DBManager.connect();
	}

	/*
	 * @BeforeAll(order=1) public static void printEnvironment() {
	 * System.out.println("Running on Environment: " + ConfigReader.get("env")); }
	 */

	// Executes a method after all scenarios
	@AfterAll
	public static void afterAll() {
		long suiteEndTime = System.currentTimeMillis();
		long totalDuration = (suiteEndTime - suiteStartTime) / 1000;

		System.out.println("========== TEST EXECUTION FINISHED ==========");
		System.out.println("Total Execution Time: " + totalDuration + " seconds");
		//DBManager.closeConnection();
	}

	// ==================================
	// ===== SCENARIO LEVEL HOOKS ======
	// ==================================

	// Execute method before each scenario.
	@Before(order = 0)
	public void loadConfig(Scenario scenario) {
		ConfigReader.init();
		scenarioStartTime = System.currentTimeMillis();
		System.out.println("\n--- Starting Scenario: " + scenario.getName() + " ---");
	}

	// Execute method before each scenario.

	@Before(order = 1)
	public void initDriver() {
		DriverFactory.initDriver();
	}

	// Execute method before each scenario.
	@Before(order = 2)
	public void setupBrowser() {
		WebDriver driver = DriverFactory.getDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		//driver.get(ConfigReader.get("url"));
	}

	// ==================================
	// ===== STEP LEVEL HOOKS ===========
	// ==================================

	// Execute method before each step.
	@BeforeStep
	public void beforeStep(Scenario scenario) {
		System.out.println("Executing Step in Scenario: " + scenario.getName());
	}

	@AfterStep
	public void afterStep(Scenario scenario) {
		if (scenario.isFailed()) {
			ConfigReader.attachScreenshot(scenario, "Step Failed Screenshot");
		}
	}

	// ==================================
	// ===== AFTER SCENARIO ============
	// ==================================


	// Execute method after each scenario.
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
