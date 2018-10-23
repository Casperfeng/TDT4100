package objectstructures;

import javafx.fxml.FXML;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class StopWatchController {

	StopWatch stopWatch;
//	FxmlTimer fxmlTimer;
	
	public StopWatch getStopWatch() {
		return stopWatch;
	}

	public void setStopWatch(StopWatch stopWatch) {
		this.stopWatch = stopWatch;
	}

	@FXML
	void initialize() {
//		stopWatch = new StopWatch();
//		fxmlTimer = new FxmlTimer(this, "#handleRealTick");
//		fxmlTimer.start(1000, 1000);
	}
	
	void updateView() {
		updateViewHands();
	}
	
	@FXML void handleStartAction() {
		stopWatch.start();
		updateView();
	}
	@FXML void handleLapAction() {
		stopWatch.lap();
		updateView();
	}
	@FXML void handleStopAction() {
		stopWatch.stop();		
		updateView();
	}
	
	@FXML Circle clockFace;
	@FXML Line timeSecHand, timeMinHand, timeHourHand;
	@FXML Line lastLapSecHand, lastLapMinHand, lastLapHourHand;
	@FXML Line currentLapSecHand, currentLapMinHand, currentLapHourHand;

	private void updateViewHands() {
		updateViewHands(timeHourHand, timeMinHand, timeSecHand, stopWatch.getTime());
		updateViewHands(lastLapHourHand, lastLapMinHand, lastLapSecHand, stopWatch.getLastLapTime());
		updateViewHands(currentLapHourHand, currentLapMinHand, currentLapSecHand, stopWatch.getLapTime());
	}

	private void updateViewHands(Line hourHand, Line minHand, Line secHand, int time) {
		updateViewHand(hourHand, (time / (60 * 60)) * 2 * Math.PI / 24, 0.5);
		updateViewHand(minHand, ((time / 60) % 60) * 2 * Math.PI / 60, 0.7);
		updateViewHand(secHand, (time % 60) * 2 * Math.PI / 60, 0.9);
	}

	private void updateViewHand(Line hand, double radians, double length) {
		hand.setVisible(radians >= 0.0);
		hand.setLayoutX(clockFace.getCenterX());
		hand.setLayoutY(clockFace.getCenterY());
		radians = radians - Math.PI / 2;
		hand.setEndX(clockFace.getRadius() * length * Math.cos(radians));
		hand.setEndY(clockFace.getRadius() * length * Math.sin(radians));
	}
}
