package com.po.kazan;

import org.json.JSONArray;
import org.json.JSONObject;

import com.badlogic.gdx.Gdx;

public class Calculation {

	public String name;
	private int houseType; // 1-> villa, 2-> apartment
	private int storeys; // 1,2,3,4,5,6
	private int area; // x metrekare
	private int height; // 2.60, 2.70, 2.80
	private int manto; // 1-> evet, 2-> hayır
	private int nearAway; // 1-> near(dense), 2-> away(open)
	private int window; // Anlamsız, hesaplamada hiç kullanılmamış.
	private int windowType; // 1-> çelik, 2-> ahşap, 3-> pvc; 3 ise isolationType = good olabilir.
	private int heat; // 1-> 20, 2-> 22, 3-> 24
	private int sysSize; // 1-> küçük, 2-> orta, 3-> büyük
	private int fuelType; // 1-> kömür, 2-> odun, 3-> pellet, 4-> doğal gaz, 5-> lpg, 6-> dizel, 7-> fuel oil
	private float V;
	private double base;
	private double hourlyCons;
	private float avHeat;
	private float k;
	public int[] fuelTypeCal;
	public double[] fuelTypeEff;
	public kValue[] kChart;

	public Calculation(String name, int houseType, int storeys, int area,
			int height, int manto, int nearAway, int window, int windowType,
			int heat, int sysSize, int fuelType) {
		this.name = name;
		this.houseType = houseType;
		this.storeys = storeys;
		this.area = area;
		this.height = height;
		this.manto = manto;
		this.nearAway = nearAway;
		this.window = window;
		this.windowType = windowType;
		this.heat = heat;
		this.sysSize = sysSize;
		this.fuelType = fuelType;
		
		V = 1; base = 1.0; hourlyCons = 1; avHeat = 1; k = 1;
		fuelTypeCal = new int[8];
		fuelTypeEff = new double[8];
		kChart = new kValue[40];

		kChart[0] = new kValue("greater9", "villa", "good", "dense", 17);
		kChart[1] = new kValue("greater9", "villa", "good", "open", 25);
		kChart[2] = new kValue("greater9", "villa", "poor", "dense", 27);
		kChart[3] = new kValue("greater9", "villa", "poor", "open", 37);
		kChart[4] = new kValue("greater9", "apartment", "good", "dense", 19);
		kChart[5] = new kValue("greater9", "apartment", "good", "open", 28);
		kChart[6] = new kValue("greater9", "apartment", "poor", "dense", 30);
		kChart[7] = new kValue("greater9", "apartment", "poor", "open", 40);

		kChart[8] = new kValue("between85", "villa", "good", "dense", 20);		
		kChart[9] = new kValue("between85", "villa", "good", "open", 28);
		kChart[10] = new kValue("between85", "villa", "poor", "dense", 33);
		kChart[11] = new kValue("between85", "villa", "poor", "open", 43);
		kChart[12] = new kValue("between85", "apartment", "good", "dense", 22);
		kChart[13] = new kValue("between85", "apartment", "good", "open", 30);
		kChart[14] = new kValue("between85", "apartment", "poor", "dense", 40);
		kChart[15] = new kValue("between85", "apartment", "poor", "open", 50);

		kChart[16] = new kValue("between40", "villa", "good", "dense", 22);		
		kChart[17] = new kValue("between40", "villa", "good", "open", 30);
		kChart[18] = new kValue("between40", "villa", "poor", "dense", 37);
		kChart[19] = new kValue("between40", "villa", "poor", "open", 45);
		kChart[20] = new kValue("between40", "apartment", "good", "dense", 25);
		kChart[21] = new kValue("between40", "apartment", "good", "open", 33);
		kChart[22] = new kValue("between40", "apartment", "poor", "dense", 45);
		kChart[23] = new kValue("between40", "apartment", "poor", "open", 55);


		kChart[24] = new kValue("between15", "villa", "good", "dense", 24);
		kChart[25] = new kValue("between16", "villa", "good", "open", 34);
		kChart[26] = new kValue("between17", "villa", "poor", "dense", 40);
		kChart[27] = new kValue("between18", "villa", "poor", "open", 49);
		kChart[28] = new kValue("between19", "apartment", "good", "dense", 28);
		kChart[29] = new kValue("between110", "apartment", "good", "open", 38);
		kChart[30] = new kValue("between111", "apartment", "poor", "dense", 50);
		kChart[31] = new kValue("between112", "apartment", "poor", "open", 60);

		kChart[32] = new kValue("between612", "villa", "good", "dense", 30);
		kChart[33] = new kValue("between613", "villa", "good", "open", 40);
		kChart[34] = new kValue("between614", "villa", "poor", "dense", 45);
		kChart[35] = new kValue("between615", "villa", "poor", "open", 60);
		kChart[36] = new kValue("between616", "apartment", "good", "dense", 35);
		kChart[37] = new kValue("between617", "apartment", "good", "open", 45);
		kChart[38] = new kValue("between618", "apartment", "poor", "dense", 60);
		kChart[39] = new kValue("between619", "apartment", "poor", "open", 70);

		fuelTypeCal[1] = 6000;
		fuelTypeCal[2] = 3500;
		fuelTypeCal[3] = 4400;
		fuelTypeCal[4] = 8250;
		fuelTypeCal[5] = 11000;
		fuelTypeCal[6] = 9600;
		fuelTypeCal[7] = 10200;

		fuelTypeEff[1] = 0.75;
		fuelTypeEff[2] = 0.75;
		fuelTypeEff[3] = 0.95;
		fuelTypeEff[4] = 0.94;
		fuelTypeEff[5] = 0.88;
		fuelTypeEff[6] = 0.92;
		fuelTypeEff[7] = 0.94;
	}



	public Calculation(){
		houseType = 0;
		storeys = 0;
		area = 0;
		height = 0;
		manto = 0;
		nearAway = 0;
		window = 0;
		windowType = 0;
		heat = 0;
		sysSize = 0;
		fuelType = 0;
		V = 1; base = 1.0; hourlyCons = 1; avHeat = 1; k = 1;
		fuelTypeCal = new int[8];
		fuelTypeEff = new double[8];
		kChart = new kValue[40];

		kChart[0] = new kValue("greater9", "villa", "good", "dense", 17);
		kChart[1] = new kValue("greater9", "villa", "good", "open", 25);
		kChart[2] = new kValue("greater9", "villa", "poor", "dense", 27);
		kChart[3] = new kValue("greater9", "villa", "poor", "open", 37);
		kChart[4] = new kValue("greater9", "apartment", "good", "dense", 19);
		kChart[5] = new kValue("greater9", "apartment", "good", "open", 28);
		kChart[6] = new kValue("greater9", "apartment", "poor", "dense", 30);
		kChart[7] = new kValue("greater9", "apartment", "poor", "open", 40);

		kChart[8] = new kValue("between85", "villa", "good", "dense", 20);		
		kChart[9] = new kValue("between85", "villa", "good", "open", 28);
		kChart[10] = new kValue("between85", "villa", "poor", "dense", 33);
		kChart[11] = new kValue("between85", "villa", "poor", "open", 43);
		kChart[12] = new kValue("between85", "apartment", "good", "dense", 22);
		kChart[13] = new kValue("between85", "apartment", "good", "open", 30);
		kChart[14] = new kValue("between85", "apartment", "poor", "dense", 40);
		kChart[15] = new kValue("between85", "apartment", "poor", "open", 50);

		kChart[16] = new kValue("between40", "villa", "good", "dense", 22);		
		kChart[17] = new kValue("between40", "villa", "good", "open", 30);
		kChart[18] = new kValue("between40", "villa", "poor", "dense", 37);
		kChart[19] = new kValue("between40", "villa", "poor", "open", 45);
		kChart[20] = new kValue("between40", "apartment", "good", "dense", 25);
		kChart[21] = new kValue("between40", "apartment", "good", "open", 33);
		kChart[22] = new kValue("between40", "apartment", "poor", "dense", 45);
		kChart[23] = new kValue("between40", "apartment", "poor", "open", 55);


		kChart[24] = new kValue("between15", "villa", "good", "dense", 24);
		kChart[25] = new kValue("between16", "villa", "good", "open", 34);
		kChart[26] = new kValue("between17", "villa", "poor", "dense", 40);
		kChart[27] = new kValue("between18", "villa", "poor", "open", 49);
		kChart[28] = new kValue("between19", "apartment", "good", "dense", 28);
		kChart[29] = new kValue("between110", "apartment", "good", "open", 38);
		kChart[30] = new kValue("between111", "apartment", "poor", "dense", 50);
		kChart[31] = new kValue("between112", "apartment", "poor", "open", 60);

		kChart[32] = new kValue("between612", "villa", "good", "dense", 30);
		kChart[33] = new kValue("between613", "villa", "good", "open", 40);
		kChart[34] = new kValue("between614", "villa", "poor", "dense", 45);
		kChart[35] = new kValue("between615", "villa", "poor", "open", 60);
		kChart[36] = new kValue("between616", "apartment", "good", "dense", 35);
		kChart[37] = new kValue("between617", "apartment", "good", "open", 45);
		kChart[38] = new kValue("between618", "apartment", "poor", "dense", 60);
		kChart[39] = new kValue("between619", "apartment", "poor", "open", 70);

		fuelTypeCal[1] = 6000;
		fuelTypeCal[2] = 3500;
		fuelTypeCal[3] = 4400;
		fuelTypeCal[4] = 8250;
		fuelTypeCal[5] = 11000;
		fuelTypeCal[6] = 9600;
		fuelTypeCal[7] = 10200;

		fuelTypeEff[1] = 0.75;
		fuelTypeEff[2] = 0.75;
		fuelTypeEff[3] = 0.95;
		fuelTypeEff[4] = 0.94;
		fuelTypeEff[5] = 0.88;
		fuelTypeEff[6] = 0.92;
		fuelTypeEff[7] = 0.94;
	}

	public void calculate(){

		V = storeys*area;

		if(height == 1)
			V *= 2.60;
		if(height == 2)
			V *= 2.70;
		if(height == 3)
			V *= 2.80;
		if(height == 4)
			V *= 2.90;

		System.out.println("V: " + V);
		// hacmi hesapladık. şimdi k'yı bulalım.

		String iso = "poor";
		if(manto == 1 && (windowType == 2 || windowType == 3))
			iso = "good";
		// 1-> çelik, 2-> ahşap, 3-> pvc; 3 ise isolationType = good olabilir.
		if (windowType == 1){
			iso = "poor";
		}else if (manto == 2){
			iso = "poor";
		}else if(manto == 1 && (windowType == 2 || windowType == 3)){
			iso = "good";
		}

		int tempKValue;
		String tempHeat = "";

		if (avHeat > 9){

			tempHeat = "greater9";
		}if(avHeat <= 8 && avHeat > 5){
			tempHeat = "between85";
		}if(avHeat <= 5 && avHeat >= 0){
			tempHeat = "between40";
		}if(avHeat <= -1 && avHeat >= -5){
			tempHeat = "between15";
		}if(avHeat <= -1 && avHeat >= -6){
			tempHeat = "between16";
		}if(avHeat <= -1 && avHeat >= -7){
			tempHeat = "between17";
		} if(avHeat <= -1 && avHeat >= -8){
			tempHeat = "between18";
		}if(avHeat <= -1 && avHeat >= -9){
			tempHeat = "between19";
		}if(avHeat <= -1 && avHeat >= -10){
			tempHeat = "between110";
		}if(avHeat <= -1 && avHeat >= -11){
			tempHeat = "between111";
		}if(avHeat <= -1 && avHeat >= -12){
			tempHeat = "between112";
		}if(avHeat <= -6 && avHeat >= -12){
			tempHeat = "between612";
		}if(avHeat <= -6 && avHeat >= -13){
			tempHeat = "between613";
		}if(avHeat <= -6 && avHeat >= -14){
			tempHeat = "between614";
		}if(avHeat <= -6 && avHeat >= -15){
			tempHeat = "between615";
		}if(avHeat <= -6 && avHeat >= -16){
			tempHeat = "between616";
		}if(avHeat <= -6 && avHeat >= -17){
			tempHeat = "between617";
		}if(avHeat <= -6 && avHeat >= -18){
			tempHeat = "between618";
		}if(avHeat <= -6 && avHeat >= -19){
			tempHeat = "between619";
		}
		String tempHouseType;
		if (houseType == 1){
			tempHouseType = "villa";
		}else{
			tempHouseType = "apartment";
		}
		String tempHouseLocation;
		if(nearAway == 1){
			tempHouseLocation = "dense";
		}else{
			tempHouseLocation = "open";

		}
		int tempIndex;
		for (int i = 0; i < kChart.length; i++){

			if(kChart[i].heatIntervalType.equals(tempHeat) && kChart[i].houseType.equals(tempHouseType)
					&& kChart[i].isolationType.equals(iso) && kChart[i].houseLocation.equals(tempHouseLocation)){
				tempIndex = i;
				this.k = kChart[i].kValue;
				break;
			}
		}
		System.out.println("avHeat: " + avHeat);
		System.out.println("heatIntervalType: " + tempHeat);
		System.out.println("houseType: " + tempHouseType);
		System.out.println("isolationType: " + iso);
		System.out.println("houseLocation: " + tempHouseLocation);

		System.out.println("k Value: " + k);

		base = k * V;

		// 1-> çelik, 2-> ahşap, 3-> pvc; 3 ise isolationType = good olabilir.

		double tempWin = 1;
		double tempHt = 1;

		if (windowType == 1)
			tempWin = 1.0;
		else if (windowType == 2)
			tempWin = 1.05;
		else if (windowType == 2)
			tempWin = 1.10;

		// 1-> 20, 2-> 22, 3-> 24
		System.out.println("tempWin: " + tempWin);

		if(heat == 1)
			tempHt = 1.0;
		if(heat == 2)
			tempHt = 1.033;
		if(heat == 3)
			tempHt = 1.071;

		System.out.println("tempHt: " + tempHt);


		base = (base*tempHt * tempWin * 1.05);

		System.out.println("base after tempHt: " + base);

		// 1-> küçük, 2-> orta, 3-> büyük

		int tempSize = 0;

		if(sysSize == 1)
			tempSize = 3500*storeys;
		if(sysSize == 2)
			tempSize = 4500*storeys;
		if(sysSize == 3)
			tempSize = 5500*storeys;

		base += tempSize;
		System.out.println("base after syssize: " + base);
		
		double finalValue = (base / fuelTypeCal[fuelType])*(1+(1-fuelTypeEff[fuelType]));
		System.out.println("finVal: " + finalValue);

		hourlyCons = finalValue;
	}

	public int getHouseType() { 
		return houseType;
	}

	public void setHouseType(int houseType) {
		this.houseType = houseType;
	}

	public int getStoreys() {
		return storeys;
	}

	public void setStoreys(int storeys) {
		this.storeys = storeys;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getManto() {
		return manto;
	}

	public void setManto(int manto) {
		this.manto = manto;
	}

	public int getNearAway() {
		return nearAway;
	}

	public void setNearAway(int nearAway) {
		this.nearAway = nearAway;
	}

	public int getWindow() {
		return window;
	}

	public void setWindow(int window) {
		this.window = window;
	}

	public int getWindowType() {
		return windowType;
	}

	public void setWindowType(int windowType) {
		this.windowType = windowType;
	}

	public int getHeat() {
		return heat;
	}

	public void setHeat(int heat) {
		this.heat = heat;
	}

	public int getSysSize() {
		return sysSize;
	}

	public void setSysSize(int sysSize) {
		this.sysSize = sysSize;
	}

	public int getFuelType() {
		return fuelType;
	}

	public void setFuelType(int fuelType) {
		this.fuelType = fuelType;
	}

	public float getV() {
		return V;
	}

	public void setV(float v) {
		V = v;
	}

	public double getBase() {
		return base;
	}

	public void setBase(double base) {
		this.base = base;
	}

	public double getHourlyCons() {
		return hourlyCons;
	}

	public void setHourlyCons(double hourlyCons) {
		this.hourlyCons = hourlyCons;
	}

	public float getAvHeat() {
		return avHeat;
	}

	public void setAvHeat(float avHeat) {
		this.avHeat = avHeat;
	}

	public float getK() {
		return k;
	}

	public void setK(float k) {
		this.k = k;
	}

}
