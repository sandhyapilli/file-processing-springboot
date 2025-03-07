package com.example.fileProcessor;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.*;
	import org.springframework.web.multipart.MultipartFile;

	import java.io.IOException;
	import java.util.List;

	@RestController
	@RequestMapping("/file")
	public class FileProcessorController {

	    @Autowired
	    private FileProcessorService fileProcessorService;

	    
	    @PostMapping("/process")
	    public ResponseEntity<List<String>> processFile(
	            @RequestParam("file") MultipartFile file,
	            @RequestParam(value = "startRow", defaultValue = "0") int startRow) {
	        try {
	            List<String> processedData = fileProcessorService.processFile(file, startRow);
	            return ResponseEntity.ok(processedData);
	        } catch (IOException e) {
	            return ResponseEntity.badRequest().body(List.of("Error processing file: " + e.getMessage()));
	        }
	    }

	    @PostMapping("/convert-xml")
	    public ResponseEntity<String> convertXmlToJson(@RequestParam("file") MultipartFile file) {
	        try {
	            String jsonOutput = fileProcessorService.convertXmlToJson(file);
	            return ResponseEntity.ok(jsonOutput);
	        } catch (IOException e) {
	            return ResponseEntity.badRequest().body("Error converting XML to JSON: " + e.getMessage());
	        }
	    }
	}


