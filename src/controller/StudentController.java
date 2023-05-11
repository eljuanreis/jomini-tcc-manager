package controller;

import model.Student;
import service.FileService;

public class StudentController {
	private FileService service;
	private final String formatLine = "%s;%s\r\n";
	private String validationErrors;
	
	public StudentController() {
		this.service = new FileService();
	}
	
	private boolean validate(String[] data) {
		return true;
	}

	public void save(String[] data) {
		if (!this.validate(data)) {
			
			return;
		}
		
		String dataToFile = String.format(formatLine, data[0], data[1]);
		System.out.println(dataToFile);
	}
}
