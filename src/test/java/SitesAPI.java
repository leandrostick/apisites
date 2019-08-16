
import com.google.gson.Gson;

import java.util.Collection;
import static spark.Spark.*;

public class SitesAPI {
    public static void main(String[] args) {

        final ISiteService service = new SiteServiceMapImpl();

        get("/", (req,res) -> "Probandooooo SIisisi");

        get("/sites", (req,res) -> {
            res.type("application/json");
            Collection<Site> sites = service.getSites();
            return new Gson().toJson(new StandarResponse(StatusResponse.SUCCESS , new Gson().toJsonTree(sites)));
        });

        get("/sites/:id/categories" , (req,res) -> {
            res.type("application/json");
            Collection<Category> categories = service.getCategories(req.params(":id"));
            return new Gson().toJson(new StandarResponse(StatusResponse.SUCCESS , new Gson().toJsonTree(categories)));
        });
    }
}
