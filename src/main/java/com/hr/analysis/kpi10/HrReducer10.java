package com.hr.analysis.kpi10;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class HrReducer10 extends Reducer<Text, Text, Text, Text> {

	private Text result = new Text();

	@Override
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

		int counter = 0;
		float avgSatisfaction = 0f, avgEvaluation = 0f, satisfactionSum = 0f, evaluationSum = 0f, lowPercent = 0f,
				mediumPercent = 0f, highPercent = 0f, empLowSalary = 0f, empMediumSalary = 0f, empHighSalary = 0f,
				employeesLeft = 0f;
		for (Text val : values) {
			String[] tokens = val.toString().split(",");
			satisfactionSum += Float.parseFloat(tokens[0]);
			evaluationSum += Float.parseFloat(tokens[1]);
			counter++;

			// Calculate total number of Employees who left, no matter their
			// salary is low, medium or high.
			if (Integer.parseInt(tokens[6]) == 1) {
				employeesLeft += Integer.parseInt(tokens[6]);
			}

			// Calculate on the basis of salary that is low.
			if (tokens[9].equalsIgnoreCase("low")) {
				empLowSalary += Integer.parseInt(tokens[6]);
				// Calculate on the basis of salary that is medium.
			} else if (tokens[9].equalsIgnoreCase("medium")) {
				empMediumSalary += Integer.parseInt(tokens[6]);
				// Calculate on the basis of salary that is high.
			} else if (tokens[9].equalsIgnoreCase("high")) {
				empHighSalary += Integer.parseInt(tokens[6]);
			}
		}
		// Checking not divisible by zero.
		if (counter != 0) {
			// Calculate Average Satisfaction.
			avgSatisfaction = satisfactionSum / counter;
			// Calculate Average Evaluation.
			avgEvaluation = evaluationSum / counter;

			// Calculate Employees percentage when Salary is 'LOW'.
			lowPercent = (empLowSalary / employeesLeft) * 100;
			// Calculate Employees percentage when Salary is 'MEDIUM'.
			mediumPercent = (empMediumSalary / employeesLeft) * 100;
			// Calculate Employees percentage when Salary is 'HIGH'.
			highPercent = (empHighSalary / employeesLeft) * 100;
		}
		result.set("Avg Satisfaction : " + avgSatisfaction + " Avg Evaluation " + avgEvaluation + " when salary "
				+ " low " + lowPercent + "% medium " + mediumPercent + "% high " + highPercent + "%");
		context.write(key, result);
	}

}
