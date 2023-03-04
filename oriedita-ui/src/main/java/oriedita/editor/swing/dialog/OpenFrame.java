package oriedita.editor.swing.dialog;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import oriedita.editor.canvas.CreasePattern_Worker;
import oriedita.editor.canvas.MouseMode;
import oriedita.editor.databinding.ApplicationModel;
import oriedita.editor.databinding.CanvasModel;
import oriedita.editor.handler.CustomLineTypes;
import oriedita.editor.service.ButtonService;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;

public class OpenFrame extends JDialog {
    private JCheckBox selectAnd3ClickCheckBox;
    private JButton o_F_checkButton;
    private JButton foldableLinePlusGridInputButton;
    private JButton select_polygonButton;
    private JButton unselect_polygonButton;
    private JButton select_lXButton;
    private JButton unselect_lXButton;
    private JButton del_lButton;
    private JButton del_l_XButton;
    private JPanel panel;
    private JButton replace_lineButton;
    private JComboBox fromLineDropBox;
    private JComboBox toLineDropBox;

    private CustomLineTypes customLineTypes;

    private ApplicationModel applicationModel;

    public OpenFrame(String name, Frame owner, CanvasModel canvasModel, CreasePattern_Worker mainCreasePatternWorker, ButtonService buttonService, ApplicationModel applicationModel) {
        super(owner, name);
        this.applicationModel = applicationModel;
        setData(applicationModel);

        setContentPane($$$getRootComponent$$$());

        setDefaultCloseOperation(HIDE_ON_CLOSE);

        buttonService.registerButton(o_F_checkButton, "o_F_checkAction");
        buttonService.registerButton(foldableLinePlusGridInputButton, "foldableLinePlusGridInputAction");
        buttonService.registerButton(select_polygonButton, "select_polygonAction");
        buttonService.registerButton(unselect_polygonButton, "unselect_polygonAction");
        buttonService.registerButton(select_lXButton, "select_lXAction");
        buttonService.registerButton(unselect_lXButton, "unselect_lXAction");
        buttonService.registerButton(del_lButton, "del_lAction");
        buttonService.registerButton(del_l_XButton, "del_l_XAction");
        buttonService.registerButton(selectAnd3ClickCheckBox, "selectAnd3ClickAction");
        buttonService.registerButton(replace_lineButton, "replace_lineButton");

        o_F_checkButton.addActionListener(e -> canvasModel.setMouseMode(MouseMode.FLAT_FOLDABLE_CHECK_63));
        foldableLinePlusGridInputButton.addActionListener(e -> {
            canvasModel.setMouseMode(MouseMode.FOLDABLE_LINE_INPUT_39);
            canvasModel.setMouseModeAfterColorSelection(MouseMode.FOLDABLE_LINE_INPUT_39);

            mainCreasePatternWorker.unselect_all(false);
        });
        select_polygonButton.addActionListener(e -> canvasModel.setMouseMode(MouseMode.SELECT_POLYGON_66));
        unselect_polygonButton.addActionListener(e -> canvasModel.setMouseMode(MouseMode.UNSELECT_POLYGON_67));
        select_lXButton.addActionListener(e -> canvasModel.setMouseMode(MouseMode.SELECT_LINE_INTERSECTING_68));
        unselect_lXButton.addActionListener(e -> canvasModel.setMouseMode(MouseMode.UNSELECT_LINE_INTERSECTING_69));
        del_lButton.addActionListener(e -> canvasModel.setMouseMode(MouseMode.CREASE_DELETE_OVERLAPPING_64));
        del_l_XButton.addActionListener(e -> canvasModel.setMouseMode(MouseMode.CREASE_DELETE_INTERSECTING_65));
        replace_lineButton.addActionListener(e -> {
            canvasModel.setMouseMode(MouseMode.REPLACE_LINE_TYPE_SELECT_72);
            mainCreasePatternWorker.unselect_all(false);
        });
        fromLineDropBox.addActionListener(e -> {
            applicationModel.setCustomFromLineType(CustomLineTypes.from(fromLineDropBox.getSelectedIndex() - 1));
            fromLineDropBox.setSelectedIndex(applicationModel.getCustomFromLineType().getType() + 1);
        });
        toLineDropBox.addActionListener(e -> {
            applicationModel.setCustomToLineType(CustomLineTypes.from(toLineDropBox.getSelectedIndex()));
            toLineDropBox.setSelectedIndex(applicationModel.getCustomToLineType().getType());
        });
        selectAnd3ClickCheckBox.addActionListener(e -> canvasModel.setCkbox_add_frame_SelectAnd3click_isSelected(selectAnd3ClickCheckBox.isSelected()));

        pack();
        setResizable(false);
    }

    public void setData(PropertyChangeEvent e, CanvasModel data) {
        if (e == null || e.getPropertyName() == null || e.getPropertyName().equals("mouseMode")) {
            MouseMode m = data.getMouseMode();

            o_F_checkButton.setSelected(m == MouseMode.FLAT_FOLDABLE_CHECK_63);
            foldableLinePlusGridInputButton.setSelected(m == MouseMode.FOLDABLE_LINE_INPUT_39);
            select_polygonButton.setSelected(m == MouseMode.SELECT_POLYGON_66);
            unselect_polygonButton.setSelected(m == MouseMode.UNSELECT_POLYGON_67);
            unselect_lXButton.setSelected(m == MouseMode.UNSELECT_LINE_INTERSECTING_69);
            select_lXButton.setSelected(m == MouseMode.SELECT_LINE_INTERSECTING_68);
            unselect_lXButton.setSelected(m == MouseMode.UNSELECT_LINE_INTERSECTING_69);
            del_lButton.setSelected(m == MouseMode.CREASE_DELETE_OVERLAPPING_64);
            del_l_XButton.setSelected(m == MouseMode.CREASE_DELETE_INTERSECTING_65);
            replace_lineButton.setSelected(m == MouseMode.REPLACE_LINE_TYPE_SELECT_72);
        }

        selectAnd3ClickCheckBox.setSelected(data.isCkbox_add_frame_SelectAnd3click_isSelected());
    }

    public void setData(ApplicationModel applicationModel) {
        fromLineDropBox.setSelectedIndex(applicationModel.getCustomFromLineType().getType() + 1);
        toLineDropBox.setSelectedIndex(applicationModel.getCustomToLineType().getType());
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new GridLayoutManager(1, 1, new Insets(10, 10, 10, 10), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        panel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        selectAnd3ClickCheckBox = new JCheckBox();
        selectAnd3ClickCheckBox.setText("sel<=>mcm");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(selectAnd3ClickCheckBox, gbc);
        o_F_checkButton = new JButton();
        o_F_checkButton.setText("O_F_check");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(o_F_checkButton, gbc);
        select_polygonButton = new JButton();
        select_polygonButton.setBackground(new Color(-16711936));
        select_polygonButton.setText("select_polygon");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(select_polygonButton, gbc);
        select_lXButton = new JButton();
        select_lXButton.setBackground(new Color(-16711936));
        select_lXButton.setText("select_lX");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(select_lXButton, gbc);
        del_lButton = new JButton();
        del_lButton.setText("Del_l");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(del_lButton, gbc);
        replace_lineButton = new JButton();
        replace_lineButton.setMaximumSize(new Dimension(-1, -1));
        replace_lineButton.setMinimumSize(new Dimension(-1, -1));
        replace_lineButton.setText("replace_line");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(replace_lineButton, gbc);
        fromLineDropBox = new JComboBox();
        fromLineDropBox.setMaximumSize(new Dimension(-1, -1));
        fromLineDropBox.setMinimumSize(new Dimension(-1, -1));
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Any");
        defaultComboBoxModel1.addElement("Edge");
        defaultComboBoxModel1.addElement("Mountain");
        defaultComboBoxModel1.addElement("Valley");
        defaultComboBoxModel1.addElement("Aux");
        fromLineDropBox.setModel(defaultComboBoxModel1);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(fromLineDropBox, gbc);
        final JLabel label1 = new JLabel();
        label1.setText(" -> ");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label1, gbc);
        toLineDropBox = new JComboBox();
        toLineDropBox.setMaximumSize(new Dimension(-1, -1));
        toLineDropBox.setMinimumSize(new Dimension(-1, -1));
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("Edge");
        defaultComboBoxModel2.addElement("Mountain");
        defaultComboBoxModel2.addElement("Valley");
        defaultComboBoxModel2.addElement("Aux");
        toLineDropBox.setModel(defaultComboBoxModel2);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(toLineDropBox, gbc);
        unselect_polygonButton = new JButton();
        unselect_polygonButton.setBackground(new Color(-16711936));
        unselect_polygonButton.setText("unselect_polygon");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(unselect_polygonButton, gbc);
        unselect_lXButton = new JButton();
        unselect_lXButton.setBackground(new Color(-16711936));
        unselect_lXButton.setText("unselect_lX");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(unselect_lXButton, gbc);
        del_l_XButton = new JButton();
        del_l_XButton.setText("Del_l_X");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(del_l_XButton, gbc);
        foldableLinePlusGridInputButton = new JButton();
        foldableLinePlusGridInputButton.setIcon(new ImageIcon(getClass().getResource("/ppp/oritatami_kanousen_and_kousitenkei.png")));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(foldableLinePlusGridInputButton, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}