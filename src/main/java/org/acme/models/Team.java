package org.acme.models;

public class Team implements Comparable<Team>{
    private int id;
    private int rank;
    private String name;
    private String form;
    private String image;

    public Team(int id, int rank, String name, String form, String image) {
        this.id = id;
        this.rank = rank;
        this.name = name;
        this.form = form;
        this.image = image;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getRank() {
        return rank;
    }
    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getform() {
        return form;
    }
    public void setform(String form) {
        this.form = form;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int compareTo(Team team) {
        return this.rank - team.rank;
    }
}
