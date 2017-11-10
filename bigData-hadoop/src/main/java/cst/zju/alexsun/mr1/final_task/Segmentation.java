package cst.zju.alexsun.mr1.final_task;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;
import java.util.*;

/**
 * 分词工具类
 */
public class Segmentation {

    public static void stanfordToolTest(String text){

        Properties properties = new Properties();
        properties.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
        Annotation annotation = new Annotation(text);
        pipeline.annotate(annotation);
        List<CoreMap> sentences = annotation.get(CoreAnnotations
                .SentencesAnnotation.class);
        for (CoreMap sentence : sentences){
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)){
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                System.out.println(word);
            }
        }
    }

    /**
     * jieba分词工具测试
     */
    public static void jiebaTest(){

        JiebaSegmenter segmentation = new JiebaSegmenter();
        String sentence = "中国人民大学是中国人民的大学";
        List<SegToken> tokens = segmentation.process(sentence, JiebaSegmenter.SegMode.SEARCH);
        for(SegToken token : tokens){
            System.out.println(token.word);
        }
    }

    /**
     * Mapper 分词,
     * 输入的数据格式为 "hour \t keyword"
     * 事先将搜索数据处理为只包含提交搜索的时间（精确到hour）和关键词
     */
    public static class TokenizerMapper
            extends Mapper<Object, Text,HourWordPair,IntWritable> {
        private Text word = new Text();
        private HashSet<String> stopWords;
        private JiebaSegmenter jiebaSegmenter = new JiebaSegmenter();
        private HashMap<String,Integer> map = new HashMap<String, Integer>();

        public void map(Object key, Text value, Context context
        ) throws IOException, InterruptedException {
          String line = value.toString();
          String info[] = line.split("\t");
          IntWritable one = new IntWritable(1);
          IntWritable hour = new IntWritable(Integer.parseInt(info[0]));
          List<SegToken> tokens = jiebaSegmenter.process(info[1],
                  JiebaSegmenter.SegMode.SEARCH);
          for(SegToken token : tokens){
              String word = token.word;
              if(stopWords.contains(word))
                  continue;
              HourWordPair hourWordPair = new HourWordPair(hour.get(), word);
              context.write(hourWordPair, one);
          }

        }
    }

    /**
     *  Reducer
     */
    public static class CountReducer
            extends Reducer<HourWordPair,IntWritable,HourWordPair,IntWritable> {
        private IntWritable result = new IntWritable();
        public void reduce(HourWordPair key, Iterable<IntWritable> values,
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

    public static void main(String args[]){
        // jiebaTest();

    }

}
