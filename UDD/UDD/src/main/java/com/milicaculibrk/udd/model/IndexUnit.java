package com.milicaculibrk.udd.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = IndexUnit.INDEX_NAME, shards = 1, replicas = 0)
public class IndexUnit {

    public static final String INDEX_NAME = "book";

    @Id
    @Field(type = FieldType.Keyword)
    private String filename;

    @Field(type = FieldType.Text, analyzer = "serbian", searchAnalyzer = "serbian")
    private String title;

    @Field(type = FieldType.Text, analyzer = "serbian", searchAnalyzer = "serbian")
    private String writer;

    @Field(type = FieldType.Text, analyzer = "serbian", searchAnalyzer = "serbian")
    private String genres;

    @Field(type = FieldType.Text, analyzer = "serbian", searchAnalyzer = "serbian")
    private String keywords;

    @Field(type = FieldType.Text, analyzer = "serbian", searchAnalyzer = "serbian")
    private String text;

    public IndexUnit() {
    }

    public IndexUnit(String filename, String title, String writer, String genres, String keywords, String text) {
        this.filename = filename;
        this.title = title;
        this.writer = writer;
        this.genres = genres;
        this.keywords = keywords;
        this.text = text;
    }

    public static String getIndexName() {
        return INDEX_NAME;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
