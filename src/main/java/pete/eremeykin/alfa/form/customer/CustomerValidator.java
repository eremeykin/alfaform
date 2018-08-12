package pete.eremeykin.alfa.form.customer;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.util.Calendar;

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
        validateSex(customer.getSex(), errors);
    }

    private void validateEmail(String email, Errors errors) {
        final String field = "email";
        if (!StringUtils.hasLength(email)) {
            errors.rejectValue(field, REQUIRED, REQUIRED);
            return;
        }
        String[] emailParts = StringUtils.split(email, "@");
        if (emailParts == null|| StringUtils.countOccurrencesOf(email, "@")!=1 || emailParts.length != 2) {
            errors.rejectValue(field, INVALID, "email должен содержать ровно один символ \"@\" ");
            return;
        }
        String left = emailParts[0];
        String right = emailParts[1];
        if (!StringUtils.hasLength(left)) {
            errors.rejectValue(field, INVALID, "имя пользователя в email не должно быть пустым");
        } else if (!StringUtils.hasLength(right)) {
            errors.rejectValue(field, INVALID, "домен email не должен быть пустым");
        } else if (!StringUtils.hasText(".")) {
            errors.rejectValue(field, INVALID, "домен email должен содержать точку");
        }
    }

    private void validateString(String string, Errors errors, String field) {
        if (!StringUtils.hasLength(string)) {
            errors.rejectValue(field, REQUIRED, REQUIRED);
        } else if (string.length() > 70) {
            errors.rejectValue(field, INVALID, "слишком длинное значение");
        }
    }

    private void validatePassword(String password, Errors errors) {
        final String field = "password";
        if (!StringUtils.hasLength(password)) {
            errors.rejectValue(field, REQUIRED, REQUIRED);
        } else if (password.length() < 5) {
            errors.rejectValue(field, INVALID, "слишком короткий пароль");
        } else if (password.length() > 70) {
            errors.rejectValue(field, INVALID, "длина пароля вне мыслимого диапазона");
        }
    }

    private void validateBirthDate(LocalDate birthDate, Errors errors) {
        final String field = "birthDate";
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if (birthDate == null) {
            errors.rejectValue(field, REQUIRED, REQUIRED);
        } else if (birthDate.getYear() < 1850 || birthDate.getYear() > currentYear - 10) {
            errors.rejectValue(field, INVALID, "значение вне допустимого диапазона");
        }
    }

    private void validateInn(String inn, Errors errors) {
        final String field = "inn";
        if (!StringUtils.hasLength(inn)) {
            errors.rejectValue(field, REQUIRED, REQUIRED);
        } else if (inn.length() != 12) {
            errors.rejectValue(field, INVALID, "ИНН физического лица состоит из 12 цифр");
        }
    }

    private void validateSex(Customer.Sex sex, Errors errors) {
        final String field = "sex";
        if (sex == null) {
            errors.rejectValue(field, REQUIRED, REQUIRED);
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Customer.class.isAssignableFrom(clazz);
    }
}
