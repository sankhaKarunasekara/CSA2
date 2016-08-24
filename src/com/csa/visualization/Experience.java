package com.csa.visualization;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Experience {

	public Experience() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int inningsId;
	int matchId;
	int firstInningsOrSecondInnings;
	String stadium;
	String city;

	// average dot bowl percentage of top 5 bowlers
	Double avgDotBowlPrerentage;

	// average strike rate of top 5 bowlers
	Double avgStrikeRate;

	// Average average of top 5 bowlers
	Double avgAverage;

	// Average Economy of top 5 bowlers
	Double avgEconomy;

	// average dotBowls to runs ratio of top 5 bowlers
	Double avgDotBowlToRunsRatio;

	// average totalNumberOfInnings of top 5 bowlers
	Double avgTotalNumberOfInnings;

	// Double sixHittingFrequency;
	// Double fourHittingFrequency;
	// Double BoundaryRunsPresentage;

	public int getInningsId() {
		return inningsId;
	}

	public void setInningsId(int inningsId) {
		this.inningsId = inningsId;
	}

	public int getMatchId() {
		return matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	public int getFirstInningsOrSecondInnings() {
		return firstInningsOrSecondInnings;
	}

	public void setFirstInningsOrSecondInnings(int firstInningsOrSecondInnings) {
		this.firstInningsOrSecondInnings = firstInningsOrSecondInnings;
	}

	public String getStadium() {
		return stadium;
	}

	public void setStadium(String stadium) {
		this.stadium = stadium;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Double getAvgDotBowlPrerentage() {
		return avgDotBowlPrerentage;
	}

	public void setAvgDotBowlPrerentage(Double avgDotBowlPrerentage) {
		this.avgDotBowlPrerentage = avgDotBowlPrerentage;
	}

	public Double getAvgStrikeRate() {
		return avgStrikeRate;
	}

	public void setAvgStrikeRate(Double avgStrikeRate) {
		this.avgStrikeRate = avgStrikeRate;
	}

	public Double getAvgAverage() {
		return avgAverage;
	}

	public void setAvgAverage(Double avgAverage) {
		this.avgAverage = avgAverage;
	}

	public Double getAvgEconomy() {
		return avgEconomy;
	}

	public void setAvgEconomy(Double avgEconomy) {
		this.avgEconomy = avgEconomy;
	}

	public Double getAvgDotBowlToRunsRatio() {
		return avgDotBowlToRunsRatio;
	}

	public void setAvgDotBowlToRunsRatio(Double avgDotBowlToRunsRatio) {
		this.avgDotBowlToRunsRatio = avgDotBowlToRunsRatio;
	}

	public Double getAvgTotalNumberOfInnings() {
		return avgTotalNumberOfInnings;
	}

	public void setAvgTotalNumberOfInnings(Double avgTotalNumberOfInnings) {
		this.avgTotalNumberOfInnings = avgTotalNumberOfInnings;
	}

}
