package Controller;

import Model.Person;
import Model.PersonRepository;
import Response.PersonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
    private final PersonRepository repository;
    private static final Logger log = LoggerFactory.getLogger(PersonController.class);

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/Person/All")
    List<Person> All() {
        return repository.findAll();
    }

    @PostMapping("/Person/New")
    Person NewPerson(@RequestBody Person newPerson) {
        return repository.save(newPerson);
    }

    @GetMapping("/Person/Get/{id}")
    PersonResponse GetPersonById(@PathVariable Long id) {
        PersonResponse response = new PersonResponse();
        Person requestedPerson;

        try {
            log.info("Querying person by id (" + id + ")");
            requestedPerson = repository.findById(id).orElse(null);
            response.setPerson(requestedPerson);
        } catch (Exception e) {
            log.error("Exception: " + e);
        }

        return response;
    }
}
