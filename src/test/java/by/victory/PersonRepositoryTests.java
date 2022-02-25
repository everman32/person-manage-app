package by.victory;

import by.victory.entity.PersonEntity;
import by.victory.repository.PersonRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace =AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class PersonRepositoryTests {
    private final PersonRepository repository;

    @Autowired
    public PersonRepositoryTests(PersonRepository repository){
        this.repository=repository;
    }


    @Test
    public void testCreate() throws ParseException {
        PersonEntity person=new PersonEntity();

        person.setName("Stepan");
        person.setPatronymic("Gennadievich");
        person.setSurname("Frolov");

        java.util.Date utilDate = new SimpleDateFormat("dd.MM.yyyy").parse("12.03.1980");
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        person.setBirthDate(sqlDate);

        person.setEmail("stepan.frolov@gmail.com");

        person.setCityList(new HashSet<>(
                Arrays.asList("Mogilev", "Nesvig", "Brest", "Polotsk", "Gomel")));

        person.setPassportNumber("МР2049214");
        PersonEntity savedPerson=repository.save(person);

        Assertions.assertThat(savedPerson).isNotNull();
        Assertions.assertThat(savedPerson.getId()).isGreaterThan(0);
    }

    @Test
    public void testGetAll(){
        Iterable<PersonEntity> people = repository.findAll();
        Assertions.assertThat(people).hasSizeGreaterThan(0);

        people.forEach(System.out::println);
    }

    @Test
    public void testGet(){
        Integer personId=2;
        Optional<PersonEntity> person=repository.findById(personId);

        Assertions.assertThat(person).isPresent();
        System.out.println(person.get());
    }

    @Test
    public void testUpdate(){
        Integer personId=1;
        Optional<PersonEntity> optionalPerson = repository.findById(personId);
        Assertions.assertThat(optionalPerson).isPresent();

        PersonEntity person=optionalPerson.get();

        person.setName("Alex");
        repository.save(person);

        PersonEntity updatedPerson =repository.findById(personId).get();
        Assertions.assertThat(updatedPerson.getName()).isEqualTo("Alex");
    }

    @Test
    public void testDelete(){
        Integer personId=2;
        repository.deleteById(personId);

        Optional<PersonEntity> person= repository.findById(personId);
        Assertions.assertThat(person).isNotPresent();
    }
}
