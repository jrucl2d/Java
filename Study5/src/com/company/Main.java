package com.company;

public class Main {

    public static void main(String[] args) {
	    String a = "I love you";
	    if(a.contains("love")){ // contains를 통해서 가졌는지 여부 판단
	        System.out.println("있음");
        } else{
	        System.out.println("없음");
        }
        String b = "man";
        // String은 원시 자료형이 아니기 때문에 equals라는 메소드로 파악
	    if(b.equals("man")){
	        System.out.println("같음");
        }
	    // 대소문자 구별 없이 비교 가능
	    if(b.equalsIgnoreCase("MaN")){
	        System.out.println("대소문자 구별 없이 같음");
        }
    }

}
