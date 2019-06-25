package banking.users.domain.repository;

import java.util.List;

import banking.users.domain.entity.UserClaim;

public interface UserClaimRepository {
	public List<UserClaim> findByUserId(Long userId) throws Exception;
}
