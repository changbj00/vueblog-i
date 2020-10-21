package com.example.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class DateUtils {
    public static DateTime getDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        DateTime dateTime = DateUtil.parseDateTime(dateString);
        return dateTime;
    }

    public static void main(String[] args) {
        Date date=getDate();
        System.out.println(date);
    }
}
