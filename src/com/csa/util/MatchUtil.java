/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csa.util;

import com.csa.entity.Bowl;

import com.csa.entity.Innings;
import java.io.File;
import com.csa.entity.MatchDetails;
import com.csa.entity.Result;
import com.csa.entity.Team;
import com.csa.entity.Wicket;
import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sankha
 * 
 */
public class MatchUtil {
	/**
	 * @param match
	 */
	public void printMatch(MatchDetails match) {
		System.out.println("match Id: " + match.getMatchId());
		System.out.println("matchDate: " + match.getMatchDate());
		System.out.println("matchDayOrNight: " + match.getDayOrNight());
		System.out.println("idDLMethod: " + match.isDLMethod());
		System.out.println("teamOne: " + match.getTeamOne());
		System.out.println("teamTwo: " + match.getTeamTwo());
		System.out.println("umprie1: " + match.getUmprie1());
		System.out.println("umprie2: " + match.getUmprie2());
	}

	public static MatchDetails getMatchInfoFromFile(File filePath)
			throws FileNotFoundException, YamlException, ParseException {
		MatchDetails match = new MatchDetails();

		YamlReader reader = new YamlReader(new FileReader(filePath));

		Object object = reader.read();
		// System.out.println(object);
		Map map = (Map) object;
		//System.out.println(map.size());

		Map info = (Map) map.get("info");
		//System.out.println(info.size());

		ArrayList<String> teams = (ArrayList<String>) info.get("teams");
		//System.out.println(info.get("teams"));
		match.setTeamOne(teams.get(0));
		match.setTeamTwo(teams.get(1));

		ArrayList<String> umpires = (ArrayList<String>) info.get("umpires");
		//System.out.println(umpires);
		match.setUmprie1(umpires.get(0));
		match.setUmprie2(umpires.get(1));

		Map toss = (Map) info.get("toss");
		//System.out.println((String) toss.get("winner"));
		match.setTossWinningTeam((String) toss.get("winner"));
		match.setTossDecision((String) toss.get("decision"));

		//System.out.println(info.get("city"));
		match.setCity((String) info.get("city"));

		// System.err.println(info.get("dates"));
		ArrayList<String> dates = (ArrayList<String>) info.get("dates");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateInString = dates.get(0);
		Date date = sdf.parse(dateInString);
		match.setMatchDate(date);

		match.setVenue((String) info.get("venue"));

		Map outcome = (Map) info.get("outcome");
		String winner = (String) outcome.get("winner");
		// System.out.println("winner"+outcome.get("winner"));

		Map by = (Map) outcome.get("by");
		String by_which = (String) by.keySet().toArray()[0];
		int by_amount = Integer.parseInt((String) by.get(by_which));
		//System.out.println("keyset" + by_amount);
		// end of extracting basic match info

		HashMap<String, Integer> extraMap = new HashMap<>();
		extraMap.put("byes", 1);
		extraMap.put("legbyes", 2);
		extraMap.put("noballs", 3);
		extraMap.put("wides", 4);

		ArrayList<Map> innings = (ArrayList<Map>) map.get("innings");

		Map inn1 = innings.get(0);
		Map inn1_info = (Map) inn1.get("1st innings");
		//System.out.println(inn1_info.get("team"));

		Map inn2 = innings.get(1);
		Map inn2_info = (Map) inn2.get("2nd innings");
		//System.out.println(inn2_info.get("team"));

		HashMap<Integer, Bowl> inni1_deli = getDeliveriesinfo(inn1_info);
		HashMap<Integer, Bowl> inni2_deli = getDeliveriesinfo(inn2_info);

		Innings firstInnings = new Innings();

		Team battingTeam = new Team();
		String name = (String) inn1_info.get("team");
		battingTeam.setTeamName(name);
		firstInnings.setBattingTeam(battingTeam);

		Team fieldingTeam = new Team();
		name = (String) inn2_info.get("team");
		fieldingTeam.setTeamName(name);
		firstInnings.setFieldingTeam(fieldingTeam);

		firstInnings.setDeliveries(inni1_deli);

		// set the Number of Wickets lost in the innings
		int numOfWicketsLostInFirstInnings = getNumberOfWicketInInnings(inni1_deli);
		firstInnings.setNumberOfWickets(numOfWicketsLostInFirstInnings);

		// set Number of Runs Socred in an Innings
		int numOfRunsInInn1 = getNumberOfRunsScoredInAnInnings(inni1_deli);
		firstInnings.setNumberOfRunsScored(numOfRunsInInn1);

		// set Number of Runs Extras in an Innings
		int numOfExtrasInInn1 = getNumberOfExtrasInAnInnings(inni1_deli);
		firstInnings.setNumberOfExtras(numOfExtrasInInn1);

		int inni1_numberOfOvers = 0; // don't know how to find this
		firstInnings.setNumberOfOvers(inni1_numberOfOvers);

		match.setFirstInnings(firstInnings);

		Innings secondInnings = new Innings();

		secondInnings.setBattingTeam(fieldingTeam);
		secondInnings.setFieldingTeam(battingTeam);
		secondInnings.setDeliveries(inni2_deli);

		// set the Number of Wickets lost in the innings
		int numOfWicketsLostInSecondInnings = getNumberOfWicketInInnings(inni2_deli);
		secondInnings.setNumberOfWickets(numOfWicketsLostInSecondInnings);

		// set Number of Runs Socred in an Innings
		int numOfRunsInInn2 = getNumberOfRunsScoredInAnInnings(inni2_deli);
		secondInnings.setNumberOfRunsScored(numOfRunsInInn2);

		// set Number of Runs Extras in an Innings
		int numOfExtrasInInn2 = getNumberOfExtrasInAnInnings(inni2_deli);
		secondInnings.setNumberOfExtras(numOfExtrasInInn2);

		int numOfOversInSecondInnings = 0; // don't know how to find this
		secondInnings.setNumberOfOvers(numOfOversInSecondInnings);

		match.setSecondInnings(secondInnings);

		Result result = new Result();
		// r.setDLmethod(true); //don't know how to find this
		// r.setMatchId(); //which ID ??
		result.setWinningTeam(winner);
		if (winner.equals(battingTeam.teamName))
			result.setWonByFirstBatOrSecondBat(1);
		else
			result.setWonByFirstBatOrSecondBat(2);

		if (by_which.equals("runs"))
			result.setWonByRuns(by_amount);
		else
			result.setWonByWickets(by_amount);

		match.setResult(result);

		// return match object
		return match;
	}

	public static HashMap<Integer, Bowl> getDeliveriesinfo(Map inn1_info) {

		HashMap<Integer, Bowl> inni1_deli = new HashMap<>();

		ArrayList<Map> deli_list = (ArrayList<Map>) inn1_info.get("deliveries");
		int inni1_wicketNumber = 0; //

		for (int i = 0; i < deli_list.size(); i++) {

			Map deli_map = deli_list.get(i);
			//System.out.println(deli_map);
			String deli_name = (String) deli_map.keySet().toArray()[0];
			int overNumber = Integer.parseInt(deli_name.split("\\.")[0]);

			/**correction- overnumbers Should start with one*/
			overNumber = overNumber+1;
			
			Map delivery = (Map) deli_map.get(deli_name);
			//System.out.println(delivery.get("runs"));

			Map run_map = (Map) delivery.get("runs");
			int extras = Integer.parseInt((String) run_map.get("extras"));
			int total = Integer.parseInt((String) run_map.get("total"));
			int runs = Integer.parseInt((String) run_map.get("batsman"));

			Bowl bowl = new Bowl();
			
			//adding
			bowl.setBatsman((String) delivery.get("batsman"));
			bowl.setNonStriker((String) delivery.get("non_striker"));
			bowl.setBowler((String) delivery.get("bowler"));
			bowl.setOverNumber(overNumber);

			int bowlNumber = i + 1;
			bowl.setBowlnumber(bowlNumber); // what is this ????? 3.2 or 20th
											// ball

			if (delivery.containsKey("extras"))
				bowl.setExtraType(extras); // type int ??? ;for now it's string

			bowl.setRuns(runs);
			bowl.setExtras(extras);
			bowl.setTotalRuns(total);
			// runs and totoal runs ????

			if (delivery.containsKey("wicket")) {

				Map wicket_map = (Map) delivery.get("wicket");
				String player_out = (String) wicket_map.get("player_out");
				@SuppressWarnings("unchecked")
				ArrayList<String> fielders = (ArrayList<String>) wicket_map
						.get("fielders");

				String kind = (String) wicket_map.get("kind");

				inni1_wicketNumber++;
				Wicket wicket = new Wicket();
				wicket.setWicketNumber(inni1_wicketNumber);
				wicket.setBowler((String) delivery.get("bowler"));
				wicket.setBatsman(player_out);
				wicket.setFielder(fielders);
				wicket.setWicketType(kind);

				bowl.setIsWicket(1); // boolean or int;
				bowl.setWicket(wicket);
			}

			inni1_deli.put(bowlNumber, bowl);
		}
		return inni1_deli;
	}

	/**
	 * @param diliveries
	 * @return number of wickets in an Inning
	 */

	public static int getNumberOfWicketInInnings(Map<Integer, Bowl> diliveries) {
		int maxWicketNumber = 0;
		for (int i = 1; i <= diliveries.size(); i++) {
			Bowl bowl = diliveries.get(i);

			if (bowl.getIsWicket() == 1) {
				maxWicketNumber++;
			}
		}
		return maxWicketNumber;
	}

	public static int getNumberOfRunsScoredInAnInnings(
			Map<Integer, Bowl> diliveries) {
		int numberOfRuns = 0;
		for (int i = 1; i <= diliveries.size(); i++) {
			Bowl bowl = diliveries.get(i);
			numberOfRuns = numberOfRuns + bowl.getTotalRuns();
		}
		return numberOfRuns;
	}

	public static int getNumberOfExtrasInAnInnings(Map<Integer, Bowl> diliveries) {
		int numberOfExtras = 0;
		for (int i = 1; i <= diliveries.size(); i++) {
			Bowl bowl = diliveries.get(i);
			numberOfExtras = numberOfExtras + bowl.getExtras();

		}
		return numberOfExtras;
	}

}
