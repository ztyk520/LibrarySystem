package com.zt.pojo;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2023/11/17 20:37
 */
public class Book {
    private Integer id;
    private String bookname;
    private String author;
    private String publisher;
    private String ISBN;
    private Integer count;
    private boolean status;

    public Book(Integer id, String bookname, String author, String publisher, String ISBN, int count, boolean status) {
        this.id = id;
        this.bookname = bookname;
        this.author = author;
        this.publisher = publisher;
        this.ISBN = ISBN;
        this.count = count;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Book() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
