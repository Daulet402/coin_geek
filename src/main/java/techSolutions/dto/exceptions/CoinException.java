package techSolutions.dto.exceptions;

public class CoinException extends Exception {
    public CoinException() {
    }

    public CoinException(String s) {
        super(s);
    }

    public CoinException(String s, Throwable throwable) {
        super(s, throwable);
    }
}