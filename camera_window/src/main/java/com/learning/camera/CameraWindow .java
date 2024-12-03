package com.learning.camera;

import org.bytedeco.javacv.*;
import org.bytedeco.javacv.Frame;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

class CameraWindow extends JFrame {
    private static final long serialVersionUID = 1L;

    private OpenCVFrameGrabber grabber;
    private JPanel panel;
    private BufferedImage bufferedImage;

    public CameraWindow() {
        // 设置窗口属性
        setTitle("摄像头画面");
        setSize(650, 510);  // 设置窗口大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // 居中显示窗口
        setResizable(false);

        // 创建JPanel用于展示图像
        panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bufferedImage != null) {
                    // 通过Graphics2D来翻转图像
                    Graphics2D g2d = (Graphics2D) g;
                    AffineTransform transform = AffineTransform.getScaleInstance(-1, 1); // 水平翻转
                    transform.translate(-bufferedImage.getWidth(), 0);  // 调整画布位置
                    g2d.drawImage(bufferedImage, transform, null);  // 绘制翻转后的图像
                }
            }
        };
        add(panel, BorderLayout.CENTER);
    }

    public void startCamera() {
        try {
            // 初始化摄像头
            grabber = new OpenCVFrameGrabber(0); // 使用默认摄像头
            grabber.start();
            
            // 开启线程不断读取图像帧并显示
            new Thread(() -> {
                while (true) {
                    try {
                        // 获取摄像头的当前帧
                        Frame frame = grabber.grab();
                        if (frame != null) {
                            // 将 Frame 转换为 BufferedImage
                            bufferedImage = convertFrameToImage(frame);

                            // 刷新窗口
                            repaint();
                        }
                        // 控制帧率，避免占用过多的 CPU
                        Thread.sleep(30);
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 使用 Java2DFrameConverter 将 Frame 转换为 BufferedImage
    @SuppressWarnings("resource")
    private BufferedImage convertFrameToImage(Frame frame) {
        Java2DFrameConverter converter = new Java2DFrameConverter();
        return converter.convert(frame);  // 将 Frame 转换为 BufferedImage
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CameraWindow window = new CameraWindow();
            window.setVisible(true);
            window.startCamera();
        });
    }
}
