package com.marvastsi.salesprocessing.service;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import com.marvastsi.salesprocessing.deserializer.SaleDeserializer;
import com.marvastsi.salesprocessing.model.Sale;

public class SalesProcessor {

	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, SaleDeserializer.class.getName());
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, "group1");
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "10");

		try (KafkaConsumer<String, Sale> consumer = new KafkaConsumer<>(properties)) {
			consumer.subscribe(Arrays.asList("sales"));

			while (true) {
				ConsumerRecords<String, Sale> records = consumer.poll(Duration.ofMillis(200));

				records.forEach(r -> {
					Sale sale = r.value();
					if (new Random().nextBoolean()) {
						sale.setStatus("APROVADA");
					} else {
						sale.setStatus("REPROVADA");
					}
					try {
						Thread.sleep(500);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println(r.value());
				});
			}
		}

	}

}
