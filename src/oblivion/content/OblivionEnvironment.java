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

		oreNiobium, wallOreHafnium,
		argeletine, goletenira, malenatite, paletolime, mudone,
		argeletineWall, goleteniraWall, malenatiteWall, paletolimeWall, mudoneWall,
		boronite, tarrobonite, carmebonite,
		boroniteWall, tarroboniteWall, carmeboniteWall,
		methystane, lathanite, amonetha, porotrate,
		methystaneWall, lathaniteWall, amonethaWall, porotrateWall;

	public void load() {

		// lonela
		baletise = new Floor("baletise") {{
			attributes.set(Attribute.oil, -0.25f);
			attributes.set(Attribute.water, -0.25f);
			mapColor = Color.valueOf("212121");
		}};
		powderite = new Floor("powderite") {{
			attributes.set(Attribute.oil, -0.25f);
			attributes.set(Attribute.water, -0.25f);
			mapColor = Color.valueOf("3B3B3B");
		}};
		tobolite = new Floor("tobolite") {{
			attributes.set(Attribute.oil, -0.25f);
			attributes.set(Attribute.water, -0.25f);
			mapColor = Color.valueOf("707070");
		}};
		driedpowder = new Floor("driedpowder") {{
			itemDrop = Items.sand;
			attributes.set(Attribute.oil, -0.25f);
			attributes.set(Attribute.water, -0.25f);
			mapColor = Color.valueOf("999999");
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
		oreNiobium = new OreBlock(OblivionResources.niobium);
		wallOreHafnium = new OreBlock(OblivionResources.hafnium) {{wallOre = true;}};
		mudone = new Floor("mudone") {{
			attributes.set(Attribute.oil, 0.025f);
			mapColor = Color.valueOf("7A6357");
		}};
		argeletine = new Floor("argeletine") {{
			attributes.set(Attribute.water, -0.0375f);
			mapColor = Color.valueOf("B3927F");
		}};
		goletenira = new Floor("goletenira") {{
			attributes.set(Attribute.water, -0.0625f);
			mapColor = Color.valueOf("CBAF9F");
		}};
		malenatite = new Floor("malenatite") {{
			attributes.set(Attribute.water, -0.1f);
			mapColor = Color.valueOf("DEC4B5");
		}};
		paletolime = new Floor("paletolime") {{
			itemDrop = Items.sand;
			attributes.set(Attribute.water, -0.125f);
			mapColor = Color.valueOf("E9DAD2");
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
			attributes.set(Attribute.oil, 0.0625f);
			mapColor = Color.valueOf("5D835E");
		}};
		tarrobonite = new Floor("tarrobonite") {{
			attributes.set(Attribute.oil, 0.0875f);
			mapColor = Color.valueOf("415C42");
		}};
		carmebonite = new Floor("carmebonite") {{
			attributes.set(Attribute.oil, 0.1625f);
			mapColor = Color.valueOf("8D9E84");
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
			mapColor = Color.valueOf("2E2738");
		}};
		lathanite = new Floor("lathanite") {{
			attributes.set(Attribute.water, 0.1875f);
			mapColor = Color.valueOf("463E52");
		}};
		amonetha = new Floor("amonetha") {{
			attributes.set(Attribute.water, 0.0875f);
			mapColor = Color.valueOf("5D536B");
		}};
		porotrate = new Floor("porotrate") {{
			attributes.set(Attribute.water, 0.0375f);
			mapColor = Color.valueOf("7D6B91");
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