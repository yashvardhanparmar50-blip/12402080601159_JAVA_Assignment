class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String msg) {
        super(msg);
    }
}

class Bank {
    double balance = 1000;

    void withdraw(double amt) throws InsufficientBalanceException {
        if (amt > balance) {
            throw new InsufficientBalanceException("Not enough balance!");
        }
        balance -= amt;
        System.out.println("Withdrawn: " + amt);
    }
}

class Main8 {
    public static void main(String[] args) {
        Bank b = new Bank();
        try {
            b.withdraw(1500);
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }
    }
}