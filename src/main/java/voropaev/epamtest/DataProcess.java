package voropaev.epamtest;

import java.io.File;
import java.util.Map;

public interface DataProcess {
	public Map<String, String> getDataFromFile(File file);
	public void savePair(String name, String value);
	public void deletePair(String name);
	public void showSortedByName(String name);
	public void showSortedByValue(String value);
	public void showDataOnScreen();
}
