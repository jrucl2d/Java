package tdd.prac;

public class QueryString {
    private String query;

    public QueryString(String queryString) {
        if (queryString == null) throw new NullPointerException();
        this.query = queryString;
    }

    public int count() {
        if ("".equals(query)) return 0;
        return 1;
    }
}
