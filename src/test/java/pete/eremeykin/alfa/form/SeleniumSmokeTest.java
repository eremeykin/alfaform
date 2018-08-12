package pete.eremeykin.alfa.form;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SeleniumSmokeTest extends SeleniumTest {


    @Value("${spring.datasource.initialization-mode}")
    private String initMode;


    @Test
    public void testHomePage() {
        driver.get(homeUrl);
        WebElement h2 = driver.findElement(By.tagName("h2"));
        assertThat(h2.getText(), is("Привет!"));
        WebElement base = driver.findElement(By.id("base"));
        List<WebElement> aList = base.findElements(By.tagName("a"));
        for (WebElement a : aList) {
            assertThat(a.getText(), isOneOf("Список пользователей", "Регистрационная форма"));
        }
    }

    @Test
    public void testStylesheetsAndJavaScriptLoad() {
        testCssAndJs(homeUrl);
        testCssAndJs(homeUrl + "/customers");
        testCssAndJs(homeUrl + "/customers/new");
    }

    private void testCssAndJs(String url) {
        driver.get(url);
        String bootstrapCssLink = driver.findElement(By.id("bootstrap-css")).getAttribute("href");
        String bootstrapJsLink = driver.findElement(By.id("bootstrap-js")).getAttribute("src");
        String mainCssLink = driver.findElement(By.id("main-css")).getAttribute("href");
        String jqueryLink = driver.findElement(By.id("jquery-js")).getAttribute("src");
        String datepickerJsLink = driver.findElement(By.id("datepicker-js")).getAttribute("src");
        String datepickerCssLink = driver.findElement(By.id("datepicker-css")).getAttribute("href");

        // bootstrap Css
        System.out.println(bootstrapCssLink);
        driver.get(bootstrapCssLink);
        assertThat(driver.getPageSource(), containsString("* Bootstrap v"));

        // bootstrap Js
        driver.get(bootstrapJsLink);
        assertThat(driver.getPageSource(), containsString("* Bootstrap v"));

        // main Css
        driver.get(mainCssLink);
        assertThat(driver.getPageSource(), containsString("/*alfaform main css*/"));

        // jquery Js
        driver.get(jqueryLink);
        assertThat(driver.getPageSource(), containsString("/*! jQuery v"));

        //datepicker Js
        driver.get(datepickerJsLink);
        assertThat(driver.getPageSource(), containsString("* Datepicker for Bootstrap"));

        //datepicker Css
        driver.get(datepickerCssLink);
        assertThat(driver.getPageSource(), containsString("* Datepicker for Bootstrap"));

    }

    @Test
    public void testCustomersListLink() {
        driver.get(homeUrl);
        WebElement listLink = driver.findElement(By.id("list"));
        listLink.click();
        System.out.println(driver.getPageSource());
    }

    @Test
    public void testCustomersListTable() {
        driver.get(homeUrl + "/customers");
        WebElement table = driver.findElement(By.id("customers"));
        List<WebElement> headers = table.findElements(By.tagName("th"));
        String[] expected = new String[]{"Email", "Пароль", "Фамилия",
                "Имя", "Отчество", "Дата рождения", "Пол", "Адрес", "ИНН"};
        assertThat(headers, hasSize(expected.length));
        for (WebElement header : headers) {
            assertThat(header.getText(), isOneOf(expected));
        }
    }


    /**
     * This test should run when DB init is active
     */
    @Test
    public void testInitialTableState() throws IOException {
        org.junit.Assume.assumeTrue(initMode.equals("always"));
        driver.get(homeUrl + "/customers");
        WebElement table = driver.findElement(By.id("customers"));
        String tableText = table.getText();
        assertThat(tableText, containsString("Жуков"));
        assertThat(tableText, containsString("Муж.")); // at least one male
        assertThat(tableText, containsString("Жен.")); // and one female
        assertThat(tableText, containsString("999999999999"));

        assertThat(tableText, not(containsString("Err"))); // there is no problem with sex field
        assertThat(tableText, not(containsString("???"))); // there is no encoding problem
    }
}
