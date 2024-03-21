package com.donationmanagementsystem.service;

import com.donationmanagementsystem.entity.ReportDTO;
import java.util.List;


public interface ReportService {
  ReportDTO saveReport(ReportDTO reportDTO);
    ReportDTO getReportById(int id);
    List<ReportDTO> getAllReports();
    void deleteReportById(int id);
}
