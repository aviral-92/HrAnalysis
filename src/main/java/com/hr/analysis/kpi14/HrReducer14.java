package com.hr.analysis.kpi14;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class HrReducer14 extends Reducer<Text, Text, Text, Text> {

	private Text result = new Text();

	@Override
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

		int experiance = 0;
		int highExp = 0;
		for (Text val : values) {
			String[] tokens = val.toString().split(",");
			experiance = Integer.parseInt(tokens[4].toString());
			if (experiance > highExp) {
				highExp = experiance;
			}
		}
		result.set(String.valueOf(highExp));
		context.write(key, result);
	}

}
