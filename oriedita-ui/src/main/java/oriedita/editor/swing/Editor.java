package oriedita.editor.swing;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import oriedita.editor.Canvas;
import oriedita.editor.CanvasUI;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.concurrent.Executor;

/**
 * BorderLayout for each different part.
 */
@ApplicationScoped
public class Editor {
    private final Canvas canvas1;
    private JPanel root;
    private CanvasUI canvas;
    private RightPanel rightPanel;
    private BottomPanel bottomPanel;
    private TopPanel topPanel;
    private LeftPanel leftPanel;

    @Inject
    public Editor(Canvas canvas, RightPanel rightPanel, BottomPanel bottomPanel, TopPanel topPanel, LeftPanel leftPanel) {
        this.canvas1 = canvas;
        this.rightPanel = rightPanel;
        this.bottomPanel = bottomPanel;
        this.topPanel = topPanel;
        this.leftPanel = leftPanel;

        $$$setupUI$$$();
    }

    public void init(Executor service) throws InterruptedException {
        service.execute(leftPanel::init);
        service.execute(topPanel::init);
        service.execute(rightPanel::init);
        service.execute(bottomPanel::init);
    }

    private void createUIComponents() {
        this.canvas = canvas1.getCanvasImpl();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        root = new JPanel();
        root.setLayout(new BorderLayout(0, 0));
        root.add(bottomPanel.$$$getRootComponent$$$(), BorderLayout.SOUTH);
        root.add(rightPanel.$$$getRootComponent$$$(), BorderLayout.EAST);
        root.add(canvas, BorderLayout.CENTER);
        root.add(topPanel.$$$getRootComponent$$$(), BorderLayout.NORTH);
        root.add(leftPanel.$$$getRootComponent$$$(), BorderLayout.WEST);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return root;
    }

    public RightPanel getRightPanel() {
        return rightPanel;
    }

    public BottomPanel getBottomPanel() {
        return bottomPanel;
    }

    public TopPanel getTopPanel() {
        return topPanel;
    }

    public LeftPanel getLeftPanel() {
        return leftPanel;
    }
}
