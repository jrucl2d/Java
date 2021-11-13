package operators;

import io.reactivex.Maybe;
import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.List;

public class QueryTvSales {
    public static void main(String[] args) {
        List<Pair<String, Integer>> sales = new ArrayList<>();

        sales.add(Pair.of("TV", 2500));
        sales.add(Pair.of("Camera", 300));
        sales.add(Pair.of("TV", 1600));
        sales.add(Pair.of("Phone", 800));

        Maybe<Integer> tvSales = Observable.fromIterable(sales)
            // 매출 데이터 중 TV 매출을 필터링
            .filter(sale -> "TV".equals(sale.getLeft()))
            .map(Pair::getRight)

            // TV 매출의 합 구하기
            .reduce((s1, s2) -> s1 + s2);

        tvSales.subscribe(tot -> System.out.println("TV Sales: $" + tot));
    }

    private static class Pair<T, E> {
        private final T left;
        private final E right;

        private Pair(T left, E right) {
            this.left = left;
            this.right = right;
        }

        public static <T, E> Pair<T, E> of(T t, E e) {
            return new Pair<>(t, e);
        }

        public T getLeft() {
            return left;
        }

        public E getRight() {
            return right;
        }
    }

    private static class ProductSales {
        private String mProduct;
        private int mSale;

        public String getmProduct() {
            return mProduct;
        }

        public int getmSale() {
            return mSale;
        }

        public void setmProduct(String mProduct) {
            this.mProduct = mProduct;
        }

        public void setmSale(int mSale) {
            this.mSale = mSale;
        }
    }
}
