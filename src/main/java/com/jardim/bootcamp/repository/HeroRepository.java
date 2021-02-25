package com.jardim.bootcamp.repository;

import com.jardim.bootcamp.document.Hero;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface HeroRepository extends CrudRepository<Hero, String> {

}
