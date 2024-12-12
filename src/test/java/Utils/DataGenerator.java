package Utils;

import com.github.javafaker.Faker;

public class DataGenerator {


    static Faker faker = new Faker();

    public static String generateValidPassword() {
        // Generate a pattern of mixed letters and numbers based on the specified length
        String pattern = "?".repeat(8); // `?` is replaced by a random letter or digit
        return faker.bothify(pattern);
    }

    public static String generateInValidPassword() {

        String pattern = "?".repeat(6);
        return faker.bothify(pattern);
    }

    public static String generateFirstName(){

        String first_name = faker.name().firstName();
        return first_name;
    }

    public static String generateInvalidFirstName(){

        String first_name = null;
        return first_name;
    }

    public static String generateSecondName(){

        String last_name = faker.name().lastName();
        return last_name;
    }

    public static String generateInvalidSecondName(){

        String last_name = null;
        return last_name;
    }

    public static String generateEmail(){

        String email = faker.internet().emailAddress();
        return email;
    }

    public static String generateInvalidEmail(){

        String email = null;
        return email;
    }

}
