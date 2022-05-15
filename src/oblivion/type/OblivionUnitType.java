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

// engineSize and engineOffset is now useless {:
public class OblivionUnitType extends UnitType {
	public Cons<Unit> engineDrawer = unit -> {}, topDrawer = unit -> {};

	public OblivionUnitType(String name) {
		super(name);
	}

	@Override
	public void drawEngine(Unit unit) {
		engineDrawer.get(unit);
	}
}