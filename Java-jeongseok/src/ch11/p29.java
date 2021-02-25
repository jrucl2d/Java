package ch11;

import java.util.TreeSet;

public class p29 {
    public static void main(String[] args) {
        TreeSet set = new TreeSet();
        int[] scores = {80, 95, 50, 35, 45, 65, 10, 100};

        for(int score : scores){
            set.add(score);
        }

        System.out.println(set.headSet(50));
        System.out.println(set.tailSet(50));
    }
}
