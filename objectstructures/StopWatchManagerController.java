package objectstructures;

import javafx.fxml.FXML;

public class StopWatchManagerController {

	StopWatchManager stopWatchManager;
	FxmlTimer fxmlTimer;
	
	@FXML
	void initialize() {
		stopWatchManager = new StopWatchManager();
		stopWatch1Controller.setStopWatch(stopWatchManager.newStopWatch("stopWatch1"));
		stopWatch2Controller.setStopWatch(stopWatchManager.newStopWatch("stopWatch2"));
		stopWatch3Controller.setStopWatch(stopWatchManager.newStopWatch("stopWatch3"));
		fxmlTimer = new FxmlTimer(this, "#handleRealTick");
		fxmlTimer.start(1000, 1000);
	}

	@FXML StopWatchController stopWatch1Controller, stopWatch2Controller, stopWatch3Controller;

	private void updateView() {
		stopWatch1Controller.updateView();
		stopWatch2Controller.updateView();
		stopWatch3Controller.updateView();
	}

	public void handleRealTick(long duration) {
		stopWatchManager.tick((int)(duration / 1000));
		updateView();
	}
	@FXML void handleStartAction() {
		for (StopWatch stopWatch : stopWatchManager.getAllWatches()) {
			stopWatch.start();
		}
		updateView();
	}
	@FXML void handleLapAction() {
		for (StopWatch stopWatch : stopWatchManager.getStartedWatches()) {
			stopWatch.lap();
		}
		updateView();
	}
	@FXML void handleStopAction() {
		for (StopWatch stopWatch : stopWatchManager.getStartedWatches()) {
			stopWatch.stop();
		}
		updateView();
	}

	@FXML void handleRemove1Action() { stopWatchManager.removeStopWatch("stopWatch1"); }
	@FXML void handleRemove2Action() { stopWatchManager.removeStopWatch("stopWatch2"); }
	@FXML void handleRemove3Action() { stopWatchManager.removeStopWatch("stopWatch3"); }
}
