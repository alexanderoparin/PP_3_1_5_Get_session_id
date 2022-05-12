package spring_rest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class App {

    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Communication communication = context.getBean("communication", Communication.class);

        List<User> allUsers = communication.getAllUsers();
        System.out.println(allUsers);

        User user1 = new User("James", "Brown", (byte) 50);
        communication.saveUser(user1);

        User user2 = new User(3L, "Thomas", " Shelby", (byte) 50);
        communication.saveUser(user2);
    }
}
