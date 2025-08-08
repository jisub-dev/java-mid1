package time.test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

public class TestAdjusters {

    public static void main(String[] args) {
        int year = 2024;
        int month = 1;

        LocalDate date = LocalDate.of(year, month, 1);
        DayOfWeek firstDayOfWeek = date.getDayOfWeek();
        System.out.println("firstDayOfWeek = " + firstDayOfWeek);
        DayOfWeek lastDayOfWeek = date.with(TemporalAdjusters.lastDayOfMonth()).getDayOfWeek();
        System.out.println("lastDayOfWeek = " + lastDayOfWeek);

        // 내 버전
        /*LocalDateTime firstDayOfWeek = dateTime.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("firstDayOfWeek = " + firstDayOfWeek.getDayOfWeek());
        LocalDateTime lastDayOfWeek = dateTime.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("lastDayOfWeek = " + lastDayOfWeek.getDayOfWeek());*/

    }
}
