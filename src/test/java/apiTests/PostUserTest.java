package apiTests;

import com.example.invoker.ApiClient;
import com.example.invoker.ApiException;
import com.example.invoker.ApiResponse;
import com.example.model.User;
import com.example.model.UserCreateRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PostUserTest {
    ApiClient apiClient = new ApiClient();

    @Test
    public void PostUserPositiveTest() throws JsonProcessingException, ApiException {
        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json");

        UserCreateRequest user = new UserCreateRequest();
        user.setName("Ioan");
        user.setEmail("ioan@google.com");

        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(user);

        RequestBody requestBody = RequestBody.create(userJson, JSON);

        Request request = new Request.Builder()
                .url(apiClient.getBasePath() + "/users")
                .post(requestBody)
                .addHeader("Content-Type", "application/json")
                .build();

        ApiResponse<User> response = apiClient.execute(client.newCall(request),User.class);
        assertThat("Expected: 201", response.getStatusCode(),is(201));
    }
}
