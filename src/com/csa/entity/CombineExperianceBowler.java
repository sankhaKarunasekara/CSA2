package com.csa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CombineExperianceBowler {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int matchId;
	// total Number of matches played by top 5 bowlers
	int dffnoOfMatches;

	Double diffBowlingEconomy;
	
	// bowling average of top 5 bowlers
	Double diffAvgAvgOfBowlers;

	// strike rate of top 5 bowlers
	Double diffAvgStrikeRate;

	// dot bowl percentage of top 5 bowlers
	Double diffDotBowlPresentage;

	// dot bowls to runs ratio of top 5 bowlers
	Double diffDotBowlsToRunsRatio;

	// if 1 firstBowlTeam win if 2 second Bowl Team win
	int win;

	public Double getDiffBowlingEconomy() {
		return diffBowlingEconomy;
	}

	public void setDiffBowlingEconomy(Double diffBowlingEconomy) {
		this.diffBowlingEconomy = diffBowlingEconomy;
	}

	public int getDffnoOfMatches() {
		return dffnoOfMatches;
	}

	public void setDffnoOfMatches(int dffnoOfMatches) {
		this.dffnoOfMatches = dffnoOfMatches;
	}

	public Double getDiffAvgAvgOfBowlers() {
		return diffAvgAvgOfBowlers;
	}

	public void setDiffAvgAvgOfBowlers(Double diffAvgAvgOfBowlers) {
		this.diffAvgAvgOfBowlers = diffAvgAvgOfBowlers;
	}

	public Double getDiffAvgStrikeRate() {
		return diffAvgStrikeRate;
	}

	public void setDiffAvgStrikeRate(Double diffAvgStrikeRate) {
		this.diffAvgStrikeRate = diffAvgStrikeRate;
	}

	public Double getDiffDotBowlPresentage() {
		return diffDotBowlPresentage;
	}

	public void setDiffDotBowlPresentage(Double diffDotBowlPresentage) {
		this.diffDotBowlPresentage = diffDotBowlPresentage;
	}

	public Double getDiffDotBowlsToRunsRatio() {
		return diffDotBowlsToRunsRatio;
	}

	public void setDiffDotBowlsToRunsRatio(Double diffDotBowlsToRunsRatio) {
		this.diffDotBowlsToRunsRatio = diffDotBowlsToRunsRatio;
	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

}
