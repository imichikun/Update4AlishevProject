package alishev_mvc5.controller;

import alishev_mvc5.dao.PersonDaoImpl;
import alishev_mvc5.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class MyController {
    private PersonDaoImpl personDaoImpl;

    @Autowired
    public MyController(PersonDaoImpl personDaoImpl) {
        this.personDaoImpl = personDaoImpl;
    }

    @GetMapping("/")
    public String getAll(Model model){
        model.addAttribute("peopleAll", personDaoImpl.getAll());
        return "1people_all";
    }

    @GetMapping("/{id}")
    public String getPersonById(@PathVariable("id") int id, Model model){
        model.addAttribute("personModel", personDaoImpl.getById(id));
        return "2person_by_id";
    }

    @GetMapping("/new")
    public String getNewPerson(Model model){
        Person newPerson = new Person();
        newPerson.setId(PersonDaoImpl.ID++);            // заранее задаём ID для вновь создаваемого Person, чтобы потом
        model.addAttribute("newPerson", newPerson);  // по нему отделять только что созданный объект от существующего
        return "3create_person";
    }

    @PostMapping("/")
    public String createPerson(@ModelAttribute("newPerson") @Valid Person person, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "3create_person";

        personDaoImpl.save(person);
        return "redirect:/";
    }

    @PutMapping("/{id}")
    public String updatePerson(@PathVariable("id") int id, Model model){
        Person updatedPerson = personDaoImpl.getById(id);
        model.addAttribute("newPerson", updatedPerson); // после того как обновили поля Person, называем наш атрибут
        return "3create_person";                          // также как и в методе /new - (newPerson), чтобы повторно
    }                                                     // использовать view "3create_new"

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id){
        Person deletePerson = personDaoImpl.getById(id);
        personDaoImpl.deletePerson(deletePerson);
        return "redirect:/";
    }
}