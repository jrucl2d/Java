package ch12.sub03_enum;

enum Dir {
    EAST(1, ">"), SOUTH(2, "V"), WEST(3, "<"), NORTH(4, "^");
    private final int val;
    private final String symbol;
    Dir(int val, String symbol){
        this.val = val;
        this.symbol = symbol;
    }
    private static final Dir[] DIR_ARR = Dir.values();
    public int getVal() {return val;}
    public String getSymbol() {return symbol;}

    public static Dir of(int dir) {
        if (dir < 1 || dir > 4){
            throw new IllegalArgumentException("Invalid value :" + dir);
        }
        return DIR_ARR[dir - 1];
    }
    public Dir rotate(int num) {
        num = num % 4;
        if(num < 0) num += 4;
        return DIR_ARR[(val - 1 + num) % 4];
    }
    public String toString() {
        return name() + getSymbol();
    }
}

public class p6 {
    public static void main(String[] args) {
        for(Dir d: Dir.values()){
            System.out.println("d.name() = " + d.name() + d.getVal());
        }
        Dir d1 = Dir.EAST;
        Dir d2 = Dir.of(1);

        System.out.println("d1 = " + d1);
        System.out.println("d2 = " + d2);
        System.out.println("Dir.EAST.rotate(1) = " + Dir.EAST.rotate(1));
        System.out.println("Dir.EAST.rotate(2) = " + Dir.EAST.rotate(2));
        System.out.println("Dir.EAST.rotate(-1) = " + Dir.EAST.rotate(-1));
    }
}
