//package mta.edu.vn.jdbctojpa.jdbctojpa.data;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Repository;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.util.List;
//
//@Repository
//public class PersonJdbcDao {
//
//    public class CustomRowMapper implements RowMapper<Person>{
//
//        @Override
//        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
//            Person person = new Person();
//            person.setId(rs.getInt("id"));
//            person.setName(rs.getString("name"));
//            person.setBirthday(rs.getDate("birthday"));
//            person.setLocation(rs.getString("location"));
//            return person;
//        }
//    }
//
//    @Autowired
//    JdbcTemplate jdbcTemplate;
//
//    public List<Person> findAll() {
////        List<Person> persons =
////                jdbcTemplate
////                        .query("select * from person", new BeanPropertyRowMapper<>(Person.class));
//
//        List<Person> persons =
//                jdbcTemplate
//                        .query("select * from person", new CustomRowMapper());
//
//        return persons;
//    }
//
//    public Person findById(int id) {
//        return jdbcTemplate.queryForObject(
//                "select * from person where id = ?", new Object[]{id},
//                new BeanPropertyRowMapper<>(Person.class));
//    }
//
//    public int delById(int id) {
//        // Return number of row effect
//        // sql statement, array object of facts related
//        return jdbcTemplate.update("delete * from person where id = ?", new Object[]{id});
//    }
//
//    public int insertElement(Person person) {
//        return jdbcTemplate.update(
//                "insert into person(id, name, location, birthday) " +
//                "values(?, ?, ?, ?)",
//                new Object[]{ person.getId(), person.getName(), person.getLocation(), person.getBirthday().getTime() }
//                );
//    }
//
//    public int updateElement(Person person) {
//        return jdbcTemplate.update(
//                "update person " +
//                        "set localtion = ?, " +
//                        "set name = ?, " +
//                        "set birthday = ? " +
//                        "where id = ?",
//                new Object[]{ person.getLocation(), person.getName(), person.getBirthday().getTime(), person.getId()}
//        );
//    }
//
//
//}
