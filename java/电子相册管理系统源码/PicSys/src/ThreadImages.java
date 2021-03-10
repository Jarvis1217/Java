
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ThreadImages implements Runnable {

    ArrayList<JLabel> SmallLabels = new ArrayList<JLabel>();
    ArrayList<JTextField> SmallTextFields = new ArrayList<JTextField>();
    ArrayList<JPanel> SmallPanels = new ArrayList<JPanel>();
    JPanel ImagePanel;
    int i;
    ArrayList<File> ImageFiles;
    int k;

    ThreadImages(ArrayList<File> ImageFiles, int k, int i, ArrayList<JLabel> SmallLabels, ArrayList<JTextField> SmallTextFields, ArrayList<JPanel> SmallPanels, JPanel ImagePanel) {
        this.k = k;
        this.ImageFiles = ImageFiles;
        this.i = i;
        this.SmallLabels = SmallLabels;
        this.SmallTextFields = SmallTextFields;
        this.SmallPanels = SmallPanels;
        this.ImagePanel = ImagePanel;
    }

    public synchronized void run() {

        for (int j = k; j < i; j++) {

            ImageIcon ic1 = new ImageIcon(ImageFiles.get(j).getAbsolutePath());
            double h1 = ic1.getIconHeight();
            double w1 = ic1.getIconWidth();
            if (h1 < 77 && w1 < 100) {
                Image im = ic1.getImage().getScaledInstance((int) w1, (int) h1, Image.SCALE_DEFAULT);//改变大小
                ImageIcon ic2 = new ImageIcon(im);//从新得到一个固定图片
                //SmallLabels.add(j, new JLabel());
                //SmallTextFields.add(j, new JTextField());
                SmallLabels.get(j).setIcon(ic2);
                ic2.setImageObserver(SmallLabels.get(j));
                SmallTextFields.get(j).setText(ImageFiles.get(j).getName());

            } else {
                if (h1 * 180 > w1 * 142) {
                    Image im = ic1.getImage().getScaledInstance((int) (w1 / (h1 / 81)), 81, Image.SCALE_DEFAULT);//改变大小
                    ImageIcon ic2 = new ImageIcon(im);//从新得到一个固定图片
                    SmallLabels.get(j).setIcon(ic2);
                    ic2.setImageObserver(SmallLabels.get(j));
                    SmallTextFields.get(j).setText(ImageFiles.get(j).getName());
                } else {
                    Image im = ic1.getImage().getScaledInstance(105, (int) (h1 / (w1 / 105)), Image.SCALE_DEFAULT);//改变大小
                    final ImageIcon ic2 = new ImageIcon(im);//从新得到一个固定图片                                    
                    SmallLabels.get(j).setIcon(ic2);
                    ic2.setImageObserver(SmallLabels.get(j));
                    SmallTextFields.get(j).setText(ImageFiles.get(j).getName());
                }
            }
           
            ImagePanel.add(SmallPanels.get(j));
            if (ImageFiles.size()>20) {
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
        }
      

    }
}
