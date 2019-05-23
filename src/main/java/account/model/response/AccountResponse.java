package account.model.response;

import account.dto.entity.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountResponse extends BaseResponse {
    private Account account;

    public AccountResponse(Account account) {
        super();
        this.account = account;
    }
}
