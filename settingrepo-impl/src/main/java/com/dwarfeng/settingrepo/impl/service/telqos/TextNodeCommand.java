package com.dwarfeng.settingrepo.impl.service.telqos;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.settingrepo.sdk.bean.dto.WebInputTextNodeInspectInfo;
import com.dwarfeng.settingrepo.sdk.bean.dto.WebInputTextNodePutInfo;
import com.dwarfeng.settingrepo.stack.bean.dto.TextNodeInspectInfo;
import com.dwarfeng.settingrepo.stack.bean.dto.TextNodeInspectResult;
import com.dwarfeng.settingrepo.stack.bean.dto.TextNodePutInfo;
import com.dwarfeng.settingrepo.stack.service.TextNodeQosService;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.sdk.configuration.TelqosCommand;
import com.dwarfeng.springtelqos.sdk.util.CliCommandUtil;
import com.dwarfeng.springtelqos.stack.command.CommandDescriptor;
import com.dwarfeng.springtelqos.stack.command.CommandExecutor;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@TelqosCommand
public class TextNodeCommand extends CliCommand {

    @SuppressWarnings({"SpellCheckingInspection", "GrazieInspectionRunner", "RedundantSuppression"})
    private static final String IDENTITY = "txtnode";

    // region 指令选项

    private static final String COMMAND_OPTION_INSPECT = "inspect";
    private static final String COMMAND_OPTION_PUT = "put";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_INSPECT,
            COMMAND_OPTION_PUT
    };

    private static final String COMMAND_SUB_OPTION_JSON = "json";
    private static final String COMMAND_SUB_OPTION_JSON_FILE = "jf";
    private static final String COMMAND_SUB_OPTION_JSON_FILE_LONG_OPT = "json-file";

    // endregion

    private final TextNodeQosService textNodeQosService;

    public TextNodeCommand(TextNodeQosService textNodeQosService) {
        super(IDENTITY);
        this.textNodeQosService = textNodeQosService;
    }

    @Override
    protected DescriptionProvider provideDescriptionProvider() {
        return context -> "文本节点操作服务";
    }

    @Override
    protected CliSyntaxProvider provideCliSyntaxProvider() {
        return this::cliSyntaxProvider;
    }

    private String cliSyntaxProvider(CommandDescriptor.Context context) throws Exception {
        String identity = context.getRuntimeIdentity();
        String[] patterns = new String[]{
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_INSPECT) +
                        " [" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] " +
                        "[" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_PUT) +
                        " [" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] " +
                        "[" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]"
        };
        return CliCommandUtil.cliSyntax(patterns);
    }

    @Override
    protected List<Option> provideOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder(COMMAND_OPTION_INSPECT).optionalArg(true).hasArg(false).desc("查看指定的文本节点").build());
        list.add(Option.builder(COMMAND_OPTION_PUT).optionalArg(true).hasArg(false).desc("向指定的文本节点中放入指定的信息").build());
        list.add(Option.builder(COMMAND_SUB_OPTION_JSON).hasArg(true).type(String.class).desc("JSON 字符串").build());
        list.add(Option.builder(COMMAND_SUB_OPTION_JSON_FILE).longOpt(COMMAND_SUB_OPTION_JSON_FILE_LONG_OPT)
                .hasArg(true).type(File.class).desc("JSON 文件").build());
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
            case COMMAND_OPTION_INSPECT:
                handleInspect(context, cmd);
                break;
            case COMMAND_OPTION_PUT:
                handlePut(context, cmd);
                break;
            default:
                throw new IllegalStateException("不应该执行到此处, 请联系开发人员");
        }
    }

    private void handleInspect(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        TextNodeInspectInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputTextNodeInspectInfo.toStackBean(JSON.parseObject(json, WebInputTextNodeInspectInfo.class));
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputTextNodeInspectInfo.toStackBean(
                        JSON.parseObject(in, WebInputTextNodeInspectInfo.class)
                );
            }
        } else {
            throw new UnsupportedOperationException("not supported yet");
        }

        TextNodeInspectResult result = textNodeQosService.inspect(info);
        if (Objects.isNull(result)) {
            context.sendMessage("查看结果: null");
            return;
        }
        context.sendMessage("查看结果: ");
        context.sendMessage("  value: " + result.getValue());
    }

    private void handlePut(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        TextNodePutInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputTextNodePutInfo.toStackBean(JSON.parseObject(json, WebInputTextNodePutInfo.class));
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputTextNodePutInfo.toStackBean(JSON.parseObject(in, WebInputTextNodePutInfo.class));
            }
        } else {
            throw new UnsupportedOperationException("not supported yet");
        }

        textNodeQosService.put(info);
        context.sendMessage("放入成功");
    }
}
