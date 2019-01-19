package nl.cityparking.garfield.simulator.math;public class NormalDistribution {	private double mean;	private double standardDeviation;	private double variance;	private double normalizingFactor;	public NormalDistribution(double mean, double standardDeviation) {		this.mean = mean;		this.standardDeviation = standardDeviation;		this.variance = Math.pow(standardDeviation, 2); // save precious CPU cycles!		this.normalizingFactor = 1 / (Math.sqrt(2 * Math.PI * this.variance));	}	public double value(double x) {		return this.normalizingFactor * Math.pow(Math.E, -(Math.pow(x - this.mean, 2) / (2 * this.variance)));	}	public double rescaledValue(double x) {		return this.value(Math.tan(Math.PI * (x - 0.5)));	}	public double rescaledLogValue(double x) {		// It'll try to calculate infinity if x = 1 or x = 0, which results in NaN		if (x >= 1 || x <= 0) {			return 0;		}		return this.value(Math.log(x) - Math.log(1-x));	}	public double getMean() {		return this.mean;	}	public double getStandardDeviation() {		return this.standardDeviation;	}}