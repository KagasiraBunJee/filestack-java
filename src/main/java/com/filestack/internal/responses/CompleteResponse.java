package com.filestack.internal.responses;

/** Response from multipart complete endpoint. */
@SuppressWarnings("unused")
public class CompleteResponse {
  private String url;
  private String handle;
  private String filename;
  private long size;
  private String mimetype;
  private String key;

  public String getUrl() {
    return url;
  }

  public String getHandle() {
    return handle;
  }

  public String getFilename() {
    return filename;
  }

  public long getSize() {
    return size;
  }

  public String getMimetype() {
    return mimetype;
  }

  public String getKey() {
    return key;
  }
}
