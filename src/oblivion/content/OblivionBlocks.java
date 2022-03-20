package oblivion.content;

import arc.graphics.*;
import mindustry.type.*;
import mindustry.ctype.*;
import mindustry.world.*;
import mindustry.content.*;
import mindustry.world.meta.*;
import mindustry.world.draw.*;
import mindustry.entities.bullet.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.defense.turrets.*;
import oblivion.graphics.*;
import oblivion.world.blocks.defense.*;

import static mindustry.type.ItemStack.*;

public class OblivionBlocks implements ContentList {
	public static Block 
		start,
		mesoForge, carbonicInfuser, cloroSynthetizer, calonicKiln, moloniteSmelter,

		uno, rain, granite,

		merci;

	@Override
	public void load() {
		start = new Block("start"){{
			buildVisibility = BuildVisibility.debugOnly;
			inEditor = false;
			alwaysUnlocked = true;
		}};

		mesoForge = new GenericCrafter("meso-forge") {{
			requirements(Category.crafting, with(
				Items.silicon, 40,
				Items.graphite, 60,
				Items.copper, 50,
				Items.lead, 80
			));
			size = 3;
			health = 200;
			craftTime = 30f;
			drawer = new DrawSmelter(Color.valueOf("FFC2CA"));
			consumes.items(with(
				Items.copper, 2,
				Items.lead, 2
			));
			consumes.power(1f);
			outputItem = new ItemStack(OblivionResources.mesulfate, 1);
		}};
		carbonicInfuser = new GenericCrafter("carbonic-infuser") {{
			requirements(Category.crafting, with(
				OblivionResources.mesulfate, 80,
				Items.silicon, 120,
				Items.plastanium, 100,
				Items.graphite, 90,
				Items.titanium, 110
			));
			size = 4;
			health = 220;
			craftTime = 60f;
			drawer = new DrawSmelter(Color.valueOf("DEDEDE"));
			consumes.items(with(
				OblivionResources.calenmite, 3,
				Items.silicon, 2
			));
			consumes.power(1.5f);
			outputItem = new ItemStack(OblivionResources.carmanite, 2);
		}};
		cloroSynthetizer = new GenericCrafter("cloro-synthetizer") {{
			requirements(Category.crafting, with(
				Items.thorium, 70,
				Items.plastanium, 120,
				Items.silicon, 150
			));
			size = 3;
			health = 200;
			craftTime = 90f;
			drawer = new DrawCultivator() {{
				plantColor = Color.valueOf("86F284");
				plantColorLight = Color.valueOf("6EC76C");
			}};
			consumes.items(with(
				OblivionResources.mesulfate, 1,
				Items.sporePod, 1
			));
			consumes.power(0.5f);
			outputItem = new ItemStack(OblivionResources.copremite, 1);
		}};
		calonicKiln = new GenericCrafter("calonic-kiln") {{
			requirements(Category.crafting, with(
				Items.thorium, 60,
				Items.metaglass, 150,
				Items.titanium, 120,
				Items.graphite, 200
			));
			size = 3;
			health = 200;
			craftTime = 30f;
			craftEffect = OblivionFx.calciteSmelt;
			drawer = new DrawSmelter(Color.valueOf("D1D1D1"));
			consumes.items(with(
				OblivionResources.mesulfate, 1,
				Items.metaglass, 3
			));
			consumes.power(0.25f);
			outputItem = new ItemStack(OblivionResources.calenmite, 1);
		}};
		moloniteSmelter = new GenericCrafter("molonite-smelter") {{
			requirements(Category.crafting, with(
				OblivionResources.carmanite, 120,
				OblivionResources.copremite, 180,
				Items.surgeAlloy, 100,
				Items.thorium, 130,
				Items.plastanium, 150
			));
			size = 5;
			health = 300;
			craftTime = 120f;
			drawer = new DrawSmelter(Color.valueOf("B5BFFF"));
			consumes.items(with(
				OblivionResources.mesulfate, 5,
				Items.thorium, 4,
				Items.silicon, 6
			));
			consumes.power(2.5f);
			outputItem = new ItemStack(OblivionResources.mothalate, 1);
		}};

		uno = new ItemTurret("uno") {{
			requirements(Category.turret, with(
				OblivionResources.mesulfate, 10,
				Items.silicon, 15,
				Items.graphite, 20
			));
			size = 1;
			health = 160;
			reloadTime = 60f;
			range = 120f;
			rotateSpeed = 10f;
			ammo(
				OblivionResources.mesulfate, new BasicBulletType(2f, 15) {{
					lifetime = 60f;
					frontColor = Color.valueOf("E86F6F");
					backColor = Color.valueOf("AD4747");
				}},
				Items.graphite, new BasicBulletType(2f, 22) {{
					lifetime = 60f;
					frontColor = Color.valueOf("95ABD9");
					backColor = Color.valueOf("626F9B");
				}},
				Items.silicon, new MissileBulletType(2f, 7) {{
					lifetime = 60f;
					splashDamage = 10;
					splashDamageRadius = 16f;
				}}
			); 
		}};
		rain = new ItemTurret("rain") {{
			requirements(Category.turret, with(
				OblivionResources.mesulfate, 75,
				Items.silicon, 125,
				Items.titanium, 50,
				Items.graphite, 100
			));
			size = 2;
			health = 640;
			reloadTime = 10f;
			spread = 4f;
			shots = 2;
			alternate = true;
			range = 184f;
			rotateSpeed = 7.5f;
			ammo(
				OblivionResources.mesulfate, new BasicBulletType(2.5f, 20) {{
					lifetime = 73.6f;
					shootSound = Sounds.artilery;
					frontColor = Color.valueOf("E86F6F");
					backColor = Color.valueOf("AD4747");
				}},
				Items.graphite, new BasicBulletType(2f, 27) {{
					lifetime = 73.6f;
					shootSound = Sounds.artilery;
					frontColor = Color.valueOf("95ABD9");
					backColor = Color.valueOf("626F9B");
				}},
				Items.silicon, new MissileBulletType(2f, 17) {{
					lifetime = 73.6f;
					splashDamage = 10;
					splashDamageRadius = 16f;
					shootSound = Sounds.artilery;
				}}
			);
		}};
		granite = new ItemTurret("granite") {{
			requirements(Category.turret, with(
				OblivionResources.mesulfate, 160,
				Items.silicon, 120,
				Items.thorium, 80,
				Items.titanium, 135,
				Items.graphite, 140
			));
			size = 3;
			health = 1440;
			reloadTime = 90f;
			range = 240f;
			rotateSpeed = 5f;
			ammo(
				OblivionResources.mesulfate, new ArtilleryBulletType(3f, 35) {{
					lifetime = 80f;
					width = 10f;
					height = 10f;
					splashDamage = 20;
					splashDamageRadius = 20f;
					collides = true;
					shootSound = Sounds.shootBig;
					frontColor = Color.valueOf("E86F6F");
					backColor = Color.valueOf("AD4747");
				}},
				Items.graphite, new ArtilleryBulletType(3f, 50) {{
					lifetime = 80f;
					width = 10f;
					height = 10f;
					splashDamage = 30;
					splashDamageRadius = 20f;
					collides = true;
					shootSound = Sounds.shootBig;
					frontColor = Color.valueOf("95ABD9");
					backColor = Color.valueOf("626F9B");
				}},
				Items.silicon, new ArtilleryBulletType(3f, 20) {{
					lifetime = 80f;
					width = 10f;
					height = 10f;
					splashDamage = 15;
					splashDamageRadius = 20f;
					collides = true;
					shootSound = Sounds.shootBig;
					homingPower = 0.08f;
				}}
			);
		}};

		merci = new BulletSpreader("merci") {{
			requirements(Category.turret, with(
				OblivionResources.mesulfate, 30,
				Items.silicon, 45
			));
			size = 2;
			health = 320;
			reloadTime = 60f;
			shots = 3;
			spread = 10f;
			offset = 4f;
			bullet = new BasicBulletType(2f, 16) {{
				lifetime = 60f;
				frontColor = Color.valueOf("E86F6F");
				backColor = Color.valueOf("AD4747");
			}};
			consumes.items(with(
				OblivionResources.mesulfate, 3
			));
			consumes.power(0.2f);
		}};
	}
}