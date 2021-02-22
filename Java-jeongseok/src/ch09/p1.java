package ch09;

public class p1 {
    public static void main(String[] args) {
        Value v1 = new Value(10);
        Value v2 = new Value(10);

        if(v1.equals(v2)){
            System.out.println("same");
        } else {
            System.out.println("not same");
        }
        v2 = v1;
        if(v1.equals(v2)){
            System.out.println("same");
        } else {
            System.out.println("not same");
        }
    }
}
class Value {
    int value;
    Value(int value){
        this.value = value;
    }
}