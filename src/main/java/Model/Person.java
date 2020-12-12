package Model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Person {

    private @Id @GeneratedValue Long id;
    private String name;
    private String rut;
    private Integer accountNumber;
    private Integer balance;

    public Person() {
    }

    public Person(String name, String rut, Integer accountNumber, Integer balance) {
        this.name = name;
        this.rut = rut;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public Person(Long id, String name, String rut, Integer accountNumber, Integer balance) {
        this.id = id;
        this.name = name;
        this.rut = rut;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id.equals(person.id) && name.equals(person.name) && rut.equals(person.rut) && accountNumber.equals(person.accountNumber) && balance.equals(person.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rut, accountNumber);
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", name:'" + name + '\'' +
                ", rut:'" + rut + '\'' +
                ", accountNumber:" + accountNumber +
                ", balance:" + balance +
                '}';
    }
}