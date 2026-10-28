package com.dwarfeng.settingrepo.impl.service.telqos;

import com.dwarfeng.settingrepo.stack.service.WarmupDriveQosService;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.sdk.configuration.TelqosCommand;
import com.dwarfeng.springtelqos.sdk.util.CliCommandUtil;
import com.dwarfeng.springtelqos.stack.command.CommandDescriptor;
import com.dwarfeng.springtelqos.stack.command.CommandExecutor;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * 预热驱动处理器操作命令。
 *
 * <p>
 * 该命令用于控制内层调度器、查看本地缓存以及立即执行一次预热。
 * 选项名采用无连字符的短名称，便于 Telqos 交互输入。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
@TelqosCommand
public class WarmupDriveCommand extends CliCommand {

    @SuppressWarnings({"SpellCheckingInspection", "GrazieInspectionRunner", "RedundantSuppression"})
    private static final String IDENTITY = "warmupdrive";

    // region 指令选项

    private static final String COMMAND_OPTION_STATUS = "status";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_STATUS
    };

    // endregion

    private final WarmupDriveQosService warmupDriveQosService;

    public WarmupDriveCommand(WarmupDriveQosService warmupDriveQosService) {
        super(IDENTITY);
        this.warmupDriveQosService = warmupDriveQosService;
    }

    @Override
    protected DescriptionProvider provideDescriptionProvider() {
        return context -> "预热驱动处理器操作/查看";
    }

    @Override
    protected CliSyntaxProvider provideCliSyntaxProvider() {
        return this::cliSyntaxProvider;
    }

    private String cliSyntaxProvider(CommandDescriptor.Context context) throws Exception {
        String identity = context.getRuntimeIdentity();
        String[] patterns = new String[]{
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_STATUS)
        };
        return CliCommandUtil.cliSyntax(patterns);
    }

    @Override
    protected List<Option> provideOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder(COMMAND_OPTION_STATUS).optionalArg(true).hasArg(false).desc("查看预热驱动处理器状态").build());
        return list;
    }

    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    @Override
    protected void executeWithCmd(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        Pair<String, Integer> pair = CliCommandUtil.analyseCommand(cmd, COMMAND_OPTION_ARRAY);
        if (pair.getRight() != 1) {
            context.sendMessage(CliCommandUtil.optionMismatchMessage(COMMAND_OPTION_ARRAY));
            context.sendMessage(context.getCommandManual(context.getRuntimeIdentity()));
            return;
        }
        switch (pair.getLeft()) {
            case COMMAND_OPTION_STATUS:
                printStatus(context);
                break;
            default:
                throw new IllegalStateException("不应该执行到此处, 请联系开发人员");
        }
    }

    private void printStatus(CommandExecutor.Context context) throws Exception {
        boolean startedFlag = warmupDriveQosService.isStarted();

        context.sendMessage(String.format("started: %b.", startedFlag));
    }
}
