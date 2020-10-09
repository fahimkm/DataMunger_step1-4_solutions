package com.stackroute.datamunger.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

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

	@SuppressWarnings("deprecation")
	@Test
	public void testRetrieveHeader() throws IOException {
		Header result = reader.getHeader();

		assertEquals(new String[] { "id", "season", "city", "date", "team1", "team2", "toss_winner", "toss_decision",
				"result", "dl_applied", "winner", "win_by_runs", "win_by_wickets", "player_of_match", "venue",
				"umpire1", "umpire2", "umpire3" }, result.getHeaders());
		display("successRetrieveHeaderTestCase", Arrays.toString(result.getHeaders()));
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

		assertEquals(new String[] { "java.lang.Integer", "java.lang.Integer", "java.lang.String", "java.util.Date",
				"java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.Integer", "java.lang.String", "java.lang.Integer", "java.lang.Integer", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String", "java.lang.Object" },
				result.getDataTypes());
		display("successRetrieveDataTypesTestCase", Arrays.toString(result.getDataTypes()));
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

	}

	@Test
	public void testNotNullHeader() throws IOException {
		Header result = reader.getHeader();
		assertNotNull(result);
		display("notNUllHeaderTestCase", Arrays.toString(result.getHeaders()));
	}

	@Test
	public void testNotNullDataTypes() throws IOException {
		DataTypeDefinitions result = reader.getColumnType();
		assertNotNull(result);
		display("notNUllDataTypesTestCase", Arrays.toString(result.getDataTypes()));
	}

	private void display(String testCaseName, String result) {
		System.out.println(testCaseName);
		System.out.println("----------------------------------------------");
		System.out.println(result);
	}

}