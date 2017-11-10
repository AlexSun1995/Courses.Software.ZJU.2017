package cst.zju.alexsun.mr1.final_task;

import org.apache.hadoop.io.WritableComparable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class HourWordPair implements WritableComparable<HourWordPair> {
    private int hour;
    private String word;

    public HourWordPair(int hour, String word){
        this.hour = hour;
        this.word = word;
    }

    public int compareTo(HourWordPair o) {
        if(this.hour <= o.hour )
            return -1;
        else
            return 1;
    }

    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(this.hour);
        dataOutput.writeChars(this.word);
    }

    public void readFields(DataInput dataInput) throws IOException {
        this.hour = dataInput.readInt();
        this.word = dataInput.readUTF();
    }
}
