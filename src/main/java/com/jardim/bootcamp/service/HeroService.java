package com.jardim.bootcamp.service;

import com.jardim.bootcamp.document.Hero;
import com.jardim.bootcamp.repository.HeroRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HeroService {
    private final HeroRepository heroRepo;

    public HeroService(HeroRepository heroRepo){
        this.heroRepo = heroRepo;
    }

    public Flux<Hero> findAll(){
        return Flux.fromIterable(this.heroRepo.findAll());
    }

    public Mono<Hero> findById(String id){
        return Mono.justOrEmpty(heroRepo.findById(id));
    }

    public Mono<Hero> save(Hero hero){
        return Mono.justOrEmpty(heroRepo.save(hero));
    }

    public Mono<Boolean> deleteById( String id){
        heroRepo.deleteById(id);
        return Mono.justOrEmpty(true);
    }
}
