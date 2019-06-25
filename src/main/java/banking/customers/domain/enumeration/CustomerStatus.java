package banking.customers.domain.enumeration;

public enum CustomerStatus {
	ACTIVE(1), INACTIVE(0);
	private final int value;

    private CustomerStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
