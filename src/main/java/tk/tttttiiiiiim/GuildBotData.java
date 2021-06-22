package tk.tttttiiiiiim;

@SuppressWarnings("all")
public class GuildBotData
{
    public String guildID;
    public String chatName;
    public long chatID;

    public GuildBotData(String guildId, String chatName, long chatId)
    {
        this.guildID = guildId;
        this.chatName = chatName;
        this.chatID = chatId;
    }
}
