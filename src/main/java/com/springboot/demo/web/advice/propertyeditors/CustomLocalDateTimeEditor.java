package com.springboot.demo.web.advice.propertyeditors;

import java.beans.PropertyEditorSupport;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class CustomLocalDateTimeEditor extends PropertyEditorSupport {
	
	private final DateTimeFormatter dateTimeFormatter;
	private final boolean allowEmpty;
	
	public CustomLocalDateTimeEditor(DateTimeFormatter dateTimeFormatter, boolean allowEmpty) {
		super();
		this.dateTimeFormatter = dateTimeFormatter;
		this.allowEmpty = allowEmpty;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.isBlank(text) && !allowEmpty) {
			throw new IllegalArgumentException("illegal arguments: " + text);
		}
		LocalDateTime ldt = null;
		try {
			if (NumberUtils.isCreatable(text)) {
				// 优先支持Long
				long milliseconds = Long.valueOf(text);
				ldt = LocalDateTime.ofInstant(Instant.ofEpochMilli(milliseconds), ZoneId.systemDefault());
			} else if (Objects.nonNull(dateTimeFormatter) && StringUtils.isNotBlank(text)) {
				// 其次支持String
				ldt = LocalDateTime.parse(text, dateTimeFormatter);
			}
			setValue(ldt);
		} catch (Exception e) {
			throw new IllegalArgumentException("Could not parse date: " + e.getMessage(), e);
		}
	}
}
