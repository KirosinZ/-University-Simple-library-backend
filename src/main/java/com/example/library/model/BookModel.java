package com.example.library.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class BookModel {
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "authorId")
    private Long authorId;
    @JsonProperty(value = "languageId")
    private Long languageId;
}
