package com.myself.demo;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Work {
    JFrame jf;
    private JTextField Screen;

    public Work() {
        jf = new JFrame("Work");
        jf.setBounds(100,100,379,598);
        jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
        jf.getContentPane().setLayout(null);

        Screen = new JTextField();
        Screen.setEditable(false);
        Screen.setFont(new Font("宋体", Font.PLAIN, 30));
        Screen.setBounds(10, 25, 346, 66);
        Screen.setHorizontalAlignment(JTextField.RIGHT);
        jf.getContentPane().add(Screen);
        Screen.setColumns(10);

        RoundButton div = new RoundButton("/");
        div.setBounds(299, 170, 45, 45);
        jf.getContentPane().add(div);

        RoundButton mul = new RoundButton("x");
        mul.setBounds(299, 239, 45, 45);
        jf.getContentPane().add(mul);

        RoundButton sub = new RoundButton("-");
        sub.setBounds(299, 309, 45, 45);
        jf.getContentPane().add(sub);

        RoundButton add = new RoundButton("+");
        add.setBounds(299, 385, 45, 45);
        jf.getContentPane().add(add);

        RoundButton equal = new RoundButton("=");
        equal.setBounds(299, 465, 45, 45);
        jf.getContentPane().add(equal);

        RoundButton num9 = new RoundButton("9");
        num9.setBounds(222, 239, 45, 45);
        jf.getContentPane().add(num9);

        RoundButton num8 = new RoundButton("8");
        num8.setBounds(142, 239, 45, 45);
        jf.getContentPane().add(num8);

        RoundButton num7 = new RoundButton("7");
        num7.setBounds(61, 239, 45, 45);
        jf.getContentPane().add(num7);

        RoundButton num6 = new RoundButton("6");
        num6.setBounds(222, 309, 45, 45);
        jf.getContentPane().add(num6);

        RoundButton num5 = new RoundButton("5");
        num5.setBounds(142, 309, 45, 45);
        jf.getContentPane().add(num5);

        RoundButton num4 = new RoundButton("4");
        num4.setBounds(61, 309, 45, 45);
        jf.getContentPane().add(num4);

        RoundButton num1 = new RoundButton("1");
        num1.setBounds(61, 385, 45, 45);
        jf.getContentPane().add(num1);

        RoundButton num2 = new RoundButton("2");
        num2.setBounds(142, 385, 45, 45);
        jf.getContentPane().add(num2);

        RoundButton num3 = new RoundButton("3");
        num3.setBounds(222, 385, 45, 45);
        jf.getContentPane().add(num3);

        RoundButton point = new RoundButton(".");
        point.setBounds(222, 465, 45, 45);
        jf.getContentPane().add(point);

        RoundButton num0 = new RoundButton("0");
        num0.setBounds(61, 465, 124, 45);
        jf.getContentPane().add(num0);

        RoundButton mod = new RoundButton("%");
        mod.setBounds(222, 170, 45, 45);
        jf.getContentPane().add(mod);

        RoundButton delete = new RoundButton("<-");
        delete.setBounds(142, 170, 45, 45);
        jf.getContentPane().add(delete);

        RoundButton clear = new RoundButton("C");
        clear.setBounds(61, 170, 45, 45);
        jf.getContentPane().add(clear);

        num0.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Screen.setText(Screen.getText() + "0");
            }
        });

        num1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Screen.setText(Screen.getText() + "1");
            }
        });

        num2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Screen.setText(Screen.getText() + "2");
            }
        });

        num3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Screen.setText(Screen.getText() + "3");
            }
        });

        num4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Screen.setText(Screen.getText() + "4");
            }
        });

        num5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Screen.setText(Screen.getText() + "5");
            }
        });

        num6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Screen.setText(Screen.getText() + "6");
            }
        });

        num7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Screen.setText(Screen.getText() + "7");
            }
        });

        num8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Screen.setText(Screen.getText() + "8");
            }
        });

        num9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Screen.setText(Screen.getText() + "9");
            }
        });

        point.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Screen.setText(Screen.getText() + ".");
            }
        });

        clear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Screen.setText("");
            }
        });

        add.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Screen.setText(Screen.getText()+"+");
            }
        });

        sub.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Screen.setText(Screen.getText()+"-");
            }
        });

        mul.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Screen.setText(Screen.getText()+"*");
            }
        });

        div.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Screen.setText(Screen.getText()+"/");
            }
        });

        delete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String text = Screen.getText().substring(0,Screen.getText().length()-1);

                Screen.setText(text);
            }
        });

        mod.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Screen.setText(Screen.getText()+"%");
            }
        });

        equal.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String str = Screen.getText();

                ScriptEngineManager manager = new ScriptEngineManager();
                ScriptEngine engine = manager.getEngineByName("js");
                try {
                    Screen.setText(String.valueOf(engine.eval(str)));
                } catch (ScriptException scriptException) {
                    scriptException.printStackTrace();
                }
            }
        });

        jf.setVisible(true);
    }

    public static void main(String[] args) {
        new Work();
    }
}
