package pete.eremeykin.alfa.form.customer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class CustomerController {

    private static final  String NEW_CUSTOMER_FORM = "customers/newCustomerForm";

    private final CustomerRepository customers;

    public CustomerController(CustomerRepository customers){
        this.customers = customers;
    }

    @InitBinder
    public void initCustomerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/customers/new")
    public String initCustomerForm(Map<String, Object> model){
        Customer customer = new Customer();
        model.put("customer", customer);
        return NEW_CUSTOMER_FORM;
    }

    @PostMapping("/customers/new")
    public String processCreationForm(Customer customer, BindingResult result) {
        this.customers.save(customer);
        return NEW_CUSTOMER_FORM;
    }
}
