package ch07.after;

public class p7_33 {
    private int outerIv = 0;
    static int outerCv = 0;

    class InstanceInner {
        int iiv = outerIv; // 외부 클래스의 private 멤버에도 접근 가능
        int iiv2 = outerCv;
    }

    static class StaticInner {
//        int siv = outerIv;
        static int scv = outerCv;
    }

    void myMethod() {
        int lv = 0; // final이 자동 추가될 것이다. 값 변경 불가.
        final int LV = 0;

        class LocalInner {
            int liv = outerIv;
            int liv2 = outerCv;
            int liv3 = lv; // jdk 1.8부터 오류 안 남
            int liv4 = LV; // 원래 외부 클래스 지역 변수는 final이 붙은 상수만 접근 가능했다.
        }
    }
}
