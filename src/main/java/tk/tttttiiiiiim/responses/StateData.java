package tk.tttttiiiiiim.responses;

@SuppressWarnings("all")
public class StateData
{
    public String state;
    public long updated;
    public long cases;
    public long todayCases;
    public long deaths;
    public long todayDeaths;
    public long recovered;
    public long active;
    public long casesPerOneMillion;
    public long deathPerOneMillion;
    public long tests;
    public long testsPerOneMillion;
    public long population;

    public StateData(String state, long updated, long cases, long todayCases, long deaths, long todayDeaths, long recovered, long active, long casesPerOneMillion, long deathPerOneMillion, long tests, long testsPerOneMillion, long population)
    {
        this.state = state;
        this.updated = updated;
        this.cases = cases;
        this.todayCases = todayCases;
        this.deaths = deaths;
        this.todayDeaths = todayDeaths;
        this.recovered = recovered;
        this.active = active;
        this.casesPerOneMillion = casesPerOneMillion;
        this.deathPerOneMillion = deathPerOneMillion;
        this.tests = tests;
        this.testsPerOneMillion = testsPerOneMillion;
        this.population = population;
    }
}
