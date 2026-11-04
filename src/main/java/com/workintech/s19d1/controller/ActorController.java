package com.workintech.s19d1.controller;

import com.workintech.s19d1.dto.ActorRequest;
import com.workintech.s19d1.dto.ActorResponse;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.ActorService;
import com.workintech.s19d1.util.Converter;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/actor")
public class ActorController {
    private ActorService actorService;

    @GetMapping
    public List<ActorResponse> findAll() {
        List<Actor> actors = actorService.findAll();
        return Converter.actorResponseConvert(actors);
    }

    @GetMapping("/{id}")
    public ActorResponse findById(@PathVariable Long id) {
        Actor actor = actorService.findById(id);
        return Converter.actorResponseConvert(actor);
    }

    @PostMapping
    public ActorResponse addActor(@Validated @RequestBody ActorRequest actorRequest) {
        Actor actor = actorRequest.getActor();
        List<Movie> movies = actorRequest.getMovies();
        for (Movie movie : movies) {
            actor.addMovie(movie);
        }
        Actor savedActor = actorService.save(actor);
        return Converter.actorResponseConvert(savedActor);

    }
    @PutMapping("/{id}")
    public ActorResponse update(@PathVariable Long id, @Validated @RequestBody Actor actor) {
        Actor foundActor = actorService.findById(id);
        foundActor.setFirstName(actor.getFirstName());
        foundActor.setLastName(actor.getLastName());
        foundActor.setGender(actor.getGender());
        foundActor.setBirthDate(actor.getBirthDate());

        if (actor.getMovies() != null) {
            foundActor.setMovies(actor.getMovies());
        }

        Actor savedActor = actorService.save(foundActor);
        return Converter.actorResponseConvert(savedActor);
    }

    @DeleteMapping("/{id}")
    public ActorResponse delete(@PathVariable Long id) {
        Actor actor = actorService.findById(id);
        actorService.delete(actor);
        return Converter.actorResponseConvert(actor);
    }
}
