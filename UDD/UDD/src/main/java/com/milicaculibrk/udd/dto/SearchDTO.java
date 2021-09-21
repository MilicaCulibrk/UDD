package com.milicaculibrk.udd.dto;

public class SearchDTO {

    private String title;
    private String operatorTitle;
    private String writer;
    private String operatorWriter;
    private String genres;
    private String operatorGenres;
    private String keyWords;
    private String operatorKeyWords;
    private String text;
    private String operatorText;
    private String searchType;

    public SearchDTO() {
    }

    public SearchDTO(String title, String operatorTitle, String writer, String operatorWriter, String genres, String operatorGenres, String keyWords, String operatorKeyWords, String text, String operatorText, String searchType) {
        this.title = title;
        this.operatorTitle = operatorTitle;
        this.writer = writer;
        this.operatorWriter = operatorWriter;
        this.genres = genres;
        this.operatorGenres = operatorGenres;
        this.keyWords = keyWords;
        this.operatorKeyWords = operatorKeyWords;
        this.text = text;
        this.operatorText = operatorText;
        this.searchType = searchType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOperatorTitle() {
        return operatorTitle;
    }

    public void setOperatorTitle(String operatorTitle) {
        this.operatorTitle = operatorTitle;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getOperatorWriter() {
        return operatorWriter;
    }

    public void setOperatorWriter(String operatorWriter) {
        this.operatorWriter = operatorWriter;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getOperatorGenres() {
        return operatorGenres;
    }

    public void setOperatorGenres(String operatorGenres) {
        this.operatorGenres = operatorGenres;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getOperatorKeyWords() {
        return operatorKeyWords;
    }

    public void setOperatorKeyWords(String operatorKeyWords) {
        this.operatorKeyWords = operatorKeyWords;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOperatorText() {
        return operatorText;
    }

    public void setOperatorText(String operatorText) {
        this.operatorText = operatorText;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }
}
