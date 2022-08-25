package com.models;

public class CustomerSpender {
	
	public int invoice_id;
	public int invoice_customer_id;
	public double invoice_total;
	
	public CustomerSpender(int invoice_id, int invoice_customer_id, double invoice_total) {
		super();
		this.invoice_id = invoice_id;
		this.invoice_customer_id = invoice_customer_id;
		this.invoice_total = invoice_total;
	}

	public int getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(int invoice_id) {
		this.invoice_id = invoice_id;
	}

	public int getInvoice_customer_id() {
		return invoice_customer_id;
	}

	public void setInvoice_customer_id(int invoice_customer_id) {
		this.invoice_customer_id = invoice_customer_id;
	}

	public double getInvoice_total() {
		return invoice_total;
	}

	public void setInvoice_total(int invoice_total) {
		this.invoice_total = invoice_total;
	}
	
	
}
