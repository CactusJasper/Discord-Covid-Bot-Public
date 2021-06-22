package tk.tttttiiiiiim;

import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.utils.*;
import net.dv8tion.jda.api.utils.cache.*;
import org.quartz.*;
import org.quartz.impl.*;
import tk.tttttiiiiiim.events.*;
import tk.tttttiiiiiim.tasks.*;

import javax.security.auth.login.*;
import java.util.*;

public class DiscordCovidBot
{
    public static ArrayList<GuildBotData> guildData = new ArrayList<>();
    public static JDA jda;

    public static void main(String[] args) throws LoginException
    {
        JDABuilder builder = JDABuilder.createDefault("DISCORD TOKEN HERE");

        // Disable parts of the cache
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        // Enable the bulk delete event
        builder.setBulkDeleteSplittingEnabled(false);
        builder.setCompression(Compression.ZLIB);
        // Set activity
        builder.setActivity(Activity.watching("SARS-COV-2 Data"));

        builder.addEventListeners(new MessageEvent());
        FileManager.readGuildsFile();
        jda = builder.build();

        try
        {
            JobDetail saveGuildsJob = JobBuilder.newJob(SaveGuildsTask.class).build();
            JobDetail globalJob = JobBuilder.newJob(GlobalDataTask.class).build();
            JobDetail usDataJob = JobBuilder.newJob(USDataTask.class).build();
            JobDetail ukDataJob = JobBuilder.newJob(UKDataTask.class).build();
            JobDetail germanDataJob = JobBuilder.newJob(GermanDataTask.class).build();

            CronTrigger saveGuildsJobTrigger = TriggerBuilder.newTrigger().withIdentity("SaveGuildsJobTrigger").withSchedule(CronScheduleBuilder.cronSchedule("0 0 * * * ?")).forJob(saveGuildsJob).build();
            CronTrigger globalJobTrigger = TriggerBuilder.newTrigger().withIdentity("GlobalDataTrigger").withSchedule(CronScheduleBuilder.cronSchedule("0 0 1 * * ?")).forJob(globalJob).build();
            CronTrigger usJobTrigger = TriggerBuilder.newTrigger().withIdentity("USJobTrigger").withSchedule(CronScheduleBuilder.cronSchedule("0 30 20 * * ?")).forJob(usDataJob).build();
            CronTrigger ukJobTrigger = TriggerBuilder.newTrigger().withIdentity("UKJobTrigger").withSchedule(CronScheduleBuilder.cronSchedule("0 30 20 * * ?")).forJob(ukDataJob).build();
            CronTrigger germanJobTrigger = TriggerBuilder.newTrigger().withIdentity("GermanJobTrigger").withSchedule(CronScheduleBuilder.cronSchedule("0 30 20 * * ?")).forJob(germanDataJob).build();

            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            scheduler.scheduleJob(saveGuildsJob, saveGuildsJobTrigger);
            scheduler.scheduleJob(globalJob, globalJobTrigger);
            scheduler.scheduleJob(usDataJob, usJobTrigger);
            scheduler.scheduleJob(ukDataJob, ukJobTrigger);
            scheduler.scheduleJob(germanDataJob, germanJobTrigger);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
