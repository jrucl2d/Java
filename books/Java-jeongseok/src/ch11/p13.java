package ch11;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class p13 {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        Iterator it = list.iterator();
        while(it.hasNext()){
            Object obj = it.next();
            System.out.println("obj = " + obj);
        }

        Set set = new HashSet();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        Iterator it2 = set.iterator();
        while(it2.hasNext()){
            System.out.println("it2.next() = " + it2.next());
        }
    }
}
