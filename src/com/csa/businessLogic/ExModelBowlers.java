package com.csa.businessLogic;

import java.io.File;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.csa.entity.Bowl;
import com.csa.entity.CombineExperianceBowler;
import com.csa.entity.Innings;
import com.csa.entity.MatchDetails;
import com.csa.entity.Result;
import com.csa.entity.Team;
import com.csa.entity.Wicket;
import com.csa.player.entity.Bowler;
import com.csa.playerprofile.util.PlayerProfile;
import com.csa.util.BowlersUtil;
import com.csa.util.FileUtil;
import com.csa.util.InningsUtil;
import com.csa.util.MatchUtil;
import com.csa.util.PlayerUtil;
import com.csa.visualization.BatsmansInning;
import com.csa.visualization.InningByInningsResults;
import com.esotericsoftware.yamlbeans.YamlException;

public class ExModelBowlers {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new Configuration().configure(
				"hibernatePlayer.cfg.xml").buildSessionFactory();

		SessionFactory sfFirstInnings = new Configuration().configure(
				"FirstInningsBowler.cfg.xml").buildSessionFactory();

		SessionFactory sfSecondInnings = new Configuration().configure(
				"SecondInningsBowler.cfg.xml").buildSessionFactory();

		Session sessionPlayer = sessionFactory.openSession();

		ArrayList<String> filenameList = FileUtil.fileList("resources/ipl/");
		for (String fileName : filenameList) {

			String filepath = "resources/ipl/" + fileName;

			File file = null;

			try {
				file = new File(filepath);

			} catch (Exception e) {
				e.printStackTrace();
			}
			MatchDetails match;
			// visualization
			HashMap<Integer, BatsmansInning> allBattingInnings;

			InningByInningsResults inningResults;
			BatsmansInning battingInnings;
			sessionPlayer.beginTransaction();

			try {
				match = MatchUtil.getMatchInfoFromFile(file);

				/** add experience based factors **/

				/***** get blowers in this match ***************************/
				ArrayList<String> firstInningsBowlers = BowlersUtil
						.getBowlers(match.getFirstInnings());

				/***** get blowers in this match ***************************/
				ArrayList<String> secondInningsBowlers = BowlersUtil
						.getBowlers(match.getSecondInnings());

				/************** get Bowlers Details *************************/

				// session for 2nd database, experiance based factors
			//	Session session1st = sfFirstInnings.openSession();

				// session for 2nd database, experiance based factors
			//	Session session2nd = sfSecondInnings.openSession();

				/** Experience Based Bowler **/

				// number Of Matches Played By Bowlers 1stInnings
				int numOfMatches1 = 0;
				Double sumOfAvgRuns1 = 0.0;
				Double sumOfAvg1 = 0.0;
				Double sumOfStrikeRate1 = 0.0;
				Double sumOfAvgDotBowls1 = 0.0;
				Double dotBowlsToRuns1 = 0.0;

				int numOfMatches2 = 0;
				Double sumOfAvgRuns2 = 0.0;
				Double sumOfAvg2 = 0.0;
				Double sumOfStrikeRate2 = 0.0;
				Double sumOfAvgDotBowls2 = 0.0;
				Double dotBowlsToRuns2 = 0.0;

				ArrayList<Bowl> deliveriesList;
				String bowlersName;
				Double bowlingAverage;
				Double bowlingStrikeRate;
				Double dotBowlPresentage;
				Double bowlingEconomy;

				int inningsNumber;

				int numberOfMatches;
				int totalNumberOfBowls;
				int totalNumberOfDotBowls;
				int totalNumberOfWickets;

				int numberOfBowlsInPowerPlay;
				int numberOfBowlsInMiddle;
				int numberOfBowlsInDeath;

				int numberOfDotsInPowerPlay;
				int numberOfDotsInMiddle;
				int numberOfDotsInDeath;

				int numberOfWicketsInPowerPlay;
				int numberOfWicketsInMiddle;
				int numberOfWicketsInDeath;

				Bowler oldBowler;
				Bowler newBowler;

				try {

					for (int i = 0; i < firstInningsBowlers.size(); i++) {

						newBowler = new Bowler();

						bowlersName = firstInningsBowlers.get(i);

						if (bowlersName.contains(")")) {
							continue;
						}

						oldBowler = BowlersUtil.getOldBowler(sessionPlayer,
								bowlersName);

						System.out.println("This is bowlers Name :"
								+ oldBowler.getName());

						numOfMatches1 = numOfMatches1
								+ oldBowler.getTotalNumberOfInnings();
						sumOfAvgRuns1 = sumOfAvgRuns1
								+ oldBowler.getAverageRunsInInnings();
						sumOfAvg1 = sumOfAvg1 + oldBowler.getBowlingAverage();
						sumOfStrikeRate1 = sumOfStrikeRate1
								+ oldBowler.getBowlingStrikeRate();
						sumOfAvgDotBowls1 = sumOfAvgDotBowls1
								+ oldBowler.getBowlersDotBowlPresentage();
						dotBowlsToRuns1 = dotBowlsToRuns1
								+ oldBowler.getDotBowlToRunsRatio();
					}

				} catch (Exception e) {
					e.printStackTrace();

				} finally {
					// System.out.println(allBowlersList.toString());
					// System.out.println(allMatches.size());

				}
//
//				try {
//
//					for (int i = 0; i < secondInningsBowlers.size(); i++) {
//
//						oldBowler = new Bowler();
//						int matchId = match.getMatchId();
//
//						bowlersName = secondInningsBowlers.get(i);
//
//						if (bowlersName.contains(")")) {
//							continue;
//						}
//
//						oldBowler = BowlersUtil.getOldBowler(sessionPlayer,
//								bowlersName);
//
//						System.out.println("THis is bowlers Name :"
//								+ oldBowler.getName());
//
//						numOfMatches2 = numOfMatches2
//								+ oldBowler.getTotalNumberOfInnings();
//						sumOfAvgRuns2 = sumOfAvgRuns2
//								+ oldBowler.getAverageRunsInInnings();
//						sumOfAvg2 = sumOfAvg2 + oldBowler.getBowlingAverage();
//						sumOfStrikeRate2 = sumOfStrikeRate2
//								+ oldBowler.getBowlingStrikeRate();
//						sumOfAvgDotBowls2 = sumOfAvgDotBowls2
//								+ oldBowler.getBowlersDotBowlPresentage();
//						dotBowlsToRuns2 = dotBowlsToRuns2
//								+ oldBowler.getDotBowlToRunsRatio();
//					}
//
//				} catch (Exception e) {
//					e.printStackTrace();
//
//				} finally {
//				}

				CombineExperianceBowler ceb = new CombineExperianceBowler();

				// these 2 should be more sofisticated
				int numberOfBowlers1 = firstInningsBowlers.size();
				int numberOfBowlers2 = secondInningsBowlers.size();
//
//				int diffMatches = numOfMatches1 - numOfMatches2;
//				Double diffAvg = (sumOfAvg1 / numberOfBowlers1)
//						- (sumOfAvg2 / numberOfBowlers2);
//				Double diffStrikeRate = (sumOfStrikeRate1 / numberOfBowlers1)
//						- (sumOfStrikeRate2 / numberOfBowlers2);
//				Double diffDotPres = (sumOfAvgDotBowls1 / numberOfBowlers1)
//						- (sumOfAvgDotBowls2 / numberOfBowlers2);
//				Double diffDotRunsRatio = (dotBowlsToRuns1 / numberOfBowlers1)
//						- (dotBowlsToRuns2 / numberOfBowlers1);
//
//				
				
				int diffMatches = numOfMatches1;
				Double diffAvg = (sumOfAvg1 / numberOfBowlers1);
				Double diffStrikeRate = (sumOfStrikeRate1 / numberOfBowlers1);
				Double diffDotPres = (sumOfAvgDotBowls1 / numberOfBowlers1);
				Double diffDotRunsRatio = (dotBowlsToRuns1 / numberOfBowlers1);

				int win = match.getResult().getWonByFirstBatOrSecondBat();

				ceb.setDffnoOfMatches(diffMatches);
				ceb.setDiffAvgAvgOfBowlers(diffAvg);
				ceb.setDiffAvgStrikeRate(diffStrikeRate);
				ceb.setDiffDotBowlPresentage(diffDotPres);
				ceb.setDiffDotBowlsToRunsRatio(diffDotRunsRatio);
				ceb.setWin(win);

				try {
					sessionPlayer.beginTransaction();
					sessionPlayer.save(ceb);
					sessionPlayer.getTransaction().commit();

				} catch (Exception e) {
					e.printStackTrace();
				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (YamlException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		sessionPlayer.close();
		sessionFactory.close();
	}
}
