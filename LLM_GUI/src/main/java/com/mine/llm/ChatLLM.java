package com.mine.llm;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

public class ChatLLM extends Application {
    private VBox chatDisplay; // 用于显示聊天消息的容器
    private TextArea messageInput; // 用户输入消息的文本区域
    private ScrollPane scrollPane;

    private final List<ObjectNode> messages = new ArrayList<>();
    private static final String API_URL = "https://api.deepseek.com/chat/completions";
    private static final String API_KEY = "sk-d5888fec891d4a52928514a5b547f423";

    public ChatLLM() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode userMessageNode = objectMapper.createObjectNode();
        userMessageNode.put("role", "system");
        userMessageNode.put("content", "");
        messages.add(userMessageNode);
    }

    @Override
    public void start(Stage primaryStage) {
        // 创建聊天消息显示区域
        chatDisplay = new VBox();
        chatDisplay.setSpacing(10); // 设置消息之间的间距

        // 聊天显示区域的滚动容器
        scrollPane = new ScrollPane(chatDisplay);
        scrollPane.setFitToWidth(true); // 自动适应宽度
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // 始终显示垂直滚动条

        // 创建用户输入区域
        messageInput = new TextArea();
        messageInput.setWrapText(true); // 启用自动换行

        // 为用户输入区域添加按键事件监听器
        messageInput.addEventHandler(KeyEvent.KEY_PRESSED, this::handleKeyPress);

        // 布局设置
        BorderPane root = new BorderPane();
        root.setCenter(createChatDisplayArea(scrollPane)); // 将聊天显示区域放置在布局的中心
        root.setBottom(createInputArea()); // 将输入区域放置在布局的底部

        // 创建场景并显示窗口
        Scene scene = new Scene(root, 500, 600); // 设置窗口大小
        primaryStage.setScene(scene);
        primaryStage.setTitle("ChatLLM"); // 设置窗口标题

        // 设置关闭事件处理程序
        primaryStage.setOnCloseRequest(event -> {
            Platform.exit(); // 关闭 JavaFX 应用程序线程
            System.exit(0);  // 确保所有非守护线程停止运行
        });

        primaryStage.show(); // 显示窗口
    }

    private VBox createChatDisplayArea(ScrollPane scrollPane) {
        VBox chatBox = new VBox(); // 使用VBox布局容器
        VBox.setVgrow(scrollPane, Priority.ALWAYS); // 设置聊天显示区域动态调整大小
        chatBox.getChildren().add(scrollPane); // 将滚动聊天显示区域添加到布局中
        chatBox.setStyle("-fx-padding: 10; -fx-background-color: #f9f9f9;"); // 设置内边距和背景颜色
        return chatBox;
    }

    private VBox createInputArea() {
        VBox inputBox = new VBox(); // 使用VBox布局容器
        VBox.setVgrow(messageInput, Priority.ALWAYS); // 设置输入区域动态调整大小
        inputBox.getChildren().add(messageInput); // 将输入区域添加到布局中
        inputBox.setStyle("-fx-padding: 10;"); // 设置内边距
        return inputBox;
    }

    private void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) { // 检测是否按下Enter键
            if (event.isShiftDown()) { // 如果同时按下Shift键
                // 在输入框中插入换行符
                messageInput.insertText(messageInput.getCaretPosition(), "\n");
                event.consume(); // 阻止事件传播
            } else {
                // 发送消息
                sendMessage();
                event.consume(); // 阻止事件传播
            }
        }
    }

    // 创建右键复制菜单
    private void addCopyContextMenu(Label label) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem copyItem = new MenuItem("复制");
        copyItem.setOnAction(e -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(label.getText()); // 获取 Label 的文本
            clipboard.setContent(content);     // 将内容放入剪贴板
        });
        contextMenu.getItems().add(copyItem);
        label.setOnContextMenuRequested(e -> contextMenu.show(label, e.getScreenX(), e.getScreenY()));
    }

    private void sendMessage() {
        String message = messageInput.getText().trim(); // 获取输入框中的消息并去除首尾空格
        if (!message.isEmpty()) { // 如果消息不为空
            // 添加用户消息到消息历史记录
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode userMessageNode = objectMapper.createObjectNode();
            userMessageNode.put("role", "user");
            userMessageNode.put("content", message);
            messages.add(userMessageNode);

            // 用户消息靠右显示
            Label userMessage = new Label(message);
            userMessage.setWrapText(true); // 启用自动换行
            userMessage.setStyle("-fx-alignment: center-right; -fx-background-color: #90EE90; -fx-padding: 10; -fx-background-radius: 10;");
            userMessage.setMaxWidth(Double.MAX_VALUE);
            userMessage.setTextAlignment(TextAlignment.RIGHT);

            addCopyContextMenu(userMessage);

            HBox userMessageBox = new HBox(userMessage);
            userMessageBox.setStyle("-fx-alignment: center-right;");

            chatDisplay.getChildren().add(userMessageBox); // 将用户消息添加到聊天显示区域
            messageInput.clear(); // 清空输入框

            // 滚动到聊天显示区域的底部
            PauseTransition pause = new PauseTransition(Duration.millis(100));
            pause.setOnFinished(event -> scrollPane.setVvalue(scrollPane.getVmax()));
            pause.play();

            // 获取AI回复
            new Thread(() -> {
                try {
                    getAIResponse();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private void getAIResponse() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        OkHttpClient client = new OkHttpClient();

        ObjectNode requestBody = objectMapper.createObjectNode();
        requestBody.put("model", "deepseek-chat");
        requestBody.set("messages", objectMapper.valueToTree(messages));
        requestBody.put("stream", true);

        Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Content-Type", "application/json")
                .post(RequestBody.create(requestBody.toString(), MediaType.get("application/json")))
                .build();

        Label aiMessage = new Label();
        aiMessage.setWrapText(true); // 启用自动换行
        aiMessage.setStyle("-fx-alignment: center-left; -fx-background-color: #87CEEB; -fx-padding: 10; -fx-background-radius: 10;");
        aiMessage.setMaxWidth(Double.MAX_VALUE);
        aiMessage.setTextAlignment(TextAlignment.LEFT);

        addCopyContextMenu(aiMessage);

        Platform.runLater(() -> {
            HBox aiMessageBox = new HBox(aiMessage);
            aiMessageBox.setStyle("-fx-alignment: center-left;");
            chatDisplay.getChildren().add(aiMessageBox);
            scrollPane.setVvalue(scrollPane.getVmax());
        });

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }

                final StringBuilder completeMessage = new StringBuilder();

                try (ResponseBody responseBody = response.body()) {
                    if (responseBody != null) {
                        InputStream inputStream = responseBody.byteStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            if (!line.isBlank()) {
                                if ("data: [DONE]".equals(line)) {
                                    ObjectNode aiMessageNode = objectMapper.createObjectNode();
                                    aiMessageNode.put("role", "assistant");
                                    aiMessageNode.put("content", completeMessage.toString());
                                    messages.add(aiMessageNode);
                                } else {
                                    String jsonLine = line.substring(6);
                                    ObjectNode jsonNode = (ObjectNode) objectMapper.readTree(jsonLine);
                                    String delta = jsonNode.path("choices").get(0).path("delta").path("content").asText("");
                                    completeMessage.append(delta);

                                    // 增量显示内容
                                    Platform.runLater(() -> aiMessage.setText(completeMessage.toString()));
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
