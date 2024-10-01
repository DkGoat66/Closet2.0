import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Closet {
    private Map<String, List<ClothingItem>> wardrobe;
    private WeatherService weatherService;
    private String lastFetchedWeather;

    public Closet() {
        this.wardrobe = new HashMap<>();
        this.weatherService = new WeatherService();
        this.lastFetchedWeather = "";
    }

    // Add clothing item to wardrobe, categorized by weather (convert WeatherType to String)
    public void addClothingItem(ClothingItem item) {
        String weatherKey = item.getSuitableWeather().name();  // Convert enum to String
        wardrobe.computeIfAbsent(weatherKey, k -> new ArrayList<>()).add(item);
    }

    // Generate an outfit based on the weather
    public Outfit generateOutfit(String weather) {
        Outfit outfit = new Outfit();
        List<ClothingItem> suitableItems = wardrobe.getOrDefault(weather, new ArrayList<>());
        if (!suitableItems.isEmpty()) {
            for (ClothingItem item : suitableItems) {
                outfit.addClothingItem(item);
            }
        }
        return outfit;
    }

    // Fetch the weather only if not already fetched
    public String getWeather(String location) {
        if (lastFetchedWeather.isEmpty()) {
            lastFetchedWeather = weatherService.getWeather(location);
        }
        return lastFetchedWeather;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Closet closet = new Closet();

        // Allow users to input their clothing items
        System.out.println("Enter your clothing items (type 'done' to finish):");
        while (true) {
            System.out.print("Enter clothing item name (or 'done' to finish): ");
            String itemName = scanner.nextLine();
            if (itemName.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter clothing category (e.g., casual, formal, etc.): ");
            String category = scanner.nextLine();

            System.out.print("Enter suitable weather (SUNNY, COLD, RAINY, SNOWY, WINDY): ");
            String weatherInput = scanner.nextLine().toUpperCase();

            WeatherType weatherType;
            try {
                weatherType = WeatherType.valueOf(weatherInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid weather type! Try again.");
                continue;
            }

            // Add the clothing item to the closet
            closet.addClothingItem(new ClothingItem(itemName, category, weatherType));
            System.out.println("Clothing item added: " + itemName);
        }

        // Now that we have clothes, ask for location to fetch the weather
        System.out.print("Enter your location for weather info: ");
        String location = scanner.nextLine();

        // Fetch weather data
        String currentWeather = closet.getWeather(location);
        System.out.println("Current weather: " + currentWeather);

        // Generate and display the outfit
        Outfit outfit = closet.generateOutfit(currentWeather.toUpperCase());  // Ensure case consistency
        System.out.println("Your outfit suggestion:");
        outfit.display();

        scanner.close();
    }
}
