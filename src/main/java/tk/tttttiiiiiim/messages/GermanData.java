package tk.tttttiiiiiim.messages;

import com.google.gson.*;
import com.google.gson.reflect.*;
import tk.tttttiiiiiim.responses.*;

import java.io.*;
import java.net.*;

public class GermanData
{
    public static String getGermanDataMessage()
    {
        try
        {
            URL url = new URL("https://corona.lmao.ninja/v2/countries/Germany");
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
            NationData data = gson.fromJson(content.toString(), new TypeToken<NationData>(){}.getType());

            String text = "== German SARS-COV-2 Stats ==\n";
            text += "Total Cases: " + data.cases + "\n";
            text += "Today's Cases: " + data.todayCases + "\n";
            text += "Total Deaths: " + data.deaths + "\n";
            text += "Today's Deaths: " + data.todayDeaths + "\n";
            text += "Total Recovered: " + data.recovered + "\n";
            text += "Active Cases: " + data.active + "\n";
            text += "Current Critical Cases: " + data.critical + "\n";
            text += "Cases Per Million People: " + data.casesPerOneMillion + "\n";
            text += "Deaths Per Million People: " + data.deathsPerOneMillion + "\n";
            text += "Test's Done: " + data.tests + "\n";
            text += "Test's Per Million People: " + data.testsPerOneMillion + "\n";
            text += "Population: " + data.population + "\n";
            text += "One Case every " + data.oneCasePerPeople + " people" + "\n";
            text += "One Death every " + data.oneDeathPerPeople + " people" + "\n";
            text += "Active Per Million People: " + data.activePerOneMillion + "\n";
            text += "Recovered Per Million People: " + data.recoveredPerOneMillion + "\n";
            text += "Critical Per Million People: " + data.criticalPerOneMillion + "\n";
            return text;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "no data";
        }
    }
}
