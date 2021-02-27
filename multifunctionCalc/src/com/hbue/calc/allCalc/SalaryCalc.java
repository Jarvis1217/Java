package com.hbue.calc.allCalc;

import javax.swing.*;

import com.formdev.flatlaf.FlatLightLaf;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SalaryCalc {
	JFrame jf;

	public SalaryCalc() {
		jf = new JFrame("工资计算器");
		jf.setResizable(false);
		jf.setBounds(100,100,496,513);
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

		JLabel beforeLabel = new JLabel("个人应得工资");
		beforeLabel.setFont(new Font("宋体", Font.BOLD, 13));
		beforeLabel.setBounds(88, 159, 81, 21);
		jf.getContentPane().add(beforeLabel);

		JTextField beforeCalc = new JTextField();
		beforeCalc.setBounds(194, 159, 116, 21);
		jf.getContentPane().add(beforeCalc);
		beforeCalc.setColumns(10);

		JLabel taxLabel = new JLabel("应缴税额");
		taxLabel.setFont(new Font("宋体", Font.BOLD, 13));
		taxLabel.setBounds(88, 214, 81, 15);
		jf.getContentPane().add(taxLabel);

		JTextField taxCalc = new JTextField();
		taxCalc.setEditable(false);
		taxCalc.setBounds(194, 211, 116, 21);
		jf.getContentPane().add(taxCalc);
		taxCalc.setColumns(10);

		JLabel taxLabel_1 = new JLabel("税后所得工资");
		taxLabel_1.setFont(new Font("宋体", Font.BOLD, 13));
		taxLabel_1.setBounds(88, 268, 81, 15);
		jf.getContentPane().add(taxLabel_1);

		JTextField afterCalc = new JTextField();
		afterCalc.setEditable(false);
		afterCalc.setBounds(194, 265, 116, 21);
		jf.getContentPane().add(afterCalc);
		afterCalc.setColumns(10);

		JButton doneBtn = new JButton("完成");
		doneBtn.setFont(new Font("宋体", Font.PLAIN, 13));
		doneBtn.setBounds(194, 349, 97, 23);
		jf.getContentPane().add(doneBtn);


		standardCalc.addActionListener(e -> {
			jf.dispose();
			new StandardCalc();
		});

		exchangeRateCalc.addActionListener(e -> {
			jf.dispose();
			new ExchangeRateCalc();
		});

		loanCalc.addActionListener(e -> {
			jf.dispose();
			new LoanCalc();
		});

		doneBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				double beforeSalary = Double.parseDouble(beforeCalc.getText());
				if(beforeSalary <= 5000) {
					taxCalc.setText("0");
					afterCalc.setText("0");
				}
				else if(beforeSalary >5000 && beforeSalary <= 36000) {
					taxCalc.setText(String.valueOf(beforeSalary * 0.03));
					afterCalc.setText(String.valueOf(beforeSalary - beforeSalary * 0.03));
				}
				else if(beforeSalary >36000 && beforeSalary <= 144000) {
					taxCalc.setText(String.valueOf(Math.abs((beforeSalary - 36000) * 0.1 - 2520)));
					afterCalc.setText(String.valueOf(beforeSalary - Math.abs((beforeSalary - 36000) * 0.1 - 2520)));
				}
				else if(beforeSalary > 144000 && beforeSalary<= 300000) {
					taxCalc.setText(String.valueOf(Math.abs((beforeSalary - 144000) * 0.2 - 16920)));
					afterCalc.setText(String.valueOf(beforeSalary - Math.abs((beforeSalary - 144000) * 0.2 - 16920)));
				}
				else if(beforeSalary > 300000 && beforeSalary<= 420000) {
					taxCalc.setText(String.valueOf(Math.abs((beforeSalary - 300000) * 0.25 - 31920)));
					afterCalc.setText(String.valueOf(beforeSalary - Math.abs((beforeSalary - 300000) * 0.25 - 31920)));
				}
				else if(beforeSalary > 420000 && beforeSalary<= 660000) {
					taxCalc.setText(String.valueOf(Math.abs((beforeSalary - 420000) * 0.3 - 52920)));
					afterCalc.setText(String.valueOf(beforeSalary - Math.abs((beforeSalary - 420000) * 0.3 - 52920)));
				}
				else if(beforeSalary > 660000 && beforeSalary<= 960000) {
					taxCalc.setText(String.valueOf(Math.abs((beforeSalary - 660000) * 0.35 - 85920)));
					afterCalc.setText(String.valueOf(beforeSalary - Math.abs((beforeSalary - 660000) * 0.35 - 85920)));
				}
				else if(beforeSalary > 960000) {
					taxCalc.setText(String.valueOf(Math.abs((beforeSalary - 960000) * 0.45 - 181920)));
					afterCalc.setText(String.valueOf(beforeSalary - Math.abs((beforeSalary - 960000) * 0.45 - 181920)));
				}
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
		new SalaryCalc();
	}
}
