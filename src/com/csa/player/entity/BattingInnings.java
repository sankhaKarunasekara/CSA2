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
public class BattingInnings {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int battingInningsId;

	@ElementCollection
	Map<Integer, Bowl> bowlsFacedByBatsman = new HashMap<Integer, Bowl>();

	public BattingInnings() {
		// TODO Auto-generated constructor stub
	}

	public int getBattingInningsId() {
		return battingInningsId;
	}

	public void setBattingInningsId(int battingInningsId) {
		this.battingInningsId = battingInningsId;
	}

	public Map<Integer, Bowl> getBowlsFacedByBatsman() {
		return bowlsFacedByBatsman;
	}

	public void setBowlsFacedByBatsman(Map<Integer, Bowl> bowlsFacedByBatsman) {
		this.bowlsFacedByBatsman = bowlsFacedByBatsman;
	}
}
