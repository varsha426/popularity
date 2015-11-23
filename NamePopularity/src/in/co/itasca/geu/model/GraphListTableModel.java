/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.co.itasca.geu.model;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author student
 */
public class GraphListTableModel extends  AbstractTableModel{
    
      
    private Map<Point, Object> lookup;

  private final int rows;

  private final int columns;

  private final String headers[];

  public GraphListTableModel(int rows, String columnHeaders[]) {

    this.rows = rows;
    this.columns = columnHeaders.length;
    headers = columnHeaders;
    lookup = new HashMap<Point, Object>();
  }

  public int getColumnCount() {
    return columns;
  }

  public int getRowCount() {
    return rows;
  }

  public String getColumnName(int column) {
    return headers[column];
  }

  public Object getValueAt(int row, int column) {
    return lookup.get(new Point(row, column));
  }

  public void setValueAt(Object value, int row, int column) {
    if ((rows < 0) || (columns < 0)) {
      throw new IllegalArgumentException("Invalid row/column setting");
    }
    if ((row < rows) && (column < columns)) {
      lookup.put(new Point(row, column), value);
    }
  }


     

    

}
