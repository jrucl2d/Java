package ch09;

import java.util.Locale;
import java.util.Scanner;

public class p35 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] arr = null;

        while(true){
            String prompt = ">>";
            System.out.print(prompt);

            String input = sc.nextLine();

            input = input.trim();
            arr = input.split(" +"); // 하나 이상의 공백을 나타내는 정규식

            String command = arr[0].trim();
            if("".equals(command)) continue;

            command = command.toLowerCase();
            if(command.equals("q")){
                System.exit(0);
            } else {
                for(String i : arr){
                    System.out.println(i);
                }
            }
        }
    }
}
