package com.example;

public class SingleTon {
	private static SingleTon singleTonInstance;
	//private constructor
	private SingleTon() {
	}
	//public static get instance
	public static SingleTon getSingleInstance() {
		if (singleTonInstance == null) {
			synchronized (SingleTon.class) {
				if (singleTonInstance == null) {
					singleTonInstance = new SingleTon();
				}
			}
		}
		return singleTonInstance;
	}
}