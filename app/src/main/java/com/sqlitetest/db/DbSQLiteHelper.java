package com.sqlitetest.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 创建库helper类
 */
public class DbSQLiteHelper extends SQLiteOpenHelper {

    public DbSQLiteHelper(Context context) {
        //使用自定义Context 创建SQLiteOpenHelper
        this(context, DbConfig.DATABASE_NAME, null, DbConfig.DATABASE_VERSION);
    }

    public DbSQLiteHelper(Context context, String name) {
        this(context, name, null, DbConfig.DATABASE_VERSION);
    }

    public DbSQLiteHelper(Context context, String name, int version) {
        this(context, name, null, version);
    }

    public DbSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * 当执行getWritableDatabase或getReadableDatabase时，
     * 若数据库文件不存在，则执行。也就是只执行一次。
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //在创建数据库时，创建一个数据表table
        sqLiteDatabase.execSQL(DbConfig.TAB_USER);
    }

    /**
     * 当数据库newVersion大于oldVersion时执行。一般新增表和字段时在这处理。
     * 已经安装过的用户  通过自增version  回调升级方法
     * 在这个方法中通过版本判断来处理逻辑
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    }

}