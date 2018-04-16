package Threads.FirstEx;

import lombok.SneakyThrows;

import java.util.Random;

public class WriteTransactionsThread extends Thread {
    @Override
    @SneakyThrows
    public void run() {
        Random random = new Random();

        while(true) {
            int from = random.nextInt(8999) + 1000;
            int to = random.nextInt(8999) + 1000;
            double amount = random.nextDouble()*100;
            MoneyTransactionsLock.addTransaction(new TransactionContent(from, to, amount));
            Thread.sleep(5000);
            if (random.nextBoolean())
                MoneyTransactionsLock.getTransactions();

        }
    }

    public static void main(String... args) {
        new WriteTransactionsThread().start();
        new WriteTransactionsThread().start();
        new WriteTransactionsThread().start();
    }
}
