package com.sqlitetest.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.sqlitetest.MyApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 12133 on 2019-4-6.
 */

public class DBManager {

    public static void ff() {

        List<Student> objects = new ArrayList<>();
        for (int i =0;i<10000;i++){
            if (0 ==i%2){
                objects.add(new Student(i+"","123456"));
            }else {
                objects.add(new Student(i+"","123456"+i));
            }

        }
        long startTime = System.currentTimeMillis();
        DatabaseContext databaseContext = new DatabaseContext(MyApp.context);
        DbSQLiteHelper mySQLiteOpenHelper = new DbSQLiteHelper(databaseContext);
        SQLiteDatabase writableDatabase = mySQLiteOpenHelper.getWritableDatabase();
        //开启事务
        writableDatabase.beginTransaction();
        //编写sql 语句
        String sql = "INSERT OR IGNORE into user(name,passwords) values(?,?)";
        Cursor cursor;
        SQLiteStatement stat = writableDatabase.compileStatement(sql);
        try {
            for (int i = 0; i < objects.size(); i++) {
                Student student = objects.get(i);
//               cursor =writableDatabase.query("user", new String[]{"passwords"}, "passwords = ?", new String[]{student.getPasswords()}, null,null, null, null);
//                int count = cursor.getCount();
//                if (count==0){
                    stat.bindString(1, student.getName());
                    stat.bindString(2, student.getPasswords());
                    stat.executeInsert();
//                }

            }
            //设置事务成功
            writableDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            throw e;
        } finally {
            //结束事务
            writableDatabase.endTransaction();
            //关闭数据库
            writableDatabase.close();
        }
        long endTime = System.currentTimeMillis();
        Log.e("haha", "用时:" + (endTime - startTime) + "毫秒");
    }

    public static void ff1() {
        long startTime = System.currentTimeMillis();
        DatabaseContext databaseContext = new DatabaseContext(MyApp.context);
        DbSQLiteHelper mySQLiteOpenHelper = new DbSQLiteHelper(databaseContext);
        SQLiteDatabase writableDatabase = mySQLiteOpenHelper.getWritableDatabase();
        //开启事务
        writableDatabase.beginTransaction();
        try {
            //这里写代码
            for (int i = 0; i < 10000; i++) {
                ContentValues value = new ContentValues();
                //向数据表中字段名为name的字段中添加"张三"。
                value.put("name", "张三" + i);
                //向数据表中字段名为passwords的字段中添加"123416"。
                value.put("passwords", "123416" + i);
                //db数据库对象在前面已经创建，这里直接使用。
                //在数据库的user数据表中插入：字段名name为"张三"，字段名passwords为"123416"的数据。
                writableDatabase.insert("user", null, value);
            }
            //设置事务成功
            writableDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            throw e;
        } finally {
            //结束事务
            writableDatabase.endTransaction();
            writableDatabase.close();
        }
        long endTime = System.currentTimeMillis();
        Log.e("haha", "用时:" + (endTime - startTime) + "毫秒");
    }

    //用时最少
    public static void ff2() {
        long startTime = System.currentTimeMillis();
        DatabaseContext databaseContext = new DatabaseContext(MyApp.context);
        DbSQLiteHelper mySQLiteOpenHelper = new DbSQLiteHelper(databaseContext);
        SQLiteDatabase writableDatabase = mySQLiteOpenHelper.getWritableDatabase();
        //开启事务
        writableDatabase.beginTransaction();
        DatabaseUtils.InsertHelper ih = new DatabaseUtils.InsertHelper(writableDatabase, "user");
        final int name = ih.getColumnIndex("name");
        final int passwords = ih.getColumnIndex("passwords");
        try {
            //这里写代码
            for (int i = 0; i < 10000; i++) {
                ih.prepareForInsert();
                ih.bind(name, "张三" + i);
                ih.bind(passwords, "123416" + i);
                ih.execute();
            }
            //设置事务成功
            writableDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            throw e;
        } finally {
            //结束事务
            writableDatabase.endTransaction();
            writableDatabase.close();
        }
        long endTime = System.currentTimeMillis();
        Log.e("haha", "用时:" + (endTime - startTime) + "毫秒");
    }
}
