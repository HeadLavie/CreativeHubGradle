package Models;

public class UserResponse {

    public static String first_name;
    public static String second_name;
    public static String email;
    public static String uid;
    public static String username;
    public static String photo;
    public static String last_visited_at;
    public static String profile_cover;

    public UserResponse() {

    }

    public static String getFirst_name() {
        return first_name;
    }

    public static void setFirst_name(String first_name) {
        UserResponse.first_name = first_name;
    }

    public static String getProfile_cover() {
        return profile_cover;
    }

    public static void setProfile_cover(String profile_cover) {
        UserResponse.profile_cover = profile_cover;
    }

    public static String getLast_visited_at() {
        return last_visited_at;
    }

    public static void setLast_visited_at(String last_visited_at) {
        UserResponse.last_visited_at = last_visited_at;
    }

    public static String getPhoto() {
        return photo;
    }

    public static void setPhoto(String photo) {
        UserResponse.photo = photo;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        UserResponse.username = username;
    }

    public static String getUid() {
        return uid;
    }

    public static void setUid(String uid) {
        UserResponse.uid = uid;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        UserResponse.email = email;
    }

    public static String getSecond_name() {
        return second_name;
    }

    public static void setSecond_name(String second_name) {
        UserResponse.second_name = second_name;
    }
}
