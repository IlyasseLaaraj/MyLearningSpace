package impl.models;

import impl.interfaces.Titolo;

public class TitoloClass implements Titolo {
	
	private String id;
	private String name;
	private int duration;
	private int quantity;
	private double value;
	private int intrest;
	private int iMin;
	private int risk;

	public TitoloClass(String id, String name, int duration, int quantity, double value, int intrest, int iMin, int risk) {
	this.id = id;
	this.name = name;
	this.duration = duration;
	this.quantity = quantity;
	this.value = value;
	this.intrest = intrest;
	this.iMin = iMin;
	this.risk = risk;
	}

	public String getId () {
		return this.id;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public String getName () {
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getDuration () {
		return this.duration;
	}
	
	public void setDuration(int duration){
		this.duration = duration;
	}
	
	public int getQuantity () {
		return this.quantity;
	}
	
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
	
	public double getValue () {
		return this.value;
	}
	
	public void setValue(int value){
		this.value = value;
	}
	
	public int getInterest () {
		return this.intrest;
	}
	
	public void setInterest(int intrest){
		this.intrest = intrest;
	}
	
	public int getIMin () {
		return this.iMin;
	}
	
	public void setIMin(int iMin){
		this.iMin = iMin;
	}
	
	public int getRisk () {
		return this.risk;
	}
	
	public void setRisk(int risk){
		this.risk = risk;
	}
}
