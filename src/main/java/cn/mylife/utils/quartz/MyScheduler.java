package cn.mylife.utils.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author yangjie
 * 2018/11/18 16:32
 */
@Controller
public class MyScheduler {

    public static void myScheduler(HttpServletRequest request) throws SchedulerException {

        Date startDate = new Date();
        startDate.setTime(startDate.getTime() + 60*1000);

        //创建一个jobDetail
        JobDetail jobDetail = JobBuilder.newJob(EmailEncodeJob.class).withIdentity("job1", "group1").build();

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("session", request.getSession());

        //创建trigger
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startAt(startDate)
                .usingJobData(jobDataMap)
                //若不设置withSchedule，则只会执行一次
//                .withSchedule(
//                        SimpleScheduleBuilder.simpleSchedule()
//                                 .withIntervalInMinutes(1)
//                                             //重复执行的次数，因为加入任务的时候马上执行了，所以不需要重复，否则会多一次。
//                                             .withRepeatCount(0))
                .build();

        //获取调度器实例, 定义一个scheduler的实例
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, trigger);
    }

//    public static void main(String[] args) throws SchedulerException {
//        Date startDate = new Date();
//        startDate.setTime(startDate.getTime() + 5*1000);
//
//        //创建一个jobDetail
//        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("job1", "group1").build();
//
//        //创建trigger
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .withIdentity("trigger1", "group1")
//                .startAt(startDate)
//                //若不设置withSchedule，则只会执行一次
////                .withSchedule(
////                        SimpleScheduleBuilder.simpleSchedule()
////                                 .withIntervalInMinutes(1)
////                                             //重复执行的次数，因为加入任务的时候马上执行了，所以不需要重复，否则会多一次。
////                                             .withRepeatCount(0))
//                .build();
//
//        //定义一个scheduler的实例
//        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
//        Scheduler scheduler = schedulerFactory.getScheduler();
//        scheduler.start();
//        scheduler.scheduleJob(jobDetail, trigger);
//    }
}
