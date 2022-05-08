package oblivion.content;

import mindustry.ctype.*;
import mindustry.world.*;
import mindustry.world.blocks.environment.*;

public class OblivionEnvironment implements ContentList {
	public static Block
		baletise, powderite, tobolite, driedpowder,
		baletiseWall, powderiteWall, toboliteWall, driedpowderWall,

		argeletine, goletenira, malenatite, paletolime, mudone,
		argeletineWall, goleteniraWall, malenatiteWall, paletolimeWall, mudoneWall;

	@Override
	public void load() {

		// lonela
		baletise = new Floor("baletise") {{
			attributes.set(Attribute.oil, -1f);
			attributes.set(Attribute.water, 0f);
		}};
		powderite = new Floor("powderite") {{
			attributes.set(Attribute.oil, -1f);
			attributes.set(Attribute.water, -1f);
		}};
		tobolite = new Floor("tobolite") {{
			attributes.set(Attribute.oil, -1f);
			attributes.set(Attribute.water, -1f);
		}};
		driedpowder = new Floor("driedpowder") {{
			itemDrop = Items.sand;
			attributes.set(Attribute.oil, -1f);
			attributes.set(Attribute.water, -1f);
		}};
		baletiseWall = new StaticWall("baletise-wall");
		powderiteWall = new StaticWall("powderite-wall");
		toboliteWall = new StaticWall("tobolite-wall");
		driedpowderWall = new StaticWall("driedpowder-wall");

		// lamoni 1
		mudone = new Floor("mudone") {{
			attributes.set(Attribute.water, 0.5f);
		}};

		argeletine = new Floor("argeletine") {{
			attributes.set(Attribute.water, 0.15f);
		}};

		goletenira = new Floor("goletenira") {{
			attributes.set(Attribute.water, -0.1f);
		}};

		malenatite = new Floor("malenatite") {{
			attributes.set(Attribute.water, -0.15f);
		}};

		paletolime = new Floor("paletolime") {{
			itemDrop = Items.sand;
			attributes.set(Attribute.water, -0.25f);
			attributes.set(Attribute.oil, 0.75f);
		}};

		mudoneWall = new StaticWall("mudone-wall");
		argeletineWall = new StaticWall("argeletine-wall");
		goleteniraWall = new StaticWall("goletenira-wall");
		malenatiteWall = new StaticWall("malenatite-wall");
		paletolimeWall = new StaticWall("paletolime-wall");
	}
}