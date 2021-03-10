
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class 主界面 extends javax.swing.JFrame {

    ArrayList<File> ImageFiles = new ArrayList<File>();
    ArrayList<JPanel> SmallPanels = new ArrayList<JPanel>();        //这里定义N个小的面板，上面存放标签，下面用于存放文件名的文本框
    ArrayList<JLabel> SmallLabels = new ArrayList<JLabel>();        //这里定义N个小的标签，用来存放读取的图片
    ArrayList<JTextField> SmallTextFields = new ArrayList<JTextField>();  //这里定义N个文本框，用来显示与图片相对应的文件的名称
    JScrollPane BigScrollPane;             //这里定义一个滚动条，把大的面板放在滚动条里
    ArrayList<File> ClickedFilePath = new ArrayList<File>();      //这里定义了一个，鼠标点击的文件路径下的所有文件
    int ImagesQuantity;                                         //这里定义的是图片的总数
    int SelectImage = -1;                                    //这里定义的是选择的图片，0为第一张，-1是未选择
    JFrame IntroduceFrame = new JFrame();                        //帮助里的关于介绍软件作者而弹出的面板
    JTextArea IntroduceTextArea = new JTextArea();                //同上，介绍软件作者的文本域，被加在了面板上
    JPopupMenu PopupMenu = new JPopupMenu();                      //右键单击文件时弹出的弹出式菜单
    JMenuItem Copy = new JMenuItem(" 复制 ");                    // 菜单中的复制选项
    JMenuItem Delete = new JMenuItem(" 删除 ");              // 菜单中的删除选项
    JMenuItem Cut = new JMenuItem(" 剪切 ");                  //菜单中的剪切选项
    JMenuItem Rename = new JMenuItem(" 重命名 ");                  //菜单中的重命名选项
    JPanel ImagePanel = new JPanel();
    String FilePath;
    MouseEvent E;
    File TemporaryFile;
    ImageIcon TemporaryIcon;
    String OldName;
    JPopupMenu OutPopupMenu = new JPopupMenu();
    JMenuItem Refresh = new JMenuItem("刷新");
    JMenuItem Paste = new JMenuItem("粘贴");
    JMenuItem BatchRename = new JMenuItem("批量重命名");
    ArrayList<BufferedInputStream> SourceFile = new ArrayList<BufferedInputStream>();
    ArrayList<FileOutputStream> NewFile = new ArrayList<FileOutputStream>();
    ArrayList<TreePath> TreePaths = new ArrayList<TreePath>();
    String SourceFileName = null;
    int CopyNum = 0;
    int PasteNumber = 0;
    String CopyName;
    String CopyPath;
    int CutFlag = 0;

    /** 创建主界面*/
    public 主界面() {
        initComponents();
    }

    /*初始化介绍软件作者的面板和文本域 */
    public void InitIntroduction() {
        IntroduceFrame.setVisible(false);
        IntroduceFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);//点叉的时候只是隐藏面板
        IntroduceFrame.setSize(220, 130);
        IntroduceFrame.setLocationRelativeTo(null);      //弹出面板时在屏幕的中央
        IntroduceFrame.add(IntroduceTextArea);
        IntroduceTextArea.setEditable(false);            //文本设为不可编辑
    }

    /*初始化一些必要的数据*/
    public void Init() {

        InitIntroduction();
        BigScrollPane = new JScrollPane(ImagePanel);         //滚动面板里加上显示用于显示图像的大面板
        ImagePanel.setLayout(null);                             //图像面板的布局设为null（这点非常重要）
        jTabbedPane1.add(BigScrollPane);                    //在标签化窗口中加入已有图像面板的滚动面板
        PopupMenu.add(Copy);                                 //弹出式窗口中加入复制菜单项
        //jPopupMenu.addSeparator();                         // 往菜单中加横线
        PopupMenu.add(Cut);                                 //弹出式窗口中加入剪切菜单项
        PopupMenu.add(Delete);                             //弹出式窗口中加入删除菜单项
        PopupMenu.add(Rename);                              //弹出式窗口中加入重命名菜单项
        OutPopupMenu.add(Refresh);
        OutPopupMenu.add(Paste);
        OutPopupMenu.add(BatchRename);
        jComboBox1.addPopupMenuListener(new PopupMenuListener() {

            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            }

            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                jTree1.setSelectionPath(TreePaths.get(jComboBox1.getSelectedIndex()));
                ShowImages(E, TreePaths.get(jComboBox1.getSelectedIndex()), 1);
            }

            public void popupMenuCanceled(PopupMenuEvent e) {
                System.out.println("我是3");
            }
        });

        ImagePanel.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseReleased(MouseEvent evt) {
                OutPopupMenu(evt);
            }
        });

        BatchRename.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                BatchRename();
            }
        });

        Delete.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete();
            }
        });

        Rename.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Rename();
            }
        });

        Copy.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    Copy();
                } catch (IOException ex) {
                    Logger.getLogger(主界面.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        Cut.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    Cut();
                } catch (IOException ex) {
                    Logger.getLogger(主界面.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        Refresh.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowImages(E, new TreePath(0), 0);
            }
        });

        Paste.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    Paste();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(主界面.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });


    }

    public void Back() {
        jTree1.setSelectionPath(TreePaths.get(jComboBox1.getSelectedIndex() - 1));
        ShowImages(E, TreePaths.get(jComboBox1.getSelectedIndex() - 1), 1);
        jScrollPane1.getVerticalScrollBar().setValue((int) (jTree1.getRowHeight() * jTree1.getMaxSelectionRow()));
    }

    public void Next() {
        jTree1.setSelectionPath(TreePaths.get(jComboBox1.getSelectedIndex() + 1));
        ShowImages(E, TreePaths.get(jComboBox1.getSelectedIndex() + 1), 1);
        jScrollPane1.getVerticalScrollBar().setValue((int) (jTree1.getRowHeight() * jTree1.getMaxSelectionRow()));
    }

    public void Up() {
        if (jTree1.getSelectionPath().getParentPath() != null) {
            jTree1.setSelectionPath(jTree1.getSelectionPath().getParentPath());
            System.out.println(jTree1.getMaxSelectionRow());
            for (int i = jTree1.getRowCount(); i >= jTree1.getMaxSelectionRow(); i--) {
                jTree1.collapseRow(i);
            }
        }
    }

    public void BatchRename() {
        //ImagePanel.removeAll();
        int num = 0;
        File file = new File(FilePath);
        File[] files = file.listFiles();
        ArrayList<File> filesA = new ArrayList<File>();
        ArrayList<ImageIcon> ImageIcons = new ArrayList<ImageIcon>();
        for (int i = 0; i < files.length; i++) {
            String ext = files[i].getName().substring(
                    files[i].getName().lastIndexOf("."),
                    files[i].getName().length()).toLowerCase();
            if (ext.equals(".jpg") || ext.equals(".gif") || ext.equals(".bmp")) {
                filesA.add(files[i]);
            }
        }
        for (int i = 0; i < filesA.size(); i++) {
            if (!filesA.get(i).renameTo(filesA.get(i))) {
                //ImageIcons.add(GetImageIcon(new ImageIcon(filesA.get(i).getAbsolutePath())));
                SmallLabels.get(i).setIcon(null);
            }
        }

        String string = JOptionPane.showInputDialog(null, "请输入新的名字(不包含后缀)", "批量重命名", JOptionPane.INFORMATION_MESSAGE);

        for (int i = 0; i < filesA.size(); i++) {
            String axt = filesA.get(i).getName().substring(
                    filesA.get(i).getName().lastIndexOf("."),
                    filesA.get(i).getName().length()).toLowerCase();
            System.out.println(filesA.get(i));
            int j = i + 1;
            if (SmallLabels.get(i).getIcon() == null) {
                filesA.get(i).renameTo(new File(FilePath + File.separator + string + "(" + j + ")" + axt));
                ImageIcons.add(GetImageIcon(new ImageIcon(FilePath + File.separator + string + "(" + j + ")" + axt)));
                SmallTextFields.get(i).setText(string + "(" + j + ")" + axt);
                ClickedFilePath.add(new File(FilePath + File.separator + string + "(" + j + ")" + axt));
            } else {
                filesA.get(i).renameTo(new File(FilePath + File.separator + string + "(" + j + ")" + axt));
                SmallTextFields.get(i).setText(string + "(" + j + ")" + axt);
                ClickedFilePath.add(new File(FilePath + File.separator + string + "(" + j + ")" + axt));
            }
        }
        for (int i = 0; i < filesA.size(); i++) {
            if (SmallLabels.get(i).getIcon() == null) {
                SmallLabels.get(i).setIcon(ImageIcons.get(num));
                num++;
            }
        }



    }

    public void Paste() throws FileNotFoundException {
        int flag = 0;
        int PasteNum = 0;
        int FlagName = 0;
        if (CutFlag == 1) {
            for (int i = 0; i < ImageFiles.size(); i++) {
                if (SmallTextFields.get(i).getText().equals(SourceFileName)) {
                    FlagName = 1;
                }


            }
            if (FlagName == 1) {
                JOptionPane.showMessageDialog(null,
                        "本目录下存在相同名字的文件，不能进行粘贴操作!!!",
                        "ERROR", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (CopyPath == FilePath) {
                    JOptionPane.showMessageDialog(null,
                            "无法在同一目录进行剪切和粘贴操作!!!",
                            "ERROR", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    NewFile.add(new FileOutputStream(new File(FilePath + File.separator + SourceFileName)));
                    byte[] content = new byte[1000];
                    int size = 0;

                    try {
                        while ((size = SourceFile.get(CopyNum - 1).read(content)) != -1) {
                            NewFile.get(PasteNumber).write(content, 0, size);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        NewFile.get(PasteNumber).close();
                        SourceFile.get(CopyNum - 1).close();
                    } catch (IOException ex) {
                    }
                    PasteNumber++;
                    ImageIcon ic1 = new ImageIcon(FilePath + File.separator + SourceFileName);
                    double h1 = ic1.getIconHeight();
                    double w1 = ic1.getIconWidth();
                    if (h1 < 77 && w1 < 100) {
                        Image im = ic1.getImage().getScaledInstance((int) w1, (int) h1, Image.SCALE_DEFAULT);//改变大小
                        ImageIcon ic2 = new ImageIcon(im);//从新得到一个固定图片
                        SmallLabels.add(new JLabel());
                        SmallTextFields.add(new JTextField());
                        SmallLabels.get(SmallLabels.size() - 1).setIcon(ic2);
                        SmallTextFields.get(SmallLabels.size() - 1).setText(SourceFileName);

                    } else {
                        if (h1 * 180 > w1 * 142) {
                            Image im = ic1.getImage().getScaledInstance((int) (w1 / (h1 / 81)), 81, Image.SCALE_DEFAULT);//改变大小
                            ImageIcon ic2 = new ImageIcon(im);
                            SmallLabels.add(new JLabel());
                            SmallTextFields.add(new JTextField());
                            SmallLabels.get(SmallLabels.size() - 1).setIcon(ic2);
                            SmallTextFields.get(SmallLabels.size() - 1).setText(SourceFileName);
                        } else {
                            Image im = ic1.getImage().getScaledInstance(105, (int) (h1 / (w1 / 105)), Image.SCALE_DEFAULT);//改变大小
                            ImageIcon ic2 = new ImageIcon(im);
                            SmallLabels.add(new JLabel());
                            SmallTextFields.add(new JTextField());
                            SmallLabels.get(SmallLabels.size() - 1).setIcon(ic2);
                            SmallTextFields.get(SmallLabels.size() - 1).setText(SourceFileName);
                        }
                        SmallPanels.add(new JPanel());
                        int j = SmallLabels.size() - 1;
                        ImagePanel.add(SmallPanels.get(j));
                        ImageFiles.add(new File(FilePath + File.separator + SourceFileName));
                        
                        if (ImageFiles.size() > 20) {
                            SmallPanels.get(j).setBounds(j % 5 * 131, 1 + (j / 5 * 125), 120, 110);
                        } else {
                            SmallPanels.get(j).setBounds(j % 5 * 135, 1 + (j / 5 * 125), 120, 110);
                        }
                        SmallPanels.get(j).setLayout(new java.awt.BorderLayout(0, 0));
                        SmallPanels.get(j).add(SmallLabels.get(j), java.awt.BorderLayout.CENTER);
                        SmallPanels.get(j).add(SmallTextFields.get(j), java.awt.BorderLayout.PAGE_END);
                        SmallTextFields.get(j).setBorder(null);
                        SmallTextFields.get(j).setHorizontalAlignment(SwingConstants.CENTER);
                        SmallTextFields.get(j).setEditable(false);
                        if (j == 0) {
                            SmallLabels.get(0).setDisplayedMnemonic(501);
                        } else {
                            SmallLabels.get(j).setDisplayedMnemonic(j);
                        }
                        SmallLabels.get(j).setHorizontalAlignment(SwingConstants.CENTER);
                        SmallLabels.get(j).setOpaque(true);
                        SmallLabels.get(j).setBackground(new java.awt.Color(244, 244, 244));
                        if (new File(CopyPath + File.separator + SourceFileName).delete()) {
                            JOptionPane.showMessageDialog(null,
                                    "剪切操作已成功完成!!!",
                                    "ERROR", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "源文件正被另一个程序使用中，不能被删除!!!",
                                    "ERROR", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        } else {
            String SourceFileNameFront = SourceFileName.substring(0,
                    SourceFileName.indexOf(".")).toLowerCase();

            if (SourceFileName.indexOf("(") != -1) {
                SourceFileNameFront = SourceFileName.substring(0,
                        SourceFileName.indexOf("(")).toLowerCase();
            }
            System.out.println((String) (SourceFileNameFront) + "aabcdefg");

            String SourceFileNameLast = SourceFileName.substring(
                    SourceFileName.lastIndexOf("."),
                    SourceFileName.length()).toLowerCase();
            for (int size = 0; size < SmallTextFields.size(); size++) {
                if (SourceFileNameFront.equals(SmallTextFields.get(size).getText().substring(0,
                        SmallTextFields.get(size).getText().indexOf(".")).toLowerCase())) {
                    PasteNum = 2;

                }


            }
            for (int size = 0; size < SmallTextFields.size(); size++) {


                if (new String(SourceFileNameFront + "(" + PasteNum + ")").equals(SmallTextFields.get(size).getText().substring(0,
                        SmallTextFields.get(size).getText().indexOf(".")).toLowerCase())) {
                    PasteNum++;
                    size = 0;
                }
            }

            if (PasteNum != 0) {
                NewFile.add(new FileOutputStream(new File(FilePath + File.separator + SourceFileNameFront + "(" + PasteNum + ")" + SourceFileNameLast)));
            } else {
                NewFile.add(new FileOutputStream(new File(FilePath + File.separator + SourceFileNameFront + SourceFileNameLast)));
            }

            try {
                CopyTwo();
            } catch (IOException e) {
            }
            byte[] content = new byte[1000];
            int size = 0;

            try {
                while ((size = SourceFile.get(CopyNum - 1).read(content)) != -1) {
                    NewFile.get(PasteNumber).write(content, 0, size);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            ShowImages(E, new TreePath(0), 0);
            try {
                NewFile.get(PasteNumber).close();
            } catch (IOException ex) {
            }
            PasteNumber++;


        }

    }

    public void CopyTwo() throws IOException {
        if (CopyNum != 0) {
            try {
                SourceFile.get(CopyNum - 1).close();
            } catch (IOException ex) {
            }
        }

        try {
            SourceFile.add(new BufferedInputStream(new FileInputStream(CopyPath + File.separator + CopyName)));

            SourceFileName = CopyName;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        CopyNum++;

    }

    public void Copy() throws IOException {
        CutFlag = 0;
        if (CopyNum != 0) {
            try {
                SourceFile.get(CopyNum - 1).close();
            } catch (IOException ex) {
            }
        }
        CopyName = SmallTextFields.get(SelectImage).getText();
        CopyPath = FilePath;
        try {
            SourceFile.add(new BufferedInputStream(new FileInputStream(FilePath + File.separator + SmallTextFields.get(SelectImage).getText())));

            SourceFileName = SmallTextFields.get(SelectImage).getText();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        CopyNum++;

    }

    public void Cut() throws IOException {
        if (CopyNum != 0) {
            try {
                SourceFile.get(CopyNum - 1).close();
            } catch (IOException ex) {
            }
        }
        CopyPath = FilePath;
        try {
            SourceFile.add(new BufferedInputStream(new FileInputStream(FilePath + File.separator + SmallTextFields.get(SelectImage).getText())));
            SourceFileName = SmallTextFields.get(SelectImage).getText();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        CopyNum++;
        CutFlag = 1;
    }

    public void OutPopupMenu(MouseEvent evt) {
        if (evt.isPopupTrigger()) {                          //判断鼠标的点击是否为右键的点击
            OutPopupMenu.show(evt.getComponent(), evt.getX(), evt.getY());         //弹出式菜单在此时弹出，并设置好了其弹出的位置
        }
    }

    public void Delete() {
        SmallLabels.get(SelectImage).setIcon(null);
        if (JOptionPane.showConfirmDialog(null, "你确定要删除" + SmallTextFields.get(SelectImage).getText() + "吗?") == JOptionPane.YES_OPTION) {
            if (new File(FilePath + File.separator + SmallTextFields.get(SelectImage).getText()).delete()) {
                if (ImagesQuantity - 1 > 20) {
                    SmallPanels.get(SelectImage).setBounds(3000, 1, 0, 0);
                    SmallPanels.remove(SelectImage);
                    SmallTextFields.remove(SelectImage);
                    SmallLabels.remove(SelectImage);
                    SmallPanels.trimToSize();
                    SmallLabels.trimToSize();
                    SmallTextFields.trimToSize();
                    for (int i = SelectImage; i < SmallPanels.size(); i++) {
                        System.out.println("size" + SmallPanels.size());
                        SmallPanels.get(i).setBounds((i) % 5 * 135, 1 + ((i) / 5 * 125), 120, 110);
                    }
                } else {
                    SmallPanels.get(SelectImage).setBounds(3000, 1, 0, 0);
                    SmallPanels.remove(SelectImage);
                    SmallTextFields.remove(SelectImage);
                    SmallLabels.remove(SelectImage);
                    SmallPanels.trimToSize();
                    SmallLabels.trimToSize();
                    SmallTextFields.trimToSize();
                    for (int i = SelectImage; i < SmallPanels.size(); i++) {
                        System.out.println("size" + SmallPanels.size());
                        SmallPanels.get(i).setBounds((i) % 5 * 135, 1 + ((i) / 5 * 125), 120, 110);
                    }
                }
                ImagesQuantity--;
            } else {
                JOptionPane.showMessageDialog(null,
                        "文件正被另一个程序使用中，无法进行删除操作!!!",
                        "ERROR", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public void Rename() {
        Robot mRobot = null;
        try {
            mRobot = new Robot();
        } catch (java.awt.AWTException awe) {
            System.err.println("new   Robot   error");
        }
        TemporaryFile = new File(FilePath + File.separator + SmallTextFields.get(SelectImage).getText());
        Point point = new Point();
        point = SmallTextFields.get(SelectImage).getLocationOnScreen();
        mRobot.mouseMove(point.x + 50, point.y + 5);//鼠标移动
        SmallTextFields.get(SelectImage).setEditable(true);
        OldName = (String) SmallTextFields.get(SelectImage).getText();
        SmallTextFields.get(SelectImage).setBackground(Color.white);
        TemporaryIcon = (ImageIcon) SmallLabels.get(SelectImage).getIcon();
        if (!TemporaryFile.renameTo(TemporaryFile)) {
            SmallLabels.get(SelectImage).setIcon(null);
        }
        SmallTextFields.get(SelectImage).addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    RenameText();
                } catch (IOException ex) {
                    Logger.getLogger(主界面.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void RenameText() throws IOException {
        File filetwo = new File(FilePath + File.separator + SmallTextFields.get(SelectImage).getText());

        //Runtime.getRuntime().exec("cmd /c RENAME " + TemporaryFile.getPath() + " " + SmallTextFields.get(SelectImage).getText());      
        /*System.out.println(filetwo);
        System.out.println("cmd /c ren " + TemporaryFile.getPath() + " " + SmallTextFields.get(SelectImage).getText());
        if (!TemporaryFile.renameTo(filetwo)) {
        BufferedInputStream BIS = new BufferedInputStream(new FileInputStream(TemporaryFile));
        FileOutputStream FOS = new FileOutputStream(filetwo);
        byte[] content = new byte[1000];
        int size = 0;
        while ((size = BIS.read(content)) != -1) {
        FOS.write(content, 0, size);
        }
        BIS.close();
        FOS.close();
        Delete2();
        ShowImages(E, new TreePath(0), 0);}*/
        if (TemporaryFile.renameTo(filetwo)) {
            JOptionPane.showMessageDialog(null,
                    "重命名操作成功!!!",
                    "重命名", JOptionPane.INFORMATION_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(null, "文件被其他程序占用，重命名操作失败", "重命名", JOptionPane.INFORMATION_MESSAGE);

        }
        SmallTextFields.get(SelectImage).setBackground(null);
        SmallTextFields.get(SelectImage).setEditable(false);
        SmallLabels.get(SelectImage).setIcon(GetImageIcon(new ImageIcon(filetwo.getAbsolutePath())));
        SmallLabels.get(SelectImage).setBackground(new java.awt.Color(244, 244, 244));
    }

    public ImageIcon GetImageIcon(ImageIcon imageicon) {
        //File filetwo = new File(FilePath + File.separator + SmallTextFields.get(SelectImage).getText());
        //ImageIcon imageicon = new ImageIcon(filetwo.getAbsolutePath());
        double h1 = imageicon.getIconHeight();
        double w1 = imageicon.getIconWidth();
        if (h1 < 77 && w1 < 100) {
            Image image = imageicon.getImage().getScaledInstance((int) w1, (int) h1, Image.SCALE_DEFAULT);//改变大小
            ImageIcon Finalii = new ImageIcon(image);//从新得到一个固定图片
            return Finalii;

        } else {
            if (h1 * 180 > w1 * 142) {
                Image image = imageicon.getImage().getScaledInstance((int) (w1 / (h1 / 81)), 81, Image.SCALE_DEFAULT);//改变大小
                ImageIcon Finalii = new ImageIcon(image);//从新得到一个固定图片
                return Finalii;
            } else {
                Image image = imageicon.getImage().getScaledInstance(105, (int) (h1 / (w1 / 105)), Image.SCALE_DEFAULT);//改变大小
                ImageIcon Finalii = new ImageIcon(image);//从新得到一个固定图片
                return Finalii;
            }
        }
    }

    public void PopupMenu(MouseEvent evt) {
        if (evt.isPopupTrigger()) {                          //判断鼠标的点击是否为右键的点击
            JLabel SelectLabel = new JLabel();                     //定义一个临时的标签
            SelectLabel = (JLabel) evt.getSource();                 //让它等于所点击的标签
            PopupMenu.show(evt.getComponent(), evt.getX(), evt.getY());         //弹出式菜单在此时弹出，并设置好了其弹出的位置

            for (int b = 0; b < SmallLabels.size(); b++) {
                SmallLabels.get(b).setBackground(new java.awt.Color(244, 244, 244));
            }
            JLabel CurrentLabel = new JLabel();
            CurrentLabel = (JLabel) evt.getSource();
            CurrentLabel.setBackground(new java.awt.Color(194, 194, 194));
            for (int y = 0; y < SmallLabels.size(); y++) {
                if (SmallLabels.get(y).getDisplayedMnemonic() == CurrentLabel.getDisplayedMnemonic()) {
                    SelectImage = y;
                }
            }
        }
    }

    public void InitLabelListener() {
        for (int i = 0; i < SmallLabels.size(); i++) {
            SmallLabels.get(i).setBorder(null);
            SmallLabels.get(i).addMouseListener(new java.awt.event.MouseAdapter() {

                public void mouseReleased(MouseEvent evt) {
                    PopupMenu(evt);
                }

                public void mousePressed(java.awt.event.MouseEvent evt) {
                    for (int b = 0; b < SmallLabels.size(); b++) {
                        SmallLabels.get(b).setBackground(new java.awt.Color(244, 244, 244));
                    }
                    JLabel CurrentLabel = new JLabel();
                    CurrentLabel = (JLabel) evt.getSource();
                    CurrentLabel.setBackground(new java.awt.Color(194, 194, 194));
                    int clickTimes = evt.getClickCount();

                    for (int y = 0; y < SmallLabels.size(); y++) {
                        if (SmallLabels.get(y).getDisplayedMnemonic() == CurrentLabel.getDisplayedMnemonic()) {
                            SelectImage = y;
                        }
                    }
                    if (clickTimes == 2) {
                        ImageIcon ic2 = null;

                        for (int t = 0; t < ClickedFilePath.size(); t++) {

                            if (ClickedFilePath.get(t).getName().equals(SmallTextFields.get(SelectImage).getText())) {
                                System.out.println("点击的图片" + ClickedFilePath.get(t).getAbsolutePath());
                                ImageIcon TemporaryIcon = new ImageIcon(ClickedFilePath.get(t).getAbsolutePath());
                                Image TemporaryImage = TemporaryIcon.getImage().getScaledInstance(TemporaryIcon.getIconWidth(), TemporaryIcon.getIconHeight(), Image.SCALE_DEFAULT);
                                ic2 = new ImageIcon(TemporaryImage);
                            }
                        }
                        FullFrame jj = new FullFrame(ClickedFilePath, SmallTextFields, SelectImage, ImagesQuantity, SmallLabels, "123");
                        jj.setVisible(true);
                        jj.getJLabel1().setIcon(ic2);
                        jj.getJLabel1().setHorizontalAlignment(SwingConstants.CENTER);
                    }
                }
            });
        }
    }

    public void Open() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            SelectImage = -1;
            FullFrame jj = new FullFrame(ClickedFilePath, SmallTextFields, SelectImage, ImagesQuantity, SmallLabels, chooser.getSelectedFile().getAbsolutePath());
            ImageIcon ic1 = new ImageIcon(chooser.getSelectedFile().getAbsolutePath());
            Image im = ic1.getImage().getScaledInstance(ic1.getIconWidth(), ic1.getIconHeight(), Image.SCALE_DEFAULT);
            ImageIcon ic2 = new ImageIcon(im);
            jj.getJLabel1().setIcon(ic2);
            jj.setVisible(true);
            jj.getJLabel1().setHorizontalAlignment(SwingConstants.CENTER);

        }
    }

    public void RunTree(final JTree jTree1) {
        File[] roots = (new PFileSystemView()).getRoots();
        FileNode nod = null;
        nod = new FileNode(roots[0]);
        nod.explore();
        jTree1.setModel(new DefaultTreeModel(nod));
        jTree1.addTreeExpansionListener(new TreeExpansionListener() {

            public void treeExpanded(TreeExpansionEvent event) {
                TreePath path = event.getPath();
                FileNode node = (FileNode) path.getLastPathComponent();
                if (!node.isExplored()) {
                    DefaultTreeModel model = ((DefaultTreeModel) jTree1.getModel());
                    node.explore();
                    model.nodeStructureChanged(node);
                }
            }

            public void treeCollapsed(TreeExpansionEvent event) {
            }
        });

        jTree1.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {

                ShowImages(e, new TreePath(0), 0);
                E = e;

            }
        });
    }

    public void ShowImages(MouseEvent e, TreePath path, int FlagTree) {
        try {

            Locale systime = Locale.CHINA;
            SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd   HH:mm:ss", systime);
            String temptime = timeformat.format(new Date());//求得本地机的系统时间;
            System.out.println("开始的时间为:" + temptime);
            SmallPanels.clear();
            SmallLabels.clear();
            SmallTextFields.clear();
            ImagePanel.removeAll();
            ImagePanel.repaint();
            ImageFiles.clear();
            int flag = 0;
            ImagePanel.setLayout(null);
            String filepath = null;
            ImagesQuantity = 0;


            JTree tree = (JTree) e.getSource();
            int row = tree.getRowForLocation(e.getX(), e.getY());
            if (row == -1) {
                return;
            }
            if (FlagTree == 0) {
                path = tree.getPathForRow(row);
            }

            if (path == null) {
                return;
            }
            FileNode node = (FileNode) path.getLastPathComponent();
            if (node == null) {
                return;
            }
            try {
                filepath = node.getWorR1();
                FilePath = node.getWorR1();
                System.out.println("node=" + path);
            } catch (IOException ex) {
                Logger.getLogger(主界面.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(filepath);

            for (int i = 0; i < jComboBox1.getItemCount(); i++) {
                if (filepath.equals(jComboBox1.getItemAt(i))) {
                    flag = 1;
                }
            }
            if (flag == 0) {
                jComboBox1.addItem(filepath);
                TreePaths.add(path);
            }
            jComboBox1.setSelectedItem(filepath);
            File[] files = node.getWorR().listFiles();
            ClickedFilePath.clear();
            for (int FilesQuantity = 0; FilesQuantity < files.length; FilesQuantity++) {
                ClickedFilePath.add(files[FilesQuantity]);
            }

            int PictureNumber = 0;
            for (int CIN = 0; CIN < files.length; CIN++) {
                String ext = files[CIN].getName().substring(
                        files[CIN].getName().lastIndexOf("."),
                        files[CIN].getName().length()).toLowerCase();
                if (ext.equals(".jpg") || ext.equals(".gif") || ext.equals(".bmp")) {
                    PictureNumber++;
                    ImageFiles.add(files[CIN]);
                }
            }

            for (int mm = 0; mm < ImageFiles.size(); mm++) {
                SmallLabels.add(new JLabel());
                SmallPanels.add(new JPanel());
                SmallTextFields.add(new JTextField());
            }
            int i = ImageFiles.size();
            Runnable threadimages1 = new ThreadImages(ImageFiles, 0, i / 6, SmallLabels, SmallTextFields, SmallPanels, ImagePanel);
            Runnable threadimages2 = new ThreadImages(ImageFiles, i / 6, i / 6 * 2, SmallLabels, SmallTextFields, SmallPanels, ImagePanel);
            Runnable threadimages3 = new ThreadImages(ImageFiles, i / 6 * 2, i / 6 * 3, SmallLabels, SmallTextFields, SmallPanels, ImagePanel);
            Runnable threadimages4 = new ThreadImages(ImageFiles, i / 6 * 3, i / 6 * 4, SmallLabels, SmallTextFields, SmallPanels, ImagePanel);
            Runnable threadimages5 = new ThreadImages(ImageFiles, i / 6 * 4, i / 6 * 5, SmallLabels, SmallTextFields, SmallPanels, ImagePanel);
            Runnable threadimages6 = new ThreadImages(ImageFiles, i / 6 * 5, i, SmallLabels, SmallTextFields, SmallPanels, ImagePanel);
            Thread t1 = new Thread(threadimages1);
            Thread t2 = new Thread(threadimages2);
            Thread t3 = new Thread(threadimages3);
            Thread t4 = new Thread(threadimages4);
            Thread t5 = new Thread(threadimages5);
            Thread t6 = new Thread(threadimages6);
            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t5.start();
            t6.start();
            /* try {
            while (t6.isAlive()||t5.isAlive()||t4.isAlive()||t3.isAlive()||t2.isAlive()||t1.isAlive()) {
            Thread.sleep(500);
            }
            } catch (InterruptedException ex) {
            Logger.getLogger(主界面.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            ImagesQuantity = ImageFiles.size();
            System.out.println("图片总数为:" + ImagesQuantity);
            InitLabelListener();

            if (ImagesQuantity > 20) {
                BigScrollPane.getVerticalScrollBar().setVisible(true);
                if (ImagesQuantity % 5 == 0) {
                    ImagePanel.setPreferredSize(new Dimension(632, 125 * (ImagesQuantity / 5)));
                } else {
                    ImagePanel.setPreferredSize(new Dimension(632, 125 * (ImagesQuantity / 5 + 1)));
                }
                BigScrollPane.getVerticalScrollBar().setValue(0);
            } else {
                ImagePanel.setPreferredSize(new Dimension(632, 399));
            }


        } catch (StringIndexOutOfBoundsException ex) {
            ImagePanel.setPreferredSize(new Dimension(632, 399));
        }

    }

    /*   public void ShowImages(MouseEvent e, TreePath path, int FlagTree) {
    try {
    SmallPanels.clear();
    SmallLabels.clear();
    SmallTextFields.clear();
    ImagePanel.removeAll();
    ImagePanel.repaint();
    int flag = 0;
    ImagePanel.setLayout(null);
    String filepath = null;
    ImagesQuantity = 0;
    int ChangeImagesNum = 0;      //这里定义一个当前图片在数组的下标
    JTree tree = (JTree) e.getSource();
    int row = tree.getRowForLocation(e.getX(), e.getY());
    if (row == -1) {
    return;
    }
    if (FlagTree == 0) {
    path = tree.getPathForRow(row);
    }

    if (path == null) {
    return;
    }
    FileNode node = (FileNode) path.getLastPathComponent();
    if (node == null) {
    return;
    }
    try {
    filepath = node.getWorR1();
    FilePath = node.getWorR1();
    System.out.println("node=" + path);
    } catch (IOException ex) {
    Logger.getLogger(主界面.class.getName()).log(Level.SEVERE, null, ex);
    }
    System.out.println(filepath);

    for (int i = 0; i < jComboBox1.getItemCount(); i++) {
    if (filepath.equals(jComboBox1.getItemAt(i))) {
    flag = 1;
    }
    }
    if (flag == 0) {
    jComboBox1.addItem(filepath);
    TreePaths.add(path);
    }
    jComboBox1.setSelectedItem(filepath);
    File[] files = node.getWorR().listFiles();
    ClickedFilePath.clear();
    for (int FilesQuantity = 0; FilesQuantity < files.length; FilesQuantity++) {
    ClickedFilePath.add(files[FilesQuantity]);
    }
    int PictureNumber = 0;
    for (int CIN = 0; CIN < files.length; CIN++) {
    String ext = files[CIN].getName().substring(
    files[CIN].getName().lastIndexOf("."),
    files[CIN].getName().length()).toLowerCase();
    if (ext.equals(".jpg") || ext.equals(".gif") || ext.equals(".bmp")) {
    PictureNumber++;
    }
    }

    for (int i = 0; i < files.length; i++) {
    String ext = files[i].getName().substring(
    files[i].getName().lastIndexOf("."),
    files[i].getName().length()).toLowerCase();
    if (ext.equals(".jpg") || ext.equals(".gif") || ext.equals(".bmp")) {
    //Image ic=new Image(files[i].getAbsolutePath()) {};
    ImageIcon ic1 = new ImageIcon(files[i].getAbsolutePath());
    double h1 = ic1.getIconHeight();
    double w1 = ic1.getIconWidth();
    if (h1 < 77 && w1 < 100) {
    Image im = ic1.getImage().getScaledInstance((int) w1, (int) h1, Image.SCALE_DEFAULT);//改变大小
    ImageIcon ic2 = new ImageIcon(im);//从新得到一个固定图片
    SmallLabels.add(new JLabel());
    SmallTextFields.add(new JTextField());
    SmallLabels.get(ChangeImagesNum).setIcon(ic2);
    ic2.setImageObserver(SmallLabels.get(ChangeImagesNum));
    SmallTextFields.get(ChangeImagesNum).setText(files[i].getName());

    } else {
    if (h1 * 180 > w1 * 142) {
    Image im = ic1.getImage().getScaledInstance((int) (w1 / (h1 / 81)), 81, Image.SCALE_DEFAULT);//改变大小
    ImageIcon ic2 = new ImageIcon(im);//从新得到一个固定图片
    SmallLabels.add(new JLabel());
    SmallTextFields.add(new JTextField());
    SmallLabels.get(ChangeImagesNum).setIcon(ic2);
    ic2.setImageObserver(SmallLabels.get(ChangeImagesNum));
    SmallTextFields.get(ChangeImagesNum).setText(files[i].getName());
    } else {
    Image im = ic1.getImage().getScaledInstance(105, (int) (h1 / (w1 / 105)), Image.SCALE_DEFAULT);//改变大小
    final ImageIcon ic2 = new ImageIcon(im);//从新得到一个固定图片
    SmallLabels.add(new JLabel());
    SmallTextFields.add(new JTextField());
    SmallLabels.get(ChangeImagesNum).setIcon(ic2);
    ic2.setImageObserver(SmallLabels.get(ChangeImagesNum));
    SmallTextFields.get(ChangeImagesNum).setText(files[i].getName());
    }
    }
    SmallPanels.add(new JPanel());
    ImagePanel.add(SmallPanels.get(ChangeImagesNum));
    if (PictureNumber > 20) {
    SmallPanels.get(ChangeImagesNum).setBounds(ChangeImagesNum % 5 * 131, 1 + (ChangeImagesNum / 5 * 125), 120, 110);
    } else {
    SmallPanels.get(ChangeImagesNum).setBounds(ChangeImagesNum % 5 * 135, 1 + (ChangeImagesNum / 5 * 125), 120, 110);
    }
    SmallPanels.get(ChangeImagesNum).setLayout(new java.awt.BorderLayout(0, 0));
    SmallPanels.get(ChangeImagesNum).add(SmallLabels.get(ChangeImagesNum), java.awt.BorderLayout.CENTER);
    SmallPanels.get(ChangeImagesNum).add(SmallTextFields.get(ChangeImagesNum), java.awt.BorderLayout.PAGE_END);
    SmallTextFields.get(ChangeImagesNum).setBorder(null);
    SmallTextFields.get(ChangeImagesNum).setHorizontalAlignment(SwingConstants.CENTER);
    SmallTextFields.get(ChangeImagesNum).setEditable(false);

    if (ChangeImagesNum == 0) {
    SmallLabels.get(0).setDisplayedMnemonic(501);
    } else {
    SmallLabels.get(ChangeImagesNum).setDisplayedMnemonic(ChangeImagesNum);
    }
    SmallLabels.get(ChangeImagesNum).setHorizontalAlignment(SwingConstants.CENTER);
    SmallLabels.get(ChangeImagesNum).setOpaque(true);
    SmallLabels.get(ChangeImagesNum).setBackground(new java.awt.Color(244, 244, 244));

    ChangeImagesNum++;
    }
    }
    ImagesQuantity = ChangeImagesNum;
    int Record = ImagesQuantity;
    System.out.println("图片总数为:" + ImagesQuantity);
    InitLabelListener();

    if (ImagesQuantity > 20) {
    BigScrollPane.getVerticalScrollBar().setVisible(true);
    ImagePanel.setPreferredSize(new Dimension(632, SmallPanels.get(ChangeImagesNum - 1).getY() + 116));
    BigScrollPane.getVerticalScrollBar().setValue(0);
    System.out.println("bvcxz");
    } else {
    ImagePanel.setPreferredSize(new Dimension(632, 399));
    }

    } catch (StringIndexOutOfBoundsException ex) {
    ImagePanel.setPreferredSize(new Dimension(632, 399));
    }
    }*/
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jComboBox1 = new javax.swing.JComboBox();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jComboBox2 = new javax.swing.JComboBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/后退.jpg"))); // NOI18N
        jButton1.setText("后退");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/前进.JPG"))); // NOI18N
        jButton2.setText("前进");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/向上.jpg"))); // NOI18N
        jButton3.setText("向上");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/刷新.jpg"))); // NOI18N
        jButton4.setText("刷新");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton4);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/删除.jpg"))); // NOI18N
        jButton5.setText("删除");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton5);

        jScrollPane1.setViewportView(jTree1);
        RunTree(jTree1);

        jComboBox1.setPreferredSize(new java.awt.Dimension(62, 14));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "请选择文件夹外观", " ", " ", " " }));

        jMenuBar1.setMaximumSize(new java.awt.Dimension(1000, 32769));

        jMenu1.setText("文件");

        jMenuItem1.setText("打开");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("退出");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("工具");

        jMenuItem3.setText("批量改名");
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("帮助");

        jMenuItem4.setText("关于");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
                    .addComponent(jComboBox1, 0, 685, Short.MAX_VALUE)))
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                        .addGap(2, 2, 2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Open();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        IntroduceFrame.setVisible(true);
        IntroduceTextArea.setText("\n      制作人:浪客剑心\n" + "      学号:200830690130\n" + "      班级:华南农业大学\n");
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jComboBox1.getSelectedIndex() > 0) {
            Back();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (jComboBox1.getSelectedIndex() + 1 < TreePaths.size()) {
            Next();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Up();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        ShowImages(E, new TreePath(0), 0);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Delete();
    }//GEN-LAST:event_jButton5ActionPerformed

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//尝试使用系统外观
        } catch (Exception e) {
            System.err.println("不能使用系统外观");
        }

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                主界面 zhu = new 主界面();
                zhu.Init();
                zhu.setLocationRelativeTo(null);
                zhu.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
}
