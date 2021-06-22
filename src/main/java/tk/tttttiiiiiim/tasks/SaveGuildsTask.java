package tk.tttttiiiiiim.tasks;

import org.quartz.*;
import tk.tttttiiiiiim.*;

import java.util.*;

public class SaveGuildsTask implements Job
{
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException
    {
        FileManager.saveGuildsFile();
        DiscordCovidBot.guildData = new ArrayList<>();
        FileManager.readGuildsFile();
        System.out.println("Reloaded guilds.json");
    }
}
