package models;

import com.google.gson.Gson;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Distributors {

    private List<Distributor> distributors;

    public Distributors() {
        distributors = createDistributors();
    }

    /**
     * Creates the initial list of distributors from Distributors.xlsx utilizing Apache POI
     *
     * @return a list of all distributors
     */
    private List<Distributor> createDistributors() {
        List<Distributor> distributors = new ArrayList<>();
        Workbook wb;
        try {
            wb = WorkbookFactory.create(new File("resources/Distributors.xlsx"));
        } catch (IOException e) {
            //! Would log this with appropriate logging tool, but printing error for now and guard with return
            e.printStackTrace();
            return null;
        }

        // begin iterating through sheets, rows.
        for (Sheet sheet : wb) {
            Distributor distributor = new Distributor(sheet.getSheetName());

            // skipping index 0 to avoid headings.
            for (int rowNum = 1; rowNum < sheet.getPhysicalNumberOfRows(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row != null) {
                    String name = "";
                    int id = -1;
                    double cost = 0;

                    for (int cellNum = 0; cellNum < row.getPhysicalNumberOfCells(); cellNum++) {
                        Cell cell = row.getCell(cellNum, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

                        if (cell != null) {
                            switch (cellNum) {
                                case 0:
                                    name = cell.getStringCellValue();
                                    break;
                                case 1:
                                    id = (int) cell.getNumericCellValue();
                                    break;
                                case 2:
                                    cost = cell.getNumericCellValue();
                                    break;
                                default:
                                    // do nothing
                                    break;
                            }
                        } else {
                            //! Spreadsheet is empty in cell. Should be impossible in given scenario. Log error to be corrected.
                        }
                    }
                    distributor.addItem(new Item(name, id, cost));
                }
            }
            distributors.add(distributor);
        }

        return distributors;
    }

    /**
     * Given a list of items to restock and their stock amounts, calculates lowest restock cost among distributors
     *
     * @param body json string denoting items
     * @return the lowest cost to restock
     */
    public double getRestockCost(String body) {
        Gson gson = new Gson();
        Item[] itemArr = gson.fromJson(body, Item[].class);
        double cost = 0;

        for (Item item : itemArr) {
            // temporarily using cost value from json
            int restockAmount = (int) item.getCost();
            // ensure within bounds.
            restockAmount = Math.min(restockAmount, item.getCapacity() - item.getStock());
            double cheapestCost = Double.MAX_VALUE;

            for (Distributor distributor : distributors) {
                double distributorCost = distributor.getCheapestCost(item.getName());
                if (distributorCost < cheapestCost) {
                    cheapestCost = distributorCost;
                }
            }

            cost += cheapestCost * restockAmount;
        }

        return cost;
    }
}
