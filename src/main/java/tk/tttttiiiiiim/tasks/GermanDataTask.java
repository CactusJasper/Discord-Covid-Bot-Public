package tk.tttttiiiiiim.tasks;

import org.quartz.*;
import tk.tttttiiiiiim.*;
import tk.tttttiiiiiim.messages.*;

import java.util.*;

public class GermanDataTask implements Job
{
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException
    {
        String data = GermanData.getGermanDataMessage();

        if(!data.equals("no data"))
        {
            for(int i = 0; i < DiscordCovidBot.guildData.size(); i++)
            {
                GuildBotData gbd = DiscordCovidBot.guildData.get(i);
                Objects.requireNonNull(Objects.requireNonNull(DiscordCovidBot.jda.getGuildCache()
                        .getElementById(gbd.guildID)).getTextChannelCache().getElementById(gbd.chatID))
                        .sendMessage(codeBlock(data)).queue(response -> { });
            }
        }
    }

    private String codeBlock(String text)
    {
        return "```" + '\n' + text + "\n```";
    }
}
