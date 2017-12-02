package com.hr.analysis.kpi5;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class HrReducer5 extends Reducer<Text, Text, Text, Text> {

	private Text result = new Text();

	@Override
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

		int low = 0, medium = 0, high = 0;
		for (Text val : values) {

			String[] tokens = val.toString().split(",");
			if (tokens[9].equalsIgnoreCase("low")) {
				low++;
			} else if (tokens[9].equalsIgnoreCase("medium")) {
				medium++;
			} else if (tokens[9].equalsIgnoreCase("high")) {
				high++;
			}
		}
		result.set("low : " + low + " medium : " + medium + " high : " + high);
		context.write(key, result);
	}
}
