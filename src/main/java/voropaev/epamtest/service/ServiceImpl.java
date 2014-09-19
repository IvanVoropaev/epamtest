package voropaev.epamtest.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import voropaev.epamtest.dao.DataOperatorImpl;
import voropaev.epamtest.entity.Pair;

public class ServiceImpl implements Service {
	
	private DataOperatorImpl dao;
	
	public ServiceImpl(String path) {
		this.dao = new DataOperatorImpl(path);
	}

	public DataOperatorImpl getDao() {
		return dao;
	}

	public void setDao(DataOperatorImpl dao) {
		this.dao = dao;
	}

	@Override
	public List<Pair> getPairsList() {
		List<String> list = dao.readData();
		List<Pair> listOfPairs = new ArrayList<Pair>();
		for(String s : list) {
			StringTokenizer st = new StringTokenizer(s, "=");
			int i = 0;
			Pair pair = new Pair(); 
			while(st.hasMoreTokens()) {
				if (i == 0) pair.setName(st.nextToken().trim());
				if (i == 1) pair.setValue(st.nextToken().trim());
				i++;	
			}	
			//System.out.println(key + " " + value);
			if(pair.getName()==null)
				listOfPairs.add(new Pair("null", "null"));
			else if(nameValid(pair.getName()))
				listOfPairs.add(pair);
			else
				listOfPairs.add(new Pair("error", "error"));
		}
		return listOfPairs;
	}

	@Override
	public void savePairsMap() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void addPair(Pair pair) {
		if(nameValid(pair.getName()))
			dao.savePair(pair.getName() + " = " + pair.getValue());
	}

	@Override
	public void deletePair(String name) {
		List<Pair> list = getPairsList();
		boolean found = false;
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getName().equals(name)) {
				dao.deletePair(i);
				found = true;
				System.out.println("Pair " + list.get(i).toString() + " has been deleted.");
			}								
		}
		if(!found) 
			System.out.println("Can't delete. Pair with name: '" + name + "' not found or name is invalid.");
	}

	@Override
	public List<Pair> sortByName(final boolean up) {
		List<Pair> pairs = getPairsList();
		Collections.sort(pairs, new Comparator<Pair>() {
			@Override
			public int compare(Pair o1, Pair o2) {
				if(up)
					return o1.getName().compareTo(o2.getName());
				else
					return -o1.getName().compareTo(o2.getName());
			}	
		});
		return pairs;
	}

	@Override
	public List<Pair> sortByValue(final boolean up) {
		List<Pair> pairs = getPairsList();
		Collections.sort(pairs, new Comparator<Pair>() {
			@Override
			public int compare(Pair o1, Pair o2) {
				if(up)
					return o1.getValue().compareTo(o2.getValue());
				else
					return -o1.getValue().compareTo(o2.getValue());
			}	
		});
		return pairs;
	}
	
	public static boolean nameValid(String string) {
		boolean valid = true;
		Pattern p = Pattern.compile("[\\W]");
		Matcher m = p.matcher(string);
		while(m.find()) {
			System.out.println("Name: '" + string +"' is invalid. Only letters, numbers and '_' symbol is avalable.");
			valid = false;
			break;
		}
		return valid;
	}

}
