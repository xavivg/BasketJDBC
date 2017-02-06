
package model;

import java.time.LocalDate;


public class Player {
    private String name;
    private LocalDate birth;
    private int nBaskets;
    private int nAssists;
    private int nRebounds;
    private String position;
    private Team team;

public Player(){

}
public Player(String name, LocalDate birth, int nBaskets, int nAssists, int nRebounds, String position, Team team){
    this.name = name;
    this.birth = birth;
    this.nBaskets = nBaskets;
    this.nAssists = nAssists;
    this.nRebounds = nRebounds;
    this.position = position;
    this.team = team;
}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public int getnBaskets() {
        return nBaskets;
    }

    public void setnBaskets(int nBaskets) {
        this.nBaskets = nBaskets;
    }

    public int getnAssists() {
        return nAssists;
    }

    public void setnAssists(int nAssists) {
        this.nAssists = nAssists;
    }

    public int getnRebounds() {
        return nRebounds;
    }

    public void setnRebounds(int nRebounds) {
        this.nRebounds = nRebounds;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

}