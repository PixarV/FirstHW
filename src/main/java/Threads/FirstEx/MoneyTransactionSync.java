package Threads.FirstEx;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

// transaction: account_number_from account_number_to amount (in rub.)
public class MoneyTransactionSync {
    private MoneyTransactionSync() {}

    @SneakyThrows
    public static BufferedReader getTransactions() {
        return new BufferedReader(new FileReader("transactions"));
    }

    @SneakyThrows
    public static synchronized void addTransaction(TransactionContent transaction) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("transactions", true))) {
            writer.write(String.format("\n%d %d %f",
                    transaction.getAccountFrom(),
                    transaction.getAccountTo(),
                    transaction.getAmount()));
        }
    }
}
