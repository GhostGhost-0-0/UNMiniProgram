package com.zzx.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @program: UNMinProgram
 * @Description
 * @Author: 那个小楠瓜
 * @create: 2022-03-22 17:03
 **/
public class UuidUtils {

    private static long tmpID;
    private static boolean tmpIDlocked = false;

    public static Long getId() {
        long ltime = 0;
        while (true) {
            if (tmpIDlocked == false) {
                tmpIDlocked = true;
                ltime = Long.valueOf(new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date()).toString()) * 10000;
                if (tmpID < ltime) {
                    tmpID = ltime;
                }else {
                    tmpID = tmpID + 1;
                    ltime = tmpID;
                }
                tmpIDlocked = false;
                return ltime;
            }
        }
    }
}
