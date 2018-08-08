package pete.eremeykin.alfa.form;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:config.xml")
public class AlfaForm {
    public static void main(String[] args)  throws Throwable{
            SpringApplication.run(AlfaForm.class, args);
    }
}
