package projet6.mainwindow;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MainWindow {

	public static void main(String[] args) {
		JFrame mainwindow = new JFrame();
		mainwindow.setSize(800,600);
		mainwindow.setTitle("App");
		JMenuBar menuBar = new JMenuBar();
		mainwindow.setJMenuBar(menuBar);
		JMenu menu = new JMenu("Help");
		menuBar.add(menu); 
		mainwindow.setVisible(true);

	}

}
 