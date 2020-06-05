package com.tink.rest.tools;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import com.squareup.moshi.adapters.EnumJsonAdapter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

/**
 * Provides EnumJsonAdapters which fallback for UNKNOWN or OTHER. If those values are not present in
 * the enum, no adapter will be created
 */
class TinkEnumJsonAdapterFactory implements JsonAdapter.Factory {

	@Override
	public JsonAdapter<?> create(Type type, Set<? extends Annotation> annotations, Moshi moshi) {

		Class<?> rawType = Types.getRawType(type);

		if (rawType.isEnum()) {

			Class<? extends Enum> enumClass = (Class<? extends Enum>) rawType;

			Enum<?>[] values = enumClass.getEnumConstants();

			Enum<?> fallback = null;
			for (Enum<?> e : values) {
				if(e.name().equals("UNKNOWN") || e.name().equals("OTHER")) {
					fallback = e;
					break;
				}
			}

			if (fallback != null) {
				//noinspection unchecked
				return EnumJsonAdapter.create(enumClass).withUnknownFallback(fallback);
			}
		}
		return null;
	}
}
