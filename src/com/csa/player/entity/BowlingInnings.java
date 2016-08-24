package com.csa.player.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.csa.entity.Bowl;

@Entity
public class BowlingInnings {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int bowlingInningsId;

	int matchId;
	int inningsId;
	
	public int getMatchId() {
		return matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	public int getInningsId() {
		return inningsId;
	}

	public void setInningsId(int inningsId) {
		this.inningsId = inningsId;
	}

	@ElementCollection
	Map<Integer, Bowl> deliveriesByBowler = new HashMap<Integer, Bowl>();

	
	public int getBowlingInningsId() {
		return bowlingInningsId;
	}

	public void setBowlingInningsId(int bowlingInningsId) {
		this.bowlingInningsId = bowlingInningsId;
	}

	public Map<Integer, Bowl> getDeliveriesByBowler() {
		return deliveriesByBowler;
	}

	public void setDeliveriesByBowler(Map<Integer, Bowl> deliveriesByBowler) {
		this.deliveriesByBowler = deliveriesByBowler;
	}

}
