/**
 * 统一处理接口
 */
interface Processor {
    String name();
    Object process(Object input);
}
/**
 * 波形
 */
class Waveform {
    private static long counter;
    private final long id = counter++;
    public String toString() { return "Waveform " + id; }
}
/**
 * 统一过滤处理接口
 */
class Filter {
    public String name() {
        return getClass().getSimpleName();
    }
    public Waveform process(Waveform input) { return input; }
}
/**
 * 高通滤波器
 */
class HighPass extends Filter {
    double cutoff;
    public HighPass(double cutoff) { this.cutoff = cutoff; }
    public Waveform process(Waveform input) { return input; }
}
/**
 * 低通滤波器
 */
class LowPass extends Filter {
    double cutoff;
    public LowPass(double cutoff) { this.cutoff = cutoff; }
    public Waveform process(Waveform input) {
        return input; // Dummy processing
    }
}
/**
 * 采用适配器方式把Filter转成Processor
 */
class FilterAdapter implements Processor {
    Filter filter;
    public FilterAdapter(Filter filter) {
        this.filter = filter;
    }
    public String name() { return filter.name(); }
    public Waveform process(Object input) {
        return filter.process((Waveform)input);
    }
}
public class FilterProcessor {
    //应用策略
    public static void process(Processor p, Object s) {
        System.out.println("Using Processor " + p.name());
        System.out.println(p.process(s));
    }
    public static void main(String[] args) {
        Waveform w = new Waveform();
        process(new FilterAdapter(new LowPass(1.0)), w);
        process(new FilterAdapter(new HighPass(2.0)), w);

    }
}