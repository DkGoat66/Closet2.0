package come.example;

public class ClothingItem {
    private String name;
    private String category;
    private WeatherType suitableWeather;

    public ClothingItem(String name, String category, WeatherType suitableWeather) {
        this.name = name;
        this.category = category;
        this.suitableWeather = suitableWeather;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public WeatherType getSuitableWeather() {
        return suitableWeather;
    }

    @Override
    public String toString() {
        return name + " (" + category + ")";
    }
}
