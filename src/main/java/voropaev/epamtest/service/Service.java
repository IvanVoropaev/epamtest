package voropaev.epamtest.service;

import java.util.List;

import voropaev.epamtest.entity.Pair;

public interface Service {
	public List<Pair> getPairsList();
	public void savePairsMap();
	public void addPair(Pair pair);
	public void deletePair(String name);
	public List<Pair> sortByName(boolean up);
	public List<Pair> sortByValue(boolean up);
}
