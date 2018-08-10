package pete.eremeykin.alfa.form.customer;

import org.springframework.format.annotation.DateTimeFormat;
import pete.eremeykin.alfa.form.model.BaseEntity;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "dd.MM.YYYY")
    private Date birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private Sex sex;

    @Column(name = "address")
    private String address;

    @Column(name = "inn")
    private String inn;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Sex getSex() {
        return sex;
    }

    public String getAddress() {
        return address;
    }

    public String getInn() {
        return inn;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    enum Sex {
        MALE,FEMALE
    }

}
