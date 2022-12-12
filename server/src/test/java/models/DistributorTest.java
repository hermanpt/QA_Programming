package models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DistributorTest {

	private Distributor distributor;

	@Before
	public void init() {
		distributor = new Distributor("Distributor1");

	}


	@Test
	public void testAddItemWithOneItemAdded() {
		// Create a new Item
		Item itemToAdd = new Item("item1", 1, 0.97);

		// Call addItem() method to add only one item
		distributor.addItem(itemToAdd);

		// Get list of items added using getItems() method
		List<Item> itemsAdded = distributor.getItems();
		// Get the first item added
		Item itemAdded = itemsAdded.get(0);

		// Test if the item added successfully
		assertEquals(itemAdded, itemToAdd);
	}


	@Test
	public void getCheapestCostWhenItemFound() {

		double expectedCheapestCost = 0.96;

		distributor.addItem(new Item("item1", 1, 0.97));
		distributor.addItem(new Item("item2", 2, expectedCheapestCost));

		double actualCheapestCost = distributor.getCheapestCost("item2");

		assertTrue(actualCheapestCost == expectedCheapestCost);
	}


	@Test
	public void getCheapestCostWhenItemNotFound() {

		double expectedCheapestCost = Double.MAX_VALUE;

		distributor.addItem(new Item("item1", 1, 0.97));
		distributor.addItem(new Item("item2", 2, 0.96));

		double actualCheapestCost = distributor.getCheapestCost("item3");

		assertTrue(actualCheapestCost == expectedCheapestCost);
	}
}