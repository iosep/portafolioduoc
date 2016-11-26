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
import CTL.CompetenciaCTL;
import CTL.EvaluacionCTL;
import O.EvaluacionO;
import O.Reporte1O;
import O.Reporte2O;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

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

    public void reporteFinal(int perId, String per, String excelFilePath) throws IOException {
        EvaluacionCTL evCtl = new EvaluacionCTL();
        ArrayList<EvaluacionO> evList = evCtl.findEvaluacionesByPeriodoId(perId);
        Collections.sort(evList, new Comparator<EvaluacionO>() {
            @Override
            public int compare(EvaluacionO e1, EvaluacionO e2) {
                return Integer.compare(e1.getCompId(), e2.getCompId());
            }
        });
        int maxCanCom = 0;
        int auxCanCom = 0;
        int comId = evList.get(0).getCompId();
        for (EvaluacionO e : evList) {
            if (comId != e.getCompId()) {
                if (auxCanCom > maxCanCom) {
                    maxCanCom = auxCanCom;
                }
                auxCanCom = 0;
            }
            auxCanCom++;
            comId = e.getCompId();
        }

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        CellStyle cs = workbook.createCellStyle();
        cs.setBorderLeft(BorderStyle.THIN);
        cs.setBorderRight(BorderStyle.THIN);
        cs.setBorderTop(BorderStyle.THIN);
        cs.setBorderBottom(BorderStyle.THIN);

        comId = evList.get(0).getCompId();
        int sumComp = 0;
        float canComp = 0.0f;
        int rowN = 4;
        Row row = sheet.createRow(rowN);
        int cellN = 0;
        Cell cell = row.createCell(cellN);
        CompetenciaCTL comCtl = new CompetenciaCTL();
        cell.setCellValue(comCtl.getCompetenciaById(evList.get(0).getCompId()).getNombre());
        evList.add(new EvaluacionO("", "", 0, 0, 0, 0, 0, -5));
        for (EvaluacionO e : evList) {
            System.out.println("e.compId: " + e.getCompId());
            if (comId != e.getCompId()) {
                System.out.println("if (compId != e.compId)");
                int promedio = Math.round(sumComp / canComp);
                cell = row.createCell(maxCanCom + 1);
                cell.setCellValue(promedio);
                sumComp = 0;
                canComp = 0.0f;
                if (e.getCompId() != -5) {
                    System.out.println("if if (compId != -5)");
                    row = sheet.createRow(++rowN);
                    cellN = 0;
                    cell = row.createCell(cellN);
                    cell.setCellValue(comCtl.getCompetenciaById(e.getCompId()).getNombre());
                }
            }
            if (e.getCompId() != -5) {
                System.out.println("else if (e.compId != -5)");
                cell = row.createCell(++cellN);
                cell.setCellValue(e.getNota());
                sumComp += e.getNota();
                canComp++;
            }
            comId = e.getCompId();
        }

        for (int i = 3; i <= rowN; i++) {
            if (sheet.getRow(i) != null) {
                row = sheet.getRow(i);
            } else {
                row = sheet.createRow(i);
            }
            for (int j = 0; j <= maxCanCom + 1; j++) {
                if (row.getCell(j) != null) {
                    row.getCell(j).setCellStyle(cs);
                } else {
                    row.createCell(j).setCellStyle(cs);
                }
            }
        }

        this.createHeaderRowRepFinal(sheet, per, maxCanCom);

        try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
            workbook.write(outputStream);
        }
    }

    private void createHeaderRowRepFinal(Sheet sheet, String per, int maxComp) {

        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        CellStyle cellS = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 11);
        cellStyle.setFont(font);
        cellS.setFont(font);
        cellS.setBorderLeft(BorderStyle.THIN);
        cellS.setBorderRight(BorderStyle.THIN);
        cellS.setBorderTop(BorderStyle.THIN);
        cellS.setBorderBottom(BorderStyle.THIN);

        Row row1 = sheet.createRow(0);
        Row row2 = sheet.createRow(1);

        Date now = new Date();
        Cell c0 = row1.createCell(0);
        c0.setCellStyle(cellStyle);
        c0.setCellValue("Fecha: " + Formato.dateToString(now));

        Cell c02 = row2.createCell(0);
        c02.setCellStyle(cellStyle);
        c02.setCellValue("Periodo: " + per);

        Row row = sheet.getRow(3);

        Cell c1 = row.getCell(0);
        c1.setCellStyle(cellS);
        c1.setCellValue("Competencia");

        Cell c2 = row.getCell(1);
        c2.setCellStyle(cellS);
        c2.setCellValue("Resultados Funcionarios");

        Cell c3 = row.getCell(maxComp + 1);
        c3.setCellStyle(cellS);
        c3.setCellValue("Promedio");

        sheet.addMergedRegion(new CellRangeAddress(
                3, //first row (0-based)
                3, //last row  (0-based)
                1, //first column (0-based)
                maxComp //last column  (0-based)
        ));

    }

}
