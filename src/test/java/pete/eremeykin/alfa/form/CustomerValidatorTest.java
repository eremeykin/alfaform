package pete.eremeykin.alfa.form;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pete.eremeykin.alfa.form.customer.Customer;
import pete.eremeykin.alfa.form.customer.CustomerValidator;
import pete.eremeykin.alfa.form.utils.IvanChelovekov;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;


public class CustomerValidatorTest {

    private static final String REQUIRED = "это поле необходимо заполнить";
    private static final String INVALID = "неверное значение";

    Validator validator;

    Customer ivan;

    Errors mockErrors;

    ErrorsAnswer answer;

    @Before
    public void setValidCustomer() {
        ivan = new IvanChelovekov();
    }

    @Before
    public void setCustomerValidator() {
        validator = new CustomerValidator();
    }

    private class ErrorsAnswer implements Answer<Void> {

        private final List<ErrorsRecord> records = new ArrayList<>();

        class ErrorsRecord {
            String field;
            String errorCode;
            String defaultMessage;

            ErrorsRecord(String field, String errorCode, String defaultMessage) {
                this.field = field;
                this.errorCode = errorCode;
                this.defaultMessage = defaultMessage;
            }
        }

        @Override
        public Void answer(InvocationOnMock invocation) throws Throwable {
            String methodName = invocation.getMethod().getName();
            if (methodName.equals("rejectValue")) {
                String field = invocation.getArgument(0);
                String errorCode = invocation.getArgument(1);
                String defaultMessage = invocation.getArgument(2);
                records.add(new ErrorsRecord(field, errorCode, defaultMessage));
            }
            return null;
        }
    }

    @Before
    public void setMockErrors() {
        answer = new ErrorsAnswer();
        mockErrors = mock(Errors.class, answer);
    }

    @Test
    public void testSupportsCustomer() {
        assertThat(validator.supports(Customer.class), equalTo(true));
        assertThat(validator.supports(CustomerValidator.class), equalTo(false));
        class CustomerChild extends Customer {
        }
        assertThat(validator.supports(CustomerChild.class), equalTo(true));
        assertThat(validator.supports(Object.class), equalTo(false));
        assertThat(validator.supports(ivan.getClass()), equalTo(true));
    }

    @Test
    public void testValid() {
        validator.validate(ivan, mockErrors);
        assertThat(answer.records, hasSize(0));
    }

    @Test
    public void testEmptyFirstName() {
        ivan.setFirstName("");
        validator.validate(ivan, mockErrors);
        assertFieldRequired("firstName");
    }

    @Test
    public void testEmptyLastName() {
        ivan.setLastName("");
        validator.validate(ivan, mockErrors);
        assertFieldRequired("lastName");
    }

    @Test
    public void testEmptyEmail() {
        ivan.setEmail("");
        validator.validate(ivan, mockErrors);
        assertFieldRequired("email");
    }

    @Test
    public void testWrongEmail() {
        ivan.setEmail("abcd@");
        validator.validate(ivan, mockErrors);
        assertEmailInvalid();
    }

    @Test
    public void testEmptyPassword() {
        ivan.setPassword("");
        validator.validate(ivan, mockErrors);
        assertFieldRequired("password");
    }

    private void assertFieldRequired(String field) {
        assertThat(answer.records, hasSize(1));
        assertThat(answer.records.get(0).field, equalTo(field));
        assertThat(answer.records.get(0).errorCode, equalTo(REQUIRED));
    }

    private void assertEmailInvalid() {
        boolean oneInvalid = false;
        for (ErrorsAnswer.ErrorsRecord rec : answer.records) {
            if (rec.field.equals("email") && rec.errorCode.equals(INVALID))
                return;
        }
        fail("Email is not invalid");
    }
}
