package come.example;

import org.springframework.stereotype.Controller; // Use Controller instead of RestController
import org.springframework.ui.Model; // Import Model
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller // Changed from RestController to Controller to handle views
public class ClosetController {

    private Closet closet = new Closet();

    // Endpoint to show the navigation page (index)
    @GetMapping("/")
    public String showNavigation() {
        return "Homepage"; // Return the index.html view
    }

    // Endpoint to display the 'Add Clothing' form
    @GetMapping("/addClothing")
    public String showAddClothingForm() {
        return "addClothing"; // Return the addClothing.html view
    }

    // Endpoint to add clothing item via a POST request
    @PostMapping("/addClothing")
    public String addClothingItem(@RequestParam String name,
                                  @RequestParam String category,
                                  @RequestParam String weather,
                                  Model model) {
        // Logic to add clothing to the Closet object
        WeatherType weatherType;
        try {
            weatherType = WeatherType.valueOf(weather.toUpperCase());
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Invalid weather type.");
            return "addClothing"; // Return to form with error
        }

        ClothingItem item = new ClothingItem(name, category, weatherType);
        closet.addClothingItem(item);
        return "redirect:/clothingList"; // Redirect to clothing list page
    }

    // Endpoint to get the list of all clothing items and show it in a view
    @GetMapping("/clothingList")
    public String showClothingList(Model model) {
        // Add clothing items to the model
        List<ClothingItem> clothingItems = closet.getAllClothingItems();
        model.addAttribute("clothingItems", clothingItems);
        return "clothingList"; // Return the clothingList.html view
    }

    // Endpoint to display the outfit generation page
    @GetMapping("/generateOutfitPage")
    public String showGenerateOutfitForm() {
        return "generateOutfitPage"; // Return the generateOutfitPage.html view
    }

    // Endpoint to generate an outfit based on the weather and show it in a view
    @PostMapping("/generateOutfit")
    public String generateOutfit(@RequestParam String weather, Model model) {
        // Logic to generate an outfit based on the weather
        Outfit outfit = closet.generateOutfit(weather);
        model.addAttribute("outfit", outfit);
        return "outfitSuggestion"; // Return the outfitSuggestion.html view
    }
}
