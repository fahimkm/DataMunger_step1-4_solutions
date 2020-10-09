package com.stackroute.datamunger.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.stackroute.datamunger.query.DataTypeDefinitions;
import com.stackroute.datamunger.query.Header;

public class CsvQueryProcessor extends QueryProcessingEngine {
	
	String fileName;
	BufferedReader br = null;
	// Parameterized constructor to initialize filename
	public CsvQueryProcessor(String fileName) throws FileNotFoundException {
			this.fileName = fileName;
			br = new BufferedReader(new FileReader(fileName));
		}

	/*
	 * Implementation of getHeader() method. We will have to extract the headers
	 * from the first line of the file.
	 * Note: Return type of the method will be Header
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
	 * getDataRow() method will be used in the upcoming assignments
	 */
	
	@Override
	public void getDataRow() {
		
	}

	/*
	 * Implementation of getColumnType() method. To find out the data types, we will
	 * read the first line from the file and extract the field values from it. If a
	 * specific field value can be converted to Integer, the data type of that field
	 * will contain "java.lang.Integer", otherwise if it can be converted to Double,
	 * then the data type of that field will contain "java.lang.Double", otherwise,
	 * the field is to be treated as String. 
	 * Note: Return Type of the method will be DataTypeDefinitions
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
			line1 += " ,";
	        String[] data = line1.split(",");
	        for(int i=0;i < data.length ; i++) {
	        	if(data[i].matches("-?\\d+")){
	        		data[i] ="java.lang.Integer";
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
