package com.hr.analysis.kpi13;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class HrReducer13 extends Reducer<Text, Text, Text, Text> {

	private Text result = new Text();

	@Override
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

		int highlyExp = 0, exp = 0;
		for (Text val : values) {
			String[] tokens = val.toString().split(",");
			exp = Integer.parseInt(tokens[4]);
			if (exp > highlyExp) {
				highlyExp = exp;
				exp = 0;
			}
		}
		result.set("Highly Exp : " + highlyExp + " years");
		context.write(key, result);
	}

}
