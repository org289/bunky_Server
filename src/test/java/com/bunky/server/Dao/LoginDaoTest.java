//package com.bunky.server.Dao;
//
//import com.bunky.server.Entity.User;
//import org.junit.ClassRule;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.util.TestPropertyValues;
//import org.springframework.context.ApplicationContextInitializer;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.testcontainers.containers.PostgreSQLContainer;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//class LoginDaoTest {
//
//    @Autowired
//    private LoginDao loginDao;
//
//    @Autowired
//    private JdbcTemplate template;
//
////    @ClassRule
////    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:11.1")
////            .withDatabaseName("integration-tests-db")
////            .withUsername("sa")
////            .withPassword("sa");
////
////    static class Initializer
////            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
////        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
////            TestPropertyValues.of(
////                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
////                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
////                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
////            ).applyTo(configurableApplicationContext.getEnvironment());
////        }
////    }
//
//    @Test
//    void createUser() {
//        Integer id = loginDao.createUser("org289@gmail.com", "or");
//        User user = template.query("SELECT * FROM users WHERE user_id=" + id, resultSet -> {
//            String mail = resultSet.getString("mail");
//            String name = resultSet.getString("name");
//            Integer userId = resultSet.getInt("user_id");
//            User newUser = new User(name, mail);
//            newUser.setId(userId);
//            return newUser;
//        });
//        assertEquals(new User("or", "org289@gmail.com"), user);
//    }
//
//
//    private void testIfCorrect(Integer userId1, Integer userId2, Integer userId3) {
//        assertEquals(1, userId1);
//        assertEquals(2, userId2);
//        assertEquals(35, userId3);
//    }
//
//    @Test
//    void getUserByMail() {
//        Integer userId1 = loginDao.getUserByMail("org289@gmail.com");
//        Integer UserId2 = loginDao.getUserByMail("amypariz@gmail.com");
//        Integer UserId3 = loginDao.getUserByMail("yuval@gmail.com");
//
//        testIfCorrect(userId1, UserId2, UserId3);
//    }
//
//    @Test
//    void getAllUsers() {
//        List<User> result = loginDao.getAllUsers();
//        Integer UserId1 = result.get(0).getId();
//        Integer UserId2 = result.get(1).getId();
//        Integer UserId3 = result.get(2).getId();
//
//        testIfCorrect(UserId2, UserId1, UserId3);
//    }
//
//    @Test
//    void getUserById() {
//    }
//
//    @Test
//    void aptByUser() {
//    }
//
//    @Test
//    void getAllApt() {
//    }
//
//    @Test
//    void loginApt() {
//    }
//}