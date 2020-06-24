package org.lixianyuan.ampq.bean;

import lombok.Data;

/**
 * @author lxy
 * @Date 2020/5/23
 * @Descript
 **/
@Data
public class Book {
    private String bookName;
    private String auth;

    public Book(String bookName, String auth) {
        this.bookName = bookName;
        this.auth = auth;
    }
    public Book(){

    }

}
