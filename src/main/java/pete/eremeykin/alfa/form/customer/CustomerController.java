package pete.eremeykin.alfa.form.customer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;

@Controller
public class CustomerController {

    private static final String NEW_CUSTOMER_FORM = "customers/newCustomerForm";
    private static final String SUCCESS = "/success";

    private final CustomerRepository customers;

    public CustomerController(CustomerRepository customers) {
        this.customers = customers;
    }

    @InitBinder("customer")
    public void initCustomerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
        dataBinder.setValidator(new CustomerValidator());
    }

    @GetMapping("/customers/new")
    public String initCustomerForm(Map<String, Object> model) {
        Customer customer = new Customer();
        model.put("customer", customer);
        return NEW_CUSTOMER_FORM;
    }

    @PostMapping("/customers/new")
    public String processCustomerCreationForm(@Valid Customer customer, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.put("customer", customer);
            return NEW_CUSTOMER_FORM;
        } else {
            this.customers.save(customer);
            return SUCCESS;
        }
    }

    @GetMapping("/customers")
    public String processAllCustomers(Map<String, Object> model) {
        Collection<Customer> results = this.customers.findAll();
        model.put("customers", results);
        return "customers/customersList";
    }
}
