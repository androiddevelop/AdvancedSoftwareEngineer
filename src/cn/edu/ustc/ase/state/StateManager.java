package cn.edu.ustc.ase.state;

/**
 * 状态管理器
 * 
 * @author Yuedong Li
 * 
 */
public class StateManager {
	private static StateManager stateManager;
	private static PaintState state = PaintState.NONE;

	private StateManager() {
	}

	public static StateManager getInstance() {
		if (stateManager == null)
			stateManager = new StateManager();
		return stateManager;
	}

	public PaintState getState() {
		return state;
	}

	public void setState(PaintState state) {
		StateManager.state = state;
	}
}