package cn.mylife.utils.quartz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;


/**
 * @author yangjie
 * 2018/11/18 16:30
 */
@Controller
public class EmailEncodeJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        final JobDataMap mergedJobDataMap = jobExecutionContext.getMergedJobDataMap();
        final HttpSession session = (HttpSession)mergedJobDataMap.get("session");
        session.removeAttribute("emailCode");
    }
}
