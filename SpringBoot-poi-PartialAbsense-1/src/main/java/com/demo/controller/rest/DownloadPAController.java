package com.demo.controller.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.EncryptedDocumentException;
import org.springframework.beans.factory.annotation.Autowired;
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
	public void download_partialAbsence() throws Exception, IOException{
		getPAService.ReadAttendance();
	
		
	}
	

}
