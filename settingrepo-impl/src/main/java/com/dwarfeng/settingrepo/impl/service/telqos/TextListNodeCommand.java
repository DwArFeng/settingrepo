package com.dwarfeng.settingrepo.impl.service.telqos;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.settingrepo.sdk.bean.dto.*;
import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.service.TextListNodeQosService;
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

/**
 * 文本列表节点操作命令。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
@TelqosCommand
public class TextListNodeCommand extends CliCommand {

    @SuppressWarnings({"SpellCheckingInspection", "GrazieInspectionRunner", "RedundantSuppression"})
    private static final String IDENTITY = "textlnode";

    // region 指令选项

    private static final String COMMAND_OPTION_SIZE = "size";
    private static final String COMMAND_OPTION_INSPECT = "inspect";
    private static final String COMMAND_OPTION_FETCH = "fetch";
    private static final String COMMAND_OPTION_INSERT = "insert";
    private static final String COMMAND_OPTION_UPDATE = "update";
    private static final String COMMAND_OPTION_CHANGE_ORDER = "co";
    private static final String COMMAND_OPTION_CHANGE_ORDER_LONG_OPT = "change-order";
    private static final String COMMAND_OPTION_REMOVE = "remove";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_SIZE,
            COMMAND_OPTION_INSPECT,
            COMMAND_OPTION_FETCH,
            COMMAND_OPTION_INSERT,
            COMMAND_OPTION_UPDATE,
            COMMAND_OPTION_CHANGE_ORDER,
            COMMAND_OPTION_REMOVE
    };

    private static final String COMMAND_SUB_OPTION_JSON = "json";
    private static final String COMMAND_SUB_OPTION_JSON_FILE = "jf";
    private static final String COMMAND_SUB_OPTION_JSON_FILE_LONG_OPT = "json-file";

    // endregion

    private final TextListNodeQosService textListNodeQosService;

    public TextListNodeCommand(TextListNodeQosService textListNodeQosService) {
        super(IDENTITY);
        this.textListNodeQosService = textListNodeQosService;
    }

    @Override
    protected DescriptionProvider provideDescriptionProvider() {
        return context -> "文本列表节点操作服务";
    }

    @Override
    protected CliSyntaxProvider provideCliSyntaxProvider() {
        return this::cliSyntaxProvider;
    }

    @SuppressWarnings("DuplicatedCode")
    private String cliSyntaxProvider(CommandDescriptor.Context context) throws Exception {
        String identity = context.getRuntimeIdentity();
        String[] patterns = new String[]{
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_SIZE) + " [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_INSPECT) + " [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_FETCH) + " [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_INSERT) + " [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_UPDATE) + " [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_CHANGE_ORDER) + " [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_REMOVE) + " [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]"
        };
        return CliCommandUtil.cliSyntax(patterns);
    }

    @Override
    protected List<Option> provideOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder(COMMAND_OPTION_SIZE).optionalArg(true).hasArg(false).desc("获取文本列表节点的大小").build());
        list.add(Option.builder(COMMAND_OPTION_INSPECT).optionalArg(true).hasArg(false).desc("查看文本列表节点").build());
        list.add(Option.builder(COMMAND_OPTION_FETCH).optionalArg(true).hasArg(false).desc("拉取文本列表节点文本").build());
        list.add(Option.builder(COMMAND_OPTION_INSERT).optionalArg(true).hasArg(false).desc("插入文本列表节点文本").build());
        list.add(Option.builder(COMMAND_OPTION_UPDATE).optionalArg(true).hasArg(false).desc("更新文本列表节点文本").build());
        list.add(Option.builder(COMMAND_OPTION_CHANGE_ORDER).longOpt(COMMAND_OPTION_CHANGE_ORDER_LONG_OPT)
                .optionalArg(true).hasArg(false).desc("更改文本列表节点的顺序").build());
        list.add(Option.builder(COMMAND_OPTION_REMOVE).optionalArg(true).hasArg(false).desc("移除文本列表节点").build());
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
            case COMMAND_OPTION_FETCH:
                handleFetch(context, cmd);
                break;
            case COMMAND_OPTION_INSERT:
                handleInsert(context, cmd);
                break;
            case COMMAND_OPTION_UPDATE:
                handleUpdate(context, cmd);
                break;
            case COMMAND_OPTION_CHANGE_ORDER:
                handleChangeOrder(context, cmd);
                break;
            case COMMAND_OPTION_REMOVE:
                handleRemove(context, cmd);
                break;
            default:
                throw new IllegalStateException("不应该执行到此处, 请联系开发人员");
        }
    }

    private void handleSize(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        TextListNodeSizeInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputTextListNodeSizeInfo.toStackBean(JSON.parseObject(json, WebInputTextListNodeSizeInfo.class));
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputTextListNodeSizeInfo.toStackBean(
                        JSON.parseObject(in, WebInputTextListNodeSizeInfo.class)
                );
            }
        } else {
            throw new UnsupportedOperationException("not supported yet");
        }
        TextListNodeSizeResult result = textListNodeQosService.size(info);
        if (result == null) {
            context.sendMessage("文本列表节点的大小: null");
            return;
        }
        context.sendMessage("文本列表节点的大小: ");
        context.sendMessage("  size: " + result.getSize());
    }

    private void handleInspect(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        TextListNodeInspectInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputTextListNodeInspectInfo.toStackBean(
                    JSON.parseObject(json, WebInputTextListNodeInspectInfo.class)
            );
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputTextListNodeInspectInfo.toStackBean(
                        JSON.parseObject(in, WebInputTextListNodeInspectInfo.class)
                );
            }
        } else {
            throw new UnsupportedOperationException("not supported yet");
        }
        TextListNodeInspectResult result = textListNodeQosService.inspect(info);
        if (result == null) {
            context.sendMessage("查看结果: null");
            return;
        }
        context.sendMessage("查看结果: ");
        List<TextListNodeInspectResult.Item> items = result.getItems();
        if (items == null) {
            context.sendMessage("  items: null");
            return;
        }
        context.sendMessage("  items: total size: " + items.size());
        for (int i = 0; i < items.size(); i++) {
            TextListNodeInspectResult.Item item = items.get(i);
            context.sendMessage("    " + i + "/" + items.size() + ": ");
            context.sendMessage("      index: " + item.getIndex());
            context.sendMessage("      nullFlag: " + item.isNullFlag());
        }
    }

    private void handleFetch(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        TextListNodeTextFetchInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputTextListNodeTextFetchInfo.toStackBean(
                    JSON.parseObject(json, WebInputTextListNodeTextFetchInfo.class)
            );
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputTextListNodeTextFetchInfo.toStackBean(
                        JSON.parseObject(in, WebInputTextListNodeTextFetchInfo.class)
                );
            }
        } else {
            throw new UnsupportedOperationException("not supported yet");
        }
        TextListNodeTextFetchResult result = textListNodeQosService.fetchText(info);
        if (result == null) {
            context.sendMessage("拉取结果: null");
            return;
        }
        context.sendMessage("拉取结果: ");
        context.sendMessage("  content: " + result.getContent());
    }

    private void handleInsert(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        TextListNodeTextInsertInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputTextListNodeTextInsertInfo.toStackBean(
                    JSON.parseObject(json, WebInputTextListNodeTextInsertInfo.class)
            );
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputTextListNodeTextInsertInfo.toStackBean(
                        JSON.parseObject(in, WebInputTextListNodeTextInsertInfo.class)
                );
            }
        } else {
            throw new UnsupportedOperationException("not supported yet");
        }
        textListNodeQosService.insertText(info);
        context.sendMessage("插入成功");
    }

    private void handleUpdate(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        TextListNodeTextUpdateInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputTextListNodeTextUpdateInfo.toStackBean(
                    JSON.parseObject(json, WebInputTextListNodeTextUpdateInfo.class)
            );
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputTextListNodeTextUpdateInfo.toStackBean(
                        JSON.parseObject(in, WebInputTextListNodeTextUpdateInfo.class)
                );
            }
        } else {
            throw new UnsupportedOperationException("not supported yet");
        }
        textListNodeQosService.updateText(info);
        context.sendMessage("更新成功");
    }

    private void handleChangeOrder(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        TextListNodeChangeOrderInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputTextListNodeChangeOrderInfo.toStackBean(
                    JSON.parseObject(json, WebInputTextListNodeChangeOrderInfo.class)
            );
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputTextListNodeChangeOrderInfo.toStackBean(
                        JSON.parseObject(in, WebInputTextListNodeChangeOrderInfo.class)
                );
            }
        } else {
            throw new UnsupportedOperationException("not supported yet");
        }
        textListNodeQosService.changeOrder(info);
        context.sendMessage("更改顺序成功");
    }

    private void handleRemove(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        TextListNodeRemoveInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputTextListNodeRemoveInfo.toStackBean(
                    JSON.parseObject(json, WebInputTextListNodeRemoveInfo.class)
            );
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputTextListNodeRemoveInfo.toStackBean(
                        JSON.parseObject(in, WebInputTextListNodeRemoveInfo.class)
                );
            }
        } else {
            throw new UnsupportedOperationException("not supported yet");
        }
        textListNodeQosService.remove(info);
        context.sendMessage("移除成功");
    }
}
