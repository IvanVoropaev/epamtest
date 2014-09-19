package voropaev.epamtest.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
//import static org.mockito.Mockito.*;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import voropaev.epamtest.dao.DataOperatorImpl;
import voropaev.epamtest.entity.Pair;

@RunWith(MockitoJUnitRunner.class)
public class ServiceImplTest {
	
	@Mock
	DataOperatorImpl dao;// = new DataOperatorImpl("path");
	
	@InjectMocks
	ServiceImpl service = new ServiceImpl("path");

	/*@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}*/

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetPairsMap() throws FileNotFoundException {
		List<String> list = new ArrayList<String>();
		list.add("count = 100");
		list.add("Path Windows");
		list.add("&^%* = 25 dfd xvzVF");
		list.add("name = 25 dfd xvzVF");
		list.add("");
		
		
		when(dao.readData()).thenReturn(list);
		List<Pair> listOfPairs = service.getPairsList();
		
		/*for(Pair pair : listOfPairs) 
			  System.out.println(pair.getName() + " => " + pair.getValue());*/		
		
		assertEquals(listOfPairs.get(0), new Pair("count", "100"));
		assertEquals(listOfPairs.get(1), new Pair("error", "error"));
		assertEquals(listOfPairs.get(2), new Pair("error", "error"));
		assertEquals(listOfPairs.get(3), new Pair("name", "25 dfd xvzVF"));
		assertEquals(listOfPairs.get(4), new Pair("null", "null"));
	}

	/*@Test
	public void testSavePairsMap() {
		fail("Not yet implemented");
	}*/

	@Test(expected = RuntimeException.class)
	public void testAddPair() {
		String pairStr = "pair";
		Pair pair = new Pair(pairStr, pairStr);
		doThrow(new RuntimeException()).when(dao).savePair(pair.getName() + " = " + pair.getValue());
		service.addPair(pair);
		//service.addPair(new Pair("pair", "pair"));
	}

	@Test(expected = RuntimeException.class)
	public void testDeletePair() throws FileNotFoundException {
		List<String> list = new ArrayList<String>();
		list.add("count = 100");
		list.add("Path Windows");
		list.add("&^%* = 25 dfd xvzVF");
		list.add("name = 25 dfd xvzVF");
		when(dao.readData()).thenReturn(list);
		service.deletePair("&^%");
		doThrow(new RuntimeException()).when(dao).deletePair(0);
		service.deletePair("count");
	}

	@Test
	public void testSortByName() throws FileNotFoundException {
		List<String> listStr = new ArrayList<String>();
		listStr.add("xcv = 12435");
		listStr.add("zaw = 152");
		listStr.add("count = 100");
		when(dao.readData()).thenReturn(listStr);
		
		List<Pair> list = service.getPairsList();
		
		List<Pair> sortedList = service.sortByName(true);
		assertEquals(sortedList.get(0), list.get(2));
		assertEquals(sortedList.get(1), list.get(0));
		assertEquals(sortedList.get(2), list.get(1));
		
		sortedList = service.sortByName(false);
		assertEquals(sortedList.get(0), list.get(1));
		assertEquals(sortedList.get(1), list.get(0));
		assertEquals(sortedList.get(2), list.get(2));
	}

	@Test
	public void testSortByValue() throws FileNotFoundException {
		List<String> listStr = new ArrayList<String>();
		listStr.add("xcv = x");
		listStr.add("zaw = z");
		listStr.add("count = a");
		when(dao.readData()).thenReturn(listStr);
		
		List<Pair> list = service.getPairsList();
		
		List<Pair> sortedList = service.sortByValue(true);
		assertEquals(sortedList.get(0), list.get(2));
		assertEquals(sortedList.get(1), list.get(0));
		assertEquals(sortedList.get(2), list.get(1));
		
		sortedList = service.sortByValue(false);
		assertEquals(sortedList.get(0), list.get(1));
		assertEquals(sortedList.get(1), list.get(0));
		assertEquals(sortedList.get(2), list.get(2));
	}
	
	@Test
	public void nameValid() {
		String str = "n@ame";
		assertTrue(!ServiceImpl.nameValid(str));
	}

}
