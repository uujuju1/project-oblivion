package oblivion.entities.comp;

import arc.math.*;
import mindustry.gen.*;
import oblivion.type.*;

public class CopterComp extends UnitEntity {
	public float slowdown;

	public void initialize() {
		slowdown = 1f;
		create();
	}

	@Override
	public void update() {
		OblivionUnitType type = ((OblivionUnitType) this.type);
		if (dead) {
			slowdown = Mathf.approachDelta(slowdown, 1f, type.rotorDeathSlowness);	
		} else {
			slowdown = Mathf.approachDelta(slowdown, 0f, type.rotorDeathSlowness);	
		}
		super.update();
	}
}