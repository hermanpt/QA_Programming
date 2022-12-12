package models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ItemTest {

	private Item item;


	@Test
	public void getName() {

		String expectedItemName = "name1";

		item = new Item(expectedItemName, 1, 100);

		assertEquals(expectedItemName, item.getName());
	}


	@Test
	public void getStock() {

		int expectedItemStock = 10;

		item = new Item("name1", 10, 20, 100, 1);

		assertEquals(expectedItemStock, item.getStock());
	}

	@Test
	public void getCapacity() {

		int expectedItemCapacity = 20;

		item = new Item("name1", 10, 20, 100, 1);

		assertEquals(expectedItemCapacity, item.getCapacity());
	}


	@Test
	public void getCost() {

		int expectedItemCost = 100;

		item = new Item("name1", 10, 20, 100, 1);

		assertTrue(expectedItemCost == item.getCost());
	}
}