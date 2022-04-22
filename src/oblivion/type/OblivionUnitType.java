package oblivion.type;

import arc.func.*;
import mindustry.gen.*;
import mindustry.type.*;

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
	
	@Override
	public void draw(Unit unit) {
		super.draw(unit);
		topDrawer.get(unit);
	}
}