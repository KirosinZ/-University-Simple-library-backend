package com.example.library.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@RequiredArgsConstructor
public class CountryModel {
    @JsonProperty(value = "name")
    private String name;

}
