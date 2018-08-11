package pete.eremeykin.alfa.form.utils;

import pete.eremeykin.alfa.form.customer.Customer;

import java.time.LocalDate;
import java.util.Date;

// just a sample valid user
public class IvanChelovekov extends Customer {

    public IvanChelovekov(){
        this.setEmail("ivan@example.com");
        this.setPassword("ivAnChel1337");
        this.setFirstName("Иван");
        this.setLastName("Человеков");
        this.setPatronymic("Петрович");
        this.setBirthDate(LocalDate.of(1975,5,9));
        this.setSex(Customer.Sex.MALE);
        this.setAddress("г. Москва, ул. Пешеходная, д. 3");
        this.setInn("414509012402");
    }

}
