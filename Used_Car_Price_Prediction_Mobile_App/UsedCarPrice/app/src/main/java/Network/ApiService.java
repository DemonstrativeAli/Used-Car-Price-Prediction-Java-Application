package Network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import java.util.Map;

public interface ApiService {
    @POST("/predict")
    Call<Map<String, Object>> predictPrice(@Body Map<String, Object> requestBody);
}