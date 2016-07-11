package com.csa.businessLogic;

import java.util.ArrayList;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;

import com.csa.entity.Bowl;
import com.csa.entity.MatchDetails;
import com.csa.player.entity.Bowler;
import com.csa.player.entity.BowlingInnings;
import com.csa.playerprofile.util.PlayerProfile;

public class GeneratePlayerProfile {

	public static void main(String args[]) {

		// System.out.print(addBlackSlashInfrontOfBrackets("Sandeep Sharma (1)"));

		SessionFactory sessionFactory = new Configuration().configure(
				"hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionFactory.openSession();

		/*************** get all bolwers List ***********************/
		ArrayList<String> allBowlersList = getAllBowlers(session);
		/*********************************************************/

		/*************** get all matches List *********************/
		ArrayList<MatchDetails> allMatches = getAllMatches(session);

		/************** get Bowlers Details *************************/

		SessionFactory sessionFactoryPlayer = new Configuration().configure(
				"hibernatePlayer.cfg.xml").buildSessionFactory();

		Session sessionPlayer = sessionFactoryPlayer.openSession();

		ArrayList<Bowl> deliveriesList;
		String bowlersName;
		Double bowlingAverage;
		Double bowlingStrikeRate;
		Double dotBowlPresentage;
		Double bowlingEconomy;

		Bowler bowler = new Bowler();
		try {

			for (int i = 0; i < allBowlersList.size(); i++) {

				bowler = new Bowler();

				bowlersName = allBowlersList.get(i);

				if (bowlersName.contains(")")) {
					continue;
				}
				deliveriesList = getBowlersDeliveriesList(session, bowlersName);
				PlayerProfile player = new PlayerProfile();

				bowlingAverage = player.getBowlingAverage(deliveriesList);
				bowlingStrikeRate = player.getBowlersStrikeRate(deliveriesList);
				dotBowlPresentage = player.getDotBowlPresentage(deliveriesList);
				bowlingEconomy = player.getBowlersEconomy(deliveriesList);

				// bowler.setNumberOfBowlsBowled(deliveriesList.size());
				bowler.setName(bowlersName);
				bowler.setBowlersDotBowlPresentage(dotBowlPresentage);
				bowler.setBowlingAverage(bowlingAverage);
				bowler.setBowlingStrikeRate(bowlingStrikeRate);
				bowler.setBowlingEconomy(bowlingEconomy);

				try {
					sessionPlayer.beginTransaction();
					sessionPlayer.save(bowler);
					sessionPlayer.getTransaction().commit();

				} catch (Exception e) {
					System.out.println("bowlersName: " + bowlersName);
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		session.close();

		System.out.println(allBowlersList.toString());
		System.out.println(allMatches.size());
	}

	public static ArrayList<String> getAllBowlers(Session session) {
		session.beginTransaction();

		Criteria criteria = session.createCriteria(Bowl.class);
		criteria.setProjection(Projections.distinct(Projections
				.property("bowler")));

		ArrayList<String> allbowlersList = (ArrayList<String>) criteria.list();
		session.getTransaction().commit();

		return allbowlersList;
	}

	public static ArrayList<MatchDetails> getAllMatches(Session session) {
		session.beginTransaction();

		Criteria criteria = session.createCriteria(MatchDetails.class);

		ArrayList<MatchDetails> allMatchDetails = (ArrayList<MatchDetails>) criteria
				.list();
		session.getTransaction().commit();

		return allMatchDetails;
	}

	public static ArrayList<Bowl> getBowlersDeliveriesList(Session session,
			String bowler) {
		session.beginTransaction();

		// need a way to find out the match
		Query query = session.createQuery("from Bowl where bowler = " + "\""
				+ bowler + "\"");
		ArrayList<Bowl> bowlList = (ArrayList<Bowl>) query.list();
		session.getTransaction().commit();
		return bowlList;
	}
}
