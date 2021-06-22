package tk.tttttiiiiiim.responses;

@SuppressWarnings("all")
public class CountryData
{
    public CountryData[] results;

    public CountryData(CountryData[] data)
    {
        results = data;
    }

    public class CountryFullData
    {
        public String country_region;
        public long confirmed;
        public long deaths;
        public long recovered;
        public long last_updated;

        public CountryFullData(String country_region, long confirmed, long deaths, long recovered, long last_updated)
        {
            this.country_region = country_region;
            this.confirmed = confirmed;
            this.deaths = deaths;
            this.recovered = recovered;
            this.last_updated = last_updated;
        }
    }
}
