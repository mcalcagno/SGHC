package uy.com.sghc.gui.frames.components;


import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;

import org.apache.commons.lang.StringUtils;
 
public class DateLabelFormatter extends AbstractFormatter {
 
	private static final long serialVersionUID = 5272081197665925023L;

	private String datePattern = "dd/MM/yyyy";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);       
     
    @Override
	protected JFormattedTextField getFormattedTextField() {
    	final JFormattedTextField jform = super.getFormattedTextField();
    	jform.setFont(new Font("SansSerif", Font.BOLD, 14));
		return jform;
	}

	@Override
    public Object stringToValue(final String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }
 
    @Override
    public String valueToString(final Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }         
        return StringUtils.EMPTY;
    }   
}