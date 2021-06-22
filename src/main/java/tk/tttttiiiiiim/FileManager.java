package tk.tttttiiiiiim;

import com.google.gson.*;
import com.google.gson.reflect.*;

import java.io.*;
import java.util.*;

@SuppressWarnings("all")
public class FileManager
{
    public static void saveGuildsFile()
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        if(DiscordCovidBot.guildData.size() > 0)
        {
            File file = new File("guilds.json");
            String toWrite = gson.toJson(DiscordCovidBot.guildData);

            try
            {
                if(file.createNewFile())
                {
                    FileWriter fw = new FileWriter(file, false);
                    fw.write(toWrite);
                    fw.close();
                }
                else
                {
                    FileWriter fw = new FileWriter(file, false);
                    fw.write(toWrite);
                    fw.close();
                }
            }
            catch(IOException e)
            {
            }
        }
    }

    public static void readGuildsFile()
    {
        File file = new File("guilds.json");
        Gson gson = new Gson();
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(file));
            ArrayList<GuildBotData> data = gson.fromJson(br, new TypeToken<ArrayList<GuildBotData>>(){}.getType());
            if(data != null)
            {
                DiscordCovidBot.guildData.addAll(data);
            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
