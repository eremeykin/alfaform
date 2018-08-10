package pete.eremeykin.alfa.form.test;

import org.junit.Test;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class SeleniumTest {

    @Test
    public void testOk(){
        WebDriver driver = new HtmlUnitDriver();
        driver.get("");


    }
}
