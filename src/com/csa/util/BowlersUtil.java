package com.csa.util;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.csa.entity.Bowl;
import com.csa.entity.Innings;
import com.csa.entity.MatchDetails;
import com.csa.player.entity.Bowler;

/**
 * @author sankha
 * 
 */
public class BowlersUtil {

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

		Query query = session.createQuery("from Bowl where bowler = " + "\""
				+ bowler + "\"");
		ArrayList<Bowl> bowlList = (ArrayList<Bowl>) query.list();
		session.getTransaction().commit();
		return bowlList;
	}

	// get experience bowler
	public static Bowler getOldBowler(Session session, String bowler) {
		session.beginTransaction();

		// get the bowler if bowler played in previous matches
		Query query = session
				.createQuery("select max(b.playerId) from Bowler b where b.name = '"
						+ bowler + "'");

		Bowler oldBowler = new Bowler();

		if (query.list().get(0) != null) {

			try {
				int maxId = (int) query.list().get(0);
				System.out.println("MaxId:" + maxId);

				Criteria criteria = session.createCriteria(Bowler.class).add(
						Restrictions.eq("playerId", maxId));

				ArrayList<Bowler> list = (ArrayList<Bowler>) criteria.list();

				oldBowler = new Bowler();
				session.getTransaction().commit();

				if (list.isEmpty()) {

				} else {
					oldBowler = list.get(0);
					System.out.println("playerId" + oldBowler.getPlayerId());
					System.out.println(oldBowler.toString());
					System.out.println("getName() " + oldBowler.getName());
					System.out.println("NumberOfBowls "
							+ oldBowler.getTotalNumberOfBowls());
					System.out.println("NumberOfDotBowls "
							+ oldBowler.getTotalNumberOfDotBowls());
					System.out.println("getTotalNumberOfRuns "
							+ oldBowler.getTotalNumberOfRuns());
					System.out.println(" getTotalNumberOfExtras"
							+ oldBowler.getTotalNumberOfExtras());
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			oldBowler = new Bowler();
			oldBowler.setName(bowler);

			session.getTransaction().commit();

		}
		return oldBowler;
	}

	public static Bowler getExBowler(Session session, String bowler, int matchId) {
		session.beginTransaction();

		// get the bowler if bowler played in previous matches
		Query query = session
				.createQuery("select max(b.playerId) from Bowler b where b.name = '"
						+ bowler
						+ "' && b.matchId<'"
						+ matchId
						+ "'");

		Bowler oldBowler = new Bowler();

		if (query.list().get(0) != null) {

			try {
				int maxId = (int) query.list().get(0);
				System.out.println("MaxId:" + maxId);

				Criteria criteria = session.createCriteria(Bowler.class).add(
						Restrictions.eq("playerId", maxId));

				ArrayList<Bowler> list = (ArrayList<Bowler>) criteria.list();

				oldBowler = new Bowler();
				session.getTransaction().commit();

				if (list.isEmpty()) {

				} else {
					oldBowler = list.get(0);
					System.out.println("playerId" + oldBowler.getPlayerId());
					System.out.println(oldBowler.toString());
					System.out.println("getName() " + oldBowler.getName());
					System.out.println("NumberOfBowls "
							+ oldBowler.getTotalNumberOfBowls());
					System.out.println("NumberOfDotBowls "
							+ oldBowler.getTotalNumberOfDotBowls());
					System.out.println("getTotalNumberOfRuns "
							+ oldBowler.getTotalNumberOfRuns());
					System.out.println(" getTotalNumberOfExtras"
							+ oldBowler.getTotalNumberOfExtras());
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			oldBowler = new Bowler();
			oldBowler.setName(bowler);

			session.getTransaction().commit();

		}
		return oldBowler;
	}

	public static ArrayList<String> getBowlers(Innings innings) {
		Map<Integer, Bowl> deliveries = innings.getDeliveries();
		ArrayList<String> bowlers = new ArrayList<String>();

		Bowl bowl;
		String bowlersName;
		for (int i = 1; i <= deliveries.size(); i++) {
			bowl = deliveries.get(i);
			bowlersName = bowl.getBowler();
			bowlers.add(bowlersName);
		}
		return removeDuplicates(bowlers);
	}

	public static ArrayList<String> removeDuplicates(ArrayList<String> list) {

		// Store unique items in result.
		ArrayList<String> result = new ArrayList<>();

		// Record encountered Strings in HashSet.
		HashSet<String> set = new HashSet<>();

		// Loop over argument list.
		for (String item : list) {

			// If String is not in set, add it to the list and the set.
			if (!set.contains(item)) {
				result.add(item);
				set.add(item);
			}
		}
		return result;
	}

	// /**
	// * get all blowers from an Innings
	// *
	// * @param match
	// * @return
	// */
	// public static ArrayList<Bowler> getMatchBowlers(
	// Map<Integer, Bowl> InningsDeliveries) {
	// Bowl bowl;
	//
	// Map<String, Bowler> bowlersDetails = new HashMap<String, Bowler>();
	// Bowler bowler;
	// String bowlersName;
	//
	// int totalNumberOfBowls;
	// int totalNumberOfDotBowls;
	// int totalNumberOfWickets;
	//
	// int numberOfBowlsInPowerPlay;
	// int numberOfBowlsInMiddle;
	// int numberOfBowlsInDeath;
	//
	// int numberOfDotsInPowerPlay;
	// int numberOfDotsInMiddle;
	// int numberOfDotsInDeath;
	//
	// int numberOfWicketsInPowerPlay;
	// int numberOfWicketsInMiddle;
	// int numberOfWicketsInDeath;
	//
	// for (int i = 1; i <= InningsDeliveries.size(); i++) {
	// bowl = InningsDeliveries.get(i);
	// bowlersName = bowl.getBowler();
	//
	// if (bowlersDetails.containsKey(bowlersName)) {
	// bowler = bowlersDetails.get(bowlersName);
	//
	// totalNumberOfBowls = bowler.getTotalNumberOfBowls() + 1;
	//
	// } else {
	//
	// }
	//
	// wicket = bowl.getWicket();
	// if (bowl.isWicket == 1) {
	// session.save(wicket);
	// }
	// }
	//
	// }

}
