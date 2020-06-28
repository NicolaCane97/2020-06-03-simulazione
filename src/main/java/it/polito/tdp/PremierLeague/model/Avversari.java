package it.polito.tdp.PremierLeague.model;

public class Avversari implements Comparable<Avversari> {

	private Player p;
	private Double peso;
	
	public Avversari(Player p, Double peso) {
		this.p = p;
		this.peso = peso;
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
	 * @return the peso
	 */
	public Double getPeso() {
		return peso;
	}

	/**
	 * @param peso the peso to set
	 */
	public void setPeso(Double peso) {
		this.peso = peso;
	}



	@Override
	public int compareTo(Avversari o) {
	 return o.getPeso().compareTo(this.getPeso());
	}

	@Override
	public String toString() {
		return  p + " "+ peso;
	}
	
	
	
	
}
