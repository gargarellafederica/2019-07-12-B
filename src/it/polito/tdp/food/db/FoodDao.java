package it.polito.tdp.food.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.food.model.Adiacenti;
import it.polito.tdp.food.model.Condiment;
import it.polito.tdp.food.model.Food;
import it.polito.tdp.food.model.Portion;

public class FoodDao {
	public List<Food> listAllFoods(){
		String sql = "SELECT * FROM food" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Food> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Food(res.getInt("food_code"),
							res.getString("display_name")
							));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}

	}
	
	public List<Condiment> listAllCondiments(){
		String sql = "SELECT * FROM condiment" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Condiment> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Condiment(res.getInt("condiment_code"),
							res.getString("display_name"),
							res.getDouble("condiment_calories"), 
							res.getDouble("condiment_saturated_fats")
							));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Portion> listAllPortions(){
		String sql = "SELECT * FROM portion" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Portion> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Portion(res.getInt("portion_id"),
							res.getDouble("portion_amount"),
							res.getString("portion_display_name"), 
							res.getDouble("calories"),
							res.getDouble("saturated_fats"),
							res.getInt("food_code")
							));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}

	}

	public List<Food> cibidataporzione(int porz, Map<Integer, Food> mappacibi) {
		String sql = "SELECT f.food_code, f.display_name " + 
				"FROM food f , portion p " + 
				"WHERE p.food_code=f.food_code " + 
				"GROUP BY f.food_code, f.display_name " + 
				"HAVING COUNT(p.portion_id) <= ? " ;
		try {
			Connection conn = DBConnect.getConnection() ;

			List<Food> lista= new ArrayList<>();
			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, porz);
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					if(mappacibi.get(res.getInt("food_code"))==null) {
						Food cibo= new Food(res.getInt("food_code"),
								res.getString("display_name"));
						mappacibi.put(cibo.getFood_code(), cibo);
						lista.add(cibo);
					}
					else {
						lista.add(mappacibi.get(res.getInt("food_code")));
					}
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return lista;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public List<Adiacenti> listAdiacenti(){
		String sql = "SELECT DISTINCT f1.food_code AS cibo1, f2.food_code AS cibo2, (AVG(p1.saturated_fats)-(AVG(p2.saturated_fats))) AS media " + 
				"FROM food f1, food f2, portion p1, portion p2 " + 
				"WHERE f1.food_code<> f2.food_code " + 
				"AND f1.food_code=p1.food_code AND f2.food_code=p2.food_code " + 
				"GROUP BY f1.food_code, f2.food_code " + 
				"HAVING media>0 " ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Adiacenti> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Adiacenti(res.getInt("cibo1"),
							res.getInt("cibo2"),
							res.getDouble("media")));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}
	}
}
