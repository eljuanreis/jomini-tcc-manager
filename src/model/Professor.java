package model;

public class Professor {
	private String name;
	private String areas;
	private final String formatLine = "%s;%s\r\n";

	public Professor(String name, String areas) {
		this.name = name;
		this.areas = areas;
	}
	
	@Override
	public String toString() {
		return String.format(formatLine, this.name, this.areas);
	}
}
