package analyza;

import javax.swing.table.AbstractTableModel;

public class FileModel extends AbstractTableModel {
	
	private FileC list = null;
	
	public FileModel(FileC list){
		this.list = list;
	}
	

	public int getRowCount() {
		return list.size();
	}

	public int getColumnCount() {
		return 3;
	}
	
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0: return "Word";
		case 1: return "Number of occurances";
		case 2: return "Procentage of occurances";
		}
		return "";
	}
	
	public Class getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0: return String.class;
		case 1: return Integer.class;
		case 2: return Integer.class;
		}
		return null;
		
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		WordC s = (WordC)list.get(rowIndex);
		switch (columnIndex){
		case 0: return s.getWord();
		case 1: return new Integer (s.getNumberOfOccur());
		case 2: return new Double(s.getProcentageOfOccur());
		}
		
		return null;
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == 2;
	}
	
	public void setValueAt(Object value, int rowIndex, int columnIndex){
		WordC s = (WordC)list.get(rowIndex);
		switch (columnIndex){
		case 1: s.setNumberOfOccur(((Integer) value).intValue());
		case 2: s.setProcentageOfOccur(((Double) value).intValue());
		break;
		}
	}
	
	public void Refresh() {
		fireTableDataChanged();
	}

}

