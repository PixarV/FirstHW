package Threads.FirstEx;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransactionContent {
    int accountFrom;
    int accountTo;
    double amount;
}
