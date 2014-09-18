package voropaev.epamtest.dao;

import java.util.List;

public interface DataOperator {
	public List<String> readData();
	public void savePair(String string);
	public void deletePair(int number);
}
