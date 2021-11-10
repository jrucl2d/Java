package tdd.datesub;

public class SubDate {

    public static int getYearDay(int year) {
        int result = 0;
        for (int i = 1; i < year; i++) {
            if (isLeapYear(i)) result += 366;
            else result += 365;
        }
        return result;
    }

    // 위부터 아래로 우선순위
    // 400으로 나누어 떨어지면 윤년
    // 100으로 나누어 떨어지면 평년
    // 4로 나누어 떨어지면 윤년
    public static boolean isLeapYear(int year) {
        if (year % 400 == 0) return true;
        if (year % 100 == 0) return false;
        return year % 4 == 0;
    }
}
