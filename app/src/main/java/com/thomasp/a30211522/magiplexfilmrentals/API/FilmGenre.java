package com.thomasp.a30211522.magiplexfilmrentals.API;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name"
})

public class FilmGenre {

    @JsonProperty("id")
    public int id;
    @JsonProperty("name")
    public String name;

}
