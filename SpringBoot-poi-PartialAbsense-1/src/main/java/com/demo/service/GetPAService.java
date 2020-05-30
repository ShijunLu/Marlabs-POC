package com.demo.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class GetPAService {
	
	public static final String Attendance_XLSX_FILE_PATH = Paths.get("filestorage") + "/Java April 2020- Session 10 - Attendee Report.xlsx";
	public static final String Contracts_XLSX_FILE_PATH = Paths.get("filestorage") + "/ContactsList.xlsx";
	
	public void ReadAttendance() throws EncryptedDocumentException, IOException {
		System.out.println(Attendance_XLSX_FILE_PATH);
		

		Set<String> firstLastNames_set = getNameList(Attendance_XLSX_FILE_PATH, 30);
		
         System.out.println(firstLastNames_set);
         
//       find the person in Contractlist
		 Workbook workbook2 = WorkbookFactory.create(new File(Contracts_XLSX_FILE_PATH));
		 Sheet Contract_sheet = workbook2.getSheetAt(0);
		 
		 Workbook targetbook = new XSSFWorkbook();
		 Sheet traget_sheet = targetbook.createSheet("Partial Absense list");
		
//		 copy header
		 int target_RowNum = 0;
		 Row row = Contract_sheet.getRow(0);
		 int colNum = row.getPhysicalNumberOfCells();
		 Row headerRow = traget_sheet.createRow(target_RowNum++);
		 
		 for (int i = 0; i < colNum; i++) {
			 Cell cell = headerRow.createCell(i);
			 Cell contractSheetCell = row.getCell(i);
			 cell.setCellValue(contractSheetCell.getStringCellValue());
			 }
		 
		 
         for (int i = 1; i <= Contract_sheet.getLastRowNum();i ++) {
        	 DataFormatter formatter = new DataFormatter();
        	 String flName = formatter.formatCellValue(Contract_sheet.getRow(i).getCell(3)) + formatter.formatCellValue(Contract_sheet.getRow(i).getCell(4));
        	 if (! firstLastNames_set.contains(flName)) continue;
        	 System.out.println(flName);
        	 
        	 Row target_row = traget_sheet.createRow(target_RowNum ++);
        	 Row source_row = Contract_sheet.getRow(i);
    		 for (int j = 0; j < colNum; j++) {
    			 Cell target_cell = target_row.createCell(j);
    			 Cell contractSheetCell = source_row.getCell(j);
    			 
    		        // Set the cell data type
    			 target_cell.setCellType(contractSheetCell.getCellType());
    			 
    			// Set the cell data value
    		     switch (contractSheetCell.getCellType()) {
    		     case BLANK:
    		    	 target_cell.setCellValue(contractSheetCell.getStringCellValue());
    		    	 break;
    		     case NUMERIC:
    		    	 target_cell.setCellValue(contractSheetCell.getNumericCellValue());
    		    	 break;
      		     case STRING:
    		    	 target_cell.setCellValue(contractSheetCell.getStringCellValue());
    		    	 break;
    		    
    		     }
    			 
    			 }
         }
         
     
         
         File f =  new File(Paths.get("filestorage") + "/PartialAbsenteesList.xlsx");
         
         FileOutputStream out = new FileOutputStream(f);
         targetbook.write(out);
         
		 
	}
	
	
	
	
//	Read the Attendance xlsx adn return the name list of students who missing more than 30 mins
	public Set<String> getNameList(String Attendance_XLSX_FILE_PATH, int missing_time) throws EncryptedDocumentException, IOException{
		 Workbook workbook = WorkbookFactory.create(new File(Attendance_XLSX_FILE_PATH));
		 Sheet sheet = workbook.getSheetAt(0);
		 
//		 get the total time of session duration
         Cell durationCell = sheet.getRow(4).getCell(2);  
         int Session_duration = getTimeinMins(durationCell);
         System.out.println("Duration in mins: "+ Session_duration + "mins");
         
//       iter through the attendance xlsx and collect the student first + last name
//		 collect studnets' information who missing more than 30 mins
         
         Set<String> firstLastNames = new HashSet<String>();
         
         for (int i = 8; i <= sheet.getLastRowNum();i ++) {
        	 int TimeinSession = getTimeinMins(sheet.getRow(i).getCell(7));
        	 if (TimeinSession + missing_time >= Session_duration) continue;
        	 
        	 DataFormatter formatter = new DataFormatter();

        	 String fLname = formatter.formatCellValue(sheet.getRow(i).getCell(3)) + formatter.formatCellValue(sheet.getRow(i).getCell(2));
        	 firstLastNames.add(fLname);
         }
         
         return firstLastNames;	
	}
	
	public int getTimeinMins(Cell cell) {
		DataFormatter formatter = new DataFormatter();
        String data= formatter.formatCellValue(cell);
        String hours = "0";
        String mins = "0";
        if(data.split(" ").length >= 4) {
        	hours = data.split(" ")[0];
        	mins = data.split(" ")[2];
        }
        else if(data.split(" ").length == 0) {
        	return 0;
        }
        else if(data.split(" ").length == 2) {
        	if (data.split(" ")[1] == "hours" || data.split(" ")[1] == "hour") {
        		hours = data.split(" ")[0];
        		mins = "0";
        	}
        	else if (data.split(" ")[1] == "minutes" || data.split(" ")[1] == "minute") {
        		hours = "0";
        		mins = data.split(" ")[0];
        	}
        }
 
		int duration = Integer.parseInt(hours) * 60 + Integer.parseInt(mins);
		return duration;
	}
	
	
	
	
	
}
