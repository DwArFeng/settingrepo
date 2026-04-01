package com.dwarfeng.settingrepo.impl.service.telqos;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.settingrepo.sdk.bean.dto.*;
import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.service.KvNodeQosService;
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
public class KvNodeCommand extends CliCommand {

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

    private static final String[] COMMAND_OPTION_ARRAY = {
            COMMAND_OPTION_COUNT,
            COMMAND_OPTION_INSPECT,
            COMMAND_OPTION_INSPECT_ITEM,
            COMMAND_OPTION_PUT_ITEM,
            COMMAND_OPTION_REMOVE_ITEM,
            COMMAND_OPTION_CLEAR
    };

    private static final String COMMAND_OPTION_JSON = "json";
    private static final String COMMAND_OPTION_JSON_FILE = "jf";
    private static final String COMMAND_OPTION_JSON_FILE_LONG_OPT = "json-file";

    @SuppressWarnings("SpellCheckingInspection")
    private static final String IDENTITY = "kvnode";
    private static final String DESCRIPTION = "键值对节点操作服务";

    private static final String CMD_LINE_SYNTAX_COUNT = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_COUNT) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_INSPECT = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_INSPECT) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_INSPECT_ITEM = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_INSPECT_ITEM) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_PUT_ITEM = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_PUT_ITEM) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_REMOVE_ITEM = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_REMOVE_ITEM) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_CLEAR = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_CLEAR) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";

    private static final String[] CMD_LINE_ARRAY = {
            CMD_LINE_SYNTAX_COUNT,
            CMD_LINE_SYNTAX_INSPECT,
            CMD_LINE_SYNTAX_INSPECT_ITEM,
            CMD_LINE_SYNTAX_PUT_ITEM,
            CMD_LINE_SYNTAX_REMOVE_ITEM,
            CMD_LINE_SYNTAX_CLEAR
    };

    private static final String CMD_LINE_SYNTAX = CommandUtil.syntax(CMD_LINE_ARRAY);

    private final KvNodeQosService kvNodeQosService;

    public KvNodeCommand(KvNodeQosService kvNodeQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.kvNodeQosService = kvNodeQosService;
    }

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        list.add(
                Option.builder(COMMAND_OPTION_COUNT).longOpt(COMMAND_OPTION_COUNT_LONG_OPT)
                        .desc("键值对节点的条目数量").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_INSPECT).longOpt(COMMAND_OPTION_INSPECT_LONG_OPT)
                        .desc("查看键值对节点").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_INSPECT_ITEM).longOpt(COMMAND_OPTION_INSPECT_ITEM_LONG_OPT)
                        .desc("查看键值对节点中的指定条目").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_PUT_ITEM).longOpt(COMMAND_OPTION_PUT_ITEM_LONG_OPT)
                        .desc("向键值对节点中放入指定条目").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_REMOVE_ITEM).longOpt(COMMAND_OPTION_REMOVE_ITEM_LONG_OPT)
                        .desc("移除键值对节点中的指定条目").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_CLEAR).longOpt(COMMAND_OPTION_CLEAR_LONG_OPT)
                        .desc("清空键值对节点中的全部条目").build()
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
            }
        } catch (Exception e) {
            throw new TelqosException(e);
        }
    }

    private void handleCount(Context context, CommandLine cmd) throws Exception {
        KvNodeCountInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 KvNodeCountInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputKvNodeCountInfo.toStackBean(
                    JSON.parseObject(json, WebInputKvNodeCountInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 KvNodeCountInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputKvNodeCountInfo.toStackBean(
                        JSON.parseObject(in, WebInputKvNodeCountInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 键值对节点的条目数量。
        KvNodeCountResult result = kvNodeQosService.count(info);

        // 输出结果。
        if (Objects.isNull(result)) {
            context.sendMessage("查询结果: null");
        } else {
            context.sendMessage("查询结果: ");
            context.sendMessage("  count: " + result.getCount());
        }
    }

    private void handleInspect(Context context, CommandLine cmd) throws Exception {
        KvNodeInspectInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 KvNodeInspectInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputKvNodeInspectInfo.toStackBean(
                    JSON.parseObject(json, WebInputKvNodeInspectInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 KvNodeInspectInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputKvNodeInspectInfo.toStackBean(
                        JSON.parseObject(in, WebInputKvNodeInspectInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 查看键值对节点。
        KvNodeInspectResult result = kvNodeQosService.inspect(info);

        // 输出结果。
        if (Objects.isNull(result)) {
            context.sendMessage("查询结果: null");
        } else {
            processInspectResultItems(context, result.getItems());
        }
    }

    private void processInspectResultItems(Context context, List<KvNodeInspectResult.Item> items) throws Exception {
        while (true) {
            CommandUtil.CropResult cropResult = CommandUtil.cropData(
                    context, items, "数据总数: " + items.size(), "输入 q 退出"
            );
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
            Context context, int i, int endIndex, KvNodeInspectResult.Item item
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

    private void handleInspectItem(Context context, CommandLine cmd) throws Exception {
        KvNodeItemInspectInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 KvNodeItemInspectInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputKvNodeItemInspectInfo.toStackBean(
                    JSON.parseObject(json, WebInputKvNodeItemInspectInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 KvNodeItemInspectInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputKvNodeItemInspectInfo.toStackBean(
                        JSON.parseObject(in, WebInputKvNodeItemInspectInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 查看键值对节点中的指定条目。
        KvNodeItemInspectResult result = kvNodeQosService.inspectItem(info);

        // 输出结果。
        if (Objects.isNull(result)) {
            context.sendMessage("查询结果: null");
        } else {
            context.sendMessage("查询结果: ");
            context.sendMessage("  value: " + result.getValue());
        }
    }

    private void handlePutItem(Context context, CommandLine cmd) throws Exception {
        KvNodeItemPutInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 KvNodeItemPutInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputKvNodeItemPutInfo.toStackBean(
                    JSON.parseObject(json, WebInputKvNodeItemPutInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 KvNodeItemPutInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputKvNodeItemPutInfo.toStackBean(
                        JSON.parseObject(in, WebInputKvNodeItemPutInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 向键值对节点中放入指定条目。
        kvNodeQosService.putItem(info);

        // 输出结果。
        context.sendMessage("放入成功");
    }

    private void handleRemoveItem(Context context, CommandLine cmd) throws Exception {
        KvNodeItemRemoveInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 KvNodeItemRemoveInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputKvNodeItemRemoveInfo.toStackBean(
                    JSON.parseObject(json, WebInputKvNodeItemRemoveInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 KvNodeItemRemoveInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputKvNodeItemRemoveInfo.toStackBean(
                        JSON.parseObject(in, WebInputKvNodeItemRemoveInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 移除键值对节点中的指定条目。
        kvNodeQosService.removeItem(info);

        // 输出结果。
        context.sendMessage("移除成功");
    }

    private void handleClear(Context context, CommandLine cmd) throws Exception {
        KvNodeClearInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 KvNodeClearInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputKvNodeClearInfo.toStackBean(
                    JSON.parseObject(json, WebInputKvNodeClearInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 KvNodeClearInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputKvNodeClearInfo.toStackBean(
                        JSON.parseObject(in, WebInputKvNodeClearInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 清空键值对节点中的全部条目。
        kvNodeQosService.clear(info);

        // 输出结果。
        context.sendMessage("清空成功");
    }
}
