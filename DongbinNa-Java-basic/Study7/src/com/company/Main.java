package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        System.out.print("정수 입력 : ");
//        int i = sc.nextInt();
//        System.out.print("입력된 정수 : " + i);
//        sc.close(); // 정상적으로 정료될 수 있도록

        // 파일 위치를 정확하게 해줘야 함
        File file = new File("resources/input.txt");
        try{
            Scanner sc = new Scanner(file);
            while(sc.hasNextInt()){
                System.out.println(sc.nextInt() * 100);
            }
             sc.close();
        } catch(FileNotFoundException e){
            System.out.println("파일 읽어오는 도중 오류 발생");
        }
    }
}
