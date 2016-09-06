package com.csa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Bowl {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	public int bowlId;
	
	public String bowler;
	public int overNumber;
	public int bowlnumber;
	public int ligitBowlnumber;
	
	public String batsman;
	public String nonStriker;
	
	public int runs;
	public int extras;
	public int extraType;
	
	public int totalRuns;
	public int isWicket;
	
	@OneToOne 
	public Wicket wicket;
	
	public Bowl() {
		// TODO Auto-generated constructor stub
	}

    /**
     * @return the bowler
     */
    public String getBowler() {
        return bowler;
    }

    /**
     * @param bowler the bowler to set
     */
    public void setBowler(String bowler) {
        this.bowler = bowler;
    }

    /**
     * @return the overNumber
     */
    public int getOverNumber() {
        return overNumber;
    }

    /**
     * @param overNumber the overNumber to set
     */
    public void setOverNumber(int overNumber) {
        this.overNumber = overNumber;
    }

    /**
     * @return the bowlnumber
     */
    public int getBowlnumber() {
        return bowlnumber;
    }

    /**
     * @param bowlnumber the bowlnumber to set
     */
    public void setBowlnumber(int bowlnumber) {
        this.bowlnumber = bowlnumber;
    }

    /**
     * @return the ligitBowlnumber
     */
    public int getLigitBowlnumber() {
        return ligitBowlnumber;
    }

    /**
     * @param ligitBowlnumber the ligitBowlnumber to set
     */
    public void setLigitBowlnumber(int ligitBowlnumber) {
        this.ligitBowlnumber = ligitBowlnumber;
    }

    /**
     * @return the batsman
     */
    public String getBatsman() {
        return batsman;
    }

    /**
     * @param batsman the batsman to set
     */
    public void setBatsman(String batsman) {
        this.batsman = batsman;
    }

    /**
     * @return the nonStriker
     */
    public String getNonStriker() {
        return nonStriker;
    }

    /**
     * @param nonStriker the nonStriker to set
     */
    public void setNonStriker(String nonStriker) {
        this.nonStriker = nonStriker;
    }

    /**
     * @return the runs
     */
    public int getRuns() {
        return runs;
    }

    /**
     * @param runs the runs to set
     */
    public void setRuns(int runs) {
        this.runs = runs;
    }

    /**
     * @return the extras
     */
    public int getExtras() {
        return extras;
    }

    /**
     * @param extras the extras to set
     */
    public void setExtras(int extras) {
        this.extras = extras;
    }

    /**
     * @return the extraType
     * byes =1
     * legByes 2
     * noballs 3
     * wides 4
     */
    public int getExtraType() {
        return extraType;
    }

    /**
     * @param extraType the extraType to set
     */
    public void setExtraType(int extraType) {
        this.extraType = extraType;
    }

    /**
     * @return the totalRuns
     */
    public int getTotalRuns() {
        return totalRuns;
    }

    /**
     * @param totalRuns the totalRuns to set
     */
    public void setTotalRuns(int totalRuns) {
        this.totalRuns = totalRuns;
    }

    /**
     * @return the isWicket
     */
    public int getIsWicket() {
        return isWicket;
    }

    /**
     * @param isWicket the isWicket to set
     */
    public void setIsWicket(int isWicket) {
        this.isWicket = isWicket;
    }

    /**
     * @return the wicket
     */
    public Wicket getWicket() {
        return wicket;
    }

    /**
     * @param wicket the wicket to set
     */
    public void setWicket(Wicket wicket) {
        this.wicket = wicket;
    }

}
