import com.dwarfeng.settingrepo.impl.handler.formatter.GroovyFormatterRegistry
import com.dwarfeng.settingrepo.stack.bean.entity.SettingCategory
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey

/**
 * 测试用格式化器。
 */
@SuppressWarnings("GrPackage")
class TestFormatterProcessor implements GroovyFormatterRegistry.Processor {

    @Override
    StringIdKey format(SettingCategory settingCategory, String[] args) throws Exception {
        StringBuilder sb = new StringBuilder()
        for (int i = 0; i < args.length; i++) {
            String arg = args[i]
            sb.append(arg)
            if (i < args.length - 1) {
                sb.append('.')
            }
        }
        return new StringIdKey(sb.toString())
    }
}
