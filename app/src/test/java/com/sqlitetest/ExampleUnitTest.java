package com.sqlitetest;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        String sql = String.format("insert into user (name,passwords) values (%s, %s)","张三","123456");
        System.out.print(sql);
    }
}