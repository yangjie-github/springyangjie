package jiezidata.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * date转换示例
 *
 * @author yangjie
 * @date 2018/8/24 15:23
 */
public class MyDate {

    public static void main (String[] args) throws ParseException {
        String time = "2018-05-16";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //字符串转换为日期
        Date date = format.parse(time);
        Date mydate = format.parse(time);
        //日期转换为字符串
        String format1 = format.format(new Date());

        //Calendar类
        //获取年
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        System.out.println(year);
        //获取月0-11
        int month = instance.get(Calendar.MONTH) + 1;
        System.out.println(month);
        //获取日
        int day = instance.get(Calendar.DAY_OF_MONTH);
        System.out.println(day);
        int DATE = instance.get(Calendar.DATE);
        System.out.println(DATE);
        //获取时
        int hour = instance.get(Calendar.HOUR);
        System.out.println(hour);
        //获取分
        int minute = instance.get(Calendar.MINUTE);
        System.out.println(minute);
        //获取秒
        int decond = instance.get(Calendar.SECOND);
        System.out.println(decond);
        //获取星期
        int week = instance.get(Calendar.DAY_OF_WEEK) - 1;
        System.out.println(week);
        //获取2018年8月的最后一天
        instance.set(2018, 8, 1);
        instance.add(Calendar.DAY_OF_MONTH, -1);
        int i = instance.get(Calendar.DAY_OF_MONTH);
        System.out.println("2018年8月的最后一天" + i);
        //直接获取某个月的最后一天
        Calendar instance2 = Calendar.getInstance();
        instance2.set(Calendar.DAY_OF_MONTH, instance.getActualMaximum(Calendar.DAY_OF_MONTH));
        int i2 = instance2.get(Calendar.DAY_OF_MONTH);
        System.out.println("直接获取2018年8月的最后一天" + i);

        //与date的转换
        Date time1 = Calendar.getInstance().getTime();
        System.out.println(format.format(time1));

        Calendar instance1 = Calendar.getInstance();
        instance1.setTime(mydate);
        Date time2 = instance1.getTime();
        System.out.println(format.format(time2));
    }
}
