package models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;
import org.mockito.MockedStatic;

public class InventoryTest {

	private Inventory inventory;


	@Test
	public void testGetItemsUnderPercentCapacityWithPercent1() {

		inventory = new Inventory();

		int expectedStockSize = 16;

		List<Item> itemsUnderStocked = inventory.getItemsUnderPercentCapacity(1);

		assertNotNull(itemsUnderStocked);
		assertEquals(expectedStockSize, itemsUnderStocked.size());
	}


	@Test
	public void testGetItemsUnderPercentCapacityWithPercentLessThan1() {

		inventory = new Inventory();

		int expectedStockSize = 6;

		List<Item> itemsUnderStocked = inventory.getItemsUnderPercentCapacity(0.5);

		assertNotNull(itemsUnderStocked);
		assertEquals(expectedStockSize, itemsUnderStocked.size());
	}


	@Test
	public void testCreateInventory() {

		String name = "itemName1";
		int stock = 100;
		int capacity = 400;
		int id = 1;
		Item expectedItemUnderStocked = new Item(name, stock, capacity, id);
		int expectedItemCountUnderStocked = 1;

		Workbook mockWorkbook = mock(Workbook.class);

		try (MockedStatic<WorkbookFactory> workbookFactoryMocked = mockStatic(WorkbookFactory.class)) {

			workbookFactoryMocked.when(() -> {
				WorkbookFactory.create(new File("resources/Inventory.xlsx"));
			}).thenReturn(mockWorkbook);

			Sheet sheet = mock(Sheet.class);
			when(sheet.getPhysicalNumberOfRows()).thenReturn(2);

			Iterator<Sheet> sheetIterator = mock(Iterator.class);
			when(sheetIterator.hasNext()).thenReturn(true, false);
			when(sheetIterator.next()).thenReturn(sheet);
			when(mockWorkbook.iterator()).thenReturn(sheetIterator);

			Row headerRow = mock(Row.class);
			Row dataRow = mock(Row.class);
			when(sheet.getRow(0)).thenReturn(headerRow);
			when(sheet.getRow(1)).thenReturn(dataRow);

			Iterator<Row> rowIterator = mock(Iterator.class);
			when(rowIterator.hasNext()).thenReturn(true, false);
			when(rowIterator.next()).thenReturn(headerRow);
			when(rowIterator.next()).thenReturn(dataRow);
			when(sheet.iterator()).thenReturn(rowIterator);

			Cell cell0 = mock(Cell.class);
			Cell cell1 = mock(Cell.class);
			Cell cell2 = mock(Cell.class);
			Cell cell3 = mock(Cell.class);
			when(dataRow.getPhysicalNumberOfCells()).thenReturn(4);

			Iterator<Cell> cellIterator = mock(Iterator.class);
			when(cellIterator.hasNext()).thenReturn(true, true, true, false);
			when(cellIterator.next()).thenReturn(cell0);
			when(cellIterator.next()).thenReturn(cell1);
			when(cellIterator.next()).thenReturn(cell2);
			when(cellIterator.next()).thenReturn(cell3);
			when(dataRow.iterator()).thenReturn(cellIterator);

			when(dataRow.getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL)).thenReturn(cell0);
			when(cell0.getStringCellValue()).thenReturn(name);

			when(dataRow.getCell(1, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL)).thenReturn(cell1);
			when(cell1.getNumericCellValue()).thenReturn((double) stock);

			when(dataRow.getCell(2, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL)).thenReturn(cell2);
			when(cell2.getNumericCellValue()).thenReturn((double) capacity);

			when(dataRow.getCell(3, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL)).thenReturn(cell3);
			when(cell3.getNumericCellValue()).thenReturn((double) id);

			inventory = new Inventory();
			List<Item> actualItemsUnderStocked = inventory.getItemsUnderPercentCapacity(1);

			assertTrue(expectedItemCountUnderStocked == actualItemsUnderStocked.size());

			assertEquals(expectedItemUnderStocked.getName(), actualItemsUnderStocked.get(0).getName());

		}

	}

}