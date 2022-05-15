package oblivion.type;

import arc.func.*;
import arc.math.*;
import arc.util.*;
import arc.math.geom.*;
import arc.graphics.g2d.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.entities.abilities.*;
import mindustry.graphics.*;

import mindustry.Vars;

// lazy to remove class and rework depending units
public class OblivionUnitType extends UnitType {
	public Cons<Unit> engineDrawer = unit -> {}, topDrawer = unit -> {};

	public OblivionUnitType(String name) {
		super(name);
	}

	@Override
	public void drawEngines(Unit unit) {
		engineDrawer.get(unit);
	}
}