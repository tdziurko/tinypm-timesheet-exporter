/**
 * Copyright (c) 2009 Agilers
 * All rights reserved.
 */
package com.google.code.tinypmclient.internal.binding;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date serializer/deserializer.
 * 
 * Usage: @XmlJavaTypeAdapter(DateAdapter.class)
 * 
 * @author Maria Hotloś (maria.hotlos@agilers.com)
 */
public class DateAdapter extends XmlAdapter<String, Date> {

	private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	private DateFormat dateFormat;
	
	public DateAdapter() {
		this(DEFAULT_DATE_FORMAT);
	}
	
	public DateAdapter(String dateFormat) {
		this.dateFormat = new SimpleDateFormat(dateFormat);
	}
	
	@Override
	public String marshal(Date date) throws Exception {
		return dateFormat.format(date);
	}

	@Override
	public Date unmarshal(String date) throws Exception {
		return dateFormat.parse(date);
	}

}
