package banking.users.application.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserAuthDto {
	private long id;
	private String name;
	private String bearerToken;
	private boolean isAuthenticated;
	private List<UserClaimDto> claims;
	
	public UserAuthDto() {
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBearerToken() {
		return bearerToken;
	}

	public void setBearerToken(String bearerToken) {
		this.bearerToken = bearerToken;
	}

	@JsonProperty(value="isAuthenticated")
	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}

	public List<UserClaimDto> getClaims() {
		return claims;
	}

	public void setClaims(List<UserClaimDto> claims) {
		this.claims = claims;
	}
}
