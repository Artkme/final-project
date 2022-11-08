package ge.ufc.webapps.model;

public class User {
    private String fullName;
    private double balance;

    public User(String fullName, double balance) {
        this.fullName = fullName;
        this.balance = balance;
    }

    public User(){}

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
