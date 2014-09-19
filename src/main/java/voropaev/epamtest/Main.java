package voropaev.epamtest;

import java.io.FileNotFoundException;

import voropaev.epamtest.service.ServiceImpl;
import voropaev.epamtest.view.View;

public class Main {	
	
	private final static String PATH_BY_DEFAULT = "src/main/resources/data.txt";
	

	public static void main(String[] args) {
		View view;
		int menuItem = 0;
		if(args.length!=0) {
			view = new View(new ServiceImpl(args[0]));
		}
		else {
			View.showMenu();
			view = new View();
			menuItem = view.readFromConsole();
			view.action(menuItem);
		}
		/*View view = new View();
		view.showMenu();
		int menuItem = view.readFromConsole();
		//System.out.println(i);
		view.action(menuItem);*/
	}		
}
