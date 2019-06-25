package banking.common.infrastructure.security;

import java.util.function.Function;

public class Hashing {
	private static final UpdatableBCrypt bcrypt = new UpdatableBCrypt(11);

	public static String hash(String password) {
		return bcrypt.hash(password);
	}
	
	public static boolean verifyHash(String password, String hash) {
	    return bcrypt.verifyHash(password, hash);
	}

	public static boolean verifyAndUpdateHash(String password, String hash, Function<String, Boolean> updateFunc) {
		return bcrypt.verifyAndUpdateHash(password, hash, updateFunc);
	}
}
