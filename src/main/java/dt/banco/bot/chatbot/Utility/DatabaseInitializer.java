package dt.banco.bot.chatbot.Utility;

import dt.banco.bot.chatbot.Model.Person;
import dt.banco.bot.chatbot.Model.Repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseInitializer {
    private static final Logger log = LoggerFactory.getLogger(DatabaseInitializer.class);

    @Bean
    CommandLineRunner initDatabase(PersonRepository repository){
        return args -> {
            log.info("Preloading Person " + repository.save(new Person("Ricardo Césped", "19.632.624-3", 196326243, 1000000)));
            log.info("Preloading Person " + repository.save(new Person("Tanisha Rachyl", "1.111.111-1", 11111111, 150000)));
            log.info("Preloading Person " + repository.save(new Person("Andrés Giorgio", "2.222.222-2", 22222222, 100000)));
            log.info("Preloading Person " + repository.save(new Person("Dragan Nkiruka", "3.333.333-3", 33333333, 40000)));
            log.info("Preloading Person " + repository.save(new Person("Kees Heard", "4.444.444-4", 44444444, 329650)));
            log.info("Preloading Person " + repository.save(new Person("Garnett Nana", "5.555.555-5", 55555555, 1289420)));
        };
    }

    public static void EmptyPersons(PersonRepository repository) {
        log.info("Deleting all persons stored in Database");
        repository.deleteAll();
    }
}
