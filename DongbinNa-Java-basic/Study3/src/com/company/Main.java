package com.company;

public class Main {

    public static void main(String[] args) {
	    double a = 10.3;
	    double b = 9.6;
	    double c = 10.1;

	    System.out.println((a+b+c) / 3);

//	    for(char i = 'a' ; i <= 'z' ; i++){
//	    	System.out.println(i);
//		}

	    int e = 200;
	    System.out.println("10진수 "+e);
	    System.out.format("8진수 %o\n", e);
		System.out.format("16진수 %x", e);

		String name = "John Doe";
		System.out.println(name);
		System.out.println(name.substring(0, 5));
	}
}
