package com.nhathanh.controller;

import java.util.List;

import com.nhathanh.dao.HoaDonDAO;
import com.nhathanh.model.HoaDon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nhathanh.dao.ReportAdminDAO;
import com.nhathanh.model.Report;

@Controller
public class ReportController {
	@Autowired
	ReportAdminDAO report;
	@Autowired
	HoaDonDAO hdDAO;
	@RequestMapping("/admin/report")
	public String report(Model model) {
		List<Report> ds = report.listReport();
		List<HoaDon> hd = hdDAO.findAll();
		model.addAttribute("report", ds);
		model.addAttribute("report2", hd);
		return "pageAdmin/report";
	}


}
