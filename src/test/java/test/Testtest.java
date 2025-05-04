package test;

import com.example.invoker.ApiClient;
import com.example.invoker.ApiException;
import com.example.invoker.ApiResponse;
import com.example.model.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class Testtest {
    ApiClient apiClient = new ApiClient();

    @Test
    public void testoftest() throws ApiException {
        OkHttpClient client = new OkHttpClient();

        User user = new User();
        user.setId(1);
        user.setName("huan");

        Request request = new Request.Builder()
                .url(apiClient.getBasePath() + "/users")
                .build();

        ApiResponse<User> response = apiClient.execute(client.newCall(request), User.class);

        assertThat("Expected: 200", response.getStatusCode(), is(200));
        assertThat(response.getData(), is(user));
    }

}
