package projet6.mainwindow;


import java.awt.event.*;

import javax.swing.*;

public class MainWindow extends JFrame implements ActionListener {

	JFrame MainWindow;
	public static void  main(String[] args) {
		new MainWindow();
	}
		

	public  MainWindow() {
		MainWindow = new JFrame();
		MainWindow.setSize(800,600);
		MainWindow.setTitle("App");
		JMenuBar menuBar = new JMenuBar();
		MainWindow.setJMenuBar(menuBar);
		JMenu menu = new JMenu("Fichier");
		menuBar.add(menu); 
		JMenuItem Help = new JMenuItem("Help");
		Help.addActionListener(this);
		menu.add(Help);
		MainWindow.setVisible(true);
	}
	
    public  void    actionPerformed(ActionEvent e)
    
    {	
    	switch(e.getActionCommand() ) {
    	
    	case "Help" :
    		JOptionPane.showMessageDialog(MainWindow,
    			    "Créé par Theo et Zakari");
    	}
        
    }
}
 