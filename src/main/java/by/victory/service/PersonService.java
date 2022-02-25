package by.victory.service;

import by.victory.entity.PersonEntity;
import by.victory.exception.PersonNotFoundException;
import by.victory.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository repository;

    @Autowired
    public PersonService(PersonRepository repository){
        this.repository=repository;
    }


    public List<PersonEntity> getAll(){
        return (List<PersonEntity>) repository.findAll();
    }

    public void save (PersonEntity person){
        repository.save(person);
    }

    public PersonEntity get(Integer id) throws PersonNotFoundException {
        Optional<PersonEntity> person=repository.findById(id);
        if (person.isPresent()){
            return person.get();
        }
        throw new PersonNotFoundException("Could not find any person with id "+id);
    }

    public void delete(Integer id) throws PersonNotFoundException {
        Long count=repository.countById(id);
        if (count==null ||count==0){
            throw new PersonNotFoundException("Could not find any person with id "+id);
        }
        repository.deleteById(id);
    }
}
