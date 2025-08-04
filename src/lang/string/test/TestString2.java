package lang.string.test;

public class TestString2 {

    public static void main(String[] args) {
        String[] arr = {"hello", "java", "jvm", "spring", "jpa"};
        //코드 작성
        int sum = 0;

        for (String s : arr) {
            int result = s.length();
            sum += result;
            System.out.println(s + ":" + result);
        }
        System.out.println("sum = " + sum);
    }
}
