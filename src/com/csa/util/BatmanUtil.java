/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author sanjya
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import entity.Bowl;
import entity.Innings;
import entity.MatchDetails;
import player.entity.Batsman;
/**
* @author sanjya
*
*/
public class BatsmanUtil {
public static ArrayList<String> getAllBatmans(Session session) {
session.beginTransaction();
Criteria criteria = session.createCriteria(Bowl.class); //check here 32
criteria.setProjection(Projections.distinct(Projections
.property("bowler")));  //check line 33
ArrayList<String> allbatsmanList = (ArrayList<String>) criteria.list();
session.getTransaction().commit();
return allbatsmanList;
}


public static ArrayList<MatchDetails> getAllMatches(Session session) {
session.beginTransaction();
Criteria criteria = session.createCriteria(MatchDetails.class);
ArrayList<MatchDetails> allMatchDetails = (ArrayList<MatchDetails>) criteria
.list();
session.getTransaction().commit();
return allMatchDetails;
}
public static ArrayList<Bowl> getBatsmansDeliveriesList(Session session,
String batsman) {
session.beginTransaction();
Query query = session.createQuery("from Bowl where bowler = " + "\""
+ bowler + "\"");  //check line 53
ArrayList<Bowl> bowlList = (ArrayList<Bowl>) query.list();
session.getTransaction().commit();
return bowlList;
}


// get experience bowler
public static Batsman getOldBatsman(Session session, String bowler) {
    
//change this function
session.beginTransaction();
// get the bowler if bowler played in previous matches
Query query = session
.createQuery("select max(b.playerId) from Bowler b where b.name = '"
+ bowler + "'");
Batsman oldBatsman = new Batsman();
if (query.list().get(0) != null) {
try {
int maxId = (int) query.list().get(0);
System.out.println("MaxId:"+ maxId);
Criteria criteria = session.createCriteria(Batsman.class).add(
Restrictions.eq("playerId", maxId));
ArrayList<Batsman> list = (ArrayList<Batsman>) criteria.list();
oldBatsman = new Batsman();
session.getTransaction().commit();
if (list.isEmpty()) {
} else {
oldBatsman = list.get(0);
System.out.println("playerId" + oldBatsman.getPlayerId());
System.out.println(oldBatsman.toString());
System.out.println("getName() " + oldBatsman.getName());
System.out.println("NumberOfBowls "
+ oldBatsman.getTotalNumberOfBowls());
System.out.println("NumberOfDotBowls "
+ oldBatsman.getTotalNumberOfDotBowls());
System.out.println("getTotalNumberOfRuns "
+ oldBatsman.getTotalNumberOfRuns());

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



public static ArrayList<String> getBatsmans(Innings innings) {
Map<Integer, Bowl> deliveries = innings.getDeliveries();
ArrayList<String> batsmans = new ArrayList<String>();
Bowl bowl;
String batsmanName;
for (int i = 1; i <= deliveries.size(); i++) {
bowl = deliveries.get(i);
batsmanName = bowl.getBatsman();
batsmans.add(batsmanName);
}
return removeDuplicates(batsmans);
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

