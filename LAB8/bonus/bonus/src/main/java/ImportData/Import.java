package ImportData;

import Models.Book;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Import {
    String path;
    DataSource dataSource;

    public Import(String path) {
        this.path = path;
    }

    public void importData(DataSource dataSource) {
        this.dataSource = dataSource;

        try {
            Reader reader = new FileReader(path);
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();

            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {

                String[] dataArray = record[2].split(";");
                List<String> dataList = new ArrayList<>();
                dataList.addAll(Arrays.asList(dataArray));
                Book book = new Book(dataSource);
                book.add(record[0], record[1], record[3], String.valueOf(dataList));
            }
            csvReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
