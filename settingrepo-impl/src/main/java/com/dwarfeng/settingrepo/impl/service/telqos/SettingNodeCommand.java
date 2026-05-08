package com.dwarfeng.settingrepo.impl.service.telqos;

import com.alibaba.fastjson.JSON;
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
        return context -> "设置节点操作服务";
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

    @Override
    protected List<Option> provideOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder(COMMAND_OPTION_EXISTS).optionalArg(true).hasArg(false).desc("判断指定的设置节点是否存在").build());
        list.add(Option.builder(COMMAND_OPTION_INSPECT).optionalArg(true).hasArg(false).desc("查看指定的设置节点").build());
        list.add(Option.builder(COMMAND_OPTION_INIT).optionalArg(true).hasArg(false).desc("初始化指定的设置节点").build());
        list.add(Option.builder(COMMAND_OPTION_REMOVE).optionalArg(true).hasArg(false).desc("移除指定的设置节点").build());
        list.add(Option.builder(COMMAND_SUB_OPTION_JSON).hasArg(true).type(String.class).desc("JSON 字符串").build());
        list.add(
                Option.builder(COMMAND_SUB_OPTION_JSON_FILE).longOpt(COMMAND_SUB_OPTION_JSON_FILE_LONG_OPT)
                        .hasArg(true).type(File.class).desc("JSON 文件").build()
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
                throw new IllegalStateException("不应该执行到此处, 请联系开发人员");
        }
    }

    private void handleExists(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        SettingNodeExistsInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputSettingNodeExistsInfo.toStackBean(
                    JSON.parseObject(json, WebInputSettingNodeExistsInfo.class)
            );
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputSettingNodeExistsInfo.toStackBean(
                        JSON.parseObject(in, WebInputSettingNodeExistsInfo.class)
                );
            }
        } else {
            throw new UnsupportedOperationException("not supported yet");
        }

        SettingNodeExistsResult result = settingNodeQosService.exists(info);
        context.sendMessage("节点存在: " + result.isExists());
    }

    private void handleInspect(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        SettingNodeInspectInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputSettingNodeInspectInfo.toStackBean(
                    JSON.parseObject(json, WebInputSettingNodeInspectInfo.class)
            );
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputSettingNodeInspectInfo.toStackBean(
                        JSON.parseObject(in, WebInputSettingNodeInspectInfo.class)
                );
            }
        } else {
            throw new UnsupportedOperationException("not supported yet");
        }

        SettingNodeInspectResult result = settingNodeQosService.inspect(info);
        if (Objects.isNull(result)) {
            context.sendMessage("查询结果: null");
            return;
        }
        context.sendMessage("查询结果: ");
        context.sendMessage("  type: " + result.getType() + " (" + formatSettingNodeType(result.getType()) + ")");
        context.sendMessage("  lastModifiedDate: " + result.getLastModifiedDate());
        context.sendMessage("  remark: " + result.getRemark());
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

    private void handleInit(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        SettingNodeInitInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputSettingNodeInitInfo.toStackBean(JSON.parseObject(json, WebInputSettingNodeInitInfo.class));
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputSettingNodeInitInfo.toStackBean(JSON.parseObject(in, WebInputSettingNodeInitInfo.class));
            }
        } else {
            throw new UnsupportedOperationException("not supported yet");
        }

        settingNodeQosService.init(info);
        context.sendMessage("初始化成功");
    }

    private void handleRemove(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        SettingNodeRemoveInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputSettingNodeRemoveInfo.toStackBean(
                    JSON.parseObject(json, WebInputSettingNodeRemoveInfo.class)
            );
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputSettingNodeRemoveInfo.toStackBean(
                        JSON.parseObject(in, WebInputSettingNodeRemoveInfo.class)
                );
            }
        } else {
            throw new UnsupportedOperationException("not supported yet");
        }

        settingNodeQosService.remove(info);
        context.sendMessage("删除成功");
    }
}
