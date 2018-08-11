package pete.eremeykin.alfa.form.customer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface CustomerRepository extends Repository<Customer, Integer> {

    @Transactional(readOnly = true)
    Customer findById(Integer id);

    @Query("SELECT customer FROM Customer customer")
    @Transactional(readOnly = true)
    Collection<Customer> findAll();

    void save(Customer customer);

}
