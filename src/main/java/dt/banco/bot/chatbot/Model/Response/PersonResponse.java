package dt.banco.bot.chatbot.Model.Response;

import dt.banco.bot.chatbot.Model.ModelAssembler.PersonModelAssembler;
import dt.banco.bot.chatbot.Model.Person;
import org.springframework.hateoas.EntityModel;

public class PersonResponse extends Response {
    private EntityModel<Person> person;

    public PersonResponse() {
        super(200, "NOK", "NOK");
        this.person = null;
    }

    public PersonResponse(Person person, PersonModelAssembler assembler) {
        super(200, "OK", "OK");
        this.person = assembler.toModel(person);
    }

    public PersonResponse(Integer statusCode, String status, String message, EntityModel<Person> person) {
        super(statusCode, status, message);
        this.person = person;
    }

    public EntityModel<Person> getPerson() {
        return person;
    }

    public void setPerson(EntityModel<Person> person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "{" +
                "StatusCode:" + getStatusCode() +
                ", Status:'" + getStatus() + '\'' +
                ", Message:'" + getMessage() + '\'' +
                ", Person:" + person +
                '}';
    }
}
