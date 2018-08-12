package pete.eremeykin.alfa.form;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import pete.eremeykin.alfa.form.customer.Customer;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SeleniumTest extends BasicTest {

    WebDriver driver;


    @Before
    public void setDriver() throws IOException {
        driver = new HtmlUnitDriver();
    }

    private boolean corresponds(WebElement element, Customer customer) {
        try {
            if (!customer.getEmail().equals(element.findElement(By.id("email")).getText())) {
                return false;
            }
            if (!customer.getPassword().equals(element.findElement(By.id("password")).getText())) {
                return false;
            }
            if (!customer.getFirstName().equals(element.findElement(By.id("firstName")).getText())) {
                return false;
            }
            if (!customer.getLastName().equals(element.findElement(By.id("lastName")).getText())) {
                return false;
            }
            if (!customer.getPatronymic().equals(element.findElement(By.id("patronymic")).getText())) {
                return false;
            }
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            String date = customer.getBirthDate().format(fmt);
            if (!date.equals(element.findElement(By.id("birthDate")).getText())) {
                return false;
            }
            String sex = customer.getSex() == Customer.Sex.MALE ? "Муж." : "Жен.";
            if (!sex.equals(element.findElement(By.id("sex")).getText())) {
                return false;
            }
            if (!customer.getAddress().equals(element.findElement(By.id("address")).getText())) {
                return false;
            }
            if (!customer.getInn().equals(element.findElement(By.id("inn")).getText())) {
                return false;
            }
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
        return true;
    }

    boolean isSaved(Customer customer) {
        driver.get(homeUrl + "/customers");
        List<WebElement> trs = driver.findElements(By.tagName("tr"));

        for (WebElement tr : trs) {
            if (corresponds(tr, customer)) {
                return true;
            }
        }
        return false;
    }

}
