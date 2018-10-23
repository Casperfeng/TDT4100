package objectstructures;

import java.lang.reflect.Method;

import javafx.animation.AnimationTimer;

public class FxmlTimer {

	public static long MICROSECOND_SCALE = 1_000, MILLISECOND_SCALE = MICROSECOND_SCALE * 1_000, SECOND_SCALE = MILLISECOND_SCALE * 1000;

	private Object controller;
	private String method;
	private long scale = MILLISECOND_SCALE;

	public FxmlTimer() {
	}

	public FxmlTimer(Object controller, String method) {
		setController(controller);
		setMethod(method);
	}

	public void setController(Object controller) {
		this.controller = controller;
	}

	public void setMethod(String method) {
		if (method.startsWith("#")) {
			method = method.substring(1);
		}
		this.method = method;
	}

	public void setScale(long scale) {
		this.scale = scale;
	}

	private long start = -1, delay = -1, repeat = -1;

	private AnimationTimer timer = new AnimationTimer() {
		@Override
		public void handle(long now) {
			if (isRunning()) {
				while (true) {
					// duration in milliseconds
					long duration = (now - start);
					if (duration < delay) {
						break;
					}
					callback(method, duration / scale);
					// restart timer and set new delay
					FxmlTimer.this.start += delay;
					FxmlTimer.this.delay = FxmlTimer.this.repeat;
				}
			}
		}
	};

	public boolean isRunning() {
		return timer != null && start >= 0 && delay >= 0;
	}

	public void start(int delay, int repeat) {
		this.delay = delay * scale;
		this.repeat = repeat * scale;
		this.start = System.nanoTime();
		this.timer.start();
	}

	public void start(int delay) {
		start(delay, -1);
	}

	public void stop() {
		this.start = this.delay = this.repeat = -1;
		if (this.timer != null) {
			this.timer.stop();
		}
	}

	private static Class<?>[] parameterTypes1 = new Class<?>[]{Long.TYPE}, parameterTypes2 = new Class<?>[]{};

	private void callback(String method, long duration) {
		if (controller != null && method != null) {
			Method m = null;
			try {
				m = controller.getClass().getMethod(method, parameterTypes1);
			} catch (Exception e) {
			}
			if (m != null) {
				try {
					m.invoke(controller, new Object[]{duration});
				} catch (Exception e) {
				}
				return;
			}
			try {
				m = controller.getClass().getMethod(method, parameterTypes2);
			} catch (Exception e) {
			}
			if (m != null) {
				try {
					m.invoke(controller, new Object[]{});
				} catch (Exception e) {
				}
				return;
			}
		}
	}
}
