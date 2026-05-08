package com.dwarfeng.settingrepo.impl.service.telqos;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.settingrepo.sdk.bean.dto.*;
import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.service.NavigationNodeQosService;
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

@TelqosCommand
public class NavigationNodeCommand extends CliCommand {

    @SuppressWarnings({"SpellCheckingInspection", "GrazieInspectionRunner", "RedundantSuppression"})
    private static final String IDENTITY = "navigationnode";

    // region 指令选项

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

    private static final String COMMAND_SUB_OPTION_JSON = "json";
    private static final String COMMAND_SUB_OPTION_JSON_FILE = "jf";
    private static final String COMMAND_SUB_OPTION_JSON_FILE_LONG_OPT = "json-file";

    // endregion

    private final NavigationNodeQosService navigationNodeQosService;

    public NavigationNodeCommand(NavigationNodeQosService navigationNodeQosService) {
        super(IDENTITY);
        this.navigationNodeQosService = navigationNodeQosService;
    }

    @Override
    protected DescriptionProvider provideDescriptionProvider() {
        return context -> "导航节点操作服务";
    }

    @Override
    protected CliSyntaxProvider provideCliSyntaxProvider() {
        return this::cliSyntaxProvider;
    }

    private String cliSyntaxProvider(CommandDescriptor.Context context) throws Exception {
        String identity = context.getRuntimeIdentity();
        String[] patterns = new String[]{
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_SIZE) +
                        " [" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] " +
                        "[" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_INSPECT) +
                        " [" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] " +
                        "[" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_INSERT_ITEM) +
                        " [" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] " +
                        "[" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_UPDATE_ITEM) +
                        " [" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] " +
                        "[" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_REMOVE_ITEM) +
                        " [" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] " +
                        "[" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_FORMAT_INDEX) +
                        " [" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] " +
                        "[" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]"
        };
        return CliCommandUtil.cliSyntax(patterns);
    }

    @Override
    protected List<Option> provideOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder(COMMAND_OPTION_SIZE).optionalArg(true).hasArg(false).desc("查看导航节点大小").build());
        list.add(Option.builder(COMMAND_OPTION_INSPECT).optionalArg(true).hasArg(false).desc("查看导航节点").build());
        list.add(Option.builder(COMMAND_OPTION_INSERT_ITEM).longOpt(COMMAND_OPTION_INSERT_ITEM_LONG_OPT)
                .optionalArg(true).hasArg(false).desc("插入导航节点条目").build());
        list.add(Option.builder(COMMAND_OPTION_UPDATE_ITEM).longOpt(COMMAND_OPTION_UPDATE_ITEM_LONG_OPT)
                .optionalArg(true).hasArg(false).desc("更新导航节点条目").build());
        list.add(Option.builder(COMMAND_OPTION_REMOVE_ITEM).longOpt(COMMAND_OPTION_REMOVE_ITEM_LONG_OPT)
                .optionalArg(true).hasArg(false).desc("移除导航节点条目").build());
        list.add(Option.builder(COMMAND_OPTION_FORMAT_INDEX).longOpt(COMMAND_OPTION_FORMAT_INDEX_LONG_OPT)
                .optionalArg(true).hasArg(false).desc("格式化导航节点索引").build());
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
            default:
                throw new IllegalStateException("不应该执行到此处, 请联系开发人员");
        }
    }

    private void handleSize(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        NavigationNodeSizeInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputNavigationNodeSizeInfo.toStackBean(
                    JSON.parseObject(json, WebInputNavigationNodeSizeInfo.class)
            );
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputNavigationNodeSizeInfo.toStackBean(
                        JSON.parseObject(in, WebInputNavigationNodeSizeInfo.class)
                );
            }
        } else {
            throw new UnsupportedOperationException("not supported yet");
        }

        NavigationNodeSizeResult result = navigationNodeQosService.size(info);
        if (result == null) {
            context.sendMessage("查询结果: null");
            return;
        }
        context.sendMessage("查询结果: ");
        context.sendMessage("  size: " + result.getSize());
    }

    private void handleInspect(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        NavigationNodeInspectInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputNavigationNodeInspectInfo.toStackBean(
                    JSON.parseObject(json, WebInputNavigationNodeInspectInfo.class)
            );
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputNavigationNodeInspectInfo.toStackBean(
                        JSON.parseObject(in, WebInputNavigationNodeInspectInfo.class)
                );
            }
        } else {
            throw new UnsupportedOperationException("not supported yet");
        }

        NavigationNodeInspectResult result = navigationNodeQosService.inspect(info);
        if (result == null) {
            context.sendMessage("查看结果: null");
            return;
        }
        context.sendMessage("查看结果: ");
        FastJsonNavigationNodeInspectResult fastJsonResult = FastJsonNavigationNodeInspectResult.of(result);
        context.sendMessage(JSON.toJSONString(fastJsonResult, true));
    }

    private void handleInsertItem(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        NavigationNodeItemInsertInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputNavigationNodeItemInsertInfo.toStackBean(
                    JSON.parseObject(json, WebInputNavigationNodeItemInsertInfo.class)
            );
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputNavigationNodeItemInsertInfo.toStackBean(
                        JSON.parseObject(in, WebInputNavigationNodeItemInsertInfo.class)
                );
            }
        } else {
            throw new UnsupportedOperationException("not supported yet");
        }

        NavigationNodeItemInsertResult result = navigationNodeQosService.insertItem(info);
        if (result == null) {
            context.sendMessage("插入结果: null");
            return;
        }
        context.sendMessage("插入成功");
        context.sendMessage("  category: " + result.getCategory());
        context.sendMessage("  args: " + java.util.Arrays.toString(result.getArgs()));
        context.sendMessage("  itemKey: " + result.getItemKey());
    }

    private void handleUpdateItem(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        NavigationNodeItemUpdateInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputNavigationNodeItemUpdateInfo.toStackBean(
                    JSON.parseObject(json, WebInputNavigationNodeItemUpdateInfo.class)
            );
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputNavigationNodeItemUpdateInfo.toStackBean(
                        JSON.parseObject(in, WebInputNavigationNodeItemUpdateInfo.class)
                );
            }
        } else {
            throw new UnsupportedOperationException("not supported yet");
        }
        navigationNodeQosService.updateItem(info);
        context.sendMessage("更新成功");
    }

    private void handleRemoveItem(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        NavigationNodeItemRemoveInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputNavigationNodeItemRemoveInfo.toStackBean(
                    JSON.parseObject(json, WebInputNavigationNodeItemRemoveInfo.class)
            );
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputNavigationNodeItemRemoveInfo.toStackBean(
                        JSON.parseObject(in, WebInputNavigationNodeItemRemoveInfo.class)
                );
            }
        } else {
            throw new UnsupportedOperationException("not supported yet");
        }
        navigationNodeQosService.removeItem(info);
        context.sendMessage("移除成功");
    }

    private void handleFormatIndex(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        NavigationNodeFormatIndexInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputNavigationNodeFormatIndexInfo.toStackBean(
                    JSON.parseObject(json, WebInputNavigationNodeFormatIndexInfo.class)
            );
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputNavigationNodeFormatIndexInfo.toStackBean(
                        JSON.parseObject(in, WebInputNavigationNodeFormatIndexInfo.class)
                );
            }
        } else {
            throw new UnsupportedOperationException("not supported yet");
        }
        navigationNodeQosService.formatIndex(info);
        context.sendMessage("格式化成功");
    }
}
