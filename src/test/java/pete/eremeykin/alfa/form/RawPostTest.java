package pete.eremeykin.alfa.form;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pete.eremeykin.alfa.form.customer.Customer;
import pete.eremeykin.alfa.form.utils.IvanChelovekov;
import pete.eremeykin.alfa.form.utils.ValidRandomCustomer;

import java.nio.charset.Charset;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RawPostTest extends SeleniumTest {

    Customer ivan;

    @Before
    public void setIvan() {
        ivan = new IvanChelovekov();
    }

    private class MyNameValue extends BasicNameValuePair {
        MyNameValue(String name, String value) {
            super(name, value);
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            } else if (!(object instanceof NameValuePair)) {
                return false;
            } else {
                BasicNameValuePair that = (BasicNameValuePair) object;
                return this.getName().equals(that.getName());
            }
        }
    }

    private void fillParams(Set<MyNameValue> params, Customer customer) {
        params.add(new MyNameValue("email", customer.getEmail()));
        params.add(new MyNameValue("password", customer.getPassword()));
        params.add(new MyNameValue("firstName", customer.getFirstName()));
        params.add(new MyNameValue("lastName", customer.getLastName()));
        params.add(new MyNameValue("patronymic", customer.getPatronymic()));
        params.add(new MyNameValue("sex", customer.getSex().toString()));
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        params.add(new MyNameValue("birthDate", customer.getBirthDate().format(fmt)));
        params.add(new MyNameValue("address", customer.getAddress()));
        params.add(new MyNameValue("inn", customer.getInn()));
    }

    @Test
    public void testPostNewValidRandomCustomer() throws Exception {
        driver.get(homeUrl + "/customers");
        int before = driver.findElements(By.tagName("tr")).size();

        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(homeUrl + "/customers/new");
        Set<MyNameValue> params = new HashSet<>(9);
        Customer customer = new ValidRandomCustomer();
        fillParams(params, customer);
        httppost.setEntity(new UrlEncodedFormEntity(params, Charset.forName("UTF-8")));

        httpclient.execute(httppost);

        driver.get(homeUrl + "/customers");
        int after = driver.findElements(By.tagName("tr")).size();
        assertThat(isSaved(customer), equalTo(true));
        assertThat(after, equalTo(before + 1));
    }

    @Test
    public void testPostWrongDateFormat() throws Exception {
        driver.get(homeUrl + "/customers");
        int before = driver.findElements(By.tagName("tr")).size();

        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(homeUrl + "/customers/new");
        Set<MyNameValue> params = new HashSet<>(9);
        Customer customer = new ValidRandomCustomer();
        fillParams(params, customer);
        params.add(new MyNameValue("birthDate", "1984.04.17"));
        httppost.setEntity(new UrlEncodedFormEntity(params, Charset.forName("UTF-8")));

        httpclient.execute(httppost);

        driver.get(homeUrl + "/customers");
        int after = driver.findElements(By.tagName("tr")).size();
        assertThat(isSaved(customer), equalTo(false));
        assertThat(after, equalTo(before));//TODO fix this test
    }
}
