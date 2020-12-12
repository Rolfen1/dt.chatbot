package dt.banco.bot.chatbot.Controller;

import dt.banco.bot.chatbot.Model.ModelAssembler.PersonModelAssembler;
import dt.banco.bot.chatbot.Model.Person;
import dt.banco.bot.chatbot.Model.Repository.PersonRepository;
import dt.banco.bot.chatbot.Model.Request.TransferRequest;
import dt.banco.bot.chatbot.Model.Response.TransferResponse;
import dt.banco.bot.chatbot.Model.TransferReceipt;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @PostMapping(value ="/Transfer/ToAccount", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> TransferToAccount(@RequestBody TransferRequest request) {
        try {
            Person from = personRepository.findByAccountNumber(request.getFrom());
            Person to = personRepository.findByAccountNumber(request.getTo());

            if (from != null && to != null) {
                if (from.getBalance() >= request.getAmount()) {
                    from.setBalance(from.getBalance() - request.getAmount());
                    to.setBalance(to.getBalance() + request.getAmount());
                    personRepository.save(from);
                    personRepository.save(to);

                    TransferReceipt receipt = new TransferReceipt(from, to, request.getAmount());

                    log.info("The money transfer has been successful");

                    HttpHeaders headers = new HttpHeaders();
                    headers.add("Content-Disposition", "inline; filename=receipt.pdf");
                    return ResponseEntity
                            .ok()
                            .header(headers.toString())
                            .contentType(MediaType.APPLICATION_PDF)
                            .body(new InputStreamResource(receipt.toPdf()));
                } else {
                    log.info("The balance of the origin account is less than the amount to be transferred");
                }
            } else {
                String message = from == null ? "The account number (" + request.getFrom() + ") couldn't be found. " : "";
                message = message + (to == null ? "The account number (" + request.getTo() + ") couldn't be found." : "");
                log.info(message);
            }
        } catch (Exception e) {
            log.error("Exception: ", e);
        }
        return ResponseEntity.badRequest().build();
    }
}
