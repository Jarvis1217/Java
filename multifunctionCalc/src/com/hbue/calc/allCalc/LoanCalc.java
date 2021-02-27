package com.hbue.calc.allCalc;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;

public class LoanCalc {
    JFrame jf;

    public LoanCalc() {
        jf = new JFrame("房贷计算器");
        jf.setResizable(false);
        jf.setBounds(100, 100, 496, 513);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.getContentPane().setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 492, 29);
        jf.getContentPane().add(menuBar);

        JMenu mnNewMenu = new JMenu("选项");
        menuBar.add(mnNewMenu);

        JMenuItem standardCalc = new JMenuItem("标准计算器");
        mnNewMenu.add(standardCalc);

        JMenuItem exchangeRateCalc = new JMenuItem("汇率计算器");
        mnNewMenu.add(exchangeRateCalc);

        JMenuItem salaryCalc = new JMenuItem("工资计算器");
        mnNewMenu.add(salaryCalc);

        JMenuItem loanCalc = new JMenuItem("房贷计算器");
        mnNewMenu.add(loanCalc);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "等额本金还款", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(10, 39, 458, 194);
        jf.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("贷款本金");
        lblNewLabel.setFont(new Font("宋体", Font.BOLD, 13));
        lblNewLabel.setBounds(10, 28, 58, 15);
        panel.add(lblNewLabel);

        JTextField capital = new JTextField();
        capital.setBounds(80, 25, 105, 21);
        panel.add(capital);
        capital.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("贷款期季数");
        lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 13));
        lblNewLabel_1.setBounds(207, 28, 75, 15);
        panel.add(lblNewLabel_1);

        JTextField season = new JTextField();
        season.setColumns(10);
        season.setBounds(292, 25, 105, 21);
        panel.add(season);

        JLabel lblNewLabel_1_1 = new JLabel("已归还本金");
        lblNewLabel_1_1.setFont(new Font("宋体", Font.BOLD, 13));
        lblNewLabel_1_1.setBounds(10, 84, 75, 18);
        panel.add(lblNewLabel_1_1);

        JTextField haveReturn = new JTextField();
        haveReturn.setColumns(10);
        haveReturn.setBounds(80, 83, 105, 21);
        panel.add(haveReturn);

        JLabel lblNewLabel_1_1_1 = new JLabel("季利率");
        lblNewLabel_1_1_1.setFont(new Font("宋体", Font.BOLD, 13));
        lblNewLabel_1_1_1.setBounds(220, 83, 47, 18);
        panel.add(lblNewLabel_1_1_1);

        JTextField seasonRate = new JTextField();
        seasonRate.setColumns(10);
        seasonRate.setBounds(292, 83, 105, 21);
        panel.add(seasonRate);

        JLabel lblNewLabel_1_1_2 = new JLabel("剩余每季还款额");
        lblNewLabel_1_1_2.setFont(new Font("宋体", Font.BOLD, 13));
        lblNewLabel_1_1_2.setBounds(80, 147, 102, 18);
        panel.add(lblNewLabel_1_1_2);

        JTextField result = new JTextField();
        result.setEditable(false);
        result.setColumns(10);
        result.setBounds(177, 147, 105, 21);
        panel.add(result);

        JButton aCalc = new JButton("计算");
        aCalc.setFont(new Font("宋体", Font.BOLD, 13));
        aCalc.setBounds(288, 146, 97, 23);
        panel.add(aCalc);

        aCalc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                double cap = Double.parseDouble(capital.getText());
                double sea = Double.parseDouble(season.getText());
                double ret = Double.parseDouble(haveReturn.getText());
                double rate = Double.parseDouble(seasonRate.getText());

                result.setText(String.valueOf(new BigDecimal(cap / sea + (cap - ret) * rate).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue()));
            }
        });

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "等额本息还款", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_1.setBounds(10, 243, 458, 216);
        jf.getContentPane().add(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel_2 = new JLabel("贷款本金");
        lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 13));
        lblNewLabel_2.setBounds(116, 39, 69, 15);
        panel_1.add(lblNewLabel_2);

        JTextField AA = new JTextField();
        AA.setBounds(195, 36, 102, 21);
        panel_1.add(AA);
        AA.setColumns(10);

        JLabel lblNewLabel_2_1 = new JLabel("还款月数");
        lblNewLabel_2_1.setFont(new Font("宋体", Font.BOLD, 13));
        lblNewLabel_2_1.setBounds(116, 81, 69, 15);
        panel_1.add(lblNewLabel_2_1);

        JTextField BB = new JTextField();
        BB.setColumns(10);
        BB.setBounds(195, 78, 102, 21);
        panel_1.add(BB);

        JLabel lblNewLabel_2_1_1 = new JLabel("月利率");
        lblNewLabel_2_1_1.setFont(new Font("宋体", Font.BOLD, 13));
        lblNewLabel_2_1_1.setBounds(116, 127, 69, 18);
        panel_1.add(lblNewLabel_2_1_1);

        JTextField CC = new JTextField();
        CC.setColumns(10);
        CC.setBounds(195, 127, 102, 21);
        panel_1.add(CC);

        JLabel lblNewLabel_2_1_1_1 = new JLabel("每月还款额");
        lblNewLabel_2_1_1_1.setFont(new Font("宋体", Font.BOLD, 13));
        lblNewLabel_2_1_1_1.setBounds(83, 177, 102, 15);
        panel_1.add(lblNewLabel_2_1_1_1);

        JTextField result_1 = new JTextField();
        result_1.setEditable(false);
        result_1.setColumns(10);
        result_1.setBounds(195, 174, 102, 21);
        panel_1.add(result_1);

        JButton bCalc = new JButton("计算");
        bCalc.setFont(new Font("宋体", Font.BOLD, 13));
        bCalc.setBounds(307, 173, 97, 23);
        panel_1.add(bCalc);

        bCalc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                double cap = Double.parseDouble(AA.getText());
                double mon = Double.parseDouble(BB.getText());
                double monRate = Double.parseDouble(CC.getText());

                double q = cap * monRate * Math.pow(1 + monRate, mon);
                double w = Math.pow(1 + monRate, mon) - 1;

                result_1.setText(String.valueOf(new BigDecimal(q / w).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue()));
            }
        });

        standardCalc.addActionListener(e -> {
            jf.dispose();
            new StandardCalc();
        });

        exchangeRateCalc.addActionListener(e -> {
            jf.dispose();
            new ExchangeRateCalc();
        });

        salaryCalc.addActionListener(e -> {
            jf.dispose();
            new SalaryCalc();
        });

        jf.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        new LoanCalc();
    }
}
