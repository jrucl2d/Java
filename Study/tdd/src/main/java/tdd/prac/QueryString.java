package tdd.prac;

public class QueryString {
    private String query;

    public QueryString(String queryString) {
        if (queryString == null) throw new NullPointerException();
        this.query = queryString;
    }

    public int count() {
        if ("".equals(query)) return 0;
        String[] pairs = query.split("&");
        return pairs.length;
    }

    public String valueFor(String name) {
        String[] pairs = query.split("&");
        for (String pair: pairs) {
            String[] nameAndValue = pair.split("=");
            if (nameAndValue[0].equals(name)) return nameAndValue[1];
        }
        return null;
    }
}
