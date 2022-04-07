package oblivion.type;

import mindustry.gen.*;
import mindustry.type.*;
import oblivion.type.draw.*;

// engineSize and engineOffset is now useless {:
public class OblivionUnitType extends UnitType {
	public EngineDrawer engineDrawer = new EngineDrawer(unit -> {});

	public OblivionUnitType(String name) {
		super(name);
	}

	@Override
	public void drawEngine(Unit unit) {
		engineDrawer.render(unit);
	}
}