package account.model.operation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Операция по переводу денег с одного счёта на другой
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferOperation extends BaseOperation {

    /**
     * Идентификатор счёта откуда перводим
     */
    @NotNull
    private Long accountFromId;

    /**
     * Идентификатор счёта на который перводим
     */
    @NotNull
    private Long accountToId;

    public TransferOperation(@NotNull Long accountFromId,
                             @NotNull Long accountToId,
                             @NotNull @Min(value = 0, message = "The amount most be positive") BigDecimal amount) {
        super(amount);
        this.accountFromId = accountFromId;
        this.accountToId = accountToId;
    }
}
