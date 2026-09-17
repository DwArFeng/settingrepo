package com.dwarfeng.settingrepo.impl.service.telqos;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.dutil.basic.sdk.io.IOUtil;
import com.dwarfeng.settingrepo.impl.internal.i18n.ImplMessageKey;
import com.dwarfeng.settingrepo.impl.internal.i18n.ImplMessages;
import com.dwarfeng.settingrepo.sdk.bean.dto.WebInputFileNodeInspectInfo;
import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.service.FileNodeQosService;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.sdk.configuration.TelqosCommand;
import com.dwarfeng.springtelqos.sdk.util.CliCommandUtil;
import com.dwarfeng.springtelqos.stack.command.CommandDescriptor;
import com.dwarfeng.springtelqos.stack.command.CommandExecutor;
import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.lang3.tuple.Pair;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 文件节点指令。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
@TelqosCommand
public class FileNodeCommand extends CliCommand {

    @SuppressWarnings({"SpellCheckingInspection", "GrazieInspectionRunner", "RedundantSuppression"})
    private static final String IDENTITY = "filenode";

    // region 指令选项

    private static final String COMMAND_OPTION_INSPECT = "inspect";
    private static final String COMMAND_OPTION_DOWNLOAD = "download";
    private static final String COMMAND_OPTION_UPLOAD = "upload";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_INSPECT,
            COMMAND_OPTION_DOWNLOAD,
            COMMAND_OPTION_UPLOAD
    };

    private static final String COMMAND_SUB_OPTION_JSON = "json";
    private static final String COMMAND_SUB_OPTION_JSON_FILE = "jf";
    private static final String COMMAND_SUB_OPTION_JSON_FILE_LONG_OPT = "json-file";

    // endregion

    private final FileNodeQosService fileNodeQosService;

    public FileNodeCommand(FileNodeQosService fileNodeQosService) {
        super(IDENTITY);
        this.fileNodeQosService = fileNodeQosService;
    }

    @Override
    protected DescriptionProvider provideDescriptionProvider() {
        return _ -> ImplMessages.message(ImplMessageKey.TELQOS_FILE_DESCRIPTION);
    }

    @Override
    protected CliSyntaxProvider provideCliSyntaxProvider() {
        return this::cliSyntaxProvider;
    }

    @SuppressWarnings("DuplicatedCode")
    private String cliSyntaxProvider(CommandDescriptor.Context context) throws Exception {
        String identity = context.getRuntimeIdentity();
        String[] patterns = new String[]{
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_INSPECT) +
                        " [" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] " +
                        "[" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_DOWNLOAD) +
                        " [" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] " +
                        "[" + CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_UPLOAD) +
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
                Option.builder(COMMAND_OPTION_INSPECT).optionalArg(true).hasArg(false)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_FILE_OPTION_INSPECT)).get()
        );
        list.add(
                Option.builder(COMMAND_OPTION_DOWNLOAD).optionalArg(true).hasArg(false)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_FILE_OPTION_DOWNLOAD)).get()
        );
        list.add(
                Option.builder(COMMAND_OPTION_UPLOAD).optionalArg(true).hasArg(false)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_FILE_OPTION_UPLOAD)).get()
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

    @SuppressWarnings("DuplicatedCode")
    @Override
    protected void executeWithCmd(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        Pair<String, Integer> pair = CliCommandUtil.analyseCommand(cmd, COMMAND_OPTION_ARRAY);
        if (pair.getRight() != 1) {
            context.sendMessage(CliCommandUtil.optionMismatchMessage(COMMAND_OPTION_ARRAY));
            context.sendMessage(context.getCommandManual(context.getRuntimeIdentity()));
            return;
        }
        switch (pair.getLeft()) {
            case COMMAND_OPTION_INSPECT:
                handleInspect(context, cmd);
                break;
            case COMMAND_OPTION_DOWNLOAD:
                handleDownload(context, cmd);
                break;
            case COMMAND_OPTION_UPLOAD:
                handleUpload(context, cmd);
                break;
            default:
                throw new IllegalStateException(
                        ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_INTERNAL_UNREACHABLE)
                );
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void handleInspect(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        FileNodeInspectInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputFileNodeInspectInfo.toStackBean(JSON.parseObject(json, WebInputFileNodeInspectInfo.class));
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputFileNodeInspectInfo.toStackBean(
                        JSON.parseObject(in, WebInputFileNodeInspectInfo.class)
                );
            }
        } else {
            throw new UnsupportedOperationException(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_UNSUPPORTED));
        }

        FileNodeInspectResult result = fileNodeQosService.inspect(info);
        if (result == null) {
            context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_INSPECT_RESULT_NULL));
            return;
        }
        context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_INSPECT_RESULT_HEADER));
        context.sendMessage(
                ImplMessages.message(ImplMessageKey.TELQOS_COMMON_FIELD_ORIGIN_NAME, result.getOriginName())
        );
        context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_FIELD_LENGTH, result.getLength()));
    }

    @SuppressWarnings("DuplicatedCode")
    private void handleDownload(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        DownloadInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = JSON.parseObject(json, DownloadInfo.class);
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = JSON.parseObject(in, DownloadInfo.class);
            }
        } else {
            throw new UnsupportedOperationException(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_UNSUPPORTED));
        }

        FileNodeFileStream fileNodeFileStream = fileNodeQosService.downloadFileStream(
                new FileNodeFileStreamDownloadInfo(info.getCategory(), info.getArgs())
        );
        if (Objects.isNull(fileNodeFileStream)) {
            context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_FILE_NOT_FOUND));
            return;
        }

        InputStream in = null;
        OutputStream out = null;
        try {
            in = fileNodeFileStream.getContent();
            out = Files.newOutputStream(
                    new File(info.getFilePath()).toPath(),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING
            );
            IOUtil.trans(in, out, 4096);
        } finally {
            if (Objects.nonNull(in)) {
                in.close();
            }
            if (Objects.nonNull(out)) {
                out.close();
            }
        }
        context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_FILE_DOWNLOAD_SUCCEEDED));
    }

    @SuppressWarnings("DuplicatedCode")
    private void handleUpload(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        UploadInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = cmd.getOptionValue(COMMAND_SUB_OPTION_JSON);
            info = JSON.parseObject(json, UploadInfo.class);
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = JSON.parseObject(in, UploadInfo.class);
            }
        } else {
            throw new UnsupportedOperationException(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_UNSUPPORTED));
        }

        InputStream in = null;
        try {
            File file = new File(info.getFilePath());
            String originName = file.getName();
            long length = file.length();
            in = Files.newInputStream(file.toPath());
            fileNodeQosService.uploadFileStream(
                    new FileNodeFileStreamUploadInfo(info.getCategory(), info.getArgs(), originName, length, in)
            );
        } finally {
            if (Objects.nonNull(in)) {
                in.close();
            }
        }
        context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_FILE_UPLOAD_SUCCEEDED));
    }

    public static class DownloadInfo implements Dto {

        @Serial
        private static final long serialVersionUID = -3747479124063413591L;

        @JSONField(name = "category")
        @NotNull
        @NotEmpty
        private String category;

        @JSONField(name = "args")
        @NotNull
        private String[] args;

        @JSONField(name = "file_path")
        @NotNull
        @NotEmpty
        private String filePath;

        public DownloadInfo() {
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String[] getArgs() {
            return args;
        }

        public void setArgs(String[] args) {
            this.args = args;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        @Override
        public String toString() {
            return "DownloadInfo{" +
                    "category='" + category + '\'' +
                    ", args=" + Arrays.toString(args) +
                    ", filePath='" + filePath + '\'' +
                    '}';
        }
    }

    public static class UploadInfo implements Dto {

        @Serial
        private static final long serialVersionUID = -726455623791383908L;

        @JSONField(name = "category")
        @NotNull
        @NotEmpty
        private String category;

        @JSONField(name = "args")
        @NotNull
        private String[] args;

        @JSONField(name = "file_path")
        @NotNull
        @NotEmpty
        private String filePath;

        public UploadInfo() {
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String[] getArgs() {
            return args;
        }

        public void setArgs(String[] args) {
            this.args = args;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        @Override
        public String toString() {
            return "UploadInfo{" +
                    "category='" + category + '\'' +
                    ", args=" + Arrays.toString(args) +
                    ", filePath='" + filePath + '\'' +
                    '}';
        }
    }
}
