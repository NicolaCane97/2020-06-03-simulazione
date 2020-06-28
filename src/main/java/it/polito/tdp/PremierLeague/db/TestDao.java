package it.polito.tdp.PremierLeague.db;

import java.util.HashMap;

public class TestDao {
	

	public static void main(String[] args) {
		TestDao testDao = new TestDao();
		testDao.run();
	}
	
	public void run() {
		PremierLeagueDAO dao = new PremierLeagueDAO();
		System.out.println("Players:");
		//System.out.println(dao.listAllPlayers());
		System.out.println("Actions:");
		System.out.println(dao.listAllActions());
	}

}
