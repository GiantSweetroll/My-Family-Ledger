package shared.components;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;


import models.Category;

public class ComboBoxRenderer extends DefaultListCellRenderer{
	
	public ComboBoxRenderer(Category category) {
		super();
		this.setOpaque(true);
	}
	
	public Component getListCellRendererComponent(
            JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {	
		
		if (value instanceof Category) {
            value = ((Category)value).getName();
        }
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		return this;
	}
}
