package voropaev.epamtest.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataOperatorImpl implements DataOperator {
	
	private String path;
	
	public DataOperatorImpl(String path) {
		this.path = path;
	}

	@Override
	public List<String> readData() {
		List<String> list = new ArrayList<String>();
		Scanner in = null;
		
		try {
			in = new Scanner(new File(path));	
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			System.out.println("Системе не удается найти указанный путь.");
		}
		
		while(in.hasNextLine())
			list.add(in.nextLine());
		
		in.close();
		return list;
	}

	@Override
	public void savePair(String string) {
		try {
			FileWriter sw = new FileWriter(path, true);
			sw.write(string + "\n");
			sw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deletePair(int number) {
		List<String> list = readData();
		list.remove(number);
		list.add(number, "");
		PrintWriter out;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(path)));
			for(String s : list) {
				out.println(s);
				out.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
