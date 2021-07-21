package com.boot.bookstore.mapper;

import com.boot.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

// 继承JpaRepository<实体类类型，主键类型>
@Component
public interface BookMapper extends JpaRepository<Book, Integer> {

    public Book findByIsbn(String isbn);
//    //方法命名规则
//    public Book findByCustId(long id);
//    //模糊查询
//    public List<Book>findByCustNameLike(String CustName);
//    //删除
//    public void deleteByCustId(long id);
//    //多条件模糊查询
//    public List<Book>findByCustNameLikeAndCustAddressLike(String CustName,String CustAddress);
//    //查数
//    public long count();
//    //通过姓名查找
//    public List<Book> findByCustName(String custName);
}
