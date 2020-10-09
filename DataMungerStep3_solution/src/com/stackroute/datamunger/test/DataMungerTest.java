package com.stackroute.datamunger.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.stackroute.datamunger.query.DataTypeDefinitions;
import com.stackroute.datamunger.query.Header;
import com.stackroute.datamunger.reader.CsvQueryProcessor;

public class DataMungerTest {

	private static CsvQueryProcessor reader;

	@BeforeClass
	public static void init() throws FileNotFoundException {
		reader = new CsvQueryProcessor("data/ipl.csv");
	}

	@AfterClass
	public static void close() throws FileNotFoundException {
		reader = null;
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testRetrieveHeader() throws IOException {
		Header result = reader.getHeader();

		assertEquals("Headers are not matching with the included ipl.csv file of data folder in number or sequence",
				new String[] { "id", "season", "city", "date", "team1", "team2", "toss_winner", "toss_decision",
						"result", "dl_applied", "winner", "win_by_runs", "win_by_wickets", "player_of_match", "venue",
						"umpire1", "umpire2", "umpire3" },
				result.getHeaders());
		display("successRetrieveHeaderTestCase", result.toString());
	}

	
	@Test
	public void testRetrieveHeaderFailure() throws IOException {
		Header result = reader.getHeader();

		assertNotNull("Headers are not matching with the included ipl.csv file of data folder in number or sequence",
				result.getHeaders());
		display("successRetrieveHeaderTestCase", result.toString());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testRetrieveDataTypes() throws IOException {
		DataTypeDefinitions result = reader.getColumnType();

		assertEquals(
				"DataTypes are not matching with the included ipl.csv file of data folder in number or sequence, possibly null values could be the reason",
				new String[] { "java.lang.Integer", "java.lang.Integer", "java.lang.String", "java.lang.String",
						"java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String",
						"java.lang.String", "java.lang.Integer", "java.lang.String", "java.lang.Integer",
						"java.lang.Integer", "java.lang.String", "java.lang.String", "java.lang.String",
						"java.lang.String", "java.lang.String" },
				result.getDataTypes());
		display("successRetrieveDataTypesTestCase", result.toString());
	}

	@Test
	public void testRetrieveDataTypesFailure() throws IOException {
		DataTypeDefinitions result = reader.getColumnType();

		assertNotNull(
				"DataTypes are not matching with the included ipl.csv file of data folder in number or sequence, possibly null values could be the reason",
				result.getDataTypes());
		display("successRetrieveDataTypesTestCase", result.toString());
	}

	@Test(expected = FileNotFoundException.class)
	public void testFileNotFound() throws IOException {
		reader = new CsvQueryProcessor("data/ipl2.csv");
		@SuppressWarnings("unused")
		Header result = reader.getHeader();

	}

	@Test
	public void testNotNullHeader() throws IOException {
		Header result = reader.getHeader();
		assertNotNull(
				"getHeader() method of CsvQueryProcessor class is returning null. Please note that the first line of the file contains headers separated by comma",
				result);
		display("notNUllHeaderTestCase", result.toString());
	}

	@Test
	public void testNotNullDataTypes() throws IOException {
		DataTypeDefinitions result = reader.getColumnType();
		assertNotNull(
				"getColumnType() method CsvQueryProcessor class is returning null. Please note that we will have find out the data type of all columns and add it to an array which is the member of DataTypeDefinitions class",
				result);
		display("notNullDataTypesTestCase", result.toString());
	}

	private void display(String testCaseName, String result) {
		System.out.println(testCaseName);
		System.out.println("----------------------------------------------");
		System.out.println(result);
	}

}