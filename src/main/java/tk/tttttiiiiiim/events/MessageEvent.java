package tk.tttttiiiiiim.events;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.*;
import net.dv8tion.jda.api.hooks.*;
import tk.tttttiiiiiim.*;
import tk.tttttiiiiiim.messages.*;

import java.util.*;

@SuppressWarnings("all")
public class MessageEvent extends ListenerAdapter
{
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if(event.isFromType(ChannelType.PRIVATE))
        {
            /*System.out.printf("[PM] %s: %s\n", event.getAuthor().getName(),
                    event.getMessage().getContentDisplay());*/
            String[] args = event.getMessage().getContentDisplay().split(" ");
            if(args.length > 0)
            {
                if(args[0].equalsIgnoreCase("++global_data"))
                {
                    // Global Data Message
                    //event.getMessage().delete().queue();
                    String data = GlobalDataMessages.getGlobalDataMessage();
                    if(data != "no data")
                    {
                        MessageChannel channel = event.getChannel();
                        channel.sendMessage(codeBlock(data)).queue(response -> { });
                    }
                    else
                    {
                        MessageChannel channel = event.getChannel();
                        channel.sendMessage(codeBlock("Currently unable to retreive global data check back later thankyou!\n" + "If the issue persists contact the developer of the SARS-COV-2 Bot")).queue(response -> { });
                    }
                }
            }
        }
        else if(event.getAuthor().isBot())
        {
            boolean containsWolfy = false;
            boolean containsShort = false;
            boolean safeOveride = false;
            boolean unsafe = false;

            String[] args = event.getMessage().getContentDisplay().split(" ");

            if(args.length > 0)
            {
                for(int i = 0; i < args.length; i++)
                {
                    if(args[i].toLowerCase().contains("wolf") || args[i].toLowerCase().contains("w.o.l.f") || args[i].toLowerCase().contains("w.o.l.f.y"))
                    {
                        containsWolfy = true;
                        if(args.length == 3 && i == 0)
                        {
                            if(args[i + 1].toLowerCase().contains("is") && args[i + 2].toLowerCase().contains("not"))
                            {
                                safeOveride = true;
                            }
                        }

                        if(args.length == 3 && i == 0)
                        {
                            if(args[i + 1].toLowerCase().contains("isnt") || args[i + 1].toLowerCase().contains("isn't"))
                            {
                                if(args[i + 2].toLowerCase().contains("tall"))
                                {
                                    unsafe = true;
                                }
                            }
                        }
                    }

                    if(args[i].toLowerCase().contains("short"))
                    {
                        containsShort = true;
                    }
                }

                if(containsShort && containsWolfy && (!safeOveride))
                {
                    event.getMessage().addReaction("\uD83D\uDC4E").queue();
                }

                if(unsafe)
                {
                    event.getMessage().addReaction("\uD83D\uDC4E").queue();
                }
            }
        }
        else
        {
            /*System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(),
                    event.getTextChannel().getName(), Objects.requireNonNull(event.getMember()).getEffectiveName(),
                    event.getMessage().getContentDisplay());*/

            String[] args = event.getMessage().getContentDisplay().split(" ");

            if(args.length > 0)
            {
                if(args[0].equalsIgnoreCase("++setup"))
                {
                    event.getMessage().delete().queue();
                    if(args.length >= 2)
                    {
                        String chatName = args[1].substring(1, args[1].length());

                        List<TextChannel> channels = event.getGuild().getTextChannelCache().getElementsByName(chatName);

                        if(channels.size() == 0)
                        {
                            // Send message saying unable to find chat
                            event.getMessage().getTextChannel().sendMessage("Unnable to find the provided chat please make sure that chat exists");
                        }
                        else
                        {
                            long chatId = channels.get(0).getIdLong();
                            GuildBotData guildData = new GuildBotData(event.getGuild().getId(), chatName, chatId);
                            boolean found = false;
                            int pos = -1;
                            for(int i = 0; i < DiscordCovidBot.guildData.size(); i++)
                            {
                                if(DiscordCovidBot.guildData.get(i).guildID.equalsIgnoreCase(event.getGuild().getId()))
                                {
                                    found = true;
                                    pos = i;
                                }
                            }

                            if(found)
                            {
                                if(pos != -1)
                                {
                                    if(DiscordCovidBot.guildData.get(pos).chatID != chatId)
                                    {
                                        DiscordCovidBot.guildData.get(pos).chatName = chatName;
                                        DiscordCovidBot.guildData.get(pos).chatID = chatId;
                                    }
                                }
                            }
                            else
                            {
                                DiscordCovidBot.guildData.add(guildData);
                            }

                            FileManager.saveGuildsFile();
                            MessageChannel channel = event.getChannel();
                            channel.sendMessage("Sending SARS-COV-2 Data to " + args[1]).queue(response -> { });
                            String data = GlobalDataMessages.getGlobalDataMessage();
                            if(data != "no data")
                            {
                                event.getGuild().getTextChannelCache().getElementById(chatId).sendMessage(codeBlock(data)).queue(response -> { });
                            }
                        }
                    }
                    else
                    {
                        event.getMessage().getTextChannel().sendMessage("Please provide a target text channel");
                    }
                }
                else if(args[0].equalsIgnoreCase("++global_data"))
                {
                    // Global Data Message
                    event.getMessage().delete().queue();
                    String data = GlobalDataMessages.getGlobalDataMessage();
                    if(data != "no data")
                    {
                        MessageChannel channel = event.getChannel();
                        channel.sendMessage(codeBlock(data)).queue(response -> { });
                    }
                    else
                    {
                        MessageChannel channel = event.getChannel();
                        channel.sendMessage(codeBlock("Currently unable to retreive global data check back later thankyou!\n" + "If the issue persists contact the developer of the SARS-COV-2 Bot")).queue(response -> { });
                    }
                }
                else if(args[0].equalsIgnoreCase("++us_data"))
                {
                    // US Data Message
                    event.getMessage().delete().queue();
                    String data = USData.getUSDataMessage();
                    if(data != "no data")
                    {
                        MessageChannel channel = event.getChannel();
                        channel.sendMessage(codeBlock(data)).queue(response -> { });
                    }
                    else
                    {
                        MessageChannel channel = event.getChannel();
                        channel.sendMessage(codeBlock("Currently unable to retreive US data check back later thankyou!\n" + "If the issue persists contact the developer of the SARS-COV-2 Bot")).queue(response -> { });
                    }
                }
                else if(args[0].equalsIgnoreCase("++uk_data"))
                {
                    // UK Data Message
                    event.getMessage().delete().queue();
                    String data = UKData.getUKDataMessage();
                    if(data != "no data")
                    {
                        MessageChannel channel = event.getChannel();
                        channel.sendMessage(codeBlock(data)).queue(response -> { });
                    }
                    else
                    {
                        MessageChannel channel = event.getChannel();
                        channel.sendMessage(codeBlock("Currently unable to retreive UK data check back later thankyou!\n" + "If the issue persists contact the developer of the SARS-COV-2 Bot")).queue(response -> { });
                    }
                }
                else if(args[0].equalsIgnoreCase("++german_data"))
                {
                    // German Data Message
                    event.getMessage().delete().queue();
                    String data = GermanData.getGermanDataMessage();
                    if(data != "no data")
                    {
                        MessageChannel channel = event.getChannel();
                        channel.sendMessage(codeBlock(data)).queue(response -> { });
                    }
                    else
                    {
                        MessageChannel channel = event.getChannel();
                        channel.sendMessage(codeBlock("Currently unable to retreive German data check back later thankyou!\n" + "If the issue persists contact the developer of the SARS-COV-2 Bot")).queue(response -> { });
                    }
                }
                else if(args[0].equalsIgnoreCase("++us_extra_data"))
                {
                    // US Extra Data Message
                    event.getMessage().delete().queue();
                    ArrayList<String> data = USData.getUSExtraDataMessage();
                    if(data != null)
                    {
                        MessageChannel channel = event.getChannel();

                        for(int i = 0; i < data.size(); i++)
                        {
                            channel.sendMessage(codeBlock(data.get(i))).queue(response -> { });
                        }
                    }
                    else
                    {
                        MessageChannel channel = event.getChannel();
                        channel.sendMessage(codeBlock("Currently unable to retreive US Extra data check back later thankyou!\n" + "If the issue persists contact the developer of the SARS-COV-2 Bot")).queue(response -> { });
                    }
                }
                else if(args[0].equalsIgnoreCase("++country"))
                {
                    if(args.length >= 2)
                    {
                        event.getMessage().delete().queue();
                        String data = CountryMessage.getCountryMessage(args[1]);
                        if(data != "no data")
                        {
                            MessageChannel channel = event.getChannel();
                            channel.sendMessage(codeBlock(data)).queue(response -> { });
                        }
                        else
                        {
                            MessageChannel channel = event.getChannel();
                            channel.sendMessage(codeBlock("Currently unable to retreive " + args[1] + " data check back later thankyou!\n" + "If the issue persists contact the developer of the SARS-COV-2 Bot")).queue(response -> { });
                        }
                    }
                }
                else
                {
                    boolean containsWolfy = false;
                    boolean containsShort = false;
                    boolean safeOveride = false;
                    boolean unsafe = false;

                    for(int i = 0; i < args.length; i++)
                    {
                        if(args[i].toLowerCase().contains("wolf") || args[i].toLowerCase().contains("w.o.l.f") || args[i].toLowerCase().contains("w.o.l.f.y"))
                        {
                            containsWolfy = true;
                            if(args.length == 3 && i == 0)
                            {
                                if(args[i + 1].toLowerCase().contains("is") && args[i + 2].toLowerCase().contains("not"))
                                {
                                    safeOveride = true;
                                }
                            }

                            if(args.length == 3 && i == 0)
                            {
                                if(args[i + 1].toLowerCase().contains("isnt") || args[i + 1].toLowerCase().contains("isn't"))
                                {
                                    if(args[i + 2].toLowerCase().contains("tall"))
                                    {
                                        unsafe = true;
                                    }
                                }
                            }
                        }

                        if(args[i].toLowerCase().contains("short"))
                        {
                            containsShort = true;
                        }
                    }

                    if(containsShort && containsWolfy && (!safeOveride))
                    {
                        event.getMessage().addReaction("\uD83D\uDC4E").queue();
                    }

                    if(unsafe)
                    {
                        event.getMessage().addReaction("\uD83D\uDC4E").queue();
                    }
                }
            }
        }
    }

    private static String codeBlock(String text)
    {
        return "```" + '\n' + text + "\n```";
    }
}
