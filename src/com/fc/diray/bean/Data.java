package com.fc.diray.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Data {
    private String date;
    private String content;
    private String title;
    private int id;

    public Data() {
    }

    public Data(String date, String content, String title, int id) {
        this.date = date;
        this.content = content;
        this.title = title;
        this.id = id;
    }

    public String getDate() {
        return stampToDate(date);
    }
    public String getDate2() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        lt=lt*1000;
//        System.out.println(lt);
        Date date = new Date(lt);
//        String new_lt = String.valueOf(lt);
        res = simpleDateFormat.format(date);

//        System.out.println(res);
        return res;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Data{" +
                "date=" + date +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", id=" + id +
                '}';
    }
}
