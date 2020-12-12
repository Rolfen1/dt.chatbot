package dt.banco.bot.chatbot.Model.ModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import dt.banco.bot.chatbot.Controller.PersonController;
import dt.banco.bot.chatbot.Model.Person;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PersonModelAssembler implements RepresentationModelAssembler<Person, EntityModel<Person>> {
    @Override
    public EntityModel<Person> toModel(Person person) {

        return EntityModel.of(person, //
                linkTo(methodOn(PersonController.class).GetPersonById(person.getId())).withSelfRel());
    }
}
