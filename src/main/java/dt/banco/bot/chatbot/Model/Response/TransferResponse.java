package dt.banco.bot.chatbot.Model.Response;

import dt.banco.bot.chatbot.Controller.TransferController;
import org.springframework.hateoas.EntityModel;

public class TransferResponse extends Response {
    private String pdf;

    public TransferResponse() {
        super(200, "NOK", "NOK");
        this.pdf = null;
    }

    public TransferResponse(String pdf) {
        super(200, "OK", "OK");
        this.pdf = pdf;
    }

    public TransferResponse(Integer statusCode, String status, String message, String pdf) {
        super(statusCode, status, message);
        this.pdf = pdf;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }
}
