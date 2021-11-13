package tree;

public class TreeProcessor {
    public static int lookup(String k, int defaultVal, Tree t) {
        if (t == null) return defaultVal;
        if (k.equals(t.getKey())) return t.getVal();
        return lookup(k, defaultVal,
                k.compareTo(t.getKey()) < 0 ? t.getLeft() : t.getRight());
    }

    public static void update(String k, int newVal, Tree t) {
        if (t == null) {
            // 새로운 노드 추가 필요
        }
        else if (k.equals(t.getKey()))
            t.setVal(newVal);
        else
            update(k, newVal,
                    k.compareTo(t.getKey()) < 0 ? t.getLeft() : t.getRight());
    }

    public static Tree fUpdate(String k, int newVal, Tree t) {
        return (t == null) ?
                new Tree(k, newVal, null, null) :
                k.equals(t.getKey()) ?
                        new Tree(k, newVal, t.getLeft(), t.getRight()) :
                        k.compareTo(t.getKey()) < 0 ?
                                new Tree(t.getKey(), t.getVal(), fUpdate(k, newVal, t.getLeft()), t.getRight()) :
                                new Tree(t.getKey(), t.getVal(), t.getLeft(), fUpdate(k, newVal, t.getRight()));
    }
}
