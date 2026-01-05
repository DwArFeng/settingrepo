package com.dwarfeng.settingrepo.impl.service.telqos;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.settingrepo.sdk.bean.dto.WebInputSettingNodeExistsInfo;
import com.dwarfeng.settingrepo.sdk.bean.dto.WebInputSettingNodeInitInfo;
import com.dwarfeng.settingrepo.sdk.bean.dto.WebInputSettingNodeInspectInfo;
import com.dwarfeng.settingrepo.sdk.bean.dto.WebInputSettingNodeRemoveInfo;
import com.dwarfeng.settingrepo.sdk.util.Constants;
import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.service.SettingNodeQosService;
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
public class SettingNodeCommand extends CliCommand {

    private static final String COMMAND_OPTION_EXISTS = "exists";
    private static final String COMMAND_OPTION_INSPECT = "inspect";
    private static final String COMMAND_OPTION_INIT = "init";
    private static final String COMMAND_OPTION_REMOVE = "remove";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_EXISTS,
            COMMAND_OPTION_INSPECT,
            COMMAND_OPTION_INIT,
            COMMAND_OPTION_REMOVE
    };

    private static final String COMMAND_OPTION_JSON = "json";
    private static final String COMMAND_OPTION_JSON_FILE = "jf";
    private static final String COMMAND_OPTION_JSON_FILE_LONG_OPT = "json-file";

    @SuppressWarnings("SpellCheckingInspection")
    private static final String IDENTITY = "settingnode";
    private static final String DESCRIPTION = "设置节点操作服务";

    private static final String CMD_LINE_SYNTAX_EXISTS = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_EXISTS) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_INSPECT = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_INSPECT) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_INIT = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_INIT) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_REMOVE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_REMOVE) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";

    private static final String[] CMD_LINE_ARRAY = new String[]{
            CMD_LINE_SYNTAX_EXISTS,
            CMD_LINE_SYNTAX_INSPECT,
            CMD_LINE_SYNTAX_INIT,
            CMD_LINE_SYNTAX_REMOVE
    };

    private static final String CMD_LINE_SYNTAX = CommandUtil.syntax(CMD_LINE_ARRAY);

    private final SettingNodeQosService settingNodeQosService;

    public SettingNodeCommand(SettingNodeQosService settingNodeQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.settingNodeQosService = settingNodeQosService;
    }

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder(COMMAND_OPTION_EXISTS).desc("判断指定的设置节点是否存在").build());
        list.add(Option.builder(COMMAND_OPTION_INSPECT).desc("查看指定的设置节点").build());
        list.add(Option.builder(COMMAND_OPTION_INIT).desc("初始化指定的设置节点").build());
        list.add(Option.builder(COMMAND_OPTION_REMOVE).desc("移除指定的设置节点").build());
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
                case COMMAND_OPTION_EXISTS:
                    handleExists(context, cmd);
                    break;
                case COMMAND_OPTION_INSPECT:
                    handleInspect(context, cmd);
                    break;
                case COMMAND_OPTION_INIT:
                    handleInit(context, cmd);
                    break;
                case COMMAND_OPTION_REMOVE:
                    handleRemove(context, cmd);
                    break;
            }
        } catch (Exception e) {
            throw new TelqosException(e);
        }
    }

    private void handleExists(Context context, CommandLine cmd) throws Exception {
        SettingNodeExistsInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 SettingNodeExistsInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputSettingNodeExistsInfo.toStackBean(
                    JSON.parseObject(json, WebInputSettingNodeExistsInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 SettingNodeExistsInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputSettingNodeExistsInfo.toStackBean(
                        JSON.parseObject(in, WebInputSettingNodeExistsInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 查询设置节点是否存在。
        SettingNodeExistsResult result = settingNodeQosService.exists(info);

        // 输出结果。
        context.sendMessage("节点存在: " + result.isExists());
    }

    private void handleInspect(Context context, CommandLine cmd) throws Exception {
        SettingNodeInspectInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 SettingNodeInspectInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputSettingNodeInspectInfo.toStackBean(
                    JSON.parseObject(json, WebInputSettingNodeInspectInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 SettingNodeInspectInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputSettingNodeInspectInfo.toStackBean(
                        JSON.parseObject(in, WebInputSettingNodeInspectInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 查询设置节点。
        SettingNodeInspectResult result = settingNodeQosService.inspect(info);

        // 输出结果。
        if (Objects.isNull(result)) {
            context.sendMessage("查询结果: null");
        } else {
            context.sendMessage("查询结果: ");
            context.sendMessage("  type: " + result.getType() + " (" + formatSettingNodeType(result.getType()) + ")");
            context.sendMessage("  lastModifiedDate: " + result.getLastModifiedDate());
            context.sendMessage("  remark: " + result.getRemark());
        }
    }

    private String formatSettingNodeType(int type) {
        switch (type) {
            case Constants.SETTING_NODE_TYPE_TEXT:
                return "文本";
            case Constants.SETTING_NODE_TYPE_LONG_TEXT:
                return "长文本";
            case Constants.SETTING_NODE_TYPE_IMAGE:
                return "图片";
            case Constants.SETTING_NODE_TYPE_IMAGE_LIST:
                return "图片列表";
            default:
                return "未知";
        }
    }

    private void handleInit(Context context, CommandLine cmd) throws Exception {
        SettingNodeInitInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 SettingNodeInitInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputSettingNodeInitInfo.toStackBean(
                    JSON.parseObject(json, WebInputSettingNodeInitInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 SettingNodeInitInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputSettingNodeInitInfo.toStackBean(
                        JSON.parseObject(in, WebInputSettingNodeInitInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 初始化设置节点。
        settingNodeQosService.init(info);

        // 输出结果。
        context.sendMessage("初始化成功");
    }

    private void handleRemove(Context context, CommandLine cmd) throws Exception {
        SettingNodeRemoveInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 SettingNodeRemoveInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputSettingNodeRemoveInfo.toStackBean(
                    JSON.parseObject(json, WebInputSettingNodeRemoveInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 SettingNodeRemoveInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputSettingNodeRemoveInfo.toStackBean(
                        JSON.parseObject(in, WebInputSettingNodeRemoveInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 删除设置节点。
        settingNodeQosService.remove(info);

        // 输出结果。
        context.sendMessage("删除成功");
    }
}
