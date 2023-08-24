package GUI;

import javax.swing.*;
import java.awt.event.*;


public class Replace 
{
	JFrame f;
	JLabel replace,find;
	JTextField findTF,replaceTF;
	JButton findB,replaceB,replaceAllB;
	static String findStr,replaceStr;
	
	
	
	Replace()
	{
		f = new JFrame("Find & Replace");
		f.setBounds(0, 0, 500, 250);
		f.setLayout(null);
		
		find = new JLabel("Enter the String to be Found:");
		find.setBounds(10, 10, 220, 20);
		findTF = new JTextField(100);
		findTF.setBounds(240, 10, 150, 20);
		
		replace = new JLabel("Enter the String to be Replaced:" );
		replace.setBounds(10, 80, 250, 20);
		replaceTF = new JTextField(50);
		replaceTF.setBounds(260, 80, 150, 20);
		
		findB = new JButton("Find");
		findB.setBounds(10, 150, 100, 30);
		findB.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) 
			{
				findStr = findTF.getText();
			}
		});
		replaceB = new JButton("Replace");
		replaceB.setBounds(120, 150, 100, 30);
		replaceB.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) 
			{
				findStr = findTF.getText();
				replaceStr = replaceTF.getText();
				
			}
		});
		replaceAllB = new JButton("Replace All");
		replaceAllB.setBounds(230, 150, 120, 30);
		replaceAllB.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) 
			{
				replaceStr = replaceTF.getText();
			}
		});
		
		f.add(find);
		f.add(findTF);
		f.add(replace);
		f.add(replaceTF);
		f.add(findB);
		f.add(replaceB);
		f.add(replaceAllB);
		
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
