package com.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import pageObjects.HomePage;
import pageObjects.LoginPage;

public class SeleniumTest {
	public WebDriver driver;
	public WebDriverWait wait;
	LoginPage loginPage;
	HomePage homePage;

	@BeforeTest
	public void testSetup() {
		System.setProperty("webdriver.chrome.driver", "/Users/guneetkaur/Documents/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test(dataProvider = "excel-data")
	public void VerifyInvalidLogin(String username, String password) {
		homePage = new HomePage(driver);
		homePage.navigateTo_HomePage();
		loginPage = new LoginPage(driver);
		loginPage.loginYahooPage(username, password);
		Assert.assertEquals(driver.getTitle(), 
				"Yahoo Finance – stock market live, quotes, business & finance news");

	}

	@DataProvider(name = "excel-data")

	public Object[][] excelDP() throws IOException {
		String file = System.getProperty("user.dir") + "/src/test/resources/Credentials.xlsx";
		Object[][] arrObj = getExcelData(file, "Credentials");
		return arrObj;

	}

	public String[][] getExcelData(String fileName, String sheetName) {
		String[][] data = null;
		try {
			FileInputStream fis = new FileInputStream(fileName);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh = wb.getSheet(sheetName);
			XSSFRow row = sh.getRow(0);
			int noOfRows = sh.getPhysicalNumberOfRows();
			int noOfCols = row.getLastCellNum();
			Cell cell;
			data = new String[noOfRows - 1][noOfCols];
			for (int i = 1; i < noOfRows; i++) {
				for (int j = 0; j < noOfCols; j++) {
					row = sh.getRow(i);
					cell = row.getCell(j);
					data[i - 1][j] = cell.getStringCellValue();
					System.out.print(data[i - 1][j] + "\t");
				}
			}
		}

		catch (Exception e) {
			System.out.println("The exception is: " + e.getMessage());
		}

		return data;

	}

	@AfterTest
	public void cleanUp() {
		driver.quit();

	}
}
