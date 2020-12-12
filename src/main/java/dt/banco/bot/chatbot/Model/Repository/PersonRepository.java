package dt.banco.bot.chatbot.Model.Repository;

import dt.banco.bot.chatbot.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("select p from Person p where p.accountNumber = ?1")
    Person findByAccountNumber(Integer accountNumber);
}
