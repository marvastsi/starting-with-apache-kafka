package com.marvastsi.salesprocessing.deserializer;

import java.io.IOException;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marvastsi.salesprocessing.model.Sale;

public class SaleDeserializer implements Deserializer<Sale> {

	@Override
	public Sale deserialize(String topic, byte[] sale) {
		try {
			return new ObjectMapper().readValue(sale, Sale.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
