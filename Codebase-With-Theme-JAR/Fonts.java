package GUI;

import java.awt.Color;
import java.awt.Font;
import javax.swing.text.*;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class Fonts {

	Style s;
	JTextPane tp;
	JFrame f;
	JDialog fontDialog;
	JButton B;
	JComboBox cb;
	String fname;
		
	Fonts()
	{
		f = new JFrame("FONT");
		f.setSize(500, 500);
		f.setLayout(null);
		
		tp = new JTextPane();
		tp.setBounds(0, 0, 300, 300);
		
		cb = new JComboBox();
		cb.setBounds(400, 400, 100, 30);
		f.add(cb);
		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Font[] allFonts = e.getAllFonts();
		for(int i=0;i<allFonts.length;i++)
		{
			cb.addItem(allFonts[i].getName());
		}
		cb.addItemListener(new ItemListener() {
			
			
			public void itemStateChanged(ItemEvent ie) 
			{
								
						fname = cb.getSelectedItem().toString();
				
			}
		});
		
		B = new JButton("Font");
		B.setBounds(100, 400, 20, 20);
		
		B.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent arg0) {
				String sel = tp.getSelectedText();
			
			Font f = new Font(fname,Font.BOLD,10);
				String txt = tp.getText();
				tp.setSelectedTextColor(Color.red);
							}
		});
		
		
		f.add(tp);
		f.add(B);
		
		f.setDefaultCloseOperation(3);
		f.setVisible(true);
		
		
	}
	public static void main(String[] args) 
	{
		new Fonts();
	}

}

///here is sometimes problem occurred..
///need to find out exact position of selected text
///try for this---- write 'int a; int b;' in textpane and 
///select only second 'int' word & change font

