package oblivion.type.draw;

import arc.func.*;
import arc.graphics.g2d.*;
import mindustry.gen.*;

/** custom drawer for drawing engines in {@link OblivionUnitType}*/
public class EngineDrawer {
	public Cons<Unit> drawer = unit -> {};

	public EngineDrawer(Cons<Unit> renderer) {
		this.drawer = renderer;
	}

	public void render(Unit unit) {
		Draw.reset();
		drawer.get(unit);
		Draw.reset();
	}
}