package by.victory.service;

import by.victory.entity.PersonEntity;
import by.victory.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


}
