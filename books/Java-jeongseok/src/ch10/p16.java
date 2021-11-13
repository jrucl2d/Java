package ch10;

import java.text.ChoiceFormat;

public class p16 {
    public static void main(String[] args) {
        double[] limits = {60, 70, 80, 90};
        String pattern = "60#D|70#C|80<B|90#A"; // #는 경계값 포함, <는 포함하지 않음
        String[] grades = {"D", "C", "B", "A"};
        
        int[] scores = {100, 95, 88, 70, 52, 60, 70};
        ChoiceFormat form = new ChoiceFormat(limits, grades);
        ChoiceFormat form2 = new ChoiceFormat(pattern);
        
        for(int score : scores) {
            System.out.println(score + ":" + form.format(score));
        }
        for(int score : scores) {
            System.out.println(score + ":" + form2.format(score));
        }
    }
}
