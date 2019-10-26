package com.omnicns.medicine.test.selenium;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
class People {
    String name;
    String phone;
}

public class LotteriaLoginTest {


    private WebDriver driver;
    JavascriptExecutor js;

    public void setUp() {
//		System.setProperty("webdriver.chrome.driver", "src/test/driver/win32/chromedriver.exe"); // 다운받은 ChromeDriver 위치를 넣어줍니다.
        System.setProperty("webdriver.chrome.driver", "src/test/driver/mac64/chromedriver"); // 다운받은 ChromeDriver 위치를 넣어줍니다.
        driver = new ChromeDriver(); // Driver 생성
        js = (JavascriptExecutor) this.driver;
    }

    public void test_title() throws InterruptedException { //타이틀 확인하는 테스트 코드
        driver.get("https://www.lotteria40.com/Pick"); // URL로 접속하기


        List<People> peoples = new ArrayList<>();
//        peoples.add(new People("노주리혜", "01044971979"));
//        peoples.add(new People("김현하", "01050950425"));
//        peoples.add(new People("선재선", "01044972492"));
//        peoples.add(new People("황현정", "01099673532"));
//        peoples.add(new People("노슬기", "01029496554"));
//        peoples.add(new People("최정심", "01091490010"));
//        peoples.add(new People("김영미", "01091544141"));
//        peoples.add(new People("김재린", "01084801848"));
//        peoples.add(new People("한빛나리", "01028282709"));
//        peoples.add(new People("선삼선", "01033518715"));
        peoples.add(new People("선분선", "01077884532"));


        for (People people : peoples) {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
//                WebElement coolestWidgetEvah = driver.findElement(By.cssSelector("#ranklist > li:nth-child(1) > div.btm > a")); //id로 Element 가져오기
			WebElement coolestWidgetEvah = driver.findElement(By.cssSelector("#ranklist > li:nth-child(2) > div.btm > a")); //id로 Element 가져오기
                coolestWidgetEvah.click();
                Thread.sleep(500);


                WebElement modal = driver.findElement(By.id("modal"));
                WebElement name = driver.findElement(By.name("Name"));
                WebElement phone = driver.findElement(By.name("Phone"));
                WebElement termAct1 = driver.findElement(By.id("TermAct1"));
                WebElement termAct2 = driver.findElement(By.id("TermAct2"));
//			js.executeScript("document.getElementById('TermAct1').setAttribute('display', 'block')");
//			js.executeScript("document.getElementById('TermAct2').setAttribute('display', 'block')");
//			js.executeScript("document.getElementById('TermAct1').setAttribute('style', 'display:block')");
//			js.executeScript("document.getElementById('TermAct2').setAttribute('style', 'display:block')");
                js.executeScript("arguments[0].setAttribute('style', 'display:block')", termAct1);
                js.executeScript("arguments[0].setAttribute('style', 'display:block')", termAct2);
                js.executeScript("document.getElementById('TermAct1').setAttribute('checked', 'checked')");
                js.executeScript("document.getElementById('TermAct2').setAttribute('checked', 'checked')");
//			WebElement termAct1 = driver.findElement(By.cssSelector("#form1 > div > div.form-info > div.check-info > div:nth-child(1) > div.tit > label > span"));
//			WebElement termAct2 = driver.findElement(By.cssSelector("#form1 > div > div.form-info > div.check-info > div:nth-child(2) > div.tit > label > span"));
                name.clear();
                phone.clear();
                name.sendKeys(people.name);
                phone.sendKeys(people.phone);
                Thread.sleep(500);

                WebElement submit = driver.findElement(By.cssSelector("#form1 > div > div.btn-box > a"));
                submit.click();
                Thread.sleep(500);
                WebElement close = driver.findElement(By.cssSelector("#modal > div > div.close-modal"));
                close.click();
                Thread.sleep(500);
            }


//		List<WebElement> cheese = driver.findElements(By.className("cheese")); //클래스이름으로 Element 가져오기
//		WebElement iframe = driver.findElement(By.tagName("iframe")); //태그이름으로 Element 가져오기
            //driver.findElement(By.xpath("//input[@id='invoice_supplier_id'])).sendKeys("your value");
//		WebElement form = driver.findElement(By.tagName("form")); //태그이름으로 Element 가져오기
//		username.sendKeys("wcfw");
//		password.sendKeys("cwcw");
//		form.submit();

//		Assert.assertThat(driver.getTitle(),is("URL의 Title")); // Title 확인 작업
        }
    }

    public void tearDown() {
        driver.quit();  // Driver 종료
    }


    public static void main(String[] args) throws InterruptedException {
        LotteriaLoginTest lotteriaLoginTest = new LotteriaLoginTest();
        lotteriaLoginTest.setUp();
        lotteriaLoginTest.test_title();
        lotteriaLoginTest.tearDown();
    }

}
