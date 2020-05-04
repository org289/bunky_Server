package com.bunky.server.Dao;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class LoginDaoTest {

    @Autowired
    private UserAptDao userAptDao;

//    @MockBean
//    private UserRepo userRepo;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private JdbcTemplate template;

//    @Test
//    public void testCreateUser() {
//        User user = new User("or", "org289@gmail.com");
//        User savedInDb = entityManager.persist(user);
//        User getFromDb = userRepo.findById(savedInDb.getId()).orElse(null);
//
//        assertEquals(savedInDb, getFromDb);
//        assertEquals(user, getFromDb);
//    }

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
}