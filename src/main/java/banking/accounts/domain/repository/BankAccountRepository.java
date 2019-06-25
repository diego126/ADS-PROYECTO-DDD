package banking.accounts.domain.repository;

import java.util.List;

import banking.accounts.domain.entity.BankAccount;

public interface BankAccountRepository {
	BankAccount findByNumber(String accountNumber) throws Exception;
	BankAccount findByNumberLocked(String accountNumber) throws Exception;
	List<BankAccount> get(long customerId);
	BankAccount persist(BankAccount bankAccount);
	BankAccount save(BankAccount bankAccount);
}
