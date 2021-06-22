package tk.tttttiiiiiim.messages;

import com.google.gson.*;
import com.google.gson.reflect.*;
import tk.tttttiiiiiim.responses.*;

import java.io.*;
import java.net.*;

public class GlobalDataMessages
{
    public static String getGlobalDataMessage()
    {
        try
        {
            URL url = new URL("https://corona.lmao.ninja/v2/all");
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
            GlobalData gd = gson.fromJson(content.toString(), new TypeToken<GlobalData>(){}.getType());

            String text = "== Current Global Stats ==\n";
            text += "Total Cases: " + gd.cases + "\n";
            text += "Today's Cases: " + gd.todayCases + "\n";
            text += "Total Recovered: " + gd.recovered + "\n";
            text += "Today's Recovered: " + gd.todayRecovered + "\n";
            text += "Total Deaths: " + gd.deaths + "\n";
            text += "Today's Deaths: " + gd.todayDeaths + "\n";
            text += "Active Cases: " + gd.active + "\n";
            text += "Critical Cases: " + gd.critical + "\n";
            text += "Cases Per Million People: " + gd.casesPerOneMillion + "\n";
            text += "Deaths Per Million People: " + gd.deathsPerOneMillion + "\n";
            text += "Test's Done: " + gd.tests + "\n";
            text += "Test's Per Million People: " + gd.testsPerOneMillion + "\n";
            text += "Population: " + gd.population + "\n";
            text += "Active Per Million People: " + gd.activePerOneMillion + "\n";
            text += "Recovered Per Million People: " + gd.recoveredPerOneMillion + "\n";
            text += "Critical Per Million People: " + gd.criticalPerOneMillion + "\n";
            text += "Affected Countries: " + gd.affectedCountries + "\n";
            text += "Please Keep Safe Wear Masks and Social Distance Together the World Can Beat Covid";
            return text;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "no data";
        }
    }
}
