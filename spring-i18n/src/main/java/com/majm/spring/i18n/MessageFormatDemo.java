package com.majm.spring.i18n;

import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * {@link MessageFormat} </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-04-29 22:48
 * @since
 */
@Slf4j
public class MessageFormatDemo {


    public static void main(String[] args) {
        int planet = 7;
        String event = "a disturbance in the Force";

        String pattern = "At {1,time, long} on {1,date,full}, there was {2} on planet {0,number,integer}.";
        MessageFormat messageFormat = new MessageFormat(pattern);
        String result = messageFormat.format(new Object[]{planet, new Date(), event});
        log.info(result);

        // 重置MessageFormat
        // apply
        pattern = "This is a text: {0}";
        messageFormat.applyPattern(pattern);
        result = messageFormat.format(new Object[]{"Hello World", 666});
        log.info(result);

        // 重置 Local
        messageFormat.setLocale(Locale.ENGLISH);
        pattern = "At?? {1,time, long} on {1,date,full}, there was {2} on planet {0,number,integer}.";
        messageFormat.applyPattern(pattern);
        result = messageFormat.format(new Object[]{planet, new Date(), "???"});
        log.info(result);

        // 重置 Format
        // 根据参数索引 来设置 Pattern
        messageFormat.setFormat(1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        result = messageFormat.format(new Object[]{planet, new Date(), "???"});
        log.info(result);

        int fileCount = 1273;
        String diskName = "MyDisk";
        Object[] testArgs = {new Long(fileCount), diskName};

        MessageFormat form = new MessageFormat(
                "The disk \"{1}\" contains {0} file(s).");

        log.info(form.format(testArgs));
    }
}
