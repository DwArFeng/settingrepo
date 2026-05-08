package com.dwarfeng.settingrepo.impl.service.telqos;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.settingrepo.sdk.bean.dto.*;
import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.service.KvNodeQosService;
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
public class KvNodeCommand extends CliCommand {

    @SuppressWarnings({"SpellCheckingInspection", "GrazieInspectionRunner", "RedundantSuppression"})
    private static final String IDENTITY = "kvnode";

    // region 指令选项

    private static final String COMMAND_OPTION_COUNT = "cnt";
    private static final String COMMAND_OPTION_COUNT_LONG_OPT = "count";
    private static final String COMMAND_OPTION_INSPECT = "i";
    private static final String COMMAND_OPTION_INSPECT_LONG_OPT = "inspect";
    private static final String COMMAND_OPTION_INSPECT_ITEM = "ii";
    private static final String COMMAND_OPTION_INSPECT_ITEM_LONG_OPT = "inspect-item";
    private static final String COMMAND_OPTION_PUT_ITEM = "pi";
    private static final String COMMAND_OPTION_PUT_ITEM_LONG_OPT = "put-item";
    private static final String COMMAND_OPTION_REMOVE_ITEM = "ri";
    private static final String COMMAND_OPTION_REMOVE_ITEM_LONG_OPT = "remove-item";
    private static final String COMMAND_OPTION_CLEAR = "clr";
    private static final String COMMAND_OPTION_CLEAR_LONG_OPT = "clear";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_COUNT,
            COMMAND_OPTION_INSPECT,
            COMMAND_OPTION_INSPECT_ITEM,
            COMMAND_OPTION_PUT_ITEM,
            COMMAND_OPTION_REMOVE_ITEM,
            COMMAND_OPTION_CLEAR
    };

    private static final String COMMAND_SUB_OPTION_JSON = "json";
    private static final String COMMAND_SUB_OPTION_JSON_FILE = "jf";
    private static final String COMMAND_SUB_OPTION_JSON_FILE_LONG_OPT = "json-file";

    // endregion

    private final KvNodeQosService kvNodeQosService;

    public KvNodeCommand(KvNodeQosService kvNodeQosService) {
        super(IDENTITY);
        this.kvNodeQosService = kvNodeQosService;
    }

    @Override
    protected DescriptionProvider provideDescriptionProvider() {
        return context -> "键值对节点操作服务";
    }

    @Override
    protected CliSyntaxProvider provideCliSyntaxProvider() {
        return this::cliSyntaxProvider;
    }

    private String cliSyntaxProvider(CommandDescriptor.Context context) throws Exception {
        String identity = context.getRuntimeIdentity();
        String[] patterns = new String[]{
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_COUNT) +
                        " [" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] " +
                        "[" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_INSPECT) +
                        " [" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] " +
                        "[" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_INSPECT_ITEM) +
                        " [" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] " +
                        "[" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_PUT_ITEM) +
                        " [" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] " +
                        "[" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_REMOVE_ITEM) +
                        " [" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] " +
                        "[" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_CLEAR) +
                        " [" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] " +
                        "[" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]"
        };
        return CliCommandUtil.cliSyntax(patterns);
    }

    @Override
    protected List<Option> provideOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder(COMMAND_OPTION_COUNT).longOpt(COMMAND_OPTION_COUNT_LONG_OPT)
                .optionalArg(true).hasArg(false).desc("键值对节点的条目数量").build());
        list.add(Option.builder(COMMAND_OPTION_INSPECT).longOpt(COMMAND_OPTION_INSPECT_LONG_OPT)
                .optionalArg(true).hasArg(false).desc("查看键值对节点").build());
        list.add(Option.builder(COMMAND_OPTION_INSPECT_ITEM).longOpt(COMMAND_OPTION_INSPECT_ITEM_LONG_OPT)
                .optionalArg(true).hasArg(false).desc("查看键值对节点中的指定条目").build());
        list.add(Option.builder(COMMAND_OPTION_PUT_ITEM).longOpt(COMMAND_OPTION_PUT_ITEM_LONG_OPT)
                .optionalArg(true).hasArg(false).desc("向键值对节点中放入指定条目").build());
        list.add(Option.builder(COMMAND_OPTION_REMOVE_ITEM).longOpt(COMMAND_OPTION_REMOVE_ITEM_LONG_OPT)
                .optionalArg(true).hasArg(false).desc("移除键值对节点中的指定条目").build());
        list.add(Option.builder(COMMAND_OPTION_CLEAR).longOpt(COMMAND_OPTION_CLEAR_LONG_OPT)
                .optionalArg(true).hasArg(false).desc("清空键值对节点中的全部条目").build());
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
            case COMMAND_OPTION_COUNT:
                handleCount(context, cmd);
                break;
            case COMMAND_OPTION_INSPECT:
                handleInspect(context, cmd);
                break;
            case COMMAND_OPTION_INSPECT_ITEM:
                handleInspectItem(context, cmd);
                break;
            case COMMAND_OPTION_PUT_ITEM:
                handlePutItem(context, cmd);
                break;
            case COMMAND_OPTION_REMOVE_ITEM:
                handleRemoveItem(context, cmd);
                break;
            case COMMAND_OPTION_CLEAR:
                handleClear(context, cmd);
                break;
            default:
                throw new IllegalStateException("不应该执行到此处, 请联系开发人员");
        }
    }

    private void handleCount(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        KvNodeCountInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputKvNodeCountInfo.toStackBean(JSON.parseObject(json, WebInputKvNodeCountInfo.class));
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputKvNodeCountInfo.toStackBean(JSON.parseObject(in, WebInputKvNodeCountInfo.class));
            }
        } else {
            throw new UnsupportedOperationException("not supported yet");
        }

        KvNodeCountResult result = kvNodeQosService.count(info);
        if (Objects.isNull(result)) {
            context.sendMessage("查询结果: null");
            return;
        }
        context.sendMessage("查询结果: ");
        context.sendMessage("  count: " + result.getCount());
    }

    private void handleInspect(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        KvNodeInspectInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputKvNodeInspectInfo.toStackBean(JSON.parseObject(json, WebInputKvNodeInspectInfo.class));
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputKvNodeInspectInfo.toStackBean(JSON.parseObject(in, WebInputKvNodeInspectInfo.class));
            }
        } else {
            throw new UnsupportedOperationException("not supported yet");
        }

        KvNodeInspectResult result = kvNodeQosService.inspect(info);
        if (Objects.isNull(result)) {
            context.sendMessage("查询结果: null");
            return;
        }
        processInspectResultItems(context, result.getItems());
    }

    private void processInspectResultItems(CommandExecutor.Context context, List<KvNodeInspectResult.Item> items)
            throws Exception {
        while (true) {
            CliCommandUtil.CropResult cropResult = CliCommandUtil.cropData(context, items, "数据总数: " + items.size());
            if (cropResult.isExitFlag()) {
                break;
            }
            context.sendMessage("");
            for (int i = cropResult.getBeginIndex(); i < cropResult.getEndIndex(); i++) {
                KvNodeInspectResult.Item item = items.get(i);
                printKvNodeInspectResultItem(context, i, cropResult.getEndIndex(), item);
            }
        }
    }

    private void printKvNodeInspectResultItem(
            CommandExecutor.Context context, int i, int endIndex, KvNodeInspectResult.Item item
    ) throws Exception {
        context.sendMessage(String.format("索引: %d/%d", i, endIndex));
        if (Objects.isNull(item)) {
            context.sendMessage("null");
        } else {
            context.sendMessage(String.format("  itemStringId: %s", item.getItemStringId()));
            context.sendMessage(String.format("  value: %s", item.getValue()));
        }
        context.sendMessage("");
    }

    private void handleInspectItem(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        KvNodeItemInspectInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputKvNodeItemInspectInfo.toStackBean(
                    JSON.parseObject(json, WebInputKvNodeItemInspectInfo.class)
            );
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputKvNodeItemInspectInfo.toStackBean(
                        JSON.parseObject(in, WebInputKvNodeItemInspectInfo.class)
                );
            }
        } else {
            throw new UnsupportedOperationException("not supported yet");
        }

        KvNodeItemInspectResult result = kvNodeQosService.inspectItem(info);
        if (Objects.isNull(result)) {
            context.sendMessage("查询结果: null");
            return;
        }
        context.sendMessage("查询结果: ");
        context.sendMessage("  value: " + result.getValue());
    }

    private void handlePutItem(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        KvNodeItemPutInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputKvNodeItemPutInfo.toStackBean(JSON.parseObject(json, WebInputKvNodeItemPutInfo.class));
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputKvNodeItemPutInfo.toStackBean(JSON.parseObject(in, WebInputKvNodeItemPutInfo.class));
            }
        } else {
            throw new UnsupportedOperationException("not supported yet");
        }
        kvNodeQosService.putItem(info);
        context.sendMessage("放入成功");
    }

    private void handleRemoveItem(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        KvNodeItemRemoveInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputKvNodeItemRemoveInfo.toStackBean(
                    JSON.parseObject(json, WebInputKvNodeItemRemoveInfo.class)
            );
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputKvNodeItemRemoveInfo.toStackBean(
                        JSON.parseObject(in, WebInputKvNodeItemRemoveInfo.class)
                );
            }
        } else {
            throw new UnsupportedOperationException("not supported yet");
        }
        kvNodeQosService.removeItem(info);
        context.sendMessage("移除成功");
    }

    private void handleClear(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        KvNodeClearInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputKvNodeClearInfo.toStackBean(JSON.parseObject(json, WebInputKvNodeClearInfo.class));
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputKvNodeClearInfo.toStackBean(JSON.parseObject(in, WebInputKvNodeClearInfo.class));
            }
        } else {
            throw new UnsupportedOperationException("not supported yet");
        }
        kvNodeQosService.clear(info);
        context.sendMessage("清空成功");
    }
}
