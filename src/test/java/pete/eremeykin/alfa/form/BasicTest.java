package pete.eremeykin.alfa.form;

import org.junit.Before;
import org.springframework.boot.web.server.LocalServerPort;
import pete.eremeykin.alfa.form.customer.Customer;
import pete.eremeykin.alfa.form.utils.IvanChelovekov;

import java.io.IOException;
import java.util.Properties;

public class BasicTest {

    String homeUrl;

    Customer ivan;

    @LocalServerPort
    int port;


    @Before
    public void setIvan() {
        ivan = new IvanChelovekov();
    }

    @Before
    public void setUrl() throws IOException {
        Properties p = new Properties();
        p.load(ClassLoader.getSystemResourceAsStream("test.properties"));
        homeUrl= p.getProperty("url")+ ":" + port;
    }
}
