package oblivion.blocks.pressure;

import arc.graphics.Color;
import arc.util.Nullable;
import mindustry.gen.Building;

public interface PressureBuilding {
	default float pressureEfficiency() {return 1f;}

	default PressureModule pressureModule() {return null;}

	default void addPressure(float pressure, @Nullable Building src) {}
	default void subPressure(float pressure, @Nullable Building src) {}
	default void setPressure(float pressure, @Nullable Building src) {}

	default boolean acceptsPressure(float pressure, Building src) {return true;}
	default boolean outputsPressure(float pressure, Building src) {return true;}

	default void overPressure() {}
	default void drawPressure(Color pressureColor) {}
}