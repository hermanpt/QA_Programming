import com.google.gson.Gson;
import models.Distributors;
import models.Inventory;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        Gson gson = new Gson();
        Inventory inventory = new Inventory();
        Distributors distributors = new Distributors();

        final double stockThreshold = 0.25;

        //This is required to allow GET and POST requests with the header 'content-type'
        options("/*",
                (request, response) -> {
                    response.header("Access-Control-Allow-Headers",
                            "content-type");

                    response.header("Access-Control-Allow-Methods",
                            "GET, POST");


                    return "OK";
                });

        //This is required to allow the React app to communicate with this API
        before((request, response) -> response.header("Access-Control-Allow-Origin", "http://localhost:3000"));

        //Returns JSON containing the candies for which the stock is less than 25% of it's capacity
        get("/low-stock", (request, response) -> inventory.getItemsUnderPercentCapacity(stockThreshold), gson::toJson);

        //Returns JSON containing the total cost of restocking candy
        post("/restock-cost", (request, response) -> distributors.getRestockCost(request.body()), gson::toJson);



    }
}
