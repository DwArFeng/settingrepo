package com.dwarfeng.settingrepo.impl.service.telqos;

import com.alibaba.fastjson2.JSON;
import com.dwarfeng.settingrepo.impl.internal.i18n.ImplMessageKey;
import com.dwarfeng.settingrepo.impl.internal.i18n.ImplMessages;
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
        return _ -> ImplMessages.message(ImplMessageKey.TELQOS_KV_DESCRIPTION);
    }

    @Override
    protected CliSyntaxProvider provideCliSyntaxProvider() {
        return this::cliSyntaxProvider;
    }

    @SuppressWarnings("DuplicatedCode")
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

    @SuppressWarnings("DuplicatedCode")
    @Override
    protected List<Option> provideOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder(COMMAND_OPTION_COUNT).longOpt(COMMAND_OPTION_COUNT_LONG_OPT)
                .optionalArg(true).hasArg(false)
                .desc(ImplMessages.message(ImplMessageKey.TELQOS_KV_OPTION_COUNT)).get());
        list.add(Option.builder(COMMAND_OPTION_INSPECT).longOpt(COMMAND_OPTION_INSPECT_LONG_OPT)
                .optionalArg(true).hasArg(false)
                .desc(ImplMessages.message(ImplMessageKey.TELQOS_KV_OPTION_INSPECT)).get());
        list.add(Option.builder(COMMAND_OPTION_INSPECT_ITEM).longOpt(COMMAND_OPTION_INSPECT_ITEM_LONG_OPT)
                .optionalArg(true).hasArg(false)
                .desc(ImplMessages.message(ImplMessageKey.TELQOS_KV_OPTION_INSPECT_ITEM)).get());
        list.add(Option.builder(COMMAND_OPTION_PUT_ITEM).longOpt(COMMAND_OPTION_PUT_ITEM_LONG_OPT)
                .optionalArg(true).hasArg(false)
                .desc(ImplMessages.message(ImplMessageKey.TELQOS_KV_OPTION_PUT)).get());
        list.add(Option.builder(COMMAND_OPTION_REMOVE_ITEM).longOpt(COMMAND_OPTION_REMOVE_ITEM_LONG_OPT)
                .optionalArg(true).hasArg(false)
                .desc(ImplMessages.message(ImplMessageKey.TELQOS_KV_OPTION_REMOVE)).get());
        list.add(Option.builder(COMMAND_OPTION_CLEAR).longOpt(COMMAND_OPTION_CLEAR_LONG_OPT)
                .optionalArg(true).hasArg(false)
                .desc(ImplMessages.message(ImplMessageKey.TELQOS_KV_OPTION_CLEAR)).get());
        list.add(
                Option.builder(COMMAND_SUB_OPTION_JSON).hasArg(true).type(String.class)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_OPTION_JSON_STRING)).get()
        );
        list.add(Option.builder(COMMAND_SUB_OPTION_JSON_FILE).longOpt(COMMAND_SUB_OPTION_JSON_FILE_LONG_OPT)
                .hasArg(true).type(File.class)
                .desc(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_OPTION_JSON_FILE)).get());
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
                throw new IllegalStateException(
                        ImplMessages.message(ImplMessageKey.TELQOS_COMMON_INTERNAL_UNREACHABLE)
                );
        }
    }

    private void handleCount(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        KvNodeCountInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputKvNodeCountInfo.toStackBean(JSON.parseObject(json, WebInputKvNodeCountInfo.class));
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputKvNodeCountInfo.toStackBean(JSON.parseObject(in, WebInputKvNodeCountInfo.class));
            }
        } else {
            throw new UnsupportedOperationException(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_UNSUPPORTED));
        }

        KvNodeCountResult result = kvNodeQosService.count(info);
        if (Objects.isNull(result)) {
            context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_QUERY_RESULT_NULL));
            return;
        }
        context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_QUERY_RESULT_HEADER));
        context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_KV_FIELD_COUNT, result.getCount()));
    }

    private void handleInspect(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        KvNodeInspectInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputKvNodeInspectInfo.toStackBean(JSON.parseObject(json, WebInputKvNodeInspectInfo.class));
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputKvNodeInspectInfo.toStackBean(JSON.parseObject(in, WebInputKvNodeInspectInfo.class));
            }
        } else {
            throw new UnsupportedOperationException(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_UNSUPPORTED));
        }

        KvNodeInspectResult result = kvNodeQosService.inspect(info);
        if (Objects.isNull(result)) {
            context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_QUERY_RESULT_NULL));
            return;
        }
        processInspectResultItems(context, result.getItems());
    }

    private void processInspectResultItems(CommandExecutor.Context context, List<KvNodeInspectResult.Item> items)
            throws Exception {
        while (true) {
            CliCommandUtil.CropResult cropResult = CliCommandUtil.cropData(
                    context,
                    items,
                    ImplMessages.message(ImplMessageKey.TELQOS_COMMON_DATA_TOTAL, items.size())
            );
            if (cropResult.exitFlag()) {
                break;
            }
            context.sendMessage("");
            for (int i = cropResult.beginIndex(); i < cropResult.endIndex(); i++) {
                KvNodeInspectResult.Item item = items.get(i);
                printKvNodeInspectResultItem(context, i, cropResult.endIndex(), item);
            }
        }
    }

    private void printKvNodeInspectResultItem(
            CommandExecutor.Context context, int i, int endIndex, KvNodeInspectResult.Item item
    ) throws Exception {
        context.sendMessage(String.format(ImplMessages.message(ImplMessageKey.TELQOS_IAHN_INDEX), i, endIndex));
        if (Objects.isNull(item)) {
            context.sendMessage("null");
        } else {
            context.sendMessage(
                    String.format(
                            ImplMessages.message(ImplMessageKey.TELQOS_KV_FIELD_ITEM_STRING_ID),
                            item.getItemStringId()
                    )
            );
            context.sendMessage(
                    String.format(
                            ImplMessages.message(ImplMessageKey.TELQOS_KV_FIELD_VALUE_FORMAT),
                            item.getValue()
                    )
            );
        }
        context.sendMessage("");
    }

    private void handleInspectItem(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        KvNodeItemInspectInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputKvNodeItemInspectInfo.toStackBean(
                    JSON.parseObject(json, WebInputKvNodeItemInspectInfo.class)
            );
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputKvNodeItemInspectInfo.toStackBean(
                        JSON.parseObject(in, WebInputKvNodeItemInspectInfo.class)
                );
            }
        } else {
            throw new UnsupportedOperationException(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_UNSUPPORTED));
        }

        KvNodeItemInspectResult result = kvNodeQosService.inspectItem(info);
        if (Objects.isNull(result)) {
            context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_QUERY_RESULT_NULL));
            return;
        }
        context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_QUERY_RESULT_HEADER));
        context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_FIELD_VALUE, result.getValue()));
    }

    private void handlePutItem(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        KvNodeItemPutInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputKvNodeItemPutInfo.toStackBean(JSON.parseObject(json, WebInputKvNodeItemPutInfo.class));
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputKvNodeItemPutInfo.toStackBean(JSON.parseObject(in, WebInputKvNodeItemPutInfo.class));
            }
        } else {
            throw new UnsupportedOperationException(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_UNSUPPORTED));
        }
        kvNodeQosService.putItem(info);
        context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_KV_PUT_SUCCEEDED));
    }

    private void handleRemoveItem(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        KvNodeItemRemoveInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputKvNodeItemRemoveInfo.toStackBean(
                    JSON.parseObject(json, WebInputKvNodeItemRemoveInfo.class)
            );
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputKvNodeItemRemoveInfo.toStackBean(
                        JSON.parseObject(in, WebInputKvNodeItemRemoveInfo.class)
                );
            }
        } else {
            throw new UnsupportedOperationException(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_UNSUPPORTED));
        }
        kvNodeQosService.removeItem(info);
        context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_REMOVE_SUCCEEDED));
    }

    private void handleClear(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        KvNodeClearInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputKvNodeClearInfo.toStackBean(JSON.parseObject(json, WebInputKvNodeClearInfo.class));
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputKvNodeClearInfo.toStackBean(JSON.parseObject(in, WebInputKvNodeClearInfo.class));
            }
        } else {
            throw new UnsupportedOperationException(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_UNSUPPORTED));
        }
        kvNodeQosService.clear(info);
        context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_KV_CLEAR_SUCCEEDED));
    }
}
