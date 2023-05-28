package contracts;

public interface IGroupController {
	public String[] listStudents(String filter);
	public void removeStudent(int position);
}
