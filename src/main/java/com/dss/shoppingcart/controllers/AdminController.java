package com.dss.shoppingcart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dss.shoppingcart.services.DatabaseExportService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private DatabaseExportService databaseExportService;

    @GetMapping("/export")
    public ResponseEntity<String> exportDatabase() {
        String sqlScript = databaseExportService.exportDatabaseToSql();

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=database_export.sql");
        
        return new ResponseEntity<>(sqlScript, headers, HttpStatus.OK);
    }
}
