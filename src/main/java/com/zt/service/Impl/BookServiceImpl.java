package com.zt.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zt.Utils.StringEmpty;
import com.zt.dao.BookMapper;
import com.zt.pojo.Book;
import com.zt.pojo.UserDetail;
import com.zt.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 * @description: TODO
 * @date 2023/11/18 16:50
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;
    /**
     * @description:
     * @param: bookname
    pageNum
     * @return: 一个map集合，包含了已经分好页的列表books，总页数pages，导航页navigatePages
     * @author Administrator
     * @date: 2023/11/18 23:59
     */
    @Override
    public Map<String,Object> seachBookBybookname(String bookname, Integer pageNum) {
//        设置默认一页的数据有5条
        Integer pageSize = 5;
//        传入的pageNum和bookname为空则设置默认值
        if(pageNum == null){
            pageNum = 1;
        }
        if(bookname == null){
            bookname = "";
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Book> books = bookMapper.selectByBookName(bookname);
        PageInfo<Book> bookPageInfo = new PageInfo<>(books, 3);
        Integer navigatePages = bookPageInfo.getNavigatePages();
        Integer pages = bookPageInfo.getPages();
        Map<String, Object> map = new HashMap<>();
        map.put("booklist", books);
        map.put("pages",pages);
        map.put("navigatePages", navigatePages);
        return map;
    }

    @Override
    public boolean borrowBook(Integer uid,Integer bid) {
//        先根据用户图书id找到这本书
        Book book = bookMapper.selectBookById(bid);
//        判断书本状态和数量，如果已借阅或者数量为零就返回结果借阅失败
        if (book.isStatus() || book.getCount() == 0){
            return false;
        }else{
//            如果书本可以被借阅，先更新书本的状态
            bookMapper.updateBookById(bid);
//            再更新书本的数量
            bookMapper.updateBookCountById(bid);
//            同时记录借阅日期
            DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            Date currentTime = new Date();
            String dateString = dateFormat.format(currentTime);
//            把借阅记录持久化
            bookMapper.insertBookBorrow(uid, bid, dateString);
//            返回结果
            return true;
        }
    }
    @Override
    public List<Map<String,Object>> returnRecodeBook(String bookname,Integer uid,Integer pageNum) {
//      如果这个bookname为null就设置为空字符串
        if (bookname == null){
            bookname = "";
        }
        return bookMapper.selectMapById(uid,bookname);
    }

    @Override
    public void returnBook(Integer uid,Integer bid) {
//        首先要记录归还时间
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date currentTime = new Date();
        String dateString = dateFormat.format(currentTime);
//        将归还时间更新到该用户的借还记录borrow中
        bookMapper.updateReturnDate(uid, bid, dateString);
//        改变图书的数量和状态
        bookMapper.updateBookStatusAndCount(bid);
    }


    @Override
    public boolean addBook(Book book) {
//        首先判断传来的参数是否完整的有书名，作者，出版社，ISBN，数量，不能存在error和0
        if (!(StringEmpty.isError(new Object[]{book.getBookname(),book.getAuthor(),book.getISBN(),book.getPublisher()})&&book.getCount() != 0)){
            return false;
        }
//        将添加的书本置为未借阅
        book.setStatus(false);
        bookMapper.insertBook(book);
        return true;
    }

    @Override
    public Book selectBookById(Integer id) {
        Book book = bookMapper.selectBookById(id);
        return book;
    }

    @Override
    public void updateBook(Book book) {
        bookMapper.updateBook(book);
    }

    @Override
    public void deleteBook(Integer id) {
        bookMapper.delteBookById(id);
    }

    @Override
    public List<Map<String,Object>> selectBorrowCodeByUidAndBid(Integer uid, String bookname, Integer bid,Integer pageNum) {
//        根据参数查询记录
        List<Map<String, Object>> maps = bookMapper.selectBorrowCodeByUidAndBid(uid, bookname, bid);
        return maps;
    }

    @Override
    public void deleteBorrowByBorrowdate(String borrowdate) {
        bookMapper.deleteBorrowByBorrowdate(borrowdate);
    }


}
