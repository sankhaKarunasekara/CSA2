package com.csa.player.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Batsman {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int playerId;
	private int matchId;

	private String name;
	private int totalNumberOfInnings;
	private int inningsNumber;
	
	//private int numberOfBowlsBowled;
	private Double battingAverage;
	private Double battingStrikeRate;
	private Double batmansDotBowlPresentage;
	private Double battingEconomy;
	
	private int totalNumberOfNotOuts;
	private int totalNumberOfRuns;
	private int totalNumberOfOuts;
	
	private int totalNumberOfBowls;
	private int totalNumberOfDotBowls;
	
	private int numberOfBowlsInPowerPlay;
	private int numberOfBowlsInMiddle;
	private int numberOfBowlsInDeath;
	
	private int numberOfDotsInPowerPlay;
	private int numberOfDotsInMiddle;
	private int numberOfDotsInDeath;
	
	private int numberOfOutsInPowerPlay;
	private int numberOfOutsInMiddle;
	private int numberOfOutsInDeath;
	

	private int totalNumberOfBattingSegments;
	
	public int getInningsNumber() {
		return inningsNumber;
	}

	public void setInningsNumber(int inningsNumber) {
		this.inningsNumber = inningsNumber;
	}

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

	public Double getbattingAverage() {
		return battingAverage;
	}

	public void setbattingAverage(Double battingAverage) {
		this.battingAverage = battingAverage;
	}

	public Double getbattingStrikeRate() {
		return battingStrikeRate;
	}

	public void setbattingStrikeRate(Double battingStrikeRate) {
		this.battingStrikeRate = battingStrikeRate;
	}

	public Double getbatmansDotBowlPresentage() {
		return batmansDotBowlPresentage;
	}

	public void setbatmansDotBowlPresentage(Double batmansDotBowlPresentage) {
		this.batmansDotBowlPresentage = batmansDotBowlPresentage;
	}

	public Double getbattingEconomy() {
		return battingEconomy;
	}

	public void setbattingEconomy(Double battingEconomy) {
		this.battingEconomy = battingEconomy;
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

	public int getTotalNumberOfRuns() {
		return totalNumberOfRuns;
	}

	public void setTotalNumberOfRuns(int totalNumberOfRuns) {
		this.totalNumberOfRuns = totalNumberOfRuns;
	}

	public int getTotalNumberOfBattingSegments() {
		return totalNumberOfBattingSegments;
	}

	public void setTotalNumberOfBattingSegments(int totalNumberOfBattingSegments) {
		this.totalNumberOfBattingSegments = totalNumberOfBattingSegments;
	}

	public int getNumberOfOutsInPowerPlay() {
		return numberOfOutsInPowerPlay;
	}

	public void setNumberOfOutsInPowerPlay(int numberOfOutsInPowerPlay) {
		this.numberOfOutsInPowerPlay = numberOfOutsInPowerPlay;
	}

	public int getNumberOfOutsInDeath() {
		return numberOfOutsInDeath;
	}

	public void setNumberOfOutsInDeath(int numberOfOutsInDeath) {
		this.numberOfOutsInDeath = numberOfOutsInDeath;
	}

	public int getNumberOfOutsInMiddle() {
		return numberOfOutsInMiddle;
	}

	public void setNumberOfOutsInMiddle(int numberOfOutsInMiddle) {
		this.numberOfOutsInMiddle = numberOfOutsInMiddle;
	}

	public int getTotalNumberOfNotOuts() {
		return totalNumberOfNotOuts;
	}

	public void setTotalNumberOfNotOuts(int totalNumberOfNotOuts) {
		this.totalNumberOfNotOuts = totalNumberOfNotOuts;
	}

	public int getTotalNumberOfOuts() {
		return totalNumberOfOuts;
	}

	public void setTotalNumberOfOuts(int totalNumberOfOuts) {
		this.totalNumberOfOuts = totalNumberOfOuts;
	}

	

//	public int getNumberOfBowlsBowled() {
//		return numberOfBowlsBowled;
//	}
//
//	public void setNumberOfBowlsBowled(int numberOfBowlsBowled) {
//		this.numberOfBowlsBowled = numberOfBowlsBowled;
//	}

}
