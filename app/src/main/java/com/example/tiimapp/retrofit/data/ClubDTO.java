package com.example.tiimapp.retrofit.data;

import java.util.ArrayList;
import java.util.List;

public class ClubDTO {

    private String clubName;
    private String shortname;
    private String coach;
    private String stadium;
    private String webPage;
    private String captain;


    public ClubDTO (String clubName, String shortname, String coach, String stadium, String webPage, String captain) {
        this.clubName = clubName;
        this.shortname = shortname;
        this.coach = coach;
        this.stadium = stadium;
        this.webPage = webPage;
        this.captain = captain;

    }

    public ClubDTO() {

    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }
}
