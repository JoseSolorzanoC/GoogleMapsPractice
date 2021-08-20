package com.uteq.appmoviles.googlemapspractice.Models.Country;

import java.util.Map;

public class CountryAll {
    private String StatusMsg;
    private Map<String, CountryOne> Results;
    private int StatusCode;

    public CountryAll(String statusMsg, Map<String, CountryOne> results, int statusCode) {
        StatusMsg = statusMsg;
        Results = results;
        StatusCode = statusCode;
    }

    public String getStatusMsg() {
        return StatusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        StatusMsg = statusMsg;
    }

    public Map<String, CountryOne> getResults() {
        return Results;
    }

    public void setResults(Map<String, CountryOne> results) {
        Results = results;
    }

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int statusCode) {
        StatusCode = statusCode;
    }
}
