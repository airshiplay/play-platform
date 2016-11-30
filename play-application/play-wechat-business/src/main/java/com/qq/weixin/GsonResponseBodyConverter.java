package com.qq.weixin;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

public class GsonResponseBodyConverter <T> implements Converter<ResponseBody, T> {
	  private final Gson gson;
	  private final TypeAdapter<T> adapter;

	  GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
	    this.gson = gson;
	    this.adapter = adapter;
	  }

	  @Override public T convert(ResponseBody value) throws IOException {
		String a=  value.string();
	    JsonReader jsonReader = gson.newJsonReader(value.charStream());
	    try {
	      return adapter.fromJson(a);
	    } finally {
	      value.close();
	    }
	  }
	}
