package account.service;

import account.dto.entity.Account;
import account.model.AccountException;
import account.model.operation.BalanceOperation;
import account.model.operation.TransferOperation;
import org.springframework.transaction.annotation.Transactional;

public interface AccountService {
    /**
     * Получает данные о счёте
     * @param accountId Идентификатор счёта
     * @return Данные о счёте
     */
    Account getAccountById(Long accountId) throws AccountException;

    /**
     * Положить деньги на счёт
     * @param operation сколько и куда положить денег
     * @return Данные о состоянии счёта после операции
     */
    @Transactional(rollbackFor = {AccountException.class})
    Account putMoneyIntoAccount(BalanceOperation operation) throws AccountException;

    /**
     * Снять деньги со счёта
     * @param operation сколько и куда положить денег
     * @return Данные о состоянии счёта после операции
     */
    @Transactional(rollbackFor = {AccountException.class})
    Account withdrawMoneyFromAccount(BalanceOperation operation) throws AccountException;

    /**
     * Перевести деньги со счёта на счёт
     * @param operation откуда, куда и сколько перевести денег
     */
    @Transactional(rollbackFor = {AccountException.class})
    void transferMoney(TransferOperation operation) throws AccountException;
}
