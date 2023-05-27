package contracts;

public interface IConsultGroupController {
	public String[] loadGroups(String filter, boolean supressWarning);
	public void initListingTable(String[] data);
}
