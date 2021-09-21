package com.milicaculibrk.udd.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class UploadBookDTO {

    private String title;
    private String writer;
    private String[] genres;
    private String keywords;
    private List<MultipartFile> files;


    public UploadBookDTO() {
    }

    public UploadBookDTO(String title, String writer, String[] genres, String keywords, List<MultipartFile> files) {
        this.title = title;
        this.writer = writer;
        this.genres = genres;
        this.keywords = keywords;
        this.files = files;
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

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }
}
