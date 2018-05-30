//package mta.edu.vn.jdbctojpa.jdbctojpa.data;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.ManyToMany;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//@Entity
//public class Course {
//
//    @Id
//    @GeneratedValue //auto incre
//    private Integer id;
//
//    private String name;
//
//    @ManyToMany(mappedBy = "courses")
//    private List<Person> persons;
//
//    public void addPerson(Person person) {
//        if(persons == null) {
//            persons = new ArrayList<>();
//        }
//
//        this.persons.add(person);
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public List<Person> getPersons() {
//        return persons;
//    }
//
//    public void setPersons(List<Person> persons) {
//        this.persons = persons;
//    }
//
//    @Override
//    public String toString() {
//        return "Course{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", persons=" + persons +
//                '}';
//    }
//
//    public Course() {
//    }
//}
