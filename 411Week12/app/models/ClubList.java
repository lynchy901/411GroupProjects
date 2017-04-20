package models;

import java.util.ArrayList;

/**
 * Created by nathan on 4/20/17.
 */
public class ClubList {

    private static ArrayList<Club> clubs;

    public ClubList() {};

    public static ClubList getInstance() {
        return new ClubList();
    }

    public void addClub(Club club) {
        clubs.add(club);
    }

    public ArrayList<Club> getClubList() {
        if (clubs == null) {
            clubs = new ArrayList<>();

            Club c1 = new Club("clubname", "fff", 1212L, "describe");
            Club c2 = new Club("asdfasdf", "ddd", 1212L, "ddd");
            Club c3 = new Club("dddd", "sss", 1212L, "fffffff");
            Club c4 = new Club("NicoleClub", "Nicole", 10000L, "describe");
            Club c5 = new Club("cluberino", "fff", 1212L, "describe");


            clubs.add(c1);
            clubs.add(c2);
            clubs.add(c3);
            clubs.add(c4);
            clubs.add(c5);
        }
        return clubs;
    }

    public Club findClub(long id) {
        Club club = null;
        for (int i = 0; i < clubs.size(); i++) {
            if (id == clubs.get(i).getId()) {
                club = clubs.get(i);
            }
        }
        return club;
    }

    public void print() {
        for (int i = 0; i < clubs.size(); i++) {
            clubs.get(i).print();
        }
    }

}
