package dt.banco.bot.chatbot.Model.Response;

public class BalanceResponse extends Response {
    private Integer balance;

    public BalanceResponse() {
        super(200, "NOK", "NOK");
        this.balance = null;
    }

    public BalanceResponse(Integer balance) {
        super(200, "OK", "Your balance is: " + balance);
        this.balance = balance;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
