package cst.zju.alexsun.mr1.final_task;
import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.junit.Test;

import java.io.*;
import java.util.HashSet;
import java.util.List;

public class Segmentation1 {

    /**
     * jieba 分词工具测试
     *
     **/
    public static void jiebaTest(){

        JiebaSegmenter segmentation = new JiebaSegmenter();
        String sentence = "中国人民大学是中国人民的大学";
        List<SegToken> tokens = segmentation.process(sentence, JiebaSegmenter.SegMode.SEARCH);
        for(SegToken token : tokens){
            System.out.println(token.word);
        }
    }

    /**
     * Mapper 分词,输入的数据为keywords
     */
    public static class TokenizerMapper
            extends Mapper<Object, Text,Text,IntWritable> {
        private Text word = new Text();
        private static HashSet<String> stopWords = new HashSet<String>();
        private JiebaSegmenter jiebaSegmenter = new JiebaSegmenter();

        /**
         * 载入停用词
         */
        static {
            File file = new File("src/main/java/cst/zju/" +
                    "alexsun/mr1/final_task/hit_stopwords.txt");
            try{
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String line = null;
                while((line = br.readLine())!=null){
                    stopWords.add(line);
                }

            }catch(IOException e) {
                e.printStackTrace();
            }
        }

        public void map(Object key, Text value, Context context)
                throws IOException, InterruptedException {
            String line = value.toString();
            IntWritable one = new IntWritable(1);
            List<SegToken> tokens = jiebaSegmenter.process(line,
                    JiebaSegmenter.SegMode.SEARCH);
            for(SegToken token : tokens){
                String word = token.word;
                if(stopWords.contains(word)){
                    continue;
                }
                Text tWord = new Text(word);
                context.write(tWord, one);
            }
        }
    }

    /**
     *  Reducer
     */
    public static class CountReducer
            extends Reducer<Text, IntWritable, Text,IntWritable> {
        private IntWritable result = new IntWritable();
        public void reduce(Text key, Iterable<IntWritable> values,
                           Context context
        ) throws IOException, InterruptedException {

            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }
            result.set(sum);
            context.write(key, result);
        }
    }

    public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "MR tokenizer");
        job.setJarByClass(Segmentation1.class);
        job.setMapperClass(Segmentation1.TokenizerMapper.class);
        job.setCombinerClass(Segmentation1.CountReducer.class);
        job.setReducerClass(Segmentation1.CountReducer.class);
        // job.setInputFormatClass(InputFormat.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path("hdfs://localhost:9000/tokenizer/input/"));
        FileOutputFormat.setOutputPath(job, new Path("hdfs://localhost:9000/tokenizer/output/t2"));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
