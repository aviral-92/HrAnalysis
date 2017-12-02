package com.hr.analysis.kpi15;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class HrReducer15 extends Reducer<NullWritable, Text, NullWritable, Text> {

	private StringBuffer result = new StringBuffer("Dept ");
	float projIt, projSales, projHr, projMark, projAcc, projMgmt, projProd, projRD, projSupp, projTech;
	float proj, totProj, projPercent = 0f;

	@Override
	public void reduce(NullWritable key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {

		for (Text val : values) {
			String[] tokens = val.toString().split(",");
			totProj += Float.parseFloat(tokens[2]);

			if (tokens[8].toString().equalsIgnoreCase("accounting")) {
				projAcc += Float.parseFloat(tokens[2]);
			} else if (tokens[8].toString().equalsIgnoreCase("hr")) {
				projHr += Float.parseFloat(tokens[2]);
			} else if (tokens[8].toString().equalsIgnoreCase("IT")) {
				projIt += Float.parseFloat(tokens[2]);
			} else if (tokens[8].toString().equalsIgnoreCase("management")) {
				projMgmt += Float.parseFloat(tokens[2]);
			} else if (tokens[8].toString().equalsIgnoreCase("marketing")) {
				projMark += Float.parseFloat(tokens[2]);
			} else if (tokens[8].toString().equalsIgnoreCase("product_mng")) {
				projProd += Float.parseFloat(tokens[2]);
			} else if (tokens[8].toString().equalsIgnoreCase("RandD")) {
				projRD += Float.parseFloat(tokens[2]);
			} else if (tokens[8].toString().equalsIgnoreCase("sales")) {
				projSales += Float.parseFloat(tokens[2]);
			} else if (tokens[8].toString().equalsIgnoreCase("support")) {
				projSupp += Float.parseFloat(tokens[2]);
			} else if (tokens[8].toString().equalsIgnoreCase("technical")) {
				projTech += Float.parseFloat(tokens[2]);
			}
		}
		if ((projAcc / totProj) * 100 > 40) {
			result.append(" accounting ");
		}
		if ((projHr / totProj) * 100 > 40) {
			result.append(" hr ");
		}
		if ((projIt / totProj) * 100 > 40) {
			result.append(" IT ");
		}
		if ((projMgmt / totProj) * 100 > 40) {
			result.append(" management ");
		}
		if ((projMark / totProj) * 100 > 40) {
			result.append(" marketing ");
		}
		if ((projProd / totProj) * 100 > 40) {
			result.append(" product_mng ");
		}
		if ((projRD / totProj) * 100 > 40) {
			result.append(" RandD ");
		}
		if ((projSales / totProj) * 100 > 40) {
			result.append(" sales ");
		}
		if ((projSupp / totProj) * 100 > 40) {
			result.append(" support ");
		}
		if ((projTech / totProj) * 100 > 40) {
			result.append(" technical ");
		}
		if (result.toString().equalsIgnoreCase("Dept ")) {
			result = new StringBuffer("No dept is greater than 40% of overall project.");
		} else {
			result.append(" is/are greater than 40% of overall projects.");
		}
		context.write(key, new Text(result.toString()));
	}

}
