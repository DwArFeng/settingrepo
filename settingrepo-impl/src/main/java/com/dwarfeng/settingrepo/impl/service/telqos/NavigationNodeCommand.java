package com.dwarfeng.settingrepo.impl.service.telqos;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.settingrepo.sdk.bean.dto.*;
import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.service.NavigationNodeQosService;
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

@TelqosCommand
public class NavigationNodeCommand extends CliCommand {

    private static final String COMMAND_OPTION_SIZE = "size";
    private static final String COMMAND_OPTION_INSPECT = "inspect";
    private static final String COMMAND_OPTION_INSERT_ITEM = "ii";
    private static final String COMMAND_OPTION_INSERT_ITEM_LONG_OPT = "insert-item";
    private static final String COMMAND_OPTION_UPDATE_ITEM = "ui";
    private static final String COMMAND_OPTION_UPDATE_ITEM_LONG_OPT = "update-item";
    private static final String COMMAND_OPTION_REMOVE_ITEM = "ri";
    private static final String COMMAND_OPTION_REMOVE_ITEM_LONG_OPT = "remove-item";
    private static final String COMMAND_OPTION_FORMAT_INDEX = "fi";
    private static final String COMMAND_OPTION_FORMAT_INDEX_LONG_OPT = "format-index";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_SIZE,
            COMMAND_OPTION_INSPECT,
            COMMAND_OPTION_INSERT_ITEM,
            COMMAND_OPTION_UPDATE_ITEM,
            COMMAND_OPTION_REMOVE_ITEM,
            COMMAND_OPTION_FORMAT_INDEX
    };

    private static final String COMMAND_OPTION_JSON = "json";
    private static final String COMMAND_OPTION_JSON_FILE = "jf";
    private static final String COMMAND_OPTION_JSON_FILE_LONG_OPT = "json-file";

    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String IDENTITY = "navigationnode";
    private static final String DESCRIPTION = "导航节点操作服务";

    private static final String CMD_LINE_SYNTAX_SIZE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_SIZE) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_INSPECT = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_INSPECT) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_INSERT_ITEM = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_INSERT_ITEM) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_UPDATE_ITEM = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_UPDATE_ITEM) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_REMOVE_ITEM = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_REMOVE_ITEM) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_FORMAT_INDEX = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_FORMAT_INDEX) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";

    private static final String[] CMD_LINE_ARRAY = new String[]{
            CMD_LINE_SYNTAX_SIZE,
            CMD_LINE_SYNTAX_INSPECT,
            CMD_LINE_SYNTAX_INSERT_ITEM,
            CMD_LINE_SYNTAX_UPDATE_ITEM,
            CMD_LINE_SYNTAX_REMOVE_ITEM,
            CMD_LINE_SYNTAX_FORMAT_INDEX
    };

    private static final String CMD_LINE_SYNTAX = CommandUtil.syntax(CMD_LINE_ARRAY);

    private final NavigationNodeQosService navigationNodeQosService;

    public NavigationNodeCommand(NavigationNodeQosService navigationNodeQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.navigationNodeQosService = navigationNodeQosService;
    }

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder(COMMAND_OPTION_SIZE).desc("查看导航节点大小").build());
        list.add(Option.builder(COMMAND_OPTION_INSPECT).desc("查看导航节点").build());
        list.add(
                Option.builder(COMMAND_OPTION_INSERT_ITEM).longOpt(COMMAND_OPTION_INSERT_ITEM_LONG_OPT)
                        .desc("插入导航节点条目").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_UPDATE_ITEM).longOpt(COMMAND_OPTION_UPDATE_ITEM_LONG_OPT)
                        .desc("更新导航节点条目").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_REMOVE_ITEM).longOpt(COMMAND_OPTION_REMOVE_ITEM_LONG_OPT)
                        .desc("移除导航节点条目").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_FORMAT_INDEX).longOpt(COMMAND_OPTION_FORMAT_INDEX_LONG_OPT)
                        .desc("格式化导航节点索引").build()
        );
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
                case COMMAND_OPTION_SIZE:
                    handleSize(context, cmd);
                    break;
                case COMMAND_OPTION_INSPECT:
                    handleInspect(context, cmd);
                    break;
                case COMMAND_OPTION_INSERT_ITEM:
                    handleInsertItem(context, cmd);
                    break;
                case COMMAND_OPTION_UPDATE_ITEM:
                    handleUpdateItem(context, cmd);
                    break;
                case COMMAND_OPTION_REMOVE_ITEM:
                    handleRemoveItem(context, cmd);
                    break;
                case COMMAND_OPTION_FORMAT_INDEX:
                    handleFormatIndex(context, cmd);
                    break;
            }
        } catch (Exception e) {
            throw new TelqosException(e);
        }
    }

    private void handleSize(Context context, CommandLine cmd) throws Exception {
        NavigationNodeSizeInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 NavigationNodeSizeInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputNavigationNodeSizeInfo.toStackBean(
                    JSON.parseObject(json, WebInputNavigationNodeSizeInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 NavigationNodeSizeInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputNavigationNodeSizeInfo.toStackBean(
                        JSON.parseObject(in, WebInputNavigationNodeSizeInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 查询导航节点大小。
        NavigationNodeSizeResult result = navigationNodeQosService.size(info);

        // 输出结果。
        if (result == null) {
            context.sendMessage("查询结果: null");
        } else {
            context.sendMessage("查询结果: ");
            context.sendMessage("  size: " + result.getSize());
        }
    }

    private void handleInspect(Context context, CommandLine cmd) throws Exception {
        NavigationNodeInspectInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 NavigationNodeInspectInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputNavigationNodeInspectInfo.toStackBean(
                    JSON.parseObject(json, WebInputNavigationNodeInspectInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 NavigationNodeInspectInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputNavigationNodeInspectInfo.toStackBean(
                        JSON.parseObject(in, WebInputNavigationNodeInspectInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 查看导航节点。
        NavigationNodeInspectResult result = navigationNodeQosService.inspect(info);

        // 输出结果。
        if (result == null) {
            context.sendMessage("查看结果: null");
        } else {
            context.sendMessage("查看结果: ");
            FastJsonNavigationNodeInspectResult fastJsonResult = FastJsonNavigationNodeInspectResult.of(result);
            String jsonString = JSON.toJSONString(fastJsonResult, true);
            context.sendMessage(jsonString);
        }
    }

    private void handleInsertItem(Context context, CommandLine cmd) throws Exception {
        NavigationNodeItemInsertInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 NavigationNodeItemInsertInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputNavigationNodeItemInsertInfo.toStackBean(
                    JSON.parseObject(json, WebInputNavigationNodeItemInsertInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 NavigationNodeItemInsertInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputNavigationNodeItemInsertInfo.toStackBean(
                        JSON.parseObject(in, WebInputNavigationNodeItemInsertInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 插入导航节点条目。
        NavigationNodeItemInsertResult result = navigationNodeQosService.insertItem(info);

        // 输出结果。
        if (result == null) {
            context.sendMessage("插入结果: null");
        } else {
            context.sendMessage("插入成功");
            context.sendMessage("  category: " + result.getCategory());
            context.sendMessage("  args: " + java.util.Arrays.toString(result.getArgs()));
            context.sendMessage("  itemKey: " + result.getItemKey());
        }
    }

    private void handleUpdateItem(Context context, CommandLine cmd) throws Exception {
        NavigationNodeItemUpdateInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 NavigationNodeItemUpdateInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputNavigationNodeItemUpdateInfo.toStackBean(
                    JSON.parseObject(json, WebInputNavigationNodeItemUpdateInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 NavigationNodeItemUpdateInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputNavigationNodeItemUpdateInfo.toStackBean(
                        JSON.parseObject(in, WebInputNavigationNodeItemUpdateInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 更新导航节点条目。
        navigationNodeQosService.updateItem(info);

        // 输出结果。
        context.sendMessage("更新成功");
    }

    private void handleRemoveItem(Context context, CommandLine cmd) throws Exception {
        NavigationNodeItemRemoveInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 NavigationNodeItemRemoveInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputNavigationNodeItemRemoveInfo.toStackBean(
                    JSON.parseObject(json, WebInputNavigationNodeItemRemoveInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 NavigationNodeItemRemoveInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputNavigationNodeItemRemoveInfo.toStackBean(
                        JSON.parseObject(in, WebInputNavigationNodeItemRemoveInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 移除导航节点条目。
        navigationNodeQosService.removeItem(info);

        // 输出结果。
        context.sendMessage("移除成功");
    }

    private void handleFormatIndex(Context context, CommandLine cmd) throws Exception {
        NavigationNodeFormatIndexInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 NavigationNodeFormatIndexInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputNavigationNodeFormatIndexInfo.toStackBean(
                    JSON.parseObject(json, WebInputNavigationNodeFormatIndexInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 NavigationNodeFormatIndexInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputNavigationNodeFormatIndexInfo.toStackBean(
                        JSON.parseObject(in, WebInputNavigationNodeFormatIndexInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 格式化导航节点索引。
        navigationNodeQosService.formatIndex(info);

        // 输出结果。
        context.sendMessage("格式化成功");
    }
}

