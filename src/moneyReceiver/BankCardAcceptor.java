package moneyReceiver;

public class BankCardAcceptor implements MoneyReceiver{
    private int amount;

    public BankCardAcceptor(int amount) {
        this.amount = amount;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public void addMoney() {

    }
}
