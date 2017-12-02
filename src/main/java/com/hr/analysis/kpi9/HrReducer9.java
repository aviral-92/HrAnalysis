package com.hr.analysis.kpi9;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class HrReducer9 extends Reducer<Text, Text, Text, Text> {

	private Text result = new Text();

	@Override
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

		int counter = 0, employeesLeft = 0;
		float avgSatisfaction = 0f, avgWorkingHrs = 0f, satisfactionSum = 0f, workingHrsSum = 0f;
		for (Text val : values) {
			String[] tokens = val.toString().split(",");
			if (tokens[9].equalsIgnoreCase("low") && Integer.parseInt(tokens[7]) == 0) {
				satisfactionSum += Float.parseFloat(tokens[0]);
				workingHrsSum += Float.parseFloat(tokens[3]);
				if (Integer.parseInt(tokens[6]) == 1) {
					employeesLeft += Integer.parseInt(tokens[6]);
				}
				counter++;
			}
		}
		if (counter != 0) {
			avgSatisfaction = satisfactionSum / counter;
			avgWorkingHrs = workingHrsSum / counter;
		}
		result.set("Salary low & not promoted in 5yrs - Average Satisfaction : " + avgSatisfaction + " Average Working Hrs : " + avgWorkingHrs
				+ " Employees Left : " + employeesLeft);
		context.write(key, result);
	}

}
