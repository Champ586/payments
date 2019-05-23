package account.controller;

import account.model.operation.BalanceOperation;
import account.model.operation.TransferOperation;
import account.model.response.AccountResponse;
import account.model.response.BaseResponse;
import account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts/{accountId}")
    public BaseResponse getAccountByClientId(@PathVariable(name = "accountId") Long accountId) {
        try {
            return new AccountResponse(accountService.getAccountById(accountId));
        }
        catch (Exception e) {
            return BaseResponse.getServerErrorResponse(e.getMessage());
        }
    }

    @PostMapping("/accounts/{accountId}/put")
    public BaseResponse putMoneyIntoAccount(@PathVariable(name = "accountId") Long accountId,
                                            @RequestBody BigDecimal amount) {
        try {
            return new AccountResponse(
                    accountService.putMoneyIntoAccount(new BalanceOperation(accountId, amount)));
        }
        catch (Exception e) {
            return BaseResponse.getServerErrorResponse(e.getMessage());
        }
    }

    @PostMapping("/accounts/{accountId}/withdraw")
    public BaseResponse withdrawMoneyFromAccount(@PathVariable(name = "accountId") Long accountId,
                                                 @RequestBody BigDecimal amount) {
        try {
            return new AccountResponse(
                    accountService.withdrawMoneyFromAccount(new BalanceOperation(accountId, amount)));
        }
        catch (Exception e) {
            return BaseResponse.getServerErrorResponse(e.getMessage());
        }
    }

    @PostMapping("/accounts/{accountFromId}/transfer/{accountToId}")
    public BaseResponse transferMoney(@PathVariable(name = "accountFromId") Long accountFromId,
                                      @PathVariable(name = "accountToId") Long accountToId,
                                      @RequestBody BigDecimal amount) {
        try {
            accountService.transferMoney(new TransferOperation(accountFromId, accountToId, amount));
            return new BaseResponse();
        }
        catch (Exception e) {
            return BaseResponse.getServerErrorResponse(e.getMessage());
        }
    }
}
