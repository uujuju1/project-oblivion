package oblivion.blocks.production;

import arc.*;
import mindustry.ui.*;
import mindustry.world.blocks.production.*;
import oblivion.blocks.util.*;

public class ExpandableCrafter extends GenericCrafter {
	public ExpandableCrafter(String name) {
		super(name);
		baseEfficiency = 0f;
	}

	@Override
	public void setbars() {
		addBar("efficiency", (ExpandableCrafterBuild entity) ->
		new Bar(() ->
		Core.bundle.format("bar.efficiency", (int)(entity.efficiencyScale() * 100 * displayEfficiencyScale)),
		() -> Pal.lightOrange,
		entity::efficiencyScale));
	}

	public class ExpandableCrafterBuild extends GenericCrafterBuild {
		public float effAmount;

		@Override
		public float efficiencyScale() {
			return 0.001f > effAmount ? 0f : effAmount;
		}

		@Override
		public void updateTile() {
			effAmount = 0f;
			for (int i = 0; i < this.proximity.size; i++) {
				if (this.proximity.get(i) instanceof EfficiencyBlockBuild) {
					effAmount += ((EfficiencyBlock) this.proximity.get(i)).updateEfficiency;
				}
			}
			super.updateTile();
		}
	}
}