package voropaev.epamtest.service;

import java.util.List;
import java.util.Map;

import voropaev.epamtest.entity.Pair;

public interface Service {
	public List<Pair> getPairsList();
	public void savePairsMap();
	public void addPair(String name, String value);
	public void deletePair(String name);
	public Map<String, String> sortByName(String name, boolean updown);
	public Map<String, String> sortByValue(String value, boolean updown);
}
