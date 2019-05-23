package account.model.operation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

/**
 * Базовая операция с деньгами
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseOperation {

    /**
     * Количество денег которые нужно перевести
     */
    @NotNull
    @Positive(message = "The amount most be positive")
    private BigDecimal amount;

    /**
     * Меняет знак у количества для операций снятия со счёта
     */
    public void negateAmount() {
        amount = amount.negate();
    }
}
