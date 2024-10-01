import java.util.ArrayList;
import java.util.List;

public class Outfit {
    private List<ClothingItem> items;

    public Outfit() {
        this.items = new ArrayList<>();
    }

    public void addClothingItem(ClothingItem item) {
        items.add(item);
    }

    public void display() {
        if (items.isEmpty()) {
            System.out.println("No suitable outfit found.");
        } else {
            for (ClothingItem item : items) {
                System.out.println(item);
            }
        }
    }
}
