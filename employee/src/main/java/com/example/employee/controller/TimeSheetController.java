package com.example.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.authenticate.ResponseListOfObject;
import com.example.employee.authenticate.ResponseMessage;
import com.example.employee.authenticate.ResponseObject;
import com.example.employee.dto.TimeSheet;
import com.example.employee.service.TimeSheetService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/timeSheet")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
public class TimeSheetController {

	private final TimeSheetService service;
	
	@PostMapping("/add")
	public ResponseEntity<?> addTimeSheet(@RequestBody TimeSheet sheet) {
		boolean isAdded = service.addTimeSheet(sheet);
		if (isAdded == true) {
			return ResponseEntity.ok(new ResponseMessage(false, HttpStatus.OK,"TimeSheet Is Added Successfully"));
		}
		return ResponseEntity.ok(new ResponseMessage(true, HttpStatus.OK,"TimeSheet Is Not Added Successfully"));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateTimeSheet(@RequestBody TimeSheet sheet,@PathVariable long id) {
		boolean isUpdated = service.updateTimeSheet(sheet, id);
		if (isUpdated == true) {
			return ResponseEntity.ok(new ResponseMessage(false, HttpStatus.OK,"TimeSheet Is Updated Successfully"));
		}
		return ResponseEntity.ok(new ResponseMessage(true, HttpStatus.OK,"TimeSheet Is Not Updated Successfully"));
	}
	
	
	@GetMapping("/getAllTimeSheet/{id}")
	public ResponseEntity<?> getAllTimeSheet(@PathVariable long id) {
		List<TimeSheet> allTimeSheetOfSpecificEmployee = service.getAllTimeSheetOfSpecificEmployee(id);
		if (allTimeSheetOfSpecificEmployee != null) {
			return ResponseEntity.ok(new ResponseListOfObject(false,HttpStatus.OK,allTimeSheetOfSpecificEmployee));
		}
		return ResponseEntity.ok(new ResponseMessage(true, HttpStatus.OK,"No TimeSheet is Available for this Employee"));
	}
	
	@GetMapping("/getTimeSheet/{id}")
	public ResponseEntity<?> getTimeSheet(@PathVariable long id) {
		TimeSheet timeSheet = service.getTimeSheet(id);
		System.out.println(timeSheet.getDate());
		if (timeSheet != null) {
			System.out.println(timeSheet.getDate());
			return ResponseEntity.ok(new ResponseObject(false,HttpStatus.OK,timeSheet));
		}
		return ResponseEntity.ok(new ResponseMessage(true, HttpStatus.OK,"No TimeSheet is Available for this Employee"));
	}
	
}
