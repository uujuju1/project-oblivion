package oblivion.world.heat;

import arc.util.*;

public interface HeatComp {

	default HeatModule heatModule() {
		return null;
	}

	default boolean recievesHeat(float heat, Building source) {
		return true;
	}
	default boolean outputsHeat(float heat, Building source) {
		return true;
	}

	default void addHeat(float heat, @Nullable Building src) {
		heatModule().heat += heat;
	}
	default void setHeat(float heat, @Nullable Building src) {
		heatModule().heat = heat;
	}
	default void subHeat(float heat, @Nullable Building src) {
		heatModule().heat -= heat;
	}

	default void drawHeat() {}
	default void overheat() {}
	default float heatEfficiency() {return 1f;}
}