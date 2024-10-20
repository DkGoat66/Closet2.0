package come.example;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // Use RestController instead of Controller
public class ClosetController {

    private Closet closet = new Closet();

    // Endpoint to add clothing item via a POST request
    @PostMapping("/addClothing")
    public String addClothingItem(@RequestParam String name,
                                  @RequestParam String category,
                                  @RequestParam String weather) {
        WeatherType weatherType;
        try {
            weatherType = WeatherType.valueOf(weather.toUpperCase());
        } catch (IllegalArgumentException e) {
            return "Error: Invalid weather type.";
        }

        ClothingItem item = new ClothingItem(name, category, weatherType);
        closet.addClothingItem(item);
        return "Added " + item;
    }

    // Endpoint to generate an outfit based on the weather
    @PostMapping("/generateOutfit")
    public String generateOutfit(@RequestParam String weather) {
        Outfit outfit = closet.generateOutfit(weather);
        return outfit.toString();
    }

    // Endpoint to get the list of all clothing items
    @GetMapping("/clothingList")
    public List<ClothingItem> showClothingList() {
        return closet.getAllClothingItems();
    }
}
