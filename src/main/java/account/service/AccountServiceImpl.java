package account.service;

import account.dto.entity.Account;
import account.dto.repository.AccountRepository;
import account.model.AccountException;
import account.model.operation.BalanceOperation;
import account.model.operation.TransferOperation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Account getAccountById(Long accountId) throws AccountException {
        return repository.findById(accountId).orElseThrow(() -> AccountException.getAccountNotFoundException(accountId));
    }

    @Override
    @Transactional(rollbackFor = {AccountException.class})
    public Account putMoneyIntoAccount(BalanceOperation operation) throws AccountException {
        return changeBalanceOfAccount(operation);
    }

    @Override
    @Transactional(rollbackFor = {AccountException.class})
    public Account withdrawMoneyFromAccount(BalanceOperation operation) throws AccountException {
        operation.negateAmount();
        return changeBalanceOfAccount(operation);
    }

    @Override
    @Transactional(rollbackFor = {AccountException.class})
    public void transferMoney(TransferOperation operation) throws AccountException {
        withdrawMoneyFromAccount(new BalanceOperation(operation.getAccountFromId(), operation.getAmount()));
        putMoneyIntoAccount(new BalanceOperation(operation.getAccountToId(), operation.getAmount()));
    }

    private Account changeBalanceOfAccount(BalanceOperation operation) throws AccountException {
        Account account = getAccountById(operation.getAccountId());
        BigDecimal newBalance = account.getBalance().add(operation.getAmount());
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw AccountException.getNegativeBalanceException(operation.getAccountId());
        }
        account.setBalance(newBalance);
        return repository.save(account);
    }
}
