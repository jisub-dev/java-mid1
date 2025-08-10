package nested.nested;

public class NestedOuter {

    private static int outClassValue = 3;
    private int outInstanceValue = 2;

    static class Nested {
        private int nestedInstanceValue = 1;

        public void print() {
            // 자신의 멤버에 접근
            System.out.println(nestedInstanceValue);

            // 바깥 클래스의 인스턴스 멤버에 접근에는 접근할 수 없다
            //System.out.println(outInstanceValue);

            // 바깥 클래스의 클래스 멤버에는 접근할 수 있다. private 도 가능. 두가지 형태로 접근 가능.
            // private 이더라도 클래스 내부에서 접근하는 것이기 때문에 접근 가능
            System.out.println(outClassValue);
            System.out.println(NestedOuter.outClassValue);




        }
    }
}
