package cst.zju.alexsun.mr1.final_task;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class PairData implements WritableComparable{
    private Integer hour;
    private String keyword;
    public PairData(Integer hour, String keyword){
        this.hour = hour;
        this.keyword = keyword;
    }
    public boolean isSame(PairData o){
        if(o.hour == this.hour && o.keyword == this.keyword)
            return true;
        else
            return false;
    }

    public int compareTo(Object o) {
        return 0;
    }

    public void write(DataOutput dataOutput) throws IOException {

    }

    public void readFields(DataInput dataInput) throws IOException {

    }
}
