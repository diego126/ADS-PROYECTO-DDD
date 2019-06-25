package banking.customers.domain.repository;

import java.util.List;

import banking.customers.domain.entity.Customer;

public interface CustomerRepository {
	public Customer save(Customer customer);
	public List<Customer> get(int page, int pageSize);
	public Customer get(long customerId);
}
