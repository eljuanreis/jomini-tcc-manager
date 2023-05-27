package contracts;

import javax.swing.JTable;
import javax.swing.JTextField;

public interface IInsertOrientationController {
	public JTextField getTitleField();
	public void setTitleField(JTextField titleField);
	public JTextField getDescField();
	public void setDescField(JTextField descField);
	public JTable initListingTable();
	public void saveOrientation();
}
