package mapstrut;

import domain.SubSource;
import domain.SubTarget;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Description: ${description}
 * @Author: lina.feng
 * @Date: 2020/3/20 12:26
 * @Version: 1.0
 */
@Mapper
public interface SubBeanMapper {
    public SubBeanMapper instance = Mappers.getMapper(SubBeanMapper.class);

    public SubTarget subSource2subTarget(SubSource subSource);
}

