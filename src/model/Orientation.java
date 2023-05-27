package model;

public class Orientation {

	private String code;
	private String title;
	private String description;
	private Boolean isDone; // Status

	private final String formatLine = "%s;%s;%s;%s;";

	public Orientation(String code, String title, String description, Boolean isDone) {
		this.code = code;
		this.title = title;
		this.description = description;
		this.isDone = isDone;
	}

	@Override
	public String toString() {
		StringBuffer dataFile = new StringBuffer();

		String fixedData = String.format(formatLine, code, title, description, isDone);

		dataFile.append(fixedData);

		dataFile.append("\r\n");

		return dataFile.toString();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsDone() {
		return isDone;
	}

	public void setIsDone(Boolean isDone) {
		this.isDone = isDone;
	}

}
