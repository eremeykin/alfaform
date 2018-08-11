package pete.eremeykin.alfa.form;

import org.junit.Before;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BasicTest {

    String homeUrl;

    @LocalServerPort
    int port;

    @Before
    public void setUrl() throws IOException {
        Properties p = new Properties();
        InputStream is = ClassLoader.getSystemResourceAsStream("test.properties");
        p.load(is);
        homeUrl = p.getProperty("url");
        homeUrl += ":" + port;
    }
}
