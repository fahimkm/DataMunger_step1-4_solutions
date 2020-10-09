package com.stackroute.datamunger.query.parser;

/* This class is used for storing name of field, aggregate function for 
 * each aggregate function
 * generate getter and setter for this class,
 * Also override toString method
 * */

public class AggregateFunction {
	private String fieldName;
	private String aggregateFunction;
	// Write logic for constructor
	public AggregateFunction(String field, String function) {
		fieldName = field;
		aggregateFunction = function;
		

	}
	public String getAggregateFunction() {
		return fieldName+" "+aggregateFunction;
		
	}
	public void setAggregateFunction(String fieldName, String aggregateFunction) {
		this.fieldName = fieldName;
		this.aggregateFunction = aggregateFunction;
	}
	
	public String  toString() {
		return fieldName+" "+aggregateFunction;
	}

}