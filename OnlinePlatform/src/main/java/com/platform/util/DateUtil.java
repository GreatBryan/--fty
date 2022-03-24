package com.platform.util;

import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
public class DateUtil {

    //获取当前时间的时间戳
    public Timestamp getCurrentTimestamp(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dt = new Date();
        String nowTimestampLong = formatter.format(dt);
        return Timestamp.valueOf(nowTimestampLong);
    }
}
