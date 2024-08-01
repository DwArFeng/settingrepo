package com.dwarfeng.settingrepo.impl.service.telqos;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.dutil.basic.io.IOUtil;
import com.dwarfeng.settingrepo.sdk.bean.dto.WebInputImageNodeInspectInfo;
import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.service.ImageNodeQosService;
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
public class ImageNodeCommand extends CliCommand {

    private static final String COMMAND_OPTION_INSPECT = "inspect";
    private static final String COMMAND_OPTION_DOWNLOAD = "download";
    private static final String COMMAND_OPTION_UPLOAD = "upload";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_INSPECT,
            COMMAND_OPTION_DOWNLOAD,
            COMMAND_OPTION_UPLOAD
    };

    private static final String COMMAND_OPTION_JSON = "json";
    private static final String COMMAND_OPTION_JSON_FILE = "jf";
    private static final String COMMAND_OPTION_JSON_FILE_LONG_OPT = "json-file";

    @SuppressWarnings("SpellCheckingInspection")
    private static final String IDENTITY = "imgnode";
    private static final String DESCRIPTION = "图片节点操作服务";

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

    private static final String[] CMD_LINE_ARRAY = new String[]{
            CMD_LINE_SYNTAX_INSPECT,
            CMD_LINE_SYNTAX_DOWNLOAD,
            CMD_LINE_SYNTAX_UPLOAD
    };

    private static final String CMD_LINE_SYNTAX = CommandUtil.syntax(CMD_LINE_ARRAY);

    private final ImageNodeQosService imageNodeQosService;

    public ImageNodeCommand(ImageNodeQosService imageNodeQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.imageNodeQosService = imageNodeQosService;
    }

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder(COMMAND_OPTION_INSPECT).desc("查看图片节点图片").build());
        list.add(Option.builder(COMMAND_OPTION_DOWNLOAD).desc("下载图片节点图片").build());
        list.add(Option.builder(COMMAND_OPTION_UPLOAD).desc("上传图片节点图片").build());
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
                case COMMAND_OPTION_INSPECT:
                    handleInspect(context, cmd);
                    break;
                case COMMAND_OPTION_DOWNLOAD:
                    handleDownload(context, cmd);
                    break;
                case COMMAND_OPTION_UPLOAD:
                    handleUpload(context, cmd);
                    break;
            }
        } catch (Exception e) {
            throw new TelqosException(e);
        }
    }

    private void handleInspect(Context context, CommandLine cmd) throws Exception {
        ImageNodeInspectInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 ImageNodeInspectInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputImageNodeInspectInfo.toStackBean(
                    JSON.parseObject(json, WebInputImageNodeInspectInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 ImageListNodeInspectInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputImageNodeInspectInfo.toStackBean(
                        JSON.parseObject(in, WebInputImageNodeInspectInfo.class)
                );
            }
        } else {
            throw new IllegalArgumentException("未指定必要的参数");
        }

        // 查看图片节点。
        ImageNodeInspectResult result = imageNodeQosService.inspect(info);

        // 输出结果。
        if (result == null) {
            context.sendMessage("查看结果: null");
        } else {
            context.sendMessage("查看结果: ");
            context.sendMessage("  originName: " + result.getOriginName());
            context.sendMessage("  length: " + result.getLength());
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
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 DeviceFileStreamDownloadInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = JSON.parseObject(in, DownloadInfo.class);
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 获取 ImageNodeFileStream。
        ImageNodeFileStream imageNodeFileStream = imageNodeQosService.downloadFileStream(
                new ImageNodeFileStreamDownloadInfo(info.getCategory(), info.getArgs())
        );

        // 如果 ImageNodeFileStream 为 null，则发送文件不存在信息。
        if (Objects.isNull(imageNodeFileStream)) {
            context.sendMessage("图片文件不存在");
            return;
        }

        // 下载图片。
        InputStream in = null;
        OutputStream out = null;
        try {
            in = imageNodeFileStream.getContent();
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

    private void handleUpload(Context context, CommandLine commandLine) throws Exception {
        UploadInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 UploadInfo。
        if (commandLine.hasOption(COMMAND_OPTION_JSON)) {
            String json = commandLine.getOptionValue(COMMAND_OPTION_JSON);
            info = JSON.parseObject(json, UploadInfo.class);
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 UploadInfo。
        else if (commandLine.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) commandLine.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = JSON.parseObject(in, UploadInfo.class);
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 上传图片。
        InputStream in = null;
        try {
            File file = new File(info.getFilePath());
            String originName = file.getName();
            long length = file.length();
            in = Files.newInputStream(file.toPath());
            imageNodeQosService.uploadFileStream(new ImageNodeFileStreamUploadInfo(
                    info.getCategory(), info.getArgs(), originName, length, in
            ));
        } finally {
            if (Objects.nonNull(in)) {
                in.close();
            }
        }

        // 输出结果。
        context.sendMessage("文件上传成功");
    }

    public static class DownloadInfo implements Dto {

        private static final long serialVersionUID = -3957973451481400034L;

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

        private static final long serialVersionUID = -4149967325700516987L;

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
