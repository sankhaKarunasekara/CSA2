package com.csa.entity;

import java.util.HashMap;


import java.util.Map;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
public class Innings {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int InningsId;

	@AttributeOverrides({
			@AttributeOverride(name = "teamId", column = @Column(name = "tea")),
			@AttributeOverride(name = "teamName", column = @Column(name = "tem")),
			@AttributeOverride(name = "captain", column = @Column(name = "cap")),
			@AttributeOverride(name = "wicketKeper", column = @Column(name = "wick")) })
	@OneToOne
	private Team battingTeam;

	@OneToOne
	private Team fieldingTeam;

	private int numberOfOvers;

	@ElementCollection
	private Map<Integer, Bowl> deliveries = new HashMap<Integer, Bowl>();

	private int numberOfWickets;

	private int numberOfRunsScored;
	private int numberOfExtras;

	public Innings() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the battingTeam
	 */
	public Team getBattingTeam() {
		return battingTeam;
	}

	/**
	 * @param battingTeam
	 *            the battingTeam to set
	 */
	public void setBattingTeam(Team battingTeam) {
		this.battingTeam = battingTeam;
	}

	/**
	 * @return the fieldingTeam
	 */
	public Team getFieldingTeam() {
		return fieldingTeam;
	}

	/**
	 * @param fieldingTeam
	 *            the fieldingTeam to set
	 */
	public void setFieldingTeam(Team fieldingTeam) {
		this.fieldingTeam = fieldingTeam;
	}

	/**
	 * @return the numberOfOvers
	 */
	public int getNumberOfOvers() {
		return numberOfOvers;
	}

	/**
	 * @param numberOfOvers
	 *            the numberOfOvers to set
	 */
	public void setNumberOfOvers(int numberOfOvers) {
		this.numberOfOvers = numberOfOvers;
	}

	/**
	 * @return the deliveries
	 */
	public Map<Integer, Bowl> getDeliveries() {
		return deliveries;
	}

	/**
	 * @param deliveries
	 *            the deliveries to set
	 */
	public void setDeliveries(Map<Integer, Bowl> deliveries) {
		this.deliveries = deliveries;
	}

	public int getNumberOfWickets() {
		return numberOfWickets;
	}

	public void setNumberOfWickets(int numberOfWickets) {
		this.numberOfWickets = numberOfWickets;
	}

	public int getNumberOfRunsScored() {
		return numberOfRunsScored;
	}

	public void setNumberOfRunsScored(int numberOfRunsScored) {
		this.numberOfRunsScored = numberOfRunsScored;
	}

	public int getNumberOfExtras() {
		return numberOfExtras;
	}

	public void setNumberOfExtras(int numberOfExtras) {
		this.numberOfExtras = numberOfExtras;
	}

}
