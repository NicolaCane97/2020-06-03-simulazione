package it.polito.tdp.PremierLeague.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.PremierLeague.model.Action;
import it.polito.tdp.PremierLeague.model.Adiacenze;
import it.polito.tdp.PremierLeague.model.Player;

public class PremierLeagueDAO {
	
	public List<Player> listAllPlayers(Map<Integer, Player> idMap){
		String sql = "SELECT * FROM Players";
		List<Player> result = new ArrayList<Player>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Player player = new Player(res.getInt("PlayerID"), res.getString("Name"));
				if(!idMap.containsKey(player.getPlayerID())) {
					idMap.put(player.getPlayerID(), player);
				}
				result.add(player);
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Action> listAllActions(){
		String sql = "SELECT * FROM Actions";
		List<Action> result = new ArrayList<Action>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Action action = new Action(res.getInt("PlayerID"),res.getInt("MatchID"),res.getInt("TeamID"),res.getInt("Starts"),res.getInt("Goals"),
						res.getInt("TimePlayed"),res.getInt("RedCards"),res.getInt("YellowCards"),res.getInt("TotalSuccessfulPassesAll"),res.getInt("totalUnsuccessfulPassesAll"),
						res.getInt("Assists"),res.getInt("TotalFoulsConceded"),res.getInt("Offsides"));
				
				result.add(action);
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Player> getVertici(Map<Integer, Player> idMap, double goal){
		String sql = "SELECT playerID " + 
				"FROM actions " + 
				"GROUP BY playerID " + 
				"HAVING AVG(goals) > ?";
		List<Player> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setDouble(1, goal);
			ResultSet res = st.executeQuery();
			while(res.next()) {
				result.add(idMap.get(res.getInt("playerID")));
			}
			
			conn.close();
			return result;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	public List<Adiacenze> getArchi(Map<Integer, Player> idMap){
		
		String sql = "SELECT a1.PlayerID AS p1, a2.PlayerID AS p2, (SUM(a1.TimePlayed) - SUM(a2.TimePlayed)) AS peso " + 
				"FROM actions a1, actions a2 " + 
				"WHERE a1.PlayerID > a2.PlayerID AND " + 
				"a1.`Starts` = 1 AND a2.`Starts` = 1 " + 
				"AND a1.MatchID = a2.MatchID AND a1.TeamID != a2.TeamID " + 
				"GROUP BY p1, p2";
		
		List<Adiacenze> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while(res.next()) {
				
				result.add(new Adiacenze(idMap.get(res.getInt("p1")), idMap.get(res.getInt("p2")), res.getDouble("peso")));
			}
			
			conn.close();
			return result;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
