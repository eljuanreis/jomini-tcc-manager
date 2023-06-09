package model;

public class Group {

	private String code;
	private String professor;
	private String area;
	private String tema;
	private String students;
	
	private final String formatLine = "%s;%s;%s;%s;";
	
	public Group(String code, String professor, String area, String tema, String students) {
		this.code = code;
		this.professor = professor;
		this.area = area;
		this.tema = tema;
		this.students = students;
	}

	@Override
	public String toString() {
		StringBuffer dataFile = new StringBuffer();

		String fixedData = String.format(formatLine, code, professor, tema, area);
		
		dataFile.append(fixedData);

		dataFile.append(students + "\r\n");
		
		return dataFile.toString();
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getProfessor() {
		return professor;
	}
	
	public void setProfessor(String professor) {
		this.professor = professor;
	}
	
	public String getArea() {
		return area;
	}
	
	public void setArea(String area) {
		this.area = area;
	}
	
	public String getTema() {
		return tema;
	}
	
	public void setTema(String tema) {
		this.tema = tema;
	}
	
	public String getStudents() {
		return students;
	}
	
	public void setStudents(String students) {
		this.students = students;
	}
	
}
