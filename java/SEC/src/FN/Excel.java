/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FN;

/**
 *
 * @author iosep
 */
import O.Reporte1O;
import O.Reporte2O;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HeaderFooter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class Excel {

    public void writeExcelRep1(ArrayList<Reporte1O> rep1, String excelFilePath, String funcRut, String per) throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        this.createHeaderRowRep1(sheet, funcRut, per);

        int rowCount = 4;

        for (Reporte1O r1 : rep1) {
            Row row = sheet.createRow(++rowCount);
            writeRep1(r1, row);
        }

        try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
            workbook.write(outputStream);
        }
    }

    private void writeRep1(Reporte1O r1, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue(r1.getComp());

        cell = row.createCell(1);
        cell.setCellValue(r1.getResult());

        cell = row.createCell(2);
        cell.setCellValue(r1.getNopt());

        cell = row.createCell(3);
        cell.setCellValue(r1.getBrecha());
    }

    private void createHeaderRowRep1(Sheet sheet, String funcRut, String per) {

        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 11);
        cellStyle.setFont(font);

        Row row1 = sheet.createRow(0);
        Row row2 = sheet.createRow(1);
        Row row3 = sheet.createRow(2);

        Date now = new Date();
        Cell c0 = row1.createCell(0);
        c0.setCellStyle(cellStyle);
        c0.setCellValue("Fecha: " + Formato.dateToString(now));

        Cell c01 = row2.createCell(0);
        c01.setCellStyle(cellStyle);
        c01.setCellValue("Funcionario: " + funcRut);

        Cell c02 = row3.createCell(0);
        c02.setCellStyle(cellStyle);
        c02.setCellValue("Periodo: " + per);

        Row row = sheet.createRow(4);

        Cell c1 = row.createCell(0);
        c1.setCellStyle(cellStyle);
        c1.setCellValue("Competencia");

        Cell c2 = row.createCell(1);
        c2.setCellStyle(cellStyle);
        c2.setCellValue("Resultado");

        Cell c3 = row.createCell(2);
        c3.setCellStyle(cellStyle);
        c3.setCellValue("Requerido");

        Cell c4 = row.createCell(3);
        c4.setCellStyle(cellStyle);
        c4.setCellValue("Brecha");

    }

    public void writeExcelRep2(ArrayList<Reporte2O> rep2, String excelFilePath, String funcRut, String per) throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        this.createHeaderRowRep2(sheet, funcRut, per);

        int rowCount = 4;

        for (Reporte2O r2 : rep2) {
            Row row = sheet.createRow(++rowCount);
            writeRep2(r2, row);
        }

        try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
            workbook.write(outputStream);
        }
    }

    private void writeRep2(Reporte2O r2, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue(r2.getComp());

        cell = row.createCell(1);
        cell.setCellValue(r2.getObs());
    }

    private void createHeaderRowRep2(Sheet sheet, String funcRut, String per) {

        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 11);
        cellStyle.setFont(font);

        Row row1 = sheet.createRow(0);
        Row row2 = sheet.createRow(1);
        Row row3 = sheet.createRow(2);

        Date now = new Date();
        Cell c0 = row1.createCell(0);
        c0.setCellStyle(cellStyle);
        c0.setCellValue("Fecha: " + Formato.dateToString(now));

        Cell c01 = row2.createCell(0);
        c01.setCellStyle(cellStyle);
        c01.setCellValue("Funcionario: " + funcRut);

        Cell c02 = row3.createCell(0);
        c02.setCellStyle(cellStyle);
        c02.setCellValue("Periodo: " + per);

        Row row = sheet.createRow(4);

        Cell c1 = row.createCell(0);
        c1.setCellStyle(cellStyle);
        c1.setCellValue("Competencia");

        Cell c2 = row.createCell(1);
        c2.setCellStyle(cellStyle);
        c2.setCellValue("Observaciones");

    }
    
}
