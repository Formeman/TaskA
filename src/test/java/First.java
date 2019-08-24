import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class First {
    private WebDriver driver;

    @BeforeClass
        public static void setupClass() {
            WebDriverManager.chromedriver().setup();
        }

        @BeforeMethod
        public void setupTest() {
            driver = new ChromeDriver();

            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        }

        @AfterMethod
        public void teardown() {
            if (driver != null) {
                driver.quit();
            }
        }

        @Test
        public void test() throws IOException, InterruptedException {
        String email= WorkWithFile.getEmail();
        String password = WorkWithFile.getPassword();
        String message = WorkWithFile.getMessage();
        String subject = WorkWithFile.getSubject();
        String emails = WorkWithFile.work();

        driver.get("https://account.mail.ru/login?page=https%3A%2F%2Fe.mail.ru%2Fmessages%2Finbox%3Fauthid%3Djznydfvn.6z%26back%3D1%26dwhsplit%3Ds3502.1s%26from%3Dmail.login%26back%3D1&email="+email+"%40mail.ru&fail=1&opener=mail.login&allow_external=1&lang=en_US");
        Thread.sleep(2000);
        driver.findElement(By.name("Password")).click();
        driver.findElement(By.name("Password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[data-test-id*='submit-button']")).click();

        driver.findElements(By.cssSelector("a[data-name*='compose']")).get(0).click();

        driver.findElement(By.cssSelector("div[data-bem*='label-input'] textarea[tabindex='4']")).click();
        driver.findElement(By.cssSelector("div[data-bem*='label-input'] textarea[tabindex='4']")).sendKeys(emails);

        driver.findElement(By.cssSelector("div[class*='compose-head__field'] input[class*='b-input']")).click();
        driver.findElement(By.cssSelector("div[class*='compose-head__field'] input[class*='b-input']")).sendKeys(subject);


        WebElement we = driver.findElement(By.cssSelector("iframe[id*='toolkit']"));
        driver.switchTo().frame(we);
        driver.findElement(By.id("tinymce")).clear();
        driver.findElement(By.id("tinymce")).sendKeys(message);

        driver.switchTo().defaultContent();
        driver.findElements(By.cssSelector("div[data-name*='send']")).get(0).click();
        }



}
