package GUI;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.event.*;

public class Indentation extends JFrame 
{
	JTextPane tp;
	//t_no
	
	Indentation()
	{
		setSize(400, 400);
		setTitle("IN");
		tp = new JTextPane();
		tp.setBounds(0, 0, 400, 400);
		add(tp);
		tp.addKeyListener(new KeyAdapter() {
			
			
			public void keyTyped(KeyEvent kt) 
			{
				//if(kt.getKeyCode() == KeyEvent.VK_TAB)
					
				
			}
			
			public void keyReleased(KeyEvent kr) 
			{
			
				
				
				
				
				
			}
			
		});
		setDefaultCloseOperation(3);
		setVisible(true);
	}

	public static void main(String[] args) 
	{
		

	}

}
