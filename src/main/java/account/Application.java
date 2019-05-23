package account;

import account.dto.entity.Account;
import account.dto.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Создадим 2 аккаунта с которыми можно работать
     */
    @Bean
    public CommandLineRunner demo(AccountRepository repository) {
        return (args) -> {
            Account account1 = new Account(1L, BigDecimal.valueOf(1000));
            repository.save(account1);
            Account account2 = new Account(2L, BigDecimal.valueOf(3000));
            repository.save(account2);
        };
    }
}
