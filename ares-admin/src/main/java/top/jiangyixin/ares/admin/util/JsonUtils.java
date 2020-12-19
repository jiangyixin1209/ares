package top.jiangyixin.ares.admin.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * json操作工具类
 *
 * @author jiangyixin
 * @version 1.0
 * @date 2020/12/19
 */
public class JsonUtils {
    private final static Logger logger = LoggerFactory.getLogger(JsonUtils.class);
    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static ObjectMapper getInstance() {
        return OBJECT_MAPPER;
    }

    /**
     * 对象 -》 json
     * @param obj       对象
     * @return          json字符串
     */
    public static String writeValueAsString(Object obj) {
        try {
            return getInstance().writeValueAsString(obj);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * json -》 对象
     * @param json      json字符串
     * @param clazz     对象类
     * @return          对象
     */
    public static <T> T readValue(String json, Class<T> clazz) {
        try {
            return getInstance().readValue(json, clazz);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * json -》 泛型对象
     * @param json      json字符串
     * @param clazz     对象类
     * @return          对象
     */
    public static <T> T readValueRefer(String json, Class<T> clazz) {
        try {
            return getInstance().readValue(json, new TypeReference<T>() {});
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
