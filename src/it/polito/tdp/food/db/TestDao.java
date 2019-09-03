package it.polito.tdp.food.db;

import java.util.HashMap;
import java.util.Map;

import it.polito.tdp.food.model.Food;

public class TestDao {

	public static void main(String[] args) {
		FoodDao dao = new FoodDao();
		
		/*System.out.println("Printing all the condiments...");
		System.out.println(dao.listAllCondiments());
		
		System.out.println("Printing all the foods...");
		System.out.println(dao.listAllFoods());
		
		System.out.println("Printing all the portions...");
		System.out.println(dao.listAllPortions());*/
		
		Map<Integer, Food> mappa= new HashMap<>();
		dao.cibidataporzione(1, mappa);
		System.out.println(mappa.values().toString());
		System.out.println(mappa.values().size());
		System.out.println(dao.listAdiacenti());
		
		
	}

}
