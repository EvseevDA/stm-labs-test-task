package com.github.evseevda.stmlabstesttask.businesslogicservice.core.http.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;


@Component
@RequiredArgsConstructor
public class HttpResponseWriter {

    private final ObjectMapper objectMapper;

    public void write(HttpResponse httpResponse) throws IOException {
        PrintWriter writer = httpResponse.writer();
        writer.print(objectMapper.writeValueAsString(httpResponse.getBody()));
        writer.flush();
    }

}
