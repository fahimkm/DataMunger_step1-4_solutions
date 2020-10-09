package com.stackroute.datamunger.query.parser;
import java.util.List;

/* 
 * This class will contain the elements of the parsed Query String such as conditions,
 * logical operators,aggregate functions, file name, fields group by fields, order by
 * fields, Query Type
 * */

public class QueryParameter {
	private String fileName;
	private String queryString,baseQuery,QUERY_TYPE;
	private List<Restriction> restrictions , conditions;
	private List<String> fields,logicalOperators,orderByFields,groupByFields;
	private List<AggregateFunction> aggregateFunctions;
	public String getFileName() {
		return fileName;
	}
	public String setFileName(String fileName) {
		return this.fileName = fileName;
	}
	public String getQueryString() {
		return queryString;
	}
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	public String getBaseQuery() {
		return baseQuery;
	}
	public void setBaseQuery(String baseQuery) {
		this.baseQuery = baseQuery;
	}
	public String getQUERY_TYPE() {
		return QUERY_TYPE;
	}
	public void setQUERY_TYPE(String qUERY_TYPE) {
		QUERY_TYPE = qUERY_TYPE;
	}
	public List<Restriction> getRestrictions() {
		return restrictions;
	}
	public void setRestrictions(List<Restriction> restrictions) {
		this.restrictions = restrictions;
	}
	public List<String> getFields() {
		return fields;
	}
	public void setFields(List<String> fields) {
		this.fields = fields;
	}
	public List<String> getLogicalOperators() {
		return logicalOperators;
	}
	public void setLogicalOperators(List<String> logicalOperators) {
		this.logicalOperators = logicalOperators;
	}
	public List<String> getOrderByFields() {
		return orderByFields;
	}
	public void setOrderByFields(List<String> orderByFields) {
		this.orderByFields = orderByFields;
	}
	public List<String> getGroupByFields() {
		return groupByFields;
	}
	public void setGroupByFields(List<String> groupByFields) {
		this.groupByFields = groupByFields;
	}
	public List<AggregateFunction> getAggregateFunctions() {
		return aggregateFunctions;
	}
	public void setAggregateFunctions(List<AggregateFunction> aggregateFunctions) {
		this.aggregateFunctions = aggregateFunctions;
	}
	public List<Restriction> getConditions() {
		return conditions;
	}
	public void setConditions(List<Restriction> conditions) {
		this.conditions = conditions;
	}
	
	
}