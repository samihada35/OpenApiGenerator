package apiTests;

import com.example.invoker.ApiClient;
import com.example.invoker.ApiException;
import com.example.invoker.ApiResponse;
import com.example.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;

public class GetAllUsersTest {
    ApiClient apiClient = new ApiClient();

    @Test
    public void getAllUsersPositiveTest() throws ApiException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(apiClient.getBasePath() + "/users")
                .build();

        TypeReference<List<User>> typeRef = new TypeReference<>(){};

        ApiResponse<List<User>> response = apiClient.execute(client.newCall(request), typeRef.getType());

        assertThat("Expected: 200", response.getStatusCode(), is(200));

        List<User> users = response.getData();
        assertFalse(users.isEmpty());

        User user = new User();
        user.setId(1);
        user.setName("Pedro");
        user.setEmail("test@test.test");

        assertThat(users.getFirst(), is(user));
    }

}
