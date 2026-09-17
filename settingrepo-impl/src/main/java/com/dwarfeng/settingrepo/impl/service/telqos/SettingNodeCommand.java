package com.dwarfeng.settingrepo.impl.service.telqos;

import com.alibaba.fastjson2.JSON;
import com.dwarfeng.settingrepo.impl.internal.i18n.ImplMessageKey;
import com.dwarfeng.settingrepo.impl.internal.i18n.ImplMessages;
import com.dwarfeng.settingrepo.sdk.bean.dto.WebInputSettingNodeExistsInfo;
import com.dwarfeng.settingrepo.sdk.bean.dto.WebInputSettingNodeInitInfo;
import com.dwarfeng.settingrepo.sdk.bean.dto.WebInputSettingNodeInspectInfo;
import com.dwarfeng.settingrepo.sdk.bean.dto.WebInputSettingNodeRemoveInfo;
import com.dwarfeng.settingrepo.sdk.util.Constants;
import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.service.SettingNodeQosService;
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

/**
 * 设置节点指令。
 *
 * <p>
 * 该指令提供设置节点相关操作。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
@TelqosCommand
public class SettingNodeCommand extends CliCommand {

    @SuppressWarnings({"SpellCheckingInspection", "GrazieInspectionRunner", "RedundantSuppression"})
    private static final String IDENTITY = "settingnode";

    // region 指令选项

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

    private static final String COMMAND_SUB_OPTION_JSON = "json";
    private static final String COMMAND_SUB_OPTION_JSON_FILE = "jf";
    private static final String COMMAND_SUB_OPTION_JSON_FILE_LONG_OPT = "json-file";

    // endregion

    private final SettingNodeQosService settingNodeQosService;

    public SettingNodeCommand(SettingNodeQosService settingNodeQosService) {
        super(IDENTITY);
        this.settingNodeQosService = settingNodeQosService;
    }

    @Override
    protected DescriptionProvider provideDescriptionProvider() {
        return _ -> ImplMessages.message(ImplMessageKey.TELQOS_SETTING_DESCRIPTION);
    }

    @Override
    protected CliSyntaxProvider provideCliSyntaxProvider() {
        return this::cliSyntaxProvider;
    }

    private String cliSyntaxProvider(CommandDescriptor.Context context) throws Exception {
        String identity = context.getRuntimeIdentity();
        String[] patterns = new String[]{
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_EXISTS) +
                        " [" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] " +
                        "[" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_INSPECT) +
                        " [" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] " +
                        "[" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_INIT) +
                        " [" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] " +
                        "[" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_REMOVE) +
                        " [" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] " +
                        "[" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]"
        };
        return CliCommandUtil.cliSyntax(patterns);
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    protected List<Option> provideOptions() {
        List<Option> list = new ArrayList<>();
        list.add(
                Option.builder(COMMAND_OPTION_EXISTS).optionalArg(true).hasArg(false)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_SETTING_OPTION_EXISTS)).get()
        );
        list.add(
                Option.builder(COMMAND_OPTION_INSPECT).optionalArg(true).hasArg(false)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_SETTING_OPTION_INSPECT)).get()
        );
        list.add(
                Option.builder(COMMAND_OPTION_INIT).optionalArg(true).hasArg(false)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_SETTING_OPTION_INIT)).get()
        );
        list.add(
                Option.builder(COMMAND_OPTION_REMOVE).optionalArg(true).hasArg(false)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_SETTING_OPTION_REMOVE)).get()
        );
        list.add(
                Option.builder(COMMAND_SUB_OPTION_JSON).hasArg(true).type(String.class)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_OPTION_JSON_STRING)).get()
        );
        list.add(
                Option.builder(COMMAND_SUB_OPTION_JSON_FILE).longOpt(COMMAND_SUB_OPTION_JSON_FILE_LONG_OPT)
                        .hasArg(true).type(File.class)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_OPTION_JSON_FILE)).get()
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
            default:
                throw new IllegalStateException(
                        ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_INTERNAL_UNREACHABLE)
                );
        }
    }

    private void handleExists(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        SettingNodeExistsInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputSettingNodeExistsInfo.toStackBean(
                    JSON.parseObject(json, WebInputSettingNodeExistsInfo.class)
            );
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputSettingNodeExistsInfo.toStackBean(
                        JSON.parseObject(in, WebInputSettingNodeExistsInfo.class)
                );
            }
        } else {
            throw new UnsupportedOperationException(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_UNSUPPORTED));
        }

        SettingNodeExistsResult result = settingNodeQosService.exists(info);
        context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_SETTING_RESULT_EXISTS, result.isExists()));
    }

    private void handleInspect(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        SettingNodeInspectInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputSettingNodeInspectInfo.toStackBean(
                    JSON.parseObject(json, WebInputSettingNodeInspectInfo.class)
            );
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputSettingNodeInspectInfo.toStackBean(
                        JSON.parseObject(in, WebInputSettingNodeInspectInfo.class)
                );
            }
        } else {
            throw new UnsupportedOperationException(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_UNSUPPORTED));
        }

        SettingNodeInspectResult result = settingNodeQosService.inspect(info);
        if (Objects.isNull(result)) {
            context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_QUERY_RESULT_NULL));
            return;
        }
        context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_QUERY_RESULT_HEADER));
        context.sendMessage(
                ImplMessages.message(
                        ImplMessageKey.TELQOS_SETTING_FIELD_TYPE,
                        result.getType(),
                        formatSettingNodeType(result.getType())
                )
        );
        context.sendMessage(
                ImplMessages.message(
                        ImplMessageKey.TELQOS_SETTING_FIELD_LAST_MODIFIED_DATE,
                        result.getLastModifiedDate()
                )
        );
        context.sendMessage(
                ImplMessages.message(ImplMessageKey.TELQOS_SETTING_FIELD_REMARK, result.getRemark())
        );
    }

    private String formatSettingNodeType(int type) {
        return switch (type) {
            case Constants.SETTING_NODE_TYPE_TEXT -> ImplMessages.message(ImplMessageKey.TELQOS_SETTING_TYPE_TEXT);
            case Constants.SETTING_NODE_TYPE_LONG_TEXT ->
                    ImplMessages.message(ImplMessageKey.TELQOS_SETTING_TYPE_LONG_TEXT);
            case Constants.SETTING_NODE_TYPE_IMAGE -> ImplMessages.message(ImplMessageKey.TELQOS_SETTING_TYPE_IMAGE);
            case Constants.SETTING_NODE_TYPE_IMAGE_LIST ->
                    ImplMessages.message(ImplMessageKey.TELQOS_SETTING_TYPE_IMAGE_LIST);
            default -> ImplMessages.message(ImplMessageKey.TELQOS_SETTING_TYPE_UNKNOWN);
        };
    }

    private void handleInit(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        SettingNodeInitInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputSettingNodeInitInfo.toStackBean(JSON.parseObject(json, WebInputSettingNodeInitInfo.class));
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputSettingNodeInitInfo.toStackBean(JSON.parseObject(in, WebInputSettingNodeInitInfo.class));
            }
        } else {
            throw new UnsupportedOperationException(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_UNSUPPORTED));
        }

        settingNodeQosService.init(info);
        context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_SETTING_RESULT_INIT_SUCCEEDED));
    }

    private void handleRemove(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        SettingNodeRemoveInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputSettingNodeRemoveInfo.toStackBean(
                    JSON.parseObject(json, WebInputSettingNodeRemoveInfo.class)
            );
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputSettingNodeRemoveInfo.toStackBean(
                        JSON.parseObject(in, WebInputSettingNodeRemoveInfo.class)
                );
            }
        } else {
            throw new UnsupportedOperationException(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_UNSUPPORTED));
        }

        settingNodeQosService.remove(info);
        context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_SETTING_RESULT_DELETE_SUCCEEDED));
    }
}
