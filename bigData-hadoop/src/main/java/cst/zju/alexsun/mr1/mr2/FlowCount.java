package cst.zju.alexsun.mr1.mr2;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class FlowCount {
     public static class FlowCountMapper extends
             Mapper<LongWritable,Text,FlowBean,Text> {

         @Override
         protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
             // super.map(key, value, context);
             String line = value.toString();
             String[] fields = line.split("\t");
             try{
                 String nbr = fields[0];
                 long up = Long.parseLong(fields[1]);
                 long down = Long.parseLong(fields[2]);
                 FlowBean flowBean = new FlowBean(up,down);
                 context.write(flowBean,new Text(nbr));
             }catch (Exception e){
                 e.printStackTrace();
             }
         }
     }

    public static class FlowCountReducer extends
            Reducer<FlowBean,Text,Text,FlowBean> {
        @Override
        protected void reduce(FlowBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            super.reduce(key, values, context);
            Text nbr = values.iterator().next();
            context.write(nbr,key);
        }
    }

    public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);
        job.setJarByClass(FlowCount.class);
        job.setMapperClass(FlowCountMapper.class);
        job.setReducerClass(FlowCountReducer.class);
        job.setMapOutputKeyClass(FlowCount.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        FileInputFormat.setInputPaths(job,new Path("sss"));
        FileOutputFormat.setOutputPath(job,new Path(""));
        job.waitForCompletion(true);
    }
}
