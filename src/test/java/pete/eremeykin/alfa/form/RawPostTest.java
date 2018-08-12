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

    private HttpClient httpClient;
    private HttpPost httpPost;
    private Set<MyNameValue> httpParams;

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

    private void fillParams(Customer customer) {
        httpParams.add(new MyNameValue("email", customer.getEmail()));
        httpParams.add(new MyNameValue("password", customer.getPassword()));
        httpParams.add(new MyNameValue("firstName", customer.getFirstName()));
        httpParams.add(new MyNameValue("lastName", customer.getLastName()));
        httpParams.add(new MyNameValue("patronymic", customer.getPatronymic()));
        httpParams.add(new MyNameValue("sex", customer.getSex().toString()));
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        httpParams.add(new MyNameValue("birthDate", customer.getBirthDate().format(fmt)));
        httpParams.add(new MyNameValue("address", customer.getAddress()));
        httpParams.add(new MyNameValue("inn", customer.getInn()));
    }

    @Before
    public void prepareHttpClient(){
        httpClient = HttpClients.createDefault();
        httpPost = new HttpPost(homeUrl + "/customers/new");
        httpParams = new HashSet<>(9);
    }

    private int countCustomersTableRows(){
        driver.get(homeUrl + "/customers");
        return driver.findElements(By.tagName("tr")).size();
    }

    @Test
    public void testPostNewValidRandomCustomer() throws Exception {
        int rowsBefore = countCustomersTableRows();
        Customer customer = new ValidRandomCustomer();
        fillParams(customer);
        httpPost.setEntity(new UrlEncodedFormEntity(httpParams, Charset.forName("UTF-8")));
        httpClient.execute(httpPost);
        int rowsAfter  = countCustomersTableRows();
        assertThat(isSaved(customer), equalTo(true));
        assertThat(rowsAfter, equalTo(rowsBefore + 1));
    }

    @Test
    public void testPostWrongDateFormat() throws Exception {
        int rowsBefore = countCustomersTableRows();
        Customer customer = new ValidRandomCustomer();
        fillParams(customer);
        httpParams.add(new MyNameValue("birthDate", "1984.04.17"));
        httpPost.setEntity(new UrlEncodedFormEntity(httpParams, Charset.forName("UTF-8")));
        httpClient.execute(httpPost);
        int rowsAfter = countCustomersTableRows();
        assertThat(isSaved(customer), equalTo(false));
        // invalid user must not be inserted !
        assertThat(rowsAfter, equalTo(rowsBefore));//TODO fix this test
    }
}
