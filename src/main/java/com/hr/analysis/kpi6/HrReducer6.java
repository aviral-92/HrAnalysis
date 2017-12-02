package com.hr.analysis.kpi6;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class HrReducer6 extends Reducer<Text, Text, Text, Text> {

	private Text result = new Text();

	@Override
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

		int noOfEmployees = 0;
		for (Text val : values) {

			String[] tokens = val.toString().split(",");
			if ((Integer.parseInt(tokens[7]) == 1) && (Integer.parseInt(tokens[6]) == 1)) {
				noOfEmployees++;
			}
		}
		result.set(String.valueOf(noOfEmployees));
		context.write(key, result);
	}
}
