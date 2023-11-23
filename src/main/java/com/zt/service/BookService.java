package com.zt.service;

import com.zt.pojo.Book;
import com.zt.pojo.UserDetail;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2023/11/18 16:49
 */
public interface BookService {
    Map<String,Object> seachBookBybookname(String bookname, Integer PageNum);
    boolean borrowBook(Integer uid,Integer bid);
//    图书归还记录查询业务方法
    List<Map<String,Object>> returnRecodeBook(String bookname,Integer id, Integer pageNum);
//    图书归还业务方法
    void returnBook(Integer uid,Integer bid);
//    图书添加业务
    boolean addBook(Book book);
//    简单的根据图书id查询功能
    Book selectBookById(Integer id);
//    更新图书
    void updateBook(Book book);
    void deleteBook(Integer id);
    List<Map<String,Object>> selectBorrowCodeByUidAndBid(Integer uid,String bookname,Integer bid,Integer pageNum);

    void deleteBorrowByBorrowdate(String borrowdate);
}
