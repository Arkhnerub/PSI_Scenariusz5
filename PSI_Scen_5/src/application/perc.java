package application;
import java.util.Random;

public class perc {
	Random rand = new Random();
	private double suma;
	double[] wector;
	double[] weights;
	int id;
	
	public perc(double[] vector) {
		this.wector = vector;
		weights=new double[vector.length];
		for(int i=0;i<vector.length;i++) {
			weights[i]=rand.nextDouble()*2-1;
		}
	}
	
	public void setVector(double[] vector) {
		this.wector=vector;
	}
	public double sum() {
		suma=0;
		for(int i=0;i<weights.length;i++) {
			suma+=wector[i]*weights[i];
		}
		return suma;
	}
	public double getSuma() {
		return suma;
	}
	public double[] getWeights() {
		return weights;
	}
	public void setWeight(int index, double weight) {
		this.weights[index]=weight;
	}
	public void ucz(double wspolczynnikUczenia, double[] input) {
		for(int i=0;i<weights.length;i++) {
			weights[i]=weights[i]+wspolczynnikUczenia*(input[i]-weights[i]);
		}
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
}
