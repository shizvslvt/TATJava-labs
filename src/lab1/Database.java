package lab1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Database {
    public boolean status;
    private Estates estates;

    public Database() {
        this.status = true;
        this.estates = Estates.getInstance();
    }

    public void loadDataFromDatabase() {
        if (status) {
            try (FileReader reader = new FileReader("lab1-estates.json")) {
                estates.clearEstates();
                JSONArray estatesArray = new JSONArray(new JSONTokener(reader));

                for (int i = 0; i < estatesArray.length(); i++) {
                    JSONObject estateObj = estatesArray.getJSONObject(i);
                    int id = estateObj.getInt("id");
                    String title = estateObj.getString("title");
                    double price = estateObj.getDouble("price");
                    String city = estateObj.getString("city");

                    estates.addEstate(new Estate(id, title, price, city));
                }
                System.out.println("Данные успешно загружены из базы данных (JSON).");
            } catch (IOException e) {
                System.out.println("Ошибка при чтении файла JSON: " + e.getMessage());
            }
        } else {
            System.out.println("Нет подключения к базе данных.");
        }
    }

    public void saveDataToDatabase() {
        if (status) {
            List<Estate> estateList = estates.getEstateList();
            JSONArray estatesArray = new JSONArray();

            for (Estate estate : estateList) {
                JSONObject estateObj = new JSONObject();
                estateObj.put("id", estate.id);
                estateObj.put("title", estate.title);
                estateObj.put("price", estate.price);
                estateObj.put("city", estate.city);
                estatesArray.put(estateObj);
            }

            try (FileWriter file = new FileWriter("lab1-estates.json")) {
                file.write(estatesArray.toString(4));
                System.out.println("Данные успешно сохранены в базу данных (JSON).");
            } catch (IOException e) {
                System.out.println("Ошибка при записи в файл JSON: " + e.getMessage());
            }
        } else {
            System.out.println("Нет подключения к базе данных.");
        }
    }

    public void toggleStatus() {
        status = !status;
        System.out.println("Статус базы данных изменён: " + (status ? "Подключена" : "Отключена"));
    }
}