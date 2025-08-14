package exception.ex3.exception;

public class ConnectExceptionV3 extends NetworkClientExceptionV3 {

    private final String address; // 연결을 시도한 address 저장

    public ConnectExceptionV3(String address, String message) {
        super(message);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
