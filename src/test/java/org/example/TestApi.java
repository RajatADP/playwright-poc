package org.example;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for simple App.
 */
public class TestApi extends BaseApiClass
{
    private APIRequestContext request;
    private String baseUrl = "https://reqres.in";

    @BeforeAll
    void createRequestContext() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");

        request = playwright.request().newContext(new APIRequest.NewContextOptions()
                .setBaseURL(baseUrl)
                .setExtraHTTPHeaders(headers));
    }


    @Test
    void shouldSearchWiki() {
        Map<String, String> data = new HashMap<String, String>();
        data.put("name", "morpheus");
        data.put("job", "leader");
        APIResponse response = request.post("/api/users", RequestOptions.create().setData(data));
        assertTrue(response.ok());
    }

    @AfterAll
    void closeApiContext() {
        if (request != null) {
            request.dispose();
            request = null;
        }
    }
}
