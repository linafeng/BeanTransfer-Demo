package mapstrut.base;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Collection;
import java.util.List;

/**
 * @author jack cooper
 * <p></p>
 * 基础转换类，提供基本的几个方法，直接继承就可以，如果有需要写Mappings的写在 {@link #to(Object)} 方法上
 * 并且接口类上一定要加上 {@link org.mapstruct.Mapper} 注解
 * 默认注解，需要单独定义 如 CategoryMapper MAPPER = Mappers.getMapper(CategoryMapper.class); 以此进行实例创建和调用
 * 或者如下
 *
 * @Mapper(componentModel = "spring") 此注解可通过spring进行注入。
 */
public interface BasicObjectMapper<SOURCE, TARGET> {
    /**
     * 如有需要自定义该注解即可
     * 例如：
     *
     * @Mappings({
     * @Mapping(source = "code", target = "categoryCode"),
     * @Mapping(source = "name", target = "categoryName")
     * })
     * <p></p>
     * 重写此注解时一定要注意 返回值（TARGET） 和 参数（SOURCE） 的顺序
     */
    @Mappings({
            @Mapping(source = "line1",target = "seq")
    })
    @InheritConfiguration
    TARGET to(SOURCE source);

    @InheritConfiguration
    List<TARGET> to(Collection<SOURCE> source);

    /**
     * 同名参考配置翻转
     * 默认不用配置name
     * 如果有多个方法符合翻转条件需要配置指定方法名
     * @param source
     * @return
     */
    @InheritInverseConfiguration(name = "to")
    SOURCE from(TARGET source);

    @InheritInverseConfiguration(name = "to")
    List<SOURCE> from(Collection<TARGET> source);
}