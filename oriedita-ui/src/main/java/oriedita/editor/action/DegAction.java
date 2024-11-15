package oriedita.editor.action;

import oriedita.editor.canvas.CreasePattern_Worker;
import oriedita.editor.canvas.MouseMode;
import oriedita.editor.databinding.AngleSystemModel;
import oriedita.editor.databinding.CanvasModel;
import oriedita.editor.service.ButtonService;

import java.awt.event.ActionEvent;

public class DegAction extends AbstractOrieditaAction implements OrieditaAction{
    private final CanvasModel canvasModel;
    private final AngleSystemModel angleSystemModel;
    private final ButtonService buttonService;
    private final CreasePattern_Worker creasePattern_worker;
    private final MouseMode mouseMode;
    private final AngleSystemModel.AngleSystemInputType angleSystemInputType;

    public DegAction(CanvasModel canvasModel,
                     AngleSystemModel angleSystemModel,
                     ButtonService buttonService,
                     CreasePattern_Worker creasePattern_worker,
                     MouseMode mouseMode,
                     AngleSystemModel.AngleSystemInputType angleSystemInputType){
        this.canvasModel = canvasModel;
        this.angleSystemModel = angleSystemModel;
        this.buttonService = buttonService;
        this.creasePattern_worker = creasePattern_worker;
        this.mouseMode = mouseMode;
        this.angleSystemInputType = angleSystemInputType;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        angleSystemModel.setAngleSystemInputType(angleSystemInputType);
        canvasModel.setMouseMode(mouseMode);
        buttonService.Button_shared_operation();
        creasePattern_worker.unselect_all(false);
    }
}
