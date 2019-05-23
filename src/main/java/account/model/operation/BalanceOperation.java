package account.model.operation;

import account.model.AccountException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Операция с одним счётом
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalanceOperation extends BaseOperation {

    /**
     * Идентификатор счёта над которым осуществляется операция
     */
    @NotNull
    private Long accountId;

    public BalanceOperation(@NotNull Long accountId,
                            @NotNull BigDecimal amount) throws AccountException {
        super(amount);
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw AccountException.getWrongRangeException();
        }
        this.accountId = accountId;
    }
}
