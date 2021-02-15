package com.hbue.calc.allCalc;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.JButton;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.ScriptEngine;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class StandardCalc {
    JFrame jf;

    public StandardCalc() {
        jf = new JFrame("标准计算器");
        jf.setResizable(false);
        jf.getContentPane().setFont(new Font("宋体", Font.BOLD, 16));
        jf.setBounds(100,100,496,513);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.getContentPane().setLayout(null);

        JButton num0 = new JButton("0");
        num0.setFont(new Font("宋体", Font.BOLD, 16));
        num0.setBounds(129, 403, 109, 49);
        jf.getContentPane().add(num0);

        JButton num1 = new JButton("1");
        num1.setFont(new Font("宋体", Font.BOLD, 16));
        num1.setBounds(10, 343, 109, 49);
        jf.getContentPane().add(num1);

        JButton num2 = new JButton("2");
        num2.setFont(new Font("宋体", Font.BOLD, 16));
        num2.setBounds(129, 344, 109, 49);
        jf.getContentPane().add(num2);

        JButton num3 = new JButton("3");
        num3.setFont(new Font("宋体", Font.BOLD, 16));
        num3.setBounds(248, 343, 109, 49);
        jf.getContentPane().add(num3);

        JButton num4 = new JButton("4");
        num4.setFont(new Font("宋体", Font.BOLD, 16));
        num4.setBounds(10, 284, 109, 49);
        jf.getContentPane().add(num4);

        JButton num5 = new JButton("5");
        num5.setFont(new Font("宋体", Font.BOLD, 16));
        num5.setBounds(129, 284, 109, 49);
        jf.getContentPane().add(num5);

        JButton num6 = new JButton("6");
        num6.setFont(new Font("宋体", Font.BOLD, 16));
        num6.setBounds(248, 284, 109, 49);
        jf.getContentPane().add(num6);

        JButton num7 = new JButton("7");
        num7.setFont(new Font("宋体", Font.BOLD, 16));
        num7.setBounds(10, 225, 109, 49);
        jf.getContentPane().add(num7);

        JButton num8 = new JButton("8");
        num8.setFont(new Font("宋体", Font.BOLD, 16));
        num8.setBounds(129, 225, 109, 49);
        jf.getContentPane().add(num8);

        JButton num9 = new JButton("9");
        num9.setFont(new Font("宋体", Font.BOLD, 16));
        num9.setBounds(248, 225, 109, 49);
        jf.getContentPane().add(num9);

        JButton addBtn = new JButton("+");
        addBtn.setFont(new Font("宋体", Font.BOLD, 16));
        addBtn.setBounds(367, 343, 109, 49);
        jf.getContentPane().add(addBtn);

        JButton subBtn = new JButton("-");
        subBtn.setFont(new Font("宋体", Font.BOLD, 16));
        subBtn.setBounds(367, 284, 109, 49);
        jf.getContentPane().add(subBtn);

        JButton multiBtn = new JButton("*");
        multiBtn.setFont(new Font("宋体", Font.BOLD, 16));
        multiBtn.setBounds(367, 225, 109, 49);
        jf.getContentPane().add(multiBtn);

        JButton divBtn = new JButton("/");
        divBtn.setFont(new Font("宋体", Font.BOLD, 16));
        divBtn.setBounds(367, 166, 109, 49);
        jf.getContentPane().add(divBtn);

        JButton equalBtn = new JButton("=");
        equalBtn.setFont(new Font("宋体", Font.BOLD, 16));
        equalBtn.setBounds(367, 403, 109, 49);
        jf.getContentPane().add(equalBtn);

        JButton pointBtn = new JButton(".");
        pointBtn.setFont(new Font("宋体", Font.BOLD, 16));
        pointBtn.setBounds(248, 403, 109, 49);
        jf.getContentPane().add(pointBtn);

        JButton clearBtn = new JButton("C");
        clearBtn.setFont(new Font("宋体", Font.BOLD, 16));
        clearBtn.setBounds(10, 403, 109, 49);
        jf.getContentPane().add(clearBtn);

        JButton backBtn = new JButton("<=");
        backBtn.setFont(new Font("宋体", Font.BOLD, 16));
        backBtn.setBounds(248, 166, 109, 49);
        jf.getContentPane().add(backBtn);

        JButton squareBtn = new JButton("x²");
        squareBtn.setFont(new Font("宋体", Font.BOLD, 16));
        squareBtn.setBounds(129, 166, 109, 49);
        jf.getContentPane().add(squareBtn);

        JButton squareRootBtn = new JButton("√x");
        squareRootBtn.setFont(new Font("宋体", Font.BOLD, 16));
        squareRootBtn.setBounds(10, 166, 109, 49);
        jf.getContentPane().add(squareRootBtn);

        JTextField screen = new JTextField();
        screen.setEditable(false);
        screen.setFont(new Font("宋体", Font.PLAIN, 30));
        screen.setBounds(10, 53, 466, 86);
        screen.setHorizontalAlignment(JTextField.RIGHT);
        jf.getContentPane().add(screen);
        screen.setColumns(10);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 492, 29);
        jf.getContentPane().add(menuBar);

        JMenu mnNewMenu = new JMenu("选项");
        menuBar.add(mnNewMenu);

        JMenuItem standardCalc = new JMenuItem("标准计算器");
        mnNewMenu.add(standardCalc);

        JMenuItem exchangeRateCalc = new JMenuItem("汇率计算器");
        mnNewMenu.add(exchangeRateCalc);

        num0.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                screen.setText(screen.getText() + "0");
            }
        });

        num1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                screen.setText(screen.getText() + "1");
            }
        });

        num2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                screen.setText(screen.getText() + "2");
            }
        });

        num3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                screen.setText(screen.getText() + "3");
            }
        });

        num4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                screen.setText(screen.getText() + "4");
            }
        });

        num5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                screen.setText(screen.getText() + "5");
            }
        });

        num6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                screen.setText(screen.getText() + "6");
            }
        });

        num7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                screen.setText(screen.getText() + "7");
            }
        });

        num8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                screen.setText(screen.getText() + "8");
            }
        });

        num9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                screen.setText(screen.getText() + "9");
            }
        });

        pointBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                screen.setText(screen.getText() + ".");
            }
        });

        clearBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                screen.setText("");
            }
        });

        addBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                screen.setText(screen.getText()+"+");
            }
        });

        subBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                screen.setText(screen.getText()+"-");
            }
        });

        multiBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                screen.setText(screen.getText()+"*");
            }
        });

        divBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                screen.setText(screen.getText()+"/");
            }
        });

        backBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String text = screen.getText().substring(0,screen.getText().length()-1);

                screen.setText(text);
            }
        });

        equalBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String str = screen.getText();

                ScriptEngineManager manager = new ScriptEngineManager();
                ScriptEngine engine = manager.getEngineByName("js");
                try {
                    screen.setText(String.valueOf(engine.eval(str)));
                } catch (ScriptException scriptException) {
                    scriptException.printStackTrace();
                    screen.setText("Error!");
                }
            }
        });

        squareBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String str = screen.getText();
                try {
                    double num = Double.parseDouble(str);
                    screen.setText(String.valueOf(num*num));

                } catch (Exception ex) {
                    ex.printStackTrace();
                    screen.setText("Error!");
                }
            }
        });

        squareRootBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String str = screen.getText();
                try {
                    double num = Double.parseDouble(str);
                    screen.setText(String.valueOf(Math.sqrt(num)));

                } catch (Exception ex) {
                    ex.printStackTrace();
                    screen.setText("Error!");
                }
            }
        });

        exchangeRateCalc.addActionListener(e -> {
            jf.dispose();
            new ExchangeRateCalc();
        });

        jf.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        new StandardCalc();
    }
}
