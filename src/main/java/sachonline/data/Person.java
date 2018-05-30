//package mta.edu.vn.jdbctojpa.jdbctojpa.data;
//
//import javax.persistence.*;
//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//@Entity
//@Table(name = "person")
//public class Person {
//
//    @Id
//    @GeneratedValue //auto incre
//    private Integer id;
//
//    @ManyToMany
//    private List<Course> courses;
//
//    public void addCourse(Course course) {
//        if(courses == null) {
//            courses = new ArrayList<>();
//        }
//
//        courses.add(course);
//    }
//
//    public List<Course> getCourses() {
//        return courses;
//    }
//
//    public void setCourses(List<Course> courses) {
//        this.courses = courses;
//    }
//
//    private String location, name;
//    private Date birthday;
//
//    public Person() {
//
//    }
//
//    @Override
//    public String toString() {
//        return "Person{" +
//                "id=" + id +
//                ", location='" + location + '\'' +
//                ", name='" + name + '\'' +
//                ", birthday=" + birthday +
//                '}';
//    }
//
//    public Person(Integer id, String location, String name, Date birthday) {
//        this.id = id;
//        this.location = location;
//        this.name = name;
//        this.birthday = birthday;
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
//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
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
//    public Date getBirthday() {
//        return birthday;
//    }
//
//    public void setBirthday(Date birthday) {
//        this.birthday = birthday;
//    }
//}
