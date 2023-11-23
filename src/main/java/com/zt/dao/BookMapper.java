package com.zt.dao;

import com.zt.pojo.Book;
import com.zt.pojo.UserDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2023/11/17 23:36
 */
@Repository
public interface BookMapper {
    List<Book> selectByBookName(@Param("bookname") String bookname);
//    将图书状态变为已借阅
    void updateBookById(@Param("id") Integer id);
//    将图书数量减少1
    void updateBookCountById(@Param("id") Integer id);
    Book selectBookById(@Param("id") Integer id);
    void insertBookBorrow(@Param("uid") Integer uid, @Param("bid") Integer bid, @Param("borrowdate") String borrowdate);
//    通过用户id获取该用户借的所有书的id
    Integer[] selectBorrowBook(@Param("id") Integer id);
//    通过书本的id数组来查询书本的集合，使用动态sql
    List<Book> selectBookByIntegers(@Param("ids") Integer[] integers);
//    通过书本的id来查询它们的借阅日期
    String[] selectBookBorrowTimeByIntegers(@Param("ids") Integer[] integers);
//    多表查询，通过用户id查询它借的书以及它借的书的时间
    List<Map<String,Object>> selectMapById(@Param("id") Integer id,@Param("bookname") String bookname);
//    通过uid和bid插入归还时间
    void updateReturnDate(@Param("uid") Integer uid,@Param("bid") Integer bid,@Param("returndate") String returndate);
//    通过图书编号改变图书状态为未借阅并且图书数量加1
    void updateBookStatusAndCount(@Param("id") Integer id);
//    添加整本图书
    void insertBook(Book book);
//    更新book的所有状态除了图书编号和状态
    void updateBook(Book book);
//    删除对应id的图书
    void delteBookById(@Param("id") Integer id);
    List<Map<String,Object>> selectBorrowCodeByUidAndBid(@Param("uid") Integer uid,@Param("bookname") String bookname,@Param("bid") Integer bid);

    void deleteBorrowByBorrowdate(@Param("borrowdate") String borrowdate);
}
