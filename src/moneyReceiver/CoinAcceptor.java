package moneyReceiver;

public class CoinAcceptor implements MoneyReceiver{
    private int amount;

    public CoinAcceptor(int amount) {
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
        amount+=10;
        System.out.println("Вы пополнили баланс на 10");
    }
}
