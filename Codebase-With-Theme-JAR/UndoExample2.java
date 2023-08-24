package GUI;


import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Hashtable;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;

public class UndoExample2 extends JFrame 
{
  public UndoExample2() 
  {
    super("Undo/Redo Example 2");

    pane = new JTextPane();
    pane.setEditable(true); // Editable
    getContentPane().add(new JScrollPane(pane), BorderLayout.CENTER);

 

    // Create the undo manager and actions
    UndoManager manager = new UndoManager();
    pane.getDocument().addUndoableEditListener(manager);

    Action undoAction = new UndoAction(manager);
    Action redoAction = new RedoAction(manager);

    // Add the actions to buttons
    JPanel panel = new JPanel();
    JButton undoButton = new JButton("Undo");
    JButton redoButton = new JButton("Redo");
    undoButton.addActionListener(undoAction);
    redoButton.addActionListener(redoAction);
    panel.add(undoButton);
    panel.add(redoButton);
    getContentPane().add(panel, BorderLayout.SOUTH);

   }

  // The Undo action
  public class UndoAction extends AbstractAction
  {
    public UndoAction(UndoManager manager) 
    {
      this.manager = manager;
    }

    public void actionPerformed(ActionEvent evt)
    {
      try 
      {
        manager.undo();
      } catch (CannotUndoException e) 
      {
        Toolkit.getDefaultToolkit().beep();
      }
    }

    private UndoManager manager;
  }

  // The Redo action
  public class RedoAction extends AbstractAction {
    public RedoAction(UndoManager manager) {
      this.manager = manager;
    }

    public void actionPerformed(ActionEvent evt) {
      try {
        manager.redo();
      } catch (CannotRedoException e) {
        Toolkit.getDefaultToolkit().beep();
      }
    }

    private UndoManager manager;
  }

  public static void main(String[] args) {
    try {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    } catch (Exception evt) {}
  
    JFrame f = new UndoExample2();
    f.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent evt) {
        System.exit(0);
      }
    });
    f.setSize(250, 300);
    f.setVisible(true);

 }

  private static JTextPane pane;

  private static JMenuBar menuBar;

 
}


