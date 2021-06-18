package com.example.news;

public class Article {


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageurl() {
        return imagelink;
    }

    public void setImageurl(String imageurl) {
        this.imagelink = imagelink;
    }

    public String title;
    public String author;
    public String url;
    public String imagelink;
    public String extra;

    public Article(String title, String author,String imagelink, String url ,String extra) {
        this.title = title;
        this.author = author;
        this.url = url;
        this.imagelink = imagelink;
        this.extra = extra;
    }

    public Article(String extra){

        this.extra = extra;
    }

}
