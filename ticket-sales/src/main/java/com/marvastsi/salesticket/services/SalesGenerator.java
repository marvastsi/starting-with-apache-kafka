package com.marvastsi.salesticket.services;

import java.math.BigDecimal;
import java.util.Properties;
import java.util.Random;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.marvastsi.salesticket.model.Sale;
import com.marvastsi.salesticket.serializer.SaleSerializer;

public class SalesGenerator {

	private static Random random = new Random();
	private static long operation = 0;
	private static BigDecimal ticketPrice = BigDecimal.valueOf(250);

	public static void main(String[] args) throws Exception {

		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, SaleSerializer.class.getName());

		try (KafkaProducer<String, Sale> producer = new KafkaProducer<>(properties)) {
			while (true) {
				Sale sale = generateSale();
				ProducerRecord<String, Sale> record = new ProducerRecord<>("sales", sale);
				producer.send(record);
				Thread.sleep(200);
			}
		}
	}

	private static Sale generateSale() {
		long client = random.nextLong();
		int ticketsAmount = random.nextInt(15);
		return new Sale(operation, client, ticketsAmount, ticketPrice.multiply(BigDecimal.valueOf(ticketsAmount)));
	}
}
