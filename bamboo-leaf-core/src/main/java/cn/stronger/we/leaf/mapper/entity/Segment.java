package cn.stronger.we.leaf.mapper.entity;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @class Segment
 * @department Platform Center
 * @date 2023-08-15 16:43
 */
public class Segment {
    private AtomicLong value = new AtomicLong(0);
    private volatile long max;
    private volatile long step;
    private volatile long incr;
    private final SegmentBuffer buffer;

    public Segment(SegmentBuffer buffer) {
        this.buffer = buffer;
    }

    public AtomicLong getValue() {
        return value;
    }

    public long getIncr() {
        return incr;
    }

    public void setIncr(long incr) {
        this.incr = incr;
    }

    public void setValue(AtomicLong value) {
        this.value = value;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        this.max = max;
    }

    public long getStep() {
        return step;
    }

    public void setStep(long step) {
        this.step = step;
    }

    public SegmentBuffer getBuffer() {
        return buffer;
    }

    public long getIdle() {
        return this.getMax() - getValue().get();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Segment(");
        sb.append("value:");
        sb.append(value);
        sb.append(",max:");
        sb.append(max);
        sb.append(",step:");
        sb.append(step);
        sb.append(")");
        return sb.toString();
    }
}
