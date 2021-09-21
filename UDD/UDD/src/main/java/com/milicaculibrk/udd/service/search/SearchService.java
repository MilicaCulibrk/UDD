package com.milicaculibrk.udd.service.search;

import com.milicaculibrk.udd.dto.SearchDTO;
import com.milicaculibrk.udd.service.book.QueryBuilder;
import org.apache.lucene.queryparser.classic.ParseException;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    public  BoolQueryBuilder searchBook(SearchDTO searchDTO) throws ParseException {

        BoolQueryBuilder query = QueryBuilders.boolQuery();

        if (!searchDTO.getTitle().equals("")){
            org.elasticsearch.index.query.QueryBuilder title = QueryBuilder.buildQuery(searchDTO.getSearchType(),
                    //title su polja iz index unita, tu napisem koja polja pretrazujem
                    "title", searchDTO.getTitle().toLowerCase());

            query = checkOperator(searchDTO.getOperatorTitle(), query, title);
        }

        if (!searchDTO.getWriter().equals("")){
            org.elasticsearch.index.query.QueryBuilder writer = QueryBuilder.buildQuery(searchDTO.getSearchType(),
                    "writer", searchDTO.getWriter().toLowerCase());

            query = checkOperator(searchDTO.getOperatorWriter(), query, writer);

        }

        if (!searchDTO.getGenres().equals("")){
            org.elasticsearch.index.query.QueryBuilder genres = QueryBuilder.buildQuery(searchDTO.getSearchType(),
                    "genres", searchDTO.getGenres().toLowerCase());

            query = checkOperator(searchDTO.getOperatorGenres(), query, genres);

        }

        if (!searchDTO.getKeyWords().equals("")){
            org.elasticsearch.index.query.QueryBuilder keyWords = QueryBuilder.buildQuery(searchDTO.getSearchType(),
                    "keywords", searchDTO.getKeyWords().toLowerCase());

            query = checkOperator(searchDTO.getOperatorKeyWords(), query, keyWords);

        }

        //za text je drugacije, jer ima string kveri a ne bilder
        if (!searchDTO.getText().equals("")){
            org.elasticsearch.index.query.QueryBuilder text = QueryBuilders.queryStringQuery(searchDTO.getText());

            query = checkOperator(searchDTO.getOperatorText(), query, text);

        }

        return query;
    }

    private  BoolQueryBuilder checkOperator(String operator, BoolQueryBuilder query, org.elasticsearch.index.query.QueryBuilder queryBuilder){

        if (operator.equals("AND")) {
            query.must(queryBuilder);
        } else {
            query.should(queryBuilder);
        }

        return query;
    }

}
