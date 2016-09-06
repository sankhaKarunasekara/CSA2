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
	private Double runRate;
	private Double dotBowlToRunsRatio;
	
	private int totalNumberOfNotOuts;
	private int totalNumberOfRuns;
	
	private int totalNumberOf1s;
	private int totalNumberOf2s;
	private int totalNumberOf4s;
	private int totalNumberOf6s;
	
	
	private int totalNumberOfOuts;
	
	public Double getBattingAverage() {
		return battingAverage;
	}

	public void setBattingAverage(Double battingAverage) {
		this.battingAverage = battingAverage;
	}

	public Double getBattingStrikeRate() {
		return battingStrikeRate;
	}

	public void setBattingStrikeRate(Double battingStrikeRate) {
		this.battingStrikeRate = battingStrikeRate;
	}

	public Double getBatmansDotBowlPresentage() {
		return batmansDotBowlPresentage;
	}

	public void setBatmansDotBowlPresentage(Double batmansDotBowlPresentage) {
		this.batmansDotBowlPresentage = batmansDotBowlPresentage;
	}

	public Double getBattingEconomy() {
		return battingEconomy;
	}

	public void setBattingEconomy(Double battingEconomy) {
		this.battingEconomy = battingEconomy;
	}

	public int getTotalNumberOf1s() {
		return totalNumberOf1s;
	}

	public void setTotalNumberOf1s(int totalNumberOf1s) {
		this.totalNumberOf1s = totalNumberOf1s;
	}

	public int getTotalNumberOf2s() {
		return totalNumberOf2s;
	}

	public void setTotalNumberOf2s(int totalNumberOf2s) {
		this.totalNumberOf2s = totalNumberOf2s;
	}

	public int getTotalNumberOf4s() {
		return totalNumberOf4s;
	}

	public void setTotalNumberOf4s(int totalNumberOf4s) {
		this.totalNumberOf4s = totalNumberOf4s;
	}

	public int getTotalNumberOf6s() {
		return totalNumberOf6s;
	}

	public void setTotalNumberOf6s(int totalNumberOf6s) {
		this.totalNumberOf6s = totalNumberOf6s;
	}

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

	public Double getRunRate() {
		return runRate;
	}

	public void setRunRate(Double runRate) {
		this.runRate = runRate;
	}

	public Double getDotBowlToRunsRatio() {
		return dotBowlToRunsRatio;
	}

	public void setDotBowlToRunsRatio(Double dotBowlToRunsRatio) {
		this.dotBowlToRunsRatio = dotBowlToRunsRatio;
	}

//	public int getNumberOfBowlsBowled() {
//		return numberOfBowlsBowled;
//	}
//
//	public void setNumberOfBowlsBowled(int numberOfBowlsBowled) {
//		this.numberOfBowlsBowled = numberOfBowlsBowled;
//	}

}
