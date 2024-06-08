package gn.moria.nounkouke.country.loader;

import gn.moria.nounkouke.country.controller.CountryController;
import gn.moria.nounkouke.country.controller.request.AddCountryRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.FileInputStream;

@Component
@RequiredArgsConstructor
@Slf4j
public class CountryLoader implements ApplicationRunner {
    private final CountryController countryController;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("templates/countries.xlsx").getFile());

        FileInputStream inputStream = new FileInputStream(file);
        Workbook workbook = WorkbookFactory.create(inputStream);
        // Sheet sheet = workbook.getSheetAt(0);
        var sheet= workbook.getSheetAt(0);
        int startRow = 1;
        //int endRow = getFirstEmptyRow(sheet);
        int endRow = 35;
        long diff = endRow-startRow;

        for(int i = startRow ; i < endRow; i++){
            var row = sheet.getRow(i);
            if (row != null){
                var requestBuilder = AddCountryRequest.builder();

               for (var cell: row){
                   int idx = cell.getColumnIndex();
                   switch (idx){
                       case 0:{
                           String value = cell.getStringCellValue();
                           requestBuilder.name(value);
                           break;
                       }
                       case 1:{
                           String value = cell.getStringCellValue();
                           requestBuilder.alias(value);
                           break;
                       }
                       case 2:{
                           int value = (int) cell.getNumericCellValue();

                           requestBuilder.code(value);
                           break;
                       }
                       default:
                           String value = cell.getStringCellValue();
                           log.info("names {}",value);
                   }
               }
               var request = requestBuilder.build();
               var response = countryController.add(request);
            }
        }

    }

    public  int getFirstEmptyRow(Sheet sheet) {
        int firstEmptyRowIndex = -1;
        for (Row row : sheet) {
            if (isRowEmpty(row)) {
                firstEmptyRowIndex = row.getRowNum();
                break;
            }
        }
        return firstEmptyRowIndex;
    }

    public boolean isRowEmpty(Row row) {
        for (Cell cell : row) {
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }
}
