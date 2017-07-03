package util;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Serves as singleton holder for the OkHttp client and Retrofit services.
 * We only want to instantiate these classes once per app.
 */
public class Networking {
    private static OkHttpClient httpClient;
    private static FilestackService.Cdn cdnService;
    private static FilestackService.Api apiService;
    private static FilestackService.Process processService;

    public static OkHttpClient getHttpClient() {
        if (httpClient == null) {
            httpClient = new OkHttpClient.Builder()
                    .addInterceptor(new HeaderInterceptor())
                    .build();
        }
        return httpClient;
    }

    public static FilestackService.Cdn getCdnService() {
        if (cdnService == null) {
            Retrofit retrofit = getRetrofitBuilder().baseUrl(FilestackService.Cdn.URL).build();
            cdnService = retrofit.create(FilestackService.Cdn.class);
        }
        return cdnService;
    }

    public static FilestackService.Api getApiService() {
        if (apiService == null) {
            Retrofit retrofit = getRetrofitBuilder().baseUrl(FilestackService.Api.URL).build();
            apiService = retrofit.create(FilestackService.Api.class);
        }
        return apiService;
    }

    public static FilestackService.Process getProcessService() {
        if (processService == null) {
            Retrofit retrofit = getRetrofitBuilder().baseUrl(FilestackService.Process.URL).build();
            processService = retrofit.create(FilestackService.Process.class);
        }
        return processService;
    }

    public static void setCustomClient(OkHttpClient client) {
        if (client == null)
            return;
        httpClient = client;
        invalidate();
    }

    public static void removeCustomClient() {
        httpClient = null;
        invalidate();
    }

    private static Retrofit.Builder getRetrofitBuilder() {
        return new Retrofit.Builder()
                .client(getHttpClient())
                .addConverterFactory(GsonConverterFactory.create());
    }

    /**
     * Sets the services to null so they'll be recreated.
     */
    private static void invalidate() {
        cdnService = null;
        apiService = null;
        processService = null;
    }
}