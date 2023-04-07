package com.example.openaipractice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


//This is a class for JSON data
@Data
public class Roundtable {
    @JsonProperty("topic")
    String topic;

    @JsonProperty("context")
    String context;

    @JsonProperty("last_Script")
    String last_Script;

    @JsonProperty("AI_names")
    private List<String> AI_names;

    @JsonProperty("AI_systems")
    private List<String> AI_systems;

    @JsonProperty("next_Baton")
    private int next_Baton;
}
//topic: $('#topic').val(),
//conversation: $('#conservation').val(),
//AI_names: AI_names,
//AI_systems: AI_profiles