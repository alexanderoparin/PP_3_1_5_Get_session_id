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
        return responseEntity.getBody();
    }

    public void saveUser(User user) {
        Long id = user.getId();
        if (id == null) {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL, user, String.class);
            System.out.println("new user was added");
            System.out.println(responseEntity.getBody());
            System.out.println(user);
        } else {
            restTemplate.put(URL, user);
            System.out.println("User with id = " + id + " was edited");
            System.out.println(user);
        }
    }

    public void deleteUserToID(long id) {

    }
}
