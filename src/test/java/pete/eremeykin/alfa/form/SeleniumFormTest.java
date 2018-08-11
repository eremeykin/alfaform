package pete.eremeykin.alfa.form;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pete.eremeykin.alfa.form.customer.Customer;
import pete.eremeykin.alfa.form.utils.IvanChelovekov;
import pete.eremeykin.alfa.form.utils.ValidRandomCustomer;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SeleniumFormTest extends SeleniumTest {

    Customer ivan;

    @Before
    public void setIvan() {
        ivan = new IvanChelovekov();
    }

    private void setCustomerToForm(Customer customer) {
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys(customer.getEmail());
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys(customer.getPassword());
        WebElement firstName = driver.findElement(By.id("firstName"));
        firstName.sendKeys(customer.getFirstName());
        WebElement lastName = driver.findElement(By.id("lastName"));
        lastName.sendKeys(customer.getLastName());
        WebElement patronymic = driver.findElement(By.id("patronymic"));
        patronymic.sendKeys(customer.getPatronymic());

        switch (customer.getSex()) {
            case MALE:
                driver.findElement(By.id("msex")).click();
                break;
            case FEMALE:
                driver.findElement(By.id("fsex")).click();
                break;
        }
        WebElement birthDate = driver.findElement(By.id("birthDate"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        birthDate.sendKeys(customer.getBirthDate().format(formatter));

        WebElement address = driver.findElement(By.id("address"));
        address.sendKeys(customer.getAddress());
        WebElement inn = driver.findElement(By.id("inn"));
        inn.sendKeys(customer.getInn());
    }

    @Test
    public void testAddIvan() {
        driver.get(homeUrl + "/customers/new");
        setCustomerToForm(ivan);
        driver.findElement(By.tagName("button")).click();
        assertThat(driver.findElement(By.tagName("h2")).getText(), equalTo("Регистрация прошла успешно!"));
        assertThat(isSaved(ivan), equalTo(true));
    }

    @Test
    public void testAddIvanWithoutPatronymic(){
        driver.get(homeUrl + "/customers/new");
        ivan.setPatronymic("");
        setCustomerToForm(ivan);
        driver.findElement(By.tagName("button")).click();
        assertThat(driver.findElement(By.tagName("h2")).getText(), equalTo("Регистрация прошла успешно!"));
        assertThat(isSaved(ivan), equalTo(true));
    }

    @Test
    public void testAddTenRandom() {
        for (int i = 0; i < 10; i++) {
            driver.get(homeUrl + "/customers/new");
            Customer newCustomer = new ValidRandomCustomer();
            setCustomerToForm(newCustomer);
            SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.YYYY");
            driver.findElement(By.tagName("button")).click();
            assertThat(isSaved(newCustomer), equalTo(true));
        }
    }


}

