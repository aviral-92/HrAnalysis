package com.hr.analysis.kpi12;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class HrReducer12 extends Reducer<Text, Text, Text, Text> {

	private Text result = new Text();

	@Override
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

		float empLeft = 0f, totalEmp = 0f;
		float empPercent = 0f;
		for (Text val : values) {
			String[] tokens = val.toString().split(",");
			empLeft += Integer.parseInt(tokens[6]);
			totalEmp++;
		}
		empPercent = (empLeft / totalEmp) * 100;
		if (empPercent > 70) {
			result.set("Employees left greater than 70% : " + empPercent);
		} else {
			result.set("Employees left less than 70% : " + empPercent);
		}
		context.write(key, result);
	}
}
