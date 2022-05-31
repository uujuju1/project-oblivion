package oblivion.entities.comp;

import arc.math.*;
import mindustry.gen.*;
import mindustry.annotations.Annotations.*;
import oblivion.type.*;

abstract class CopterComp implements Unitc {
	public float slowdown;

	@Import UnitType type;
	@Import float health, rotation;
	@Import boolean dead;
	@Import int id;

	@Override
	public void create() {
		slowdown = 1f;
		super.isAdded();
	}

	@Override
	public void update() {
		OblivionUnitType type = ((OblivionUnitType) this.type);
		if (dead) {
			slowdown = Mathf.approachDelta(invSlowdonw, 1f, type.rotorDeathSlowness);	
		} else {
			slowdown = Mathf.approachDelta(invSlowdonw, 0f, type.rotorDeathSlowness);	
		}
		super.update();
	}
}