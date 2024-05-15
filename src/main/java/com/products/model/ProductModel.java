package com.products.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@Component
public class ProductModel {
	 
	 @Id
	 @GeneratedValue
	 @Column
	 private int ID;	 
	 @Column
	 private String NAME;
	 @Column
	 private float COST;
	 @Column
	 private String MANUFACTURER;
	 @Column
	 private String DESCRIPTION;
	 public ProductModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Column
	 private int NO_OF_ORDERS;
	 @Column
	 private String AVAILABILITY;
	
	public ProductModel(int iD, String nAME, float cOST, String mANUFACTURER, String dESCRIPTION, int nO_OF_ORDERS,
			String aVAILABILITY) {
		super();
		ID = iD;
		NAME = nAME;
		COST = cOST;
		MANUFACTURER = mANUFACTURER;
		DESCRIPTION = dESCRIPTION;
		NO_OF_ORDERS = nO_OF_ORDERS;
		AVAILABILITY = aVAILABILITY;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public float getCOST() {
		return COST;
	}
	public void setCOST(float cOST) {
		COST = cOST;
	}
	public String getMANUFACTURER() {
		return MANUFACTURER;
	}
	public void setMANUFACTURER(String mANUFACTURER) {
		MANUFACTURER = mANUFACTURER;
	}
	public String getDESCRIPTION() {
		return DESCRIPTION;
	}
	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}
	public int getNO_OF_ORDERS() {
		return NO_OF_ORDERS;
	}
	public void setNO_OF_ORDERS(int nO_OF_ORDERS) {
		NO_OF_ORDERS = nO_OF_ORDERS;
	}
	public String getAVAILABILITY() {
		return AVAILABILITY;
	}
	public void setAVAILABILITY(String aVAILABILITY) {
		AVAILABILITY = aVAILABILITY;
	}
	
	 
}
