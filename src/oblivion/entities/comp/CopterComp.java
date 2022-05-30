package oblivion.entities.comp;

import arc.math.*;
import mindustry.gen.*;
import mindustry.entities.comp.*;

abstract class CopterComp extends UnitEntity {
	public float slowdown, invSlowdonw;

	@Override
	public void isAdded() {
		slowdown = 1f;
		invSlowdonw = 0f;
		super.isAdded();
	}

	@Override
	public void update() {
		if (dead) {
			slowdown = Mathf.approachDelta(invSlowdonw, 1f, type.rotorDeathSlowness);	
			invSlowdonw = Mathf.approachDelta(invSlowdonw, 0f, type.rotorDeathSlowness);
		} else {
			slowdown = Mathf.approachDelta(invSlowdonw, 0f, type.rotorDeathSlowness);	
			invSlowdonw = Mathf.approachDelta(invSlowdonw, 1f, type.rotorDeathSlowness);			
		}
		super.update();
	}
}