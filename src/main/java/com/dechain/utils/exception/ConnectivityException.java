package com.dechain.utils.exception;

import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * ConnectivityException wraps errors due to connectivity issues with the server.
 */
public class ConnectivityException extends CosmosException {
  /**
   * Initializes exception with its message attribute.
   * @param resp the server response used to create error message
   */
  public ConnectivityException(Response resp) {
    super(formatMessage(resp));
  }

  /**
   * Parses the the server response into a detailed error message.
   * @param resp the server response
   * @return detailed error message
   */
  private static String formatMessage(Response resp) {
    String s =
        "Response HTTP header field Chain-Request-ID is unset. There may be network issues. Please check your local network settings.";
    // TODO(kr): include client-generated reqid here once we have that.
    String body;
    try {
      body = resp.body().string();
    } catch (IOException ex) {
      body = "[unable to read response body: " + ex.toString() + "]";
    }
    return String.format("%s status=%d body=%s", s, resp.code(), body);
  }
}
