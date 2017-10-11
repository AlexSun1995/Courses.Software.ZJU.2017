package cst.zju.alexsun.mr1.partitioner;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import java.io.IOException;

/**
 *  @author copy from blog:
 *  see this :https://www.tutorialspoint.com/map_reduce/map_reduce_partitioner.htm
 *  there will have 3 output files in the /part/output/o4/, each file
 *  represents a reducer's output.
 */
public class PartitionerTest {
    public static class PartitionerMapper extends
            Mapper<LongWritable,Text,Text,Text>{

        @Override
        protected void map(LongWritable key, Text value, Context context)
                throws IOException, InterruptedException {
            try{
                String line =  value.toString();
                String gender = line.split("\t",-3)[3];
                context.write(new Text(gender), new Text(value));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static class CaderPatitioner extends Partitioner<Text, Text>{
        @Override
        public int getPartition(Text text, Text text2, int i) {
            String[] str = text2.toString().split("\t");
            int age = Integer.parseInt(str[2]);
            if(i == 0){
                return 0;
            }

            if(age <= 20){
                return 0;
            }
            else if(age>20 && age<=30){
                return 1 % i;
            }
            else{
                return 2 % i;
            }
        }
    }

    public static class PartitionerReducer extends
            Reducer<Text,Text,Text,IntWritable>{
        public int max = -1;

        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            // super.reduce(key, values, context);
            max = -1;
            for(Text val : values){
                String[] str = val.toString().split("\t", -1);
                if(Integer.parseInt(str[4]) > max){
                    max = Integer.parseInt(str[4]);
                }
            }
            context.write(new Text(key), new IntWritable(max));
        }
    }


    public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration,"partitioner");
        job.setJarByClass(PartitionerTest.class);
        job.setMapperClass(PartitionerMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setReducerClass(PartitionerReducer.class);
        // set partition class
        job.setPartitionerClass(CaderPatitioner.class);
        job.setNumReduceTasks(3);
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        job.setOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        FileInputFormat.setInputPaths(job,new Path("hdfs://localhost:9000/part/input/input.txt"));
        FileOutputFormat.setOutputPath(job,new Path("hdfs://localhost:9000/part/output/o4/"));
        System.exit(job.waitForCompletion(true)? 0 : 1);
    }

}
