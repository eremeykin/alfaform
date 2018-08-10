package pete.eremeykin.alfa.form.customer;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;

public class CustomerValidator implements Validator {

    private static final String REQUIRED = "это поле необходимо заполнить";
    private static final String INVALID = "неверное значение";

    @Override
    public void validate(Object obj, Errors errors) {
        Customer customer = (Customer) obj;
        validateEmail(customer.getEmail(), errors);
        validatePassword(customer.getPassword(), errors);
        validateString(customer.getFirstName(), errors, "firstName");
        validateString(customer.getLastName(), errors, "lastName");
        validateBirthDate(customer.getBirthDate(), errors);
        validateString(customer.getAddress(), errors, "address");
        validateInn(customer.getInn(), errors);
    }

    private void validateEmail(String email, Errors errors) {
        final String field = "email";
        if (!StringUtils.hasLength(email)) {
            errors.rejectValue(field, REQUIRED, REQUIRED);
            return;
        }
        String[] emailParts = StringUtils.split(email, "@");
        if (emailParts == null || emailParts.length != 2) {
            errors.rejectValue(field, INVALID, "email должен содержать ровно один символ \"@\" ");
            return;
        }
        String left = emailParts[0];
        if (!StringUtils.hasLength(left)) {
            errors.rejectValue(field, INVALID, "имя пользователя в email не должно быть пустым");
            return;
        }
        String right = emailParts[1];
        if (!StringUtils.hasLength(right)) {
            errors.rejectValue(field, INVALID, "домен email не должен быть пустым");
            return;
        }
        if (!StringUtils.hasText(".")) {
            errors.rejectValue(field, INVALID, "домен email должен содержать точку");
        }
    }

    private void validateString(String name, Errors errors, String field) {
        if (!StringUtils.hasLength(name)) {
            errors.rejectValue(field, REQUIRED, REQUIRED);
            return;
        }
    }

    private void validatePassword(String password, Errors errors) {
        final String field = "password";
        if (!StringUtils.hasLength(password)) {
            errors.rejectValue(field, REQUIRED, REQUIRED);
            return;
        }
        if (password.length() < 5) {
            errors.rejectValue(field, INVALID, "слишком короткий пароль");
        }
    }

    public void validateBirthDate(Date birthDate, Errors errors) {
        final String field = "birthDate";
        if (birthDate == null) {
            errors.rejectValue(field, REQUIRED, REQUIRED);
        }
    }

    public void validateInn(String inn, Errors errors) {
        final String field = "inn";
        if (!StringUtils.hasLength(inn)) {
            errors.rejectValue(field, REQUIRED, REQUIRED);
        }
        if (inn.length() != 12) {
            errors.rejectValue(field, INVALID, "ИНН физического лица состоит из 12 цифр");
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Customer.class.isAssignableFrom(clazz);
    }

}
