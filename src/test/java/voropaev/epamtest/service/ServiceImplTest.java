package voropaev.epamtest.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
//import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	public void testGetPairsMap() {
		List<String> list = new ArrayList<String>();
		list.add("count = 100");
		list.add("Path = C:/Windows");
		list.add("&^%* = 25 dfd xvzVF");
		
		when(dao.readData()).thenReturn(list);
		List<Pair> listOfPairs = service.getPairsList();
		
		for(Pair pair : listOfPairs) {
			  String name = pair.getName();
			  String value = pair.getValue();
			  System.out.println(name + " => " + value);
		}
		
		assertEquals(listOfPairs.get(0), new Pair("count", "100"));
		assertEquals(listOfPairs.get(1), new Pair("Path", "C:/Windows"));
		assertEquals(listOfPairs.get(2), new Pair("&^%*", "25 dfd xvzVF"));
		//assertNull(map.get("&^%*"));
	}

	/*@Test
	public void testSavePairsMap() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddPair() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeletePair() {
		fail("Not yet implemented");
	}

	@Test
	public void testSortByName() {
		fail("Not yet implemented");
	}

	@Test
	public void testSortByValue() {
		fail("Not yet implemented");
	}*/
	
	@Test
	public void nameValid() {
		String str = "@#$%";
		assertTrue(service.nameValid(str));
	}

}
