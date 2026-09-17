package com.dwarfeng.settingrepo.impl.service.telqos;

import com.alibaba.fastjson2.JSON;
import com.dwarfeng.settingrepo.impl.internal.i18n.ImplMessageKey;
import com.dwarfeng.settingrepo.impl.internal.i18n.ImplMessages;
import com.dwarfeng.settingrepo.sdk.bean.dto.*;
import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.service.IahnNodeQosService;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.sdk.configuration.TelqosCommand;
import com.dwarfeng.springtelqos.sdk.util.CliCommandUtil;
import com.dwarfeng.springtelqos.stack.command.CommandDescriptor;
import com.dwarfeng.springtelqos.stack.command.CommandExecutor;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@TelqosCommand
public class IahnNodeCommand extends CliCommand {

    @SuppressWarnings({"SpellCheckingInspection", "GrazieInspectionRunner", "RedundantSuppression"})
    private static final String IDENTITY = "iahnnode";

    // region 指令选项

    private static final String COMMAND_OPTION_INSPECT_MESSAGE = "im";
    private static final String COMMAND_OPTION_INSPECT_MESSAGE_LONG_OPT = "inspect-message";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_BATCH_INSPECT_MESSAGE_BY_LOCALE = "biml";
    private static final String COMMAND_OPTION_BATCH_INSPECT_MESSAGE_BY_LOCALE_LONG_OPT
            = "batch-inspect-message-by-locale";
    private static final String COMMAND_OPTION_INSPECT_LOCALE_LIST = "ill";
    private static final String COMMAND_OPTION_INSPECT_LOCALE_LIST_LONG_OPT = "inspect-locale-list";
    private static final String COMMAND_OPTION_INSPECT_MEK_LIST = "iml";
    private static final String COMMAND_OPTION_INSPECT_MEK_LIST_LONG_OPT = "inspect-mek-list";
    private static final String COMMAND_OPTION_INSPECT_MESSAGE_TABLE = "imt";
    private static final String COMMAND_OPTION_INSPECT_MESSAGE_TABLE_LONG_OPT = "inspect-message-table";
    private static final String COMMAND_OPTION_PUT_LOCALE = "pl";
    private static final String COMMAND_OPTION_PUT_LOCALE_LONG_OPT = "put-locale";
    private static final String COMMAND_OPTION_REMOVE_LOCALE = "rl";
    private static final String COMMAND_OPTION_REMOVE_LOCALE_LONG_OPT = "remove-locale";
    private static final String COMMAND_OPTION_PUT_MEK = "pm";
    private static final String COMMAND_OPTION_PUT_MEK_LONG_OPT = "put-mek";
    private static final String COMMAND_OPTION_REMOVE_MEK = "rm";
    private static final String COMMAND_OPTION_REMOVE_MEK_LONG_OPT = "remove-mek";
    private static final String COMMAND_OPTION_UPSERT_MESSAGE = "um";
    private static final String COMMAND_OPTION_UPSERT_MESSAGE_LONG_OPT = "upsert-message";
    @SuppressWarnings("SpellCheckingInspection")
    private static final String COMMAND_OPTION_BATCH_UPSERT_MESSAGE_BY_LOCALE = "buml";
    private static final String COMMAND_OPTION_BATCH_UPSERT_MESSAGE_BY_LOCALE_LONG_OPT
            = "batch-upsert-message-by-locale";
    @SuppressWarnings("SpellCheckingInspection")
    private static final String COMMAND_OPTION_BATCH_UPSERT_MESSAGE_BY_MEK = "bumm";
    private static final String COMMAND_OPTION_BATCH_UPSERT_MESSAGE_BY_MEK_LONG_OPT = "batch-upsert-message-by-mek";

    private static final String[] COMMAND_OPTION_ARRAY = {
            COMMAND_OPTION_INSPECT_MESSAGE,
            COMMAND_OPTION_BATCH_INSPECT_MESSAGE_BY_LOCALE,
            COMMAND_OPTION_INSPECT_LOCALE_LIST,
            COMMAND_OPTION_INSPECT_MEK_LIST,
            COMMAND_OPTION_INSPECT_MESSAGE_TABLE,
            COMMAND_OPTION_PUT_LOCALE,
            COMMAND_OPTION_REMOVE_LOCALE,
            COMMAND_OPTION_PUT_MEK,
            COMMAND_OPTION_REMOVE_MEK,
            COMMAND_OPTION_UPSERT_MESSAGE,
            COMMAND_OPTION_BATCH_UPSERT_MESSAGE_BY_LOCALE,
            COMMAND_OPTION_BATCH_UPSERT_MESSAGE_BY_MEK
    };

    private static final String COMMAND_SUB_OPTION_JSON = "json";
    private static final String COMMAND_SUB_OPTION_JSON_FILE = "jf";
    private static final String COMMAND_SUB_OPTION_JSON_FILE_LONG_OPT = "json-file";

    // endregion

    private static final String FORMATTED_LOCALE_DEFAULT = "#default";

    private final IahnNodeQosService iahnNodeQosService;

    public IahnNodeCommand(IahnNodeQosService iahnNodeQosService) {
        super(IDENTITY);
        this.iahnNodeQosService = iahnNodeQosService;
    }

    @Override
    protected DescriptionProvider provideDescriptionProvider() {
        return _ -> ImplMessages.message(ImplMessageKey.TELQOS_IAHN_DESCRIPTION);
    }

    @Override
    protected CliSyntaxProvider provideCliSyntaxProvider() {
        return this::cliSyntaxProvider;
    }

    private String cliSyntaxProvider(CommandDescriptor.Context context) throws Exception {
        String identity = context.getRuntimeIdentity();
        String[] patterns = new String[]{
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_INSPECT_MESSAGE) + " [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_BATCH_INSPECT_MESSAGE_BY_LOCALE) +
                        " [" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_INSPECT_LOCALE_LIST) + " [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_INSPECT_MEK_LIST) + " [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_INSPECT_MESSAGE_TABLE) + " [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_PUT_LOCALE) + " [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_REMOVE_LOCALE) + " [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_PUT_MEK) + " [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_REMOVE_MEK) + " [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_UPSERT_MESSAGE) + " [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_BATCH_UPSERT_MESSAGE_BY_LOCALE) +
                        " [" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_BATCH_UPSERT_MESSAGE_BY_MEK) +
                        " [" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]"
        };
        return CliCommandUtil.cliSyntax(patterns);
    }

    @Override
    protected List<Option> provideOptions() {
        List<Option> list = new ArrayList<>();
        list.add(
                Option.builder(COMMAND_OPTION_INSPECT_MESSAGE).longOpt(COMMAND_OPTION_INSPECT_MESSAGE_LONG_OPT)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_IAHN_OPTION_QUERY_MESSAGE)).get()
        );
        list.add(
                Option.builder(COMMAND_OPTION_BATCH_INSPECT_MESSAGE_BY_LOCALE)
                        .longOpt(COMMAND_OPTION_BATCH_INSPECT_MESSAGE_BY_LOCALE_LONG_OPT)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_IAHN_OPTION_QUERY_BY_LOCALE)).get()
        );
        list.add(
                Option.builder(COMMAND_OPTION_INSPECT_LOCALE_LIST).longOpt(COMMAND_OPTION_INSPECT_LOCALE_LIST_LONG_OPT)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_IAHN_OPTION_QUERY_LOCALES)).get()
        );
        list.add(
                Option.builder(COMMAND_OPTION_INSPECT_MEK_LIST).longOpt(COMMAND_OPTION_INSPECT_MEK_LIST_LONG_OPT)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_IAHN_OPTION_QUERY_MEKS)).get()
        );
        list.add(
                Option.builder(COMMAND_OPTION_INSPECT_MESSAGE_TABLE)
                        .longOpt(COMMAND_OPTION_INSPECT_MESSAGE_TABLE_LONG_OPT)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_IAHN_OPTION_QUERY_TABLE)).get()
        );
        list.add(
                Option.builder(COMMAND_OPTION_PUT_LOCALE).longOpt(COMMAND_OPTION_PUT_LOCALE_LONG_OPT)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_IAHN_OPTION_PUSH_LOCALE)).get()
        );
        list.add(
                Option.builder(COMMAND_OPTION_REMOVE_LOCALE).longOpt(COMMAND_OPTION_REMOVE_LOCALE_LONG_OPT)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_IAHN_OPTION_REMOVE_LOCALE)).get()
        );
        list.add(
                Option.builder(COMMAND_OPTION_PUT_MEK).longOpt(COMMAND_OPTION_PUT_MEK_LONG_OPT)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_IAHN_OPTION_PUSH_MEK)).get()
        );
        list.add(
                Option.builder(COMMAND_OPTION_REMOVE_MEK).longOpt(COMMAND_OPTION_REMOVE_MEK_LONG_OPT)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_IAHN_OPTION_REMOVE_MEK)).get()
        );
        list.add(
                Option.builder(COMMAND_OPTION_UPSERT_MESSAGE).longOpt(COMMAND_OPTION_UPSERT_MESSAGE_LONG_OPT)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_IAHN_OPTION_UPSERT_MESSAGE)).get()
        );
        list.add(
                Option.builder(COMMAND_OPTION_BATCH_UPSERT_MESSAGE_BY_LOCALE)
                        .longOpt(COMMAND_OPTION_BATCH_UPSERT_MESSAGE_BY_LOCALE_LONG_OPT)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_IAHN_OPTION_UPSERT_BY_LOCALE)).get()
        );
        list.add(
                Option.builder(COMMAND_OPTION_BATCH_UPSERT_MESSAGE_BY_MEK)
                        .longOpt(COMMAND_OPTION_BATCH_UPSERT_MESSAGE_BY_MEK_LONG_OPT)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_IAHN_OPTION_UPSERT_BY_MEK)).get()
        );
        list.add(
                Option.builder(COMMAND_SUB_OPTION_JSON)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_OPTION_JSON_STRING))
                        .hasArg().type(String.class).get()
        );
        list.add(
                Option.builder(COMMAND_SUB_OPTION_JSON_FILE).longOpt(COMMAND_SUB_OPTION_JSON_FILE_LONG_OPT)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_OPTION_JSON_FILE))
                        .hasArg().type(File.class).get()
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
            case COMMAND_OPTION_INSPECT_MESSAGE:
                handleInspectMessage(context, cmd);
                break;
            case COMMAND_OPTION_BATCH_INSPECT_MESSAGE_BY_LOCALE:
                handleBatchInspectMessageByLocale(context, cmd);
                break;
            case COMMAND_OPTION_INSPECT_LOCALE_LIST:
                handleInspectLocaleList(context, cmd);
                break;
            case COMMAND_OPTION_INSPECT_MEK_LIST:
                handleInspectMekList(context, cmd);
                break;
            case COMMAND_OPTION_INSPECT_MESSAGE_TABLE:
                handleInspectMessageTable(context, cmd);
                break;
            case COMMAND_OPTION_PUT_LOCALE:
                handlePutLocale(context, cmd);
                break;
            case COMMAND_OPTION_REMOVE_LOCALE:
                handleRemoveLocale(context, cmd);
                break;
            case COMMAND_OPTION_PUT_MEK:
                handlePutMek(context, cmd);
                break;
            case COMMAND_OPTION_REMOVE_MEK:
                handleRemoveMek(context, cmd);
                break;
            case COMMAND_OPTION_UPSERT_MESSAGE:
                handleUpsertMessage(context, cmd);
                break;
            case COMMAND_OPTION_BATCH_UPSERT_MESSAGE_BY_LOCALE:
                handleBatchUpsertMessageByLocale(context, cmd);
                break;
            case COMMAND_OPTION_BATCH_UPSERT_MESSAGE_BY_MEK:
                handleBatchUpsertMessageByMek(context, cmd);
                break;
            default:
                throw new IllegalStateException(
                        ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_INTERNAL_UNREACHABLE)
                );
        }
    }

    private void handleInspectMessage(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        IahnNodeMessageInspectInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 IahnNodeMessageInspectInfo。
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputIahnNodeMessageInspectInfo.toStackBean(
                    JSON.parseObject(json, WebInputIahnNodeMessageInspectInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 IahnNodeMessageInspectInfo。
        else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputIahnNodeMessageInspectInfo.toStackBean(
                        JSON.parseObject(in, WebInputIahnNodeMessageInspectInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_UNSUPPORTED));
        }

        // 查询国际化消息。
        IahnNodeMessageInspectResult result = iahnNodeQosService.inspectMessage(info);

        // 输出结果。
        if (Objects.isNull(result)) {
            context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_QUERY_RESULT_NULL));
        } else {
            context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_QUERY_RESULT_HEADER));
            context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_FIELD_MESSAGE, result.getMessage()));
        }
    }

    private void handleBatchInspectMessageByLocale(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        IahnNodeMessageInspectByLocaleInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 IahnNodeMessageInspectByLocaleInfo。
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputIahnNodeMessageInspectByLocaleInfo.toStackBean(
                    JSON.parseObject(json, WebInputIahnNodeMessageInspectByLocaleInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 IahnNodeMessageInspectByLocaleInfo。
        else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputIahnNodeMessageInspectByLocaleInfo.toStackBean(
                        JSON.parseObject(in, WebInputIahnNodeMessageInspectByLocaleInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_UNSUPPORTED));
        }

        // 基于国际化地区批量查询国际化消息。
        IahnNodeMessageInspectByLocaleResult result = iahnNodeQosService.batchInspectMessageByLocale(info);

        if (Objects.isNull(result)) {
            context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_QUERY_RESULT_NULL));
        } else {
            context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_QUERY_RESULT_HEADER));
            List<IahnNodeMessageInspectByLocaleResult.Item> items = result.getItems();
            context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_FIELD_ITEMS_TOTAL_SIZE, items.size()));
            for (int i = 0; i < items.size(); i++) {
                IahnNodeMessageInspectByLocaleResult.Item item = items.get(i);
                context.sendMessage(String.format(
                        "%d / %d: medId = %s, message = %s",
                        i, items.size(), item.getMekId(), item.getMessage()
                ));
            }
        }
    }

    private void handleInspectLocaleList(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        IahnNodeLocaleListInspectInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 IahnNodeLocaleListInspectInfo。
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputIahnNodeLocaleListInspectInfo.toStackBean(
                    JSON.parseObject(json, WebInputIahnNodeLocaleListInspectInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 IahnNodeLocaleListInspectInfo。
        else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputIahnNodeLocaleListInspectInfo.toStackBean(
                        JSON.parseObject(in, WebInputIahnNodeLocaleListInspectInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_UNSUPPORTED));
        }

        // 查询国际化地区列表。
        IahnNodeLocaleListInspectResult result = iahnNodeQosService.inspectLocaleList(info);

        // 输出结果。
        if (Objects.isNull(result)) {
            context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_QUERY_RESULT_NULL));
        } else {
            processLocaleListInspectResultItems(context, result.getItems());
        }
    }

    private void processLocaleListInspectResultItems(
            CommandExecutor.Context context, List<IahnNodeLocaleListInspectResult.Item> items
    ) throws Exception {
        while (true) {
            CliCommandUtil.CropResult cropResult = CliCommandUtil.cropData(
                    context,
                    items,
                    ImplMessages.message(ImplMessageKey.TELQOS_COMMON_FIELD_DATA_TOTAL, items.size())
            );
            if (cropResult.exitFlag()) {
                break;
            }
            context.sendMessage("");
            for (int i = cropResult.beginIndex(); i < cropResult.endIndex(); i++) {
                IahnNodeLocaleListInspectResult.Item item = items.get(i);
                printIahnNodeLocaleListInspectResultItem(context, i, cropResult.endIndex(), item);
            }
        }
    }

    private void printIahnNodeLocaleListInspectResultItem(
            CommandExecutor.Context context, int i, int endIndex, IahnNodeLocaleListInspectResult.Item item
    ) throws Exception {
        context.sendMessage(String.format(ImplMessages.message(ImplMessageKey.TELQOS_IAHN_FIELD_INDEX), i, endIndex));
        if (Objects.isNull(item)) {
            context.sendMessage("null");
        } else {
            context.sendMessage(
                    String.format(ImplMessages.message(ImplMessageKey.TELQOS_IAHN_FIELD_LANGUAGE), item.getLanguage())
            );
            context.sendMessage(
                    String.format(ImplMessages.message(ImplMessageKey.TELQOS_IAHN_FIELD_COUNTRY), item.getCountry())
            );
            context.sendMessage(
                    String.format(ImplMessages.message(ImplMessageKey.TELQOS_IAHN_FIELD_VARIANT), item.getVariant())
            );
            context.sendMessage(
                    String.format(ImplMessages.message(ImplMessageKey.TELQOS_IAHN_FIELD_LABEL), item.getLabel())
            );
            context.sendMessage(
                    String.format(ImplMessages.message(ImplMessageKey.TELQOS_IAHN_FIELD_REMARK), item.getRemark())
            );
        }
        context.sendMessage("");
    }

    private void handleInspectMekList(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        IahnNodeMekListInspectInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 IahnNodeMekListInspectInfo。
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputIahnNodeMekListInspectInfo.toStackBean(
                    JSON.parseObject(json, WebInputIahnNodeMekListInspectInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 IahnNodeMekListInspectInfo。
        else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputIahnNodeMekListInspectInfo.toStackBean(
                        JSON.parseObject(in, WebInputIahnNodeMekListInspectInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_UNSUPPORTED));
        }

        // 查询国际化 Mek 列表。
        IahnNodeMekListInspectResult result = iahnNodeQosService.inspectMekList(info);

        // 输出结果。
        if (Objects.isNull(result)) {
            context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_QUERY_RESULT_NULL));
        } else {
            processMekListInspectResultItems(context, result.getItems());
        }
    }

    private void processMekListInspectResultItems(
            CommandExecutor.Context context, List<IahnNodeMekListInspectResult.Item> items
    ) throws Exception {
        while (true) {
            CliCommandUtil.CropResult cropResult = CliCommandUtil.cropData(
                    context,
                    items,
                    ImplMessages.message(ImplMessageKey.TELQOS_COMMON_FIELD_DATA_TOTAL, items.size())
            );
            if (cropResult.exitFlag()) {
                break;
            }
            context.sendMessage("");
            for (int i = cropResult.beginIndex(); i < cropResult.endIndex(); i++) {
                IahnNodeMekListInspectResult.Item item = items.get(i);
                printIahnNodeMekListInspectResultItem(context, i, cropResult.endIndex(), item);
            }
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void printIahnNodeMekListInspectResultItem(
            CommandExecutor.Context context, int i, int endIndex, IahnNodeMekListInspectResult.Item item
    ) throws Exception {
        context.sendMessage(String.format(ImplMessages.message(ImplMessageKey.TELQOS_IAHN_FIELD_INDEX), i, endIndex));
        if (Objects.isNull(item)) {
            context.sendMessage("null");
        } else {
            context.sendMessage(
                    String.format(ImplMessages.message(ImplMessageKey.TELQOS_IAHN_FIELD_MEK_ID), item.getMekId())
            );
            context.sendMessage(
                    String.format(ImplMessages.message(ImplMessageKey.TELQOS_IAHN_FIELD_LABEL), item.getLabel())
            );
            context.sendMessage(
                    String.format(
                            ImplMessages.message(ImplMessageKey.TELQOS_IAHN_FIELD_DEFAULT_MESSAGE),
                            item.getDefaultMessage()
                    )
            );
            context.sendMessage(
                    String.format(ImplMessages.message(ImplMessageKey.TELQOS_IAHN_FIELD_REMARK), item.getRemark())
            );
        }
        context.sendMessage("");
    }

    private void handleInspectMessageTable(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        IahnNodeMessageTableInspectInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 IahnNodeMessageTableInspectInfo。
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputIahnNodeMessageTableInspectInfo.toStackBean(
                    JSON.parseObject(json, WebInputIahnNodeMessageTableInspectInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 IahnNodeMessageTableInspectInfo。
        else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputIahnNodeMessageTableInspectInfo.toStackBean(
                        JSON.parseObject(in, WebInputIahnNodeMessageTableInspectInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_UNSUPPORTED));
        }

        // 查询国际化消息表。
        IahnNodeMessageTableInspectResult result = iahnNodeQosService.inspectMessageTable(info);

        // 输出结果。
        if (Objects.isNull(result)) {
            context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_QUERY_RESULT_NULL));
        } else {
            processMessageTableInspectResultItems(context, result.getColumns(), result.getRows());
        }
    }

    private void processMessageTableInspectResultItems(
            CommandExecutor.Context context, List<IahnNodeMessageTableInspectResult.Column> columns,
            List<IahnNodeMessageTableInspectResult.Row> rows
    ) throws Exception {
        while (true) {
            CliCommandUtil.CropResult cropResult = CliCommandUtil.cropData(
                    context,
                    rows,
                    ImplMessages.message(ImplMessageKey.TELQOS_COMMON_FIELD_DATA_TOTAL, rows.size())
            );
            if (cropResult.exitFlag()) {
                break;
            }
            context.sendMessage("");
            for (int i = cropResult.beginIndex(); i < cropResult.endIndex(); i++) {
                IahnNodeMessageTableInspectResult.Row row = rows.get(i);
                printIahnNodeMessageTableInspectResultRow(context, i, cropResult.endIndex(), columns, row);
            }
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void printIahnNodeMessageTableInspectResultRow(
            CommandExecutor.Context context, int i, int endIndex,
            List<IahnNodeMessageTableInspectResult.Column> columns, IahnNodeMessageTableInspectResult.Row row
    ) throws Exception {
        // 在打印行之前，先打印列信息。
        context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_IAHN_FIELD_COLUMN_TOTAL, columns.size()));
        for (int ci = 0; ci < columns.size(); ci++) {
            IahnNodeMessageTableInspectResult.Column column = columns.get(ci);
            String language = column.getLanguage();
            String country = column.getCountry();
            String variant = column.getVariant();
            context.sendMessage(
                    String.format(
                            ImplMessages.message(ImplMessageKey.TELQOS_IAHN_FIELD_COLUMN),
                            ci,
                            formatLocale(language, country, variant)
                    )
            );
        }
        // 打印行信息。
        context.sendMessage(String.format(ImplMessages.message(ImplMessageKey.TELQOS_IAHN_FIELD_INDEX), i, endIndex));
        if (Objects.isNull(row)) {
            context.sendMessage("null");
        } else {
            context.sendMessage(
                    String.format(ImplMessages.message(ImplMessageKey.TELQOS_IAHN_FIELD_MEK_ID), row.getMekId())
            );
            context.sendMessage(
                    String.format(ImplMessages.message(ImplMessageKey.TELQOS_IAHN_FIELD_LABEL), row.getLabel())
            );
            context.sendMessage(
                    String.format(
                            ImplMessages.message(ImplMessageKey.TELQOS_IAHN_FIELD_DEFAULT_MESSAGE),
                            row.getDefaultMessage()
                    )
            );
            context.sendMessage(
                    String.format(ImplMessages.message(ImplMessageKey.TELQOS_IAHN_FIELD_REMARK), row.getRemark())
            );
            context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_IAHN_FIELD_ROW_DATAS));
            for (int ri = 0; ri < row.getRowDatas().size(); ri++) {
                IahnNodeMessageTableInspectResult.RowData rowData = row.getRowDatas().get(ri);
                context.sendMessage(String.format(ImplMessages.message(ImplMessageKey.TELQOS_IAHN_FIELD_ROW), ri));
                context.sendMessage(String.format(
                        ImplMessages.message(ImplMessageKey.TELQOS_IAHN_FIELD_ROW_MESSAGE), rowData.getMessage()
                ));
            }
        }
    }

    private String formatLocale(String language, String country, String variant) {
        if (StringUtils.isEmpty(language)) {
            return FORMATTED_LOCALE_DEFAULT;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(language);
        if (StringUtils.isNotEmpty(country)) {
            sb.append("-").append(country);
        }
        if (StringUtils.isNotEmpty(variant)) {
            sb.append("-").append(variant);
        }
        return sb.toString();
    }

    private void handlePutLocale(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        IahnNodeLocalePutInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 IahnNodeLocalePutInfo。
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputIahnNodeLocalePutInfo.toStackBean(
                    JSON.parseObject(json, WebInputIahnNodeLocalePutInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 IahnNodeLocalePutInfo。
        else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputIahnNodeLocalePutInfo.toStackBean(
                        JSON.parseObject(in, WebInputIahnNodeLocalePutInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_UNSUPPORTED));
        }

        // 推入国际化地区。
        iahnNodeQosService.putLocale(info);

        // 输出结果。
        context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_IAHN_RESULT_PUSH_SUCCEEDED));
    }

    private void handleRemoveLocale(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        IahnNodeLocaleRemoveInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 IahnNodeLocaleRemoveInfo。
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputIahnNodeLocaleRemoveInfo.toStackBean(
                    JSON.parseObject(json, WebInputIahnNodeLocaleRemoveInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 IahnNodeLocaleRemoveInfo。
        else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputIahnNodeLocaleRemoveInfo.toStackBean(
                        JSON.parseObject(in, WebInputIahnNodeLocaleRemoveInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_UNSUPPORTED));
        }

        // 移除国际化地区。
        iahnNodeQosService.removeLocale(info);

        // 输出结果。
        context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_REMOVE_SUCCEEDED));
    }

    private void handlePutMek(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        IahnNodeMekPutInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 IahnNodeMekPutInfo。
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputIahnNodeMekPutInfo.toStackBean(
                    JSON.parseObject(json, WebInputIahnNodeMekPutInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 IahnNodeMekPutInfo。
        else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputIahnNodeMekPutInfo.toStackBean(
                        JSON.parseObject(in, WebInputIahnNodeMekPutInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_UNSUPPORTED));
        }

        // 推入国际化 Mek。
        iahnNodeQosService.putMek(info);

        // 输出结果。
        context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_IAHN_RESULT_PUSH_SUCCEEDED));
    }

    private void handleRemoveMek(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        IahnNodeMekRemoveInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 IahnNodeMekRemoveInfo。
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputIahnNodeMekRemoveInfo.toStackBean(
                    JSON.parseObject(json, WebInputIahnNodeMekRemoveInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 IahnNodeMekRemoveInfo。
        else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputIahnNodeMekRemoveInfo.toStackBean(
                        JSON.parseObject(in, WebInputIahnNodeMekRemoveInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_UNSUPPORTED));
        }

        // 移除国际化 Mek。
        iahnNodeQosService.removeMek(info);

        // 输出结果。
        context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_REMOVE_SUCCEEDED));
    }

    private void handleUpsertMessage(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        IahnNodeMessageUpsertInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 IahnNodeMessageUpsertInfo。
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputIahnNodeMessageUpsertInfo.toStackBean(
                    JSON.parseObject(json, WebInputIahnNodeMessageUpsertInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 IahnNodeMessageUpsertInfo。
        else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputIahnNodeMessageUpsertInfo.toStackBean(
                        JSON.parseObject(in, WebInputIahnNodeMessageUpsertInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_UNSUPPORTED));
        }

        // 插入或更新国际化消息。
        iahnNodeQosService.upsertMessage(info);

        // 输出结果。
        context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_IAHN_RESULT_UPSERT_SUCCEEDED));
    }

    private void handleBatchUpsertMessageByLocale(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        IahnNodeMessageUpsertByLocaleInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 IahnNodeMessageUpsertByLocaleInfo。
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputIahnNodeMessageUpsertByLocaleInfo.toStackBean(
                    JSON.parseObject(json, WebInputIahnNodeMessageUpsertByLocaleInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 IahnNodeMessageUpsertByLocaleInfo。
        else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputIahnNodeMessageUpsertByLocaleInfo.toStackBean(
                        JSON.parseObject(in, WebInputIahnNodeMessageUpsertByLocaleInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_UNSUPPORTED));
        }

        // 基于国际化地区批量插入或更新国际化消息。
        iahnNodeQosService.batchUpsertMessageByLocale(info);

        // 输出结果。
        context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_IAHN_RESULT_BATCH_UPSERT_SUCCEEDED));
    }

    private void handleBatchUpsertMessageByMek(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        IahnNodeMessageUpsertByMekInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 IahnNodeMessageUpsertByMekInfo。
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputIahnNodeMessageUpsertByMekInfo.toStackBean(
                    JSON.parseObject(json, WebInputIahnNodeMessageUpsertByMekInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 IahnNodeMessageUpsertByMekInfo。
        else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputIahnNodeMessageUpsertByMekInfo.toStackBean(
                        JSON.parseObject(in, WebInputIahnNodeMessageUpsertByMekInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_UNSUPPORTED));
        }

        // 基于国际化 Mek 批量插入或更新国际化消息。
        iahnNodeQosService.batchUpsertMessageByMek(info);

        // 输出结果。
        context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_IAHN_RESULT_BATCH_UPSERT_SUCCEEDED));
    }
}
