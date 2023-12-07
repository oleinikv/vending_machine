import enums.ActionLetter;
import model.*;
import moneyReceiver.BankCardAcceptor;
import moneyReceiver.CoinAcceptor;
import moneyReceiver.MoneyReceiver;
import util.ConsoleUtils;
import util.UniversalArray;
import util.UniversalArrayImpl;

public class AppRunner {

    private final UniversalArray<Product> products = new UniversalArrayImpl<>();

    private static boolean isExit = false;

    private MoneyReceiver receiver;

    private AppRunner() {
        products.addAll(new Product[]{
                new Water(ActionLetter.B, 20),
                new CocaCola(ActionLetter.C, 50),
                new Soda(ActionLetter.D, 30),
                new Snickers(ActionLetter.E, 80),
                new Mars(ActionLetter.F, 80),
                new Pistachios(ActionLetter.G, 130)
        });
        receiver = new CoinAcceptor(10);
    }

    public static void run() {
        AppRunner app = new AppRunner();
        while (!isExit) {
            app.startSimulation();
        }
    }

    private void startSimulation() {
        ConsoleUtils.print("В автомате доступны:");
        showProducts(products);

        ConsoleUtils.print("Монет на сумму: " + receiver.getAmount());

        UniversalArray<Product> allowProducts = new UniversalArrayImpl<>();
        allowProducts.addAll(getAllowedProducts().toArray());
        chooseAction(allowProducts);

    }

    private UniversalArray<Product> getAllowedProducts() {
        UniversalArray<Product> allowProducts = new UniversalArrayImpl<>();
        for (int i = 0; i < products.size(); i++) {
            if (receiver.getAmount() >= products.get(i).getPrice()) {
                allowProducts.add(products.get(i));
            }
        }
        return allowProducts;
    }

    private void chooseAction(UniversalArray<Product> products) {
        ConsoleUtils.print(" a - Пополнить баланс");
        showActions(products);
        ConsoleUtils.print(" h - Выйти");
        String action = ConsoleUtils.getString("").substring(0, 1);

        if ("a".equalsIgnoreCase(action)) {
            chooseReceiver();
            receiver.addMoney();
            return;
        }
        try {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getActionLetter().equals(ActionLetter.valueOf(action.toUpperCase()))) {
                    receiver.setAmount(receiver.getAmount() - products.get(i).getPrice());
                    ConsoleUtils.printSuccess("Вы купили " + products.get(i).getName());
                    break;
                }
            }
        } catch (IllegalArgumentException e) {
            if ("h".equalsIgnoreCase(action)) {
                isExit = true;
            } else {
                ConsoleUtils.printError("Недопустимая буква. Попробуйте еще раз.");
                chooseAction(products);
            }
        }

    }

    private void showActions(UniversalArray<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            ConsoleUtils.print(String.format(" %s - %s", products.get(i).getActionLetter().getValue(), products.get(i).getName()));
        }
    }


    private void showProducts(UniversalArray<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            ConsoleUtils.print(products.get(i).toString());
        }
    }

    public void chooseReceiver() {
        while (true) {
            int num = ConsoleUtils.getInteger("Выберите способ пополнения (1 - Банковская карта, 2 - Монетами): ");
            if (num == 1) {
                receiver = new BankCardAcceptor();
                break;
            } else if (num == 2) {
                receiver = new CoinAcceptor(10);
                break;
            } else {
                ConsoleUtils.printSuccess("Вы ввели не допустимое значение");
            }
        }
    }

}
