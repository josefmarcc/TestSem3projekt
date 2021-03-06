package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import fetchers.HarryPotterFetcher;

@Path("hp")
public class HpResource {

    @Context
    private UriInfo context;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static ExecutorService es = Executors.newCachedThreadPool();
    private static String cachedResponse;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello anonymous\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String allChars() throws IOException, Exception {
        String result = HarryPotterFetcher.hpFetch(es, GSON, "http://hp-api.herokuapp.com/api/characters");
        cachedResponse = result;
        return result;
    }
}
