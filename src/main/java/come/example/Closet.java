package come.example;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Closet {
    private Map<String, List<ClothingItem>> wardrobe = new HashMap<>();
    private List<ClothingItem> clothingItems = new ArrayList<>();
    private WeatherService weatherService;
    private String lastFetchedWeather;

    public Closet() {
        this.wardrobe = new HashMap<>();
        this.weatherService = new WeatherService();
        this.lastFetchedWeather = "";
    }

    // Add clothing item to wardrobe, categorized by weather (convert WeatherType to String)
    public void addClothingItem(ClothingItem item) {
        String weatherKey = item.getSuitableWeather().name(); // Convert enum to String
        wardrobe.computeIfAbsent(weatherKey, k -> new ArrayList<>()).add(item);
        clothingItems.add(item);
    }

    // Generate an outfit based on the weather
    public Outfit generateOutfit(String weather) {
        Outfit outfit = new Outfit(weather); // Pass weather to Outfit constructor
        List<ClothingItem> suitableItems = wardrobe.getOrDefault(weather, new ArrayList<>());
        for (ClothingItem item : suitableItems) {
            outfit.addClothingItem(item);
        }
        return outfit;
    }

    // Fetch the weather only if not already fetched
    public String getWeather(String location) {
        // Here, you might want to fetch the weather each time you need it
        // depending on your requirements.
        lastFetchedWeather = weatherService.getWeather(location);
        return lastFetchedWeather;
    }

    public List<ClothingItem> getAllClothingItems() {
        return clothingItems;
    }


}
