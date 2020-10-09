package com.stackroute.datamunger.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.stackroute.datamunger.query.DataTypeDefinitions;
import com.stackroute.datamunger.query.Header;

public class CsvQueryProcessor extends QueryProcessingEngine {

	String fileName;
	BufferedReader br = null;

	/*
	 * parameterized constructor to initialize filename. As you are trying to
	 * perform file reading, hence you need to be ready to handle the IO Exceptions.
	 */
	public CsvQueryProcessor(String fileName) throws FileNotFoundException {
		this.fileName = fileName;
		br = new BufferedReader(new FileReader(fileName));
	}

	/*
	 * implementation of getHeader() method. We will have to extract the headers
	 * from the first line of the file.
	 */
	@Override
	public Header getHeader() throws IOException {
		FileReader readFile = null;
		String header1 = "";
		try {
			 readFile =new FileReader(fileName);
			 BufferedReader br = new BufferedReader(readFile);
			 String line = br.readLine();
			 header1 = header1 + line;
			 br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] headers = header1.split(",");
		Header header2 = new Header();
		header2.setHeaders(headers);
		return header2;
	}
	

	/**
	 * This method will be used in the upcoming assignments
	 */
	@Override
	public void getDataRow() {

	}

	/*
	 * implementation of getColumnType() method. To find out the data types, we will
	 * read the first line from the file and extract the field values from it. In
	 * the previous assignment, we have tried to convert a specific field value to
	 * Integer or Double. However, in this assignment, we are going to use Regular
	 * Expression to find the appropriate data type of a field. Integers: should
	 * contain only digits without decimal point Double: should contain digits as
	 * well as decimal point Date: Dates can be written in many formats in the CSV
	 * file. However, in this assignment,we will test for the following date
	 * formats('dd/mm/yyyy',
	 * 'mm/dd/yyyy','dd-mon-yy','dd-mon-yyyy','dd-month-yy','dd-month-yyyy','yyyy-mm-dd')
	 */
	@Override
	public DataTypeDefinitions getColumnType() throws IOException {

		FileReader readFile = null;
		String header1 = "";
		String line1 = "";
		try {
			readFile =new FileReader("data/ipl.csv");
			BufferedReader br = new BufferedReader(readFile);
			String line = br.readLine();
			line1 = line1+ br.readLine();
			header1 = header1 + line;
			br.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        String[] data = line1.split(",",18);
		// TODO Auto-generated method stub
	        for(int i=0;i < data.length ; i++) {
		// checking for Integer
	        	if(data[i].matches("[0-9]+")){
	        		data[i] = "java.lang.Integer";
	        	}
		// checking for floating point numbers
	        	else if(data[i].matches("[0-9]+.[0-9]+")) {
	        		data[i] = "java.lang.Float";
	        	}
		// checking for date format dd/mm/yyyy
	        	else if(data[i].matches("([0-3][0-9])/([0-1][0-9])/([0-9]{4})")) {
	        		data[i] = "java.util.Date";
	        	}
		// checking for date format mm/dd/yyyy
	        	else if(data[i].matches("([0-1][0-9])/([0-3][0-9])/([0-9]{4})")) {
	        		data[i] = "java.util.Date";
	        	}
		// checking for date format dd-mon-yy
	        	else if(data[i].matches("([0-3][0-9])-([a-z]{3})-([0-9]{2})")) {
	        		data[i] = "java.util.Date";
	        	}
		// checking for date format dd-mon-yyyy
	        	else if(data[i].matches("([0-3][0-9])-([a-z]{3})-([0-9]{2})")) {
	        		data[i] = "java.util.Date";
	        	}
		// checking for date format dd-month-yy
	        	else if(data[i].matches("([0-3][0-9])-([a-z]{5})-([0-9]{2})")) {
	        		data[i] = "java.util.Date";
	        	}
		// checking for date format dd-month-yyyy
	        	else if(data[i].matches("([0-3][0-9])-([a-z]{5})-([0-9]{4})")) {
	        		data[i] = "java.util.Date";
	        	}
		// checking for date format yyyy-mm-dd
	        	else if(data[i].matches("([0-9]{4})-([0-1][0-9])-([0-3][0-9])")) {
	        		data[i] = "java.util.Date";
	        	}
	        	else if(data[i].isEmpty()) {
	        		data[i]= "java.lang.Object";
	        	}
	        	else {
	        		data[i] = "java.lang.String";
	        	}
		
	}
	        DataTypeDefinitions data2 = new DataTypeDefinitions();
	        data2.setDataTypes(data);
			return data2; 
	}
}
	
