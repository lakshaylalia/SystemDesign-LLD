package models;


public class User {
    private static Long userCnt =  0L;
    private Long id;
    private String username;
    private Long reputation;

    public User(String username){
        this.id = getNextUserId();
        this.username = username;
        this.reputation = 0L;
    }

    private static synchronized Long getNextUserId(){
        return ++userCnt;
    }

    public Long getReputation() {
        return reputation;
    }

    public synchronized void addReputation(Long points) {
        reputation += points;
    }

    public synchronized void subtractReputation(Long points) {
        if (reputation < points) {
            reputation = 0L;
            return;
        }
        reputation -= points;
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

    public BadgeTier getBadgeTier() {
        return BadgeTier.fromReputation(this.reputation);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", reputation=" + reputation +
                ", badge=" + getBadgeTier() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id != null && id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
