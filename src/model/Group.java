package model;

public class Group {
	private String professor;
	private String area;
	private String tema;
	private String students;
	
	private final String formatLine = "%s;%s;%s;";
	
	public Group(String professor, String area, String tema, String students) {
		this.professor = professor;
		this.area = area;
		this.tema = tema;
		this.students = students;
	}

	@Override
	public String toString() {
		StringBuffer dataFile = new StringBuffer();

		String fixedData = String.format(formatLine, professor, tema, area);
		
		dataFile.append(fixedData);

		dataFile.append(students + "\r\n");
		
		return dataFile.toString();
	}
	
}
