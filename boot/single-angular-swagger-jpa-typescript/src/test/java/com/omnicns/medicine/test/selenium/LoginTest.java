package com.omnicns.medicine.test.selenium;

import com.omnicns.medicine.MedicineApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Omnifit2CmsApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest(classes = MedicineApplication.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("local")
//@Transactional
//@WebAppConfiguration
//@WebIntegrationTest(value = "server.port=9000")
//@SeleniumTest(driver = ChromeDriver.class, baseUrl = "http://localhost:9000")
public class LoginTest {

	private @Value("${server.port}")
	int port;

	private WebDriver driver;
	@Before
	public void setUp(){
		System.setProperty("webdriver.chrome.driver", "src/test/driver/win32/chromedriver.exe"); // 다운받은 ChromeDriver 위치를 넣어줍니다.
		driver = new ChromeDriver(); // Driver 생성
	}

	@Test
	public void test_title(){ //타이틀 확인하는 테스트 코드
		driver.get("http://localhost:"+port); // URL로 접속하기
//		WebElement coolestWidgetEvah = driver.findElement(By.id("coolestWidgetEvah")); //id로 Element 가져오기
//		List<WebElement> cheese = driver.findElements(By.className("cheese")); //클래스이름으로 Element 가져오기
//		WebElement iframe = driver.findElement(By.tagName("iframe")); //태그이름으로 Element 가져오기
		//driver.findElement(By.xpath("//input[@id='invoice_supplier_id'])).sendKeys("your value");
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement form = driver.findElement(By.tagName("form")); //태그이름으로 Element 가져오기
		username.sendKeys("cwcw");
		password.sendKeys("cwcw");
		form.submit();

//		Assert.assertThat(driver.getTitle(),is("URL의 Title")); // Title 확인 작업
	}

	@After
	public void tearDown(){
		driver.quit();  // Driver 종료
	}

}
