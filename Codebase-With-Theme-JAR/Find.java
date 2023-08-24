package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Find 
{
	JFrame f;
	JButton findB,findNextB,cancelB;
	JTextField findTF;
	JLabel find;
	static String findStr; 
	
	Find()
	{
		f = new JFrame("Find");
		f.setBounds(0, 0, 400, 200);
		f.setLayout(null);
		
		
		find = new JLabel("Enter the String to Find:");
		find.setBounds(10, 20, 180, 20);
		
		findTF = new JTextField(50);
		findTF.setBounds(200, 20, 150, 20);
		
		findB = new JButton("Find");
		findB.setBounds(20, 100, 70, 30);
		findB.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) 
			{
				findStr = findTF.getText();
				System.out.println(findStr);
			}
		});
		
		findNextB = new JButton("Find Next");
		findNextB.setBounds(100, 100, 100, 30);
		cancelB = new JButton("Cancel");
		cancelB.setBounds(210, 100, 100, 30);
		
		f.add(find);
		f.add(findTF);
		f.add(findB);
		f.add(findNextB);
		f.add(cancelB);
		
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
}
