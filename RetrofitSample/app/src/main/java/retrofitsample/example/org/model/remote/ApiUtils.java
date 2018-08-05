package retrofitsample.example.org.model.remote;

/**
 * Created by Rafael S Martin on 24/03/2018.
 */

public class ApiUtils {

    private ApiUtils(){}

    public static final String BASE_URL = "https://randomuser.me/api/";

    public static APIService getApiService(){
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
