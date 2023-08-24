package GUI;
//CORRECT

import java.util.LinkedList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;
import java.io.*;
import java.awt.datatransfer.*;
import javax.swing.undo.*;
import java.awt.print.*;
import javax.swing.border.*;
import java.util.Date;


public class Notepad extends JFrame implements KeyListener,MouseListener
{

	JTextPane text1[]=new JTextPane[55];
	int panecnt=0,wordcnt=1,pos=0,start=0,startforn=0,endforn;
	String completePathFilename,filename,str1="",strforn="";
	JFrame f;
	JScrollPane sp;
	JTabbedPane mainpane;
 	StyleContext m_context = new StyleContext();
	JMenuBar mainMenuBar;
	JMenu fileMenu,editMenu,viewMenu,formatMenu,languageMenu,themeMenu,helpMenu;
	JMenuItem cutItem,copyItem,deleteItem,touppercaseItem,lowercaseItem,pasteItem;
	JToolBar t1;
	JButton newPage,open,save,saveall,pagesetup,print,redo,replace,paste,exit,c16,close,closeall,calculator,cut,copy,find,undo,bold;
	boolean check[]=new boolean[55],language=false,curliy=false,single=false,double1=false,round=false,square=false,t=false;

	Choice cb,cbsize;///////
	/**********************LinkedList**************/
	LinkedList obj=new LinkedList();
	LinkedList fname=new LinkedList();

	int tabcount()
	{
		int total=-1;
		for(int i=0;i<obj.size();i++)
		{
			String s=(String)fname.get(i);
			if(s.equals(new String("")))
			{
				total++;
			}
		}
		total=total+1;
			return total;
	}

	public void add_page()
		{

				text1[panecnt]=new JTextPane();
				int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
   				int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
				sp=new JScrollPane(text1[panecnt],v,h);
				int tabcount=tabcount();
				mainpane.addTab("Tab "+tabcount,sp);
				obj.add(panecnt);
				check[panecnt]=false;
				fname.add("");
				mainpane.setSelectedIndex(mainpane.getTabCount()-1);
				text1[panecnt].requestFocus();
				text1[panecnt].addKeyListener(this);
				text1[panecnt].addMouseListener(this);
				panecnt++;
		}


	void ReadFile()
		{

		      		BufferedReader d;
		        	StringBuffer sb = new StringBuffer();
		        	 try
		        	 {
									text1[panecnt]=new JTextPane();
									int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
					   				int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
									sp=new JScrollPane(text1[panecnt]);
				     	        	d = new BufferedReader(new FileReader(completePathFilename));
				     	        	String line;
				     	        	while((line=d.readLine())!=null)
				     	        	sb.append(line + "\n");
				     	        	text1[panecnt].setText(sb.toString());
		 							mainpane.addTab(filename,sp);
									obj.add(panecnt);
									check[panecnt]=false;
									fname.add(completePathFilename);
									mainpane.setSelectedIndex(mainpane.getTabCount()-1);
				     	        	text1[panecnt].requestFocus();
									text1[panecnt].addKeyListener(this);
									text1[panecnt].addMouseListener(this);
									panecnt++;
				                	d.close();
		            	}
		         catch(FileNotFoundException fe)
		             {
		             		System.out.println("File not Found");
		         	 }
		         catch(IOException ioe){}
		}


	//dialog box for confirmation to saving file.
	int DialogBoxForSaveFile()
		{
			if(check[(Integer)obj.get(mainpane.getSelectedIndex())]==true)
			{
				 JPanel message = new JPanel();
				 JLabel l1=new JLabel("DO you want to save Changes of current Tab ");
				 message.add(l1);
				int result = JOptionPane.showConfirmDialog(f, message, "Save", JOptionPane.YES_NO_CANCEL_OPTION);
				if(result == JOptionPane.YES_OPTION)        // selecting yes option from dialog box
				{
					int i=mainpane.getSelectedIndex();
					save();
					System.out.println(mainpane.getSelectedIndex());
					mainpane.remove(mainpane.getSelectedIndex());
					System.out.println(obj.remove(i));
					System.out.println(fname.remove(i));
					return 0;
				}
				else if(result == JOptionPane.NO_OPTION)			// selecting no option from dialog box
				{
					int i=mainpane.getSelectedIndex();
					mainpane.remove(i);
					System.out.println(i);
					System.out.println((Integer)obj.get(i));
					obj.remove(i);
					fname.remove(i);
					return 1;
				}
			}
			else
			{
				int i=mainpane.getSelectedIndex();
				mainpane.remove(i);
				System.out.println(i);
				System.out.println((Integer)obj.get(i));
				obj.remove(i);
				fname.remove(i);
				return 1;
			}
			return 2;
		}


//file dialog box for saving the file.
void save()
{
int i=mainpane.getSelectedIndex();
	if((fname.get(i))=="")
	{
		System.out.println(fname.get(i));
		JFileChooser jc=new JFileChooser();
		jc.showSaveDialog(f);
		if(jc.getSelectedFile()!=null)
		{
		 	File f1 =jc.getSelectedFile();
		        completePathFilename = f1.getAbsolutePath();
			fname.set(i,completePathFilename);
		    	try
            	{
            		System.out.println((Integer)obj.get(i));
					f1.createNewFile();
					System.out.println(i);
					FileWriter fw = new FileWriter(f1);
					String text = text1[(Integer)obj.get(i)].getText();
					System.out.println(text);
					fw.write(text);
					fw.close();
					mainpane.setTitleAt(i,f1.getName());
					check[(Integer)obj.get(mainpane.getSelectedIndex())]=false;
	    	   	}
	       	 	catch(Exception ex)
	         	{
         	            System.out.println("File not found");
         	 	}
          	}
     	}
     	else
     	{
     		 try
     	       {
					i = mainpane.getSelectedIndex();
					FileWriter fw;
					System.out.println("Path = "+fname.get(i).toString());
					File f = new File(fname.get(i).toString());
					f.delete();
					f.createNewFile();
					fw = new FileWriter(f);
					String txt = text1[(Integer)obj.get(i)].getText();
					fw.write(txt);
					fw.close();
					check[(Integer)obj.get(mainpane.getSelectedIndex())]=false;
	    	   	}
	       	 catch(Exception ex)
	         {
         	            System.out.println("File not found");
         	 }
     	}
}


void saveall()
{
	int tabno = mainpane.getSelectedIndex();
	for(int i=0;i<obj.size();i++)
	{
		mainpane.setSelectedIndex(i);
		save();
	}
	mainpane.setSelectedIndex(tabno);
	text1[(Integer)obj.get(mainpane.getSelectedIndex())].requestFocus();
}


//file dialog box for opening file
		void open()
			{
				JFileChooser jc=new JFileChooser();
				jc.showOpenDialog(f);

				 if (jc.getSelectedFile()!=null)
				   {
					   	System.out.println("Open");

						File f1 =jc.getSelectedFile();

						completePathFilename =f1.getAbsolutePath();
						filename=f1.getName();
				        ReadFile();
				   }
			 }


	Notepad()
	{

		f=new JFrame("Adavanced Notepad");
	    mainpane = new JTabbedPane();
		mainpane.addMouseListener(this);

		mainMenuBar=new JMenuBar();
		fileMenu = new JMenu("File");
		JMenuItem newItem =	new JMenuItem("New",new ImageIcon("images/New16.PNG"));
		newItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
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
		JMenuItem openItem =	new JMenuItem("Open",new ImageIcon("images/Open16.PNG"));
		openItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
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



		JMenuItem saveItem=new JMenuItem("Save",new ImageIcon("images/Save16.PNG"));
		saveItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
		saveItem.setMnemonic('V');
		fileMenu.add(saveItem);

		saveItem.addActionListener(new ActionListener()
															{
																public void actionPerformed(ActionEvent event)
																{
																		save();
																}
															}
										);


		JMenuItem saveAsItem=new JMenuItem("Save AS");
		saveAsItem.setMnemonic('S');
		fileMenu.add(saveAsItem);
		saveAsItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{

				JFileChooser jc=new JFileChooser();
				jc.showSaveDialog(f);
				if(jc.getSelectedFile()!=null)
				{
					int i = mainpane.getSelectedIndex();
			 		File f1 =jc.getSelectedFile();
					completePathFilename = f1.getAbsolutePath();
					fname.set(i,completePathFilename);
			    		try
		    			{
		    				f1.createNewFile();
						System.out.println(i);
						FileWriter fw = new FileWriter(f1);
						String text = text1[(Integer)obj.get(i)].getText();
						System.out.println(text);
						fw.write(text);
						fw.close();
						mainpane.setTitleAt(i,f1.getName());
						check[(Integer)obj.get(mainpane.getSelectedIndex())]=false;
		    	   		}
		       	 		catch(Exception ex)
			 		{
		 	            		System.out.println("File not found");
		 	 		}
				}
			}
		});


		JMenuItem saveAllItem=new JMenuItem("Save All",new ImageIcon("images/SaveAll16.PNG"));
		saveAllItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
		saveAllItem.setMnemonic('S');
		saveAllItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
					saveall();
			}
		}
);
		fileMenu.add(saveAllItem);


		JMenuItem closeItem=new JMenuItem("Close",new ImageIcon("images/Close16.PNG"));
		closeItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));


		closeItem.setMnemonic('C');
		fileMenu.add(closeItem);
		closeItem.addActionListener(new ActionListener()
																		{
																			public void actionPerformed(ActionEvent event)
																			{
																				DialogBoxForSaveFile();
																				try
																				{
																					text1[(Integer)obj.get(mainpane.getSelectedIndex())].requestFocus();
																				}
																				catch(Exception e)
																				{
																						System.out.println("eeeeee");
					}															}
																		}
													);


		JMenuItem closeAllItem=new JMenuItem("Close All",new ImageIcon("images/CloseAll16.PNG"));
		closeAllItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
		closeAllItem.setMnemonic('A');
		fileMenu.add(closeAllItem);
		closeAllItem.addActionListener(new ActionListener()
															{
																public void actionPerformed(ActionEvent event)
																{
																		while(mainpane.getTabCount()!=0)
																		{
																			int i=DialogBoxForSaveFile();
																				if(i==2)
																				{
																					try
																					{
																						text1[(Integer)obj.get(mainpane.getSelectedIndex())].requestFocus();
																						break;
																					}
																					catch(Exception e)
																					{
																																											System.out.println("eeeeee");
																					}
																				}


																		}
																}
															}

										);


		fileMenu.addSeparator();

		JMenuItem pageSetupItem=new JMenuItem("PageSetup",new ImageIcon("images/PageSetup16.PNG"));
		pageSetupItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));

		pageSetupItem.setMnemonic('P');
		fileMenu.add(pageSetupItem);


		JMenuItem printItem=new JMenuItem("Print",new ImageIcon("images/Print16.PNG"));
		printItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));

		printItem.setMnemonic('P');
		fileMenu.add(printItem);
		printItem.addActionListener(new ActionListener()
															{
																public void actionPerformed(ActionEvent event)
																{

																}
															}
										);


		fileMenu.addSeparator();
		JMenuItem exitItem=new JMenuItem("Exit",new ImageIcon("images/Exit16.PNG"));
		exitItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));

		exitItem.setMnemonic('E');
		fileMenu.add(exitItem);
		exitItem.addActionListener(new ActionListener()
													{
														public void actionPerformed(ActionEvent event)
														{
															while(mainpane.getTabCount()!=0)
															{
																int i=DialogBoxForSaveFile();
																if(i==2)
																{
																	text1[(Integer)obj.get(mainpane.getSelectedIndex())].requestFocus();
																	break;
																}
															}

															if(mainpane.getTabCount()==0)
															{
																	System.exit(0);
															}
														}
													}

										);

//------------------------------------------------------------------------------------------------------------------------------------
		mainMenuBar.add(fileMenu);

		editMenu = new JMenu("Edit");


		JMenuItem undoItem=new JMenuItem("Undo",new ImageIcon("images/Undo16.PNG"));
		undoItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
		undoItem.setMnemonic('U');
		undoItem.setEnabled(false);
		editMenu.add(undoItem);
		undoItem.addActionListener(new ActionListener()
															{
																public void actionPerformed(ActionEvent event)
																{

																}
															}
												);



		JMenuItem redoItem=new JMenuItem("Redo",new ImageIcon("images/Redo16.PNG"));
		redoItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
		redoItem.setMnemonic('R');
		redoItem.setEnabled(false);
		editMenu.add(redoItem);
		redoItem.addActionListener(new ActionListener()
															{
																public void actionPerformed(ActionEvent event)
																{

																}
															}
												);

		editMenu.addSeparator();

		cutItem=new JMenuItem("Cut",new ImageIcon("images/Cut16.PNG"));
		cutItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
		cutItem.setMnemonic('C');
		editMenu.add(cutItem);
		cutItem.setEnabled(false);
		cutItem.addActionListener(new ActionListener()
															{
																public void actionPerformed(ActionEvent event)
																{
																	text1[(Integer)obj.get(mainpane.getSelectedIndex())].cut();
																}
															}
												);


		copyItem=new JMenuItem("Copy",new ImageIcon("images/Copy16.PNG"));
		copyItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
		copyItem.setMnemonic('C');
		copyItem.setEnabled(false);
		editMenu.add(copyItem);
		copyItem.addActionListener(new ActionListener()
															{
																public void actionPerformed(ActionEvent event)
																{
																		text1[(Integer)obj.get(mainpane.getSelectedIndex())].copy();
																}
															}
												);



		pasteItem=new JMenuItem("Paste",new ImageIcon("images/Paste16.PNG"));
	    pasteItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
		pasteItem.setMnemonic('P');
		editMenu.add(pasteItem);
		pasteItem.addActionListener(new ActionListener()
															{
																public void actionPerformed(ActionEvent event)
																{
																		text1[(Integer)obj.get(mainpane.getSelectedIndex())].paste();
																}
															}
												);


		deleteItem=new JMenuItem("Delete",new ImageIcon("images/Delete16.PNG"));
	    deleteItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, java.awt.event.InputEvent.CTRL_MASK));
		deleteItem.setMnemonic('D');
		deleteItem.setEnabled(false);
		editMenu.add(deleteItem);
		deleteItem.addActionListener(new ActionListener()
															{
																public void actionPerformed(ActionEvent event)
																{
																	text1[(Integer)obj.get(mainpane.getSelectedIndex())].replaceSelection("");
																}
															}
												);


		JMenuItem selectallItem=new JMenuItem("Select All",new ImageIcon("images/SelectAll16.PNG"));
	    selectallItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
		selectallItem.setMnemonic('S');
		editMenu.add(selectallItem);
		selectallItem.addActionListener(new ActionListener()
															{
																public void actionPerformed(ActionEvent event)
																{
																		text1[(Integer)obj.get(mainpane.getSelectedIndex())].selectAll();
																}
															}
												);


		editMenu.addSeparator();

		touppercaseItem=new JMenuItem("To Upper Case",new ImageIcon("images/ToUpperCase16.PNG"));
	  //  touppercaseItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
		touppercaseItem.setMnemonic('T');
		touppercaseItem.setEnabled(false);
		editMenu.add(touppercaseItem);
		touppercaseItem.addActionListener(new ActionListener()
															{
																public void actionPerformed(ActionEvent event)
																{
																	text1[(Integer)obj.get(mainpane.getSelectedIndex())].replaceSelection((text1[(Integer)obj.get(mainpane.getSelectedIndex())].getSelectedText()).toUpperCase());
																}
															}
												);


		lowercaseItem=new JMenuItem("To Lower Case",new ImageIcon("images/ToLowerCase16.PNG"));
	  //  lowercaseItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
		lowercaseItem.setMnemonic('T');
		lowercaseItem.setEnabled(false);
		editMenu.add(lowercaseItem);
		lowercaseItem.addActionListener(new ActionListener()
															{
																public void actionPerformed(ActionEvent event)
																{
																	text1[(Integer)obj.get(mainpane.getSelectedIndex())].replaceSelection((text1[(Integer)obj.get(mainpane.getSelectedIndex())].getSelectedText()).toLowerCase());
																}
															}
												);


		editMenu.addSeparator();

		JMenuItem setreadonly=new JMenuItem("Set Read Only");
		setreadonly.setMnemonic('T');
		editMenu.add(setreadonly);
		setreadonly.addActionListener(new ActionListener()
																			{
																				public void actionPerformed(ActionEvent event)
																				{
																					if(text1[(Integer)obj.get(mainpane.getSelectedIndex())].isEditable())
																					{
																						text1[(Integer)obj.get(mainpane.getSelectedIndex())].setEditable(false);
																					}
																					else
																					{
																						text1[(Integer)obj.get(mainpane.getSelectedIndex())].setEditable(true);
																					}
																				}
																			}
																);


		editMenu.add(new JMenuItem("Trim Tralling Spaces"));


		editMenu.addSeparator();

		JMenuItem date1=new JMenuItem("Date",new ImageIcon("images/ToLowerCase16.PNG"));
		date1.setMnemonic('T');
		editMenu.add(date1);
		date1.addActionListener(new ActionListener()
																	{
																		public void actionPerformed(ActionEvent event)
																		{
																			java.util.Date d = new Date();
																			text1[(Integer)obj.get(mainpane.getSelectedIndex())].setText("Date & Time = "+d);
																		}
																	}
														);



		mainMenuBar.add(editMenu);

		viewMenu = new JMenu("View");
		mainMenuBar.add(viewMenu);

		formatMenu = new JMenu("Format");

			JMenuItem font=new JMenuItem("Font");
			font.setMnemonic('F');
			formatMenu.add(font);
			font.addActionListener(new ActionListener()
																			{
																				public void actionPerformed(ActionEvent event)
																				{
																				//	Fonts();
																				}
																			}
														);



		mainMenuBar.add(formatMenu);

		languageMenu = new JMenu("Language");

		JMenuItem clanguage=new JMenuItem("C Language");
	 // clanguage.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
		clanguage.setMnemonic('C');
		languageMenu.add(clanguage);
		clanguage.addActionListener(new ActionListener()
										{
											public void actionPerformed(ActionEvent event)
											{
												if(language == false)
												{
													language = true;
													start=text1[(Integer)obj.get(mainpane.getSelectedIndex())].getCaretPosition();
												}
												else
												{
													language = false;
												}
											}
										}
									);


		languageMenu.add(new JMenuItem("Auto Save"));
		mainMenuBar.add(languageMenu);

		themeMenu = new JMenu("Themes");


		JMenuItem metalItem=new JMenuItem("Metal");
		themeMenu.add(metalItem);
		metalItem.addActionListener(new ActionListener()
											{
															public void actionPerformed(ActionEvent event)
																		{
																			try
																			{
																				UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
																				SwingUtilities.updateComponentTreeUI(f);

																			}
																			catch (Exception e)
																			{

																			}
																		}
																	}
												);





		JMenuItem nimbusItem=new JMenuItem("Nimbus");
		themeMenu.add(nimbusItem);
		nimbusItem.addActionListener(new ActionListener()
											{
															public void actionPerformed(ActionEvent event)
																		{
																			try
																			{
																				UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
																				SwingUtilities.updateComponentTreeUI(f);
																			}
																			catch (Exception e)
																			{
																			}
																		}
																	}
												);



		JMenuItem motifItem=new JMenuItem("CDE/Motif");
		themeMenu.add(motifItem);
		motifItem.addActionListener(new ActionListener()
											{
															public void actionPerformed(ActionEvent event)
																		{
																			try {

																					UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
																					SwingUtilities.updateComponentTreeUI(f);
																			}
																			catch (Exception e)
																			{

																			}
																		}
																	}
												);



		JMenuItem gtkItem=new JMenuItem("GTK+");
		themeMenu.add(gtkItem);
		gtkItem.addActionListener(new ActionListener()
											{
															public void actionPerformed(ActionEvent event)
																		{
																			try{
																					UIManager.setLookAndFeel("javax.swing.plaf.gtk.GtkLookAndFeel");
																					SwingUtilities.updateComponentTreeUI(f);
																				}
																						catch (Exception e)
																				{
																						System.out.println("dsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsds");
																				}
																		}
																	}
												);

		mainMenuBar.add(themeMenu);

		helpMenu = new JMenu("Help");
		helpMenu.add(new JMenuItem("View Help"));

		JMenuItem helpItem = new JMenuItem("About Notepad");
		helpItem.setMnemonic('n');
		helpItem.addActionListener(new ActionListener()
										{
											public void actionPerformed(ActionEvent event)
												{
													new About();
												}
										}
					    		  );



		helpMenu.add(helpItem);
		mainMenuBar.add(helpMenu);

		t1=new JToolBar();

		newPage = new JButton(new ImageIcon("images/New16.PNG"));
		newPage.setToolTipText("add Page");
		newPage.addActionListener(new ActionListener()
											{
												public void actionPerformed(ActionEvent event)
												{
													add_page();
												}
											}
								);



		open = new JButton(new ImageIcon("images/Open16.PNG"));
		open.setToolTipText("Open File");
		open.addActionListener(new ActionListener()
																			{
																				public void actionPerformed(ActionEvent event)
																				{
																						open();
																				}
																			}
														);




		save = new JButton(new ImageIcon("images/Save16.PNG"));
		save.setToolTipText("Save File");
		save.addActionListener(new ActionListener()
									{
										public void actionPerformed(ActionEvent event)
										{
											save();
										}
									}
								);

		saveall = new JButton(new ImageIcon("images/Saveall16.PNG"));
		saveall.setToolTipText("Save All");

		saveall.addActionListener(new ActionListener()
										{
											public void actionPerformed(ActionEvent event)
												{
													saveall();
												}
											}
										);



		exit = new JButton(new ImageIcon("images/Exit16.PNG"));
		exit.setToolTipText("Exit");
		exit.addActionListener(new ActionListener()
									{
										public void actionPerformed(ActionEvent event)
										{
											while(mainpane.getTabCount()!=0)
											{
												int i=DialogBoxForSaveFile();
												if(i==2)
												{
													text1[(Integer)obj.get(mainpane.getSelectedIndex())].requestFocus();
													break;
												}
											}
											if(mainpane.getTabCount()==0)
											{
													System.exit(0);
											}
										}
									}
							);


		cut = new JButton(new ImageIcon("images/Cut16.PNG"));
		cut.setToolTipText("Cut");
		cut.addActionListener(new ActionListener()
								{
									public void actionPerformed(ActionEvent event)
									{
										text1[(Integer)obj.get(mainpane.getSelectedIndex())].cut();
									}
								}
							);


		copy = new JButton(new ImageIcon("images/Copy16.PNG"));
		copy.setToolTipText("Copy");
		copy.addActionListener(new ActionListener()
									{
										public void actionPerformed(ActionEvent event)
										{
											text1[(Integer)obj.get(mainpane.getSelectedIndex())].copy();
										}
									}
								);








		paste = new JButton(new ImageIcon("images/Paste16.PNG"));
		paste.setToolTipText("Paste");
		paste.addActionListener(new ActionListener()
									{
										public void actionPerformed(ActionEvent event)
										{
											text1[(Integer)obj.get(mainpane.getSelectedIndex())].paste();
										}
									}
								);






		undo = new JButton(new ImageIcon("images/Undo16.PNG"));
		undo.setToolTipText("Undo");

		redo = new JButton(new ImageIcon("images/Redo16.PNG"));
		redo.setToolTipText("Redo");

		replace = new JButton(new ImageIcon("images/Replace16.PNG"));
		replace.setToolTipText("Replace");
		replace.addActionListener(new ActionListener()
										{
											public void actionPerformed(ActionEvent event)
											{
												new ReplaceClass();
											}
										}
									);

		find = new JButton(new ImageIcon("images/Find16.PNG"));
		find.setToolTipText("Find");
		find.addActionListener(new ActionListener()
										{
											public void actionPerformed(ActionEvent event)
											{
												new ReplaceClass();
											}
										}
									);


		c16 = new JButton(new ImageIcon("images/C16.PNG"));

		close = new JButton(new ImageIcon("images/Close16.PNG"));
		close.setToolTipText("Close Current Tab");
		close.addActionListener(new ActionListener()
																	{
																		public void actionPerformed(ActionEvent event)
																		{
																				DialogBoxForSaveFile();

																		}
																	}
												);

	//	closeall = new JButton(new ImageIcon("images/CloseAll16.PNG"));
		//closeall.setToolTipText("Close All Tab");

		calculator = new JButton(new ImageIcon("images/Calculator16.PNG"));
		calculator.setToolTipText("Calculator");
		calculator.addActionListener(new ActionListener()
										{
											public void actionPerformed(ActionEvent event)
											{
												//calculator c1 =new calculator();
											}
										}
									);
		//t1.setLayout(new GridLayout());

		t1.add(newPage);
		t1.add(open);
		t1.add(save);
		t1.add(saveall);
		t1.add(close);
		t1.add(exit);
		t1.addSeparator();
		t1.add(cut);
		t1.add(copy);
		t1.add(paste);
		t1.addSeparator();
		t1.add(undo);
		t1.add(redo);
		t1.addSeparator();
		t1.add(find);
		t1.add(replace);
		t1.addSeparator();
		t1.add(calculator);
		t1.addSeparator();
////////////////////////////////////////////////////////////////////


		//*********************adding all available fonts into Choice*************************
		cb=new Choice();
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
				DefaultStyledDocument doc = (DefaultStyledDocument)text1[(Integer)obj.get(mainpane.getSelectedIndex())].getDocument();
				//getting selected font
				String fontname=cb.getSelectedItem();
				System.out.println("choosed font:"+fontname);

				//setting the style (font,color,size,bold/italic etc...)

				Style mystyle=m_context.addStyle("style1", null);

				//	mystyle.addAttribute(StyleConstants.Foreground, Color.red);

				mystyle.addAttribute(StyleConstants.FontFamily, fontname);

				//setting style to selected text
				int startofselection=text1[(Integer)obj.get(mainpane.getSelectedIndex())].getSelectionStart();
				String sel=text1[(Integer)obj.get(mainpane.getSelectedIndex())].getSelectedText();
				int endofselection=sel.length();
				System.out.println("start="+startofselection+"end="+endofselection);

				//m_doc[(Integer)obj.get(mainpane.getSelectedIndex())]

				doc.setCharacterAttributes(startofselection, endofselection, mystyle, true);
			}
		});

		t1.add(cb);

		cbsize=new Choice();
		String val[]={"2","4","6","8","10","12","14","16","18","20","22","24","26","28","30","32","32","34","36","38","40","42","44","46","48","50"};
		for(int i=0;i<val.length;i++)
		{
			cbsize.add(val[i]);
		}
		cbsize.addItemListener(new ItemListener()
								{
									public void itemStateChanged(ItemEvent e)
									{
										int f_sz = (Integer.parseInt(cbsize.getSelectedItem().toString()));
										DefaultStyledDocument doc = (DefaultStyledDocument)text1[(Integer)obj.get(mainpane.getSelectedIndex())].getDocument();

										Style mystyle = m_context.addStyle("style1", null);
										mystyle.addAttribute(StyleConstants.FontSize, f_sz);
										System.out.println("start"+text1[(Integer)obj.get(mainpane.getSelectedIndex())].getSelectionStart());
										System.out.println("end"+text1[(Integer)obj.get(mainpane.getSelectedIndex())].getSelectionEnd());
										int sel_len = text1[(Integer)obj.get(mainpane.getSelectedIndex())].getSelectionEnd()-text1[(Integer)obj.get(mainpane.getSelectedIndex())].getSelectionStart();
										doc.setCharacterAttributes(text1[(Integer)obj.get(mainpane.getSelectedIndex())].getSelectionStart(),sel_len, mystyle, true);



										/*
										String value=cbsize.getSelectedItem();
										System.out.println("choosed size:"+value);
										Style mystyle = sc.addStyle("style1", null);
										mystyle.addAttribute(StyleConstants.FontSize, value);

										int startofselection=text1[(Integer)obj.get(mainpane.getSelectedIndex())].getSelectionStart();
										String sel=text1[(Integer)obj.get(mainpane.getSelectedIndex())].getSelectedText();
										int endofselection=sel.length();
										System.out.println("start="+startofselection+"end="+endofselection);
										String s=text1[(Integer)obj.get(mainpane.getSelectedIndex())].getEditorKitClassNameForContentType(sel);
										System.out.println("endsdddddddddd="+s);
										DefaultStyledDocument doc = (DefaultStyledDocument)text1[(Integer)obj.get(mainpane.getSelectedIndex())].getDocument();
										doc.setCharacterAttributes(startofselection, endofselection, mystyle, true);
										*/
										//m_doc[(Integer)obj.get(mainpane.getSelectedIndex())].setCharacterAttributes(startofselection, endofselection, mystyle, true);
									}
								});
		t1.add(cbsize);

		t1.addSeparator();
		bold = new JButton(new ImageIcon("images/Bold16.PNG"));
		bold.setToolTipText("Bold");
		t1.add(bold);

	//SpellCheck16
///////////////////////////////////////////////////////////////////
		f.setLayout(new BorderLayout());
		f.setJMenuBar(mainMenuBar);
		f.add(t1,"North");

					add_page(); //adding 1st page

			f.add(mainpane);

			f.setSize(1000,1000);
			f.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			f.addWindowListener(new windowclass1());
			f.setVisible(true);
			text1[(Integer)obj.get(mainpane.getSelectedIndex())].requestFocus();
	}



boolean isKeyword(String keyword)
	{

		keyword=keyword.trim();

		if(keyword.equals(new String("if")) || keyword.equals(new String("long")) || keyword.equals(new String("struct")) || keyword.equals(new String("break")) || keyword.equals(new String("goto")) || keyword.equals(new String("signed")) || keyword.equals(new String("if")) || keyword.equals(new String("if"))
		|| keyword.equals(new String("else")) || keyword.equals(new String("short")) || keyword.equals(new String("double")) || keyword.equals(new String("case")) || keyword.equals(new String("do")) || keyword.equals(new String("unsigned")) || keyword.equals(new String("if")) || keyword.equals(new String("if"))
		|| keyword.equals(new String("void")) || keyword.equals(new String("float")) || keyword.equals(new String("union")) || keyword.equals(new String("switch")) || keyword.equals(new String("while")) || keyword.equals(new String("if")) || keyword.equals(new String("if")) || keyword.equals(new String("if"))
		|| keyword.equals(new String("int")) || keyword.equals(new String("char")) || keyword.equals(new String("continue")) || keyword.equals(new String("return")) || keyword.equals(new String("for")) || keyword.equals(new String("if")) || keyword.equals(new String("if")) || keyword.equals(new String("if"))  )
		{
			return true;
		}
		return false;
	}

void check_equal(String str,int start)
	{
		System.out.println("line"+str.length());
		DefaultStyledDocument doc = (DefaultStyledDocument)text1[(Integer)obj.get(mainpane.getSelectedIndex())].getDocument();

		Style styleblack=doc.addStyle("black", null);
		StyleConstants.setForeground(styleblack, Color.BLACK);
		doc.setCharacterAttributes(start, str.length(), styleblack,true);
		if(isKeyword(str))
			{
				try
				{
					doc = (DefaultStyledDocument)text1[(Integer)obj.get(mainpane.getSelectedIndex())].getDocument();
					Style styleKey=doc.addStyle("Keyword", null);
					StyleConstants.setForeground(styleKey, Color.BLUE);
					doc.setCharacterAttributes(start, str.length(), styleKey,true);
				}
				catch(Exception e)
				{

				}
			}
	}


	public void mouseClicked(MouseEvent m)
	{
		Object o=m.getSource();
		try
		{
			if(o==mainpane)
			{

				if(check[(Integer)obj.get(mainpane.getSelectedIndex())]==false)
				{
					cutItem.setEnabled(false);
					copyItem.setEnabled(false);
			 		deleteItem.setEnabled(false);
			 		touppercaseItem.setEnabled(false);
			 		lowercaseItem.setEnabled(false);
				}
				else
				{
					cutItem.setEnabled(true);
					copyItem.setEnabled(true);
					deleteItem.setEnabled(true);
					touppercaseItem.setEnabled(true);
					lowercaseItem.setEnabled(true);
				}
			}
			else if(o==text1[(Integer)obj.get(mainpane.getSelectedIndex())])
			{
				if(m.getModifiers() == m.BUTTON1_MASK)
				{
					if(language==true)
					{
						start=text1[(Integer)obj.get(mainpane.getSelectedIndex())].getCaretPosition();

					}
				}
				else if(m.getModifiers() == m.BUTTON3_MASK)
				{
					int x,y=0;
					Mouse m1=new Mouse();
					x=m.getX();
					y=m.getY();
					m1.show(text1[(Integer)obj.get(mainpane.getSelectedIndex())],x,y);
				}
			}
		}
		catch(Exception e)
		{
		}
	}

	public void mouseEntered(MouseEvent m)
	{

	}
	public void mouseExited(MouseEvent m)
	{

	}
	public void mousePressed(MouseEvent m)
	{

	}
	public void mouseReleased(MouseEvent m)
	{

	}

	public void keyPressed(KeyEvent k)
			{
				if(language==true)
				{
					if(k.getKeyCode()==8)
					{
						str1="";
						start=text1[(Integer)obj.get(mainpane.getSelectedIndex())].getCaretPosition();
						start=start-1;
					}
				}
			}

	public void keyReleased(KeyEvent k)
	{
		if(language==true)
		{
			if(t==true)
			{
				int i=text1[(Integer)obj.get(mainpane.getSelectedIndex())].getCaretPosition();
				try
				{
					DefaultStyledDocument doc = (DefaultStyledDocument)text1[(Integer)obj.get(mainpane.getSelectedIndex())].getDocument();
					doc.insertString(i," >",null);
				}
				catch(Exception e)
				{
					System.out.println("eeeeee");
				}
				t=false;
			}
			if(square==true)
			{
				int i=text1[(Integer)obj.get(mainpane.getSelectedIndex())].getCaretPosition();
				try
				{
						DefaultStyledDocument doc = (DefaultStyledDocument)text1[(Integer)obj.get(mainpane.getSelectedIndex())].getDocument();
						doc.insertString(i," ]",null);
				}
				catch(Exception e)
				{
					System.out.println("eeeeee");
				}
				square=false;
			}
			else if(curliy==true)
			{
					int i=text1[(Integer)obj.get(mainpane.getSelectedIndex())].getCaretPosition();
					try
					{
						DefaultStyledDocument doc = (DefaultStyledDocument)text1[(Integer)obj.get(mainpane.getSelectedIndex())].getDocument();
						doc.insertString(i,"\n\n }",null);
					}
					catch(Exception e)
					{
						System.out.println("eeeeee");
					}
					curliy=false;
			}
			else if(double1==true)
			{
				int i=text1[(Integer)obj.get(mainpane.getSelectedIndex())].getCaretPosition();
				try
				{
						DefaultStyledDocument doc = (DefaultStyledDocument)text1[(Integer)obj.get(mainpane.getSelectedIndex())].getDocument();
					//	doc.insertString(i,' \t" ',null);
				}
				catch(Exception e)
				{
					System.out.println("eeeeee");
				}
				double1=false;
			}
			else if(single==true)
			{
				int i=text1[(Integer)obj.get(mainpane.getSelectedIndex())].getCaretPosition();
				try
				{
						DefaultStyledDocument doc = (DefaultStyledDocument)text1[(Integer)obj.get(mainpane.getSelectedIndex())].getDocument();
						doc.insertString(i," '",null);
				}
				catch(Exception e)
				{
					System.out.println("eeeeee");
				}
				single=false;
			}
			else if(round==true)
			{
				int i=text1[(Integer)obj.get(mainpane.getSelectedIndex())].getCaretPosition();
				try
				{
					DefaultStyledDocument doc = (DefaultStyledDocument)text1[(Integer)obj.get(mainpane.getSelectedIndex())].getDocument();
					doc.insertString(i," )",null);
				}
				catch(Exception e)
				{
					System.out.println("eeeeee");
				}
				round=false;
			}
		}
	}

public void keyTyped(KeyEvent k)
{
	if(check[(Integer)obj.get(mainpane.getSelectedIndex())]==false)
	{
		check[(Integer)obj.get(mainpane.getSelectedIndex())]=true;
	 		cutItem.setEnabled(true);
	 		copyItem.setEnabled(true);
	 		deleteItem.setEnabled(true);
	 		touppercaseItem.setEnabled(true);
	 		lowercaseItem.setEnabled(true);
	 		cut.setEnabled(false);

	}
	else
	{			}

	if(language==true)
	{
		if(k.getKeyChar()=='[')
		{
			square=true;
		}
		else if(k.getKeyChar()=='{')
		{
			curliy=true;
		}
		else if(k.getKeyChar()=='<')
		{
			t=true;
		}
		else if(k.getKeyChar()=='(')
		{
			round=true;
		}
		/*else if(k.getKeyChar()=="'")
		{
			single=true;
		}*/
		else if(k.getKeyChar()=='"')
		{
			double1=true;
		}
		str1=str1+k.getKeyChar();
		strforn=strforn+k.getKeyChar();


		if(( k.getKeyChar() =='\t' ) || (k.getKeyChar() =='\n') || ( k.getKeyChar() == ' '))
		{
			check_equal(str1,start);
			str1="";
			start=text1[(Integer)obj.get(mainpane.getSelectedIndex())].getCaretPosition();
		}

		/*if( k.getKeyChar() =='\n' )
		{

			endforn=text1[(Integer)obj.get(mainpane.getSelectedIndex())].getCaretPosition();
				try
					{
							endforn=strforn.length();
							String s=text1[(Integer)obj.get(mainpane.getSelectedIndex())].getText(startforn, endforn);
							System.out.println(s);
							for(int i=0;i<cKeyWords.length;i++)
							{

								int localOffset=s.indexOf(cKeyWords[i]);
								System.out.println(localOffset);
							}
							strforn="";
							startforn=text1[(Integer)obj.get(mainpane.getSelectedIndex())].getCaretPosition();

					}
					catch (Exception e)
					{
						System.out.println("gggs");
					}
		}*/
	}
}




	public static void main(String argv[])
	{

		try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch (Exception e)
		{

		}
		Notepad n1=new Notepad();

	}

/////////////////////////////////////////////////////////




class s
{
	JTextField find_TF;
	public s()
	{
		JFrame findFrame = new JFrame("Find");
		findFrame.setBounds(0, 0, 400, 200);
		findFrame.setLayout(null);


		JLabel findL = new JLabel("Enter the String to Find:");
		findL.setBounds(10, 20, 180, 20);

		find_TF = new JTextField(50);
		find_TF.setBounds(200, 20, 150, 20);

		JButton findBt = new JButton("Find");
		findBt.setBounds(20, 100, 70, 30);

		findBt.addActionListener(new ActionListener()
									 {
											public void actionPerformed(ActionEvent e)
											{
													String findStr1 = find_TF.getText();
													System.out.println(findStr1);
											}
									});

		JButton findNextB = new JButton("Find Next");
		findNextB.setBounds(100, 100, 100, 30);
		findNextB.addActionListener(new ActionListener()
									 {
											public void actionPerformed(ActionEvent e)
											{

											}
									});

		JButton cancelB = new JButton("Cancel");
		cancelB.setBounds(210, 100, 100, 30);
		cancelB.addActionListener(new ActionListener()
									 {
											public void actionPerformed(ActionEvent e)
											{

											}
									});

		findFrame.add(findL);
		findFrame.add(find_TF);
		findFrame.add(findBt);
		findFrame.add(findNextB);
		findFrame.add(cancelB);

		findFrame.setVisible(true);
		findFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}
}



class ReplaceClass extends JDialog
{
	JTextField findTF,replaceTF ;
	 JLabel find,replace ;
	 String findStr,newdata,data,txt,replaceStr;
	 JButton findB,replaceB,replaceAllB;
	 ReplaceClass()
	{
		super(f,"Replace",true);
		setBounds(0, 0, 500, 250);
		setLayout(null);

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
		findB.addActionListener(new ActionListener()
							{
														public void actionPerformed(ActionEvent e)
														{
															findStr=findTF.getText();
														}
							});

		replaceB = new JButton("Replace");
		replaceB.setBounds(120, 150, 100, 30);
		replaceB.addActionListener(new ActionListener() {

																	public void actionPerformed(ActionEvent e)
															{
																findStr = findTF.getText();
																replaceStr = replaceTF.getText();
																int fr;
																text1[(Integer)obj.get(mainpane.getSelectedIndex())].selectAll();
																txt= text1[(Integer)obj.get(mainpane.getSelectedIndex())].getSelectedText();
																if((fr=txt.indexOf(findStr))==0)
																{
																	newdata =txt.replaceFirst(findStr,replaceStr);
																	System.out.println(newdata);
																}
																text1[(Integer)obj.get(mainpane.getSelectedIndex())].setText(newdata);

															}
														});

		replaceAllB = new JButton("Replace All");
		replaceAllB.setBounds(230, 150, 120, 30);
		replaceAllB.addActionListener(new ActionListener() {


															public void actionPerformed(ActionEvent e)
															{
																findStr = findTF.getText();
																replaceStr = replaceTF.getText();

																text1[(Integer)obj.get(mainpane.getSelectedIndex())].selectAll();
																txt= text1[(Integer)obj.get(mainpane.getSelectedIndex())].getSelectedText();
																newdata =txt.replace(findStr,replaceStr);
																text1[(Integer)obj.get(mainpane.getSelectedIndex())].setText(newdata);
																System.out.println(newdata);
															}
														});


		add(find);
		add(findTF);
		add(replace);
		add(replaceTF);
		add(findB);
		add(replaceB);
		add(replaceAllB);
		setVisible(true);
  }
}




///////////////////////////////////////////


class windowclass1 extends  WindowAdapter
												{

													public void windowClosing(WindowEvent w)
													{
														while(mainpane.getTabCount()!=0)
														{
																int i=DialogBoxForSaveFile();
																if(i==2)
																{
																	text1[(Integer)obj.get(mainpane.getSelectedIndex())].requestFocus();
																	break;
																}
														}
														if(mainpane.getTabCount()==0)
														{
																System.exit(0);
														}
													}
												}

class Mouse extends JPopupMenu implements ActionListener
{
 JMenuItem Cut,Copy,Paste,Delete,SelectAll,ToUpperCase,ToLowerCase;

 public Mouse()
 {
 	Cut=new JMenuItem("Cut",new ImageIcon("Cut16.PNG"));
 	Cut.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
   	Cut.setToolTipText("Cut");
   	Cut.setMnemonic('X');
   	Cut.setEnabled(false);
   	Cut.addActionListener(this);
 	add(Cut);

 	Copy=new JMenuItem("Copy",new ImageIcon("Copy16.PNG"));
 	Copy.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
   	Copy.setToolTipText("Copy");
   	Copy.setMnemonic('C');
   	Copy.setEnabled(false);
   	Copy.addActionListener(this);
 	add(Copy);

 	Paste=new JMenuItem("Paste",new ImageIcon("Paste16.PNG"));
 	Paste.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
   	Paste.setToolTipText("Paste");
   	Paste.setMnemonic('P');
   	Paste.addActionListener(this);
 	add(Paste);

 	Delete=new JMenuItem("Delete",new ImageIcon("Exit16.PNG"));
 	Delete.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, java.awt.event.InputEvent.CTRL_MASK));
   	Delete.setToolTipText("Delete");
   	Delete.setMnemonic('D');
   	Delete.setEnabled(false);
   	Delete.addActionListener(this);
 	add(Delete);

 	SelectAll=new JMenuItem("Select All",new ImageIcon("SelectAll16.PNG"));
 	SelectAll.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
   	SelectAll.setToolTipText("Select All");
   	SelectAll.setMnemonic('A');
   	SelectAll.addActionListener(this);
 	add(SelectAll);

 	addSeparator();

 	ToUpperCase=new JMenuItem("To Upper Case",new ImageIcon("ToUpperCase16.PNG"));
 	ToUpperCase.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
   	ToUpperCase.setToolTipText("To Upper Case");
   	ToUpperCase.setMnemonic('U');
   	ToUpperCase.setEnabled(false);
   	ToUpperCase.addActionListener(this);
 	add(ToUpperCase);

 	ToLowerCase=new JMenuItem("To Lower Case",new ImageIcon("ToLowerCase16.PNG"));
 	ToLowerCase.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
   	ToLowerCase.setToolTipText("To Lower case");
   	ToLowerCase.setMnemonic('X');
   	ToLowerCase.setEnabled(false);
   	ToLowerCase.addActionListener(this);
 	add(ToLowerCase);

 	//setSize(50,70);

 	if(text1[(Integer)obj.get(mainpane.getSelectedIndex())].getSelectedText()!=null)
 	{
 		Cut.setEnabled(true);
 		Copy.setEnabled(true);
 		Delete.setEnabled(true);
 		ToUpperCase.setEnabled(true);
 		ToLowerCase.setEnabled(true);
 	}

 	setVisible(true);
 }

 public void actionPerformed(ActionEvent ae)
 {
 	Object O=ae.getSource();

 	try
 	{
 		if(O==Cut)
  		{
 			text1[(Integer)obj.get(mainpane.getSelectedIndex())].cut();
  		}

  		if(O==Copy)
  		{
  			text1[(Integer)obj.get(mainpane.getSelectedIndex())].copy();
  		}

  		if(O==Paste)
  		{
  			text1[(Integer)obj.get(mainpane.getSelectedIndex())].paste();
  		}

  		if(O==SelectAll)
  		{
  			try
  			{
				text1[(Integer)obj.get(mainpane.getSelectedIndex())].selectAll();
  			}
  			catch(Exception ep)
  			{
  			}
  		}

  		if(O==Delete)
  		{
  			text1[(Integer)obj.get(mainpane.getSelectedIndex())].replaceSelection("");
  		}

 		if(O==ToUpperCase)
  		{
 			text1[(Integer)obj.get(mainpane.getSelectedIndex())].replaceSelection((text1[(Integer)obj.get(mainpane.getSelectedIndex())].getSelectedText()).toUpperCase());
  		}

  		if(O==ToLowerCase)
  		{
  			text1[(Integer)obj.get(mainpane.getSelectedIndex())].replaceSelection((text1[(Integer)obj.get(mainpane.getSelectedIndex())].getSelectedText()).toLowerCase());
  		}
 	}
 	catch(Exception e)
 	{
 	}
 }
}


class About extends JDialog
	{
		JLabel Prjct_Nm,Prjct_Ownrs;
		JButton b1;
		About()
		{
			super(f,"About Notepad",true);
			setSize(300,150);
			Prjct_Nm = new JLabel("     Advanced Notepad Created By:");
			Prjct_Nm.setBounds(10, 10, 250, 20);
			Prjct_Ownrs = new JLabel("  Rohan Deshpande");
			Prjct_Ownrs.setBounds(50, 10, 350, 20);
			b1 =new JButton(" Ok ");
			b1.setBounds(110, 70, 60, 30);

			b1.addActionListener(new ActionListener() {


																		public void actionPerformed(ActionEvent e)
																		{
																			try{
																				this.finalize();
																			}
																			//catch(Exception io)
																			catch(Throwable io)
																			{

																			}
																		}
																	});

			add(b1);
			add(Prjct_Nm);
			add(Prjct_Ownrs);
			setVisible(true);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
	}

}
