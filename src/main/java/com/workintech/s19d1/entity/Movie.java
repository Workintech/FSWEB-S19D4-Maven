package com.workintech.s19d1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="movie",schema="fsweb")
public class Movie {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="director_name")
    private String directorName;

    @Column(name = "rating")
    private int rating;

    @Column(name="release_date")
    private LocalDate releaseDate;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH,CascadeType.DETACH})
    @JoinTable(name="movie_actor",joinColumns=@JoinColumn(name="movie_id"),
            inverseJoinColumns=@JoinColumn(name="actor_id"))
    private List<Actor> actors =new ArrayList<>();

    public void addActor(Actor actor) {
        if (actors == null) {
            actors = new ArrayList<>();
        }
        actors.add(actor);
        // Karşı tarafın da listesine bu filmi eklemek için
        if (actor.getMovies() == null) {
            actor.setMovies(new ArrayList<>());
        }
        if (!actor.getMovies().contains(this)) {
            actor.getMovies().add(this);
        }
    }
}
