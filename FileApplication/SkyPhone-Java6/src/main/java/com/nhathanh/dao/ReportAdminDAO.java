package com.nhathanh.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nhathanh.model.Report;

public interface ReportAdminDAO extends JpaRepository<Report, String> {
	@Query(value = "exec proc_report1",nativeQuery=true)
	List<Report> listReport();

}
