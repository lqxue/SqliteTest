package com.sqlitetest.db;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;


/**
 * 用于支持对存储在SD卡上的数据库的访问
 * 需要加 <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
 * 动态权限
 */
public class DatabaseContext extends ContextWrapper {

    /**
     * 构造函数
     */
    public DatabaseContext(Context context) {
        super(context);
    }

    /**
     * 获得数据库路径，如果不存在，则创建对象对象
     *
     * @param name
     */
    @Override
    public File getDatabasePath(String name) {
        //判断是否存在sd卡
        boolean sdIsExist = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
        if (!sdIsExist) {//如果不存在,
            Log.e("SD卡管理：", "SD卡不存在，请加载SD卡");
            return null;
        } else {//如果存在
            //获取sd卡路径
            String dbDir = Environment.getExternalStorageDirectory().getAbsolutePath();
            //数据库所在目录
            dbDir = dbDir + "/" + DbConfig.DB_PATH;
            //数据库路径
            String dbPath = dbDir + "/" + name;
            //判断目录是否存在，不存在则创建该目录
            File dirFile = new File(dbDir);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }

            //数据库文件是否创建成功
            boolean isFileCreateSuccess = false;
            //判断文件是否存在，不存在则创建该文件
            File dbFile = new File(dbPath);
            if (!dbFile.exists()) {
                try {
                    isFileCreateSuccess = dbFile.createNewFile();//创建文件
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                isFileCreateSuccess = true;
            }

            //返回数据库文件对象
            if (isFileCreateSuccess)
                return dbFile;
            else
                return null;
        }
    }

    /**
     * 重载这个方法，是用来打开SD卡上的数据库的，android 2.3及以下会调用这个方法。
     *
     * @param name
     * @param mode
     * @param factory
     */
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode,
                                               SQLiteDatabase.CursorFactory factory) {
        return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), null);
    }

    /**
     * Android 4.0后会调用此方法获取数据库。
     *
     * @param name
     * @param mode
     * @param factory
     * @param errorHandler
     * @see ContextWrapper#openOrCreateDatabase(String, int,
     * SQLiteDatabase.CursorFactory,
     * DatabaseErrorHandler)
     */
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory,
                                               DatabaseErrorHandler errorHandler) {
        return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), null);
    }
}