package com.csa.visualization;

public class Partnership {

	int partnershipId;
	
	int partnershipNumber;
	public int getPartnershipNumber() {
		return partnershipNumber;
	}

	public void setPartnershipNumber(int partnershipNumber) {
		this.partnershipNumber = partnershipNumber;
	}

	int matchId;
	int inningsId;
	
	String batsman1;
	String batsman2;

	int partnershipScore;
	int bowlsFaced;
	int numberOfBattingSegment;

	public Partnership() {
		// TODO Auto-generated constructor stub
	}

	public int getPartnershipId() {
		return partnershipId;
	}

	public void setPartnershipId(int partnershipId) {
		this.partnershipId = partnershipId;
	}

	public String getBatsman1() {
		return batsman1;
	}

	public void setBatsman1(String batsman1) {
		this.batsman1 = batsman1;
	}

	public String getBatsman2() {
		return batsman2;
	}

	public void setBatsman2(String batsman2) {
		this.batsman2 = batsman2;
	}

	public int getPartnershipScore() {
		return partnershipScore;
	}

	public void setPartnershipScore(int partnershipScore) {
		this.partnershipScore = partnershipScore;
	}

	public int getBowlsFaced() {
		return bowlsFaced;
	}

	public void setBowlsFaced(int bowlsFaced) {
		this.bowlsFaced = bowlsFaced;
	}

	public int getNumberOfBattingSegment() {
		return numberOfBattingSegment;
	}

	public void setNumberOfBattingSegment(int numberOfBattingSegment) {
		this.numberOfBattingSegment = numberOfBattingSegment;
	}

}
