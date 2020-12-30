package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.NotePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CloudStorageApplicationTests {

	@LocalServerPort
	protected int port;

	protected String baseUrl;

	protected WebDriver driver;

	protected LoginPage loginPage;
	protected SignupPage signupPage;
	protected NotePage notePage;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
		this.baseUrl = "http://localhost:" + this.port;
		this.loginPage = new LoginPage(this.driver);
		this.signupPage = new SignupPage(this.driver);
		this.notePage = new NotePage(this.driver);
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	protected void signUpAndLogInUser(User user) {
		this.signupPage.createAndConfirmUser(this.baseUrl, user);
		this.loginPage.logInUser(this.baseUrl, user);
	}
}
