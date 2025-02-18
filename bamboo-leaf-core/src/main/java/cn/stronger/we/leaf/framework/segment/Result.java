package cn.stronger.we.leaf.framework.segment;

/**
 * @author qiang.w
 * @version 0.1.0
 * @description XXX用处
 * @class Result
 * @department Platform Center
 * @date 2023-08-15 16:50
 */
public class Result {
    private long id;
    private Status status;

    public Result() {

    }
    public Result(long id, Status status) {
        this.id = id;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Result{");
        sb.append("id=").append(id);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
