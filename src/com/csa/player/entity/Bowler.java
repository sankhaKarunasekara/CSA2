package com.csa.player.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bowler implements Comparable<Bowler> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int playerId;
	private int matchId;
	private int totalNumberOfInnings;
	private int inningsNumber;
	private Double averageRunsInInnings;
	
	public int getInningsNumber() {
		return inningsNumber;
	}

	public void setInningsNumber(int inningsNumber) {
		this.inningsNumber = inningsNumber;
	}

	private String name;
	//private int numberOfBowlsBowled;
	private Double bowlingAverage;
	private Double bowlingStrikeRate;
	private Double bowlersDotBowlPresentage;
	private Double bowlingEconomy;
	private Double dotBowlToRunsRatio;
	
	private int totalNumberOfExtras;
	private int totalNumberOfRuns;

	private int totalNumberOfBowls;
	private int totalNumberOfDotBowls;
	private int totalNumberOfWickets;
	
	private int numberOfBowlsInPowerPlay;
	private int numberOfBowlsInMiddle;
	private int numberOfBowlsInDeath;
	
	private int numberOfDotsInPowerPlay;
	private int numberOfDotsInMiddle;
	private int numberOfDotsInDeath;
	
	private int numberOfWicketsInPowerPlay;
	private int numberOfWicketsInMiddle;
	private int numberOfWicketsInDeath;
	
	public int getTotalNumberOfBowls() {
		return totalNumberOfBowls;
	}

	public void setTotalNumberOfBowls(int totalNumberOfBowls) {
		this.totalNumberOfBowls = totalNumberOfBowls;
	}

	public int getTotalNumberOfDotBowls() {
		return totalNumberOfDotBowls;
	}

	public void setTotalNumberOfDotBowls(int totalNumberOfDotBowls) {
		this.totalNumberOfDotBowls = totalNumberOfDotBowls;
	}

	public int getTotalNumberOfWickets() {
		return totalNumberOfWickets;
	}

	public void setTotalNumberOfWickets(int totalNumberOfWickets) {
		this.totalNumberOfWickets = totalNumberOfWickets;
	}

	public int getNumberOfBowlsInPowerPlay() {
		return numberOfBowlsInPowerPlay;
	}

	public void setNumberOfBowlsInPowerPlay(int numberOfBowlsInPowerPlay) {
		this.numberOfBowlsInPowerPlay = numberOfBowlsInPowerPlay;
	}

	public int getNumberOfBowlsInMiddle() {
		return numberOfBowlsInMiddle;
	}

	public void setNumberOfBowlsInMiddle(int numberOfBowlsInMiddle) {
		this.numberOfBowlsInMiddle = numberOfBowlsInMiddle;
	}

	public int getNumberOfBowlsInDeath() {
		return numberOfBowlsInDeath;
	}

	public void setNumberOfBowlsInDeath(int numberOfBowlsInDeath) {
		this.numberOfBowlsInDeath = numberOfBowlsInDeath;
	}

	public int getNumberOfDotsInPowerPlay() {
		return numberOfDotsInPowerPlay;
	}

	public void setNumberOfDotsInPowerPlay(int numberOfDotsInPowerPlay) {
		this.numberOfDotsInPowerPlay = numberOfDotsInPowerPlay;
	}

	public int getNumberOfDotsInMiddle() {
		return numberOfDotsInMiddle;
	}

	public void setNumberOfDotsInMiddle(int numberOfDotsInMiddle) {
		this.numberOfDotsInMiddle = numberOfDotsInMiddle;
	}

	public int getNumberOfDotsInDeath() {
		return numberOfDotsInDeath;
	}

	public void setNumberOfDotsInDeath(int numberOfDotsInDeath) {
		this.numberOfDotsInDeath = numberOfDotsInDeath;
	}

	public int getNumberOfWicketsInPowerPlay() {
		return numberOfWicketsInPowerPlay;
	}

	public void setNumberOfWicketsInPowerPlay(int numberOfWicketsInPowerPlay) {
		this.numberOfWicketsInPowerPlay = numberOfWicketsInPowerPlay;
	}

	public int getNumberOfWicketsInMiddle() {
		return numberOfWicketsInMiddle;
	}

	public void setNumberOfWicketsInMiddle(int numberOfWicketsInMiddle) {
		this.numberOfWicketsInMiddle = numberOfWicketsInMiddle;
	}

	public int getNumberOfWicketsInDeath() {
		return numberOfWicketsInDeath;
	}

	public void setNumberOfWicketsInDeath(int numberOfWicketsInDeath) {
		this.numberOfWicketsInDeath = numberOfWicketsInDeath;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getBowlingAverage() {
		return bowlingAverage;
	}

	public void setBowlingAverage(Double bowlingAverage) {
		this.bowlingAverage = bowlingAverage;
	}

	public Double getBowlingStrikeRate() {
		return bowlingStrikeRate;
	}

	public void setBowlingStrikeRate(Double bowlingStrikeRate) {
		this.bowlingStrikeRate = bowlingStrikeRate;
	}

	public Double getBowlersDotBowlPresentage() {
		return bowlersDotBowlPresentage;
	}

	public void setBowlersDotBowlPresentage(Double bowlersDotBowlPresentage) {
		this.bowlersDotBowlPresentage = bowlersDotBowlPresentage;
	}

	public Double getBowlingEconomy() {
		return bowlingEconomy;
	}

	public void setBowlingEconomy(Double bowlingEconomy) {
		this.bowlingEconomy = bowlingEconomy;
	}

	public int getMatchId() {
		return matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	public int getTotalNumberOfInnings() {
		return totalNumberOfInnings;
	}

	public void setTotalNumberOfInnings(int totalNumberOfInnings) {
		this.totalNumberOfInnings = totalNumberOfInnings;
	}

	public int getTotalNumberOfExtras() {
		return totalNumberOfExtras;
	}

	public void setTotalNumberOfExtras(int totalNumberOfExtras) {
		this.totalNumberOfExtras = totalNumberOfExtras;
	}

	public int getTotalNumberOfRuns() {
		return totalNumberOfRuns;
	}

	public void setTotalNumberOfRuns(int totalNumberOfRuns) {
		this.totalNumberOfRuns = totalNumberOfRuns;
	}

	public Double getDotBowlToRunsRatio() {
		return dotBowlToRunsRatio;
	}

	public void setDotBowlToRunsRatio(Double dotBowlToRunsRatio) {
		this.dotBowlToRunsRatio = dotBowlToRunsRatio;
	}

	public Double getAverageRunsInInnings() {
		return averageRunsInInnings;
	}

	public void setAverageRunsInInnings(Double averageRunsInInnings) {
		this.averageRunsInInnings = averageRunsInInnings;
	}

	
	//this method is to compare bowler with other bowler and find out who is better
	// Base Factor: average of the bowler ( other factors are unfair to debut bowler) 
	@Override
	public int compareTo(Bowler compareBowler) {
		// TODO Auto-generated method stub
		Double compareAverage = ((Bowler)compareBowler).getBowlingAverage();
		
		return (int)Math.round(this.bowlingAverage -compareAverage);
	}

	

//	public int getNumberOfBowlsBowled() {
//		return numberOfBowlsBowled;
//	}
//
//	public void setNumberOfBowlsBowled(int numberOfBowlsBowled) {
//		this.numberOfBowlsBowled = numberOfBowlsBowled;
//	}

}
