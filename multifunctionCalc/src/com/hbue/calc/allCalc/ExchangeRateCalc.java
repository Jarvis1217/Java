package com.hbue.calc.allCalc;

import com.formdev.flatlaf.FlatLightLaf;
import com.hbue.calc.utils.RateUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Objects;

public class ExchangeRateCalc {
    JFrame jf;

    public ExchangeRateCalc() {
        jf = new JFrame("汇率计算器");
        jf.setResizable(false);
        jf.setBounds(100,100,496,513);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.getContentPane().setLayout(null);

        JButton doneBtn = new JButton("完成");
        doneBtn.setBounds(176, 344, 100, 40);
        jf.getContentPane().add(doneBtn);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 492, 29);
        jf.getContentPane().add(menuBar);

        JMenu mnNewMenu = new JMenu("选项");
        menuBar.add(mnNewMenu);

        JMenuItem standardCalc = new JMenuItem("标准计算器");
        mnNewMenu.add(standardCalc);

        JMenuItem exchangeRateCalc = new JMenuItem("汇率计算器");
        mnNewMenu.add(exchangeRateCalc);

        String[] currency = new String[]{"人民币", "港元", "欧元", "美元", "英镑", "日元"};

        JComboBox<String> fromComboBox = new JComboBox<>(currency);
        fromComboBox.setBounds(300, 164, 86, 25);
        jf.getContentPane().add(fromComboBox);

        JTextField fromTextField = new JTextField();
        fromTextField.setBounds(145, 164, 145, 25);
        jf.getContentPane().add(fromTextField);
        fromTextField.setColumns(10);

        JLabel lblNewLabel = new JLabel("待兑换币种");
        lblNewLabel.setFont(new Font("宋体", Font.BOLD, 13));
        lblNewLabel.setBounds(61, 166, 73, 20);
        jf.getContentPane().add(lblNewLabel);

        JComboBox<String> toComboBox = new JComboBox<>(currency);
        toComboBox.setBounds(300, 231, 86, 25);
        jf.getContentPane().add(toComboBox);

        JTextField toTextField = new JTextField();
        toTextField.setEditable(false);
        toTextField.setColumns(10);
        toTextField.setBounds(145, 231, 145, 25);
        jf.getContentPane().add(toTextField);

        JLabel lblNewLabel_1 = new JLabel("目标币种");
        lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 13));
        lblNewLabel_1.setBounds(61, 233, 73, 20);
        jf.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("数据更新时间");
        lblNewLabel_1_1.setFont(new Font("宋体", Font.BOLD, 13));
        lblNewLabel_1_1.setBounds(48, 286, 86, 20);
        jf.getContentPane().add(lblNewLabel_1_1);

        JTextField timeTextField = new JTextField();
        timeTextField.setEditable(false);
        timeTextField.setColumns(10);
        timeTextField.setBounds(145, 286, 145, 25);
        jf.getContentPane().add(timeTextField);

        standardCalc.addActionListener(e -> {
            jf.dispose();
            new StandardCalc();
        });

        doneBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                double money = Double.parseDouble(fromTextField.getText());
                String from = (String) fromComboBox.getSelectedItem();
                String to = (String) toComboBox.getSelectedItem();

                switch(Objects.requireNonNull(from)) {
                    case "人民币": from = "CNY";break;
                    case "港币": from = "HKD";break;
                    case "欧元": from = "EUR";break;
                    case "美元": from = "USD";break;
                    case "英镑": from = "GBP";break;
                    case "日元": from = "JPY";break;
                    default:break;
                }

                switch(Objects.requireNonNull(to)) {
                    case "人民币": to = "CNY";break;
                    case "港币": to = "HKD";break;
                    case "欧元": to = "EUR";break;
                    case "美元": to = "USD";break;
                    case "英镑": to = "GBP";break;
                    case "日元": to = "JPY";break;
                    default:break;
                }

                List<String> getRates = RateUtil.getExchangeRate(from, to);
                double rate = Double.parseDouble(getRates.get(1));

                toTextField.setText(String.valueOf(money * rate));
                timeTextField.setText(getRates.get(2));
            }
        });

        jf.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        new ExchangeRateCalc();
    }
}
