package chipset.techtatva.network;

import chipset.techtatva.model.instagram.InstaFeed;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;


public class APIClient {
    //private static DataInterface dataInterface = null;
    private static InstaFeedInterface instaFeedInterface = null;

    public static InstaFeedInterface getInstagram() {
        if (instaFeedInterface == null) {
            String URL_INSTA = "https://api.instagram.com";
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(URL_INSTA)
                    .build();

            instaFeedInterface = restAdapter.create(InstaFeedInterface.class);
        }
        return instaFeedInterface;
    }

    public interface InstaFeedInterface {
        @GET("/v1/tags/techtatva15/media/recent?client_id=fd6b3100174e42d7aa7d546574e01c76")
        void getFeed(Callback<InstaFeed> instaFeedCallback);
    }
}