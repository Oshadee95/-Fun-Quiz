package com.funquiz.common.peripherals;

import javax.swing.JTable;

/**
 * 
 * @author Oshadee Amarasinghe
 *
 */
@SuppressWarnings("serial")
public class ContentTable extends JTable {

	/**
	 * This is to override the default cell editable method on a Jtable to return false
	 */
	public boolean isCellEditable(int row, int col) {
        return false;
    }
}
