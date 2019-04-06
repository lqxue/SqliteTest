package com.sqlitetest.db;


/**
 * 数据库配置文件类
 */
public class DbConfig {

    //数据库
    public static final int DATABASE_VERSION = 1;//如果数据库升级此处需+1
    public static final String DATABASE_NAME = "search.db";
    public static final String DB_PATH = "DateBase";

    //user 表
    public static final String TAB_USER = "create table if not exists user(id integer primary key  autoincrement, name varchar(20), passwords varchar(20))";
}
