/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.entity;

/**
 *
 * @author sanjya
 */
/*import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity*/
public class Batsman {
//@Id
//@GeneratedValue(strategy = GenerationType.AUTO)
private int playerId;
private int matchId;
private int totalNumberOfInnings;
private int inningsNumber;

private String name;
private int numberOfBowlsFaced;
private Double battingAverage;
private Double battingStrikeRate;
private Double batsmanDotBowlPresentage;



private int totalNumberOfRuns;
private int totalNumberOfBowls;
private int totalNumberOfDotBowls;

private int numberOfBowlsFacedInPowerPlay;
private int numberOfBowlsFacedInMiddle;
private int numberOfBowlsFacedInDeath;
private int numberOfDotsFacedInPowerPlay;
private int numberOfDotsFacedInMiddle;
private int numberOfDotsFaceInDeath;
private int numberOfRunsInPowerPlay;
private int numberOfRunsInMiddle;
private int numberOfRunsInDeath;

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public void setTotalNumberOfInnings(int totalNumberOfInnings) {
        this.totalNumberOfInnings = totalNumberOfInnings;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBattingAverage(Double battingAverage) {
        this.battingAverage = battingAverage;
    }

    public void setBattingStrikeRate(Double battingStrikeRate) {
        this.battingStrikeRate = battingStrikeRate;
    }

    public void setBatsmanDotBowlPresentage(Double batsmanDotBowlPresentage) {
        this.batsmanDotBowlPresentage = batsmanDotBowlPresentage;
    }

    public void setTotalNumberOfRuns(int totalNumberOfRuns) {
        this.totalNumberOfRuns = totalNumberOfRuns;
    }

    public void setTotalNumberOfBowls(int totalNumberOfBowls) {
        this.totalNumberOfBowls = totalNumberOfBowls;
    }

    public void setTotalNumberOfDotBowls(int totalNumberOfDotBowls) {
        this.totalNumberOfDotBowls = totalNumberOfDotBowls;
    }

    public void setNumberOfBowlsFacedInPowerPlay(int numberOfBowlsFacedInPowerPlay) {
        this.numberOfBowlsFacedInPowerPlay = numberOfBowlsFacedInPowerPlay;
    }

    public void setNumberOfBowlsFacedInMiddle(int numberOfBowlsFacedInMiddle) {
        this.numberOfBowlsFacedInMiddle = numberOfBowlsFacedInMiddle;
    }

    public void setNumberOfBowlsFacedInDeath(int numberOfBowlsFacedInDeath) {
        this.numberOfBowlsFacedInDeath = numberOfBowlsFacedInDeath;
    }

    public void setNumberOfDotsFacedInPowerPlay(int numberOfDotsFacedInPowerPlay) {
        this.numberOfDotsFacedInPowerPlay = numberOfDotsFacedInPowerPlay;
    }

    public void setNumberOfDotsFacedInMiddle(int numberOfDotsFacedInMiddle) {
        this.numberOfDotsFacedInMiddle = numberOfDotsFacedInMiddle;
    }

    public void setNumberOfDotsFaceInDeath(int numberOfDotsFaceInDeath) {
        this.numberOfDotsFaceInDeath = numberOfDotsFaceInDeath;
    }

    public void setNumberOfRunsInPowerPlay(int numberOfRunsInPowerPlay) {
        this.numberOfRunsInPowerPlay = numberOfRunsInPowerPlay;
    }

    public void setNumberOfRunsInMiddle(int numberOfRunsInMiddle) {
        this.numberOfRunsInMiddle = numberOfRunsInMiddle;
    }

    public void setNumberOfRunsInDeath(int numberOfRunsInDeath) {
        this.numberOfRunsInDeath = numberOfRunsInDeath;
    }

    public void setNumberOfBowlsFaced(int numberOfBowlsFaced) {
        this.numberOfBowlsFaced = numberOfBowlsFaced;
    }

    public void setInningsNumber(int inningsNumber) {
        this.inningsNumber = inningsNumber;
    }
    
    
    
    public int getInningsNumber() {
        return inningsNumber;
    }
    
    public int getNumberOfBowlsFaced() {
        return numberOfBowlsFaced;
    }

    
    public int getPlayerId() {
        return playerId;
    }

    public int getMatchId() {
        return matchId;
    }

    public int getTotalNumberOfInnings() {
        return totalNumberOfInnings;
    }

    public String getName() {
        return name;
    }

    public Double getBattingAverage() {
        return battingAverage;
    }

    public Double getBattingStrikeRate() {
        return battingStrikeRate;
    }

    public Double getBatsmanDotBowlPresentage() {
        return batsmanDotBowlPresentage;
    }

    public int getTotalNumberOfRuns() {
        return totalNumberOfRuns;
    }

    public int getTotalNumberOfBowls() {
        return totalNumberOfBowls;
    }

    public int getTotalNumberOfDotBowls() {
        return totalNumberOfDotBowls;
    }

    public int getNumberOfBowlsFacedInPowerPlay() {
        return numberOfBowlsFacedInPowerPlay;
    }

    public int getNumberOfBowlsFacedInMiddle() {
        return numberOfBowlsFacedInMiddle;
    }

    public int getNumberOfBowlsFacedInDeath() {
        return numberOfBowlsFacedInDeath;
    }

    public int getNumberOfDotsFacedInPowerPlay() {
        return numberOfDotsFacedInPowerPlay;
    }

    public int getNumberOfDotsFacedInMiddle() {
        return numberOfDotsFacedInMiddle;
    }

    public int getNumberOfDotsFaceInDeath() {
        return numberOfDotsFaceInDeath;
    }

    public int getNumberOfRunsInPowerPlay() {
        return numberOfRunsInPowerPlay;
    }

    public int getNumberOfRunsInMiddle() {
        return numberOfRunsInMiddle;
    }

    public int getNumberOfRunsInDeath() {
        return numberOfRunsInDeath;
    }
    
    
    // public int getNumberOfBowlsBowled() {
// return numberOfBowlsBowled;
// }
//
// public void setNumberOfBowlsBowled(int numberOfBowlsBowled) {
// this.numberOfBowlsBowled = numberOfBowlsBowled;
// }
}
