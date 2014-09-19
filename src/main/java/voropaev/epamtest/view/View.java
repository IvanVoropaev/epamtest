package voropaev.epamtest.view;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import voropaev.epamtest.entity.Pair;
import voropaev.epamtest.service.ServiceImpl;

public class View {
	
	ServiceImpl service;
	
	public View() {
		
	}
	
	public View(ServiceImpl service) {
		this.service = service;
	}
	
	public ServiceImpl getService() {
		return service;
	}

	public void setService(ServiceImpl service) {
		this.service = service;
	}


	private final static String MENU = "Choose menu item:\n"
			+ "1: choose file.\n"
			+ "2: show pair list.\n"
			+ "3: add new pair\n"
			+ "4: delete pairs by name.\n"
			+ "5: sort by name.\n"
			+ "6: sort by value.\n";
	
	private Scanner in;
	
	public static void showMenu() {
		System.out.println(MENU);
	}
	
	public static boolean validFilePath(String path) {
		if ((new File(path)).exists()) {
		    return true;
		} else {
		    return false;
		}
	}
	
	public int readFromConsole() {	
		int menuItem = 0;
		boolean b = true;
		while(b) {
			in = new Scanner(System.in);
			System.out.print("¬ведите пункт меню: ");
			if(in.hasNextInt()) {
				menuItem = in.nextInt();
				if((menuItem <= 6) && (menuItem > 0))
					b = false;
				else
					System.out.print("Error. Please enter the number between 1 and 6. ");
			}
			else
				System.out.print("Error. Please enter the number between 1 and 6. ");
		}
		return menuItem;
	}
	
	public void action(int menuItem) {
		
		switch(menuItem) {
			case 1: {
				boolean filePath = false;
				String path = null;
				
				while(!filePath) {					
					in = new Scanner(System.in);
					System.out.println("Enter file path: ");
					if(in.hasNextLine()) {
						path = in.nextLine();
						System.out.println(path);
						if(validFilePath(path))
					}
					
					//in.close();	
				}
				
				service = new ServiceImpl(path);
			}
			
			case 2: {
				List<Pair> list = service.getPairsList();
				for(Pair p : list) {
					if(!p.getName().equals("error") && !p.getName().equals("null"))
						System.out.println(p.getName() + ": " + p.getValue());
				}
			}
			
			case 3: {
				
			}
		}
	}	
}
