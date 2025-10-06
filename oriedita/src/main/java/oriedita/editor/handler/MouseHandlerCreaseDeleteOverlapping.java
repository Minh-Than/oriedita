package oriedita.editor.handler;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import oriedita.editor.canvas.MouseMode;
import oriedita.editor.drawing.tools.Camera;
import oriedita.editor.drawing.tools.DrawingUtil;
import oriedita.editor.handler.step.StepMouseHandler;
import oriedita.editor.handler.step.ObjCoordStepNode;
import origami.Epsilon;
import origami.crease_pattern.element.LineSegment;
import origami.crease_pattern.element.Point;

import java.awt.Graphics2D;

enum CreaseDeleteOverlapStep {
    CLICK_DRAG_POINT
}

@ApplicationScoped
@Handles(MouseMode.CREASE_DELETE_OVERLAPPING_64)
public class MouseHandlerCreaseDeleteOverlapping extends StepMouseHandler<CreaseDeleteOverlapStep> {
    private Point anchorPoint, releasePoint;
    private LineSegment dragSegment;

    @Inject
    public MouseHandlerCreaseDeleteOverlapping() {
        super(CreaseDeleteOverlapStep.CLICK_DRAG_POINT);
        steps.addNode(
                ObjCoordStepNode.createNode(CreaseDeleteOverlapStep.CLICK_DRAG_POINT, this::move_click_drag_point, (p) -> {
                }, this::drag_click_drag_point, this::release_click_drag_point));
    }

    @Override
    public void drawPreview(Graphics2D g2, Camera camera, DrawingSettings settings) {
        super.drawPreview(g2, camera, settings);
        DrawingUtil.drawStepVertex(g2, anchorPoint, d.getLineColor(), camera);
        DrawingUtil.drawStepVertex(g2, releasePoint, d.getLineColor(), camera);
        DrawingUtil.drawLineStep(g2, dragSegment, camera, settings.getLineWidth());
    }

    @Override
    public void reset() {
        resetStep();
        anchorPoint = null;
        releasePoint = null;
        dragSegment = null;
    }

    // Click drag point
    private void move_click_drag_point(Point p) {
        if (p.distance(d.getClosestPoint(p)) < d.getSelectionDistance()) {
            anchorPoint = d.getClosestPoint(p);
        } else
            anchorPoint = null;
    }

    private void drag_click_drag_point(Point p) {
        if (anchorPoint == null)
            return;
        releasePoint = p;
        if (p.distance(d.getClosestPoint(p)) < d.getSelectionDistance()) {
            releasePoint = d.getClosestPoint(p);
        }
        dragSegment = new LineSegment(anchorPoint, releasePoint).withColor(d.getLineColor());
    }

    private CreaseDeleteOverlapStep release_click_drag_point(Point p) {
        if (anchorPoint == null)
            return CreaseDeleteOverlapStep.CLICK_DRAG_POINT;
        if (releasePoint == null || releasePoint.distance(d.getClosestPoint(releasePoint)) > d.getSelectionDistance()) {
            reset();
            return CreaseDeleteOverlapStep.CLICK_DRAG_POINT;
        }
        if (Epsilon.high.gt0(dragSegment.determineLength())) {
            d.getFoldLineSet().deleteInsideLine(dragSegment, "l");// lは小文字のエル
            d.record();
        }
        reset();
        return CreaseDeleteOverlapStep.CLICK_DRAG_POINT;
    }
}
