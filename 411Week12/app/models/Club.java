package models;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by nathan on 4/20/17.
 */
public class Club {

    private static final AtomicInteger count = new AtomicInteger(0);
    private long id;
    private String clubName;
    private String president;
    private long memberCount;
    private String description;

    public Club() {};

    public Club(String clubName, String president, long memberCount, String description) {
        this.id = count.incrementAndGet();
        this.clubName = clubName;
        this.president = president;
        this.memberCount = memberCount;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getPresident() {
        return president;
    }

    public void setPresident(String president) {
        this.president = president;
    }

    public long getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Long memberCount) {
        this.memberCount = memberCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void print() {
        System.out.println("ID: " + this.id + " ClubName: " + this.clubName + " President: " + this.president + " MemberCount: " + this.memberCount + " Description: " + this.description);
    }
}
