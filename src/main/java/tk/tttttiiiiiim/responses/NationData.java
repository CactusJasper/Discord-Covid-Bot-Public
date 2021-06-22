package tk.tttttiiiiiim.responses;

@SuppressWarnings("all")
public class NationData
{
    public String country;
    public long updated;
    public long cases;
    public long todayCases;
    public long deaths;
    public long todayDeaths;
    public long recovered;
    public long todayRecovered;
    public long active;
    public long critical;
    public double casesPerOneMillion;
    public double deathsPerOneMillion;
    public long tests;
    public double testsPerOneMillion;
    public long population;
    public double oneCasePerPeople;
    public double oneDeathPerPeople;
    public double oneTestPerPeople;
    public double activePerOneMillion;
    public double recoveredPerOneMillion;
    public double criticalPerOneMillion;
    public long affectedCountries;

    public NationData(String country, long updated, long cases, long todayCases, long deaths, long todayDeaths, long recovered, long todayRecovered, long active, long critical, double casesPerOneMillion, double deathsPerOneMillion, long tests, double testsPerOneMillion, long population, double oneCasePerPeople, double oneDeathPerPeople, double oneTestPerPeople, double activePerOneMillion, double recoveredPerOneMillion, double criticalPerOneMillion, long affectedCountries) {
        this.country = country;
        this.updated = updated;
        this.cases = cases;
        this.todayCases = todayCases;
        this.deaths = deaths;
        this.todayDeaths = todayDeaths;
        this.recovered = recovered;
        this.todayRecovered = todayRecovered;
        this.active = active;
        this.critical = critical;
        this.casesPerOneMillion = casesPerOneMillion;
        this.deathsPerOneMillion = deathsPerOneMillion;
        this.tests = tests;
        this.testsPerOneMillion = testsPerOneMillion;
        this.population = population;
        this.oneCasePerPeople = oneCasePerPeople;
        this.oneDeathPerPeople = oneDeathPerPeople;
        this.oneTestPerPeople = oneTestPerPeople;
        this.activePerOneMillion = activePerOneMillion;
        this.recoveredPerOneMillion = recoveredPerOneMillion;
        this.criticalPerOneMillion = criticalPerOneMillion;
        this.affectedCountries = affectedCountries;
    }
}
