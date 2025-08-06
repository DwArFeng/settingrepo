package com.dwarfeng.settingrepo.impl.service.telqos;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.dutil.basic.io.IOUtil;
import com.dwarfeng.settingrepo.sdk.bean.dto.WebInputImageListNodeInspectInfo;
import com.dwarfeng.settingrepo.sdk.bean.dto.WebInputImageListNodeSizeInfo;
import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.service.ImageListNodeQosService;
import com.dwarfeng.springtelqos.node.config.TelqosCommand;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.stack.command.Context;
import com.dwarfeng.springtelqos.stack.exception.TelqosException;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.lang3.tuple.Pair;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@TelqosCommand
public class ImageListNodeCommand extends CliCommand {

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

    private static final String COMMAND_OPTION_JSON = "json";
    private static final String COMMAND_OPTION_JSON_FILE = "jf";
    private static final String COMMAND_OPTION_JSON_FILE_LONG_OPT = "json-file";

    @SuppressWarnings("SpellCheckingInspection")
    private static final String IDENTITY = "imglnode";
    private static final String DESCRIPTION = "图片列表节点操作服务";

    private static final String CMD_LINE_SYNTAX_SIZE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_SIZE) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_INSPECT = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_INSPECT) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_DOWNLOAD = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_DOWNLOAD) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_UPLOAD = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_UPLOAD) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_UPDATE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_UPDATE) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_CHANGE_ORDER = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_CHANGE_ORDER) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_REMOVE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_REMOVE) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";

    private static final String[] CMD_LINE_ARRAY = {
            CMD_LINE_SYNTAX_SIZE,
            CMD_LINE_SYNTAX_INSPECT,
            CMD_LINE_SYNTAX_DOWNLOAD,
            CMD_LINE_SYNTAX_UPLOAD,
            CMD_LINE_SYNTAX_UPDATE,
            CMD_LINE_SYNTAX_CHANGE_ORDER,
            CMD_LINE_SYNTAX_REMOVE
    };

    private static final String CMD_LINE_SYNTAX = CommandUtil.syntax(CMD_LINE_ARRAY);

    private final ImageListNodeQosService imageListNodeQosService;

    public ImageListNodeCommand(ImageListNodeQosService imageListNodeQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.imageListNodeQosService = imageListNodeQosService;
    }

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder(COMMAND_OPTION_SIZE).desc("获取图片列表节点的大小").build());
        list.add(Option.builder(COMMAND_OPTION_INSPECT).desc("查看图片列表节点").build());
        list.add(Option.builder(COMMAND_OPTION_DOWNLOAD).desc("下载图片列表节点文件").build());
        list.add(Option.builder(COMMAND_OPTION_UPLOAD).desc("上传图片列表节点文件").build());
        list.add(Option.builder(COMMAND_OPTION_UPDATE).desc("更新图片列表节点文件").build());
        list.add(
                Option.builder(COMMAND_OPTION_CHANGE_ORDER).longOpt(COMMAND_OPTION_CHANGE_ORDER_LONG_OPT)
                        .desc("更改图片列表节点的顺序").build()
        );
        list.add(Option.builder(COMMAND_OPTION_REMOVE).desc("移除图片列表节点").build());
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
            }
        } catch (Exception e) {
            throw new TelqosException(e);
        }
    }

    private void handleSize(Context context, CommandLine cmd) throws Exception {
        ImageListNodeSizeInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 ImageListNodeSizeInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputImageListNodeSizeInfo.toStackBean(
                    JSON.parseObject(json, WebInputImageListNodeSizeInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 ImageListNodeSizeInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputImageListNodeSizeInfo.toStackBean(
                        JSON.parseObject(in, WebInputImageListNodeSizeInfo.class)
                );
            }
        } else {
            throw new IllegalArgumentException("未指定必要的参数");
        }

        // 获取图片列表节点的大小。
        ImageListNodeSizeResult result = imageListNodeQosService.size(info);

        // 输出结果。
        if (result == null) {
            context.sendMessage("图片列表节点的大小: null");
        } else {
            context.sendMessage("图片列表节点的大小: ");
            context.sendMessage("  size: " + result.getSize());
        }
    }

    private void handleInspect(Context context, CommandLine cmd) throws Exception {
        ImageListNodeInspectInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 ImageListNodeInspectInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputImageListNodeInspectInfo.toStackBean(
                    JSON.parseObject(json, WebInputImageListNodeInspectInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 ImageListNodeInspectInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputImageListNodeInspectInfo.toStackBean(
                        JSON.parseObject(in, WebInputImageListNodeInspectInfo.class)
                );
            }
        } else {
            throw new IllegalArgumentException("未指定必要的参数");
        }

        // 查看图片列表节点。
        ImageListNodeInspectResult result = imageListNodeQosService.inspect(info);

        // 输出结果。
        if (result == null) {
            context.sendMessage("查看结果: null");
        } else {
            context.sendMessage("查看结果: ");
            List<ImageListNodeInspectResult.Item> items = result.getItems();
            context.sendMessage("  items: total size: " + items.size());
            for (int i = 0; i < items.size(); i++) {
                ImageListNodeInspectResult.Item item = items.get(i);
                context.sendMessage("    " + i + "/" + items.size() + ": ");
                context.sendMessage("      nullFlag: " + item.isNullFlag());
                context.sendMessage("      originName: " + item.getOriginName());
                context.sendMessage("      length: " + item.getLength());
            }
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void handleDownload(Context context, CommandLine cmd) throws Exception {
        DownloadInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 DownloadInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = JSON.parseObject(json, DownloadInfo.class);
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 DownloadInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = JSON.parseObject(in, DownloadInfo.class);
            }
        } else {
            throw new IllegalArgumentException("未指定必要的参数");
        }

        // 下载图片列表节点文件。
        ImageListNodeFileStream imageListNodeFileStream = imageListNodeQosService.downloadFileStream(
                new ImageListNodeFileStreamDownloadInfo(info.getCategory(), info.getArgs(), info.getIndex())
        );

        // 如果 ImageListNodeFileStream 为 null,则发送文件不存在信息。
        if (imageListNodeFileStream == null) {
            context.sendMessage("图片文件不存在");
            return;
        }

        // 下载图片。
        InputStream in = null;
        OutputStream out = null;
        try {
            in = imageListNodeFileStream.getContent();
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

        // 输出结果。
        context.sendMessage("文件下载成功");
    }

    private void handleUpload(Context context, CommandLine cmd) throws Exception {
        UploadInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 UploadInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = JSON.parseObject(json, UploadInfo.class);
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 UploadInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = JSON.parseObject(in, UploadInfo.class);
            }
        } else {
            throw new IllegalArgumentException("未指定必要的参数");
        }

        // 上传图片。
        InputStream in = null;
        try {
            File file = new File(info.getFilePath());
            String originName = file.getName();
            long length = file.length();
            in = Files.newInputStream(file.toPath());
            imageListNodeQosService.uploadFileStream(new ImageListNodeFileStreamUploadInfo(
                    info.getCategory(), info.getArgs(), info.getIndex(), originName, length, in
            ));
        } finally {
            if (Objects.nonNull(in)) {
                in.close();
            }
        }

        // 输出结果。
        context.sendMessage("文件上传成功");
    }

    private void handleUpdate(Context context, CommandLine cmd) throws Exception {
        UpdateInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 UpdateInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = JSON.parseObject(json, UpdateInfo.class);
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 UpdateInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = JSON.parseObject(in, UpdateInfo.class);
            }
        } else {
            throw new IllegalArgumentException("未指定必要的参数");
        }

        // 更新图片。
        InputStream in = null;
        try {
            File file = new File(info.getFilePath());
            String originName = file.getName();
            long length = file.length();
            in = Files.newInputStream(file.toPath());
            imageListNodeQosService.updateFileStream(new ImageListNodeFileStreamUpdateInfo(
                    info.getCategory(), info.getArgs(), info.getIndex(), originName, length, in
            ));
        } finally {
            if (Objects.nonNull(in)) {
                in.close();
            }
        }

        // 输出结果。
        context.sendMessage("文件更新成功");
    }

    private void handleChangeOrder(Context context, CommandLine cmd) throws Exception {
        ImageListNodeChangeOrderInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 ImageListNodeChangeOrderInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = JSON.parseObject(json, ImageListNodeChangeOrderInfo.class);
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 ImageListNodeChangeOrderInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = JSON.parseObject(in, ImageListNodeChangeOrderInfo.class);
            }
        } else {
            throw new IllegalArgumentException("未指定必要的参数");
        }

        // 更改图片列表节点的顺序。
        imageListNodeQosService.changeOrder(info);

        // 输出结果。
        context.sendMessage("更改顺序成功");
    }

    private void handleRemove(Context context, CommandLine cmd) throws Exception {
        ImageListNodeRemoveInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 ImageListNodeRemoveInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = JSON.parseObject(json, ImageListNodeRemoveInfo.class);
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 ImageListNodeRemoveInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = JSON.parseObject(in, ImageListNodeRemoveInfo.class);
            }
        } else {
            throw new IllegalArgumentException("未指定必要的参数");
        }

        // 移除图片列表节点。
        imageListNodeQosService.remove(info);

        // 输出结果。
        context.sendMessage("移除成功");
    }

    public static class DownloadInfo implements Dto {

        private static final long serialVersionUID = -5817888325650262697L;

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
            return "DownloadInfo{" +
                    "category='" + category + '\'' +
                    ", args=" + Arrays.toString(args) +
                    ", index=" + index +
                    ", filePath='" + filePath + '\'' +
                    '}';
        }
    }

    public static class UploadInfo implements Dto {

        private static final long serialVersionUID = -8596799401012420187L;

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
            return "UploadInfo{" +
                    "category='" + category + '\'' +
                    ", args=" + Arrays.toString(args) +
                    ", index=" + index +
                    ", filePath='" + filePath + '\'' +
                    '}';
        }
    }

    public static class UpdateInfo implements Dto {

        private static final long serialVersionUID = -8585477378097506454L;

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
            return "UpdateInfo{" +
                    "category='" + category + '\'' +
                    ", args=" + Arrays.toString(args) +
                    ", index=" + index +
                    ", filePath='" + filePath + '\'' +
                    '}';
        }
    }
}
