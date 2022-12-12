package models;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Inventory {

    private List<Item> inventory;

    public Inventory() {
        inventory = createInventory();
    }

    /**
     * Populates an ArrayList with all items from Inventory.xlsx
     *
     * @return a list consisting of all the items in Inventory.xlsx
     */
    private List<Item> createInventory() {
        List<Item> inventory = new ArrayList<>();
        Workbook wb;
        try {
            wb = WorkbookFactory.create(new File("resources/Inventory.xlsx"));
        } catch (IOException e) {
            //! Would log this with appropriate logging tool, but printing error for now and guard with return
            e.printStackTrace();
            return null;
        }

        // begin iterating through sheets, rows. Only one sheet here, but foreach for future extensibility
        for (Sheet sheet : wb) {
            // skipping index 0 to avoid headings.
            for (int rowNum = 1; rowNum < sheet.getPhysicalNumberOfRows(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row != null) {
                    String name = "";
                    int stock = 0;
                    int capacity = 0;
                    int id = -1;

                    for (int cellNum = 0; cellNum < row.getPhysicalNumberOfCells(); cellNum++) {
                        Cell cell = row.getCell(cellNum, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

                        if (cell != null) {
                            switch (cellNum) {
                                case 0:
                                    name = cell.getStringCellValue();
                                    break;
                                case 1:
                                    stock = (int) cell.getNumericCellValue();
                                    break;
                                case 2:
                                    capacity = (int) cell.getNumericCellValue();
                                    break;
                                case 3:
                                    id = (int) cell.getNumericCellValue();
                                    break;
                                default:
                                    // do nothing
                                    break;
                            }
                        } else {
                            //! Spreadsheet is empty in cell. Should be impossible in given scenario. Log error to be corrected.
                        }
                    }
                    inventory.add(new Item(name, stock, capacity, id));
                }
            }
        }

        return inventory;
    }

    /**
     * Find all items with a stock under a provided percent capacity
     *
     * @param percent the percent threshold to use in determining items under the given capacity requirement
     * @return a list of items that have a stock that are under a percentage of their max capacity
     */
    public List<Item> getItemsUnderPercentCapacity(double percent) {
        List<Item> itemsUnderStocked = new ArrayList<>();
        for (Item item : inventory) {
            double percentCapacity = (double) item.getStock() / item.getCapacity();

            if (percentCapacity < percent) {
                itemsUnderStocked.add(item);
            }
        }

        return itemsUnderStocked;
    }

    // mutators excluded since not required for requested functionality.
}
