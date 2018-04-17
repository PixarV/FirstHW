package Threads.FirstEx;

import lombok.SneakyThrows;

import java.util.Random;

public class WriteTransactionsThread extends Thread {
    private boolean run = true;
    @Override
    @SneakyThrows
    public void run() {
        Random random = new Random();

        while(run) {
            int from = random.nextInt(8999) + 1000;
            int to = random.nextInt(8999) + 1000;
            double amount = random.nextDouble()*100;
            MoneyTransactionsLock.addTransaction(new TransactionContent(from, to, amount));
            Thread.sleep(5000);
            if (random.nextBoolean())
                MoneyTransactionsLock.getTransactions();

        }
    }

    public void stopThread() {
        run = false;
    }

    @SneakyThrows
    public static void main(String... args) {
        WriteTransactionsThread t1 = new WriteTransactionsThread();
        WriteTransactionsThread t2 = new WriteTransactionsThread();
        WriteTransactionsThread t3 = new WriteTransactionsThread();

        t1.start();
        t2.start();
        t3.start();

        Thread.sleep(100);

        t1.stopThread();
        t2.stopThread();
        t3.stopThread();
    }
}
