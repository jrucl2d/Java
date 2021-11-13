package ch07.after;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class p7_38 {
    public static void main(String[] args) {
        Button b = new Button("Start");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Action Event Occurred");
            }
        } // 익명 클래스의 끝
        );
    }
}