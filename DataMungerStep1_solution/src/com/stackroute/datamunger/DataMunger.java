package com.stackroute.datamunger;

/*There are total 5 DataMungertest files:
 * 
 * 1)DataMungerTestTask1.java file is for testing following 3 methods
 * a)getSplitStrings()  b) getFileName()  c) getBaseQuery()
 * 
 * Once you implement the above 3 methods,run DataMungerTestTask1.java
 * 
 * 2)DataMungerTestTask2.java file is for testing following 3 methods
 * a)getFields() b) getConditionsPartQuery() c) getConditions()
 * 
 * Once you implement the above 3 methods,run DataMungerTestTask2.java
 * 
 * 3)DataMungerTestTask3.java file is for testing following 2 methods
 * a)getLogicalOperators() b) getOrderByFields()
 * 
 * Once you implement the above 2 methods,run DataMungerTestTask3.java
 * 
 * 4)DataMungerTestTask4.java file is for testing following 2 methods
 * a)getGroupByFields()  b) getAggregateFunctions()
 * 
 * Once you implement the above 2 methods,run DataMungerTestTask4.java
 * 
 * Once you implement all the methods run DataMungerTest.java.This test case consist of all
 * the test cases together.
 */

public class DataMunger {

	/*
	 * This method will split the query string based on space into an array of words
	 * and display it on console
	 */

	public String[] getSplitStrings(String queryString) {
		queryString = queryString.toLowerCase();
		String[] words = queryString.split(" ");
		
		return words;
	}

	/*
	 * Extract the name of the file from the query. File name can be found after a
	 * space after "from" clause. Note: ----- CSV file can contain a field that
	 * contains from as a part of the column name. For eg: from_date,from_hrs etc.
	 * 
	 * Please consider this while extracting the file name in this method.
	 */

	public String getFileName(String queryString) {
		queryString = queryString.toLowerCase();
		String[] words  = queryString.split(" ");
		String[] words2  = queryString.split("_");
		int location = 0,flag = 0 ; //to check if separator is " " or "_"
		String fileName;
		for(int i = 0 ; i< words.length;i++) {
			if(words[i].equals("from")) {
				location = i+1;
			}
		}
		for(int i = 0 ; i< words2.length;i++) {
			if(words2[i].equals("from")) {
				location = i+1;
				flag = 1;
				}
		}
		if(flag == 0) {
			fileName = words[location];
		}
		else {
			fileName = words2[location];
		}
		flag = 0 ; 
		return fileName ;
	}

	/*
	 * This method is used to extract the baseQuery from the query string. BaseQuery
	 * contains from the beginning of the query till the where clause
	 * 
	 * Note: ------- 
	 * 1. The query might not contain where clause but contain order
	 * by or group by clause
	 *  2. The query might not contain where, order by or group
	 * by clause
	 *  3. The query might not contain where, but can contain both group by
	 * and order by clause
	 */
	
	public String getBaseQuery(String queryString) {
		queryString = queryString.toLowerCase();
		int end = 0;
		if ( queryString.toLowerCase().indexOf("where") != -1 ) { //check if string contains where 
			end = queryString.indexOf("where") - 1; 
		}
		else if ( queryString.toLowerCase().indexOf("order") != -1 ) { //check if string contains order by 
			end = queryString.indexOf("order") - 1;
		}
		else if ( queryString.toLowerCase().indexOf("group") != -1 ) { //check if string contains group by 
			end = queryString.indexOf("group") - 1;
		}
		String baseQuery = queryString.substring(0,end);
		return baseQuery;
		
	}

	/*
	 * This method will extract the fields to be selected from the query string. The
	 * query string can have multiple fields separated by comma. The extracted
	 * fields will be stored in a String array which is to be printed in console as
	 * well as to be returned by the method
	 * 
	 * Note: 1. The field name or value in the condition can contain keywords
	 * as a substring. For eg: from_city,job_order_no,group_no etc. 2. The field
	 * name can contain '*'
	 * 
	 */
	
	public String[] getFields(String queryString) {
		queryString = queryString.toLowerCase();
		String[] fields1 = queryString.split(" ");
		String[] fields = fields1[1].split(",");
		System.out.println(fields[0]);
		return fields;
	}

	/*
	 * This method is used to extract the conditions part from the query string. The
	 * conditions part contains starting from where keyword till the next keyword,
	 * which is either group by or order by clause. In case of absence of both group
	 * by and order by clause, it will contain till the end of the query string.
	 * Note:  1. The field name or value in the condition can contain keywords
	 * as a substring. For eg: from_city,job_order_no,group_no etc. 2. The query
	 * might not contain where clause at all.
	 */
	
	public String getConditionsPartQuery(String queryString) {
		
		queryString = queryString.toLowerCase();
		int end = queryString.length();
		int start = 0;
		if(!queryString.contains("where"))
        {
            return null;
        }
		if ( queryString.toLowerCase().indexOf("where") != -1 ) {
			start = queryString.indexOf("where") + 6; 
			
		}
		if ( queryString.toLowerCase().indexOf("order by") != -1) { 
			end = queryString.indexOf("order by") - 1 ;
		}else if ( queryString.toLowerCase().indexOf("group by") != -1) { 
			end = queryString.indexOf("group by") - 1 ;
		}
		String conditionsPartQuery = queryString.substring(start,end);
		return conditionsPartQuery;
	}

	/*
	 * This method will extract condition(s) from the query string. The query can
	 * contain one or multiple conditions. In case of multiple conditions, the
	 * conditions will be separated by AND/OR keywords. for eg: Input: select
	 * city,winner,player_match from ipl.csv where season > 2014 and city
	 * ='Bangalore'
	 * 
	 * This method will return a string array ["season > 2014","city ='bangalore'"]
	 * and print the array
	 * 
	 * Note: ----- 1. The field name or value in the condition can contain keywords
	 * as a substring. For eg: from_city,job_order_no,group_no etc. 2. The query
	 * might not contain where clause at all.
	 */

	public String[] getConditions(String queryString) {	
		queryString = queryString.toLowerCase();
		int end = queryString.length();
		int start = 0;
		if(!queryString.contains("where"))
        {
            return null;
        }
		if ( queryString.toLowerCase().indexOf("where") != -1 ) {
			start = queryString.indexOf("where") + 6; 
			
		}
		if ( queryString.toLowerCase().indexOf("order by") != -1) { 
			end = queryString.indexOf("order by") - 1 ;
		}else if ( queryString.toLowerCase().indexOf("group") != -1) { 
			end = queryString.indexOf("group by") - 1 ;
		}
		String conditionsPartQuery = queryString.substring(start,end);
		String[] conditions  = conditionsPartQuery.split(" and | or ");
		for(String i:conditions) {
			System.out.println(i);
		}
		return conditions;
	}

	/*
	 * This method will extract logical operators(AND/OR) from the query string. The
	 * extracted logical operators will be stored in a String array which will be
	 * returned by the method and the same will be printed Note:  1. AND/OR
	 * keyword will exist in the query only if where conditions exists and it
	 * contains multiple conditions. 2. AND/OR can exist as a substring in the
	 * conditions as well. For eg: name='Alexander',color='Red' etc. Please consider
	 * these as well when extracting the logical operators.
	 * 
	 */

	public String[] getLogicalOperators(String queryString) {
		queryString = queryString.toLowerCase();
		String S1 = "";
		if(!queryString.contains("where"))
        {
            return null;
        }
		String[] words = queryString.split(" ");
		for(String i:words) {
			if(i.equals("and")) {
				S1=S1.concat("and ");  
			}
			if(i.equals("or")) {
				S1=S1.concat("or ");  
			}
		}
		String[] logicalOperators = S1.split(" ");
		return logicalOperators;
	}

	/*
	 * This method extracts the order by fields from the query string. Note: 
	 * 1. The query string can contain more than one order by fields. 2. The query
	 * string might not contain order by clause at all. 3. The field names,condition
	 * values might contain "order" as a substring. For eg:order_number,job_order
	 * Consider this while extracting the order by fields
	 */

	public String[] getOrderByFields(String queryString) {
		queryString = queryString.toLowerCase();
		int start = 0;
		int end = queryString.length();
		if ( queryString.toLowerCase().indexOf("order by") != -1) { 
			start = queryString.indexOf("order by")+9 ;
			
		}
		else {
			return null;
		}
		
		String orderByFields1 = queryString.substring(start,end);
		String[] orderByFields = orderByFields1.split(" ");
		return orderByFields;
	}

	/*
	 * This method extracts the group by fields from the query string. Note:
	 * 1. The query string can contain more than one group by fields. 2. The query
	 * string might not contain group by clause at all. 3. The field names,condition
	 * values might contain "group" as a substring. For eg: newsgroup_name
	 * 
	 * Consider this while extracting the group by fields
	 */

	public String[] getGroupByFields(String queryString) {
		queryString = queryString.toLowerCase();
		int start = 0;
		int end = queryString.length();
		if ( queryString.indexOf("group by") != -1) { 
			start = queryString.indexOf("group by")+9 ;
			
		}
		else {
			return null;
		}
		
		String groupByFields1 = queryString.substring(start,end);
		String[] groupByFields = groupByFields1.split(" ");
		return groupByFields;

	}

	/*
	 * This method extracts the aggregate functions from the query string. Note:
	 *  1. aggregate functions will start with "sum"/"count"/"min"/"max"/"avg"
	 * followed by "(" 2. The field names might
	 * contain"sum"/"count"/"min"/"max"/"avg" as a substring. For eg:
	 * account_number,consumed_qty,nominee_name
	 * 
	 * Consider this while extracting the aggregate functions
	 */

	public String[] getAggregateFunctions(String queryString) {
		queryString = queryString.toLowerCase();
		int start =0;
		int end = 0;
		String aggregates = "";
		if ( queryString.toLowerCase().indexOf("sum") != -1 || queryString.toLowerCase().indexOf("min") != -1||queryString.toLowerCase().indexOf("max") != -1||queryString.toLowerCase().indexOf("avg") != -1|| queryString.toLowerCase().indexOf("count") != -1) { 
			String[] S1 = queryString.split(",");
			for(int i = 0 ; i < S1.length;i++) {
				if ( S1[i].indexOf("sum") != -1) { 
					start = S1[i].indexOf("sum") ;
					end = S1[i].indexOf(")") + 1;
					aggregates = aggregates +" "+ S1[i].substring(start,end);
				}
				if ( S1[i].indexOf("min") != -1) { 
					start = S1[i].indexOf("min") ;
					end = S1[i].indexOf(")") + 1;
					aggregates = aggregates +" "+ S1[i].substring(start,end);
				}
				if ( S1[i].indexOf("max") != -1) { 
					start = S1[i].indexOf("max") ;
					end = S1[i].indexOf(")") + 1;
					aggregates = aggregates +" "+ S1[i].substring(start,end);
				}
				if ( S1[i].indexOf("count") != -1) { 
					start = S1[i].indexOf("count") ;
					end = S1[i].indexOf(")") + 1;
					aggregates = aggregates +" "+ S1[i].substring(start,end);
				}
				if ( S1[i].indexOf("avg") != -1) { 
					start = S1[i].indexOf("avg") ;
					end = S1[i].indexOf(")") + 1;
					aggregates = aggregates +" "+ S1[i].substring(start,end);
				}
			}
			aggregates = aggregates.trim();
			String[] aggregate = aggregates.split(" ");
			return aggregate;
		}
		else {
			return null;
		}
	}
}

