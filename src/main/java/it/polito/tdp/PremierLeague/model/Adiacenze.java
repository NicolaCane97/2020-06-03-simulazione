package it.polito.tdp.PremierLeague.model;

public class Adiacenze {
	
	private Player p1;
	private Player p2;
	private double peso;
	
	
	public Adiacenze(Player p1, Player p2, double peso) {
		this.p1 = p1;
		this.p2 = p2;
		this.peso = peso;
	}


	/**
	 * @return the p1
	 */
	public Player getP1() {
		return p1;
	}


	/**
	 * @param p1 the p1 to set
	 */
	public void setP1(Player p1) {
		this.p1 = p1;
	}


	/**
	 * @return the p2
	 */
	public Player getP2() {
		return p2;
	}


	/**
	 * @param p2 the p2 to set
	 */
	public void setP2(Player p2) {
		this.p2 = p2;
	}


	/**
	 * @return the peso
	 */
	public double getPeso() {
		return peso;
	}


	/**
	 * @param peso the peso to set
	 */
	public void setPeso(double peso) {
		this.peso = peso;
	}


	@Override
	public String toString() {
		return "Adiacenze [p1=" + p1 + ", p2=" + p2 + ", peso=" + peso + "]";
	}
	
	
	
	
	
	


}
