package com.thomasp.a30211522.magiplexfilmrentals.API;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class APIConfiguration {

    @JsonProperty("images")
    public FilmPosters filmPosters;
    @JsonProperty("change_keys")
    public List<String> changeKeys = null;

}
