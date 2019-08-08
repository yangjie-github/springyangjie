package jiezidata.date;

import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.concurrent.TimeUnit;

/**
 * @author yangjie
 * @date 2018/9/10 7:55
 */
public class java8Date {

    public static void main(String[] args) {

        //时间戳 instance
        Instant now = Instant.now();
        //和北京时间相差八个时区
        System.out.println(now);
        //解决方法
        Instant instant = Instant.now().plusMillis(TimeUnit.HOURS.toMillis(25));
        System.out.println(instant);
        //获取从1970年到现在的毫秒
        System.out.println(instant.toEpochMilli());
        long epochSecond = Instant.now().toEpochMilli();
        //获取毫秒值不存在时区问题
        System.out.println(epochSecond);


        //获取两个时间相差的天数
        Instant now1 = Instant.now();
        Instant instant1 = now1.plusMillis(TimeUnit.HOURS.toMillis(8));
        //注意时间先后， until时候， 时间前until时间后
        long until = now1.until(instant1, ChronoUnit.DAYS);
        System.out.println(until);
        //获取相差小时 返回24
        long until2 = now1.until(instant1, ChronoUnit.HOURS);
        System.out.println(until2);
        //获取相差毫秒
        long until3 = now1.until(instant1, ChronoUnit.MILLIS);
        System.out.println(until3);

        Instant instant3 = Instant.now();

        //偏移时区相差30小时
        OffsetDateTime atOffset = instant.atOffset(ZoneOffset.ofHours(1));

        //返回偏移的时间戳 !!!!!!!!!
        //!!!!!注意这里的时间戳是原来的instant
        //!!!!  atOffset.toInstant()==instant
        atOffset.toInstant();

        //获取年份
        atOffset.getYear();
        //获取月份
        atOffset.getMonth();
        //获取月份的第几天
        atOffset.getDayOfMonth();
        //获取星期中的第几天
        atOffset.getDayOfWeek();
        //atOffset.getDayOfWeek().getValue() 对应周数的第几天
        //atOffset.getDayOfWeek()返回 英文星期几 比如 SUNDAY


        //获取在某个时差
        //UTC不做偏差ZoneId.of("Asia/Kolkata")
        ZonedDateTime atZone = instant.atZone(ZoneOffset.UTC);
        //亚洲印度
        ZonedDateTime atZone1 = instant.atZone(ZoneId.of("Asia/Kolkata"));
        //偏差一小时
        ZonedDateTime atZone2 = instant.atZone(ZoneOffset.ofHours(1));
        //获取时间和上面相同

        Instant now2 = Instant.now();

        ZonedDateTime atZone3 = now2.atZone(ZoneOffset.UTC);

        //获取时间戳 中这个月的第一天的时间 如现在时间是2016 10 23-->>2016 10 1
        ZonedDateTime with = atZone3.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(with);
        //获取时间戳 下个月的第一天的时间 如现在时间是2016 10 23  -->>2016 11 1
        ZonedDateTime with2 = atZone3.with(TemporalAdjusters.firstDayOfNextMonth());
        //获取时间戳 下年的第一天 如现在时间是2016 10 23  -->>2017 1 1
        ZonedDateTime with3 = atZone3.with(TemporalAdjusters.firstDayOfNextYear());
        //获取时间戳 今年第一天 如现在时间是2016 10 23  -->>2016 1 1
        ZonedDateTime with4 = atZone3.with(TemporalAdjusters.firstDayOfYear());
        //获取这个这个月的第一个星期二
        ZonedDateTime with5 = atZone3.with(TemporalAdjusters.firstInMonth(DayOfWeek.TUESDAY));
        //获取时间戳 本月最后一天  2016 12 23  -->>2016 12 31
        ZonedDateTime with6 = atZone3.with(TemporalAdjusters.lastDayOfMonth());
        //......后面大家自行查看TemporalAdjusters

    }

    /**
     * 时间戳转换为LocalDate
     */
    @Test
    public void test1() {
        Instant now = Instant.now();

        ZonedDateTime atZone = now.atZone(ZoneOffset.UTC);
        //转化LocalDate
        atZone.toLocalDate();
        LocalDate date = LocalDate.from(atZone);

        //LocalDateTime
        atZone.toLocalDateTime();
        LocalDateTime.from(atZone);

        //LocalDateTime
        atZone.toLocalDateTime();
        LocalDateTime.from(date);
    }

    /**
     * LocalDate
     */
    @Test
    public void test2() {
        //获取当前时间 已经是本地时间
        LocalDate date = LocalDate.now();
        //获取指定时间
        LocalDate date2 = LocalDate.parse("2007-12-03");
        //获取指定时间
        LocalDate date3 = LocalDate.of(2007, 12, 3);
        //获取 距离1970-1-1 后面的第三十天 1970-01-31
        LocalDate date4 =LocalDate.ofEpochDay(30);
        //获取 1112年的第12天的日期  1112-1-12
        LocalDate date5 = LocalDate.ofYearDay(1112, 12);

        //判断闰年
        LocalDate now = LocalDate.now();
        System.out.println(now.isLeapYear());
    }
}
