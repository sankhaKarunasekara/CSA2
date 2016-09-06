package com.csa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CombineExperianceBatsmanAndBowler {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int matchId;
	// total Number of matches played by top 5 bowlers
	int dffnoOfMatches1;

	// bowling average of top 5 bowlers
	Double diffAvgAvgOfBatsmanAndBowler1;

	// strike rate of top 5 bowlers
	Double diffAvgStrikeRate1;

	// dot bowl percentage of top 5 bowlers
	Double diffDotBowlPresentage1;

	// dot bowls to runs ratio of top 5 bowlers
	Double diffDotBowlsToRunsRatio1;

	Double diffEconomy1;

	// total Number of matches played by top 5 bowlers
	int dffnoOfMatches2;

	// bowling average of top 5 bowlers
	Double diffAvgAvgOfBatsmanAndBowler2;

	// strike rate of top 5 bowlers
	Double diffAvgStrikeRate2;

	// dot bowl percentage of top 5 bowlers
	Double diffDotBowlPresentage2;

	public Double getDiffEconomy1() {
		return diffEconomy1;
	}

	public void setDiffEconomy1(Double diffEconomy1) {
		this.diffEconomy1 = diffEconomy1;
	}



	public Double getDiffEconomy2() {
		return diffEconomy2;
	}



	public void setDiffEconomy2(Double diffEconomy2) {
		this.diffEconomy2 = diffEconomy2;
	}



	// dot bowls to runs ratio of top 5 bowlers
	Double diffDotBowlsToRunsRatio2;

	
	Double diffEconomy2;

	// if 1 firstBowlTeam win if 2 second Bowl Team win
	int win;



	public int getMatchId() {
		return matchId;
	}



	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}



	public int getDffnoOfMatches1() {
		return dffnoOfMatches1;
	}



	public void setDffnoOfMatches1(int dffnoOfMatches1) {
		this.dffnoOfMatches1 = dffnoOfMatches1;
	}



	public Double getDiffAvgAvgOfBatsmanAndBowler1() {
		return diffAvgAvgOfBatsmanAndBowler1;
	}



	public void setDiffAvgAvgOfBatsmanAndBowler1(
			Double diffAvgAvgOfBatsmanAndBowler1) {
		this.diffAvgAvgOfBatsmanAndBowler1 = diffAvgAvgOfBatsmanAndBowler1;
	}



	public Double getDiffAvgStrikeRate1() {
		return diffAvgStrikeRate1;
	}



	public void setDiffAvgStrikeRate1(Double diffAvgStrikeRate1) {
		this.diffAvgStrikeRate1 = diffAvgStrikeRate1;
	}



	public Double getDiffDotBowlPresentage1() {
		return diffDotBowlPresentage1;
	}



	public void setDiffDotBowlPresentage1(Double diffDotBowlPresentage1) {
		this.diffDotBowlPresentage1 = diffDotBowlPresentage1;
	}



	public Double getDiffDotBowlsToRunsRatio1() {
		return diffDotBowlsToRunsRatio1;
	}



	public void setDiffDotBowlsToRunsRatio1(Double diffDotBowlsToRunsRatio1) {
		this.diffDotBowlsToRunsRatio1 = diffDotBowlsToRunsRatio1;
	}



	public int getDffnoOfMatches2() {
		return dffnoOfMatches2;
	}



	public void setDffnoOfMatches2(int dffnoOfMatches2) {
		this.dffnoOfMatches2 = dffnoOfMatches2;
	}



	public Double getDiffAvgAvgOfBatsmanAndBowler2() {
		return diffAvgAvgOfBatsmanAndBowler2;
	}



	public void setDiffAvgAvgOfBatsmanAndBowler2(
			Double diffAvgAvgOfBatsmanAndBowler2) {
		this.diffAvgAvgOfBatsmanAndBowler2 = diffAvgAvgOfBatsmanAndBowler2;
	}



	public Double getDiffAvgStrikeRate2() {
		return diffAvgStrikeRate2;
	}



	public void setDiffAvgStrikeRate2(Double diffAvgStrikeRate2) {
		this.diffAvgStrikeRate2 = diffAvgStrikeRate2;
	}



	public Double getDiffDotBowlPresentage2() {
		return diffDotBowlPresentage2;
	}



	public void setDiffDotBowlPresentage2(Double diffDotBowlPresentage2) {
		this.diffDotBowlPresentage2 = diffDotBowlPresentage2;
	}



	public Double getDiffDotBowlsToRunsRatio2() {
		return diffDotBowlsToRunsRatio2;
	}



	public void setDiffDotBowlsToRunsRatio2(Double diffDotBowlsToRunsRatio2) {
		this.diffDotBowlsToRunsRatio2 = diffDotBowlsToRunsRatio2;
	}



	public int getWin() {
		return win;
	}



	public void setWin(int win) {
		this.win = win;
	}

}