import com.dwarfeng.settingrepo.impl.handler.formatter.GroovyFormatterRegistry
import com.dwarfeng.settingrepo.stack.bean.entity.SettingCategory
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey

/**
 * 示例格式化器。
 *
 * <p>
 * 将输入的所有参数的 {@link Object#toString()} 返回的字符串简单的拼接在一起，形成的字符串作为主键的字符串返回。
 */
@SuppressWarnings("GrPackage")
class ExampleFormatterProcessor implements GroovyFormatterRegistry.Processor {

    @Override
    StringIdKey format(SettingCategory settingCategory, Object[] args) throws Exception {
        StringBuilder sb = new StringBuilder()
        for (Object arg : args) {
            sb.append(arg)
        }
        return new StringIdKey(sb.toString())
    }
}
