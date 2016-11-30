package com.airshiplay.play.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

public class ConvertUtil {

	public static <S, T> S map(T source,
			Converter<? super T, ? extends S> converter) {
		return converter.convert(source);
	}

	public static <S, T> List<S> map(Collection<T> sourceCollection,
			Converter<? super T, ? extends S> converter) {
		ArrayList<S> result = new ArrayList<S>();
		for (T source : sourceCollection) {
			result.add(converter.convert(source));
		}
		return result;
	}

	public static <S, T> List<S> map(Iterable<T> iterable,
			Converter<? super T, ? extends S> converter) {
		ArrayList<S> result = new ArrayList<S>();
		for (Iterator<T> iterator = iterable.iterator(); iterator.hasNext();) {
			T source = iterator.next();
			result.add(converter.convert(source));
		}
		return result;
	}

	public static <S, T> List<S> map(Iterator<T> iterator,
			Converter<? super T, ? extends S> converter) {
		ArrayList<S> result = new ArrayList<S>();
		for (; iterator.hasNext();) {
			T source = iterator.next();
			result.add(converter.convert(source));
		}
		return result;
	}
}
