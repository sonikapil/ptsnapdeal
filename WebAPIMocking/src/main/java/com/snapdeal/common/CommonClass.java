/**
 * kapil soni
 */
package com.snapdeal.common;

import static net.jadler.Jadler.onRequest;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.snapdeal.base.exception.SerializationException;
import com.snapdeal.base.exception.SerializationException.SerializationErrorCode;

public class CommonClass {

	@SuppressWarnings("unchecked")
	protected static byte[] doSerialize(Class<? extends Object> classType,Object obj) {
		LinkedBuffer buffer = LinkedBuffer.allocate(4096);
		byte[] data;

		try {
			@SuppressWarnings("rawtypes")
			Schema schema = RuntimeSchema.getSchema(classType);
			data = ProtostuffIOUtil.toByteArray(obj, schema, buffer);
		} finally {
			buffer.clear();
		}
		System.out.println("Return data in String form :: " + data.toString());
		return data;
	}
	
	protected static byte[] doJsonSerailize(Class<? extends Object> classType,Object obj) throws SerializationException {

		ObjectMapper mapper = new ObjectMapper();

		byte[] data = null;
		try {
			data = mapper.writeValueAsBytes(obj);
		} catch (JsonParseException exp) {
			System.out.println("Error serializing json for data " + obj);
			System.out.println("Stack trace " + exp);
			throw new SerializationException(SerializationErrorCode.JSON_SERIALIZATION_EXCEPTION, exp);
		} catch (JsonMappingException exp) {
			System.out.println("Error serializing json for data " + obj);
			System.out.println("Stack trace" + exp);
			throw new SerializationException(SerializationErrorCode.JSON_SERIALIZATION_EXCEPTION, exp);
		} catch (IOException exp) {
			System.out.println("Error serializing json for data " + obj);
			System.out.println("Stack trace" + exp);
			throw new SerializationException(SerializationErrorCode.IO_EXCEPTION, exp);
		}
		return data;
	}

	protected static void checkServerStatus()
	{
		onRequest()
				.havingPathEqualTo("/")
		.respond()
				.withBody(
						"API mocking Server started successfully \n\n "
						+ "Below APIs are mocked \n\n"
						+ "Score System API => getSellerWiseDeliveryInfo");
	}
	
}
