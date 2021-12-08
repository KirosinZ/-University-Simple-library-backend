package com.example.library.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "full_name")
    private String fullName;

    @NonNull
    @JoinColumn(name = "country_id")
    @ManyToOne
    private Country country;
}
