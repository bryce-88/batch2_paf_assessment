package ibf2022.paf.assessment.server.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.paf.assessment.server.models.User;

// TODO: Task 3
@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String FIND_BY_USERNAME = "select * from user where username = ?";
    private static final String CREATE_NEW_USER = "insert into user (user_id, username) values (?, ?)";


    public Optional<User> findUserByUsername (String username) throws EmptyResultDataAccessException {

        try {
            Optional<User> optUser = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_USERNAME, 
                BeanPropertyRowMapper.newInstance(User.class), username));
            if (optUser.isEmpty()) {
                return Optional.empty();
            } else {
                return optUser;
            }
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


    public String insertUser (User user) {

        //creates UUID for new user
        user.setUserId(UUID.randomUUID().toString().substring(0, 8));

        String userId = user.getUserId();

        int userCreated = 0;

        //if successful, return 1. if not, return 0
        userCreated = jdbcTemplate.update(CREATE_NEW_USER, userId, user.getUsername());

        if (userCreated > 0) {
            return userId;
        } else {
            return null;
        }
    }


    
}
