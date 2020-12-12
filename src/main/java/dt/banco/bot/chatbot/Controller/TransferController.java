package dt.banco.bot.chatbot.Controller;

import dt.banco.bot.chatbot.Model.ModelAssembler.PersonModelAssembler;
import dt.banco.bot.chatbot.Model.Person;
import dt.banco.bot.chatbot.Model.Repository.PersonRepository;
import dt.banco.bot.chatbot.Model.Request.TransferRequest;
import dt.banco.bot.chatbot.Model.Response.TransferResponse;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {
    private final PersonRepository personRepository;
    private final PersonModelAssembler personAssembler;
    private static final Logger log = LoggerFactory.getLogger(TransferController.class);

    public TransferController(PersonRepository personRepository, PersonModelAssembler personAssembler) {
        this.personRepository = personRepository;
        this.personAssembler = personAssembler;
    }

    @PostMapping("/Transfer/ToAccount")
    public TransferResponse TransferToAccount(@RequestBody TransferRequest request) {
        TransferResponse response = new TransferResponse();

        try {
            Person from = personRepository.findByAccountNumber(request.getFrom());
            Person to = personRepository.findByAccountNumber(request.getTo());

            if (from != null && to != null) {
                if (from.getBalance() >= request.getAmount()) {
                    from.setBalance(from.getBalance() - request.getAmount());
                    to.setBalance(to.getBalance() + request.getAmount());
                    personRepository.save(from);
                    personRepository.save(to);

                    response.setPdf("pdf");
                    response.setStatus("OK");
                    response.setMessage("The money transfer has been successful");
                } else {
                    response.setMessage("The balance of the origin account is less than the amount to be transferred");
                }
                log.info(response.getMessage());
            } else {
                String message = from == null ? "The account number (" + request.getFrom() + ") couldn't be found. " : "";
                message = message + (to == null ? "The account number (" + request.getTo() + ") couldn't be found." : "");
                response.setMessage(message);
                log.info(message);
            }
        } catch (Exception e) {
            response = new TransferResponse();
            response.setMessage("Exception: " + e);
            log.error("Exception: ", e);
        }

        return response;
    }
}
