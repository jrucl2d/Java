package ch11;

import java.util.*;

public class p31 {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put("haha", Integer.valueOf(90));
        map.put("haha", Integer.valueOf(100));
        map.put("haha2", Integer.valueOf(100));
        map.put("haha3", Integer.valueOf(80));
        map.put("haha4", Integer.valueOf(90));

        Set set = map.entrySet();
        Iterator it = set.iterator();
        
        while(it.hasNext()){
            Map.Entry e =(Map.Entry) it.next();
            System.out.print("e.getKey() = " + e.getKey());
            System.out.println("e.getValue() = " + e.getValue());
        }
        set = map.keySet();
        System.out.println("set = " + set);

        Collection values = map.values();
        it = values.iterator();
        int total = 0;
        while(it.hasNext()){
            Integer i = (Integer) it.next();
            total += i.intValue();
        }
        System.out.println("total = " + total);
        System.out.println("(float)total / set.size() = " + (float)total / set.size());
        System.out.println("Collections.max(values) = " + Collections.max(values));
        System.out.println("Collections.min(values) = " + Collections.min(values));
    }
}
