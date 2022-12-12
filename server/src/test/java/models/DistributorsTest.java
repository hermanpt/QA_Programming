package models;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class DistributorsTest {

	private Distributors distributors;
	private Gson gson = new Gson();

	@Before
	public void init() {
		distributors = new Distributors();
	}


	@Test
	public void getRestockCost() {

		double expectedRestockCost = 0.0;

		List<Item> items = new ArrayList<Item>();
		items.add(new Item("Licorice", 100, 150, 0.81, 1));
		items.add(new Item("Smarties", 80, 140, 0.89, 2));
		items.add(new Item("Good & Plenty", 20, 50, 0.46, 3));

		double actualRestockCost = distributors.getRestockCost(gson.toJson(items));

		assertTrue(expectedRestockCost == actualRestockCost);
	}

	@Test
	public void getRestockCostWithEmptyArray() {

		double expectedRestockCost = 0.0;

		double actualRestockCost = distributors.getRestockCost(gson.toJson(new ArrayList<Item>()));

		assertTrue(expectedRestockCost == actualRestockCost);
	}
}