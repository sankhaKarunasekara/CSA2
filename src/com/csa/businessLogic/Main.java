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

public class Main {

	public static void main(String[] args) {

		// Result result = new Result();
		// Team team1 = new Team();
		// Team team2 = new Team();

		// Innings innings2 = new Innings();

		// innings1.setBattingTeam(team1);
		// innings1.setFieldingTeam(team2);

		// Wicket wicket = new Wicket();
		// bowl.setWicket(wicket);
		// match.setResult(result);
		// match.setFirstInnings(innings1);
		// match.setSecondInnings(innings2);

		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		SessionFactory sessionFactoryPlayer = new Configuration().configure(
				"hibernatePlayer.cfg.xml").buildSessionFactory();

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

				/***** get blowers in this match ***************************/
				ArrayList<String> secondInningsBowlers = BowlersUtil
						.getBowlers(match.getSecondInnings());

				/*************** get all matches List *********************/
				ArrayList<MatchDetails> allMatches = BowlersUtil
						.getAllMatches(session);

				/************** get Bowlers Details *************************/

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
				try {

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

						System.out.println("THis is bowlers Name :"
								+ oldBowler.getName());

						System.out.println();
						PlayerProfile player = new PlayerProfile();

						newBowler = player.getTotalNumberOfParams(oldBowler,
								match.getFirstInnings());
						newBowler.setName(bowlersName);
						newBowler.setInningsNumber(1);

						// bowlingAverage = player
						// .getBowlingAverage(deliveriesList);
						//
						// bowlingStrikeRate = player
						// .getBowlersStrikeRate(deliveriesList);
						//
						// dotBowlPresentage = player
						// .getDotBowlPresentage(deliveriesList);
						//
						// bowlingEconomy = player
						// .getBowlersEconomy(deliveriesList);
						//
						// //
						// bowler.setNumberOfBowlsBowled(deliveriesList.size());
						// newBowler.setName(bowlersName);
						// bowler.setBowlersDotBowlPresentage(dotBowlPresentage);
						// bowler.setBowlingAverage(bowlingAverage);
						// bowler.setBowlingStrikeRate(bowlingStrikeRate);
						// bowler.setBowlingEconomy(bowlingEconomy);

						try {
							sessionPlayer.beginTransaction();
							sessionPlayer.save(newBowler);
							sessionPlayer.getTransaction().commit();

						} catch (Exception e) {
							System.out.println("bowlersName: " + bowlersName);
							e.printStackTrace();
						}
					}

				} catch (Exception e) {
					e.printStackTrace();

				} finally {
					// System.out.println(allBowlersList.toString());
					// System.out.println(allMatches.size());

				}

				try {

					for (int i = 0; i < secondInningsBowlers.size(); i++) {

						oldBowler = new Bowler();

						bowlersName = secondInningsBowlers.get(i);

						if (bowlersName.contains(")")) {
							continue;
						}
						// deliveriesList =
						// BowlersUtil.getBowlersDeliveriesList(session,
						// bowlersName);

						oldBowler = BowlersUtil.getOldBowler(sessionPlayer,
								bowlersName);

						System.out.println("THis is bowlers Name :"
								+ oldBowler.getName());

						System.out.println();
						PlayerProfile player = new PlayerProfile();

						newBowler = player.getTotalNumberOfParams(oldBowler,
								match.getSecondInnings());
						newBowler.setName(bowlersName);
						newBowler.setInningsNumber(2);
						// bowlingAverage = player
						// .getBowlingAverage(deliveriesList);
						//
						// bowlingStrikeRate = player
						// .getBowlersStrikeRate(deliveriesList);
						//
						// dotBowlPresentage = player
						// .getDotBowlPresentage(deliveriesList);
						//
						// bowlingEconomy = player
						// .getBowlersEconomy(deliveriesList);
						//
						// bowler.setNumberOfBowlsBowled(deliveriesList.size());
						// newBowler.setName(bowlersName);
						// bowler.setBowlersDotBowlPresentage(dotBowlPresentage);
						// bowler.setBowlingAverage(bowlingAverage);
						// bowler.setBowlingStrikeRate(bowlingStrikeRate);
						// bowler.setBowlingEconomy(bowlingEconomy);

						try {
							sessionPlayer.beginTransaction();
							sessionPlayer.save(newBowler);
							sessionPlayer.getTransaction().commit();

						} catch (Exception e) {
							System.out.println("bowlersName: " + bowlersName);
							e.printStackTrace();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();

				} finally {
					sessionPlayer.close();
					System.out.println(allBowlersList.toString());
					System.out.println(allMatches.size());

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