package au.com.aia.lifeappautomatedtests.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

import cucumber.api.DataTable;
import net.serenitybdd.core.Serenity;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {

    private static XSSFWorkbook wb;
    private static FileOutputStream output;
    private static InputStream input;
    private static FormulaEvaluator evaluator;
    private static final int  UnitsSheetIndex = 4;
    private static final int  SummarySheetIndex = 1;
    private static final DecimalFormat decimalformat = new DecimalFormat("0.00");
    public static String fileName = "HESTA_Quote_Tool_Draft.xlsx";
    /**
     * opens the spreadsheet template to read and sets the write location for the new file
     * param fileName the filename of the template
     * param folder the lcoation to store the updated file
     * throws Exception
     */
    public static void openSpreadsheetUpdateTo() throws Exception
    {
        // open the spreadsheet
        input = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/calculations/" + fileName);
        wb = new XSSFWorkbook(input);

        File newFile = new File(System.getProperty("user.dir") + "/target/updates");
        if(!newFile.exists())
        {
            newFile.mkdirs();
        }

        File outputFile = new File(System.getProperty("user.dir") + "/target/updates/" + fileName);
        if(outputFile.exists())
        {
            outputFile.delete();
        }
        output = new FileOutputStream(System.getProperty("user.dir") + "/target/updates/" + fileName);
    }

    public static void evaluateWorkbookFormulas(){
        wb.setForceFormulaRecalculation(true);
        XSSFFormulaEvaluator.evaluateAllFormulaCells(wb);
    }

    /**
     * updates a cell with the specified value
     * param sheetName the sheetName to update
     * param rowIndex the row index starting at 0
     * param columnIndex the column index starting at 0
     * param value the value to update
     * throws Exception
     */
    public static void updateCell(String sheetName, int rowIndex, int columnIndex, String value) throws Exception
    {
        XSSFSheet sheet = wb.getSheet(sheetName);
        XSSFCell cell = sheet.getRow(rowIndex).getCell(columnIndex);
        cell.setCellValue(value);
    }

    /**
     * updates a cell with the specified value
     * param sheetName the sheetName to update
     * param rowIndex the row index starting at 0
     * param columnIndex the column index starting at 0
     * param value the value to update
     * throws Exception
     */
    public static void updateCell(String sheetName, int rowIndex, int columnIndex, Date value) throws Exception
    {
        XSSFSheet sheet = wb.getSheet(sheetName);
        XSSFCell cell = sheet.getRow(rowIndex).getCell(columnIndex);
        cell.setCellValue(value);
    }

    /**
     * close the current sheets
     * throws Exception
     */
    public static void closeSpreadsheet() throws Exception
    {
        XSSFFormulaEvaluator.evaluateAllFormulaCells(wb);
        wb.write(output);
        input.close();
        output.close();
        wb.close();
    }

    public static void getValueOfFormulaCell(XSSFCell cell) throws Exception
    {
        evaluator = wb.getCreationHelper().createFormulaEvaluator();

        CellValue cellValue = evaluator.evaluate(cell);
        switch (cellValue.getCellType()) {
            case HSSFCell.CELL_TYPE_BOOLEAN:
                System.out.println(cellValue.getBooleanValue());
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                System.out.println(cellValue.getNumberValue());
                break;
            case HSSFCell.CELL_TYPE_STRING:
                System.out.println(cellValue.getStringValue());
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                break;
            case HSSFCell.CELL_TYPE_ERROR:
                break;
            // CELL_TYPE_FORMULA will never happen
            case HSSFCell.CELL_TYPE_FORMULA:
                break;
        }
    }

    private static String getCellValue(int type, Cell cell) {
        String s;
        BigDecimal big;
        switch (type) {
            case Cell.CELL_TYPE_BOOLEAN:
                s = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_NUMERIC:
                big = new BigDecimal(cell.getNumericCellValue());
                s = String.valueOf(big);
                break;
            case Cell.CELL_TYPE_BLANK:
                s = "";
                break;
            case Cell.CELL_TYPE_ERROR:
                s = "";
                break;
            case Cell.CELL_TYPE_FORMULA:
                s = getCellValue(cell.getCachedFormulaResultType(), cell);

                break;
            case Cell.CELL_TYPE_STRING:
                // s = cell.getStringCellValue().trim();
                s = cell.getStringCellValue();
                break;
            default:
                s = "";
        }
        return s;
    }

    public static void setAgeAndIncome(Date dateOfBirth, double annualIncome) throws Exception{
        XSSFSheet sheet = wb.getSheetAt(SummarySheetIndex);
        sheet.getRow(3).getCell(1).setCellValue(dateOfBirth);
        sheet.getRow(5).getCell(1).setCellValue(annualIncome);
    }

    public static void setOccupation(String Occupation) throws Exception{
        XSSFSheet sheet = wb.getSheetAt(SummarySheetIndex);
        sheet.getRow(6).getCell(1).setCellValue(Occupation);
    }

    public static void setDeathUnitValuesForPremiumCalc() throws Exception{
        XSSFSheet sheet = wb.getSheetAt(SummarySheetIndex);
        sheet.getRow(10).getCell(1).setCellValue("Unit");
        int intDeathUnit = (int)Serenity.getCurrentSession().get("Death Unit Number");
        sheet.getRow(12).getCell(1).setCellValue(intDeathUnit);
    }

    public static void setDeathFixedValuesForPremiumCalc() throws Exception{
        XSSFSheet sheet = wb.getSheetAt(SummarySheetIndex);
        sheet.getRow(10).getCell(1).setCellValue("Fixed");
        String strDeathFixed = (String) Serenity.getCurrentSession().get("Death Fixed");
        sheet.getRow(11).getCell(1).setCellValue(Integer.parseInt(strDeathFixed));
    }

    public static void setTPDUnitValuesForPremiumCalc() throws Exception{
        XSSFSheet sheet = wb.getSheetAt(SummarySheetIndex);
        sheet.getRow(17).getCell(1).setCellValue("Unit");
        int intTPDUnit = (int)Serenity.getCurrentSession().get("TPD Unit Number");
        sheet.getRow(19).getCell(1).setCellValue(intTPDUnit);
    }

    public static void setTPDFixedValuesForPremiumCalc() throws Exception{
        XSSFSheet sheet = wb.getSheetAt(SummarySheetIndex);
        sheet.getRow(17).getCell(1).setCellValue("Fixed");
        String strTPDFixed = (String) Serenity.getCurrentSession().get("TPD Fixed");
        sheet.getRow(18).getCell(1).setCellValue(Integer.parseInt(strTPDFixed));
    }

    public static void setIPUnitValuesForPremiumCalc(String strBenifitPeriod, String strWaitingPeriod) throws Exception{
        XSSFSheet sheet = wb.getSheetAt(SummarySheetIndex);
        int intIPUnit = (int)Serenity.getCurrentSession().get("IP Unit Number");
        sheet.getRow(26).getCell(1).setCellValue(intIPUnit);
        sheet.getRow(27).getCell(1).setCellValue(strBenifitPeriod);
        sheet.getRow(28).getCell(1).setCellValue(strWaitingPeriod);
    }

    public static void readDeathUnits () throws Exception{
        int deathColumn = 1;
        int iRow = 11;
        List<String> listDeathUnits = new ArrayList<String>();
        XSSFSheet sheet = wb.getSheetAt(UnitsSheetIndex);
        while (true){
            Cell cell = sheet.getRow(iRow).getCell(deathColumn);
            if(getCellValue(cell.getCellType(), cell).equals("")){
                break;
            }else{
                listDeathUnits.add(getCellValue(cell.getCellType(), cell));
            }
            iRow++;
        }
        Serenity.getCurrentSession().put("Death Units List",listDeathUnits);
    }

    public static void readTPDUnits () throws Exception{
        int tpdColumn = 2;
        int iRow = 11;
        List<String> listTPDUnits = new ArrayList<String>();
        XSSFSheet sheet = wb.getSheetAt(UnitsSheetIndex);
        while (true){
            Cell cell = sheet.getRow(iRow).getCell(tpdColumn);
            if(getCellValue(cell.getCellType(), cell).equals("")){
                break;
            }else{
                listTPDUnits.add(getCellValue(cell.getCellType(), cell));
            }
            iRow++;
        }
        Serenity.getCurrentSession().put("TPD Units List",listTPDUnits);
    }

    public static void readIPUnits () throws Exception{
        int ipColumn = 3;
        int iRow = 11;
        List<String> listIPUnits = new ArrayList<String>();
        XSSFSheet sheet = wb.getSheetAt(UnitsSheetIndex);
        while (true){
            Cell cell = sheet.getRow(iRow).getCell(ipColumn);
            if(getCellValue(cell.getCellType(), cell).equals("")){
                break;
            }else{
                listIPUnits.add(getCellValue(cell.getCellType(), cell));
            }
            iRow++;
        }
        Serenity.getCurrentSession().put("IP Units List",listIPUnits);
    }

    public static void readDeathPremium() throws Exception{
        XSSFSheet sheet = wb.getSheetAt(SummarySheetIndex);
        Cell cell = sheet.getRow(9).getCell(4);
        String premium = getCellValue(cell.getCellType(), cell);
        double dblPremium = (double) Math.round(Double.parseDouble(premium) * 100) / 100;
        Serenity.getCurrentSession().put("Death Premium", decimalformat.format(dblPremium));
        double dblPremiumMontly = (double) Math.round((dblPremium*52/12) * 100) / 100;
        Serenity.getCurrentSession().put("Death Premium Monthly", decimalformat.format(dblPremiumMontly));
        double dblPremiumYearly = (double) Math.round((dblPremium*52) * 100) / 100;
        Serenity.getCurrentSession().put("Death Premium Yearly", decimalformat.format(dblPremiumYearly));
    }

    public static void readTPDPremium() throws Exception{
        XSSFSheet sheet = wb.getSheetAt(SummarySheetIndex);
        Cell cell = sheet.getRow(16).getCell(4);
        String premium = getCellValue(cell.getCellType(), cell);
        double dblPremium = (double) Math.round(Double.parseDouble(premium) * 100) / 100;
        Serenity.getCurrentSession().put("TPD Premium", decimalformat.format(dblPremium));
        double dblPremiumMontly = (double) Math.round((dblPremium*52/12) * 100) / 100;
        Serenity.getCurrentSession().put("TPD Premium Monthly", decimalformat.format(dblPremiumMontly));
        double dblPremiumYearly = (double) Math.round((dblPremium*52) * 100) / 100;
        Serenity.getCurrentSession().put("TPD Premium Yearly", decimalformat.format(dblPremiumYearly));
    }

    public static void readIPPremium() throws Exception{
        XSSFSheet sheet = wb.getSheetAt(SummarySheetIndex);
        Cell cell = sheet.getRow(24).getCell(4);
        String premium = getCellValue(cell.getCellType(), cell);
        double dblPremium = (double) Math.round(Double.parseDouble(premium) * 100) / 100;
        Serenity.getCurrentSession().put("IP Premium", decimalformat.format(dblPremium));
        double dblPremiumMontly = (double) Math.round((dblPremium*52/12) * 100) / 100;
        Serenity.getCurrentSession().put("IP Premium Monthly", decimalformat.format(dblPremiumMontly));
        double dblPremiumYearly = (double) Math.round((dblPremium*52) * 100) / 100;
        Serenity.getCurrentSession().put("IP Premium Yearly", decimalformat.format(dblPremiumYearly));
    }
}