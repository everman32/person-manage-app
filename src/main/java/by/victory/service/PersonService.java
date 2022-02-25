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
    @Autowired
    private PersonRepository repository;

    public List<PersonEntity> getAll(){
        return (List<PersonEntity>) repository.findAll();
    }

    public void save (PersonEntity person){
        repository.save(person);
    }

    public PersonEntity get(Integer id) throws PersonNotFoundException {
        Optional<PersonEntity> result=repository.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new PersonNotFoundException("Could not find any persons with id "+id);
    }
}
