package voropaev.epamtest.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DataOperatorImplTest {
	
	private DataOperatorImpl dao;
	private String path;

	/*@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}*/

	@Before
	public void setUp() throws Exception {		
		path = "src/test/resources/testdata.txt";
		dao = new DataOperatorImpl(path);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReadData() {
		
		String string1 = "string 1 asdf";
		String string2 = "String 2 12elkmkvs";
		String string3 = "";
		
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path)));
			out.println(string1);
			out.println(string2);
			out.println(string3);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<String> list = dao.readData();
		System.out.println(list.size());
		assertTrue(list.size()==3);
		assertEquals(list.get(0), string1);
		assertEquals(list.get(1), string2);
		assertEquals(list.get(2), string3);
	}

	@Test
	public void testSavePair() {
		String newString = "newstring";
		List<String> list = dao.readData();
		int before = list.size();
		dao.savePair(newString);
		list = dao.readData();
		int after = list.size();
		assertEquals(before + 1, after);
	}

	@Test
	public void testDeletePair() {
		String newString = "newstring";
		dao.savePair(newString);
		List<String> list = dao.readData();
		String string = list.get(list.size() - 1);
		assertEquals(string, newString);
		dao.deletePair(list.size() - 1);
		list = dao.readData();
		assertEquals(list.get(list.size() - 1), "");
	}

}
