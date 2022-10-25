package alishev_mvc5.dao;

import alishev_mvc5.entity.Person;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDaoImpl {
    public static int ID = 0;
    private static final List<Person> people;

    static{
         people = new ArrayList<>(List.of(new Person(ID++, "Ivan", 32, "ivanushka@mail.ru"),
                new Person(ID++, "Petr", 40, "apex@protonmail.com"),
                new Person(ID++, "Nail", 35, "alishev@gmail.com")));
    }

    public List<Person> getAll() {
        return people;
    }

    public Person getById(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        for (Person pers: people)
            if (pers.getId()== person.getId()) {
                people.set(people.indexOf(pers), person);
                return;
            }

        people.add(person);
    }

    public void deletePerson(Person deletePerson) {
        people.remove(deletePerson);                        // удаление по объекту (не по индексу)
    }
}