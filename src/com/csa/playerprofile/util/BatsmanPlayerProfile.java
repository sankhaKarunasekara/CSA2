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
		String nonStriker;
		String nextBowlNunStriker;

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

	public Batsman getTotalNumberOfParams(Batsman oldBatsman, Innings innings) {

		int numberOfRuns = oldBatsman.getTotalNumberOfRuns();
		int numberOfDots = oldBatsman.getTotalNumberOfDotBowls();
		int numberOfBowls = oldBatsman.getTotalNumberOfBowls();

		Map<Integer, Bowl> deliveries = innings.getDeliveries();
		Batsman newBatsman = new Batsman();
		Bowl bowl;
		String batsmanName;
		String givenBatsmanName = oldBatsman.getName();

		for (int i = 1; i <= deliveries.size(); i++) {
			bowl = deliveries.get(i);
			// batsman who face the ball
			batsmanName = bowl.getBatsman();
			// if batman faced the ball is equal to this batsman

			if (batsmanName.equals(givenBatsmanName)) {
				// please remove extras in another version
				numberOfRuns = numberOfRuns + bowl.getRuns();
				numberOfBowls = numberOfBowls + 1;

				if (bowl.getRuns() == 0) {
					numberOfDots = numberOfDots + 1;
				}

			}
			// System.out.println(newBowler.getTotalNumberOfRuns());
			newBatsman.setTotalNumberOfBowls(numberOfBowls);
			newBatsman.setTotalNumberOfRuns(numberOfRuns);
			newBatsman.setTotalNumberOfDotBowls(numberOfDots);
		}
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
}
