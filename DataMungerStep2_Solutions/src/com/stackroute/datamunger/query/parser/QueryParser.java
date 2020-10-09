package com.stackroute.datamunger.query.parser;

import java.util.ArrayList;
import java.util.List;

/*There are total 4 DataMungerTest file:
 * 
 * 1)DataMungerTestTask1.java file is for testing following 4 methods
 * a)getBaseQuery()  b)getFileName()  c)getOrderByClause()  d)getGroupByFields()
 * 
 * Once you implement the above 4 methods,run DataMungerTestTask1.java
 * 
 * 2)DataMungerTestTask2.java file is for testing following 2 methods
 * a)getFields() b) getAggregateFunctions()
 * 
 * Once you implement the above 2 methods,run DataMungerTestTask2.java
 * 
 * 3)DataMungerTestTask3.java file is for testing following 2 methods
 * a)getRestrictions()  b)getLogicalOperators()
 * 
 * Once you implement the above 2 methods,run DataMungerTestTask3.java
 * 
 * Once you implement all the methods run DataMungerTest.java.This test case consist of all
 * the test cases together.
 */

public class QueryParser {

	private QueryParameter queryParameter = new QueryParameter();

	/*
	 * This method will parse the queryString and will return the object of
	 * QueryParameter class
	 */
	public QueryParameter parseQuery(String queryString) {
		queryParameter.setFileName(extractFileName(queryString));
		queryParameter.setBaseQuery(extractBaseQuery(queryString));
		queryParameter.setOrderByFields(extractOrderByFields(queryString));
		queryParameter.setGroupByFields(extractGroupByFields(queryString));
		queryParameter.setFields(extractFields(queryString));
		queryParameter.setLogicalOperators(extractLogicalOperators(queryString));
		queryParameter.setAggregateFunctions(extractAggregate(queryString));
		queryParameter.setRestrictions(getRestrictions(queryString));
		return queryParameter;
	}

	/*
	 * Extract the name of the file from the query. File name can be found after the
	 * "from" clause.
	 */
	
	public String extractFileName(String queryString) {
		queryString = queryString.toLowerCase();
		String file = queryString;
		int start = file.indexOf("from");
		int end = file.indexOf("csv");
		String filename = file.substring(start + 5, end + 3);
		return queryParameter.setFileName(filename);
	}

	/*
	 * 
	 * Extract the baseQuery from the query.This method is used to extract the
	 * baseQuery from the query string. BaseQuery contains from the beginning of the
	 * query till the where clause
	 */
	
	public String extractBaseQuery(String queryString) {
		String basequery1 = "";
		queryString = queryString.toLowerCase();
		if (queryString.contains("where")) {
			String[] baseQuery = queryString.split(" where");
			basequery1 = baseQuery[0];
		} else if (queryString.contains("group by")) {
			String[] baseQuery = queryString.split(" group by");
			basequery1 = basequery1 +baseQuery[0];
		} else if (queryString.contains("order by")) {
			String[] baseQuery = queryString.split(" order by");
			basequery1 = basequery1 + baseQuery[0];
		}
		return basequery1;
	}

	/*
	 * extract the order by fields from the query string. Please note that we will
	 * need to extract the field(s) after "order by" clause in the query, if at all
	 * the order by clause exists. For eg: select city,winner,team1,team2 from
	 * data/ipl.csv order by city from the query mentioned above, we need to extract
	 * "city". Please note that we can have more than one order by fields.
	 */
	public List<String> extractOrderByFields(String queryString) {
		List<String> orderByFields = null;
		String[] temp1;
		if (queryString.contains("order by")) {
			orderByFields = new ArrayList<String>();
			temp1 = queryString.trim().split(" order by ");
			String[] orderByArray = temp1[1].trim().split(",");
			for (int i = 0; i < orderByArray.length; i++) {
				orderByFields.add(orderByArray[i]);
			}
			}
		else {
			return null;
		}
		return orderByFields;

	}

	/*
	 * Extract the group by fields from the query string. Please note that we will
	 * need to extract the field(s) after "group by" clause in the query, if at all
	 * the group by clause exists. For eg: select city,max(win_by_runs) from
	 * data/ipl.csv group by city from the query mentioned above, we need to extract
	 * "city". Please note that we can have more than one group by fields.
	 */
	public List<String> extractGroupByFields(String queryString) {
		String[] temp1;
		String[] groupByfields;
		List<String> groupByFields = null;

		if (queryString.contains("group by")) {
			groupByFields = new ArrayList<String>();
			temp1 = queryString.trim().split(" group by ");
			if (temp1[1].contains("order by")) {
				final String[] groupOrderBySplit = temp1[1].trim().split(" order by");
				groupByfields = groupOrderBySplit[0].trim().split(",");
					for (int i = 0; i < groupByfields.length; i++) {
						groupByFields.add(groupByfields[i]);
					}
			}
			else {
				groupByfields = temp1[1].trim().split(",");
				for (int i = 0; i < groupByfields.length; i++) {
					groupByFields.add(groupByfields[i]);
				}
			}
					
					}
		return groupByFields;
				}
		

	/*
	 * Extract the selected fields from the query string. Please note that we will
	 * need to extract the field(s) after "select" clause followed by a space from
	 * the query string. For eg: select city,win_by_runs from data/ipl.csv from the
	 * query mentioned above, we need to extract "city" and "win_by_runs". Please
	 * note that we might have a field containing name "from_date" or "from_hrs".
	 * Hence, consider this while parsing.
	 */
	public List<String> extractFields(String queryString) {
		final List<String> field = new ArrayList<String>();
		final String file1 = queryString;
		final int start = file1.indexOf("select ");
		final int end = file1.indexOf(" from");
		String filename1 = file1.substring(start + 7, end);
		final String[] fields = filename1.split(",");
		for (int i = 0; i < fields.length; i++) {
			field.add(fields[i]);
		}
		return field;
	}

	/*
	 * Extract the conditions from the query string(if exists). for each condition,
	 * we need to capture the following: 1. Name of field 2. condition 3. value
	 * 
	 * For eg: select city,winner,team1,team2,player_of_match from data/ipl.csv
	 * where season >= 2008 or toss_decision != bat
	 * 
	 * here, for the first condition, "season>=2008" we need to capture: 1. Name of
	 * field: season 2. condition: >= 3. value: 2008
	 * 
	 * the query might contain multiple conditions separated by OR/AND operators.
	 * Please consider this while parsing the conditions.
	 * 
	 */
	public ArrayList<Restriction> getRestrictions(String queryString) {
        String query = queryString;
        String[] conditions = null;
        ArrayList<Restriction> restrictions = null;
        if(query.contains("where")) {
            String conditionQuery = query.split("where|group by|order by")[1].trim();
            conditions = conditionQuery.split(" and | or ");
        
            restrictions = new ArrayList<Restriction>();
            
            for(int i = 0; i < conditions.length; i++) {
                if(conditions[i].contains("'")) {
                    String temp = conditions[i].split(" ")[0
                                                           ];
                    String restriction[] = conditions[i].split("'");
                    Restriction ris1 = new Restriction(temp.trim(),restriction[1].trim(),restriction[0].trim().split(" ")[1]);
                    restrictions.add(ris1);
                }
                else {
                    String restriction[] = conditions[i].split(" ");
                    Restriction r = new Restriction(restriction[0].trim(),restriction[2].trim(),restriction[1].trim());
                    restrictions.add(r);
                }
            }
        }
        return restrictions;
    }

	/*
	 * Extract the logical operators(AND/OR) from the query, if at all it is
	 * present. For eg: select city,winner,team1,team2,player_of_match from
	 * data/ipl.csv where season >= 2008 or toss_decision != bat and city =
	 * bangalore
	 * 
	 * The query mentioned above in the example should return a List of Strings
	 * containing [or,and]
	 */

	public List<String> extractLogicalOperators(String queryString) {
		List<String> logical = null;
		String[] query = queryString.split(" ");
		String logic = "";
		String[] temp;
		if (queryString.contains("where ")) {
			logical = new ArrayList<String>();
			for (int i = 0; i < query.length; i++) {
				if (query[i].matches("and|or|not")) {

					logic += query[i] + " ";
				}
			}
			temp = logic.toString().split(" ");
			for (int i = 0; i < temp.length; i++) {
				logical.add(temp[i]);
			}
		}

		return logical;

	}

	/*
	 * Extract the aggregate functions from the query. The presence of the aggregate
	 * functions can determined if we have either "min" or "max" or "sum" or "count"
	 * or "avg" followed by opening braces"(" after "select" clause in the query
	 * string. in case it is present, then we will have to extract the same. For
	 * each aggregate functions, we need to know the following: 1. type of aggregate
	 * function(min/max/count/sum/avg) 2. field on which the aggregate function is
	 * being applied.
	 * 
	 * Please note that more than one aggregate function can be present in a query.
	 * 
	 * 
	 */
	public List<AggregateFunction> extractAggregate(String queryString) {
		queryString = queryString.toLowerCase();
		List<AggregateFunction> aggregate = new ArrayList<AggregateFunction>();
		int start = queryString.indexOf("select");
		int end = queryString.indexOf(" from");
		String query = queryString.substring(start + 7, end);
		String[] aggregateArray = null;
		aggregateArray = query.split(",");
		for (int i = 0; i < aggregateArray.length; i++) {
			if (aggregateArray[i].startsWith("max(") && aggregateArray[i].endsWith(")")
					|| aggregateArray[i].startsWith("min(") && aggregateArray[i].endsWith(")")
					|| aggregateArray[i].startsWith("avg(") && aggregateArray[i].endsWith(")")
					|| aggregateArray[i].startsWith("sum") && aggregateArray[i].endsWith(")")) {
				aggregate.add(new AggregateFunction(aggregateArray[i].substring(4, aggregateArray[i].length() - 1),
						aggregateArray[i].substring(0, 3)));
			} else if (aggregateArray[i].startsWith("count(") && aggregateArray[i].endsWith(")")) {
				aggregate.add(new AggregateFunction(aggregateArray[i].substring(6, aggregateArray[i].length() - 1),
						aggregateArray[i].substring(0, 5)));
			}
			
		}
		return aggregate;

	}
	
}