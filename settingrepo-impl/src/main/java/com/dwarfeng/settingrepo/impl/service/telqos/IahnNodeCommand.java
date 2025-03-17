package com.dwarfeng.settingrepo.impl.service.telqos;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.settingrepo.sdk.bean.dto.*;
import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.service.IahnNodeQosService;
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
public class IahnNodeCommand extends CliCommand {

    private static final String COMMAND_OPTION_INSPECT_MESSAGE = "im";
    private static final String COMMAND_OPTION_INSPECT_MESSAGE_LONG_OPT = "inspect-message";
    private static final String COMMAND_OPTION_BATCH_INSPECT_MESSAGE_BY_LOCALE = "biml";
    private static final String COMMAND_OPTION_BATCH_INSPECT_MESSAGE_BY_LOCALE_LONG_OPT
            = "batch-inspect-message-by-locale";
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
            COMMAND_OPTION_PUT_LOCALE,
            COMMAND_OPTION_REMOVE_LOCALE,
            COMMAND_OPTION_PUT_MEK,
            COMMAND_OPTION_REMOVE_MEK,
            COMMAND_OPTION_UPSERT_MESSAGE,
            COMMAND_OPTION_BATCH_UPSERT_MESSAGE_BY_LOCALE,
            COMMAND_OPTION_BATCH_UPSERT_MESSAGE_BY_MEK
    };

    private static final String COMMAND_OPTION_JSON = "json";
    private static final String COMMAND_OPTION_JSON_FILE = "jf";
    private static final String COMMAND_OPTION_JSON_FILE_LONG_OPT = "json-file";

    @SuppressWarnings("SpellCheckingInspection")
    private static final String IDENTITY = "iahnnode";
    private static final String DESCRIPTION = "国际化节点操作服务";

    private static final String CMD_LINE_SYNTAX_INSPECT_MESSAGE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_INSPECT_MESSAGE) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_BATCH_INSPECT_MESSAGE_BY_LOCALE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_BATCH_INSPECT_MESSAGE_BY_LOCALE) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_PUT_LOCALE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_PUT_LOCALE) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_REMOVE_LOCALE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_REMOVE_LOCALE) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_PUT_MEK = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_PUT_MEK) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_REMOVE_MEK = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_REMOVE_MEK) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_UPSERT_MESSAGE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_UPSERT_MESSAGE) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_BATCH_UPSERT_MESSAGE_BY_LOCALE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_BATCH_UPSERT_MESSAGE_BY_LOCALE) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_BATCH_UPSERT_MESSAGE_BY_MEK = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_BATCH_UPSERT_MESSAGE_BY_MEK) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";

    private static final String[] CMD_LINE_ARRAY = {
            CMD_LINE_SYNTAX_INSPECT_MESSAGE,
            CMD_LINE_SYNTAX_BATCH_INSPECT_MESSAGE_BY_LOCALE,
            CMD_LINE_SYNTAX_PUT_LOCALE,
            CMD_LINE_SYNTAX_REMOVE_LOCALE,
            CMD_LINE_SYNTAX_PUT_MEK,
            CMD_LINE_SYNTAX_REMOVE_MEK,
            CMD_LINE_SYNTAX_UPSERT_MESSAGE,
            CMD_LINE_SYNTAX_BATCH_UPSERT_MESSAGE_BY_LOCALE,
            CMD_LINE_SYNTAX_BATCH_UPSERT_MESSAGE_BY_MEK
    };

    private static final String CMD_LINE_SYNTAX = CommandUtil.syntax(CMD_LINE_ARRAY);

    private final IahnNodeQosService iahnNodeQosService;

    public IahnNodeCommand(IahnNodeQosService iahnNodeQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.iahnNodeQosService = iahnNodeQosService;
    }

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        list.add(
                Option.builder(COMMAND_OPTION_INSPECT_MESSAGE).longOpt(COMMAND_OPTION_INSPECT_MESSAGE_LONG_OPT)
                        .desc("查询国际化消息").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_BATCH_INSPECT_MESSAGE_BY_LOCALE)
                        .longOpt(COMMAND_OPTION_BATCH_INSPECT_MESSAGE_BY_LOCALE_LONG_OPT)
                        .desc("基于国际化地区批量查询国际化消息").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_PUT_LOCALE).longOpt(COMMAND_OPTION_PUT_LOCALE_LONG_OPT)
                        .desc("推入国际化地区").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_REMOVE_LOCALE).longOpt(COMMAND_OPTION_REMOVE_LOCALE_LONG_OPT)
                        .desc("移除国际化地区").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_PUT_MEK).longOpt(COMMAND_OPTION_PUT_MEK_LONG_OPT)
                        .desc("推入国际化 Mek").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_REMOVE_MEK).longOpt(COMMAND_OPTION_REMOVE_MEK_LONG_OPT)
                        .desc("移除国际化 Mek").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_UPSERT_MESSAGE).longOpt(COMMAND_OPTION_UPSERT_MESSAGE_LONG_OPT)
                        .desc("插入或更新国际化消息").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_BATCH_UPSERT_MESSAGE_BY_LOCALE)
                        .longOpt(COMMAND_OPTION_BATCH_UPSERT_MESSAGE_BY_LOCALE_LONG_OPT)
                        .desc("基于国际化地区批量插入或更新国际化消息").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_BATCH_UPSERT_MESSAGE_BY_MEK)
                        .longOpt(COMMAND_OPTION_BATCH_UPSERT_MESSAGE_BY_MEK_LONG_OPT)
                        .desc("基于国际化 Mek 批量插入或更新国际化消息").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_JSON).desc("JSON字符串").hasArg().type(String.class).build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_JSON_FILE).longOpt(COMMAND_OPTION_JSON_FILE_LONG_OPT).desc("JSON文件")
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
                case COMMAND_OPTION_INSPECT_MESSAGE:
                    handleInspectMessage(context, cmd);
                    break;
                case COMMAND_OPTION_BATCH_INSPECT_MESSAGE_BY_LOCALE:
                    handleBatchInspectMessageByLocale(context, cmd);
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
            }
        } catch (Exception e) {
            throw new TelqosException(e);
        }
    }

    private void handleInspectMessage(Context context, CommandLine cmd) throws Exception {
        IahnNodeMessageInspectInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 IahnNodeMessageInspectInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputIahnNodeMessageInspectInfo.toStackBean(
                    JSON.parseObject(json, WebInputIahnNodeMessageInspectInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 IahnNodeMessageInspectInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputIahnNodeMessageInspectInfo.toStackBean(
                        JSON.parseObject(in, WebInputIahnNodeMessageInspectInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 查询国际化消息。
        IahnNodeMessageInspectResult result = iahnNodeQosService.inspectMessage(info);

        // 输出结果。
        if (Objects.isNull(result)) {
            context.sendMessage("查询结果: null");
        } else {
            context.sendMessage("查询结果: ");
            context.sendMessage("  message: " + result.getMessage());
        }
    }

    private void handleBatchInspectMessageByLocale(Context context, CommandLine cmd) throws Exception {
        IahnNodeMessageInspectByLocaleInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 IahnNodeMessageInspectByLocaleInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputIahnNodeMessageInspectByLocaleInfo.toStackBean(
                    JSON.parseObject(json, WebInputIahnNodeMessageInspectByLocaleInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 IahnNodeMessageInspectByLocaleInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputIahnNodeMessageInspectByLocaleInfo.toStackBean(
                        JSON.parseObject(in, WebInputIahnNodeMessageInspectByLocaleInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 基于国际化地区批量查询国际化消息。
        IahnNodeMessageInspectByLocaleResult result = iahnNodeQosService.batchInspectMessageByLocale(info);

        if (Objects.isNull(result)) {
            context.sendMessage("查询结果: null");
        } else {
            context.sendMessage("查询结果: ");
            List<IahnNodeMessageInspectByLocaleResult.Item> items = result.getItems();
            context.sendMessage("  items: total size: " + items.size());
            for (int i = 0; i < items.size(); i++) {
                IahnNodeMessageInspectByLocaleResult.Item item = items.get(i);
                context.sendMessage(String.format(
                        "%d / %d: medId = %s, message = %s",
                        i, items.size(), item.getMekId(), item.getMessage()
                ));
            }
        }
    }

    private void handlePutLocale(Context context, CommandLine cmd) throws Exception {
        IahnNodeLocalePutInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 IahnNodeLocalePutInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputIahnNodeLocalePutInfo.toStackBean(
                    JSON.parseObject(json, WebInputIahnNodeLocalePutInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 IahnNodeLocalePutInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputIahnNodeLocalePutInfo.toStackBean(
                        JSON.parseObject(in, WebInputIahnNodeLocalePutInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 推入国际化地区。
        iahnNodeQosService.putLocale(info);

        // 输出结果。
        context.sendMessage("推入成功");
    }

    private void handleRemoveLocale(Context context, CommandLine cmd) throws Exception {
        IahnNodeLocaleRemoveInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 IahnNodeLocaleRemoveInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputIahnNodeLocaleRemoveInfo.toStackBean(
                    JSON.parseObject(json, WebInputIahnNodeLocaleRemoveInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 IahnNodeLocaleRemoveInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputIahnNodeLocaleRemoveInfo.toStackBean(
                        JSON.parseObject(in, WebInputIahnNodeLocaleRemoveInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 移除国际化地区。
        iahnNodeQosService.removeLocale(info);

        // 输出结果。
        context.sendMessage("移除成功");
    }

    private void handlePutMek(Context context, CommandLine cmd) throws Exception {
        IahnNodeMekPutInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 IahnNodeMekPutInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputIahnNodeMekPutInfo.toStackBean(
                    JSON.parseObject(json, WebInputIahnNodeMekPutInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 IahnNodeMekPutInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputIahnNodeMekPutInfo.toStackBean(
                        JSON.parseObject(in, WebInputIahnNodeMekPutInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 推入国际化 Mek。
        iahnNodeQosService.putMek(info);

        // 输出结果。
        context.sendMessage("推入成功");
    }

    private void handleRemoveMek(Context context, CommandLine cmd) throws Exception {
        IahnNodeMekRemoveInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 IahnNodeMekRemoveInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputIahnNodeMekRemoveInfo.toStackBean(
                    JSON.parseObject(json, WebInputIahnNodeMekRemoveInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 IahnNodeMekRemoveInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputIahnNodeMekRemoveInfo.toStackBean(
                        JSON.parseObject(in, WebInputIahnNodeMekRemoveInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 移除国际化 Mek。
        iahnNodeQosService.removeMek(info);

        // 输出结果。
        context.sendMessage("移除成功");
    }

    private void handleUpsertMessage(Context context, CommandLine cmd) throws Exception {
        IahnNodeMessageUpsertInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 IahnNodeMessageUpsertInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputIahnNodeMessageUpsertInfo.toStackBean(
                    JSON.parseObject(json, WebInputIahnNodeMessageUpsertInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 IahnNodeMessageUpsertInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputIahnNodeMessageUpsertInfo.toStackBean(
                        JSON.parseObject(in, WebInputIahnNodeMessageUpsertInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 插入或更新国际化消息。
        iahnNodeQosService.upsertMessage(info);

        // 输出结果。
        context.sendMessage("插入或更新成功");
    }

    private void handleBatchUpsertMessageByLocale(Context context, CommandLine cmd) throws Exception {
        IahnNodeMessageUpsertByLocaleInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 IahnNodeMessageUpsertByLocaleInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputIahnNodeMessageUpsertByLocaleInfo.toStackBean(
                    JSON.parseObject(json, WebInputIahnNodeMessageUpsertByLocaleInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 IahnNodeMessageUpsertByLocaleInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputIahnNodeMessageUpsertByLocaleInfo.toStackBean(
                        JSON.parseObject(in, WebInputIahnNodeMessageUpsertByLocaleInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 基于国际化地区批量插入或更新国际化消息。
        iahnNodeQosService.batchUpsertMessageByLocale(info);

        // 输出结果。
        context.sendMessage("批量插入或更新成功");
    }

    private void handleBatchUpsertMessageByMek(Context context, CommandLine cmd) throws Exception {
        IahnNodeMessageUpsertByMekInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 IahnNodeMessageUpsertByMekInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputIahnNodeMessageUpsertByMekInfo.toStackBean(
                    JSON.parseObject(json, WebInputIahnNodeMessageUpsertByMekInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 IahnNodeMessageUpsertByMekInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputIahnNodeMessageUpsertByMekInfo.toStackBean(
                        JSON.parseObject(in, WebInputIahnNodeMessageUpsertByMekInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 基于国际化 Mek 批量插入或更新国际化消息。
        iahnNodeQosService.batchUpsertMessageByMek(info);

        // 输出结果。
        context.sendMessage("批量插入或更新成功");
    }
}
