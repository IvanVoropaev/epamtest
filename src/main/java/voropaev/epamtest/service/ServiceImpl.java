package voropaev.epamtest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import voropaev.epamtest.dao.DataOperatorImpl;
import voropaev.epamtest.entity.Pair;

public class ServiceImpl implements Service {
	
	private String path;
	private DataOperatorImpl dao;
	
	public ServiceImpl(String path) {
		this.path = path;
		this.dao = new DataOperatorImpl(path);
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
			if(nameValid(pair.getName()))
				listOfPairs.add(pair);
		}
		return listOfPairs;
	}

	@Override
	public void savePairsMap() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addPair(String name, String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePair(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, String> sortByName(String name, boolean updown) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> sortByValue(String value, boolean updown) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean nameValid(String string) {
		Pattern p = Pattern.compile("\\W");
		Matcher m = p.matcher(string);
		return m.matches();	
	}

}
