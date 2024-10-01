public class WeatherService {
    // Mock function to simulate fetching weather data based on a location
    public String getWeather(String location) {
        // Simulated weather data based on location
        switch (location.toLowerCase()) {
            case "london":
                return WeatherType.RAINY.name().toLowerCase();
            case "new york":
                return WeatherType.SUNNY.name().toLowerCase();
            case "moscow":
                return WeatherType.COLD.name().toLowerCase();
            default:
                return WeatherType.SUNNY.name().toLowerCase(); // default weather
        }
    }
}
