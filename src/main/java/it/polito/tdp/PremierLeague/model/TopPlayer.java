package it.polito.tdp.PremierLeague.model;

import java.util.List;

public class TopPlayer {
	
	private Player p;
	private List<Avversari> battuti;
	
	
	public TopPlayer(Player p) {
		this.p = p;
	}


	/**
	 * @return the p
	 */
	public Player getP() {
		return p;
	}


	/**
	 * @param p the p to set
	 */
	public void setP(Player p) {
		this.p = p;
	}


	/**
	 * @return the battuti
	 */
	public List<Avversari> getBattuti() {
		return battuti;
	}


	/**
	 * @param battuti the battuti to set
	 */
	public void setBattuti(List<Avversari> battuti) {
		this.battuti = battuti;
	}
	
	
	
	

}
