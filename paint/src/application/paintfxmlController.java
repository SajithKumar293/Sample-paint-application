package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import java.util.Stack;

public class paintfxmlController implements Initializable{

	@FXML
    private Rectangle rect;

    @FXML
    private Circle circle;

    @FXML
    private Line line;
    
    @FXML
    private Ellipse oval;
    
    @FXML
    private Arc arc;
    
    @FXML
    private Button eraser;
    
    @FXML
    private ColorPicker colorpicker;
    
	@FXML
	private Canvas canvas;
	
	boolean rectSelected = false;
	boolean circleSelected = false;
	boolean lineSelected = false;
	boolean toolSelected = false;
	boolean ovalSelected = false;
	boolean arcSelected = false;
	boolean eraserSelected = false;
	double x1,y1,x2,y2;
	
	public void setStartPoint(double d, double e) {
        this.x1 = (d);
        this.y1 = (e);	
    }

    public void setEndPoint(double d, double e) {
        x2 = (d);
        y2 = (e);
    }
    
    public void drawPerfectRect(GraphicsContext g, double x12, double y12, double d, double e) {
        double px = Math.min(x12,d);
        double py = Math.min(y12,e);
        double pw=Math.abs(x12-d);
        double ph=Math.abs(y12-e);
        g.setStroke(colorpicker.getValue());
        g.strokeRect(px, py, pw, ph);
        //Stack<Integer> stk= new Stack<>(); 
        
    }
    
    private void drawPerfectCircle(GraphicsContext g, double x12, double y12, double d, double e) {
    	double px = Math.min(x12,d);
        double py = Math.min(y12,e);
        double pw = Math.abs(x12-d);
        double ph = Math.abs(y12-e);
        double r = Math.max(pw,ph);
        g.setStroke(colorpicker.getValue());
        g.strokeOval(px, py, r, r);
	}
    
    private void drawPerfectLine(GraphicsContext g, double x12, double y12, double x22, double y22) {
    	g.setStroke(colorpicker.getValue());
        g.strokeLine(x12, y12, x22, y22);
	}
    
    

	private void drawPerfectOval(GraphicsContext g, double x12, double y12, double d, double e) {
		double px = Math.min(x12,d);
        double py = Math.min(y12,e);
        double pw = Math.abs(x12-d);
        double ph = Math.abs(y12-e);
        g.setStroke(colorpicker.getValue());
        g.strokeOval(px, py, pw, ph);
		
	}
	
	private void erase(GraphicsContext g, double x12, double y12, double d, double e) {
		 double px = Math.min(x12,d);
        double py = Math.min(y12,e);
        double pw=Math.abs(x12-d);
        double ph=Math.abs(y12-e);
        g.clearRect(px, py, pw, ph);
	}

	GraphicsContext shape;
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		shape = canvas.getGraphicsContext2D();
		canvas.setOnMouseDragged(e -> {
			double x = e.getX();
			double y = e.getY();
			
			if(toolSelected) {
				shape.setFill(colorpicker.getValue());
				shape.fillRoundRect(x, y, 10, 10,10, 10);
			}
		});
		canvas.setOnMousePressed(e ->{
			setStartPoint(e.getX(), e.getY());
			System.out.println(e.getX());
			System.out.println(e.getY());
			});
		canvas.setOnMouseReleased(e -> {	
			setEndPoint(e.getX(), e.getY());
			if(rectSelected) {
				drawPerfectRect(shape,x1, y1, x2, y2);
			}
			if(circleSelected) {
				drawPerfectCircle(shape,x1, y1, x2, y2);
			}
			if(lineSelected) {
				drawPerfectLine(shape,x1, y1, x2, y2);
			}
			if(ovalSelected) {
				drawPerfectOval(shape,x1, y1, x2, y2);
			}
			if(arcSelected) {
				//drawPerfectArc(shape,x1, y1, x2, y2);
			}
			if(eraserSelected) {
				erase(shape,x1, y1, x2, y2);
			}
		});	
		}
			

	public void defaultSelected() {
		rectSelected = false;
		circleSelected = false;
		lineSelected = false;
		toolSelected = false;
		ovalSelected = false;
		arcSelected = false;
		eraserSelected = false;
	}
	
	@FXML
	public void toolselected(ActionEvent e) {
		defaultSelected(); 
		toolSelected = true;
	}
	@FXML
	public void circleselected(MouseEvent e) {
		defaultSelected(); 
		circleSelected = true;
	}
	@FXML
	public void rectselected(MouseEvent e) {
		defaultSelected(); 
		rectSelected = true;
	}
	@FXML
	public void lineselected(MouseEvent e) {
		defaultSelected(); 
		lineSelected = true;
	}
	@FXML
	public void ovalselected(MouseEvent e) {
		defaultSelected(); 
		ovalSelected = true;
	}
	@FXML
	public void arcselected(MouseEvent e) {
		defaultSelected(); 
		arcSelected = true;
	}
	@FXML
	public void eraserselected(ActionEvent e) {
		defaultSelected(); 
		eraserSelected = true;
	}
}
