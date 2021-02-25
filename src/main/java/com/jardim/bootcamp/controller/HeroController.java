package com.jardim.bootcamp.controller;

import com.jardim.bootcamp.document.Hero;
import com.jardim.bootcamp.repository.HeroRepository;
import com.jardim.bootcamp.service.HeroService;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.jardim.bootcamp.constants.HeroesConstants.HEROES_ENDPOINT_LOCAL;

@RestController
@Slf4j
public class HeroController {

    HeroService heroService;
    HeroRepository heroRepository;

    //private static final Logger log = LoggerFactory.getLogger(HeroController.class);


    public HeroController(HeroService heroService, HeroRepository heroRepository) {
        this.heroService = heroService;
        this.heroRepository = heroRepository;
    }



    @GetMapping(HEROES_ENDPOINT_LOCAL)
    public Flux<Hero> getAllItems() {
        return heroService.findAll();
    }

    @GetMapping(HEROES_ENDPOINT_LOCAL+"/{id}")
    public Mono<ResponseEntity<Hero>> findById(@PathVariable String id){
        return heroService.findById(id)
                .map((item)-> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(HEROES_ENDPOINT_LOCAL)
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<Hero> createHero(@RequestBody Hero hero){
        return heroService.save(hero);
    }

    @DeleteMapping(HEROES_ENDPOINT_LOCAL+"/{id}")
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public Mono<HttpStatus> deleteById(@PathVariable String id){
        heroService.deleteById(id);
        return Mono.just(HttpStatus.NOT_FOUND);
    }
}
