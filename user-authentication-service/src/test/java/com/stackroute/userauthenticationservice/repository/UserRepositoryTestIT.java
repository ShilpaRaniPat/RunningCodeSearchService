
package com.stackroute.userauthenticationservice.repository;

        import com.stackroute.userauthenticationservice.model.User;
        import org.junit.After;
        import org.junit.Assert;
        import org.junit.Before;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.test.context.junit4.SpringRunner;
        import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTestIT {
    @Autowired
    private UserRepository userRepository;
    private User user;

    @Before
    public void setUp() throws Exception{
        User user = new User();
        user.setEmail("xyz@gmail.com");
        user.setPassword("password");
        userRepository.save(user);
    }

    @After
    public void tearDown() throws Exception {
        user=null;

    }

    @Test
    public void findByUserNameSuccess(){
        Optional<User> fetchedUser=userRepository.findByEmail("xyz@gmail.com");
        Assert.assertEquals("xyz@gmail.com",fetchedUser.get().getEmail());
    }
    @Test
    public void findByUserNameFailure(){
        Optional<User> fetchedUser=userRepository.findByEmail("xyz@gmail.com");
        Assert.assertNotEquals("Eroteme",fetchedUser.get().getEmail());
    }
}
