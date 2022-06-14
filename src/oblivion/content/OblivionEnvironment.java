package oblivion.content;

import mindustry.world.*;
import mindustry.content.*;
import mindustry.world.meta.*;
import mindustry.world.blocks.environment.*;

public class OblivionEnvironment {
	public static Block
		baletise, powderite, tobolite, driedpowder,
		baletiseWall, powderiteWall, toboliteWall, driedpowderWall,

		argeletine, goletenira, malenatite, paletolime, mudone,
		argeletineWall, goleteniraWall, malenatiteWall, paletolimeWall, mudoneWall,
		oreNiobium,
		boronite, tarrobonite, carmebonite,
		boroniteWall, tarroboniteWall, carmeboniteWall;

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
		baletiseWall = new StaticWall("baletise-wall") {{
			mapColor = Color.valueOf("1C1C1C");
		}};
		powderiteWall = new StaticWall("powderite-wall") {{
			mapColor = Color.valueOf("303030");
		}};
		toboliteWall = new StaticWall("tobolite-wall") {{
			mapColor = Color.valueOf("5C5C5C");
		}};
		driedpowderWall = new StaticWall("driedpowder-wall") {{
			mapColor = Color.valueOf("7D7D7D");
		}};

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

		mudoneWall = new StaticWall("mudone-wall") {{
			mapColor = Color.valueOf("5C4A41");
		}};
		argeletineWall = new StaticWall("argeletine-wall") {{
			mapColor = Color.valueOf("876F60");
		}};
		goleteniraWall = new StaticWall("goletenira-wall") {{
			mapColor = Color.valueOf("AD9588");
		}};
		malenatiteWall = new StaticWall("malenatite-wall") {{
			mapColor = Color.valueOf("C4ADA0");
		}};
		paletolimeWall = new StaticWall("paletolime-wall") {{
			mapColor = Color.valueOf("D9C4B8");
		}};

		oreNiobium = new OreBlock(OblivionResources.niobium);
		// lamoni 2

		boronite = new Floor("boronite") {{
			attributes.set(Attribute.oil, 1.25f);
		}};
		tarrobonite = new Floor("tarrobonite") {{
			attributes.set(Attribute.oil, 2f);
		}};
		carmebonite = new Floor("carmebonite") {{
			attributes.set(Attribute.oil, 1.75f);
		}};

		boroniteWall = new StaticWall("boronite-wall") {{
			mapColor = Color.valueOf("364D37");
		}};
		tarroboniteWall = new StaticWall("tarrobonite-wall") {{
			mapColor = Color.valueOf("4C6B4D");
		}};
		carmeboniteWall = new StaticWall("carmebonite-wall") {{
			mapColor = Color.valueOf("7F8F77");
		}};
	}
}