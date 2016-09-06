package com.csa.businessLogic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.csa.entity.Bowl;
import com.csa.entity.CombineExperianceBatsman;
import com.csa.entity.CombineExperianceBatsmanAndBowler;
import com.csa.entity.CombineExperianceBowler;
import com.csa.entity.Innings;
import com.csa.entity.MatchDetails;
import com.csa.entity.Result;
import com.csa.entity.Team;
import com.csa.entity.Wicket;
import com.csa.player.entity.Batsman;
import com.csa.player.entity.Bowler;
import com.csa.playerprofile.util.BatsmanPlayerProfile;
import com.csa.playerprofile.util.PlayerProfile;
import com.csa.util.BatsmanUtil;
import com.csa.util.BowlersUtil;
import com.csa.util.FileUtil;
import com.csa.util.InningsUtil;
import com.csa.util.MatchUtil;
import com.csa.util.PlayerUtil;
import com.csa.visualization.BatsmansInning;
import com.csa.visualization.InningByInningsResults;
import com.esotericsoftware.yamlbeans.YamlException;

/* this class is to generate,
 * Update the database
 * Innings by Innings result analasize
 * Bowler
 * Batsman
 */
public class ALL {

	public static void main(String[] args) throws FileNotFoundException {

		SessionFactory sessionFactory = new Configuration().configure(
				"hibernateAll.cfg.xml").buildSessionFactory();
		SessionFactory sessionFactoryPlayer = new Configuration().configure(
				"hibernatePlayerAll.cfg.xml").buildSessionFactory();

		// PrintStream out = new PrintStream(new
		// FileOutputStream("logs/output.txt"));
		// System.setOut(out);

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
			MatchDetails match = null;

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

				/***** get blowers in first Innings ***************************/
				ArrayList<String> firstInningsBowlers = BowlersUtil
						.getBowlers(match.getFirstInnings());

				/***** get batmans in first Innings match ***************************/
				ArrayList<String> firstInningsBatmans = BatsmanUtil
						.getBatsmans(match.getFirstInnings());

				/***** get batmans in secondInnings *********************************/
				ArrayList<String> SecondInningsBatmans = BatsmanUtil
						.getBatsmans(match.getSecondInnings());

				/***** get blowers in second Innings ***************************/
				ArrayList<String> secondInningsBowlers = BowlersUtil
						.getBowlers(match.getSecondInnings());

				/*************** get all matches List *********************/
				ArrayList<MatchDetails> allMatches = BowlersUtil
						.getAllMatches(session);

				/************** get Bowlers Details *************************/

				// session for 2nd database, experiance based factors
				Session sessionPlayer = sessionFactoryPlayer.openSession();

				// bowlers
				String bowlersName = null;
				Double bowlingAverage;
				Double bowlingStrikeRate = null;
				Double dotBowlPresentage;
				Double bowlingEconomy;

				int numberOfMatches;
				int totalNumberOfBowls;
				int totalNumberOfDotBowls;
				int totalNumberOfWickets;

				Bowler oldBowler;
				Bowler newBowler = null;

				// batsmans
				Batsman batsman;
				String batsmansName = "";
				Double battingAverage;
				Double battingStrikeRate;
				Double battingDotBowlPrestage;
				Double battingDotBowlToRunsRatio = 0.0;
				Double runRate;

				Batsman oldBatsman;
				Batsman newBatsman;

				// common parameters
				ArrayList<Bowl> deliveriesList = null;
				int inningsNumber;
				int matchId = match.getMatchId();

				System.out.println("First Innings Bowlers: "
						+ firstInningsBowlers.toString());

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

					System.out.println();
					PlayerProfile player = new PlayerProfile();

					newBowler = player.getKeyParamsBowler(oldBowler,
							match.getFirstInnings());
					newBowler.setName(bowlersName);
					newBowler.setInningsNumber(1);
					newBowler.setMatchId(matchId);

					try {
						sessionPlayer.beginTransaction();
						sessionPlayer.save(newBowler);
						sessionPlayer.getTransaction().commit();

					} catch (Exception e) {
						System.out.println("bowlersName: " + bowlersName);
						e.printStackTrace();
					}
				}

				// first innings batsmans
				for (int j = 0; j < firstInningsBatmans.size(); j++) {

					newBatsman = new Batsman();
					batsmansName = firstInningsBatmans.get(j);

					if (batsmansName.contains(")")) {
						continue;
					}

					oldBatsman = BatsmanUtil.getOldBatsman(sessionPlayer,
							batsmansName);

					System.out.println("This is Batsmans Name :"
							+ oldBatsman.getName());

					System.out.println();
					BatsmanPlayerProfile batsmanPlayer = new BatsmanPlayerProfile();

					newBatsman = batsmanPlayer.getKeyParamsBatsman(oldBatsman,
							match.getFirstInnings());
					newBatsman.setName(batsmansName);
					newBatsman.setInningsNumber(1);
					newBatsman.setMatchId(matchId);

					try {
						sessionPlayer.beginTransaction();
						sessionPlayer.save(newBatsman);
						sessionPlayer.getTransaction().commit();

					} catch (Exception e) {
						System.out.println(" THis bowlersName: " + bowlersName);
						e.printStackTrace();
					}
				}// for

				for (int i = 0; i < secondInningsBowlers.size(); i++) {

					oldBowler = new Bowler();
					bowlersName = secondInningsBowlers.get(i);

					if (bowlersName.contains(")")) {
						continue;
					}

					oldBowler = BowlersUtil.getOldBowler(sessionPlayer,
							bowlersName);

					System.out.println("THis is bowlers Name :"
							+ oldBowler.getName());

					PlayerProfile player = new PlayerProfile();
					newBowler = new Bowler();
					newBowler = player.getKeyParamsBowler(oldBowler,
							match.getSecondInnings());
					newBowler.setName(bowlersName);

					// int matchId = match.getMatchId();
					newBowler.setMatchId(matchId);
					newBowler.setInningsNumber(2);

					try {
						sessionPlayer.beginTransaction();
						sessionPlayer.save(newBowler);
						sessionPlayer.getTransaction().commit();

					} catch (Exception e) {
						System.out.println("bowlersName: " + bowlersName);
						e.printStackTrace();
					}
				}// for second innings bowlers

				for (int j = 0; j < SecondInningsBatmans.size(); j++) {

					newBatsman = new Batsman();
					batsmansName = SecondInningsBatmans.get(j);

					if (batsmansName.contains(")")) {
						continue;
					}

					oldBatsman = BatsmanUtil.getOldBatsman(sessionPlayer,
							batsmansName);

					System.out.println("This is bowlers Name :"
							+ oldBatsman.getName());

					System.out.println();
					BatsmanPlayerProfile batsmanPlayer = new BatsmanPlayerProfile();

					newBatsman = batsmanPlayer.getKeyParamsBatsman(oldBatsman,
							match.getSecondInnings());
					newBatsman.setName(batsmansName);
					newBatsman.setInningsNumber(2);
					newBatsman.setMatchId(matchId);

					try {
						sessionPlayer.beginTransaction();
						sessionPlayer.save(newBatsman);
						sessionPlayer.getTransaction().commit();

					} catch (Exception e) {
						System.out.println("bowlersName: " + bowlersName);
						e.printStackTrace();
					}
				}// for

				// interesting start
				// number Of Matches Played By Bowlers 1stInnings
				int bowlNumOfMatches1 = 0;
				Double bowlSumOfAvgRuns1 = 0.0;
				Double bowlSumOfAvg1 = 0.0;
				Double bowlSumOfStrikeRate1 = 0.0;
				Double bowlSumOfAvgDotBowls1 = 0.0;
				Double bowlDotBowlsToRuns1 = 0.0;
				Double bowlEconomy1 = 0.0;

				int bowlNumOfMatches2 = 0;
				Double bowlSumOfAvgRuns2 = 0.0;
				Double bowlSumOfAvg2 = 0.0;
				Double bowlSumOfStrikeRate2 = 0.0;
				Double bowlSumOfAvgDotBowls2 = 0.0;
				Double bowlDotBowlsToRuns2 = 0.0;
				Double bowlEconomy2 = 0.0;

				for (int i = 0; (i < firstInningsBowlers.size() && i <= 5); i++) {

					newBowler = new Bowler();

					bowlersName = firstInningsBowlers.get(i);

					if (bowlersName.contains(")")) {
						continue;
					}

					oldBowler = BowlersUtil.getOldBowler(sessionPlayer,
							bowlersName);

					System.out.println("This is bowlers Name :"
							+ oldBowler.getName());

					bowlNumOfMatches1 = bowlNumOfMatches1
							+ oldBowler.getTotalNumberOfInnings();
					bowlSumOfAvgRuns1 = bowlSumOfAvgRuns1
							+ oldBowler.getAverageRunsInInnings();
					bowlSumOfAvg1 = bowlSumOfAvg1
							+ oldBowler.getBowlingAverage();
					bowlSumOfStrikeRate1 = bowlSumOfStrikeRate1
							+ oldBowler.getBowlingStrikeRate();
					bowlSumOfAvgDotBowls1 = bowlSumOfAvgDotBowls1
							+ oldBowler.getBowlersDotBowlPresentage();
					bowlDotBowlsToRuns1 = bowlDotBowlsToRuns1
							+ oldBowler.getDotBowlToRunsRatio();

					bowlEconomy1 = bowlEconomy1 + oldBowler.getBowlingEconomy();
				}

				for (int i = 0; (i < secondInningsBowlers.size() && i <= 5); i++) {

					oldBowler = new Bowler();
					bowlersName = secondInningsBowlers.get(i);

					if (bowlersName.contains(")")) {
						continue;
					}

					oldBowler = BowlersUtil.getOldBowler(sessionPlayer,
							bowlersName);

					System.out.println("THis is bowlers Name :"
							+ oldBowler.getName());

					bowlNumOfMatches2 = bowlNumOfMatches2
							+ oldBowler.getTotalNumberOfInnings();
					bowlSumOfAvgRuns2 = bowlSumOfAvgRuns2
							+ oldBowler.getAverageRunsInInnings();
					bowlSumOfAvg2 = bowlSumOfAvg2
							+ oldBowler.getBowlingAverage();
					bowlSumOfStrikeRate2 = bowlSumOfStrikeRate2
							+ oldBowler.getBowlingStrikeRate();
					bowlSumOfAvgDotBowls2 = bowlSumOfAvgDotBowls2
							+ oldBowler.getBowlersDotBowlPresentage();
					bowlDotBowlsToRuns2 = bowlDotBowlsToRuns2
							+ oldBowler.getDotBowlToRunsRatio();

					bowlEconomy2 = bowlEconomy2 + oldBowler.getBowlingEconomy();
				}

				CombineExperianceBowler ceb = new CombineExperianceBowler();

				// these 2 should be more sofisticated
				int numberOfBowlers1 = firstInningsBowlers.size();
				int numberOfBowlers2 = secondInningsBowlers.size();

				int diffMatches = bowlNumOfMatches1 - bowlNumOfMatches2;
				Double diffAvg = (bowlSumOfAvg1 / numberOfBowlers1)
						- (bowlSumOfAvg2 / numberOfBowlers2);
				Double diffStrikeRate = (bowlSumOfStrikeRate1 / numberOfBowlers1)
						- (bowlSumOfStrikeRate2 / numberOfBowlers2);
				Double diffDotPres = (bowlSumOfAvgDotBowls1 / numberOfBowlers1)
						- (bowlSumOfAvgDotBowls2 / numberOfBowlers2);
				Double diffDotRunsRatio = (bowlDotBowlsToRuns1 / numberOfBowlers1)
						- (bowlDotBowlsToRuns2 / numberOfBowlers2);

				Double diffBowlEconomy = (bowlEconomy1 / numberOfBowlers1)
						- (bowlEconomy2 / numberOfBowlers2);

				int win = match.getResult().getWonByFirstBatOrSecondBat();

				ceb.setDffnoOfMatches(diffMatches);
				ceb.setDiffAvgAvgOfBowlers(diffAvg);
				ceb.setDiffAvgStrikeRate(diffStrikeRate);
				ceb.setDiffDotBowlPresentage(diffDotPres);
				ceb.setDiffDotBowlsToRunsRatio(diffDotRunsRatio);
				ceb.setDiffBowlingEconomy(diffBowlEconomy);

				ceb.setWin(win);

				try {
					sessionPlayer.beginTransaction();
					sessionPlayer.save(ceb);
					sessionPlayer.getTransaction().commit();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					// sessionPlayer.close();
					System.out.println(allBowlersList.toString());
					System.out.println(allMatches.size());
				}
				// Experience Based Bowler

				int batNumOfMatches1 = 0;
				Double batSumOfAvg1 = 0.0;
				Double batSumOfStrikeRate1 = 0.0;
				Double batSumOfAvgDotBowls1 = 0.0;
				Double batDotBowlsToRuns1 = 0.0;
				Double batEconomy1 = 0.0;

				int batNumOfMatches2 = 0;
				Double batSumOfAvg2 = 0.0;
				Double batSumOfStrikeRate2 = 0.0;
				Double batSumOfAvgDotBowls2 = 0.0;
				Double batDotBowlsToRuns2 = 0.0;
				Double batEconomy2 = 0.0;

				for (int i = 0; (i < firstInningsBatmans.size() && i <= 6); i++) {

					newBatsman = new Batsman();

					batsmansName = firstInningsBatmans.get(i);

					if (bowlersName.contains(")")) {
						continue;
					}

					oldBatsman = BatsmanUtil.getOldBatsman(sessionPlayer,
							batsmansName);

					System.out.println("This is bowlers Name :"
							+ oldBatsman.getName());

					batNumOfMatches1 = batNumOfMatches1
							+ oldBatsman.getTotalNumberOfInnings();
					batSumOfAvg1 = batSumOfAvg1
							+ oldBatsman.getbattingAverage();
					batSumOfStrikeRate1 = batSumOfStrikeRate1
							+ oldBatsman.getbattingStrikeRate();
					batSumOfAvgDotBowls1 = batSumOfAvgDotBowls1
							+ oldBatsman.getbatmansDotBowlPresentage();
					batDotBowlsToRuns1 = batDotBowlsToRuns1
							+ oldBatsman.getDotBowlToRunsRatio();

					batEconomy1 = batEconomy1 + oldBatsman.getRunRate();

					System.out.println("**********************************");
					System.out.println("THis is Batsmans Name :"
							+ oldBatsman.getName());

					System.out.println("number Of Matches: "
							+ bowlNumOfMatches1);
					System.out.println("avg: " + bowlSumOfAvg1);
					System.out.println("sRate: " + bowlSumOfStrikeRate1);
					System.out
							.println("dotBowlPress: " + bowlSumOfAvgDotBowls1);
					System.out.println("dotBowlToRuns: " + bowlDotBowlsToRuns1);

					System.out.println("**********************************");
				}

				for (int i = 0; (i < SecondInningsBatmans.size() && i <= 6); i++) {

					oldBatsman = new Batsman();
					batsmansName = SecondInningsBatmans.get(i);

					if (batsmansName.contains(")")) {
						continue;
					}

					oldBatsman = BatsmanUtil.getOldBatsman(sessionPlayer,
							batsmansName);

					batNumOfMatches2 = batNumOfMatches2
							+ oldBatsman.getTotalNumberOfInnings();

					batSumOfAvg2 = batSumOfAvg2
							+ oldBatsman.getbattingAverage();

					batSumOfStrikeRate2 = batSumOfStrikeRate2
							+ oldBatsman.getbattingStrikeRate();

					batSumOfAvgDotBowls2 = batSumOfAvgDotBowls2
							+ oldBatsman.getbatmansDotBowlPresentage();
					batDotBowlsToRuns2 = batDotBowlsToRuns2
							+ oldBatsman.getDotBowlToRunsRatio();

					batEconomy2 = batEconomy2 + oldBatsman.getRunRate();

					System.out.println("**********************************");
					System.out.println("THis is Batsmans Name :"
							+ oldBatsman.getName());

					System.out
							.println("number Of Matches: " + batNumOfMatches2);
					System.out.println("avg: " + batSumOfAvg2);
					System.out.println("sRate: " + batSumOfStrikeRate2);
					System.out.println("dotBowlPress: " + batSumOfAvgDotBowls2);
					System.out.println("dotBowlToRuns" + batDotBowlsToRuns2);

					System.out.println("**********************************");
				}

				CombineExperianceBatsman ceBat = new CombineExperianceBatsman();

				// these 2 should be more sofisticated
				int numberOfBatsmans1 = firstInningsBatmans.size();
				int numberOfBatsmans2 = SecondInningsBatmans.size();

				int diffMatchesBat = batNumOfMatches1 - batNumOfMatches2;
				Double diffAvgBat = (batSumOfAvg1 / numberOfBatsmans1)
						- (batSumOfAvg2 / numberOfBatsmans2);
				Double diffStrikeRateBat = (batSumOfStrikeRate1 / numberOfBatsmans1)
						- (batSumOfStrikeRate2 / numberOfBatsmans2);
				Double diffDotPresBat = (batSumOfAvgDotBowls1 / numberOfBatsmans1)
						- (batSumOfAvgDotBowls2 / numberOfBatsmans2);
				Double diffDotRunsRatioBat = (batDotBowlsToRuns1 / numberOfBatsmans1)
						- (batDotBowlsToRuns2 / numberOfBatsmans2);

				Double diffBattingEconomy = (batEconomy1 / numberOfBatsmans1)
						- (batEconomy2 / numberOfBatsmans2);

				int winBat = match.getResult().getWonByFirstBatOrSecondBat();

				ceBat.setDffnoOfMatches(diffMatchesBat);
				ceBat.setDiffAvgAvgOfBatsman(diffAvgBat);
				ceBat.setDiffAvgStrikeRate(diffStrikeRateBat);
				ceBat.setDiffDotBowlPresentage(diffDotPresBat);
				ceBat.setDiffDotBowlsToRunsRatio(diffDotRunsRatioBat);
				ceBat.setBattingEconomy(diffBattingEconomy);
				
				ceBat.setWin(winBat);

				try {
					sessionPlayer.beginTransaction();
					sessionPlayer.save(ceBat);
					sessionPlayer.getTransaction().commit();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					// sessionPlayer.close();
					System.out.println(allBowlersList.toString());
					System.out.println(allMatches.size());
				}

				CombineExperianceBatsmanAndBowler ceBatBowl = new CombineExperianceBatsmanAndBowler();

				int diffMatchesBat1 = batNumOfMatches1 - bowlNumOfMatches1;

				Double diffAvgBat1 = (batSumOfAvg1 / numberOfBatsmans1)
						- (bowlSumOfAvg1 / numberOfBowlers1);
			
				Double diffStrikeRateBat1 = (batSumOfStrikeRate1 / numberOfBatsmans1)
						- ((bowlEconomy1/6)*100.0) / numberOfBowlers1;
			
				Double diffDotPresBat1 = (batSumOfAvgDotBowls1 / numberOfBatsmans1)
						- (bowlSumOfAvgDotBowls1 / numberOfBowlers1);
				
				Double diffDotRunsRatioBat1 = (batDotBowlsToRuns1 / numberOfBatsmans1)
						- (bowlDotBowlsToRuns1 / numberOfBowlers1);

				int diffMatchesBat2 = batNumOfMatches2 - bowlNumOfMatches2;

				Double diffAvgBat2 = (batSumOfAvg2 / numberOfBatsmans2)
						- (bowlSumOfAvg2 / numberOfBowlers2);
			
				Double diffStrikeRateBat2 = (batSumOfStrikeRate2 / numberOfBatsmans2)
						- ((bowlEconomy2/6)*200.0) / numberOfBowlers2;
			
				Double diffDotPresBat2 = (batSumOfAvgDotBowls2 / numberOfBatsmans2)
						- (bowlSumOfAvgDotBowls2 / numberOfBowlers2);
				
				Double diffDotRunsRatioBat2 = (batDotBowlsToRuns2 / numberOfBatsmans2)
						- (bowlDotBowlsToRuns2 / numberOfBowlers2);

				int winBatBowl = match.getResult()
						.getWonByFirstBatOrSecondBat();

				ceBatBowl.setDffnoOfMatches1(diffMatchesBat1);
				ceBatBowl.setDiffAvgAvgOfBatsmanAndBowler1(diffAvgBat1);
				ceBatBowl.setDiffAvgStrikeRate1(diffStrikeRateBat1);
				ceBatBowl.setDiffDotBowlPresentage1(diffDotPresBat1);
				ceBatBowl.setDiffDotBowlsToRunsRatio1(diffDotRunsRatioBat1);

				ceBatBowl.setDffnoOfMatches2(diffMatchesBat2);
				ceBatBowl.setDiffAvgAvgOfBatsmanAndBowler2(diffAvgBat2);
				ceBatBowl.setDiffAvgStrikeRate2(diffStrikeRateBat2);
				ceBatBowl.setDiffDotBowlPresentage2(diffDotPresBat2);
				ceBatBowl.setDiffDotBowlsToRunsRatio2(diffDotRunsRatioBat2);
				ceBatBowl.setWin(winBatBowl);

				try {
					sessionPlayer.beginTransaction();
					sessionPlayer.save(ceBatBowl);
					sessionPlayer.getTransaction().commit();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					// sessionPlayer.close();
					// System.out.println(allBowlersList.toString());
					// System.out.println(allMatches.size());
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
		session.close();
		sessionFactory.close();
	}
}
