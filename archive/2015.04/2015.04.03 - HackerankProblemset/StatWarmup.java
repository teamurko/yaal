package net.spak;

import net.egork.collections.Pair;
import net.egork.collections.map.Counter;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class StatWarmup {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] series = new int[n];
		for (int i = 0; i < n; ++i) {
			series[i] = in.readInt();
		}
		Arrays.sort(series);
		out.printLine(String.format("%.1f", calcMean(series)));
		out.printLine(String.format("%.1f", calcMedian(series)));
		out.printLine(calcMode(series));
		out.printLine(String.format("%.1f", calcStd(series)));
		Pair<Double, Double> toleranceInterval = calcToleranceInterval(series, 0.05);
		out.printLine(String.format("%.1f %.1f", toleranceInterval.first, toleranceInterval.second));
    }

	private Pair<Double, Double> calcToleranceInterval(int[] series, double v) {
		double mean = calcMean(series);
		double std = calcStd(series);
		double n = 1.96;
		return Pair.makePair(mean - n * std / Math.sqrt(series.length), mean + n * std / Math.sqrt(series.length));
	}

	private double calcStd(int[] series) {
		double mean = calcMean(series);
		double std2 = 0;
		for (int i : series) {
			std2 += Math.pow(i - mean, 2.0);
		}
		return Math.sqrt(std2 / series.length);
	}

	private int calcMode(int[] series) {
		int count = 1;
		int elem = series[0];
		int l = 0;
		while (l < series.length) {
			int r = l + 1;
			while (r < series.length && series[r] == series[l]) ++r;
			if (count < r - l) {
				count = r - l;
				elem = series[l];
			}
			l = r;
		}
		return elem;
	}

	private double calcMedian(int[] series) {
		int n = series.length;
		if (n % 2 == 0) {
			return (series[n / 2] + series[n / 2 - 1]) * 0.5;
		} else {
			return series[n / 2];
		}
	}

	private double calcMean(int[] series) {
		double sum = ArrayUtils.sumArray(series);
		return sum / series.length;
	}
}
