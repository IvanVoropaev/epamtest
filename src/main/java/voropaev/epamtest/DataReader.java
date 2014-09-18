package voropaev.epamtest;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class DataReader {
	
	public List<File> getFileList(String path) {
		File myFolder = new File(path);
		File[] files = myFolder.listFiles();
		return Arrays.asList(files);
	}
	
	public void printDir(String path) {
		List<File> lf = getFileList(path);
		for(File f : lf)
			System.out.println(f.getName());
	}
}
