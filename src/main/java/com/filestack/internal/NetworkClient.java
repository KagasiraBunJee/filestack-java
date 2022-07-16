package com.filestack.internal;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import java.io.IOException;

public class NetworkClient {

  private final OkHttpClient client;
  private final Gson gson;
  private okhttp3.Call currentCall;

  public NetworkClient(OkHttpClient okHttpClient, Gson gson) {
    this.client = okHttpClient;
    this.gson = gson;
  }

  <T> Response<T> call(Request request, Class<T> resultClass) throws IOException {
    currentCall = client.newCall(request);
    okhttp3.Response response = currentCall.execute();
    if (!response.isSuccessful()) {
      return Response.error(response);
    }
    ResponseBody body = response.body();
    try {
      T data = gson.fromJson(body.charStream(), resultClass);
      return Response.success(data, response);
    } finally {
      body.close();
    }
  }

  Response<ResponseBody> call(Request request) throws IOException {
    currentCall = client.newCall(request);
    okhttp3.Response response = currentCall.execute();
    if (response.isSuccessful()) {
      return Response.success(response.body(), response);
    }
    return Response.error(response);
  }

  void cancel() {
    for (okhttp3.Call call : client.dispatcher().runningCalls()) {
      call.cancel();
    }
  }
}
