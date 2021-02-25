package ch11;

import java.util.HashSet;
import java.util.Set;

public class p20 {
    public static void main(String[] args) {
        Object[] oar = {"1", Integer.valueOf(1), "2","2","2","3", "3","4", "4"};
        Set set = new HashSet();

        for(Object i : oar){
            set.add(i);
        }
        System.out.println("set = " + set);
    }
}
