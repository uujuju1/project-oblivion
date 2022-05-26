package oblivion.content;

import arc.graphics.*;
import mindustry.type.*;

public class OblivionResources {
	// lonela
	public static Item mesulfate, calenmite, copremite, mothalate, carmanite,
	// lamoni
		niobium, hafnium, sodium;

	public void load() {
		// lonela
		mesulfate = new Item("mesulfate", Color.valueOf("E86F6F")) {{cost = 2;}};
		calenmite = new Item("calenmite", Color.valueOf("EBEEF2")) {{cost = 3;}};
		copremite = new Item("copremite", Color.valueOf("31942E")) {{cost = 3;}};
		carmanite = new Item("carmanite", Color.valueOf("515151")) {{cost = 5;}};
		mothalate = new Item("mothalate", Color.valueOf("313B7A")) {{cost = 7;}};

		// lamoni
		niobium = new Item("niobium", Color.valueOf("5D687F")) {{cost = 0.5f; hardness = 0;}};
		hafnium = new Item("hafnium", Color.valueOf("404040")) {{cost = 0.3f;}};
		sodium = new Item("sodium", Color.valueOf("9FCF9C")) {{}};
	}
}