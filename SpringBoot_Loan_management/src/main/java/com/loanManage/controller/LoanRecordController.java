package com.loanManage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.loanManage.entity.LoanRecord;
import com.loanManage.service.LoanRecordService;

@Controller
public class LoanRecordController {
	@Autowired
	private LoanRecordService service;
	

	
	@RequestMapping("/")
	public String viewRecordsHomePage(Model model) {
		
		List<LoanRecord> listLoanRecords = service.listAll();
		model.addAttribute("listLoanRecords", listLoanRecords);
		return "index";
	}
	
	@RequestMapping("/new")
	public String startNewLoan(Model model) {
		LoanRecord loanRecord = new LoanRecord();
		model.addAttribute("loanRecord", loanRecord);
		return "new_loan";
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveNewLoan(@ModelAttribute("loanRecord") LoanRecord loanRecord) {
		
		service.save(loanRecord); 
		return "redirect:/index";
	}
	
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditLoanFrom(@PathVariable long id) {
		ModelAndView mav = new ModelAndView("edit_loanRecord");
		
		LoanRecord loanRecord = service.get(id);
		mav.addObject("loanRecord", loanRecord);
		
		return mav;

	}
	@RequestMapping("/delete/{id}")
	public String deleteLoanRecord(@PathVariable long id) {
		service.delete(id);
		return "redirect:/index";

	}
	
	@GetMapping("/403")
	public String error403() {
		return "403";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
}
