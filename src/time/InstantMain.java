package time;

import java.time.Instant;
import java.time.ZonedDateTime;

public class InstantMain {

    public static void main(String[] args) {
        // 생성
        Instant now = Instant.now(); // UTC 기준
        System.out.println("now = " + now);

        ZonedDateTime zdt = ZonedDateTime.now();
        Instant from = Instant.from(zdt);
        System.out.println("from = " + from);

        Instant epochStart = Instant.ofEpochSecond(0); // 1970년 1월 1알 0시 0분 0초에 + 0초 한 결과
        System.out.println("epochStart = " + epochStart);

        // 계산
        Instant later = epochStart.plusSeconds(3600); // 1970년 1월 1알 0시 0분 0초에 + 3600초 한 결과
        System.out.println("later = " + later);

        long laterEpochSecond = later.getEpochSecond();
        System.out.println("laterEpochSecond = " + laterEpochSecond); // 1970년 1월 1알 0시 0분 0초 부더 흐른 시간 (초)

    }
}
