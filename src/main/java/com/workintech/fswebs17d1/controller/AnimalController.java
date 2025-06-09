package com.workintech.fswebs17d1.controller;

import com.workintech.fswebs17d1.entity.Animal;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;


import java.util.*;


@RestController
@RequestMapping(path = "/workintech/animal")

public class AnimalController {

    private Map<Integer, Animal> animals;


    @PostConstruct
    public void loadAll(){
        System.out.println("postconstruct çalıştı");
        this.animals = new HashMap<>();
        this.animals.put(1, new Animal(1,"maymun"));
    }

    @GetMapping
    public List<Animal> getAllAnimals() {
        return new ArrayList<>(animals.values());
    }

    @GetMapping("/{id}")
    public Animal getAnimalById(@PathVariable("id") int id) {
        if(id<0){
            return null;
        }
        return this.animals.get(id);
    }


    @PostMapping
    public void addAnimal(@RequestBody Animal animal){
        this.animals.put(animal.getId(), animal);
    }

    @PutMapping("/{id}")
    public Animal updateAnimal(@PathVariable int id, @RequestBody Animal updatedAnimal) {
        if (animals.containsKey(id)) {
            animals.put(id, updatedAnimal);
            return updatedAnimal;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Animal deleteAnimal(@PathVariable int id) {
        return animals.remove(id);
    }


}
