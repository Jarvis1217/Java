
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FullFrame extends javax.swing.JFrame {

    ArrayList<File> ClickedFilePath = new ArrayList<File>();
    ArrayList<JTextField> ImagesNameTF = new ArrayList<JTextField>();
    ArrayList<JLabel> SmallLabels = new ArrayList<JLabel>();
    int SelectImage = 0;
    double CurrentMultiple = 1;
    int ImagesQuantity = 0;
    int flag = 0;
    AutoPlay play;
    String OpenFilePath;

    /** Creates new form NewJFrame */
    public FullFrame(ArrayList<File> ClickedFilePath, ArrayList<JTextField> ImagesNameTF, int SelectImage, int ImagesQuantity, ArrayList<JLabel> SmallLabels, String OpenFilePath) {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.ClickedFilePath = ClickedFilePath;
        this.ImagesNameTF = ImagesNameTF;
        this.SelectImage = SelectImage;
        this.ImagesQuantity = ImagesQuantity - 1;
        this.SmallLabels = SmallLabels;
        this.OpenFilePath = OpenFilePath;
        if (SelectImage == -1) {
            jButton2.setEnabled(false);
            jButton1.setEnabled(false);
            jButton5.setEnabled(false);
            jButton6.setEnabled(false);
        }
        if (SelectImage == 0) {
            jButton1.setEnabled(false);
            jButton1.setEnabled(false);

        }
        if (SelectImage == ImagesQuantity - 1) {
            jButton1.setEnabled(true);
            jButton2.setEnabled(false);
            jButton3.setEnabled(true);
            jButton4.setEnabled(true);
            jButton5.setEnabled(false);
            jButton6.setEnabled(false);
        }
        if (SelectImage != ImagesQuantity - 1 && SelectImage != 0 && SelectImage != -1) {
            jButton1.setEnabled(true);
            jButton2.setEnabled(true);
            jButton3.setEnabled(true);
            jButton4.setEnabled(true);
            jButton5.setEnabled(true);
            jButton6.setEnabled(true);
        }

    }

    public void Back() {
        if (SelectImage - 1 >= 0) {
            for (int i = 0; i < ClickedFilePath.size(); i++) {

                if (ClickedFilePath.get(i).getName().equals(ImagesNameTF.get(SelectImage - 1).getText())) {

                    ImageIcon ic1 = new ImageIcon(ClickedFilePath.get(i).getAbsolutePath());
                    Image im = ic1.getImage().getScaledInstance(ic1.getIconWidth(), ic1.getIconHeight(), Image.SCALE_DEFAULT);
                    ImageIcon ic2 = new ImageIcon(im);
                    getJLabel1().setIcon(ic2);
                }
            }
            SelectImage = SelectImage - 1;
            if (SelectImage == 0) {
                getJButton1().setEnabled(false);
            } else {
                getJButton1().setEnabled(true);
                jButton2.setEnabled(true);
                jButton3.setEnabled(true);
                jButton4.setEnabled(true);
                jButton5.setEnabled(true);
                jButton6.setEnabled(true);
            }
        }
    }

    public void Forward() {
        if (SelectImage + 1 <= ImagesQuantity) {
            for (int i = 0; i < ClickedFilePath.size(); i++) {

                if (ClickedFilePath.get(i).getName().equals(ImagesNameTF.get(SelectImage + 1).getText())) {

                    ImageIcon ic1 = new ImageIcon(ClickedFilePath.get(i).getAbsolutePath());
                    Image im = ic1.getImage().getScaledInstance(ic1.getIconWidth(), ic1.getIconHeight(), Image.SCALE_DEFAULT);
                    ImageIcon ic2 = new ImageIcon(im);
                    getJLabel1().setIcon(ic2);
                }
            }

            SelectImage = SelectImage + 1;
            if (SelectImage == ImagesQuantity) {
                jButton2.setEnabled(false);
                jButton5.setEnabled(false);
                jButton6.setEnabled(false);
                getJButton1().setEnabled(true);
                jButton3.setEnabled(true);
                jButton4.setEnabled(true);
            } else {
                jButton1.setEnabled(true);
            }
        }
    }

    public void Enlarge() {
        if (SelectImage == -1) {
            ImageIcon ic1 = new ImageIcon(OpenFilePath);

            double w = ic1.getIconWidth();
            double h = ic1.getIconHeight();
            Image im = ic1.getImage().getScaledInstance((int) (w * (CurrentMultiple + 0.25)), (int) (h * (CurrentMultiple + 0.25)), Image.SCALE_DEFAULT);
            ImageIcon ic2 = new ImageIcon(im);
            getJLabel1().setIcon(ic2);

        } else {
            if (SelectImage != flag) {
                CurrentMultiple = 1;
            }
            for (int i = 0; i < ClickedFilePath.size(); i++) {
                if (ClickedFilePath.get(i).getName().equals(ImagesNameTF.get(SelectImage).getText())) {
                    ImageIcon ic1 = new ImageIcon(ClickedFilePath.get(i).getAbsolutePath());

                    double w = ic1.getIconWidth();
                    double h = ic1.getIconHeight();
                    Image im = ic1.getImage().getScaledInstance((int) (w * (CurrentMultiple + 0.25)), (int) (h * (CurrentMultiple + 0.25)), Image.SCALE_DEFAULT);
                    ImageIcon ic2 = new ImageIcon(im);
                    getJLabel1().setIcon(ic2);

                }
            }
        }
        CurrentMultiple = CurrentMultiple + 0.25;
        flag = SelectImage;
    }

    public void Decrease() {
        if (SelectImage == -1) {

            ImageIcon ic1 = new ImageIcon(OpenFilePath);

            double w = ic1.getIconWidth();
            double h = ic1.getIconHeight();
            Image im = ic1.getImage().getScaledInstance((int) (w * (CurrentMultiple - 0.25)), (int) (h * (CurrentMultiple - 0.25)), Image.SCALE_DEFAULT);
            ImageIcon ic2 = new ImageIcon(im);
            getJLabel1().setIcon(ic2);
        } else {
            if (SelectImage != flag) {
                CurrentMultiple = 1;
            }
            for (int i = 0; i < ClickedFilePath.size(); i++) {
                if (ClickedFilePath.get(i).getName().equals(ImagesNameTF.get(SelectImage).getText())) {
                    ImageIcon ic1 = new ImageIcon(ClickedFilePath.get(i).getAbsolutePath());

                    double w = ic1.getIconWidth();
                    double h = ic1.getIconHeight();
                    Image im = ic1.getImage().getScaledInstance((int) (w * (CurrentMultiple - 0.25)), (int) (h * (CurrentMultiple - 0.25)), Image.SCALE_DEFAULT);
                    ImageIcon ic2 = new ImageIcon(im);
                    getJLabel1().setIcon(ic2);
                }
            }
        }
        CurrentMultiple = CurrentMultiple - 0.25;
        flag = SelectImage;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();

        jToolBar1.setRollover(true);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/2.JPG"))); // NOI18N
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/1.jpg"))); // NOI18N
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/3.jpg"))); // NOI18N
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/4.jpg"))); // NOI18N
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton4);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/5.jpg"))); // NOI18N
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton5);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture/6.jpg"))); // NOI18N
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton6);

        jScrollPane1.setViewportView(jLabel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(278, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings("empty-statement")
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Decrease();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Back();
}//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Forward();
}//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Enlarge();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        jButton6.setEnabled(true);
        jButton2.setEnabled(false);
        getJButton1().setEnabled(false);
        jButton3.setEnabled(false);
        jButton4.setEnabled(false);
        jButton5.setEnabled(false);
        play = new AutoPlay(this);
        play.start();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        play.stop();
        jButton2.setEnabled(true);
        getJButton1().setEnabled(true);
        jButton3.setEnabled(true);
        jButton4.setEnabled(true);
        jButton5.setEnabled(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JLabel getJLabel1() {
        return jLabel1;
    }

    public void setJLabel1(javax.swing.JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }

    public javax.swing.JButton getJButton1() {
        return jButton1;
    }
}
