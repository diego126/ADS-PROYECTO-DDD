package banking.accounts.infrastructure.hibernate.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import banking.accounts.domain.entity.BankAccount;
import banking.accounts.domain.repository.BankAccountRepository;
import banking.common.infrastructure.hibernate.repository.BaseHibernateRepository;

@Transactional
@Repository
public class BankAccountHibernateRepository extends BaseHibernateRepository<BankAccount>
		implements BankAccountRepository {
	@Override
	public BankAccount findByNumber(String accountNumber) throws Exception {
		BankAccount bankAccount = null;
		Criteria criteria = getSession().createCriteria(BankAccount.class);
		criteria.add(Restrictions.eq("number", accountNumber));
		bankAccount = (BankAccount) criteria.uniqueResult();
		return bankAccount;
	}

	@Override
	public BankAccount findByNumberLocked(String accountNumber) throws Exception {
		BankAccount bankAccount = null;
		Criteria criteria = getSession().createCriteria(BankAccount.class);
		criteria.add(Restrictions.eq("number", accountNumber));
		criteria.setLockMode(LockMode.PESSIMISTIC_WRITE);
		bankAccount = (BankAccount) criteria.uniqueResult();
		return bankAccount;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BankAccount> get(long customerId) {
		List<BankAccount> bankAccounts = null;
		Criteria criteria = getSession().createCriteria(BankAccount.class, "a");
		criteria.createAlias("a.customer", "c");
		criteria.add(Restrictions.eq("c.id", customerId));
		bankAccounts = criteria.list();
		return bankAccounts;
	}
	
	public BankAccount save(BankAccount bankAccount) {
		return super.save(bankAccount);
	}
}
