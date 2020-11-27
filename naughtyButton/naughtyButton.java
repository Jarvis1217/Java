package com.myself.demo;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class naughtyButton {

    private final JFrame jf;

    public naughtyButton() {
        jf = new JFrame("测试窗口");
        jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
        jf.setBounds(100, 100, 513, 392);
        jf.getContentPane().setLayout(null);

        JButton btnNewButton = new JButton("Click me!");
        btnNewButton.setBounds(198, 258, 97, 44);
        jf.getContentPane().add(btnNewButton);

        JLabel lblNewLabel = new JLabel("Hello World");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(135, 82, 223, 61);
        jf.getContentPane().add(lblNewLabel);

        JButton btnNewButton_1 = new JButton("Sorry!");
        btnNewButton_1.setBounds(198, 258, 97, 44);
        btnNewButton_1.setVisible(false);
        jf.getContentPane().add(btnNewButton_1);

        btnNewButton.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        lblNewLabel.setText("Don't Click me!");
                        btnNewButton.setVisible(false);
                        btnNewButton_1.setVisible(true);
                    }
                }
        );

        btnNewButton_1.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        lblNewLabel.setText("Hello World");
                        btnNewButton.setVisible(true);
                        btnNewButton_1.setVisible(false);
                    }
                }
        );

        jf.setVisible(true);
    }

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        new naughtyButton();
    }
}