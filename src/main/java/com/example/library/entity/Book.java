package com.example.library.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    @JoinColumn(name = "author_id")
    @ManyToOne
    private Author author;

    @NonNull
    @JoinColumn(name = "language_id")
    @ManyToOne
    private Language language;
}
