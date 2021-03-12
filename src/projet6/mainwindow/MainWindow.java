package projet6.mainwindow;


import javax.swing.*;

public class MainWindow {

	public static void main(String[] args) {
		JFrame mainwindow = new JFrame();
		mainwindow.setSize(800,600);
		mainwindow.setTitle("App");
		JMenuBar menuBar = new JMenuBar();
		mainwindow.setJMenuBar(menuBar);
		JMenu menu = new JMenu("Fichier");
		menuBar.add(menu); 
		JMenuItem Help = new JMenuItem();
		Help.addActionListener(null);
		menu.add(Help);
		mainwindow.setVisible(true);
		

	}

}
 