package com.clinik.demo.service;

import com.clinik.demo.entity.Person;
import com.clinik.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person findPersonById(Long id) {
        return personRepository.findById(id).orElse(null);
    };

    public List<Person> findAllPeople() {
        return personRepository.findAll();
    }

    public void savePerson(Person person) {
        personRepository.save(person);
    }

    public void deletePersonById(Long id) {
        personRepository.deleteById(id);
    }
}
