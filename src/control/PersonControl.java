package control;

import model.BEAN.PersonBEAN;
import model.DAO.PersonDAO;

import java.util.ArrayList;

public class PersonControl {

    private PersonDAO personDAO = new PersonDAO();

    public boolean insertPerson(PersonBEAN person){
        return personDAO.insertPerson(person);
    }

    public PersonBEAN getPerson(String name){
        return personDAO.getPerson(name);
    }

    public ArrayList<PersonBEAN> getAllPersons(){
        return personDAO.getAllPersons();
    }

    public boolean updatePerson(PersonBEAN person, int id){
        return personDAO.updatePerson(person, id);
    }

    public boolean deletePerson(int id){
        return personDAO.deletePerson(id);
    }
}
