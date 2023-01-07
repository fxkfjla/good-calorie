package com.example.goodcalorie.Models;

import java.io.Serializable;

// serializable to convert object to json to pass via intent.putExtra()
public class Book implements Serializable
{
    public Book(String name, String author, int pages)
    {
        this.title = name;
        this.author = author;
        this.pages = pages;
    }

    public Book(int id, String name, String author, int pages)
    {
        this.id = id;
        this.title = name;
        this.author = author;
        this.pages = pages;
    }

    private int id;
    private String title;
    private String author;
    private int pages;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPages() {
        return pages;
    }
}
