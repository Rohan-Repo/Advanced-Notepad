package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.print.*;

public class Ntpd 
{
	int panecnt=1;
	JFrame f;
	JScrollPane sp;
	JTabbedPane mainpane = new JTabbedPane();
	JScrollPane []pane=new JScrollPane[55];
	JMenuBar mainMenuBar;
	JMenu fileMenu,editMenu,viewMenu,formatMenu,languageMenu,themeMenu,helpMenu;
	JToolBar t1;
	JButton newPage,open,save,saveall,pagesetup,print,redo,replace,paste,exit,c16,close,closeall,calculator;

		public void add_page()
		{

				JTextArea text1=new JTextArea();
				pane[panecnt]=new JScrollPane(text1);
				mainpane.addTab("Tab "+panecnt,pane[panecnt]);
				f.add(mainpane);
				panecnt++;
		}

		void about_notepad()
		{
				JFrame help = new JFrame("A bout Notepad");
				help.setSize(350,350);
				help.setVisible(true);
		}

	//dialog box for confirmation to saving file.
	void DialogBoxForSaveFile()
		{
			 JPanel message = new JPanel();
			 JLabel l1=new JLabel("DO you want to save the File ");
			 message.add(l1);
			int result = JOptionPane.showConfirmDialog(f, message, "Save", JOptionPane.YES_NO_CANCEL_OPTION);
			if(result == JOptionPane.YES_OPTION)        // selecting yes option from dialog box
				{
					save();
				}
			else if(result == JOptionPane.NO_OPTION)			// selecting no option from dialog box
			    {
					System.exit(0);
				}
		}

//file dialog box for saving the file.
void save()
{
				JFileChooser jc=new JFileChooser();
				jc.showSaveDialog(f);
				File f1 =jc.getSelectedFile();
				System.out.println(f1.getName());

}


//file dialog box for opening file
		void open()
			{
				JFileChooser jc=new JFileChooser();
				jc.showOpenDialog(f);
				File f1 =jc.getSelectedFile();
				System.out.println(f1.getName());
		    }

	Ntpd()
	{

		f=new JFrame("Adavanced Notepad");


		mainMenuBar=new JMenuBar();

		fileMenu = new JMenu("File");


		JMenuItem newItem =	new JMenuItem("New");
		newItem.setMnemonic('N');
		fileMenu.add(newItem);

		newItem.addActionListener(new ActionListener()
													{
														public void actionPerformed(ActionEvent event)
														{
															add_page();
														}
													}
										);


		JMenuItem openItem=new JMenuItem("Open");
		openItem.setMnemonic('O');
		fileMenu.add(openItem);

		openItem.addActionListener(new ActionListener()
																	{
																		public void actionPerformed(ActionEvent event)
																		{
																				open();
																		}
																	}
												);



	//	fileMenu.add(new JMenuItem("Save"));

		JMenuItem saveItem=new JMenuItem("Save");
		saveItem.setMnemonic('V');
		fileMenu.add(saveItem);
		///////

		saveItem.addActionListener(new ActionListener()
															{
																public void actionPerformed(ActionEvent event)
																{
																		save();
																}
															}
										);


		////////////

	//	fileMenu.add(new JMenuItem("Save As"));

		JMenuItem saveAsItem=new JMenuItem("Save AS");
		saveAsItem.setMnemonic('S');
		fileMenu.add(saveAsItem);



	//	fileMenu.add(new JMenuItem("Save All"));

		JMenuItem saveAllItem=new JMenuItem("Save All");
		saveAllItem.setMnemonic('L');
		fileMenu.add(saveAllItem);

	//	fileMenu.add(new JMenuItem("Close"));

		JMenuItem closeItem=new JMenuItem("Close");
		closeItem.setMnemonic('C');
		fileMenu.add(closeItem);
				closeItem.addActionListener(new ActionListener()
																		{
																			public void actionPerformed(ActionEvent event)
																			{
																				DialogBoxForSaveFile();
																			}
																		}
													);


	//	fileMenu.add(new JMenuItem("Close All"));

		JMenuItem closeAllItem=new JMenuItem("Close All");
		closeAllItem.setMnemonic('Z');
		fileMenu.add(closeAllItem);

	//	fileMenu.add(new JMenuItem("PageSetup"));

		JMenuItem pageSetupItem=new JMenuItem("PageSetup");
		pageSetupItem.setMnemonic('K');
		fileMenu.add(pageSetupItem);


	//	fileMenu.add(new JMenuItem("Print"));

		JMenuItem printItem=new JMenuItem("Print");
		printItem.setMnemonic('P');
		fileMenu.add(printItem);
		////////////////
			printItem.addActionListener(new ActionListener()
															{
																public void actionPerformed(ActionEvent event)
																{

																}
															}
										);

		///////

	//	fileMenu.add(new JMenuItem("Exit"));

		JMenuItem exitItem=new JMenuItem("Exit");
		exitItem.setMnemonic('E');
		fileMenu.add(exitItem);
		exitItem.addActionListener(new ActionListener()
													{
														public void actionPerformed(ActionEvent event)
														{
															DialogBoxForSaveFile();
														}
													}
										);

//------------------------------------------------------------------------------------------------------------------------------------
		mainMenuBar.add(fileMenu);

		editMenu = new JMenu("Edit");
		editMenu.add(new JMenuItem("Undo"));
		editMenu.add(new JMenuItem("Redo"));
		editMenu.add(new JMenuItem("Cut"));
		editMenu.add(new JMenuItem("Copy"));
		editMenu.add(new JMenuItem("Paste"));
		editMenu.add(new JMenuItem("Delete"));
		editMenu.add(new JMenuItem("Select All"));
		editMenu.add(new JMenuItem("To Upper case"));
		editMenu.add(new JMenuItem("To Lower case"));
		editMenu.add(new JMenuItem("Set Read Only"));
		editMenu.add(new JMenuItem("Trim Tralling Spaces"));
		editMenu.add(new JMenuItem("Time/Date"));
		mainMenuBar.add(editMenu);

		viewMenu = new JMenu("View");
		mainMenuBar.add(viewMenu);

		formatMenu = new JMenu("Format");
		formatMenu.add(new JMenuItem("Font"));
		mainMenuBar.add(formatMenu);

		languageMenu = new JMenu("Language");
		languageMenu.add(new JMenuItem("C Language"));
		languageMenu.add(new JMenuItem("Auto Save"));
		mainMenuBar.add(languageMenu);

		themeMenu = new JMenu("Themes");
		themeMenu.add(new JMenuItem("Meatl"));
		themeMenu.add(new JMenuItem("Nimbus"));
		themeMenu.add(new JMenuItem("CDE/Motif"));
		themeMenu.add(new JMenuItem("GTK+"));
		mainMenuBar.add(themeMenu);

		helpMenu = new JMenu("Help");
		helpMenu.add(new JMenuItem("View Help"));

		//helpMenu.add(new JMenuItem("About Notepad"));

		JMenuItem helpItem = new JMenuItem("About Notepad");
		helpItem.setMnemonic('n');
		helpItem.addActionListener(new ActionListener()
										{
											public void actionPerformed(ActionEvent event)
												{
													about_notepad();
												}
										}
					    		  );



		helpMenu.add(helpItem);
		mainMenuBar.add(helpMenu);

		t1=new JToolBar();

		newPage = new JButton(new ImageIcon("New16.PNG"));

		newPage.addActionListener(new ActionListener()
											{
												public void actionPerformed(ActionEvent event)
												{
													add_page();
												}
											}
								);



		open = new JButton(new ImageIcon("Open16.PNG"));
		open.addActionListener(new ActionListener()
																			{
																				public void actionPerformed(ActionEvent event)
																				{
																						open();
																				}
																			}
														);




		save = new JButton(new ImageIcon("Save16.PNG"));
		save.addActionListener(new ActionListener()
									{
										public void actionPerformed(ActionEvent event)
										{
											save();
										}
									}
								);

		saveall = new JButton(new ImageIcon("Saveall16.PNG"));
		pagesetup = new JButton(new ImageIcon("PageSetup16.PNG"));
		print = new JButton(new ImageIcon("Print16.PNG"));
		redo = new JButton(new ImageIcon("Redo16.PNG"));
		replace = new JButton(new ImageIcon("Replace16.PNG"));
		paste = new JButton(new ImageIcon("Paste16.PNG"));
		exit = new JButton(new ImageIcon("Exit16.PNG"));
		c16 = new JButton(new ImageIcon("C16.PNG"));
		close = new JButton(new ImageIcon("Close16.PNG"));
		close.addActionListener(new ActionListener()
																	{
																		public void actionPerformed(ActionEvent event)
																		{
																				DialogBoxForSaveFile();

																		}
																	}
												);

		closeall = new JButton(new ImageIcon("CloseAll16.PNG"));
		/*calculator = new JButton(new ImageIcon("Calculator16.PNG"));
		calculator.addActionListener(new ActionListener()
										{
											public void actionPerformed(ActionEvent event)
											{
												calculator c1 =new calculator();
											}
										}
									);*/
		//t1.setLayout(new GridLayout());

		t1.add(newPage);
		t1.add(open);
		t1.add(save);
		t1.add(saveall);
		t1.add(pagesetup);
		t1.add(print);
		t1.add(redo);
		t1.add(replace);
		t1.add(paste);
		t1.add(exit);
		t1.add(c16);
		t1.add(close);
		t1.add(closeall);
		t1.add(calculator);
		t1.add(replace);



		f.setLayout(new BorderLayout());
		f.setJMenuBar(mainMenuBar);
		f.add(t1,"North");

			JTextArea text1=new JTextArea();
			pane[panecnt]=new JScrollPane(text1);
			mainpane.addTab("Tab "+panecnt,pane[panecnt]);
			f.add(mainpane);
			panecnt++;
			f.setSize(1000,1000);
			f.setVisible(true);
	}


	public static void main(String argv[])
	{
		Ntpd n1=new Ntpd();

	}
}
