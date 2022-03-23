package com.dwarfeng.settingrepo.impl.service.telqos;

import com.dwarfeng.settingrepo.stack.handler.Formatter;
import com.dwarfeng.settingrepo.stack.service.FormatQosService;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.stack.command.Context;
import com.dwarfeng.springtelqos.stack.exception.TelqosException;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class FormatLocalCacheCommand extends CliCommand {

    private static final String IDENTITY = "flc";
    private static final String DESCRIPTION = "本地缓存操作";
    private static final String CMD_LINE_SYNTAX_C = "flc -c";
    private static final String CMD_LINE_SYNTAX_S = "flc -s setting-category-id";
    private static final String CMD_LINE_SYNTAX = CMD_LINE_SYNTAX_C + System.lineSeparator() + CMD_LINE_SYNTAX_S;
    private static final String COMMAND_OPTION_S = "s";
    private static final String COMMAND_OPTION_C = "c";

    private final FormatQosService formatQosService;

    public FormatLocalCacheCommand(FormatQosService formatQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.formatQosService = formatQosService;
    }

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder(COMMAND_OPTION_C).optionalArg(true).hasArg(false).desc("清除缓存").build());
        list.add(Option.builder(COMMAND_OPTION_S).optionalArg(true).hasArg(true).argName("setting-category-id")
                .desc("查看指定设置类别的详细信息，如果本地缓存中不存在，则尝试抓取").build());
        return list;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    protected void executeWithCmd(Context context, CommandLine cmd) throws TelqosException {
        try {
            Pair<String, Integer> pair = analyseLcCommand(cmd);
            if (pair.getRight() != 1) {
                context.sendMessage(
                        String.format("下列选项必须且只能含有一个: -%s -%s", COMMAND_OPTION_C, COMMAND_OPTION_S)
                );
                context.sendMessage(CMD_LINE_SYNTAX);
                return;
            }
            switch (pair.getLeft()) {
                case COMMAND_OPTION_C:
                    handleC(context);
                    break;
                case COMMAND_OPTION_S:
                    handleS(context, cmd);
                    break;
            }
        } catch (Exception e) {
            throw new TelqosException(e);
        }
    }

    private Pair<String, Integer> analyseLcCommand(CommandLine cmd) {
        int i = 0;
        String subCmd = null;
        if (cmd.hasOption(COMMAND_OPTION_C)) {
            i++;
            subCmd = COMMAND_OPTION_C;
        }
        if (cmd.hasOption(COMMAND_OPTION_S)) {
            i++;
            subCmd = COMMAND_OPTION_S;
        }
        return Pair.of(subCmd, i);
    }

    private void handleC(Context context) throws Exception {
        formatQosService.clearLocalCache();
        context.sendMessage("缓存已清空");
    }

    @SuppressWarnings("DuplicatedCode")
    private void handleS(Context context, CommandLine cmd) throws Exception {
        String settingCategoryId = cmd.getOptionValue(COMMAND_OPTION_S);
        Formatter formatter = formatQosService.getFormatter(new StringIdKey(settingCategoryId));
        if (Objects.isNull(formatter)) {
            context.sendMessage("not exists!");
            return;
        }
        context.sendMessage(String.format("formatter: %s", formatter));
    }
}
