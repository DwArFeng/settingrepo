package com.dwarfeng.settingrepo.impl.service.telqos;

import com.dwarfeng.settingrepo.stack.service.SupportQosService;
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

@TelqosCommand
public class SupportCommand extends CliCommand {

    @SuppressWarnings({"SpellCheckingInspection", "GrazieInspectionRunner", "RedundantSuppression"})
    private static final String IDENTITY = "support";

    // region 指令选项

    @SuppressWarnings({"SpellCheckingInspection", "GrazieInspectionRunner", "RedundantSuppression"})
    private static final String COMMAND_OPTION_RESET_FORMATTER = "rf";
    private static final String COMMAND_OPTION_RESET_FORMATTER_LONG_OPT = "reset-formatter";
    @SuppressWarnings({"SpellCheckingInspection", "GrazieInspectionRunner", "RedundantSuppression"})
    private static final String COMMAND_OPTION_RESET_WARMUP_DRIVER = "rwd";
    private static final String COMMAND_OPTION_RESET_WARMUP_DRIVER_LONG_OPT = "reset-warmup-driver";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_RESET_FORMATTER,
            COMMAND_OPTION_RESET_WARMUP_DRIVER
    };

    // endregion

    private final SupportQosService supportQosService;

    public SupportCommand(SupportQosService supportQosService) {
        super(IDENTITY);
        this.supportQosService = supportQosService;
    }

    @Override
    protected DescriptionProvider provideDescriptionProvider() {
        return context -> "支持操作";
    }

    @Override
    protected CliSyntaxProvider provideCliSyntaxProvider() {
        return this::cliSyntaxProvider;
    }

    private String cliSyntaxProvider(CommandDescriptor.Context context) throws Exception {
        String identity = context.getRuntimeIdentity();
        String[] patterns = new String[]{
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_RESET_FORMATTER),
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_RESET_WARMUP_DRIVER)
        };
        return CliCommandUtil.cliSyntax(patterns);
    }

    @Override
    protected List<Option> provideOptions() {
        List<Option> list = new ArrayList<>();
        list.add(
                Option.builder(COMMAND_OPTION_RESET_FORMATTER).longOpt(COMMAND_OPTION_RESET_FORMATTER_LONG_OPT)
                        .optionalArg(true).hasArg(false).desc("重置格式化器支持").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_RESET_WARMUP_DRIVER).longOpt(COMMAND_OPTION_RESET_WARMUP_DRIVER_LONG_OPT)
                        .optionalArg(true).hasArg(false).desc("重置预热驱动器支持").build()
        );
        return list;
    }

    @Override
    protected void executeWithCmd(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        Pair<String, Integer> pair = CliCommandUtil.analyseCommand(cmd, COMMAND_OPTION_ARRAY);
        if (pair.getRight() != 1) {
            context.sendMessage(CliCommandUtil.optionMismatchMessage(COMMAND_OPTION_ARRAY));
            context.sendMessage(context.getCommandManual(context.getRuntimeIdentity()));
            return;
        }
        switch (pair.getLeft()) {
            case COMMAND_OPTION_RESET_FORMATTER:
                supportQosService.resetFormatter();
                context.sendMessage("重置格式化器支持成功。");
                break;
            case COMMAND_OPTION_RESET_WARMUP_DRIVER:
                supportQosService.resetWarmupDriver();
                context.sendMessage("重置预热驱动器支持成功。");
                break;
            default:
                throw new IllegalStateException("不应该执行到此处, 请联系开发人员");
        }
    }
}
