package vista.table;

import javax.swing.table.DefaultTableModel;

/**
 * Created by mpliego on 10/5/15.
 */
public class UnmodifiableTableModel extends DefaultTableModel {

    public UnmodifiableTableModel(Object[][] objects, Object[] objects1) {
        super(objects, objects1);
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }
}
