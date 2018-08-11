package pete.eremeykin.alfa.form;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testStartHome() throws Exception {
        ResultActions res = this.mockMvc.perform(get("/"));
        res = res.andExpect(status().isOk());
        res = res.andExpect(content().string(containsString("Привет!")));
    }

    @Test
    public void testOpenFormDirectly() throws Exception {
        ResultActions res = this.mockMvc.perform(get("/customers/new"));
        res = res.andExpect(status().isOk());
        res = res.andExpect(content().string(containsString("<h2>Регистрационная форма</h2>")));
    }

    @Test
    public void testOpenFormFromHome() throws Exception {
        WebDriver d = new HtmlUnitDriver();
        d.get("http://localhost:8080");
        System.out.println(d.getTitle());
    }

}
