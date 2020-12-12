package dt.banco.bot.chatbot.Controller;

import dt.banco.bot.chatbot.Model.ModelAssembler.PersonModelAssembler;
import dt.banco.bot.chatbot.Model.Person;
import dt.banco.bot.chatbot.Model.Repository.PersonRepository;
import dt.banco.bot.chatbot.Model.Response.PersonResponse;
import dt.banco.bot.chatbot.Model.Response.PersonsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class PersonController {
    private final PersonRepository repository;
    private final PersonModelAssembler assembler;
    private static final Logger log = LoggerFactory.getLogger(PersonController.class);

    public PersonController(PersonRepository repository, PersonModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/Person/All")
    public PersonsResponse All() {
        PersonsResponse response;

        try {
            response = new PersonsResponse(repository.findAll(), assembler);
        } catch (Exception e) {
            response = new PersonsResponse();
            response.setMessage("Exception: " + e);
            log.error("Exception: ", e);
        }

        return response;
    }

    @PostMapping("/Person/New")
    public PersonResponse NewPerson(@RequestBody Person newPerson) {
        PersonResponse response;

        try {
            log.info("Creating new person");
            newPerson = repository.save(newPerson);
            response = new PersonResponse(newPerson, assembler);

            if (response.getPerson() != null) {
                response.setStatus("OK");
                response.setStatusCode(201);
                response.setMessage("Successfully created Person with id (" + newPerson.getId() + ")");
                log.info(response.getMessage());
            }
        } catch (Exception e) {
            response = new PersonResponse();
            response.setMessage("Exception: " + e);
            log.error("Exception: ", e);
        }

        return response;
    }

    @GetMapping("/Person/Get/{id}")
    public PersonResponse GetPersonById(@PathVariable Long id) {
        PersonResponse response;
        Person requestedPerson;

        try {
            log.info("Querying person by id (" + id + ")");
            requestedPerson = repository.findById(id).orElse(null);
            response = new PersonResponse(requestedPerson, assembler);

            if (response.getPerson() != null) {
                response.setStatus("OK");
                response.setMessage("Person found");
            } else {
                response.setMessage("Person id (" + id + ") not found");
            }

            log.info(response.getMessage());
        } catch (Exception e) {
            response = new PersonResponse();
            response.setMessage("Exception: " + e);
            log.error("Exception: ", e);
        }

        return response;
    }
}
