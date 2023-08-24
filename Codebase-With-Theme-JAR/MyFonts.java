package GUI;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;


public class MyFonts 
{
	JFrame f;
	Choice cb;
	JTextPane tp;
	static StyleContext sc;
	public MyFonts() 
	{
		f=new JFrame();
		f.setBounds(100, 100, 400, 400);
		f.setLayout(null);
		
		//JTextPane
		sc = new StyleContext();
	    final DefaultStyledDocument doc = new DefaultStyledDocument(sc);
	    tp=new JTextPane(doc);
		tp.setBounds(100, 100, 200, 200);
		f.add(tp);
		
		//Choice
		cb=new Choice();
		cb.setBounds(50, 10, 200, 50);
		f.add(cb);
				
		//*********************adding all available fonts into Choice*************************
		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Font[] allFonts = e.getAllFonts();
		for(int i=0;i<allFonts.length;i++)
		{
				//System.out.println(allFonts[i].getFontName());
				cb.add(allFonts[i].getName());
		}
		System.out.println("TOTAL FONTS:"+allFonts.length);
		//************************************************************************************
			
		
		
		cb.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent arg0) 
			{
				//getting selected font
				String fontname=cb.getSelectedItem();
				System.out.println("choosed font:"+fontname);
				
				//setting the style (font,color,size,bold/italic etc...)
				 final Style mystyle = sc.addStyle("prasad", null);
				 mystyle.addAttribute(StyleConstants.Foreground, Color.red);
				 //mystyle.addAttribute(StyleConstants.FontSize, new Integer(16));
				 mystyle.addAttribute(StyleConstants.FontFamily, fontname);
				 //mystyle.addAttribute(StyleConstants.Bold, new Boolean(true));
				 
				 
				 //setting style to selected text
				String selectext=tp.getSelectedText();
				String alltext=tp.getText();
				int offset=alltext.indexOf(selectext);   ///here is sometimes problem occurred..
														///need to find out exact position of selected text
														///try for this---- write 'int a; int b;' in textpane and 
														///select only second 'int' word & change font
				doc.setCharacterAttributes(offset, selectext.length(), mystyle, true);
				 

				
			}
		});
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	public static void main(String[] args) 
	{
		new MyFonts();	
	}

}
