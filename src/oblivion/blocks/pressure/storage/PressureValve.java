package oblivion.blocks.pressure.storage;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.scene.ui.layout.*;
import mindustry.gen.*;
import oblivion.blocks.pressure.*;
// on of pressure switch
public class PressureValve extends PressureBlock {
	public TextureRegion bottomRegion;

	public PressureValve(String name) {
		super(name);
		acceptsPressure = true; 
	}

	@Override
	public void load() {
		super.load();
		bottomRegion = Core.atlas.find(name + "-bottom");
	}

	public class PressureValveBuild extends PressureBuild {
		public boolean open = false;
		@Override
		public void buildConfiguration(Table table) {
			table.button(Icon.settings, () -> {
				open = !open;
			});
		}

		@Override
		public boolean outputsPressure(float pressure, Building src) {return open;}

		@Override
		public void draw() {
			Draw.rect(bottomRegion, x, y, 0f);
			drawPressure(Color.valueOf("CAACFF"));
			Draw.rect(region, x, y, 0f);
		}
	}
}