package Threads.FirstEx;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// transaction: account_number_from account_number_to amount (in rub.)
public class MoneyTransactionsLock {
    private MoneyTransactionsLock() {}

    @SneakyThrows
    public static BufferedReader getTransactions() {
        return new BufferedReader(new FileReader("transactions"));
    }

    @SneakyThrows
    public static void addTransaction(TransactionContent transaction) {
        Lock lock = new ReentrantLock();
        lock.lock();
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("transactions", true))) {
            writer.write(String.format("\n%d %d %f",
                    transaction.getAccountFrom(),
                    transaction.getAccountTo(),
                    transaction.getAmount()));
        } finally {
            lock.unlock();
        }
    }
}

