package banking.users.domain.repository;

import java.util.List;

import banking.users.domain.entity.User;

public interface UserRepository {
	public User save(User user);
	public User getById(long userId);
	public User getByName(String name);
	public List<User> getPaginated(int page, int pageSize);
}
