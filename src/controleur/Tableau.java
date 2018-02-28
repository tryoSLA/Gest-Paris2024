package controleur;
import javax.swing.table.AbstractTableModel;

public class Tableau extends AbstractTableModel 
{
	private Object[][] donnees;
	private final String[] entetes;
	
	public Tableau (Object[][] donnees, String[]entetes)
	{
		super();
		this.donnees = donnees;
		this.entetes = entetes;
	}
	
	public int getRowCount()
	{
		return donnees.length;
	}
	
	public int getColumnCount()
	{
		return entetes.length;
	}

	
	public String getColumnName (int columnIndex)
	{
		return entetes[columnIndex];
	}
	
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		System.out.println("case : " +rowIndex+ "   " +columnIndex);
		return donnees[rowIndex][columnIndex];

	}
	
	public void add(Object[] data)
	{
		Object [][] newDonnees = new Object [this.donnees.length+1][];
		int i;
		for (i=0; i<this.donnees.length; i++)
		{
			newDonnees[i] = this.donnees[i];	
		}
		newDonnees[this.donnees.length]=data;
		this.donnees=newDonnees;
		
		this.fireTableDataChanged();
	}
	
	public void remove(int rowIndex)
	{
		Object[][] newdonnees = new Object[this.donnees.length-1][];
		int j=0;
		for (int i=0; i<this.donnees.length; i++)
		{
			if (i!= rowIndex)
				newdonnees[j++] = this.donnees[i];
		}
		this.donnees = newdonnees;
		this.fireTableDataChanged();
	}
	
	public void update(int rowIndex, Object data[]) {
        Object[][] newDonnees = new Object[this.donnees.length][];
        for (int i=0; i<this.donnees.length; i++) {
            if (i != rowIndex) {
                newDonnees[i] = this.donnees[i];
            } else {
                newDonnees[i] = data;
            }
            this.donnees = newDonnees;
            this.fireTableDataChanged();
        }
    }
}

