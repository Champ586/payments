package account.model;

/**
 * Специальное исключение для операций со счётом
 */
public class AccountException extends Exception {
    public AccountException(String message) {
        super(message);
    }

    public static AccountException getAccountNotFoundException(Long accountId) {
        return new AccountException(String.format("Account with id=%d not found", accountId));
    }

    public static AccountException getNegativeBalanceException(Long accountId) {
        return new AccountException(String.format("Insufficient funds in the account with id=%d", accountId));
    }

    public static AccountException getWrongRangeException() {
        return new AccountException("Amount must be postive");
    }
}
