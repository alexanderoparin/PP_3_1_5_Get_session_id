package spring_rest;

import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@AllArgsConstructor
public class Communication {

    private RestTemplate restTemplate;
    private final String URL = "http://94.198.50.185:7081/api/users";

    public List<User> getAllUsers() {
        ResponseEntity<List<User>> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<User>>() {});
        List<User> allUsers = responseEntity.getBody();
        return allUsers;
    }

    public User getUserToID() {
        return null;
    }

    public void saveUser(User user) {

    }

    public void deleteUserToID(int id) {

    }
}
