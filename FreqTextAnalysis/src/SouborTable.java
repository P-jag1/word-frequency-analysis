import javax.swing.table.AbstractTableModel;

public class SouborTable extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private Soubor seznamSlov;
	
	public SouborTable(Soubor seznamSlov) {
		super();
		this.seznamSlov = seznamSlov;
	}
	
	@Override
	public int getColumnCount() {
		return 3;
	}
	
	@Override
	public int getRowCount() {
		return seznamSlov.getSlovoCount();
	}
	
	@Override
		public Class<?> getColumnClass(int columnIndex) {
			switch (columnIndex) {
			case 0:
				return String.class;
			case 1:
				return Integer.class;
			case 2:
				return Double.class;
			default:
				return Object.class; //Nemělo by nastat
			}
		}
	
	@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false; //Vsechny bunky needitovatelne
		}
	
	@Override
		public String getColumnName(int column) {
			switch (column) {
			case 0:
				return "Slovo";
			case 1:
				return "Pocet";
			case 2:
				return "Procentuální zastoupeni";
			default:
				return "?"; //Tohle by nemělo nastat
			}
		}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		System.out.println(rowIndex+ "," + columnIndex);
		Slovo i = seznamSlov.getSlovoByIndex(rowIndex);
		switch (columnIndex) {
		case 0:
			return i.getSlovo();
		case 1:
            return i.getPocetVys();
		case 2:
			return i.getProcentoVys();
		default:
			return "?"; //Tohle by nemělo nastat
		}
	}
	
	@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Slovo i = seznamSlov.getSlovoByIndex(rowIndex);
		switch (columnIndex) {
		case 0:
			i.setSlovo((String)aValue);
			break;
		case 1:
			i.setPocetVys((Integer)aValue);
			break;
		case 2:
			i.setProcentoVys((Double)aValue);
			break;
		default:
		    break;
		}
	}
	
}
