package top.jiangyixin.ares.core;

import java.text.MessageFormat;
import java.util.Arrays;

/**
 * TODO
 * @version 1.0
 * @author jiangyixin
 * @date 2020/12/18 下午5:02
 */
public class AresCacheKey {
	private String key;
	private String[] params;
	
	public AresCacheKey(String key, String... params) {
		this.key = key;
		this.params = params;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public String[] getParams() {
		return params;
	}
	
	public void setParams(String[] params) {
		this.params = params;
	}
	
	/**
	 * 获取渲染后的key
	 *  sys_{0}, 1 -> sys_1
	 * @return          real key
	 */
	public String getRealKey() {
		return MessageFormat.format(key, (Object[]) params);
	}
	
	@Override
	public String toString() {
		return "AresCacheKey{" +
				"key='" + key + '\'' +
				", params=" + Arrays.toString(params) +
				'}';
	}
}
