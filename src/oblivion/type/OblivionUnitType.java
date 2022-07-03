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
import oblivion.entities.comp.*;

import mindustry.Vars;

// lazy to remove class and rework depending units
public class OblivionUnitType extends UnitType {
	public Cons<Unit> engineDrawer = unit -> {};
	public Seq<RotorDrawer> topDrawers = new Seq<>();
	public boolean decays = false;
	public float decayScale = 0.001f;
	public float rotorDeathSlowness = 0.008f;

	public OblivionUnitType(String name) {
		super(name);
	}

	@Override
	public void load() {
		super.load();
		topDrawers.each(d -> d.load(this));
	}

	@Override
	public void drawEngines(Unit unit) {
		engineDrawer.get(unit);
	}

	@Override
	public void update(Unit unit) {
		unit.health -= decays ? unit.maxHealth * decayPercent : 0f;
	}
}