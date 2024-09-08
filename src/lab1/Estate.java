package lab1;

public class Estate {
    public int id;
    public String title;
    public double price;
    public String city;

    public Estate(int id, String title, double price, String city) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.city = city;
    }

    @Override
    public String toString() {
        return "ID: " + id +"\t"+ title + ", цена: " + price + ", город: " + city;
    }
}