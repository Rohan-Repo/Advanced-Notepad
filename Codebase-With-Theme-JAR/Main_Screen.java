import java.awt.*;
import java.io.*;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.undo.*;
import java.awt.event.*;
import java.awt.print.*;

public class Main_Screen
{
	UndoManager manager = new UndoManager();
    LinkedList tab_no,file_name;
	JLabel msg1,msg2;
	
	PrinterJob prnJob = PrinterJob.getPrinterJob();;
	PageFormat pf;
	
	// KEY TYPED
	boolean altFlag=false,cntrlFlag;
	
	// Fonts 
	JFrame fontF;
	String[] sel_op = new String[3];
	List f_nm,f_style,f_sz;
	JLabel fnmL,fszL,fstyL; 
	JButton okB,cancelBut;
	
	//C Language
	boolean openFlag=false,roundFlag=false,squareFlag=false;
	int offset;
	int start = 0;
	String str1="",str2="";
	StyleContext sc = new StyleContext();
	DefaultStyledDocument doc;
	Style mystyle;
	
	//replace
	JFrame replacef;
	JLabel replace,find;
	JTextField findTF,replaceTF;
	JButton findB,replaceB,replaceAllB;
		
	// Find
	JFrame findFrame;
	JButton findBt,findNextB,cancelB;
	JTextField find_TF;
	JLabel findL;
	
	String txt="";
		
	String selectedText="",findStr="",replaceStr="";
	static JPanel p;
	int pos,i;
	static int Tabcnt=-1;
	JPanel above,editingArea;
	JFrame f;
	static JScrollPane sp;
	JTabbedPane tp;
	static JTextPane txtp[]=new JTextPane[50];
	static String filePath[] = new String[50];
	JMenuBar mb;
	JMenu fileMenu,editMenu,viewMenu,formatMenu,languageMenu,helpMenu;
	JMenuItem fileNew,fileOpen,fileSave,fileSaveAs,fileSaveAll,fileClose,fileCloseAll,filePageSetup,filePrint,fileExit,editDate,editUndo,editRedo,editCut,editCopy,editPaste,editSelectAll,editFind,editReplace,editUpper,editLower,formatFonts,viewThemes,helpAbout,helpContents,langC,langJava;
	JToolBar tb;
	JButton newIcon,openIcon,saveIcon,saveAllIcon,closeIcon,closeAllIcon,cutIcon,copyIcon,pasteIcon,undoIcon,redoIcon,spellCheckIcon,findIcon,replaceIcon,boldIcon,italicsIcon;
	JComboBox fontName,fontSize;
	ImageIcon newImg,openImg,saveImg,saveAllImg,closeImg,closeAllImg,cutImg,copyImg,pasteImg,undoImg,redoImg,spellCheckImg,findImg,replaceImg,boldImg,italicsImg;
			
	// themes
	JRadioButton metalR,motifR,nimbusR;
	JLabel metalL,motifL,nimbusL;
	JFrame themesF;
	JButton applyB,canB;
	
	Main_Screen()
	{
		f = new JFrame("Advanced Notepad");
		f.setLayout(null);
		f.setBounds(0, 0, 1000, 770);	
		
			
		//TOP PANEL:
		above = new JPanel();
		above.setLayout(null);
		above.setBounds(0, 0, 1000, 80);
		// Menu Bar:
		
		mb = new JMenuBar();
		mb.setBounds(0, 0, 1000, 30);
		//mb.setLayout(null);
		
		fileMenu = new JMenu("File");
		fileMenu.setBounds(10, 10, 150, 20);
		fileMenu.setToolTipText("Contains the Basic File Functions");
		fileMenu.setMnemonic('F');
		editMenu = new JMenu("Edit");
		editMenu.setBounds(180, 10, 150, 20);
		editMenu.setMnemonic('E');
		editMenu.setToolTipText("Contains the basic Editing Functions");
		viewMenu = new JMenu("View");
		viewMenu.setBounds(350, 10, 150, 20);
		viewMenu.setMnemonic('V');
		viewMenu.setToolTipText("Contains View Settings");
		formatMenu = new JMenu("Format");
		formatMenu.setBounds(520,10, 150, 20);
		formatMenu.setMnemonic('o');
		formatMenu.setToolTipText("Contains the Formatting Options");
		languageMenu = new JMenu("Language");
		languageMenu.setBounds(690, 10, 150, 20);
		languageMenu.setMnemonic('L');
		languageMenu.setToolTipText("Contains options concerning Programming Languages");
		
		helpMenu = new JMenu("Help");
		helpMenu.setBounds(850, 10, 150, 20);
		helpMenu.setMnemonic('H');
		helpMenu.setToolTipText("HELP!!!");
		
		// File Menu:
		
		fileNew = new JMenuItem("New");
		fileNew.setMnemonic('N');
		fileNew.addMouseListener(new MouseAdapter() 
		{
			public void mousePressed(MouseEvent arg0) 
			{
				newF();
				tp.setSelectedIndex(Tabcnt);
		}
		});
				
		fileOpen = new JMenuItem("Open");
		fileOpen.setMnemonic('O');
		fileOpen.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent arg0) 
			{
				open();
			}
		});
					
		fileSave = new JMenuItem("Save");
		fileSave.setMnemonic('S');
		
		fileSave.addMouseListener(new MouseAdapter(){
		
			public void mousePressed(MouseEvent arg0) 
			{
				save();	
			}
		});
		
		fileSaveAs = new JMenuItem("Save As");
		fileSaveAs.setMnemonic('A');
		fileSaveAs.addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent arg0) 
			{
				saveAs();
			}
		});
		
			
		fileSaveAll = new JMenuItem("Save All");
		fileSaveAll.setMnemonic('v');
		fileSaveAll.addMouseListener(new MouseAdapter(){
			
			public void mousePressed(MouseEvent arg0) 
			{
				saveAll();	
			}
		});
			
		fileClose = new JMenuItem("Close");
		fileClose.setMnemonic('C');
		fileClose.addMouseListener(new MouseAdapter(){
			
			public void mousePressed(MouseEvent arg0) 
			{
				close();
			}
		});
		
		fileCloseAll = new JMenuItem("Close All");
		fileCloseAll.setMnemonic('l');
		fileCloseAll.addMouseListener(new MouseAdapter(){
			
			public void mousePressed(MouseEvent arg0) 
			{
				closeAll();
			}
		});
		
		filePageSetup = new JMenuItem("Page Setup");
		filePageSetup.setMnemonic('g');
		filePageSetup.addMouseListener(new MouseAdapter(){
			
			public void mousePressed(MouseEvent arg0) 
			{
				try
			  	{
					txtp[tp.getSelectedIndex()].print();
			  	}
			  	catch(Exception e)
			  	{
			  		e.printStackTrace();
			  	}

			}
		});
		filePrint = new JMenuItem("Print");
		filePrint.setMnemonic('P');
		filePrint.addMouseListener(new MouseAdapter(){
			
			public void mousePressed(MouseEvent arg0) 
			{
				try 
				{
					// prnJob.print();
					txtp[tp.getSelectedIndex()].print();
				} 
				catch (PrinterException e) 
				{
					e.printStackTrace();
				}
			}
		});
		fileExit = new JMenuItem("Exit");
		fileExit.setMnemonic('E');
		fileExit.addMouseListener(new MouseAdapter(){
			
			public void mousePressed(MouseEvent arg0) {
				f.dispose();
			}
		});
			
		fileMenu.add(fileNew);
		fileMenu.add(fileOpen);
		fileMenu.add(fileSave);
		fileMenu.add(fileSaveAs);
		fileMenu.add(fileSaveAll);
		fileMenu.add(fileClose);
		fileMenu.add(fileCloseAll);
		fileMenu.add(filePrint);
		fileMenu.add(filePageSetup);
		fileMenu.add(fileExit);
		
		// Edit - Menu
			
			editUndo = new JMenuItem("Undo");
			editUndo.addMouseListener(new MouseAdapter(){
				
				public void mousePressed(MouseEvent arg0) 
				{
					try
					{
						manager.undo();
					}
					catch(CannotUndoException e)
					{
						JOptionPane.showMessageDialog(null, "Can not Undo!!!");
					}
				}
			});
			
			editRedo =new JMenuItem("Redo");
			editRedo.addMouseListener(new MouseAdapter(){
				
				public void mousePressed(MouseEvent arg0) 
				{
					try
					{
						manager.redo();
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(null, "Can not Redo!!!");
					}
				}
			});
			
			editCut = new JMenuItem("Cut");
			editCut.addMouseListener(new MouseAdapter(){
				
				public void mousePressed(MouseEvent arg0) 
				{
					txtp[tp.getSelectedIndex()].cut();
				}
			});
			
			editCopy =new JMenuItem("Copy");
			editCopy.addMouseListener(new MouseAdapter(){
				
				public void mousePressed(MouseEvent arg0) 
				{
					txtp[tp.getSelectedIndex()].copy();
				}
			});
			
			editPaste = new JMenuItem("Paste");
			editPaste.addMouseListener(new MouseAdapter(){
				
				public void mousePressed(MouseEvent arg0) 
				{
					txtp[tp.getSelectedIndex()].paste();
				}
			});
			
			editSelectAll = new JMenuItem("Select All");
			editSelectAll.addMouseListener(new MouseAdapter(){
				
				public void mousePressed(MouseEvent arg0) 
				{
					txtp[tp.getSelectedIndex()].selectAll();
				}
			});
			
			editReplace = new JMenuItem("Find & Replace");
			editReplace.addMouseListener(new MouseAdapter(){
				
				public void mousePressed(MouseEvent arg0) 
				{
					ReplaceClass();
				}
			});
			
			editUpper = new JMenuItem("to UpperCase");
			editUpper.addMouseListener(new MouseAdapter(){
				
				public void mousePressed(MouseEvent arg0) 
				{
					try
					{
						String txt = txtp[tp.getSelectedIndex()].getSelectedText().toUpperCase();
						txtp[tp.getSelectedIndex()].replaceSelection(txt);
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(null, "Pl. Select Some Text");
					}
				}
			});
			
			editLower = new JMenuItem("to LowerCase"); 
			editLower.addMouseListener(new MouseAdapter(){
				
				public void mousePressed(MouseEvent arg0) 
				{
					try
					{
						String txt = txtp[tp.getSelectedIndex()].getSelectedText().toLowerCase();
						txtp[tp.getSelectedIndex()].replaceSelection(txt);
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(null, "Pl. Select Some Text");
					}
				}
			});
			
			editDate = new JMenuItem("Date/Time");
			editDate.addMouseListener(new MouseAdapter() {
				
				public void mousePressed(MouseEvent arg0) 
				{
					String data = txtp[tp.getSelectedIndex()].getText();
					
					java.util.Date d = new Date();
					txtp[tp.getSelectedIndex()].setText((data+"\n"+"Date & Time = "+d));
				}
			});
			
			editMenu.add(editUndo);
			editMenu.add(editRedo);
			editMenu.add(editCut);
			editMenu.add(editCopy);
			editMenu.add(editPaste);
			editMenu.add(editReplace);
			editMenu.add(editSelectAll);
			editMenu.add(editUpper);
			editMenu.add(editLower);
			editMenu.add(editDate);
			
			//Format Menu
			formatFonts = new JMenuItem("Fonts");
			formatFonts.setToolTipText("Select Desired Fonts");
			formatFonts.setMnemonic('F');
			formatFonts.addMouseListener(new MouseAdapter(){
				
				public void mousePressed(MouseEvent arg0) 
				{
						Fonts();
				}
			});
			formatMenu.add(formatFonts);
			
			// View Menu 
			
			viewThemes = new JMenuItem("Themes");
			
			viewThemes.setToolTipText("Select Desired Themes");
			viewThemes.setMnemonic('T');
			viewThemes.addMouseListener(new MouseAdapter() {
				
				public void mousePressed(MouseEvent e) 
				{
					themes();
				}
				
			});
			viewMenu.add(viewThemes);
			
			// Language Menu
			
			langC = new JMenuItem("C/C++");
			langC.setToolTipText("C/C++ Programming Helper");
			langC.setMnemonic('C');
			langC.addMouseListener(new MouseAdapter() {
				
				public void mousePressed(MouseEvent e) 
				{
					cLanguage();
					start = txtp[tp.getSelectedIndex()].getCaretPosition();
					System.out.println("C");
				}
			});
			langJava = new JMenuItem("Java");
			langJava.setToolTipText("Java Program Helper");
			langJava.setMnemonic('J');
			langJava.addMouseListener(new MouseAdapter() {
				
				public void mousePressed(MouseEvent arg0) {
					javaLanguage();
					start = txtp[tp.getSelectedIndex()].getCaretPosition();
					System.out.println("Java");
				}
			});
			
			languageMenu.add(langC);
			languageMenu.add(langJava);
			
			
			// Help Menu
			
			helpAbout = new JMenuItem("About Notepad");
			helpAbout.setToolTipText("Info. about Advanced Notepad");
			helpAbout.setMnemonic('A');
			helpAbout.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent me)
				{
					About();
				}
			});
			
			helpContents = new JMenuItem("Help Contents");
			helpContents.setToolTipText("Complete Help Contents");
			helpContents.setMnemonic('C');
			helpContents.addMouseListener(new MouseAdapter() {
				
				public void mousePressed(MouseEvent arg0) {
					helpContents();
				}
			});
			
			
			helpMenu.add(helpAbout);
			helpMenu.add(helpContents);
			
			mb.add(fileMenu);
			mb.add(editMenu);
			mb.add(formatMenu);
			mb.add(viewMenu);
			mb.add(languageMenu);
			mb.add(helpMenu);
			
			// Tool Bar:
			
			tb = new JToolBar();
			tb.setLayout(new FlowLayout());
			tb.setBounds(0, 30, 1000, 50);
			
			newImg = new ImageIcon(path()+"NewDoc.PNG");
			newIcon = new JButton(newImg);
			newIcon.setBounds(20, 55, 20, 20);
				
			newIcon.addActionListener(new ActionListener() {
				
				
				public void actionPerformed(ActionEvent e) 
				{
						newF();	
						tp.setSelectedIndex(Tabcnt);
				}
			});
			newIcon.setToolTipText("Create a new Tab!!");
						
			openImg = new ImageIcon(path()+"OpenDoc.PNG");
			openIcon = new JButton(openImg);
			openIcon.setBounds(40, 55, 20, 20);
			openIcon.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) 
				{
					open();
					autoIndentation();
				}
			}
			);
			openIcon.setToolTipText("Open's a File!");
						
			saveImg = new ImageIcon(path()+"Save.PNG");
			saveIcon = new JButton(saveImg);
			
			saveIcon.setBounds(60, 55, 20, 20);
			saveIcon.addActionListener(new ActionListener() {
				
				
				public void actionPerformed(ActionEvent e) 
				{
					save();
				}
			}
			);
			saveIcon.setToolTipText("Save the current tab's Contents!");
			
			saveAllImg = new ImageIcon(path()+"SaveAll.PNG");
			saveAllIcon = new JButton(saveAllImg);
			
			saveAllIcon.setBounds(80, 55, 20, 20);
			saveAllIcon.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					saveAll();
				}
			});
			saveAllIcon.setToolTipText("Save all tab's Contents!");
			
			closeImg = new ImageIcon(path()+"CloseCurr.PNG");
			closeIcon = new JButton(closeImg);
		
			closeIcon.setBounds(100, 55, 20, 20);
			closeIcon.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					close();
				}
			});
			closeIcon.setToolTipText("Close the current tab");
			
			closeAllImg = new ImageIcon(path()+"CloseAll.PNG");
			closeAllIcon = new JButton(closeAllImg);
		
			closeAllIcon.setBounds(120, 55, 20, 20);
			closeAllIcon.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
						closeAll();
				}
			});
			closeAllIcon.setToolTipText("Close All tabs");
			
			cutImg = new ImageIcon(path()+"Cut.PNG");
			cutIcon = new JButton(cutImg);
			
			cutIcon.setBounds(140, 55, 20, 20);
			cutIcon.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) 
				{
					try
					{
						int pos = tp.getSelectedIndex();
						txtp[pos].cut();
					}
					catch(Exception e1)
					{
						JOptionPane.showMessageDialog(null, "Pl. Select some text!!!");
					}
				}
			});
			cutIcon.setToolTipText("CUT");
			
			copyImg = new ImageIcon(path()+"Copy.PNG");
			copyIcon = new JButton(copyImg);
			
			copyIcon.setBounds(160, 55, 20, 20);
			copyIcon.addActionListener(new ActionListener() {
				
				
				public void actionPerformed(ActionEvent e) 
				{
					int pos = tp.getSelectedIndex();
					txtp[pos].copy();
				}
			});
			copyIcon.setToolTipText("COPY");
			
			
			pasteImg = new ImageIcon(path()+"Paste.PNG");
			pasteIcon = new JButton(pasteImg);
			pasteIcon.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) 
				{
					int pos = tp.getSelectedIndex();
					txtp[pos].paste();
				}
			});
			pasteIcon.setBounds(180, 55, 20, 20);
			pasteIcon.setToolTipText("PASTE");
			
			undoImg = new ImageIcon(path()+"Undo.PNG");
			undoIcon = new JButton(undoImg);
						
			undoIcon.setBounds(200, 55, 20, 20);
			undoIcon.addActionListener(new ActionListener() {
			
				public void actionPerformed(ActionEvent e) 
				{
					try
					{
						manager.undo();
					}
					catch(CannotUndoException e1)
					{
						JOptionPane.showMessageDialog(null, "Can not Undo!!!");
					}
					
				}
			});
			
			undoIcon.setToolTipText("UNDO");
			
			redoImg = new ImageIcon(path()+"Redo.PNG");
			redoIcon = new JButton(redoImg);
			
			redoIcon.setBounds(220, 55, 20, 20);
			
			redoIcon.addActionListener(new ActionListener() {
				
				
				public void actionPerformed(ActionEvent e) {
					try
					{
						manager.redo();
					}
					catch(CannotRedoException ee)
					{
						JOptionPane.showMessageDialog(null, "Can not Redo!!");
					}
					
				}
			});
			
			redoIcon.setToolTipText("REDO");
	
			replaceImg = new ImageIcon(path()+"Replace.PNG");
			replaceIcon = new JButton(replaceImg);
			
			replaceIcon.setBounds(260, 55, 20, 20);
			replaceIcon.addActionListener(new ActionListener() {
				
				
				public void actionPerformed(ActionEvent e) 
				{
					ReplaceClass();
				}
			});
			replaceIcon.setToolTipText("FIND & REPLACE!!");
			
			boldImg = new ImageIcon(path()+"Bold.gif");
			boldIcon = new JButton(boldImg);
			boldIcon.addActionListener(new ActionListener() {
				
				
				public void actionPerformed(ActionEvent e) 
				{
					doc = (DefaultStyledDocument)txtp[tp.getSelectedIndex()].getDocument();
					mystyle = sc.addStyle("sytle1", null);
					mystyle.addAttribute(StyleConstants.Bold, new Boolean(true));
					doc.setCharacterAttributes(txtp[tp.getSelectedIndex()].getSelectionStart(), txtp[tp.getSelectedIndex()].getSelectionEnd(), mystyle, true);
				}
			});
		
			boldIcon.setBounds(300, 55, 10, 30);
			boldIcon.setToolTipText("BOLD");
			
			italicsImg = new ImageIcon(path()+"Italics.gif");
			italicsIcon = new JButton(italicsImg);
			italicsIcon.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) 
				{
					doc = (DefaultStyledDocument)txtp[tp.getSelectedIndex()].getDocument();
					mystyle = sc.addStyle("style1", null);
					mystyle.addAttribute(StyleConstants.Italic, new Boolean(true));
					doc.setCharacterAttributes(txtp[tp.getSelectedIndex()].getSelectionStart(),txtp[tp.getSelectedIndex()].getSelectionEnd(), mystyle, true);
				}
			});
			italicsIcon.setBounds(320, 55, 10, 30);
			italicsIcon.setToolTipText("ITALICS");
			
			spellCheckImg = new ImageIcon(path()+"SpellCheck.PNG");
			spellCheckIcon = new JButton(spellCheckImg);
			spellCheckIcon.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) 
				{
					boolean flag = false;
					
					System.out.println("clicked");
					String data=txtp[tp.getSelectedIndex()].getText();
					String words[]=data.split(" ");
					String line="";
					File f=new File("Dictionary.txt");
					System.out.println("PATH="+f.getAbsolutePath());
					try 
					{
						doc = (DefaultStyledDocument)txtp[tp.getSelectedIndex()].getDocument(); 
						final Style mystyle0 = sc.addStyle("prasad1", null);
						mystyle0.addAttribute(StyleConstants.Foreground, Color.black);
						String d=txtp[tp.getSelectedIndex()].getText();
						doc.setCharacterAttributes(0, d.length(), mystyle0, true);
						
						
						for(int i=0;i<words.length;i++)
						{
							offset=data.indexOf(words[i]);
							FileReader fr=new FileReader(f);
							BufferedReader br=new BufferedReader(fr);
							while((line=br.readLine())!=null)
							{
							//	System.out.println(line);
								if(line.equalsIgnoreCase(words[i]))
								{
									flag=true;
									break;
								}
							}
							if(!flag)
							{
								final Style mystyle1 = sc.addStyle("prasadk", null);
								mystyle1.addAttribute(StyleConstants.Foreground, Color.RED);
								mystyle1.addAttribute(StyleConstants.Underline, new Boolean(true));
								mystyle1.addAttribute(StyleConstants.Italic, new Boolean(true));
								d=txtp[tp.getSelectedIndex()].getText();
								doc.setCharacterAttributes(offset, words[i].length(), mystyle1, true);
								System.out.println("NOT FOUND IN DICTONAY: "+words[i]);
							}
							flag=false;
								
						}
					} catch (Exception e) {e.printStackTrace();
					}
				}
			});
				
			spellCheckIcon.setToolTipText("SPELL CHECK!!!!!");
			
			fontName = new JComboBox();
			fontName.setBounds(340, 55, 50, 25);
					
			GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
			Font[] allFonts = e.getAllFonts();
			for(int i=0;i<allFonts.length;i++)
				fontName.addItem(allFonts[i].getName());
			
			System.out.println("F_NO:"+allFonts.length);
			fontName.addItemListener(new ItemListener() {
				
				public void itemStateChanged(ItemEvent arg0) 
				{
					doc = (DefaultStyledDocument) txtp[tp.getSelectedIndex()].getDocument();
					//getting selected font
					String fontname= fontName.getSelectedItem().toString();
					System.out.println("choosed font:"+fontname);
							
					//setting the style (font,color,size,bold/italic etc...)
					mystyle = sc.addStyle("style1",null);
					
					mystyle.addAttribute(StyleConstants.FontFamily, fontname);
					
							 
					//setting style to selected text
					String selectext=txtp[tp.getSelectedIndex()].getSelectedText();
					System.out.println("Sel:"+selectext);
					String alltext=txtp[tp.getSelectedIndex()].getText();
					System.out.println("ALL:"+alltext);
					   
					int selStart = txtp[tp.getSelectedIndex()].getSelectionStart();
					doc.setCharacterAttributes(selStart, selectext.length(), mystyle, true);
				}
			});
									
			String[] fontSizes = {"6","8","10","12","14","16","20","22","24","26","28","30"};
			
			fontSize = new JComboBox(fontSizes);
			fontSize.setBounds(420, 55, 50, 30);
			
			fontSize.addItemListener(new ItemListener() {
				
				public void itemStateChanged(ItemEvent arg0) 
				{
					int f_sz = (Integer.parseInt(fontSize.getSelectedItem().toString()));
					doc = (DefaultStyledDocument)txtp[tp.getSelectedIndex()].getDocument();
					mystyle = sc.addStyle("style1", null);
					mystyle.addAttribute(StyleConstants.FontSize, f_sz);
					System.out.println("start"+txtp[tp.getSelectedIndex()].getSelectionStart());
					System.out.println("end"+txtp[tp.getSelectedIndex()].getSelectionEnd());
					int sel_len =  txtp[tp.getSelectedIndex()].getSelectionEnd()-txtp[tp.getSelectedIndex()].getSelectionStart();
					doc.setCharacterAttributes(txtp[tp.getSelectedIndex()].getSelectionStart(),sel_len, mystyle, true);
				}
			});
			
			tb.add(newIcon);
			tb.add(openIcon);
			tb.add(saveIcon);
			tb.add(saveAllIcon);
			tb.add(closeIcon);
			tb.add(closeAllIcon);
			tb.add(cutIcon);
			tb.add(copyIcon);
			tb.add(pasteIcon);
			tb.add(undoIcon);
			tb.add(redoIcon);
			tb.add(boldIcon);
			tb.add(italicsIcon);
			tb.add(fontName);
			tb.add(fontSize);
			tb.add(replaceIcon);
			tb.add(spellCheckIcon);
			
			
			
			above.add(mb);
			above.add(tb);
		
			f.add(above);
			tp = new JTabbedPane();
			tp.setBounds(0, 80, 1000, 600);
		
			Tabcnt++;
			String Name = "Tab "+Tabcnt;
			System.out.println("T="+Tabcnt);
	
			tp.add(Name,Main_Screen.createTab(Tabcnt));
			tp.setTitleAt(Tabcnt, Name);
			tp.setName("");
			
			msg1 = new JLabel("Auto Saved!!! ");
			msg1.setForeground(Color.blue);
			msg1.setBounds(400, 685, 100, 10);
			msg1.setVisible(false);
			
			msg2 = new JLabel("Auto Saved 2 Minutes Before!!!!!");
			msg2.setBounds(400, 685, 300, 10);
			msg2.setVisible(false);
			msg2.setForeground(Color.blue);
			
			txtp[0].setName("");
			
			txtp[0].getDocument().addUndoableEditListener(new UndoableEditListener() {
				
				
				public void undoableEditHappened(UndoableEditEvent arg0) 
				{
					manager.addEdit(arg0.getEdit());
				}
			});

		    f.add(tp);
			f.add(msg1);
			f.add(msg2);	
			f.setVisible(true);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
		
	public static JPanel createTab(int pos)
	{
		p = new JPanel();
		p.setLayout(null);
		txtp[pos] = new JTextPane();
		sp = new JScrollPane(txtp[pos],JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp.setBounds(0, 0, 987, 570);
		p.add(sp);
		return p;
	}
	
	public void save()
	{
		if(txtp[tp.getSelectedIndex()].getName().equals(""))
		{
			System.out.println("new file");
			JFileChooser jf = new JFileChooser();
			
			int res = jf.showSaveDialog(null);
			if(res == 0)
			{
					File f = jf.getSelectedFile();
					String name = f.getName();
					if(!name.endsWith(".."))
					{
						if(((name.endsWith(".c"))||(name.endsWith(".cpp"))||(name.endsWith(".java"))||(name.endsWith(".txt"))||(name.endsWith(".doc"))))
						{
							try
							{
								
								f.createNewFile();
								int i=tp.getSelectedIndex();
								System.out.println("INDEX="+i);
								filePath[i] = new String("");
								filePath[i] = f.getAbsolutePath();
								System.out.println("Path ="+filePath[i]);
								//file_name.add(i, filePath[i]);
								FileWriter fw = new FileWriter(f);
								String text = txtp[i].getText();
								System.out.println(text);
								fw.write(text);
								fw.close();
							}
							catch(IOException e2 )
							{
								e2.printStackTrace();
							}
					
							if(Tabcnt == 0)
							{
								
								System.out.println("aASDDDDDDDDDDddx");
								tp.setTitleAt(0, jf.getSelectedFile().getName());
								txtp[0].setName(jf.getSelectedFile().getName());
								System.out.println("fName="+jf.getSelectedFile().getName());
								tp.setName(jf.getSelectedFile().getName());
							}
							
							else
							{
								System.out.println("TAB ="+tp.getSelectedIndex());
								tp.setTitleAt(tp.getSelectedIndex(), jf.getSelectedFile().getName());
								txtp[tp.getSelectedIndex()].setName(jf.getSelectedFile().getName());
								tp.setName(jf.getSelectedFile().getName());
							}
						}
						else
							JOptionPane.showMessageDialog(null, "Invalid File Extension" + "Allowed File Extensions are : .c, .cpp, .java, .txt & .doc" );
					}
					else
						JOptionPane.showMessageDialog(null, "Invalid File Extension" + "Allowed File Extensions are : .c, .cpp, .java, .txt & .doc" );
			}
		}
		else
		{
			int tno = tp.getSelectedIndex();
			System.out.println("old file"+tno);
			FileWriter fw;
			try 
			{
				String p = "";
				p= filePath[tno];
				System.out.println("Path = "+p);
				File f = new File(p);
				System.out.println("FP"+f.getAbsolutePath());
				f.delete();
				f.createNewFile();
				fw = new FileWriter(f);
				String txt = txtp[tno].getText();
				fw.write(txt);
				fw.close();
			} 
			catch (IOException e1)
			{
				e1.printStackTrace();
			}
			
		}
	}
	
	void saveAs()
	{
		int tabno=tp.getSelectedIndex();
		
		JFileChooser jf = new JFileChooser();
			
		int res = jf.showSaveDialog(null);
		if(res == 0)
		{
				File f = jf.getSelectedFile();
				String name = f.getName();
				if(!name.endsWith(".."))
				{
					if(((name.endsWith(".c"))||(name.endsWith(".cpp"))||(name.endsWith(".java"))||(name.endsWith(".txt"))||(name.endsWith(".doc"))))
					{
						try
						{
								f.createNewFile();
								int i=tp.getSelectedIndex();
								System.out.println("TB="+i);
								filePath[i] = new String("");
								filePath[i] = f.getAbsolutePath();
								System.out.println("Path ="+filePath[i]);
								
								FileWriter fw = new FileWriter(f);
								String text = txtp[i].getText();
								System.out.println(text);
								fw.write(text);
								fw.close();
						}
						catch(IOException e2 )
						{
							e2.printStackTrace();
						}
					
						if(Tabcnt == 0)
						{
							tp.setTitleAt(0, jf.getSelectedFile().getName());
							tp.setName(jf.getSelectedFile().getName());
						}
							
						else
						{
							tp.setTitleAt(tp.getSelectedIndex(), jf.getSelectedFile().getName());
							tp.setName(jf.getSelectedFile().getName());
						}
					}
					else
						JOptionPane.showMessageDialog(null, "Invalid File Extension" + "Allowed File Extensions are : .c, .cpp, .java, .txt & .doc" );
				}
				else
						JOptionPane.showMessageDialog(null, "Invalid File Extension");
			}
		
	}
	public void saveAll()
	{ 
		for(int i=0;i<=Tabcnt;i++)
		{
			
			if(txtp[i].getName().equals(""))
			{
				System.out.println("Original");
				try
				{
					File f = new File(tp.getTitleAt(i)+".txt");
					String txt = txtp[i].getText();
					FileWriter fw = new FileWriter(f);
					fw.write(txt);
					fw.close();
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				
			}
			else
			{
				try
				{
					System.out.println("Already +nt");
					System.out.println("Contents:"+txtp[i].getText());
					if(filePath[i]!=null)
					{
						System.out.println("FP NOT NULL");
						File f = new File(filePath[i]);
						String txt = txtp[i].getText();
						
						FileWriter fw1 = new FileWriter(f);
						fw1.write(txt);
						fw1.close();
					}
					else
							System.out.println("ERRRRRRROOOORRR");
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
		}
	}

		
	public void cLanguage()
	{
		
		doc = new DefaultStyledDocument(sc);
	    
		txtp[tp.getSelectedIndex()].addKeyListener(new KeyAdapter() 
		{
				
			public void keyTyped(KeyEvent ke)
			{
	/* 
	   Here openflag/roundflag/sqrflag is a flag which is changed
   	   when {/(/[ is typed. wen {/(/[ 
   	   is adhered & key is Entered den only put closing bracket    
	*/
				str1 = str1 + ke.getKeyChar();
				
				if(ke.getKeyChar()=='{')
					openFlag=true;
				
				if(ke.getKeyChar()=='(')
					roundFlag = true;
				
				if(ke.getKeyChar()=='[')
					squareFlag = true;
								
				if(ke.getKeyChar()==' '||ke.getKeyChar()=='\t'||ke.getKeyChar()=='\n')	// if u pressed space check for keywords 
				{
					System.out.println("Start ="+start);
					System.out.println("String ="+str1);
					//str1 = txtp[tp.getSelectedIndex()].getText();
					check4C(str1,start);
					str1="";
					start=txtp[tp.getSelectedIndex()].getCaretPosition();
				}
			}
			
			public void keyReleased(KeyEvent arg0) 
			{
				try
				{
					Document d = txtp[tp.getSelectedIndex()].getDocument();
					if(openFlag)
					{
						d.insertString(txtp[tp.getSelectedIndex()].getCaretPosition(), "\n}", null);
						openFlag = false;
					}
					if(roundFlag)
					{
						d.insertString(txtp[tp.getSelectedIndex()].getCaretPosition(), ")", null);
						roundFlag = false;
					}
					if(squareFlag)
					{
						d.insertString(txtp[tp.getSelectedIndex()].getCaretPosition(), "]", null);
						squareFlag = false;
					}
					
				}
				catch(BadLocationException e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public void javaLanguage()
	{
		System.out.println("Java");
		doc = new DefaultStyledDocument(sc);
	    
		txtp[tp.getSelectedIndex()].addKeyListener(new KeyAdapter() 
		{
				
			public void keyTyped(KeyEvent ke)
			{
				str1 = str1 + ke.getKeyChar();
	/* 
	   Here openflag/roundflag/sqrflag is a flag which is changed
   	   when {/(/[ is typed. wen {/(/[ 
   	   is adhered & key is Entered den only put closing bracket    
	*/
				if(ke.getKeyChar()=='{')
					openFlag=true;
				
				if(ke.getKeyChar()=='(')
					roundFlag = true;
				
				if(ke.getKeyChar()=='[')
					squareFlag = true;
				
				if(ke.getKeyChar()==' '||ke.getKeyChar()=='\n'||ke.getKeyChar()=='\t')	
					// if u pressed space check for keywords 
				{
					System.out.println("Start ="+start);
					System.out.println("String ="+str1);
					check4Java(str1,start);
					str1="";
					start=txtp[tp.getSelectedIndex()].getCaretPosition();
				}
			}
			
			public void keyReleased(KeyEvent arg0) 
			{
				try
				{
					Document d = txtp[tp.getSelectedIndex()].getDocument();
					if(openFlag)
					{
						d.insertString(txtp[tp.getSelectedIndex()].getCaretPosition(), "\n}", null);
						openFlag = false;
					}
					if(roundFlag)
					{
						d.insertString(txtp[tp.getSelectedIndex()].getCaretPosition(), ")", null);
						roundFlag = false;
					}
					if(squareFlag)
					{
						d.insertString(txtp[tp.getSelectedIndex()].getCaretPosition(), "]", null);
						squareFlag = false;
					}
				}
				catch(BadLocationException e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	//Replace
	public void ReplaceClass()
	{
		replacef = new JFrame("Find & Replace");
		replacef.setBounds(0, 0, 500, 250);
		replacef.setLayout(null);
		
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
				findStr=findTF.getText();
				System.out.println("String:"+findStr);
				doc = (DefaultStyledDocument) txtp[tp.getSelectedIndex()].getDocument();
					
				int offset = 0;				
					
				Style mystyle1 = sc.addStyle("styleBg", null);
				mystyle1.addAttribute(StyleConstants.Foreground, Color.black);
				String d=txtp[tp.getSelectedIndex()].getText();
				doc.setCharacterAttributes(0, d.length(), mystyle1, true);
					
				int localOffset=0;
				offset = 0;
				String data=txtp[tp.getSelectedIndex()].getText().replaceAll("\r", "");
				while(data.contains(findStr))
				{
					System.out.println("data:"+data);
					System.out.println("change the color");
					localOffset=data.indexOf(findStr);
				//setting the style (font,color,size,bold/italic etc...)
				 	mystyle = sc.addStyle("sytle1", null);
				 	mystyle.addAttribute(StyleConstants.Background, Color.YELLOW);
				 	doc.setCharacterAttributes(offset+ localOffset, findStr.length(), mystyle, true);
				
				 	offset += findStr.length() + localOffset+1;
				
				 	if(data.length() <= localOffset+findStr.length()+1)
				 		break;
				 	data = data.substring(localOffset+findStr.length()+1);
				
				}//while
			}
		});
		replaceB = new JButton("Replace");
		replaceB.setBounds(120, 150, 100, 30);
		replaceB.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) 
			{
				findStr = findTF.getText();
				replaceStr = replaceTF.getText();
			
				System.out.println("FIND = "+findStr);
				System.out.println("Replace = "+replaceStr);
				int fr;
				String newdata = null,txt;
				txt = txtp[tp.getSelectedIndex()].getText();
				
				if((fr=txt.indexOf(findStr))==0)
					newdata =txt.replaceFirst(findStr, replaceStr);
				
				 txtp[tp.getSelectedIndex()].setText(newdata);
				 replacef.dispose();
				
			}
		});
		replaceAllB = new JButton("Replace All");
		replaceAllB.setBounds(230, 150, 120, 30);
		replaceAllB.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) 
			{
				findStr = findTF.getText();
				replaceStr = replaceTF.getText();
				String txt = txtp[tp.getSelectedIndex()].getText();
				String data = txt.replace(findStr, replaceStr);
				txtp[tp.getSelectedIndex()].setText(data);
				replacef.dispose();
			}
		});
		
		replacef.add(find);
		replacef.add(findTF);
		replacef.add(replace);
		replacef.add(replaceTF);
		replacef.add(findB);
		replacef.add(replaceB);
		replacef.add(replaceAllB);
		
		replacef.setVisible(true);
		
	}

	// Find
	public void FindClass()
	{
		findFrame = new JFrame("Find");
		findFrame.setBounds(0, 0, 400, 200);
		findFrame.setLayout(null);
			
		findL = new JLabel("Enter the String to Find:");
		findL.setBounds(10, 20, 180, 20);
			
		find_TF = new JTextField(50);
		find_TF.setBounds(200, 20, 150, 20);
			
		findBt = new JButton("Find");
		findBt.setBounds(60, 100, 70, 30);
		findBt.addActionListener(new ActionListener() {
				
		public void actionPerformed(ActionEvent e) 
		{
			String findStr1 = find_TF.getText();
			
			System.out.println("String:"+findStr1);
			doc = (DefaultStyledDocument) txtp[tp.getSelectedIndex()].getDocument();
				
			int offset = 0;				
				
			Style mystyle1 = sc.addStyle("styleBg", null);
			mystyle1.addAttribute(StyleConstants.Foreground, Color.black);
			String d=txtp[tp.getSelectedIndex()].getText();
			doc.setCharacterAttributes(0, d.length(), mystyle1, true);
				
			int localOffset=0;
			offset = 0;
			String data=txtp[tp.getSelectedIndex()].getText().replaceAll("\r", "");
			while(data.contains(findStr1))
			{
				System.out.println("data:"+data);
				System.out.println("change the color");
				localOffset=data.indexOf(findStr1);
			//setting the style (font,color,size,bold/italic etc...)
			 	mystyle = sc.addStyle("sytle1", null);
			 	mystyle.addAttribute(StyleConstants.Background, Color.YELLOW);
			 	doc.setCharacterAttributes(offset+ localOffset, findStr1.length(), mystyle, true);
			
			 	offset += findStr1.length() + localOffset+1;
			
			 	if(data.length() <= localOffset+findStr1.length()+1)
			 		break;
			 	data = data.substring(localOffset+findStr1.length()+1);
			
			}//while
				
				findFrame.dispose();
		}
		});
			
				
			
		cancelB = new JButton("Cancel");
		cancelB.setBounds(180, 100, 100, 30);
		cancelB.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				findFrame.dispose();
			}
		});
			
		findFrame.add(findL);
		findFrame.add(find_TF);
		findFrame.add(findBt);
		findFrame.add(cancelB);
			
		findFrame.setVisible(true);
		findFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	void newF()
	{
		Tabcnt++;
		String Name = "Tab "+Tabcnt;
		tp.add(Name,Main_Screen.createTab(Tabcnt));
		tp.setTitleAt(Tabcnt, Name);
		if(Tabcnt!=0)
			tp.setSelectedIndex(Tabcnt);
		else
			tp.setSelectedIndex(0);
		
		tp.setName("");
		
		txtp[tp.getSelectedIndex()].setName("");
		
		txtp[tp.getSelectedIndex()].getDocument().addUndoableEditListener(new UndoableEditListener()
		{
			public void undoableEditHappened(UndoableEditEvent e) 
			{
				try
				{
					manager.addEdit(e.getEdit());
				}
				catch(CannotUndoException ae)
				{
					System.out.println("EDIT FAIL!!!!");
				}
			}
		});
	}
	
	void open()
	{
		JFileChooser jf = new JFileChooser();
		int res = jf.showOpenDialog(null);
		
		if(res == 0)
		{
			System.out.println("Open");
			
			File f = jf.getSelectedFile();
			String name = f.getName();
			
			if(!name.endsWith(".."))
			{
				if(((name.endsWith(".c"))||(name.endsWith(".cpp"))||(name.endsWith(".java"))||(name.endsWith(".txt"))||(name.endsWith(".doc"))))
				{
					String path = f.getAbsolutePath();
					System.out.println("Path - "+path);
					if(f.isFile())
					{
						tp.setTitleAt(tp.getSelectedIndex(), f.getName());
						System.out.println("here");	
						System.out.println("Path - "+path);
							
						try
						{
								
							FileReader fr = new FileReader(f);
							BufferedReader br = new BufferedReader(fr);
							String txt,data="";
							int pos = tp.getSelectedIndex();
							while((txt = br.readLine())!=null)
							{
								data=data+txt+"\n";
								txtp[pos].setText(data);
							}
							br.close();
							
							txtp[pos].getDocument().addUndoableEditListener(new UndoableEditListener() {
								
								
								public void undoableEditHappened(UndoableEditEvent arg0) {
								manager.addEdit(arg0.getEdit());								}
							});
						}
						catch(Exception e1)
						{
							e1.printStackTrace();
						}
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Invalid File Extension!!!" + "Allowed File Extensions are : .c, .cpp, .java, .txt & .doc" );
			}
			else
				JOptionPane.showMessageDialog(null, "Invalid File Extension!!!" );
		}
		
		
	}
	
	void closeAll()
	{
		int t_no = Tabcnt;
		for(int i=0;i<=t_no;i++)
		{
/* While removing the tabs after deleting a tab d tab no. gets decremented so whichever tab 
v close always its d 1st tab so index no = 0 */
			filePath[tp.getSelectedIndex()] = null;
			tp.removeTabAt(0);
			Tabcnt--;
		}
	
	}
	void close()
	{
		try
		{
			filePath[tp.getSelectedIndex()] = null;
			tp.removeTabAt(tp.getSelectedIndex());
			Tabcnt--;
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(null, "All tabs have been closed, so can't close Anymore!!!");
		}
	}
	
	void Fonts()
	{
		fontF = new JFrame("Font Selector");
		fontF.setLayout(null);
		fontF.setSize(500, 300);
			
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Font[] f_names = ge.getAllFonts();
		f_nm = new List();
		f_nm.setBounds(10, 30, 150, 100);
		f_nm.select(0);
		for(i=0;i<f_names.length;i++)
			f_nm.add(f_names[i].getFontName());
		fnmL = new JLabel("Font Name");
		fnmL.setBounds(50, 140, 100, 10);
		
		String[] f_size = {"8","10","12","14","16","18","20","22","24","26","28","30"};
		f_sz =new List();
		f_sz.setBounds(170, 30 , 150, 100);
		for(i=0;i<f_size.length;i++)
			f_sz.add(f_size[i]);
		fszL = new JLabel("Font Size");
		fszL.setBounds(210, 140, 100, 10);
		f_sz.select(0);
		
		String[] styles = {"Regular","Italics","Bold"};
		f_style = new List();
		f_style.setBounds(330, 30, 100, 100);
		for(i=0;i<styles.length;i++)
			f_style.add(styles[i]);
		f_style.select(0);
		fstyL = new JLabel("Font Style");
		fstyL.setBounds(350, 140, 100, 20);
		
		
		int res;
		okB = new JButton("OK");
		okB.setBounds(50, 200, 100, 30);
		okB.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{
					if(f_nm.getSelectedItem().matches(f_nm.getItem(0)))
						sel_op[0] = f_nm.getItem(0);
					else
						sel_op[0] = f_nm.getSelectedItem();
					
					if(f_sz.getSelectedItem().matches(f_sz.getItem(0)))
						sel_op[1] = f_sz.getItem(0);
					else
						sel_op[1] = f_sz.getSelectedItem();
					
		
					if(f_style.getSelectedItem().matches(f_style.getItem(0)))
						sel_op[2] = f_style.getItem(0);
					else
						sel_op[2] = f_style.getSelectedItem();
					
					for(int i=0;i<3;i++)
						System.out.println("data"+sel_op[i]);
					mystyle = sc.addStyle("style1", null);
					mystyle.addAttribute(StyleConstants.FontFamily, sel_op[0]);
					mystyle.addAttribute(StyleConstants.FontSize,Integer.parseInt(sel_op[1]));
					if(sel_op[2].matches("Bold"))
						mystyle.addAttribute(StyleConstants.Bold, new Boolean(true));
					else
					if(sel_op[2].matches("Italics"))
						mystyle.addAttribute(StyleConstants.Italic, new Boolean(true));
					
					doc = (DefaultStyledDocument)txtp[tp.getSelectedIndex()].getDocument();
					doc.setCharacterAttributes(0,doc.getLength(), mystyle, true);
				
					fontF.dispose();
				}
				catch(Exception e)
				{
					System.out.println("ERRRORRRRRRRR");
				}
			}
		});
		
		cancelBut = new JButton("Cancel");
		cancelBut.setBounds(170, 200, 100, 30);
		cancelBut.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent arg0) 
			{
				fontF.dispose();
			}
		});
		
		
		fontF.add(f_nm);
		fontF.add(fnmL);
		fontF.add(f_sz);
		fontF.add(fszL);
		fontF.add(f_style);
		fontF.add(fstyL);
		fontF.add(okB);
		fontF.add(cancelBut);
		fontF.setVisible(true);
		fontF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
		
	}
	
	void themes()
	{
		themesF = new JFrame("Themes");
		themesF.setSize(300,300);
		themesF.setLayout(null);
		
		motifR = new JRadioButton();
		motifR.setBounds(50, 50, 40, 20);
		motifL = new JLabel("Motif");
		motifL.setBounds(40, 80, 40, 20);
		
		nimbusR = new JRadioButton();
		nimbusR.setBounds(150, 50, 40, 20);
		nimbusL = new JLabel("Nimbus");
		nimbusL.setBounds(140, 80, 60, 20);
		
		metalR = new JRadioButton();
		metalR.setBounds(100, 120, 40, 20);
		metalL = new JLabel("Metal");
		metalL.setBounds(80, 140, 70, 20);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(metalR);
		bg.add(motifR);
		bg.add(nimbusR);
		
		applyB = new JButton("Apply Themes");
		applyB.setBounds(20, 200, 120, 30);
		applyB.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent arg0) 
			{

				try
				{
					if(motifR.isSelected())
					{
						System.out.println("Motif");
						UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
					}
					if(nimbusR.isSelected())
					{
						System.out.println("Nimbus");
						UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					}
					if(metalR.isSelected())
					{
						System.out.println("Metal");
						UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
					}
					SwingUtilities.updateComponentTreeUI(f);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
			}
		});
		
		canB = new JButton("Cancel");
		canB.setBounds(160, 200, 90, 30);
		canB.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent arg0) {
			themesF.dispose();
				
			}
		});
		
		themesF.add(motifR);
		themesF.add(motifL);
		themesF.add(nimbusR);
		themesF.add(nimbusL);
		themesF.add(metalR);
		themesF.add(metalL);
		
		
		themesF.add(applyB);
		themesF.add(canB);
		
		themesF.setVisible(true);
		themesF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		 
	}
	
	
	void helpContents()
	{
		JFrame helpF;
		JTextArea ta;
		
		helpF = new JFrame("Help Contents");
		helpF.setSize(555, 250);
		ta = new JTextArea();
		ta.setBounds(0, 0, 555, 250);
		ta.setEditable(false);
		
		String s = 
		"File Menu can be used for Basic File Operations like opening a file," +
		"\nopening a new tab,saving a file,closing the tab etc.\n" +
		"\nEdit Menu Contains basic editing operations like cut,copy,paste,undo,redo etc.\n"+
		"\nFormat Menu Contains Font Selector Dialog.\n"+
		"\nView Menu Contains Themes Selector Dialog.\n"+
		"\nLanguage Menu Contains Programming Language Support Like - Auto Indentation,Paranthesis " +
		"\nMatching and Keyword colouring for C,C++ & Java Language!";
		ta.setText(s);
		helpF.add(ta);
		helpF.setVisible(true);
		helpF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	void autoSave()
	{
		long time=0;

		while(true)
		{	
			try
			{
				Thread.sleep(60000);
				time += 60000;
				saveAll();
				msg2.setVisible(false);
				msg1.setVisible(true);
			
				if(time == 120000)
				{	
					msg1.setVisible(false);
					msg2.setVisible(true);
					time = 0;
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	
	void autoIndentation()
	{
		int t_cnt = 0,l_cnt = 0;
				
		String txt = txtp[tp.getSelectedIndex()].getText();
		char t;
		
		//txtp[tp.getSelectedIndex()].getCaretPosition();
	
		for(int i=0;i<txt.length();i++)
		{
			t = txt.charAt(i);
			
			if(t == '\t')
				t_cnt++;
			if(t == '\n')
				l_cnt++;
		}
		
		System.out.println("Tabs = "+t_cnt);
		System.out.println("Lines ="+l_cnt);
					
	}
	
	void About()
	{
		JFrame AboutF = new JFrame();
		JLabel Prjct_Nm,Prjct_Ownrs;
		AboutF.setSize(370,150);
		AboutF.setTitle("About NotePad");		
			
		Prjct_Nm = new JLabel("Advanced Notepad Created By:");
		Prjct_Nm.setBounds(10, 10, 250, 20);
		Prjct_Ownrs = new JLabel(" Rohan Viraj Deshpande ");
		Prjct_Ownrs.setBounds(50, 10, 350, 20);
			
		AboutF.add(Prjct_Nm);
		AboutF.add(Prjct_Ownrs);
		AboutF.setVisible(true);
		AboutF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	
	void check4C(String str,int start)
	{
		System.out.println("STR="+str);
		System.out.println("line"+str.length());
		doc = (DefaultStyledDocument)txtp[tp.getSelectedIndex()].getDocument();

		Style styleblack=doc.addStyle("black", null);
		styleblack.addAttribute(StyleConstants.Foreground, Color.BLACK);
		doc.setCharacterAttributes(start, str.length(), styleblack,true);
		
		if(isCKeyword(str))
		{
			System.out.println("AAAAAAAAAAAAAAAAAAAAAa");
				try
				{
					doc = (DefaultStyledDocument)txtp[tp.getSelectedIndex()].getDocument();
					Style styleKey=doc.addStyle("Keyword", null);
					styleKey.addAttribute(StyleConstants.Italic,new Boolean(true));
					styleKey.addAttribute(StyleConstants.Foreground,Color.BLUE);
					doc.setCharacterAttributes(start, str.length(), styleKey,true);
				}
				catch(Exception e)
				{
					System.out.println("EEEEEEEEEEEEEERRRRRRRRRRRRRRRRRRR");
				}
			}
	}
	
	boolean isCKeyword(String keyword)
	{
		keyword=keyword.trim();

		if(keyword.equals("auto") || keyword.equals("break") || keyword.equals("case") || keyword.equals("char") || keyword.equals("constant") || keyword.equals("continue") || keyword.equals("default") || keyword.equals("do")
		|| keyword.equals("double") || keyword.equals("else") || keyword.equals("enum") || keyword.equals("extern") || keyword.equals("short") || keyword.equals("for") || keyword.equals("goto") || keyword.equals("if")
		|| keyword.equals("int") || keyword.equals("long") || keyword.equals("register") || keyword.equals("return") || keyword.equals("signed") || keyword.equals("sizeof") || keyword.equals("static") || keyword.equals("struct")
		|| keyword.equals("switch") || keyword.equals("typedef") || keyword.equals("union") || keyword.equals("unsigned") || keyword.equals("void") || keyword.equals("volatile") || keyword.equals("while") || keyword.equals("class")  
		|| keyword.equals("bool") || keyword.equals("export") || keyword.equals("true") || keyword.equals("false") || keyword.equals("friend") || keyword.equals("new") || keyword.equals("namespace") || keyword.equals("and")
		|| keyword.equals("or") || keyword.equals("public") || keyword.equals("private") || keyword.equals("protected") || keyword.equals("template") || keyword.equals("try") || keyword.equals("throw") || keyword.equals("this")
		|| keyword.equals("virtual") || keyword.equals("catch")|| keyword.equals("volatile") || keyword.equals("xor"))
		{
			return true;
		}
		return false;
	}

	void check4Java(String str,int start)
	{
		System.out.println("STR="+str);
		System.out.println("line"+str.length());
		doc = (DefaultStyledDocument)txtp[tp.getSelectedIndex()].getDocument();

		Style styleblack=doc.addStyle("black", null);
		styleblack.addAttribute(StyleConstants.Foreground, Color.BLACK);
		doc.setCharacterAttributes(start, str.length(), styleblack,true);
		if(isJavaKeyword(str))
		{
			System.out.println("AAAAAAAAAAAAAAAAAAAAAa");
				try
				{
					doc = (DefaultStyledDocument)txtp[tp.getSelectedIndex()].getDocument();
					Style styleKey=doc.addStyle("Keyword", null);
					styleKey.addAttribute(StyleConstants.Italic,new Boolean(true));
					styleKey.addAttribute(StyleConstants.Foreground,Color.BLUE);
					doc.setCharacterAttributes(start, str.length(), styleKey,true);
				}
				catch(Exception e)
				{
					System.out.println("EEEEEEEEEEEEEERRRRRRRRRRRRRRRRRRR");
				}
			}
	}
	
	boolean isJavaKeyword(String keyword)
	{
		keyword=keyword.trim();
		if(keyword.equals("abstract") || keyword.equals("continue") || keyword.equals("for") || keyword.equals("new") || keyword.equals("switch") || keyword.equals("assert") || keyword.equals("default") || keyword.equals("goto")
		|| keyword.equals("package") || keyword.equals("synchronized") || keyword.equals("boolean") || keyword.equals("do") || keyword.equals("if") || keyword.equals("private") || keyword.equals("this") || keyword.equals("break")
		|| keyword.equals("double") || keyword.equals("implements") || keyword.equals("protected") || keyword.equals("throws") || keyword.equals("throw") || keyword.equals("byte") || keyword.equals("else") || keyword.equals("import")
		|| keyword.equals("public") || keyword.equals("case") || keyword.equals("enum") || keyword.equals("instanceof") || keyword.equals("return") || keyword.equals("transient") || keyword.equals("catch") || keyword.equals("extends")  
		|| keyword.equals("int") || keyword.equals("short") || keyword.equals("try") || keyword.equals("char") || keyword.equals("final") || keyword.equals("interface") || keyword.equals("static") || keyword.equals("void")
		|| keyword.equals("class") || keyword.equals("finally") || keyword.equals("long") || keyword.equals("strictfp") || keyword.equals("volatile") || keyword.equals("while") || keyword.equals("super") || keyword.equals("native")
		|| keyword.equals("float") || keyword.equals("const") )
		{
			return true;
		}
		return false;
	}

	
	String path()
	{
		return "D:\\MCA\\SY\\Sem4\\AdvancedJava\\AdvancedNotepad\\Documentation&Code\\PRJCTTTTTT\\Images\\";
	}
	
	public static void main(String[] args) 
	{
		Main_Screen ms = new Main_Screen();
		ms.autoSave();
	}

	
}
