package oriedita.editor.swing.dialog;

import oriedita.editor.service.ButtonService;
import oriedita.editor.tools.KeyStrokeUtil;
import oriedita.editor.tools.ResourceUtil;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

public class SelectKeyStrokeDialog extends JDialog {
    private final String key;
    private final ButtonService buttonService;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel keyStrokeLabel;
    private JButton buttonClear;
    private JLabel conflictLabel;

    private KeyStroke keyStroke;

    public SelectKeyStrokeDialog(JFrame owner, String key, ButtonService buttonService, KeyStroke keyStroke) {
        super(owner, "Set Key Stroke");
        this.key = key;
        this.buttonService = buttonService;
        setContentPane(contentPane);
        setModal(true);

        setKeyStroke(keyStroke);

        KeyStrokeUtil.resetButton(buttonOK);
        KeyStrokeUtil.resetButton(buttonCancel);
        KeyStrokeUtil.resetButton(buttonClear);

        KeyListener adapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Ignore when only an action key is pressed
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_CONTROL:
                    case KeyEvent.VK_ALT:
                    case KeyEvent.VK_ALT_GRAPH:
                    case KeyEvent.VK_SHIFT:
                        setKeyStroke(null);
                        return;
                }

                KeyStroke keyStrokeForEvent = KeyStroke.getKeyStrokeForEvent(e);
                if (keyStrokeForEvent != null
                        && buttonService.getActionFromKeystroke(keyStrokeForEvent) != null
                        && !Objects.equals(buttonService.getActionFromKeystroke(keyStrokeForEvent), key)) {
                    String conflictingButton = (String) owner.getRootPane()
                            .getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                            .get(keyStrokeForEvent);

                    setConflict("Conflicting with <br>" + ResourceUtil.getBundleString("name", conflictingButton));
                } else {
                    setConflict(null);
                }
                setKeyStroke(keyStrokeForEvent);
            }
        };

        addKeyListener(adapter);
        keyStrokeLabel.addKeyListener(adapter);
        buttonOK.addKeyListener(adapter);
        buttonCancel.addKeyListener(adapter);
        buttonClear.addKeyListener(adapter);

        buttonClear.addActionListener(e -> setKeyStroke(null));

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        pack();
        setMinimumSize(getSize());
        setLocationRelativeTo(null);
        setVisible(true);

        keyStrokeLabel.grabFocus();
    }

    private void setConflict(String s) {

        if (s == null) {
            conflictLabel.setText("<html>");
            buttonOK.setEnabled(true);
        } else {
            conflictLabel.setText("<html><span color='red'>" + s + "</span>");
            buttonOK.setEnabled(false);
        }
    }

    private void setKeyStroke(KeyStroke keyStroke) {
        if (keyStroke == null) {
            keyStrokeLabel.setText("<html><i>Press any key</i>");
        } else {
            keyStrokeLabel.setText("<html>" + KeyStrokeUtil.toStringWithMetaIcon(KeyStrokeUtil.toString(keyStroke)));
        }
        this.keyStroke = keyStroke;
    }

    private void onOK() {
        if (keyStroke != null
                && buttonService.getActionFromKeystroke(keyStroke) != null
                && !Objects.equals(buttonService.getActionFromKeystroke(keyStroke), key)) {
            String conflictingButton = buttonService.getActionFromKeystroke(keyStroke);
            JOptionPane.showMessageDialog(getOwner(), "Conflicting KeyStroke! Conflicting with " + conflictingButton);
            return;
        }

        ResourceUtil.updateBundleKey("hotkey", key, keyStroke == null ? "" : keyStroke.toString());
        buttonService.setKeyStroke(keyStroke, key);
        dispose();
    }

    private void onCancel() {
        dispose();
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
        contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());
        contentPane.setMinimumSize(new Dimension(220, 150));
        contentPane.setPreferredSize(new Dimension(220, 150));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        contentPane.add(panel1, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(spacer1, gbc);
        buttonOK = new JButton();
        buttonOK.setFocusable(false);
        buttonOK.setText("OK");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(buttonOK, gbc);
        buttonCancel = new JButton();
        buttonCancel.setFocusable(false);
        buttonCancel.setText("Cancel");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(buttonCancel, gbc);
        buttonClear = new JButton();
        buttonClear.setFocusable(false);
        buttonClear.setText("Clear");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(buttonClear, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(spacer2, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(spacer3, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        contentPane.add(spacer4, gbc);
        final JPanel spacer5 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.VERTICAL;
        contentPane.add(spacer5, gbc);
        final JPanel spacer6 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.VERTICAL;
        contentPane.add(spacer6, gbc);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        contentPane.add(panel2, gbc);
        panel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-16777216)), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        keyStrokeLabel = new JLabel();
        keyStrokeLabel.setText("<html><i>None</i>");
        panel2.add(keyStrokeLabel);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        contentPane.add(panel3, gbc);
        conflictLabel = new JLabel();
        conflictLabel.setText("<html>");
        panel3.add(conflictLabel);
        final JPanel spacer7 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        contentPane.add(spacer7, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

}
