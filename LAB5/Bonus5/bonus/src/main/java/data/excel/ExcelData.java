package data.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelData {
    Map<String, List<String>> manAbilities = new HashMap<>();

    public ExcelData() {
    }

    public ExcelData(String excelPath) {
        getExcelData(excelPath);
    }

    public void makeAbilitiesMap(String excelPath) {
        getExcelData(excelPath);
    }

    public Map<String, List<String>> getAbilitiesMap() {
        return manAbilities;
    }

    private void addPeopleToAbilities(String manName, String ability) {

      List<String> names = manAbilities.get(ability);

      if(names != null){
          names.add(manName);
      } else {
          names = new ArrayList<>();
          names.add(manName);
          manAbilities.put(ability, names);
      }
    }

    private void getExcelData(String excelPath) {
        try {
            FileInputStream file = new FileInputStream(new File(excelPath));

            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                Cell cell1 = row.getCell(0);
                Cell cell2 = row.getCell(1);
                if (cell1 != null && cell2 != null) {

                    String name = cell1.getStringCellValue();
                    String ability = cell2.getStringCellValue();

                    addPeopleToAbilities(name, ability);
                }
            }

            workbook.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


