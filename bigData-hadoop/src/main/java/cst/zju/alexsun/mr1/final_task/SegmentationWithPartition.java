package cst.zju.alexsun.mr1.final_task;
import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
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
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
/**
 * MapReduce with partitioner.
 * 按照搜索发生的时间，以小时为单位（限于机器性能，使用的是500w的数据，只有0-10时的数据）
 * 分别分词，并统计搜索词的次数
 * 输入数据格式：
 * hour min sec user_id                             keyword rank page url
 * 00	00	40	a25534f965d5fd31daead21c78bf1973	cf官网	1	1   http://cf.qq.com/
 */
public class SegmentationWithPartition {
    public static class SegMapper extends
            Mapper<LongWritable, Text, Text, IntWritable>{
        private static JiebaSegmenter jiebaSegmenter = new JiebaSegmenter();
        // stop words set.
        private static HashSet<String> stops = new HashSet<String>();
        // 载入哈工大停用词表
        private static IntWritable one = new IntWritable(1);
        static{
            File file = new File("src/main/java/cst/zju/" +
                    "alexsun/mr1/final_task/hit_stopwords.txt");
            try{
                FileReader fr1 = new FileReader(file);
                BufferedReader br = new BufferedReader(fr1);
                String line = null;
                while((line = br.readLine())!=null){
                    stops.add(line);
                }

            }catch(Exception e) {
                System.err.println("error in set stopWords");
                e.printStackTrace();
            }
        }

        @Override
        protected void map(LongWritable key, Text value, Context context)
                throws IOException, InterruptedException {
            String pieces[] = value.toString().split("\t");
            String hour = pieces[0];
            String keyWords = pieces[4];
            List<SegToken> tokens = jiebaSegmenter.process(keyWords,
                    JiebaSegmenter.SegMode.SEARCH);
            for(SegToken token : tokens){
                String word = token.word;
                if(!stops.contains(word)){
                    StringBuilder sb = new StringBuilder(hour);
                    sb.append('\t');
                    sb.append(word);
                    context.write(new Text(sb.toString()), one);
                }
            }
        }

    }

    public static class HourPartitioner extends Partitioner<Text, IntWritable>{

        @Override
        public int getPartition(Text text, IntWritable intWritable, int i) {
            String pieces[] = text.toString().split("\t");
            int hour = Integer.parseInt(pieces[0]);
            return hour % i;
        }
    }

    public static class SumReducer extends Reducer<Text,
            IntWritable, Text, IntWritable>{
        private IntWritable result = new IntWritable(0);
        protected void reduce(Text key, Iterable<IntWritable> values,
                              Context context) throws IOException, InterruptedException {
            int cnt = 0;
            for(IntWritable i : values){
                cnt += i.get();
            }
            result.set(cnt);
            context.write(key, result);
        }
    }

    public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "MR partioner tokenizer");
        job.setJarByClass(SegmentationWithPartition.class);
        job.setPartitionerClass(SegmentationWithPartition.HourPartitioner.class);
        job.setNumReduceTasks(24);
        job.setMapperClass(SegmentationWithPartition.SegMapper.class);
        job.setCombinerClass(SegmentationWithPartition.SumReducer.class);
        job.setReducerClass(SegmentationWithPartition.SumReducer.class);
        // job.setInputFormatClass(InputFormat.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path("hdfs://localhost:9000/token_hour/input/"));
        FileOutputFormat.setOutputPath(job, new Path("hdfs://localhost:9000/token_hour/output/t1"));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

}
