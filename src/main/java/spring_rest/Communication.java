package spring_rest;

import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class Communication {

    private RestTemplate restTemplate;
    private final String URL = "http://94.198.50.185:7081/api/users";
    private List<String> cookies;

    @PostConstruct
    private void app(){
        ResponseEntity<List<User>> users = getAllUsers();
        System.out.println(users.getBody());

        User user1 = new User(3L, "James", "Brown", (byte) 29);
        ResponseEntity<String> responseEntityAddUser = saveUser(user1);
        System.out.println(responseEntityAddUser.getBody());

        user1.setName("Thomas");
        user1.setLastName("Shelby");
        ResponseEntity<String> responseEntityUpdateUser = editUser(user1);
        System.out.println(responseEntityUpdateUser.getBody());

        ResponseEntity<String> responseEntityDeleteUser = deleteUserToID(3L);
        System.out.println(responseEntityDeleteUser.getBody());
    }

    public ResponseEntity<List<User>> getAllUsers() {
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange
                (URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
        cookies = responseEntity.getHeaders().get("Set-Cookie")
                .stream().peek(System.out::println).collect(Collectors.toList());
        return responseEntity;
    }

    public ResponseEntity<String> saveUser(User user) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Cookie", cookies.stream().collect(Collectors.joining(";")));
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        System.out.println(httpHeaders);
        HttpEntity<User> httpEntity = new HttpEntity<>(user, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL,httpEntity, String.class);
        return responseEntity;
    }

    public ResponseEntity<String> editUser(User user) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Cookie", cookies.stream().collect(Collectors.joining(";")));
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        System.out.println(httpHeaders);
        HttpEntity<User> httpEntity = new HttpEntity<>(user, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, httpEntity, String.class);
        return responseEntity;
    }

    public ResponseEntity<String> deleteUserToID(long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Cookie", cookies.stream().collect(Collectors.joining(";")));
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        System.out.println(httpHeaders);
        HttpEntity<User> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String>responseEntity = restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, httpEntity, String.class);
        return responseEntity;
    }
}
