package com.demo.controller.rest;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.service.GetPAService;

@RestController
public class DownloadPAController {
	
	@Autowired
	private GetPAService getPAService;
	
	public DownloadPAController(GetPAService getPAService){
		this.getPAService = getPAService;
	}

	@GetMapping("/downland/partialAbsence.xlsx")
	public ResponseEntity<Resource> download_partialAbsence() throws Exception, IOException{
		Resource file = getPAService.ReadAttendance();
		
		ResponseEntity<Resource> temp = ResponseEntity.ok()
	          .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +file.getFilename() + "\"")
	          .body(file);  
		
	    return temp;
	}
	

}
