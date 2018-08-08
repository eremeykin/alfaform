package pete.eremeykin.alfa.form.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/testerror")
    public String triggerException() {
        System.out.println("Error test");
        throw new RuntimeException("Expected: controller used to showcase what "
                + "happens when an exception is thrown");
    }
}
