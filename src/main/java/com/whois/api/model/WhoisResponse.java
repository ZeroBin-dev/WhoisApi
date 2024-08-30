package com.whois.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WhoisResponse {

  @JsonProperty("response")
  private Response response;

  @Getter
  @Setter
  public static class Response {
    @JsonProperty("result")
    private Result result;

    @JsonProperty("whois")
    private Whois whois;
  }

  @Getter
  @Setter
  public static class Result {
    @JsonProperty("result_code")
    private String resultCode;

    @JsonProperty("result_msg")
    private String resultMsg;
  }

  @Getter
  @Setter
  public static class Whois {
    @JsonProperty("query")
    private String query;

    @JsonProperty("queryType")
    private String queryType;

    @JsonProperty("registry")
    private String registry;

    @JsonProperty("countryCode")
    private String countryCode;

    @JsonProperty("korean")
    private LanguageInfo korean;

    @JsonProperty("english")
    private LanguageInfo english;
  }

  @Getter
  @Setter
  public static class LanguageInfo {
    @JsonProperty("ISP")
    private Isp isp;

    @JsonProperty("user")
    private User user;
  }

  @Getter
  @Setter
  public static class Isp {
    @JsonProperty("netinfo")
    private NetInfo netinfo;

    @JsonProperty("techContact")
    private TechContact techContact;
  }

  @Getter
  @Setter
  public static class User {
    @JsonProperty("netinfo")
    private NetInfo netinfo;

    @JsonProperty("techContact")
    private TechContact techContact;
  }

  @Getter
  @Setter
  public static class NetInfo {
    @JsonProperty("range")
    private String range;

    @JsonProperty("prefix")
    private String prefix;

    @JsonProperty("servName")
    private String servName;

    @JsonProperty("orgName")
    private String orgName;

    @JsonProperty("orgID")
    private String orgID;

    @JsonProperty("addr")
    private String addr;

    @JsonProperty("zipCode")
    private String zipCode;

    @JsonProperty("regDate")
    private String regDate;

    @JsonProperty("netType")
    private String netType;
  }

  @Getter
  @Setter
  public static class TechContact {
    @JsonProperty("name")
    private String name;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("email")
    private String email;
  }
}