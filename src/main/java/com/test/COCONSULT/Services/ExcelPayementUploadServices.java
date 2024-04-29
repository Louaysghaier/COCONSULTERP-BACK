package com.test.COCONSULT.Services;

import com.test.COCONSULT.DTO.TypePayment;
import com.test.COCONSULT.Entity.Payment;
import com.test.COCONSULT.Entity.Prospect;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ExcelPayementUploadServices {
    public static boolean isValidExcelFile(MultipartFile file) {
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }
    public static List<Payment> getPayementsDataFromExcel(InputStream inputStream) {
        List<Payment> payment = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("payments"); // Assuming sheet name is "prospects"
            int rowIndex = 0;
            for (Row row : sheet) {
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                Payment payments = new Payment(); // Move instantiation inside the loop
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0:
                            payments.setIdPayment((long) cell.getNumericCellValue());
                            break;
                        case 1:
                            payments.setName(cell.getStringCellValue());
                            break;
                        case 2:
                            payments.setPayMetho(TypePayment.valueOf(cell.getStringCellValue()));
                            break;
                        case 3:
                            if (cell.getCellType() == CellType.STRING) {
                                payments.setRevenue(Double.parseDouble(cell.getStringCellValue()));
                            } else if (cell.getCellType() == CellType.NUMERIC) {
                                payments.setRevenue(Double.parseDouble(String.valueOf((long) cell.getNumericCellValue())));
                            } break;
                        case 4:
                            payments.setDatePayement(LocalDate.from(cell.getLocalDateTimeCellValue()));


                        default:
                            break;
                    }
                    cellIndex++;
                }
                payment.add(payments);
                rowIndex++; // Increment rowIndex after processing each row
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return payment;
    }
}
