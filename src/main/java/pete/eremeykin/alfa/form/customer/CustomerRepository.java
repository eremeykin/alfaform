package pete.eremeykin.alfa.form.customer;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface CustomerRepository extends Repository<Customer, Integer> {

    @Transactional(readOnly = true)
    Customer findById(Integer id);

    void save(Customer customer);

}
