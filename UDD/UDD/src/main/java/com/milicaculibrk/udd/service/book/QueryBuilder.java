package com.milicaculibrk.udd.service.book;


import org.apache.lucene.queryparser.classic.ParseException;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilders;

public class QueryBuilder {
	
	private static int maxEdits = 1;
	
	public static int getMaxEdits(){
		return maxEdits;
	}
	
	public static void setMaxEdits(int maxEdits){
		QueryBuilder.maxEdits = maxEdits;
	}
	
	public static org.elasticsearch.index.query.QueryBuilder buildQuery(String queryType, String field, String value) throws IllegalArgumentException, ParseException{
		String errorMessage = "";
		if(field == null || field.equals("")){
			errorMessage += "Field not specified";
		}
		if(value == null){
			if(!errorMessage.equals("")) errorMessage += "\n";
			errorMessage += "Value not specified";
		}
		if(!errorMessage.equals("")){
			throw new IllegalArgumentException(errorMessage);
		}

		org.elasticsearch.index.query.QueryBuilder retVal = null;
		if(queryType.equals("regular")){
			retVal = QueryBuilders.termQuery(field, value);
		}else{
			retVal = QueryBuilders.matchPhraseQuery(field, value);
		}
		
		return retVal;
	}

}
