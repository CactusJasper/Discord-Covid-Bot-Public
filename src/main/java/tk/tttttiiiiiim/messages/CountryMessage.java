package tk.tttttiiiiiim.messages;

import com.google.gson.*;
import com.google.gson.reflect.*;
import tk.tttttiiiiiim.responses.*;

import java.io.*;
import java.net.*;

public class CountryMessage
{
    public static String getCountryMessage(String name)
    {
        try
        {
            URL url = new URL("https://corona.lmao.ninja/v2/countries/" + name);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36");

            // To store our response
            StringBuilder content;

            // Get the input stream of the connection
            try(BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream())))
            {
                String line;
                content = new StringBuilder();
                while((line = input.readLine()) != null)
                {
                    // Append each line of the response and separate them
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }
            finally
            {
                connection.disconnect();
            }

            Gson gson = new Gson();
            NationData nd = gson.fromJson(content.toString(), new TypeToken<NationData>(){}.getType());

            if(nd != null)
            {
                String toRet = "== Current " + nd.country + " Stats ==\n";
                toRet += "Confirmed Cases: " + nd.cases + "\n";
                toRet += "Current Deaths: " + nd.deaths + "\n";
                toRet += "Current Recovered: " + nd.recovered + "\n";
                toRet += "Last Updated: " + nd.updated + "\n";

                return toRet;
            }
            else
            {
                return "no data";
            }
        }
        catch(Exception e)
        {
            return "no data";
        }
    }
}
