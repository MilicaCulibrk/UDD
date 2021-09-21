package com.milicaculibrk.udd.service.book;


import com.milicaculibrk.udd.enums.SearchType;
import org.apache.lucene.queryparser.classic.ParseException;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilders;

//kopirali od njega, kad pravis kveri
public class QueryBuilder {
	
	private static int maxEdits = 1;
	
	public static int getMaxEdits(){
		return maxEdits;
	}
	
	public static void setMaxEdits(int maxEdits){
		QueryBuilder.maxEdits = maxEdits;
	}
	
	public static org.elasticsearch.index.query.QueryBuilder buildQuery(SearchType queryType, String field, String value) throws IllegalArgumentException, ParseException{
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

		//izbacili fazi jer radi kao phase
		org.elasticsearch.index.query.QueryBuilder retVal = null;
		if(queryType.equals(SearchType.regular)){
			retVal = QueryBuilders.termQuery(field, value);
		}else if(queryType.equals(SearchType.fuzzy)){
			retVal = QueryBuilders.fuzzyQuery(field, value).fuzziness(Fuzziness.fromEdits(maxEdits));
		}else{
			retVal = QueryBuilders.matchPhraseQuery(field, value);
		}
		
		return retVal;
	}

}
