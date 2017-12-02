package com.hr.analysis.kpi7;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class HrReducer7 extends Reducer<Text, Text, Text, Text> {

	private Text result = new Text();

	@Override
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

		int workingHrSum = 0, counter = 0, empLeft = 0;
		float avgSatisfaction = 0f, avgWorkingHrs = 0f, satisfactionSum = 0f;
		for (Text val : values) {

			String[] tokens = val.toString().split(",");
			satisfactionSum += Float.parseFloat(tokens[0]);
			workingHrSum += Integer.parseInt(tokens[3]);
			empLeft += Integer.parseInt(tokens[6]);
			counter++;
		}
		avgSatisfaction = satisfactionSum / counter;
		avgWorkingHrs = workingHrSum / counter;
		result.set("Average Satisfaction : " + avgSatisfaction + " Average Working Hrs : " + avgWorkingHrs
				+ " Employees Left " + empLeft);
		context.write(key, result);
	}
}
