package top.jiangyixin.ares.admin.pojo.vo;

/**
 * 缓存信息 VO
 *
 * @author jiangyixin
 * @version 1.0
 * @date 2020/12/19
 */
public class CacheInfoVO {
    private String realKey;
    private String javaType;
    private int length;
    private String toString;
    private String json;

    public String getRealKey() {
        return realKey;
    }

    public void setRealKey(String realKey) {
        this.realKey = realKey;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getToString() {
        return toString;
    }

    public void setToString(String toString) {
        this.toString = toString;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    @Override
    public String toString() {
        return "CacheInfoVO{" +
                "realKey='" + realKey + '\'' +
                ", javaType='" + javaType + '\'' +
                ", length='" + length + '\'' +
                ", toString='" + toString + '\'' +
                ", json='" + json + '\'' +
                '}';
    }
}
