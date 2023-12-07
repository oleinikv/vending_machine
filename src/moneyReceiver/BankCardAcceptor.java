package moneyReceiver;

import exceptions.CustomException;
import util.ConsoleUtils;

public class BankCardAcceptor implements MoneyReceiver {
    private int amount;
    private int cardBalance;


    public BankCardAcceptor() {
        this(1200, 20);
    }

    public BankCardAcceptor(int cardBalance, int amount) {
        this.cardBalance = cardBalance;
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

        while (true) {
            String cardPAN = getCardPAN();
            String oneTimePassword = getOneTimePassword();
            ConsoleUtils.print("Баланс карты: " + cardBalance);
            int sum = ConsoleUtils.getInteger("Введите сумму пополнения: ");
            if (sum <= cardBalance) {
                amount += sum;
                cardBalance -= sum;
                break;
            } else {
                ConsoleUtils.printError("Недостаточно средств на балансе карты.");
            }
        }

    }

    private String getOneTimePassword() {
        String password = ConsoleUtils.getString("Введите одноразовый пароль: ");
        try {
            if (password.length() != 5) {
                throw new CustomException("Вы ввели не верный пароль. Повторите ввод");
            }
        } catch (CustomException e) {
            ConsoleUtils.printError(e.getMessage());
            return getOneTimePassword();
        }
        return password;
    }

    private String getCardPAN() {
        String PAN = ConsoleUtils.getString("Введите номер карты (16 цифр): ");
        try {

            if (PAN.length() != 16) {
                throw new CustomException("Введен не верный номер карты. Повторите ввод");
            }
            long iPAN = Long.parseLong(PAN);


        } catch (CustomException ce) {
            ConsoleUtils.printError(ce.getMessage());
            return getCardPAN();
        } catch (NumberFormatException ne) {
            ConsoleUtils.printError("Вы ввели не верный номер карты. Повторите ввод");
            return getCardPAN();
        }
        return PAN;
    }
}
