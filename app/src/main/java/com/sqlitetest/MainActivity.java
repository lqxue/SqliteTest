package com.sqlitetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sqlitetest.db.DBManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBManager.ff();
//        DBManager.ff1();
//        DBManager.ff2();
    }
}
