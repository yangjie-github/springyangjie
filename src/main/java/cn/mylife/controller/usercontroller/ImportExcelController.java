package cn.mylife.controller.usercontroller;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author yangjie
 * 2018/7/12 20:54
 */
@Controller
@RequestMapping(value = "excel")
public class ImportExcelController {

    @RequestMapping(value = "importExcel")
    @ResponseBody
    public String importExcel(@RequestParam(value = "file") MultipartFile file) throws IOException {

        InputStream is = file.getInputStream();

        try {
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);

             for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {

                 XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);

                 if (xssfSheet == null) {
                     continue;
                 }

                 for (int numRow = 1; numRow <= xssfSheet.getLastRowNum(); numRow++) {

                     XSSFRow sheetRow = xssfSheet.getRow(numRow);

                     if (sheetRow != null) {

                         XSSFCell name = sheetRow.getCell(0);

                         System.out.println(String.valueOf(name.getStringCellValue()));
                     }

                 }

             }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
