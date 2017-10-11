package cst.zju.alexsun.mr1.mr2;

import org.apache.hadoop.io.WritableComparable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FlowBean implements WritableComparable<FlowBean>{
    long upFlow;
    long downFlow;
    long sumFlow;

    public FlowBean(){}
    public FlowBean(long up, long down){
        super();
        this.downFlow = down;
        this.upFlow = up;
        this.sumFlow = downFlow + upFlow;
    }

    public long getSumFlow(){
        return sumFlow;
    }
    public long getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(long downFlow) {
        this.downFlow = downFlow;
    }

    public long getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(long upFlow) {
        this.upFlow = upFlow;
    }

    public int compareTo(FlowBean o) {
        return sumFlow > o.getSumFlow()?-1:1;
    }

    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(upFlow);
        dataOutput.writeLong(downFlow);
        dataOutput.writeLong(sumFlow);
    }

    public void readFields(DataInput dataInput) throws IOException {
        upFlow = dataInput.readLong();
        downFlow = dataInput.readLong();
        sumFlow = dataInput.readLong();
    }

    @Override
    public String toString() {
        return upFlow + "\t" + downFlow + "\t" + sumFlow;
    }
}
