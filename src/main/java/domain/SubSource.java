package domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description: ${description}
 * @Author: lina.feng
 * @Date: 2020/3/20 12:04
 * @Version: 1.0
 */
@Data
@Accessors(chain = true)
public class SubSource {
    private String result;
    private String name;

}
