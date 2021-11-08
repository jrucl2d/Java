package tdd.prac;

import java.util.HashMap;

public class QueryString {
    private HashMap<String, String> map = new HashMap<>();

    public QueryString(String queryString) {
        if (queryString == null) throw new NullPointerException();
        parseQueryString(queryString);
    }

    public int count() {
        return map.size();
    }

    public String valueFor(String name) {
        return map.get(name);
    }

    private void parseQueryString(String query) {
        if ("".equals(query)) return;

        String[] pairs = query.split("&");
        for (String pair: pairs) {
            String[] nameAndValue = pair.split("=");
            map.put(nameAndValue[0], nameAndValue[1]);
        }
    }
}
