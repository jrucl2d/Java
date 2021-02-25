package ch11;

import java.util.Arrays;
import java.util.Comparator;

public class p19 {
    public static void main(String[] args) {
        String[] strarr = {"cat", "Dog", "lion", "tiger"};
        Arrays.sort(strarr);
        System.out.println(Arrays.toString(strarr)); // Dog, cat, lion, tiger
        Arrays.sort(strarr, String.CASE_INSENSITIVE_ORDER);
        System.out.println(Arrays.toString(strarr)); // cat, Dog, lion, tiger
        Arrays.sort(strarr, new Descending());
        System.out.println(Arrays.toString(strarr)); // tiger, lion, cat, Dog
    }
}
class Descending implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        if(o1 instanceof Comparable && o2 instanceof Comparable) {
            Comparable c1 = (Comparable) o1;
            Comparable c2 = (Comparable) o2;
            return c1.compareTo(c2) * -1;
        }
        return -1;
    }
}
