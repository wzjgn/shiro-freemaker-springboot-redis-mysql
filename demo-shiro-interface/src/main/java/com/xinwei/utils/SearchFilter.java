package com.xinwei.utils;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;

public class SearchFilter {

	public enum Operator {
		NEQ, EQ, LIKE, GT, LT, GTE, LTE, ISNULL, ISNOTNULL
	}

	/** 属性数据类型. */
	public enum PropertyType {
		S(String.class), I(Integer.class), L(Long.class), N(Double.class), D(Date.class), B(Boolean.class);

		private Class<?> clazz;

		private PropertyType(Class<?> clazz) {
			this.clazz = clazz;
		}

		public Class<?> getValue() {
			return clazz;
		}
	}

	public String fieldName;
	public Object value;
	public Operator operator;

	public SearchFilter(String fieldName, Operator operator, Object value) {
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
	}

	/**
	 * searchParams中key的格式为OPERATOR_FIELDNAME
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, SearchFilter> parse(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = Maps.newHashMap();

		for (Entry<String, Object> entry : searchParams.entrySet()) {
			// 过滤掉空值
			String key = entry.getKey();
			Object value = entry.getValue();
			if (StringUtils.isBlank((String) value)) {
				continue;
			}

			// 拆分operator与filedAttribute
			String[] names = StringUtils.split(key, "_");
			if (names.length != 2) {
				throw new IllegalArgumentException(key + " is not a valid search filter name");
			}
			String filedName = names[1];
			String firstPart = names[0];
			String matchTypeCode = StringUtils.substring(firstPart, 0, firstPart.length() - 1);
			String propertyTypeCode = StringUtils.substring(firstPart, firstPart.length() - 1, firstPart.length());
			Operator operator = Operator.valueOf(matchTypeCode);
			Class propertyClass = Enum.valueOf(PropertyType.class, propertyTypeCode).getValue();
			Object obj = ConvertUtils.convertStringToObject(value.toString(), propertyClass);
			// 创建searchFilter
			SearchFilter filter = new SearchFilter(filedName, operator, obj);
			filters.put(key, filter);
		}
		return filters;
	}
}