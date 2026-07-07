package models;


public class User {
    private static Long userCnt =  0L;
    private Long id;
    private String username;

    public User(String username){
        this.id = getNextUserId();
        this.username = username;
    }

    private static synchronized Long getNextUserId(){
        return ++userCnt;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
