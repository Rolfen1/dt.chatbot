package dt.banco.bot.chatbot.Model.Request;

public class TransferRequest {
    private Integer amount;
    private Integer from;
    private Integer to;

    public TransferRequest() {
    }

    public TransferRequest(Integer amount, Integer from, Integer to) {
        this.amount = amount;
        this.from = from;
        this.to = to;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "{" +
                "amount:" + amount +
                ", from:" + from +
                ", to:" + to +
                '}';
    }
}
