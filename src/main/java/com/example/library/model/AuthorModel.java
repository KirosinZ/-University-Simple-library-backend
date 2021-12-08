package com.example.library.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@RequiredArgsConstructor
public class AuthorModel {
    @JsonProperty(value = "fullName")
    private String fullName;
    @JsonProperty(value = "countryId")
    private Long countryId;
}
