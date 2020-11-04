package com.marvastsi.salesticket.model;

import java.math.BigDecimal;

public class Sale {

	private Long operation;
	private Long client;
	private Integer ticketsAmount;
	private BigDecimal totalValue;
	private String status;

	public Sale() {
	}

	public Sale(Long operation, Long client, Integer ticketsAmount, BigDecimal totalValue) {
		this.operation = operation;
		this.client = client;
		this.ticketsAmount = ticketsAmount;
		this.totalValue = totalValue;
	}

	public Long getOperation() {
		return operation;
	}

	public void setOperation(Long operation) {
		this.operation = operation;
	}

	public Long getClient() {
		return client;
	}

	public void setClient(Long client) {
		this.client = client;
	}

	public Integer getTicketsAmount() {
		return ticketsAmount;
	}

	public void setTicketsAmount(Integer ticketsAmount) {
		this.ticketsAmount = ticketsAmount;
	}

	public BigDecimal getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Sale [operation=" + operation + ", client=" + client + ", ticketsAmount=" + ticketsAmount
				+ ", totalValue=" + totalValue + ", status=" + status + "]";
	}

}
