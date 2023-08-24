package GUI;

import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
//import java.awt.event.*;

class MainScreen 
{
	static JPanel p;
	static int Tabcnt;
	JPanel above,editingArea;
	JFrame f;
	static JScrollPane sp;
	JTabbedPane tp;
	static JTextPane txtp;
	JMenuBar mb;
	JMenu fileMenu,editMenu,viewMenu,formatMenu,languageMenu,themesMenu,helpMenu;
	JMenuItem fileNew,fileOpen,fileSave,fileSaveAll,fileClose,fileCloseAll,filePageSetup,filePrint,fileExit,editUndo,editRedo,editCut,editCopy,editPaste,editSelectAll,editFind,editReplace,formatFonts,viewThemes,helpAbout,helpContents;
	JToolBar tb;
	JButton newIcon,openIcon,saveIcon,saveAllIcon,closeIcon,closeAllIcon,cutIcon,copyIcon,pasteIcon,undoIcon,redoIcon,spellCheckIcon,findIcon,replaceIcon,boldIcon,italicsIcon;
	JComboBox fontName,fontSize;
	ImageIcon newImg,openImg,saveImg,saveAllImg,closeImg,closeAllImg,cutImg,copyImg,pasteImg,undoImg,redoImg,spellCheckImg,findImg,replaceImg,boldImg,italicsImg;
		
	MainScreen()
	{
			f = new JFrame("Advanced Notepad");
			f.setLayout(null);
			
			f.setSize(750,750);
			
			//TOP PANEL:
			above = new JPanel();
			//above.setVisible(true);
			above.setLayout(null);
			above.setBounds(0, 0, 740, 80);
			// Menu Bar:
			
			mb = new JMenuBar();
			mb.setBounds(0, 0, 740, 30);
			
			fileMenu = new JMenu("File");
			fileMenu.setBounds(0, 10, 30, 20);
			fileMenu.setToolTipText("Contains the Basic File Functions");
			fileMenu.setMnemonic('F');
			editMenu = new JMenu("Edit");
			editMenu.setBounds(35, 10, 30, 20);
			editMenu.setMnemonic('E');
			editMenu.setToolTipText("Contains the basic Editing Functions");
			viewMenu = new JMenu("View");
			viewMenu.setBounds(70, 10, 30, 20);
			viewMenu.setMnemonic('V');
			viewMenu.setToolTipText("Contains View Settings");
			formatMenu = new JMenu("Format");
			formatMenu.setBounds(105,10, 30, 20);
			formatMenu.setMnemonic('o');
			formatMenu.setToolTipText("Contains the Formatting Options");
			languageMenu = new JMenu("Language");
			languageMenu.setBounds(140, 10, 30, 20);
			languageMenu.setMnemonic('L');
			languageMenu.setToolTipText("Contains options concerning Languages");
		/*	themesMenu = new JMenu("Themes");
			themesMenu.setBounds(175, 10, 30, 20);
			themesMenu.setMnemonic('T');*/
			
			helpMenu = new JMenu("Help");
			helpMenu.setBounds(175, 10, 30, 20);
			helpMenu.setMnemonic('H');
			helpMenu.setToolTipText("HELP!!!");
			// File Menu:
			
			fileNew = new JMenuItem("New");
			fileNew.setMnemonic('N');
			fileOpen = new JMenuItem("Open");
			fileOpen.setMnemonic('O');
			fileSave = new JMenuItem("Save");
			fileSave.setMnemonic('S');
			fileSaveAll = new JMenuItem("Save All");
			fileSaveAll.setMnemonic('v');
			fileClose = new JMenuItem("Close");
			fileClose.setMnemonic('C');
			fileCloseAll = new JMenuItem("Close All");
			fileCloseAll.setMnemonic('l');
			filePageSetup = new JMenuItem("Page Setup");
			filePageSetup.setMnemonic('g');
			filePrint = new JMenuItem("Print");
			filePrint.setMnemonic('P');
			fileExit = new JMenuItem("Exit");
			fileExit.setMnemonic('E');
			
			fileMenu.add(fileNew);
			fileMenu.add(fileOpen);
			fileMenu.add(fileSave);
			fileMenu.add(fileSaveAll);
			fileMenu.add(fileClose);
			fileMenu.add(fileCloseAll);
			fileMenu.add(filePrint);
			fileMenu.add(filePageSetup);
			fileMenu.add(fileExit);
			
			// Edit - Menu
			
			editUndo = new JMenuItem("Undo");
			editRedo =new JMenuItem("Redo");
			editCut = new JMenuItem("Cut");
			editCopy =new JMenuItem("Copy");
			editPaste = new JMenuItem("Paste");
			editSelectAll = new JMenuItem("Select All");
			editFind = new JMenuItem("Find");
			editReplace = new JMenuItem("Replace");
			
			editMenu.add(editUndo);
			editMenu.add(editRedo);
			editMenu.add(editCut);
			editMenu.add(editCopy);
			editMenu.add(editPaste);
			editMenu.add(editFind);
			editMenu.add(editReplace);
			editMenu.add(editSelectAll);
			
			//Format Menu
			formatFonts = new JMenuItem("Fonts");
			formatFonts.setToolTipText("Select Desired Fonts");
			formatFonts.setMnemonic('F');
			formatMenu.add(formatFonts);
			
			// View Menu 
			
			viewThemes = new JMenuItem("Themes");
			viewThemes.setToolTipText("Select Desired Themes");
			viewThemes.setMnemonic('T');
			viewMenu.add(viewThemes);
			
			// Help Menu
			
			helpAbout = new JMenuItem("About Notepad");
			helpAbout.setToolTipText("Info. about Advanced Notepad");
			helpAbout.setMnemonic('A');
			helpContents = new JMenuItem("Help Contents");
			helpContents.setToolTipText("Complete Help Contents");
			helpContents.setMnemonic('C');
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
			tb.setBounds(0, 30, 740, 50);
			
			newImg = new ImageIcon(Path.imagePath+"NewDoc.PNG");
			newIcon = new JButton(newImg);
			newIcon.setBounds(20, 55, 20, 20);
			
			
			openImg = new ImageIcon(Path.imagePath+"OpenDoc.PNG");
			openIcon = new JButton(openImg);
			openIcon.setBounds(40, 55, 20, 20);
			openIcon.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) 
				{
					JFileChooser jf = new JFileChooser(new File("/home/prasad/workspace/Advanced_Notepad/Advanced_Notepad/"));
					int res = jf.showOpenDialog(null);
					
					if(res == 0)
					{
						System.out.println("Open");
						
						File f = new File(jf.getSelectedFile().getName());
						String path = f.getAbsolutePath();
						System.out.println("Path - "+path);
						if(f.isFile())
						{
							System.out.println("here");	
							System.out.println("Path - "+path);
							
							try
							{
								
								FileReader fr = new FileReader(f.getName());
								BufferedReader br = new BufferedReader(fr);
								String txt,data="";
							
								while((txt = br.readLine())!=null)
								{
									data=data+txt+"\n";
									System.out.println(txt);
									txtp.setText(data);
								
								}
							
							}
							catch(Exception e1)
							{
								e1.printStackTrace();
							}
						}
					}
				}
			}
			);
			
			saveImg = new ImageIcon(Path.imagePath+"Save.PNG");
			saveIcon = new JButton(saveImg);
			//saveIcon = new JButton("S");
			saveIcon.setBounds(60, 55, 20, 20);
			
			saveAllImg = new ImageIcon(Path.imagePath+"SaveAll.PNG");
			saveAllIcon = new JButton(saveAllImg);
			//saveAllIcon = new JButton("SA");
			saveAllIcon.setBounds(80, 55, 20, 20);
			
			closeImg = new ImageIcon(Path.imagePath+"CloseCurr.PNG");
			closeIcon = new JButton(closeImg);
		//	closeIcon = new JButton("C");
			closeIcon.setBounds(100, 55, 20, 20);
			
			closeAllImg = new ImageIcon(Path.imagePath+"CloseAll.PNG");
			closeAllIcon = new JButton(closeAllImg);
		//	closeAllIcon = new JButton("CA");
			closeAllIcon.setBounds(120, 55, 20, 20);
			
			cutImg = new ImageIcon(Path.imagePath+"Cut.PNG");
			cutIcon = new JButton(cutImg);
			//cutIcon = new JButton("CU");
			cutIcon.setBounds(140, 55, 20, 20);
			
			copyImg = new ImageIcon(Path.imagePath+"Copy.PNG");
			copyIcon = new JButton(copyImg);
			//copyIcon = new JButton("CO");
			copyIcon.setBounds(160, 55, 20, 20);
			
			pasteImg = new ImageIcon(Path.imagePath+"Paste.PNG");
			pasteIcon = new JButton(pasteImg);
			//pasteIcon = new JButton("P");
			pasteIcon.setBounds(180, 55, 20, 20);
			
			undoImg = new ImageIcon(Path.imagePath+"Undo.PNG");
			undoIcon = new JButton(undoImg);
			//undoIcon = new JButton("U");
			undoIcon.setBounds(200, 55, 20, 20);
			
			redoImg = new ImageIcon(Path.imagePath+"Redo.PNG");
			redoIcon = new JButton(redoImg);
			//redoIcon = new JButton("R");
			redoIcon.setBounds(220, 55, 20, 20);
			
			findImg = new ImageIcon(Path.imagePath+"Find.PNG");
			findIcon = new JButton(findImg);
			//findIcon = new JButton("F");
			findIcon.setBounds(240, 55, 20, 20);
			
			replaceImg = new ImageIcon(Path.imagePath+"Replace.PNG");
			replaceIcon = new JButton(replaceImg);
			//replaceIcon = new JButton("R");
			replaceIcon.setBounds(260, 55, 20, 20);
			
			spellCheckImg = new ImageIcon(Path.imagePath+"SpellCheck.PNG");
			spellCheckIcon = new JButton(spellCheckImg);
			//spellCheckIcon = new JButton("SC");
			spellCheckIcon.setBounds(280, 55, 20, 20);
		
			boldImg = new ImageIcon(Path.imagePath+"Bold.gif");
			boldIcon = new JButton(boldImg);
		//	boldIcon = new JButton("B");
			boldIcon.setBounds(300, 55, 10, 30);
			
			italicsImg = new ImageIcon(Path.imagePath+"Italics.gif");
			italicsIcon = new JButton(italicsImg);
			//italicsIcon = new JButton("I");
			italicsIcon.setBounds(320, 55, 10, 30);
			
			String[] fontNames = {"SanSerif","Arial","Times New Roman","Comic Sans MS"};
			String[] fontSizes = {"6","8","10","12"};
			
			
			fontName = new JComboBox(fontNames);
			fontName.setBounds(340, 55, 10, 10);
			fontSize = new JComboBox(fontSizes);
			fontSize.setBounds(420, 55, 10, 10);
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
			tb.add(findIcon);
			tb.add(replaceIcon);
			tb.add(spellCheckIcon);
			
			
			
			above.add(mb);
			above.add(tb);
		
			f.add(above);
			tp = new JTabbedPane();
			tp.setBounds(0, 80, 740, 740);
		
			Tabcnt++;
			String Name = "Tab "+Tabcnt;
			tp.add(Name,MainScreen.createTab());
			
			newIcon.addActionListener(new ActionListener() {
				
				
				public void actionPerformed(ActionEvent e) 
				{
					Tabcnt++;
					String Name = "Tab "+Tabcnt;
					tp.add(Name,MainScreen.createTab());
					
				}
			});
			
			f.add(tp);
			f.setVisible(true);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static JPanel createTab()
	{
		p = new JPanel();
		p.setLayout(null);
		txtp = new JTextPane();
		sp = new JScrollPane(txtp,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp.setBounds(0, 0, 740, 580);
		p.add(sp);
		return p;
	}
	public static void main(String[] args) {
		new MainScreen();
		}

}