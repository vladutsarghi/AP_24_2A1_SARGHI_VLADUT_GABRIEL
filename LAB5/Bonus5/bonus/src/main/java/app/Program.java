package app;

import data.excel.ExcelData;
import data.excel.ExcelReader;
import graph.algorithm.Algorithm;

public class Program {
    public Program(){
        run();
    }

    private void run(){
        ExcelData excelData = new ExcelData("C:\\Users\\sargh\\Desktop\\AP_24_2A1_SARGHI_VLADUT_GABRIEL\\LAB5\\Bonus5\\bonus\\Registru 6.xlsx");

        Algorithm algorithm = new Algorithm(excelData.getAbilitiesMap());
    }
}
