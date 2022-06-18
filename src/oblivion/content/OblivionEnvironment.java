package oblivion.content;

import arc.graphics.*;
import mindustry.world.*;
import mindustry.content.*;
import mindustry.world.meta.*;
import mindustry.world.blocks.environment.*;

public class OblivionEnvironment {
	public static Block
		baletise, powderite, tobolite, driedpowder,
		baletiseWall, powderiteWall, toboliteWall, driedpowderWall,

		oreNiobium,
		argeletine, goletenira, malenatite, paletolime, mudone,
		argeletineWall, goleteniraWall, malenatiteWall, paletolimeWall, mudoneWall,
		boronite, tarrobonite, carmebonite,
		boroniteWall, tarroboniteWall, carmeboniteWall,
		methystane, lathanite, amonetha, porotrate,
		methystaneWall, lathaniteWall, amonethaWall, porotrateWall;

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

		oreNiobium = new OreBlock(OblivionResources.niobium);
		// lamoni 1
		mudone = new Floor("mudone") {{
			attributes.set(Attribute.water, -0.2f);
			attributes.set(Attribute.oil, 0.75f);
		}};
		argeletine = new Floor("argeletine") {{
			attributes.set(Attribute.water, -0.4f);
		}};
		goletenira = new Floor("goletenira") {{
			attributes.set(Attribute.water, -0.6f);
		}};
		malenatite = new Floor("malenatite") {{
			attributes.set(Attribute.water, -0.8f);
		}};
		paletolime = new Floor("paletolime") {{
			itemDrop = Items.sand;
			attributes.set(Attribute.water, -1f);
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
		// lamoni 2

		boronite = new Floor("boronite") {{
			attributes.set(Attribute.oil, 1.5f);
		}};
		tarrobonite = new Floor("tarrobonite") {{
			attributes.set(Attribute.oil, 2f);
		}};
		carmebonite = new Floor("carmebonite") {{
			attributes.set(Attribute.oil, 1f);
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
		// lamoni3

		methystane = new Floor("methystane") {{
			attributes.set(Attribute.water, 0.25f);
		}};
		lathanite = new Floor("lathanite") {{
			attributes.set(Attribute.water, 0.5f);
		}};
		amonetha = new Floor("amonetha") {{
			attributes.set(Attribute.water, 0.75f);
		}};
		porotrate = new Floor("porotrate") {{
			attributes.set(Attribute.water, 1f);
		}};

		methystaneWall = new StaticWall("methystane-wall") {{
			mapColor = Color.valueOf("26202E");
		}};
		lathaniteWall = new StaticWall("lathanite-wall") {{
			mapColor = Color.valueOf("3D3647");
		}};
		amonethaWall = new StaticWall("amonetha-wall") {{
			mapColor = Color.valueOf("4E4559");
		}};
		porotrateWall = new StaticWall("porotrate-wall") {{
			mapColor = Color.valueOf("6E5E80");
		}};
	}
}