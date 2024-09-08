package lab1;

import java.util.ArrayList;
import java.util.List;

// Singleton Pattern: Використовується, щоб мати лише один екземпляр класу Estates.
// Це дозволяє уникнути дублювання даних про нерухомість і гарантує, що в програмі буде лише один глобальний список об'єктів Estate.

public class Estates {
    private static Estates instance;
    private List<Estate> estateList;

    private Estates() {
        this.estateList = new ArrayList<>();
    }

    public static Estates getInstance() {
        if (instance == null) {
            instance = new Estates();
        }
        return instance;
    }

    public List<Estate> getEstateList() {
        return estateList;
    }

    public void addEstate(Estate estate) {
        estateList.add(estate);
    }

    public void clearEstates() {
        estateList.clear();
    }
}