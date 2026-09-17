package com.dwarfeng.settingrepo.impl.service.telqos;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.dutil.basic.sdk.io.IOUtil;
import com.dwarfeng.settingrepo.impl.internal.i18n.ImplMessageKey;
import com.dwarfeng.settingrepo.impl.internal.i18n.ImplMessages;
import com.dwarfeng.settingrepo.sdk.bean.dto.WebInputFileListNodeChangeOrderInfo;
import com.dwarfeng.settingrepo.sdk.bean.dto.WebInputFileListNodeInspectInfo;
import com.dwarfeng.settingrepo.sdk.bean.dto.WebInputFileListNodeRemoveInfo;
import com.dwarfeng.settingrepo.sdk.bean.dto.WebInputFileListNodeSizeInfo;
import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.service.FileListNodeQosService;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.sdk.configuration.TelqosCommand;
import com.dwarfeng.springtelqos.sdk.util.CliCommandUtil;
import com.dwarfeng.springtelqos.stack.command.CommandDescriptor;
import com.dwarfeng.springtelqos.stack.command.CommandExecutor;
import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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

@TelqosCommand
public class FileListNodeCommand extends CliCommand {

    @SuppressWarnings({"SpellCheckingInspection", "GrazieInspectionRunner", "RedundantSuppression"})
    private static final String IDENTITY = "filelnode";

    // region 指令选项

    private static final String COMMAND_OPTION_SIZE = "size";
    private static final String COMMAND_OPTION_INSPECT = "inspect";
    private static final String COMMAND_OPTION_DOWNLOAD = "download";
    private static final String COMMAND_OPTION_UPLOAD = "upload";
    private static final String COMMAND_OPTION_UPDATE = "update";
    private static final String COMMAND_OPTION_CHANGE_ORDER = "co";
    private static final String COMMAND_OPTION_CHANGE_ORDER_LONG_OPT = "change-order";
    private static final String COMMAND_OPTION_REMOVE = "remove";

    private static final String[] COMMAND_OPTION_ARRAY = {
            COMMAND_OPTION_SIZE,
            COMMAND_OPTION_INSPECT,
            COMMAND_OPTION_DOWNLOAD,
            COMMAND_OPTION_UPLOAD,
            COMMAND_OPTION_UPDATE,
            COMMAND_OPTION_CHANGE_ORDER,
            COMMAND_OPTION_REMOVE
    };

    private static final String COMMAND_SUB_OPTION_JSON = "json";
    private static final String COMMAND_SUB_OPTION_JSON_FILE = "jf";
    private static final String COMMAND_SUB_OPTION_JSON_FILE_LONG_OPT = "json-file";

    // endregion

    private final FileListNodeQosService fileListNodeQosService;

    public FileListNodeCommand(FileListNodeQosService fileListNodeQosService) {
        super(IDENTITY);
        this.fileListNodeQosService = fileListNodeQosService;
    }

    @Override
    protected DescriptionProvider provideDescriptionProvider() {
        return _ -> ImplMessages.message(ImplMessageKey.TELQOS_FILE_LIST_DESCRIPTION);
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
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_DOWNLOAD) + " [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON) + " json-string] [" +
                        CliCommandUtil.concatOptionPrefix(COMMAND_SUB_OPTION_JSON_FILE) + " json-file]",
                identity + " " + CliCommandUtil.concatOptionPrefix(COMMAND_OPTION_UPLOAD) + " [" +
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

    @SuppressWarnings("DuplicatedCode")
    @Override
    protected List<Option> provideOptions() {
        List<Option> list = new ArrayList<>();
        list.add(
                Option.builder(COMMAND_OPTION_SIZE).optionalArg(true).hasArg(false)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_FILE_LIST_OPTION_SIZE)).get()
        );
        list.add(
                Option.builder(COMMAND_OPTION_INSPECT).optionalArg(true).hasArg(false)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_FILE_LIST_OPTION_INSPECT)).get()
        );
        list.add(
                Option.builder(COMMAND_OPTION_DOWNLOAD).optionalArg(true).hasArg(false)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_FILE_LIST_OPTION_DOWNLOAD)).get()
        );
        list.add(
                Option.builder(COMMAND_OPTION_UPLOAD).optionalArg(true).hasArg(false)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_FILE_LIST_OPTION_UPLOAD)).get()
        );
        list.add(
                Option.builder(COMMAND_OPTION_UPDATE).optionalArg(true).hasArg(false)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_FILE_LIST_OPTION_UPDATE)).get()
        );
        list.add(
                Option.builder(COMMAND_OPTION_CHANGE_ORDER).longOpt(COMMAND_OPTION_CHANGE_ORDER_LONG_OPT)
                        .optionalArg(true).hasArg(false)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_FILE_LIST_OPTION_CHANGE_ORDER)).get()
        );
        list.add(
                Option.builder(COMMAND_OPTION_REMOVE).optionalArg(true).hasArg(false)
                        .desc(ImplMessages.message(ImplMessageKey.TELQOS_FILE_LIST_OPTION_REMOVE)).get()
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
            case COMMAND_OPTION_SIZE:
                handleSize(context, cmd);
                break;
            case COMMAND_OPTION_INSPECT:
                handleInspect(context, cmd);
                break;
            case COMMAND_OPTION_DOWNLOAD:
                handleDownload(context, cmd);
                break;
            case COMMAND_OPTION_UPLOAD:
                handleUpload(context, cmd);
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
                throw new IllegalStateException(
                        ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_INTERNAL_UNREACHABLE)
                );
        }
    }

    private void handleSize(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        FileListNodeSizeInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputFileListNodeSizeInfo.toStackBean(JSON.parseObject(json, WebInputFileListNodeSizeInfo.class));
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputFileListNodeSizeInfo.toStackBean(
                        JSON.parseObject(in, WebInputFileListNodeSizeInfo.class)
                );
            }
        } else {
            throw new UnsupportedOperationException(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_UNSUPPORTED));
        }
        FileListNodeSizeResult result = fileListNodeQosService.size(info);
        if (result == null) {
            context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_FILE_LIST_RESULT_SIZE_NULL));
            return;
        }
        context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_FILE_LIST_RESULT_SIZE_HEADER));
        context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_FIELD_SIZE, result.getSize()));
    }

    private void handleInspect(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        FileListNodeInspectInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputFileListNodeInspectInfo.toStackBean(
                    JSON.parseObject(json, WebInputFileListNodeInspectInfo.class)
            );
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputFileListNodeInspectInfo.toStackBean(
                        JSON.parseObject(in, WebInputFileListNodeInspectInfo.class)
                );
            }
        } else {
            throw new UnsupportedOperationException(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_UNSUPPORTED));
        }
        FileListNodeInspectResult result = fileListNodeQosService.inspect(info);
        if (result == null) {
            context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_INSPECT_RESULT_NULL));
            return;
        }
        context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_INSPECT_RESULT_HEADER));
        List<FileListNodeInspectResult.Item> items = result.getItems();
        context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_FIELD_ITEMS_TOTAL_SIZE, items.size()));
        for (int i = 0; i < items.size(); i++) {
            FileListNodeInspectResult.Item item = items.get(i);
            context.sendMessage("    " + i + "/" + items.size() + ": ");
            context.sendMessage(
                    ImplMessages.message(ImplMessageKey.TELQOS_COMMON_FIELD_ITEM_NULL_FLAG, item.isNullFlag())
            );
            context.sendMessage(
                    ImplMessages.message(ImplMessageKey.TELQOS_COMMON_FIELD_ITEM_ORIGIN_NAME, item.getOriginName())
            );
            context.sendMessage(
                    ImplMessages.message(ImplMessageKey.TELQOS_COMMON_FIELD_ITEM_LENGTH, item.getLength())
            );
        }
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
        FileListNodeFileStream fileListNodeFileStream = fileListNodeQosService.downloadFileStream(
                new FileListNodeFileStreamDownloadInfo(info.getCategory(), info.getArgs(), info.getIndex())
        );
        if (fileListNodeFileStream == null) {
            context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_FILE_NOT_FOUND));
            return;
        }
        InputStream in = null;
        OutputStream out = null;
        try {
            in = fileListNodeFileStream.getContent();
            out = Files.newOutputStream(
                    new File(info.getFilePath()).toPath(),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING
            );
            IOUtil.trans(in, out, 4096);
        } finally {
            if (Objects.nonNull(in)) in.close();
            if (Objects.nonNull(out)) out.close();
        }
        context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_FILE_DOWNLOAD_SUCCEEDED));
    }

    @SuppressWarnings("DuplicatedCode")
    private void handleUpload(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        UploadInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
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
            fileListNodeQosService.uploadFileStream(new FileListNodeFileStreamUploadInfo(
                    info.getCategory(), info.getArgs(), info.getIndex(), originName, length, in
            ));
        } finally {
            if (Objects.nonNull(in)) in.close();
        }
        context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_FILE_UPLOAD_SUCCEEDED));
    }

    @SuppressWarnings("DuplicatedCode")
    private void handleUpdate(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        UpdateInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = JSON.parseObject(json, UpdateInfo.class);
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = JSON.parseObject(in, UpdateInfo.class);
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
            fileListNodeQosService.updateFileStream(new FileListNodeFileStreamUpdateInfo(
                    info.getCategory(), info.getArgs(), info.getIndex(), originName, length, in
            ));
        } finally {
            if (Objects.nonNull(in)) in.close();
        }
        context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_FILE_LIST_RESULT_UPDATE_SUCCEEDED));
    }

    private void handleChangeOrder(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        FileListNodeChangeOrderInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputFileListNodeChangeOrderInfo.toStackBean(
                    JSON.parseObject(json, WebInputFileListNodeChangeOrderInfo.class)
            );
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputFileListNodeChangeOrderInfo.toStackBean(
                        JSON.parseObject(in, WebInputFileListNodeChangeOrderInfo.class)
                );
            }
        } else {
            throw new UnsupportedOperationException(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_UNSUPPORTED));
        }
        fileListNodeQosService.changeOrder(info);
        context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_FILE_LIST_RESULT_ORDER_CHANGE_SUCCEEDED));
    }

    private void handleRemove(CommandExecutor.Context context, CommandLine cmd) throws Exception {
        FileListNodeRemoveInfo info;
        if (cmd.hasOption(COMMAND_SUB_OPTION_JSON)) {
            String json = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON);
            info = WebInputFileListNodeRemoveInfo.toStackBean(
                    JSON.parseObject(json, WebInputFileListNodeRemoveInfo.class)
            );
        } else if (cmd.hasOption(COMMAND_SUB_OPTION_JSON_FILE)) {
            File jsonFile = cmd.getParsedOptionValue(COMMAND_SUB_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputFileListNodeRemoveInfo.toStackBean(
                        JSON.parseObject(in, WebInputFileListNodeRemoveInfo.class)
                );
            }
        } else {
            throw new UnsupportedOperationException(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_UNSUPPORTED));
        }
        fileListNodeQosService.remove(info);
        context.sendMessage(ImplMessages.message(ImplMessageKey.TELQOS_COMMON_RESULT_REMOVE_SUCCEEDED));
    }

    public static class DownloadInfo implements Dto {

        @Serial
        private static final long serialVersionUID = -1649267985994816331L;

        @JSONField(name = "category")
        @NotNull
        @NotEmpty
        private String category;

        @JSONField(name = "args")
        @NotNull
        private String[] args;

        @JSONField(name = "index")
        @PositiveOrZero
        private int index;

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

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        @Override
        public String toString() {
            return "DownloadInfo{" + "category='" + category + '\'' + ", args=" + Arrays.toString(args) +
                    ", index=" + index + ", filePath='" + filePath + '\'' + '}';
        }
    }

    public static class UploadInfo implements Dto {

        @Serial
        private static final long serialVersionUID = 2524863655399365114L;

        @JSONField(name = "category")
        @NotNull
        @NotEmpty
        private String category;

        @JSONField(name = "args")
        @NotNull
        private String[] args;

        @JSONField(name = "index")
        @PositiveOrZero
        private Integer index;

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

        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        @Override
        public String toString() {
            return "UploadInfo{" + "category='" + category + '\'' + ", args=" + Arrays.toString(args) +
                    ", index=" + index + ", filePath='" + filePath + '\'' + '}';
        }
    }

    public static class UpdateInfo implements Dto {

        @Serial
        private static final long serialVersionUID = -2030694872730873469L;

        @JSONField(name = "category")
        @NotNull
        @NotEmpty
        private String category;

        @JSONField(name = "args")
        @NotNull
        private String[] args;

        @JSONField(name = "index")
        @PositiveOrZero
        private int index;

        @JSONField(name = "file_path")
        @NotNull
        @NotEmpty
        private String filePath;

        public UpdateInfo() {
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

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        @Override
        public String toString() {
            return "UpdateInfo{" + "category='" + category + '\'' + ", args=" + Arrays.toString(args) +
                    ", index=" + index + ", filePath='" + filePath + '\'' + '}';
        }
    }
}
