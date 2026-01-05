package com.dwarfeng.settingrepo.impl.service.telqos;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.settingrepo.sdk.bean.dto.WebInputTextNodeInspectInfo;
import com.dwarfeng.settingrepo.sdk.bean.dto.WebInputTextNodePutInfo;
import com.dwarfeng.settingrepo.stack.bean.dto.TextNodeInspectInfo;
import com.dwarfeng.settingrepo.stack.bean.dto.TextNodeInspectResult;
import com.dwarfeng.settingrepo.stack.bean.dto.TextNodePutInfo;
import com.dwarfeng.settingrepo.stack.service.TextNodeQosService;
import com.dwarfeng.springtelqos.node.config.TelqosCommand;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.stack.command.Context;
import com.dwarfeng.springtelqos.stack.exception.TelqosException;
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

    private static final String COMMAND_OPTION_INSPECT = "inspect";
    private static final String COMMAND_OPTION_PUT = "put";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_INSPECT,
            COMMAND_OPTION_PUT
    };

    private static final String COMMAND_OPTION_JSON = "json";
    private static final String COMMAND_OPTION_JSON_FILE = "jf";
    private static final String COMMAND_OPTION_JSON_FILE_LONG_OPT = "json-file";

    @SuppressWarnings("SpellCheckingInspection")
    private static final String IDENTITY = "txtnode";
    private static final String DESCRIPTION = "文本节点操作服务";

    private static final String CMD_LINE_SYNTAX_INSPECT = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_INSPECT) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_PUT = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_PUT) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";

    private static final String[] CMD_LINE_ARRAY = new String[]{
            CMD_LINE_SYNTAX_INSPECT,
            CMD_LINE_SYNTAX_PUT
    };

    private static final String CMD_LINE_SYNTAX = CommandUtil.syntax(CMD_LINE_ARRAY);

    private final TextNodeQosService textNodeQosService;

    public TextNodeCommand(TextNodeQosService textNodeQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.textNodeQosService = textNodeQosService;
    }

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder(COMMAND_OPTION_INSPECT).desc("查看指定的文本节点").build());
        list.add(Option.builder(COMMAND_OPTION_PUT).desc("向指定的文本节点中放入指定的信息").build());
        list.add(
                Option.builder(COMMAND_OPTION_JSON).desc("JSON 字符串").hasArg().type(String.class).build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_JSON_FILE).longOpt(COMMAND_OPTION_JSON_FILE_LONG_OPT).desc("JSON 文件")
                        .hasArg().type(File.class).build()
        );
        return list;
    }

    @Override
    protected void executeWithCmd(Context context, CommandLine cmd) throws TelqosException {
        try {
            Pair<String, Integer> pair = CommandUtil.analyseCommand(cmd, COMMAND_OPTION_ARRAY);
            if (pair.getRight() != 1) {
                context.sendMessage(CommandUtil.optionMismatchMessage(COMMAND_OPTION_ARRAY));
                context.sendMessage(super.cmdLineSyntax);
                return;
            }
            switch (pair.getLeft()) {
                case COMMAND_OPTION_INSPECT:
                    handleInspect(context, cmd);
                    break;
                case COMMAND_OPTION_PUT:
                    handlePut(context, cmd);
                    break;
            }
        } catch (Exception e) {
            throw new TelqosException(e);
        }
    }

    private void handleInspect(Context context, CommandLine cmd) throws Exception {
        TextNodeInspectInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 TextNodeInspectInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputTextNodeInspectInfo.toStackBean(
                    JSON.parseObject(json, WebInputTextNodeInspectInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 SettingNodeExistsInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputTextNodeInspectInfo.toStackBean(
                        JSON.parseObject(in, WebInputTextNodeInspectInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 查看指定的文本节点。
        TextNodeInspectResult result = textNodeQosService.inspect(info);

        // 输出结果。
        if (Objects.isNull(result)) {
            context.sendMessage("查看结果: null");
        } else {
            context.sendMessage("查看结果: ");
            context.sendMessage("  value: " + result.getValue());
        }
    }

    private void handlePut(Context context, CommandLine cmd) throws Exception {
        TextNodePutInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 TextNodePutInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputTextNodePutInfo.toStackBean(
                    JSON.parseObject(json, WebInputTextNodePutInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 TextNodePutInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputTextNodePutInfo.toStackBean(
                        JSON.parseObject(in, WebInputTextNodePutInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 向指定的文本节点中放入指定的信息。
        textNodeQosService.put(info);

        // 输出结果。
        context.sendMessage("放入成功");
    }
}

