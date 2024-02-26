package toolkit.frame.utils.transforms;

/**
 * @author: Nocking
 * @date: 2024/2/26
 * @package: toolkit.frame.utils.transforms
 **/

import com.alibaba.fastjson.TypeReference;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import toolkit.frame.utils.ListTypeUtils;

import java.util.List;

@MappedTypes(List.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class StringTypeTransform extends ListTypeUtils<String> {
  // 将ListTypeUtils<T>（T为任意对象），具体为特定的对象String
  @Override
  protected TypeReference<List<String>> specificType() {
    return new TypeReference<List<String>>() {
    };
  }

}
