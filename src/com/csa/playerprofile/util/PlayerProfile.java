package com.csa.playerprofile.util;

import java.util.ArrayList;
import java.util.Map;

import com.csa.entity.Bowl;
import com.csa.entity.Innings;
import com.csa.player.entity.Bowler;

public class PlayerProfile {

	public PlayerProfile() {
		// TODO Auto-generated constructor stub
	}

	public Double getBowlingAverage(ArrayList<Bowl> bowlList) {

		int numberOfWickets = 0;
		int numberOfRuns = 0;
		Double bowlingAverage = 0.0;
		for (int i = 0; i < bowlList.size(); i++) {

			Bowl bowl = bowlList.get(i);
			// for the moment all extras are put in to bowlers account
			// this need to be change
			// extra type should be populate at the getMatchDetails
			numberOfRuns = numberOfRuns + bowl.getTotalRuns();

			if ((bowl.getIsWicket() == 1)
					&& (!bowl.getWicket().getWicketType().equals("run out"))) {
				numberOfWickets = numberOfWickets + 1;
			}

		}
		bowlingAverage = numberOfRuns / (numberOfWickets + 0.0);
		if (numberOfWickets == 0) {
			return 1000.0;
		}

		return bowlingAverage;
	}

	public Bowler getTotalNumberOfParams(Bowler oldBowler, Innings innings) {

		int numberOfWickets = oldBowler.getTotalNumberOfWickets();
		int numberOfRuns = oldBowler.getTotalNumberOfRuns();
		int numberOfDots = oldBowler.getTotalNumberOfDotBowls();
		int numberOfExtras = oldBowler.getTotalNumberOfExtras();
		int numberOfBowls = oldBowler.getTotalNumberOfBowls();

		Map<Integer, Bowl> deliveries = innings.getDeliveries();

		Bowler newBowler = new Bowler();
		Bowl bowl;
		String bowlersName;
		String givenBowlersName = oldBowler.getName();

		for (int i = 1; i <= deliveries.size(); i++) {
			bowl = deliveries.get(i);
			
			//bowler who ball the bowl
			bowlersName = bowl.getBowler();

			// if bowler of the ball is equal to this bowler
			if (bowlersName.equals(givenBowlersName)) {

				// please remove extras in another version
				numberOfRuns = numberOfRuns + bowl.getTotalRuns();
				numberOfExtras = numberOfExtras + bowl.getTotalRuns()
						- bowl.getRuns();

				numberOfBowls = numberOfBowls + 1;

				if (bowl.getTotalRuns() == 0) {
					numberOfDots = numberOfDots + 1;
				}

				if ((bowl.getIsWicket() == 1)
						&& (!bowl.getWicket().getWicketType().equals("run out"))) {
					numberOfWickets = numberOfWickets + 1;
				}
			}

			// System.out.println(newBowler.getTotalNumberOfRuns());
			newBowler.setTotalNumberOfBowls(numberOfBowls);

			newBowler.setTotalNumberOfRuns(numberOfRuns);

			newBowler.setTotalNumberOfWickets(numberOfWickets);
			newBowler.setTotalNumberOfDotBowls(numberOfDots);
			newBowler.setTotalNumberOfExtras(numberOfExtras);

		}

		return newBowler;
	}

	public Double getBowlersStrikeRate(ArrayList<Bowl> bowlList) {

		int numberOfWickets = 0;
		int numberOfBowls = bowlList.size();
		Double strikeRate = 0.0;
		for (int i = 0; i < bowlList.size(); i++) {

			Bowl bowl = bowlList.get(i);

			if ((bowl.getIsWicket() == 1)
					&& (!bowl.getWicket().getWicketType().equals("run out"))) {
				numberOfWickets = numberOfWickets + 1;
			}

		}
		if (numberOfWickets == 0) {
			return 10000.0;
		}

		strikeRate = numberOfBowls / (numberOfWickets + 0.0);

		return strikeRate;
	}

	public Double getBowlersEconomy(ArrayList<Bowl> bowlList) {

		int numberOfBowls = bowlList.size();
		int numberOfRuns = 0;

		Double econ = 0.0;
		for (int i = 0; i < bowlList.size(); i++) {

			Bowl bowl = bowlList.get(i);
			numberOfRuns = numberOfRuns + bowl.getTotalRuns();

		}
		econ = numberOfRuns * 6 / (numberOfBowls + 0.0);

		return econ;
	}

	public Double getDotBowlPresentage(ArrayList<Bowl> bowlList) {

		int numberOfBowls = bowlList.size();
		int numberOfDotBowls = 0;

		Double dotBowlPresentage = 0.0;
		for (int i = 0; i < bowlList.size(); i++) {

			Bowl bowl = bowlList.get(i);

			// this is not the correct definition of a dot bowl this need to be
			// changed
			if (bowl.getTotalRuns() == 0) {
				numberOfDotBowls = numberOfDotBowls + 1;
			}

		}
		dotBowlPresentage = numberOfDotBowls * 100 / (numberOfBowls + 0.0);

		return dotBowlPresentage;
	}
}
