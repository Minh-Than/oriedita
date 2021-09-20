package origami_editor.editor.databinding;

import origami_editor.editor.folded_figure.FoldedFigure;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class FoldedFigureModel implements Serializable {
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private Color frontColor;
    private Color backColor;
    private Color lineColor;
    private double scale;
    private double rotation;
    private boolean antiAlias;
    private boolean displayShadows;
    private FoldedFigure.State state;

    public int getTransparentTransparency() {
        return transparentTransparency;
    }

    public void setTransparentTransparency(int transparentTransparency) {
        int oldTransparentTransparency = this.transparentTransparency;
        this.transparentTransparency = transparentTransparency;
        this.pcs.firePropertyChange("transparentTransparency", oldTransparentTransparency, transparentTransparency);
    }

    private int transparentTransparency;

    private boolean transparencyColor;

    public boolean isTransparencyColor() {
        return transparencyColor;
    }

    public void setTransparencyColor(boolean transparencyColor) {
        boolean oldTransparencyColor = this.transparencyColor;
        this.transparencyColor = transparencyColor;
        this.pcs.firePropertyChange("transparencyColor", oldTransparencyColor, transparencyColor);
    }

    public int getHistoryTotal() {
        return historyTotal;
    }

    public void setHistoryTotal(int historyTotal) {
        int oldHistoryTotal = this.historyTotal;
        this.historyTotal = Math.max(historyTotal, 0);
        this.pcs.firePropertyChange("historyTotal", oldHistoryTotal, this.historyTotal);
    }

    private int historyTotal;

    public FoldedFigureModel() {
        reset();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    public void reset() {
        scale = 1.0;
        rotation = 0.0;
        antiAlias = true;
        displayShadows = false;
        state = FoldedFigure.State.FRONT_0;

        frontColor = new Color(255, 255, 50);
        backColor = new Color(233, 233, 233);
        lineColor = Color.black;

        historyTotal = 20;

        transparencyColor = false;

        transparentTransparency = 16;

        this.pcs.firePropertyChange(null, null, null);
    }

    public Color getFrontColor() {
        return frontColor;
    }

    public void setFrontColor(Color frontColor) {
        Color oldFrontColor = this.frontColor;
        this.frontColor = frontColor;
        this.pcs.firePropertyChange("frontColor", oldFrontColor, frontColor);
    }

    public Color getBackColor() {
        return backColor;
    }

    public void setBackColor(Color backColor) {
        Color oldFrontColor = this.backColor;
        this.backColor = backColor;
        this.pcs.firePropertyChange("backColor", oldFrontColor, backColor);
    }

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        Color oldFrontColor = this.lineColor;
        this.lineColor = lineColor;
        this.pcs.firePropertyChange("lineColor", oldFrontColor, lineColor);
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        if (scale > 0.0) {
            double oldScale = this.scale;
            this.scale = scale;
            this.pcs.firePropertyChange("scale", oldScale, scale);
        }
    }

    public void zoomIn() {
        setScale(scale * Math.sqrt(Math.sqrt(Math.sqrt(2))));
    }

    public void zoomOut() {
        setScale(scale / Math.sqrt(Math.sqrt(Math.sqrt(2))));
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        double oldRotation = this.rotation;
        this.rotation = rotation;
        this.pcs.firePropertyChange("rotation", oldRotation, rotation);
    }

    public boolean getAntiAlias() {
        return antiAlias;
    }

    public void setAntiAlias(boolean antiAlias) {
        boolean oldAntiAlias = this.antiAlias;
        this.antiAlias = antiAlias;
        this.pcs.firePropertyChange("antiAlias", oldAntiAlias, antiAlias);
    }

    public boolean getDisplayShadows() {
        return displayShadows;
    }

    public void setDisplayShadows(boolean displayShadows) {
        boolean oldDisplayShadows = this.displayShadows;
        this.displayShadows = displayShadows;
        this.pcs.firePropertyChange("displayShadows", oldDisplayShadows, displayShadows);
    }

    public FoldedFigure.State getState() {
        return state;
    }

    public void setState(FoldedFigure.State state) {
        FoldedFigure.State oldState = this.state;
        this.state = state;
        this.pcs.firePropertyChange("state", oldState, state);
    }

    public void toggleAntiAlias() {
        setAntiAlias(!antiAlias);
    }

    public void toggleDisplayShadows() {
        setDisplayShadows(!displayShadows);
    }

    public void advanceState() {
        setState(state.advance());
    }

    public void decreaseTransparency() {
        setTransparentTransparency(Math.max(this.transparentTransparency / 2, 1));
    }

    public void increaseTransparency() {

        setTransparentTransparency(Math.min(this.transparentTransparency * 2, 64));
    }
}
