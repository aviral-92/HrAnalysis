package com.hr.analysis.kpi11;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class HrReducer11 extends Reducer<Text, Text, Text, Text> {

	private Text result = new Text();

	@Override
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

		int empLeft = 0;
		for (Text val : values) {
			String[] tokens = val.toString().split(",");
			empLeft += Integer.parseInt(tokens[6]);
		}
		result.set("Employees left : " + empLeft);
		context.write(key, result);
	}

}
