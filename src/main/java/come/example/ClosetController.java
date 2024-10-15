package come.example;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ClosetController {

    private Closet closet = new Closet();

    @GetMapping("/")
    public String index(Model model) {
        return "Homepage"; // This corresponds to index.html
    }

    @GetMapping("/Homepage")  // New mapping for the navigation page
    public String navigation() {
        return "Homepage"; // This corresponds to navigation.html
    }
    @GetMapping("addClothing")
    public String addClothingPage(){
        return "addClothing";
    }
    @PostMapping("/addClothing")
    public String addClothingItem(@RequestParam String name,
                                  @RequestParam String category,
                                  @RequestParam String weather,
                                  Model model) {
        WeatherType weatherType;
        try {
            weatherType = WeatherType.valueOf(weather.toUpperCase());
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Invalid weather type.");
            return "addClothing";
        }

        ClothingItem item = new ClothingItem(name, category, weatherType);
        closet.addClothingItem(item);
        model.addAttribute("message", "Added " + item);
        return "addClothing";
    }

    @GetMapping("/generateOutfitPage")
    public String generateOutfitPage() {
        return "generateOutfit"; // New page for generating outfits
    }

    @PostMapping("/generateOutfit")
    public String generateOutfit(@RequestParam String weather, Model model) {
        Outfit outfit = new Outfit(weather); // Create outfit with weather condition
        // Add logic to populate the outfit with clothing items based on weather
        model.addAttribute("outfit", outfit);
        return "outfit";  // Ensure this corresponds to the correct Thymeleaf template
    }

    @GetMapping("/clothingList")
    public String showclothingList(Model model) {
        List<ClothingItem> items = closet.getAllClothingItems(); // Make sure you have this method in Closet class
        model.addAttribute("clothingItems", items);
        return "clothingList"; // This corresponds to clothingList.html
    }

}
