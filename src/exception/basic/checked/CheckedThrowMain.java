package exception.basic.checked;

public class CheckedThrowMain {

    public static void main(String[] args) throws Exception { // 부모 타입을 던지면 아래 자식들도 던짐
        Service service = new Service();
        service.catchTrows();
        System.out.println("정상 종료");
    }
}
