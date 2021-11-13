package functional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

    }

    private static List<List<Integer>> subsets(List<Integer> list) {
        if (list.isEmpty()) {
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(Collections.emptyList());
            return ans;
        }
        Integer first = list.get(0);
        List<Integer> rest = list.subList(1, list.size());

        List<List<Integer>> subans1 = subsets(rest);
        List<List<Integer>> subans2 = insertAll(first, subans1);
        return concat(subans1, subans2);
    }

    private static List<List<Integer>> concat(List<List<Integer>> subans1, List<List<Integer>> subans2) {
        List<List<Integer>> result = new ArrayList<>(subans1);
        result.addAll(subans2);
        return result;
    }

    private static List<List<Integer>> insertAll(Integer first, List<List<Integer>> subans) {
        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> list : subans) {
            List<Integer> copyList = new ArrayList<>();
            copyList.add(first);
            copyList.addAll(list);
            result.add(copyList);
        }
        return result;
    }
}
