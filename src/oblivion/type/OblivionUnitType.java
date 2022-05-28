package oblivion.type;

import arc.func.*;
import arc.math.*;
import arc.util.*;
import arc.struct.*;
import arc.math.geom.*;
import arc.graphics.g2d.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.entities.abilities.*;
import mindustry.graphics.*;
import oblivion.entities.draw.*;

import mindustry.Vars;

// lazy to remove class and rework depending units
public class OblivionUnitType extends UnitType {
	public Cons<Unit> engineDrawer = unit -> {};
	public Seq<RotorDrawer> topDrawers = new Seq<>();

	public OblivionUnitType(String name) {
		super(name);
	}

	@Override
	public void load() {
		super.load();
		topDrawers.each(d -> d.load(this));
	}

	@Override
	public void draw(Unit unit) {
		super.draw(unit);
		topDrawers.each(d -> d.draw(unit));
	}

	@Override
	public void init() {
		super.init();
		topDrawers.each(d -> d.init());
	}

	@Override
	public void drawEngines(Unit unit) {
		engineDrawer.get(unit);
	}
}