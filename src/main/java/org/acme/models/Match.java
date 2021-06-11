package org.acme.models;

import org.decimal4j.util.DoubleRounder;

import java.util.Random;

public class Match implements Comparable<Match>{
    private final double MIN_QUOTATION = 1.20;
    private final double MAX_QUOTATION = 3.20;

    private int id;
    private String homeTeam;
    private String awayTeam;
    private String league;
    private String time;
    private String date;
    private double quotation;

    public Match(int id, String homeTeam, String awayTeam, String league, String time, String date) {
        Random rnd = new Random();
        double random = MIN_QUOTATION + (MAX_QUOTATION - MIN_QUOTATION) * rnd.nextDouble();

        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.league = league;
        this.time = time;
        this.date = date;
        this.quotation = DoubleRounder.round(random,2);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getQuotation() {
        return quotation;
    }

    @Override
    public int compareTo(Match match) {
        return this.time.compareTo(match.getTime());
    }
}
