package com.whois.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whois.api.model.WhoisResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Controller
public class WhoisController {

  @Value("${whois.api-key}")
  private String apiKey;

  private ObjectMapper objectMapper = new ObjectMapper();

  @GetMapping(value = {"/", ""})
  public String index(@RequestParam String query, Model model) throws Exception {
    WhoisResponse response = null;

    if (query != null && !query.isEmpty()) {
      StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B551505/whois/ip_address");
      urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + apiKey);
      urlBuilder.append("&" + URLEncoder.encode("query", "UTF-8") + "=" + URLEncoder.encode(query, "UTF-8"));
      urlBuilder.append("&" + URLEncoder.encode("answer", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));

      URL url = new URL(urlBuilder.toString());
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Content-type", "application/json");

      BufferedReader rd;
      if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
        rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      } else {
        rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
      }
      StringBuilder sb = new StringBuilder();
      String line;
      while ((line = rd.readLine()) != null) {
        sb.append(line);
      }
      rd.close();
      conn.disconnect();

      response = objectMapper.readValue(sb.toString(), WhoisResponse.class);
    }

    model.addAttribute("whoisResponse", response);
    return "index";
  }


}
