package contracts;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public interface ISearchGroupController {
	public JTextField getSearchBox();
	public void setSearchBox(JTextField searchBox);
	public DefaultComboBoxModel<String> comboBoxList();
	public void setComboBox(DefaultComboBoxModel<String> comboBoxList);
	public void searchGroup();
}
