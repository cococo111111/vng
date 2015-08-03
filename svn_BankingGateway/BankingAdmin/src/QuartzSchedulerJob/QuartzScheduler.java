/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuartzSchedulerJob;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import vng.banking.admin.config.Configuration;

/**
 *
 * @author root
 */
public class QuartzScheduler {

        public JobDetail dailyUpdateJob() {
                JobDetail job = JobBuilder.newJob(DailyUpdateTaskJob.class)
                      .withIdentity("DailyUpdateTaskJob").build();
                return job;
        }

        public Trigger getMidNightTrigger() {
                Trigger trigger = TriggerBuilder
                      .newTrigger()
                      .withIdentity("MidNightTrigger")
                      .withSchedule(
                      CronScheduleBuilder.cronSchedule(Configuration.SCHEDULER))
                      .build();
                return trigger;
        }
        
         public JobDetail getNotifyJob() {
                JobDetail job = JobBuilder.newJob(SendEmailNotifyTaskJob.class)
                      .withIdentity("NotifyJob").build();
                return job;
        }

        public Trigger getNotifyTrigger() {
                Trigger trigger = TriggerBuilder
                      .newTrigger()
                      .withIdentity("NotifyTrigger")
                      .withSchedule(
                      CronScheduleBuilder.cronSchedule(Configuration.EMAIL_SCHEDULER))
                      .build();
                return trigger;
        }
}
