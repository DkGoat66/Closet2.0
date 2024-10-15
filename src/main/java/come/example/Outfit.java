package come.example;

import java.util.ArrayList;
import java.util.List;

public class Outfit {
    private List<ClothingItem> items;
    private String weather; // New attribute to store weather condition

    public Outfit(String weather) {
        this.items = new ArrayList<>();
        this.weather = weather; // Set weather during outfit creation
    }

    public void addClothingItem(ClothingItem item) {
        items.add(item);
    }

    public List<ClothingItem> getItems() {
        return items; // Getter for items
    }

    public String getWeather() {
        return weather; // Getter for weather
    }

    @Override
    public String toString() {
        if (items.isEmpty()) {
            return "No suitable outfit found.";
        }

        StringBuilder outfitDetails = new StringBuilder("Outfit for " + weather + ": \n");
        for (ClothingItem item : items) {
            outfitDetails.append(item.toString()).append("\n");
        }
        return outfitDetails.toString();
    }
}
