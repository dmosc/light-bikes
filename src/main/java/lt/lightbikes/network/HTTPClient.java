package lt.lightbikes.network;

import okhttp3.*;

import java.util.concurrent.ExecutionException;

public final class HTTPClient extends OkHttpClient {
  public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
  private static String SERVER_URL;

  public HTTPClient(String SERVER_URL) {
    HTTPClient.SERVER_URL = SERVER_URL;
  }

  public Response query(String route) throws ExecutionException, InterruptedException {
    CallBackFuture response = new CallBackFuture();
    RequestBody body = RequestBody.create(route, JSON);

    Request request =
            new Request.Builder()
                    .url(SERVER_URL + route)
                    //                .post(body)
                    .build();

    this.newCall(request).enqueue(response);

    return response.get();
  }
}
