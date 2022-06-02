package Lib;
import java.io.File;
import java.io.IOException;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
/**
 *
 * @author MSI
 */
public class Help {
    // create and write new file *.xls
    public void writeFileExcel(String table, String[][] data) {
        WritableWorkbook workbook;
        String fileName = "./"  + table + ".xls";
        // create workbook
        try {
            workbook = Workbook.createWorkbook(new File(fileName));
 
            // create sheet
            WritableSheet sheet1 = workbook.createSheet(table, 0);
 
            // create Label and add to sheet
            sheet1.addCell(new Label(0, 0, table));
 
            // row begin write data
            int rowBegin = 2;
            int colBegin = 0;
 
            for (int row = rowBegin, i = 0; row < data.length + rowBegin; row++, i++) {
                for (int col = colBegin, j = 0; col < data[0].length + colBegin; col++, j++) {
                    Object obj = data[i][j];
                    sheet1.addCell(new Label(col, row, (String) data[i][j]));
                }
            }
            // write file
            workbook.write();
 
            // close
            workbook.close();
        } catch (IOException e) {
            System.out.println("Error create file\n" + e.toString());
        } catch (RowsExceededException e) {
            System.out.println("Error write file\n" + e.toString());
        } catch (WriteException e) {
            System.out.println("Error write file\n" + e.toString());
        }
        System.out.println("create and write success");
    }
 
    // open and read file *.xls
    public void readFileExcel() {
        Workbook workbook;
        try {
            // create workbook to open file
            workbook = Workbook.getWorkbook(new File("NHANVIEN.ods"));
            // get sheet want read
            Sheet sheet = workbook.getSheet(0);
            // get number row and col contain data
            int rows = sheet.getRows();
            int cols = sheet.getColumns();
 
            System.out.println("Data in file:");
            // read data in each cell
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    Cell cell = sheet.getCell(col, row);
                    System.out.print(cell.getContents() + "\t");
                }
                System.out.println("\n");
            }
            // close
            workbook.close();
        } catch (BiffException e) {
            System.out.println("File not found\n" + e.toString());
        } catch (IOException e) {
            System.out.println("File not found\n" + e.toString());
        }
    }
}