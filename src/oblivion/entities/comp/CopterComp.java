package oblivion.entities.comp;

import arc.math.*;
import mindustry.gen.*;
import oblivion.type.*;
import oblivion.entities.draw.*;

public class CopterComp extends UnitEntity {
	public float slowdown;

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

	@Override
	public void draw() {
		super.draw();
		type.topDrawers.each(rotor -> {
			if (rotor instanceof RotorDrawer) {
				rotor.draw(this);
			}
		});
	}
}