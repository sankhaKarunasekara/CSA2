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

public class FirstInningsOnly {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new Configuration().configure(
				"hibernate.cfg.xml").buildSessionFactory();
		SessionFactory sessionFactoryPlayer = new Configuration().configure(
				"hibernatePlayer1Only.cfg.xml").buildSessionFactory();

		Session session = sessionFactory.openSession();

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

			Innings innings1;
			Innings innings2;

			// need to change, unnecessary
			Team battingTeam1;
			Team fieldingTeam1;

			Team battingTeam2;
			Team fieldingTeam2;

			Bowl bowl;
			Wicket wicket;

			Result result;

			// visualization
			HashMap<Integer, BatsmansInning> allBattingInnings;

			InningByInningsResults inningResults;
			BatsmansInning battingInnings;
			session.beginTransaction();

			try {
				match = MatchUtil.getMatchInfoFromFile(file);

				innings1 = match.getFirstInnings();
				innings2 = match.getSecondInnings();

				battingTeam1 = innings1.getBattingTeam();
				fieldingTeam1 = innings1.getFieldingTeam();

				battingTeam2 = innings2.getBattingTeam();
				fieldingTeam2 = innings2.getFieldingTeam();

				session.save(match);

				session.save(innings1);
				session.save(innings2);

				session.save(battingTeam1);
				session.save(fieldingTeam1);

				session.save(battingTeam2);
				session.save(fieldingTeam2);

				Map<Integer, Bowl> firstInningsDeliveries = innings1
						.getDeliveries();
				Map<Integer, Bowl> secondInningsDeliveries = innings2
						.getDeliveries();

				for (int i = 1; i <= firstInningsDeliveries.size(); i++) {
					bowl = firstInningsDeliveries.get(i);

					// System.out.println(bowl.bowlnumber);
					session.save(bowl);

					wicket = bowl.getWicket();
					if (bowl.isWicket == 1) {
						session.save(wicket);
					}
				}

				for (int i = 1; i <= secondInningsDeliveries.size(); i++) {
					bowl = secondInningsDeliveries.get(i);
					session.save(bowl);

					wicket = bowl.getWicket();
					if (bowl.isWicket == 1) {
						session.save(wicket);
					}
				}
				result = match.getResult();
				session.save(result);

				// visualization
				allBattingInnings = PlayerUtil
						.getScoreCardDetailsFirstInnings(match);

				for (int i = 1; i <= allBattingInnings.size(); i++) {
					// for (int i = 1; i < 2; i++) {
					battingInnings = allBattingInnings.get(i);
					session.save(battingInnings);
				}

				// visualization
				allBattingInnings = PlayerUtil
						.getScoreCardDetailsSecondInnings(match);

				for (int i = 1; i <= allBattingInnings.size(); i++) {
					// for (int i = 1; i < 2; i++) {
					battingInnings = allBattingInnings.get(i);
					session.save(battingInnings);
				}
				// innings Results visualization
				inningResults = InningsUtil
						.generateInningsByInningsResultsFirstInnings(match);

				session.save(inningResults);

				inningResults = InningsUtil
						.generateInningsByInningsResultsSecondInnings(match);
				session.save(inningResults);

				session.getTransaction().commit();

				/** add experiance based factors **/

				ArrayList<String> allBowlersList = BowlersUtil
						.getAllBowlers(session);
				/*********************************************************/

				/***** get blowers in this match ***************************/
				ArrayList<String> firstInningsBowlers = BowlersUtil
						.getBowlers(match.getFirstInnings());

				/************** get Bowlers Details *************************/

				// session for 2nd database, experiance based factors
				Session sessionPlayer = sessionFactoryPlayer.openSession();

							
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

				//about save the bowler
				try {
					System.out.println("First Innings Bowlers: "
							+ firstInningsBowlers.toString());
					for (int i = 0; i < firstInningsBowlers.size(); i++) {

						newBowler = new Bowler();

						bowlersName = firstInningsBowlers.get(i);

						if (bowlersName.contains(")")) {
							continue;
						}
						// deliveriesList =
						// BowlersUtil.getBowlersDeliveriesList(
						// session, bowlersName);
						oldBowler = BowlersUtil.getOldBowler(sessionPlayer,
								bowlersName);

						System.out.println("This is bowlers Name :"
								+ oldBowler.getName());

						System.out.println();
						PlayerProfile player = new PlayerProfile();

						newBowler = player.getKeyParamsBowler(oldBowler,
								match.getFirstInnings());
						newBowler.setName(bowlersName);
						newBowler.setInningsNumber(1);

						int matchId = match.getMatchId();
						newBowler.setMatchId(matchId);

						try {
							sessionPlayer.beginTransaction();
							sessionPlayer.save(newBowler);
							sessionPlayer.getTransaction().commit();

						} catch (Exception e) {
							System.out.println("bowlersName: " + bowlersName);
							e.printStackTrace();
						}
					}//end of for

				} catch (Exception e) {
					e.printStackTrace();

				} finally {
					// System.out.println(allBowlersList.toString());
					// System.out.println(allMatches.size());
				}
				
				try {
					// interesting start

					// number Of Matches Played By Bowlers 1stInnings
					int numOfMatches1 = 0;
					Double sumOfAvgRuns1 = 0.0;
					Double sumOfAvg1 = 0.0;
					Double sumOfStrikeRate1 = 0.0;
					Double sumOfAvgDotBowls1 = 0.0;
					Double dotBowlsToRuns1 = 0.0;

					//about gather records of bowlers First Innings Combine
					try {

						for (int i = 0; (i < firstInningsBowlers.size() && i < 5); i++) {

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
							sumOfAvg1 = sumOfAvg1
									+ oldBowler.getBowlingAverage();
							sumOfStrikeRate1 = sumOfStrikeRate1
									+ oldBowler.getBowlingStrikeRate();
							sumOfAvgDotBowls1 = sumOfAvgDotBowls1
									+ oldBowler.getBowlersDotBowlPresentage();
							dotBowlsToRuns1 = dotBowlsToRuns1
									+ oldBowler.getDotBowlToRunsRatio();

							CombineExperianceBowler ceb = new CombineExperianceBowler();

							// these 2 should be more sofisticated
							int numberOfBowlers1 = firstInningsBowlers.size();

							int diffMatches = numOfMatches1;
							Double diffAvg = (sumOfAvg1 / numberOfBowlers1);
							Double diffStrikeRate = (sumOfStrikeRate1 / numberOfBowlers1);
							Double diffDotPres = (sumOfAvgDotBowls1 / numberOfBowlers1);
							Double diffDotRunsRatio = (dotBowlsToRuns1 / numberOfBowlers1);

							int win = match.getResult()
									.getWonByFirstBatOrSecondBat();

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
							// upto here
						}

					} catch (Exception e) {
						e.printStackTrace();

					} finally {
						// System.out.println(allBowlersList.toString());
						// System.out.println(allMatches.size());
					}// first inning bowlers finish

					// Experience Based Bowler
				} catch (Exception e) {
					e.printStackTrace();

				} finally {
					sessionPlayer.close();
					System.out.println(allBowlersList.toString());
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
			
		}//end of for
		session.close();
		sessionFactory.close();
	}
}