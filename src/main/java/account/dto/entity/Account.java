package account.dto.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Счёт
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    /**
     * Идентификатор счёта
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @NotNull
    private Long id;

    /**
     * Текущий баланс на счету
     */
    @Column(nullable = false)
    @NotNull
    private BigDecimal balance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;

        Account account = (Account) o;

        return getId().equals(account.getId()) && (getBalance().compareTo(account.getBalance()) == 0);
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getBalance().hashCode();
        return result;
    }
}
