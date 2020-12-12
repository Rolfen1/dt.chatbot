package dt.banco.bot.chatbot.Model.Response;

import dt.banco.bot.chatbot.Controller.PersonController;
import dt.banco.bot.chatbot.Model.ModelAssembler.PersonModelAssembler;
import dt.banco.bot.chatbot.Model.Person;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class PersonsResponse extends Response {
    private CollectionModel<EntityModel<Person>> persons;

    public PersonsResponse() {
        super(200, "NOK", "NOK");
        this.persons = null;
    }

    public PersonsResponse(List<Person> persons, PersonModelAssembler assembler) {
        super(200, "OK", "OK");
        this.persons = CollectionModel.of(persons.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList()),
                linkTo(methodOn(PersonController.class).All()).withSelfRel());
    }

    public PersonsResponse(Integer statusCode, String status, String message, CollectionModel<EntityModel<Person>> persons) {
        super(statusCode, status, message);
        this.persons = persons;
    }

    public CollectionModel<EntityModel<Person>> getPersons() {
        return persons;
    }

    public void setPersons(CollectionModel<EntityModel<Person>> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "{" +
                "StatusCode:" + getStatusCode() +
                ", Status:'" + getStatus() + '\'' +
                ", Message:'" + getMessage() + '\'' +
                ", Persons:" + persons +
                '}';
    }
}
