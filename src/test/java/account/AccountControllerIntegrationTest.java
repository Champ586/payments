package account;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.net.URL;

import account.dto.entity.Account;
import account.dto.repository.AccountRepository;
import account.model.response.AccountResponse;
import account.model.response.BaseResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerIntegrationTest {

    private static final BigDecimal TEST_BALANCE = BigDecimal.valueOf(5000);
    private static final BigDecimal TEST_AMOUNT= BigDecimal.valueOf(1000);

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private AccountRepository accountRepository;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
    }

    @Test
    public void getAccountSuccessTest() throws Exception {
        Account testAccount = createTestAccount();

        AccountResponse response = template.getForObject(base.toString() + "accounts/1", AccountResponse.class);

        assertThat(response.getStatus(), equalTo(HttpStatus.OK));
        assertThat(response.getError(), nullValue());
        assertThat(response.getAccount(), equalTo(testAccount));
    }

    @Test
    public void putMoneyIntoAccountSuccessTest() throws Exception {
        Account testAccount = createTestAccount();
        testAccount.setBalance(TEST_BALANCE.add(TEST_AMOUNT));

        AccountResponse response = template.postForObject(base.toString() + "accounts/1/put", TEST_AMOUNT, AccountResponse.class);

        assertThat(response.getStatus(), equalTo(HttpStatus.OK));
        assertThat(response.getError(), nullValue());
        assertThat(response.getAccount(), equalTo(testAccount));
    }

    @Test
    public void withdrawMoneyFromAccountSuccessTest() throws Exception {
        Account testAccount = createTestAccount();
        testAccount.setBalance(TEST_BALANCE.subtract(TEST_AMOUNT));

        AccountResponse response = template.postForObject(base.toString() + "accounts/1/withdraw", TEST_AMOUNT, AccountResponse.class);

        assertThat(response.getStatus(), equalTo(HttpStatus.OK));
        assertThat(response.getError(), nullValue());
        assertThat(response.getAccount(), equalTo(testAccount));
    }

    @Test
    public void transferMoneySuccessTest() throws Exception {
        Account testAccount = createTestAccount();
        testAccount.setBalance(TEST_BALANCE.subtract(TEST_AMOUNT));

        Account secondTestAccount = createSecondTestAccount();
        secondTestAccount.setBalance(TEST_BALANCE.add(TEST_AMOUNT));

        BaseResponse response = template.postForObject(base.toString() + "accounts/1/transfer/2", TEST_AMOUNT, AccountResponse.class);

        assertThat(response.getStatus(), equalTo(HttpStatus.OK));
        assertThat(response.getError(), nullValue());
    }

    private Account createTestAccount() {
        Account testAccount = new Account(1L, TEST_BALANCE);
        accountRepository.save(testAccount);
        return testAccount;
    }

    private Account createSecondTestAccount() {
        Account testAccount = new Account(2L, TEST_BALANCE);
        accountRepository.save(testAccount);
        return testAccount;
    }
}