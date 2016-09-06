/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.csa.playerprofile.util;

/**
 *
 * @author sanjya
 * 
 */
import java.util.ArrayList;
import java.util.Map;
import com.csa.entity.Bowl;
import com.csa.entity.Innings;
import com.csa.player.entity.Batsman;

public class BatsmanPlayerProfile {

	public BatsmanPlayerProfile() {
		// TODO Auto-generated constructor stub
	}

	public Double getBattingAverage(ArrayList<Bowl> bowlList) {

		// this gives us number of times batsman got out while batting
		int numberOfWickets = 0;
		int numberOfRuns = 0;
		Double battingAverage = 0.0;

		for (int i = 0; i < bowlList.size(); i++) {

			Bowl bowl = bowlList.get(i);
			Bowl nextBowl = bowlList.get(i + 1);

			// for the moment all extras are put in to bowlers account
			// this need to be change
			// extra type should be populate at the getMatchDetails
			numberOfRuns = numberOfRuns + bowl.getRuns();

			if ((bowl.getIsWicket() == 1)
					&& (!bowl.getWicket().getWicketType().equals("run out"))) {

				// if nonStriker of the nextbowl is different from this bowl
				// then,
				// runout batsman is nonStrker
				if (!bowl.nonStriker.equals(nextBowl.nonStriker)) {
					numberOfWickets = numberOfWickets + 1;
				}
			}
		}

		battingAverage = numberOfRuns / (numberOfWickets + 0.0);
		if (numberOfWickets == 0) {
			// this must change to max average batman can have
			return 1000.0;
		}

		return battingAverage;
	}

	public Batsman getKeyParamsBatsman(Batsman oldBatsman, Innings innings) {

		int totalNumberOfInnings;
		int totalNumberOfOuts;
		int totalNumberOfNotOuts;

		int totalNumberOfRuns;
		int totalNumberOf1s;
		int totalNumberOf2s;
		int totalNumberOf4s;
		int totalNumberOf6s;

		int totalNumberOfDots;
		int totalNumberOfBowls;
		int totalNumberOfSegments;

		totalNumberOfInnings = oldBatsman.getTotalNumberOfInnings();
		totalNumberOfOuts = oldBatsman.getTotalNumberOfOuts();
		totalNumberOfNotOuts = oldBatsman.getTotalNumberOfNotOuts();

		totalNumberOfRuns = oldBatsman.getTotalNumberOfRuns();
		totalNumberOf1s = oldBatsman.getTotalNumberOf1s();
		totalNumberOf2s = oldBatsman.getTotalNumberOf2s();
		totalNumberOf4s = oldBatsman.getTotalNumberOf4s();
		totalNumberOf6s = oldBatsman.getTotalNumberOf6s();

		totalNumberOfDots = oldBatsman.getTotalNumberOfDotBowls();
		totalNumberOfBowls = oldBatsman.getTotalNumberOfBowls();
		totalNumberOfSegments = oldBatsman.getTotalNumberOfBattingSegments();

		// derived Parameters
		Double battingAverage = 0.0;
		Double battingStrikeRate = 0.0;
		Double battingDotBowlPrestage = 0.0;
		Double runRate = 0.0;
		Double battingDotBowlToRunsRatio = 0.0;

		int numberOfRuns = 0;
		int numberOf1s = 0;
		int numberOf2s = 0;
		int numberOf4s = 0;
		int numberOf6s = 0;
		int numberOfDots = 0;
		int numberOfBowls = 0;
		int numberOfSegments = 0;

		Map<Integer, Bowl> deliveries = innings.getDeliveries();

		Batsman newBatsman = new Batsman();
		Bowl bowl;
		Bowl nextBowl;
		String batsmanName = null;
		String givenBatsmanName = oldBatsman.getName();
		boolean isOut = false;

		for (int i = 1; i <= deliveries.size(); i++) {
			bowl = deliveries.get(i);

			if (i == deliveries.size()) {
				nextBowl = deliveries.get(i);
			} else {
				nextBowl = deliveries.get(i + 1);
			}
			// batsman who face the ball
			batsmanName = bowl.getBatsman();
			// if batman faced the ball is equal to this batsman

			if (batsmanName.equals(givenBatsmanName)) {

				numberOfRuns = numberOfRuns + bowl.getRuns() - bowl.getExtras();
				numberOfBowls = numberOfBowls + 1;

				if (bowl.getRuns() == 0) {
					numberOfDots = numberOfDots + 1;
				}
				// need to isolate the runs from the bat only
				if (bowl.getRuns() == 4) {
					numberOf4s = numberOf4s + 1;
				}
				if (bowl.getRuns() == 1) {
					numberOf1s = numberOf1s + 1;
				}
				if (bowl.getRuns() == 6) {
					numberOf6s = numberOf6s + 1;
				}
				if (bowl.getRuns() == 2) {
					numberOf2s = numberOf2s + 1;
				}
				// count segmentation
				if ((bowl.getIsWicket() == 0)
						&& (bowl.getBatsman().equals(nextBowl.getBatsman()))) {
					numberOfSegments = numberOfSegments + 1;
				}

				if ((bowl.getIsWicket() == 1)
						&& bowl.getWicket().getBatsman()
								.equals(givenBatsmanName)) {
					isOut = true;
				}
			}
		}
		// System.out.println(newBowler.getTotalNumberOfRuns());

		// generating bowling average of a bowler // strike rate
		if (totalNumberOfOuts == 0) {
			battingAverage = 30.0;
		} else {
			battingAverage = totalNumberOfRuns * 1.0 / (totalNumberOfOuts);
		}
		// these parameters should be changed after geting the averege values
		if (totalNumberOfBowls == 0) {
			battingStrikeRate = 100.00;
			runRate = 6.0;
			battingDotBowlPrestage = 30.0;
		} else {
			battingStrikeRate = totalNumberOfRuns * 100.0
					/ (totalNumberOfBowls);
			runRate = totalNumberOfRuns * 1.0 * 6.0 / (totalNumberOfBowls);

			battingDotBowlPrestage = totalNumberOfDots * 100.0
					/ (totalNumberOfBowls);
		}

		if (totalNumberOfDots == 0) {
			battingDotBowlToRunsRatio = 3.0;
		} else {
			battingDotBowlToRunsRatio = totalNumberOfRuns * 1.0
					/ (totalNumberOfDots);
		}

		totalNumberOfInnings = totalNumberOfInnings + 1;

		if (isOut) {
			totalNumberOfOuts = totalNumberOfOuts + 1;
		} else {
			totalNumberOfNotOuts = totalNumberOfNotOuts + 1;
		}

		newBatsman.setTotalNumberOfBowls(totalNumberOfBowls + numberOfBowls);
		newBatsman.setTotalNumberOfRuns(totalNumberOfRuns + numberOfRuns);
		newBatsman.setTotalNumberOfDotBowls(totalNumberOfDots + numberOfDots);
		newBatsman.setTotalNumberOfBattingSegments(totalNumberOfSegments
				+ numberOfSegments);

		newBatsman.setTotalNumberOf1s(totalNumberOf1s + numberOf1s);
		newBatsman.setTotalNumberOf2s(totalNumberOf2s + numberOf2s);
		newBatsman.setTotalNumberOf4s(totalNumberOf4s + numberOf4s);
		newBatsman.setTotalNumberOf6s(totalNumberOf6s + numberOf6s);

		newBatsman.setName(batsmanName);
		newBatsman.setTotalNumberOfInnings(totalNumberOfInnings);
		newBatsman.setTotalNumberOfOuts(totalNumberOfOuts + 0);
		newBatsman.setTotalNumberOfNotOuts(totalNumberOfNotOuts + 0);

		// these factors are generated without this match
		newBatsman.setbatmansDotBowlPresentage(battingDotBowlPrestage);
		newBatsman.setbattingAverage(battingAverage);
		newBatsman.setbattingStrikeRate(battingStrikeRate);
		newBatsman.setbattingEconomy(runRate);
		newBatsman.setRunRate(runRate);
		newBatsman.setDotBowlToRunsRatio(battingDotBowlToRunsRatio);

		return newBatsman;
	}

	public Double getBatsmanStrikeRate(ArrayList<Bowl> bowlList) {
		int numberOfRuns = 0;
		int numberOfBowls = bowlList.size();
		Double strikeRate = 0.0;

		for (int i = 0; i < bowlList.size(); i++) {
			Bowl bowl = bowlList.get(i);

			numberOfRuns += bowl.getRuns();

		}
		if (numberOfRuns == 0 || numberOfBowls == 0) {
			return 0.0;
		}
		strikeRate = 100.0 * numberOfRuns / (numberOfBowls + 0.0);
		return strikeRate;
	}

	public Double getDotBowlPresentage(ArrayList<Bowl> bowlList) {
		int numberOfBowls = bowlList.size();
		int numberOfDotBowls = 0;
		Double dotBowlPresentage = 0.0;
		for (int i = 0; i < bowlList.size(); i++) {
			Bowl bowl = bowlList.get(i);
			// this is not the correct definition of a dot bowl this need to be
			// changed
			if (bowl.getRuns() == 0) {
				numberOfDotBowls = numberOfDotBowls + 1;
			}
		}

		if (numberOfDotBowls == 0) {
			return 0.0;
		}
		dotBowlPresentage = numberOfDotBowls * 100 / (numberOfBowls + 0.0);
		return dotBowlPresentage;
	}

	public Double getDotBowlToRunsRatio(ArrayList<Bowl> bowlList) {

		int numberOfDotBowls = 0;
		int numberOfRuns = 0;
		Double dotBowlToRunsRatio = 0.0;
		for (int i = 0; i < bowlList.size(); i++) {
			Bowl bowl = bowlList.get(i);
			// this is not the correct definition of a dot bowl this need to be
			// changed
			if (bowl.getRuns() == 0) {
				numberOfDotBowls = numberOfDotBowls + 1;
			} else {
				numberOfRuns = numberOfRuns + bowl.getRuns() - bowl.getExtras();
			}
		}

		if (numberOfDotBowls == 0) {
			return 0.0;
		}
		dotBowlToRunsRatio = numberOfRuns * 100 / (numberOfDotBowls + 0.0);
		return dotBowlToRunsRatio;
	}

	public Double getRunRateWhileBatting(ArrayList<Bowl> bowlList) {

		int numberOfRuns = 0;
		int numberOfBowls = bowlList.size();
		Double strikeRate = 0.0;

		for (int i = 0; i < bowlList.size(); i++) {
			Bowl bowl = bowlList.get(i);
			numberOfRuns += bowl.getRuns();
		}

		if (numberOfRuns == 0 || numberOfBowls == 0) {
			return 0.0;
		}

		strikeRate = numberOfRuns * 6 / (numberOfBowls + 0.0);
		return strikeRate;
	}
}