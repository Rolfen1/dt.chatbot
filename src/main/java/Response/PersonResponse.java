package Response;

import Model.Person;

public class PersonResponse extends Response {
    private Person person;

    public PersonResponse() {
        super(200, "NOK", "NOK");
        this.person = null;
    }

    public PersonResponse(Person person) {
        super(200, "OK", "OK");
        this.person = person;
    }

    public PersonResponse(Integer statusCode, String status, String message, Person person) {
        super(statusCode, status, message);
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "{" +
                "StatusCode:" + getStatusCode() +
                ", Status:'" + getStatus() + '\'' +
                ", Message:'" + getMessage() + '\'' +
                ", Person:'" + person.toString() + '\'' +
                '}';
    }
}
