package com.keyin.airportcli.client;

import java.io.IOException;

public interface HttpTransport {
    HttpResult get(String url) throws IOException, InterruptedException;
}
