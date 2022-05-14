package oblivion.content;

import arc.*;
import arc.util.*;
import arc.math.*;
import arc.struct.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.content.*;
import mindustry.graphics.*;
import mindustry.world.meta.*;
import mindustry.world.draw.*;
import mindustry.entities.part.*;
import mindustry.entities.bullet.*;
import mindustry.entities.pattern.*;
import mindustry.world.blocks.units.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.defense.turrets.*;
import oblivion.graphics.*;
// import oblivion.blocks.defense.*;
import oblivion.blocks.production.*;

import static mindustry.type.ItemStack.*;

public class OblivionBlocks {
	public static Block 
		start,
		// lonela
		mesoForge, calonicKiln, 
		uno, rain, granite,
		solfreniteFactory,

		cloroSynthetizer,
		toxic, corrosive, acidic,
		infestromeniFactory,

		carbonicInfuser,
		inductionDrill,
		alomeriTube, alomeriConveyor,
		earthquake,
		hammer, press,
		functiveFactory,

		moloniteSmelter,
		calamitySigil, fearSigil, abyssSigil,
		mandlebrotReconstructor,
		alphaReconstructor, betaReconstructor, gammaReconstructor, omegaReconstructor,

		// lamoni
		mantlePulverizer,
		imperialDrill;

	public void load() {
		start = new Block("start"){{
			buildVisibility = BuildVisibility.debugOnly;
			inEditor = false;
			alwaysUnlocked = true;
		}};

		mesoForge = new DrawableCrafter("meso-forge") {{
			requirements(Category.crafting, with(
				Items.silicon, 40,
				Items.graphite, 60,
				Items.copper, 50,
				Items.lead, 80
			));
			size = 3;
			health = 200;
			craftTime = 30f;
			draw = bu -> {
				Draw.rect(Core.atlas.find("oblivion-meso-forge"), bu.x, bu.y, 0f);
				Draw.alpha(bu.warmup);
				Draw.rect(Core.atlas.find("oblivion-meso-forge-top"), bu.x, bu.y, 0f);
				Draw.alpha(1f);
				
				Draw.color(OblivionPal.mesoDark, OblivionPal.mesoLight, bu.warmup);
				for (int i = 0; i < 4; i++) {
					float x = bu.x + Angles.trnsx((Time.time * 5f) + (i * 90f), -10f + Mathf.absin(10f, 5f), 0f);
					float y = bu.y + Angles.trnsy((Time.time * 5f) + (i * 90f), -10f + Mathf.absin(10f, 5f), 0f);
					Lines.lineAngle(x, y, Angles.angle(bu.x, bu.y, x, y), Mathf.absin(10f, 5f));
				}
				for (int i = 0; i < 4; i++) {
					float x = bu.x + Angles.trnsx((Time.time * 5f) + (i * 90f), -10f + Mathf.absin(10f, 5f), 0f);
					float y = bu.y + Angles.trnsy((Time.time * 5f) + (i * 90f), -10f + Mathf.absin(10f, 5f), 0f);
					Lines.lineAngle(x, y, Angles.angle(bu.x, bu.y, x, y) + 90, Mathf.absin(10f, 5f));
				}
				for (int i = 0; i < 4; i++) {
					float x = bu.x + Angles.trnsx((Time.time * 5f) + (i * 90f), -10f + Mathf.absin(10f, 5f), 0f);
					float y = bu.y + Angles.trnsy((Time.time * 5f) + (i * 90f), -10f + Mathf.absin(10f, 5f), 0f);
					Lines.lineAngle(x, y, Angles.angle(bu.x, bu.y, x, y) - 90, Mathf.absin(10f, 5f));
				}
				
				Fill.circle(bu.x, bu.y, (5f + Mathf.absin(5f, 1f)) * bu.warmup);
				Draw.reset();
				Fill.circle(bu.x, bu.y, (5f + Mathf.absin(5f, 1f)) /2f * bu.warmup);
			};
			consumeItems(with(
				Items.copper, 2,
				Items.lead, 2
			));
			consumePower(1f);
			outputItem = new ItemStack(OblivionResources.mesulfate, 1);
		}};
		carbonicInfuser = new DrawableCrafter("carbonic-infuser") {{
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
			draw = bu -> {
				Draw.rect(Core.atlas.find("oblivion-carbonic-infuser"), bu.x, bu.y, 0);
				Draw.alpha(bu.warmup);
				Draw.rect(Core.atlas.find("oblivion-carbonic-infuser-top"), bu.x, bu.y, 0);
				Draw.reset();
				Draw.color(Color.black, Color.valueOf("ababab"), bu.warmup);
				Fill.circle(bu.x, bu.y, (5 + Mathf.absin(5, 1)) * bu.warmup);
				for (int i = 0; i < 4; i++) {
				  float x = bu.x + Angles.trnsx((i * 90) + 45 + Time.time, (2 + Mathf.absin(5, 1)) * bu.warmup, 0);
				  float y = bu.y + Angles.trnsy((i * 90) + 45 + Time.time, (2 + Mathf.absin(5, 1)) * bu.warmup, 0);
				  Drawf.tri(x, y, 8 * bu.warmup, 8 * bu.warmup, (i * 90) + 45 + Time.time);
				}
				
				Draw.color(Color.black, Color.white, bu.warmup);
				Fill.circle(bu.x, bu.y, (5 + Mathf.absin(5, 1)) * bu.warmup / 2);
				for (int i = 0; i < 4; i++) {
				  float x = bu.x + Angles.trnsx((i * 90) + 45 + Time.time, (2 + Mathf.absin(5, 1)) * bu.warmup / 2, 0);
				  float y = bu.y + Angles.trnsy((i * 90) + 45 + Time.time, (2 + Mathf.absin(5, 1)) * bu.warmup / 2, 0);
				  Drawf.tri(x, y, 4 * bu.warmup, 4 * bu.warmup, (i * 90) + 45 + Time.time);
				}
			};
			consumeItems(with(
				OblivionResources.calenmite, 3,
				Items.silicon, 2
			));
			consumePower(1.5f);
			outputItem = new ItemStack(OblivionResources.carmanite, 2);
		}};
		cloroSynthetizer = new DrawableCrafter("cloro-synthetizer") {{
			requirements(Category.crafting, with(
				Items.thorium, 70,
				Items.plastanium, 120,
				Items.silicon, 150
			));
			size = 3;
			health = 200;
			craftTime = 90f;
			draw = bu -> {
				Draw.rect(Core.atlas.find("oblivion-cloro-synthetizer"), bu.x, bu.y, 0f);
				Draw.alpha(bu.warmup);
				Draw.rect(Core.atlas.find("oblivion-cloro-synthetizer-middle"), bu.x, bu.y, 0f);
				Draw.color(Color.valueOf("4A4B53"), Color.valueOf("989AA4"), bu.warmup);
				for (int i = 0; i < 4; i++) {
					float x = bu.x + Angles.trnsx((i * 90f), -6f, 0f);
					float y = bu.y + Angles.trnsy((i * 90f), -6f, 0f);
					Lines.lineAngle(x, y, (i * 90f) + 90f + Time.time * 2f, 2f * bu.warmup);
					Lines.lineAngle(x, y, (i * 90f) - 90f + Time.time * 2f, 2f * bu.warmup);
				}
				Draw.reset();
				Draw.rect(Core.atlas.find("oblivion-cloro-synthetizer-top"), bu.x, bu.y, 0f);
			};
			consumeItems(with(
				OblivionResources.mesulfate, 1,
				Items.sporePod, 1
			));
			consumePower(0.5f);
			outputItem = new ItemStack(OblivionResources.copremite, 1);
		}};
		calonicKiln = new DrawableCrafter("calonic-kiln") {{
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
			draw = bu -> {
				Draw.rect(Core.atlas.find("oblivion-calonic-kiln"), bu.x, bu.y, 0f);
				Draw.alpha(bu.warmup);
				Draw.rect(Core.atlas.find("oblivion-calonic-kiln-top"), bu.x, bu.y, 0f);
				Draw.alpha(1f);
				
				Draw.color(Color.valueOf("949494"), Color.valueOf("E3E3E3"), bu.warmup);
				for (int i = 0; i < 4; i++) {
					float x = bu.x + Angles.trnsx((i * 90f) + 45f, -6f, 0f);
					float y = bu.y + Angles.trnsy((i * 90f) + 45f, -6f, 0f);
					Drawf.tri(x, y, (10f + Mathf.absin(5f, 1f)), (8f + Mathf.absin(5f, 1f)) * bu.warmup, (i * 90f) + 225f);
				}
				
				Draw.reset();
				for (int i = 0; i < 4; i++) {
					float x = bu.x + Angles.trnsx((i * 90f) + 45f, -6f, 0f);
					float y = bu.y + Angles.trnsy((i * 90f) + 45f, -6f, 0f);
					Drawf.tri(x, y, (10f + Mathf.absin(5f, 1f)) / 2f, (8f + Mathf.absin(5f, 1f)) / 2f * bu.warmup, (i * 90f) + 225f);
				}
				
				Draw.color(Color.valueOf("AD4747"), Color.valueOf("F79797"), bu.warmup);
				Fill.circle(bu.x, bu.y, (5f + Mathf.absin(5f, 1f)) * bu.warmup);
				Draw.reset();
				Fill.circle(bu.x, bu.y, (5f + Mathf.absin(5f, 1f)) /2f * bu.warmup);
			};
			consumeItems(with(
				OblivionResources.mesulfate, 1,
				Items.metaglass, 3
			));
			consumePower(0.25f);
			outputItem = new ItemStack(OblivionResources.calenmite, 1);
		}};
		moloniteSmelter = new DrawableCrafter("molonite-smelter") {{
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
			draw = bu -> {
				Draw.rect(Core.atlas.find("oblivion-molonite-smelter"), bu.x, bu.y, 0);
				Draw.alpha(bu.warmup);
				Draw.rect(Core.atlas.find("oblivion-molonite-smelter-top"), bu.x, bu.y, 0);
				Draw.reset();
				Draw.color(Color.valueOf("1F254D"), Color.valueOf("424FA3"), bu.warmup);
				Fill.circle(bu.x, bu.y, (5 + Mathf.absin(5, 1)) * bu.warmup);
				for (int i = 0; i < 4; i++) {
				  float x = bu.x + Angles.trnsx((i * 90) + 45 + Time.time, (2 + Mathf.absin(5, 1)) * bu.warmup, 0);
				  float y = bu.y + Angles.trnsy((i * 90) + 45 + Time.time, (2 + Mathf.absin(5, 1)) * bu.warmup, 0);
				  Drawf.tri(x, y, 8 * bu.warmup, 8 * bu.warmup, (i * 90) + 45 + Time.time);
				}
				
				Draw.color(Color.black, Color.white, bu.warmup);
				Fill.circle(bu.x, bu.y, (5 + Mathf.absin(5, 1)) * bu.warmup / 2);
				for (int i = 0; i < 4; i++) {
				  float x = bu.x + Angles.trnsx((i * 90) + 45 + Time.time, (2 + Mathf.absin(5, 1)) * bu.warmup / 2, 0);
				  float y = bu.y + Angles.trnsy((i * 90) + 45 + Time.time, (2 + Mathf.absin(5, 1)) * bu.warmup / 2, 0);
				  Drawf.tri(x, y, 4 * bu.warmup, 4 * bu.warmup, (i * 90) + 45 + Time.time);
				}
			};
			consumeItems(with(
				OblivionResources.mesulfate, 5,
				Items.thorium, 4,
				Items.silicon, 6
			));
			consumePower(2.5f);
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
			reload = 60f;
			range = 120f;
			recoil = 0.2f;
			rotateSpeed = 10f;
			drawer = new DrawTurret() {{
				parts.addAll(
					new RegionPart("-shoot") {{
						moveY = -1f;
						progress = PartProgress.reload;
					}}
				);
			}};
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
		/*
		rain = new ItemTurret("rain") {{
			requirements(Category.turret, with(
				OblivionResources.mesulfate, 75,
				Items.silicon, 125,
				Items.titanium, 50,
				Items.graphite, 100
			));
			size = 2;
			health = 640;
			reload = 10f;
			range = 184f;
			rotateSpeed = 7.5f;
			recoil = 0.7f;
			shoot = new ShootAlternate() {{
				spread = 7f;
			}};
			drawer = new DrawTurret() {{
				parts.addAll(
					new RegionPart("-cannon") {{
						x = 0f;
						y = 2f;
						moveY = -2f;
						progress = PartProgress.reload.curve(Interp.pow2In);
						under = true;
					}}
				);
				parts.addAll(
					new RegionPart("-lock") {{
						x = 5.5f;
						y = -2f;
						moveRot = 45f;
						progress = PartProgress.warmup;
						mirror = true;
					}}
				);
			}};
			ammo(
				OblivionResources.mesulfate, new BasicBulletType(5f, 20) {{
					lifetime = 36.8f;
					shootSound = Sounds.artillery;
					frontColor = Color.valueOf("E86F6F");
					backColor = Color.valueOf("AD4747");
				}},
				Items.graphite, new BasicBulletType(2f, 27) {{
					lifetime = 73.6f;
					shootSound = Sounds.artillery;
				}},
				Items.silicon, new MissileBulletType(2f, 17) {{
					lifetime = 73.6f;
					splashDamage = 10;
					splashDamageRadius = 16f;
					shootSound = Sounds.artillery;
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
			reload = 90f;
			range = 240f;
			inaccuracy = 8f;
			rotateSpeed = 5f;
			recoil = 1f;
			shoot = new ShootPattern() {{
				shots = 4;
			}};
			drawer = new DrawTurret() {{
				parts.addAll(
					new RegionPart("-wing") {{
						x = 6.6f;
						y = -4f;
						moveRot = -15f;
						progress = PartProgress.warmup;
						mirror = true;
					}},
					new RegionPart("-shoot") {{
						moveY = -3f;
						progress = PartProgress.reload;
					}}
				);
			}};
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
		
		toxic = new ItemTurret("toxic") {{
			requirements(Category.turret, with(
				Items.graphite, 20,
				OblivionResources.copremite, 20
			));
			size = 1;
			health = 180;
			reload = 45f;
			range = 13f * 8f;
			rotateSpeed = 7f;
			drawer = new DrawTurret("poisobase-") {{
				parts.addAll(
					new RegionPart("-base") {{
						moveY = -0.2f;
						progress = PartProgress.reload;
					}},
					new RegionPart("-shoot") {{
						moveY = -1f;
						progress = PartProgress.reload;
					}}
				);
			}};
			ammo(
				OblivionResources.copremite, new BasicBulletType(1.5f, 8) {{
					lifetime = range/speed;
					width = height = 8f;
					frontColor = Color.valueOf("74C272");
					backColor = Color.valueOf("4F824B");
					shootEffect = OblivionFx.poisonShoot;
					// status = OblivionStatuses.infested;
					// statusDuration = 60f * 4.5f;
				}}
			);
		}};
		corrosive = new ItemTurret("corrosive") {{
			requirements(Category.turret, with(
				Items.graphite, 50,
				Items.titanium, 80,
				Items.silicon, 60,
				OblivionResources.copremite, 120
			));
			size = 2;
			health = 180 * 4;
			reload = 30f;
			range = 18f * 8f;
			rotateSpeed = 6f;
			ammo(
				OblivionResources.copremite, new BasicBulletType(2f, 13) {{
					lifetime = range/speed;
					width = height = 10f;
					frontColor = Color.valueOf("74C272");
					backColor = Color.valueOf("4F824B");
					shootEffect = OblivionFx.poisonShoot;
					// status = OblivionStatuses.infested;
					// statusDuration = 60f * 6f;
				}}
			);
		}};
		acidic = new ItemTurret("acidic") {{
			requirements(Category.turret, with(
				Items.graphite, 120,
				Items.thorium, 80,
				Items.silicon, 180,
				Items.titanium, 160,
				OblivionResources.copremite, 220
			));
			size = 3;
			health = 180 * 9;
			reload = 75f;
			range = 27f * 8f;
			rotateSpeed = 4.5f;
			ammo(
				OblivionResources.copremite, new BasicBulletType(2.5f, 25) {{
					lifetime = range/speed;
					width = height = 13f;
					frontColor = Color.valueOf("74C272");
					backColor = Color.valueOf("4F824B");
					shootEffect = OblivionFx.poisonShoot;
					shootSound = Sounds.artillery;
					// status = OblivionStatuses.infested;
					// statusDuration = 60f * 12f;
				}}
			);
		}};
		
		hammer = new ItemTurret("hammer") {{
			requirements(Category.turret, with(
				OblivionResources.calenmite, 40,
				Items.silicon, 50,
				Items.titanium, 20
			));
			size = 2;
			health = 200 * 4;
			reload = 45f;
			range = 20f * 8f;
			rotateSpeed = 4.5f;
			ammo(
				OblivionResources.calenmite, new BasicBulletType(2f, 35) {{
					lifetime = range/speed;
					width = height = 13f;
					frontColor = Color.white;
					backColor = Color.valueOf("DCDCDC");
					hitEffect = OblivionFx.carmaniteHit;
					shootSound = Sounds.artillery;
				}}
			);
		}};
		press = new ItemTurret("press") {{
			requirements(Category.turret, with(
				OblivionResources.calenmite, 140,
				Items.silicon, 90,
				Items.titanium, 50
			));
			size = 3;
			health = 200 * 9;
			reload = 85f;
			range = 30f * 8f;
			shots = 3;
			inaccuracy = 3f;
			velocityInaccuracy = 0.9f;
			rotateSpeed = 4.5f;
			ammo(
				OblivionResources.calenmite, new BasicBulletType(2.5f, 35) {{
					lifetime = range/speed;
					width = height = 17f;
					frontColor = Color.white;
					backColor = Color.valueOf("DCDCDC");
					hitEffect = OblivionFx.carmaniteHit;
					shootSound = Sounds.artillery;
				}}
			);
		}};

		inductionDrill = new Drill("induction-drill") {{
			requirements(Category.production, with(
				Items.silicon, 25,
				Items.graphite, 25,
				OblivionResources.carmanite, 30
			));
			size = 3;
			health = 200;
			tier = 3;
			drillTime = 280f;
			hasPower = true;
			consumePower(1f);
			consumesLiquid(new LiquidStack(Liquids.water, 0.06f)).boost();
		}};

		alomeriConveyor = new Conveyor("alomeri-conveyor") {{
			requirements(Category.distribution, with(OblivionResources.carmanite, 1, Items.silicon, 1));
			health = 180;
			speed = 0.1f;
			displayedSpeed = 14f;
		}};
		alomeriTube = new Duct("alomeri-duct") {{
			requirements(Category.distribution, with(OblivionResources.carmanite, 10, Items.silicon, 12, Items.titanium, 6));
			health = 120;
			speed = 4.5f;
		}};

		solfreniteFactory = new UnitFactory("solfrenite-factory") {{
			requirements(Category.units, with(
				Items.silicon, 45,
				Items.graphite, 60,
				OblivionResources.mesulfate, 50
			));
			size = 3;
			health = 200;
			consumePower(1.5f);
			plans = Seq.with(
				new UnitPlan(OblivionUnits.slop, 60f * 25f, with(Items.silicon, 10, OblivionResources.mesulfate, 15))
			);
		}};
		infestromeniFactory = new UnitFactory("infestromeni-factory") {{
			requirements(Category.units, with(
				Items.silicon, 60,
				Items.plastanium, 50,
				OblivionResources.copremite, 60
			));
			size = 3;
			health = 200;
			consumePower(2f);
			plans = Seq.with(
				new UnitPlan(OblivionUnits.pioli, 60f * 30f, with(Items.silicon, 6, OblivionResources.copremite, 20))
			);
		}};
		functiveFactory = new UnitFactory("functive-factory") {{
			requirements(Category.units, with(
				Items.silicon, 60,
				Items.plastanium, 50,
				OblivionResources.copremite, 60
			));
			size = 3;
			health = 200;
			consumePower(2f);
			plans = Seq.with(
				new UnitPlan(OblivionUnits.phi, 60f * 27f, with(Items.silicon, 6, OblivionResources.carmanite, 20))
			);
		}};

		alphaReconstructor = new Reconstructor("alpha-reconstructor"){{
			requirements(Category.units, with(
				Items.copper, 200, Items.lead, 120, Items.silicon, 90
			));

			size = 3;
			consumePower(3f);
			consumeItems(with(Items.silicon, 40, Items.graphite, 40));

			constructTime = 60f * 10f;

			upgrades.addAll(
				new UnitType[]{OblivionUnits.slop, OblivionUnits.detra},
				new UnitType[]{OblivionUnits.pioli, OblivionUnits.taneki},
				new UnitType[]{OblivionUnits.phi, OblivionUnits.root}
			);
		}};

		betaReconstructor = new Reconstructor("beta-reconstructor"){{
			requirements(Category.units, with(
				Items.lead, 650, Items.silicon, 450,
				Items.titanium, 350, Items.thorium, 650
			));

			size = 5;
			consumePower(6f);
			consumeItems(with(Items.silicon, 130, Items.titanium, 80, Items.metaglass, 40));

			constructTime = 60f * 30f;

			upgrades.addAll(
				new UnitType[]{OblivionUnits.detra, OblivionUnits.tedri},
				new UnitType[]{OblivionUnits.taneki, OblivionUnits.notremite},
				new UnitType[]{OblivionUnits.root, OblivionUnits.multi}
			);
		}};

		gammaReconstructor = new Reconstructor("gamma-reconstructor"){{
			requirements(Category.units, with(
				Items.lead, 2000, Items.silicon, 1000, Items.titanium, 2000,
				Items.thorium, 750, Items.plastanium, 450, Items.phaseFabric, 600
			));

			size = 7;
			consumePower(13f);
			consumeItems(with(Items.silicon, 850, Items.titanium, 750, Items.plastanium, 650));
			consumesLiquid(Liquids.cryofluid, 1f);

			constructTime = 60f * 60f * 1.5f;
			liquidCapacity = 60f;

			upgrades.addAll(
				new UnitType[]{OblivionUnits.tedri, OblivionUnits.taleni},
				new UnitType[]{OblivionUnits.notremite, OblivionUnits.dopretile},
				new UnitType[]{OblivionUnits.multi, OblivionUnits.pow}
			);
		}};

		omegaReconstructor = new Reconstructor("omega-reconstructor"){{
			requirements(Category.units, with(
				Items.lead, 4000, Items.silicon, 3000, Items.thorium, 1000,
				Items.plastanium, 600, Items.phaseFabric, 600, Items.surgeAlloy, 800
			));

			size = 9;
			consumePower(25f);
			consumeItems(with(Items.silicon, 1000, Items.plastanium, 600, Items.surgeAlloy, 500, Items.phaseFabric, 350));
			consumesLiquid(Liquids.cryofluid, 3f);

			constructTime = 60f * 60f * 4;
			liquidCapacity = 180f;

			upgrades.addAll(
				new UnitType[]{OblivionUnits.taleni, OblivionUnits.kolete},
				new UnitType[]{OblivionUnits.dopretile, OblivionUnits.niboletra},
				new UnitType[]{OblivionUnits.pow, OblivionUnits.expo}
			);
		}};

		mandlebrotReconstructor = new Reconstructor("mandlebrot-reconstructor") {{
			requirements(Category.units, with(
				Items.lead, 8000, Items.silicon, 7000, Items.thorium, 3000,
				Items.surgeAlloy, 1200, Items.phaseFabric, 1000, OblivionResources.mothalate, 1000
			));

			size = 12;
			consumePower(30f);
			consumeItems(with(Items.silicon, 2500, Items.plastanium, 1800, Items.surgeAlloy, 1000, OblivionResources.mothalate, 450));
			consumesLiquid(Liquids.cryofluid, 9f);

			constructTime = 60f * 60f * 10;
			liquidCapacity = 360f;
			upgrades.addAll(
				new UnitType[]{UnitTypes.reign, OblivionUnits.republic},
				new UnitType[]{UnitTypes.corvus, OblivionUnits.giga},
				new UnitType[]{UnitTypes.toxopid, OblivionUnits.archaranid},
				new UnitType[]{UnitTypes.eclipse, OblivionUnits.bloodmoon}
			);
		}};

		calamitySigil = new StatusBomb("calamity-sigil") {{
			requirements(Category.units, with(
				OblivionResources.mothalate, 250,
				OblivionResources.carmanite, 450,
				Items.silicon, 600,
				Items.surgeAlloy, 300,
				Items.thorium, 350,
				Items.graphite, 800
			));
			health = 350;
			size = 5;
			craftTime = 900f;
			cooldownTime = 900f;
			craftEffect = OblivionFx.calamityCraft;
			shootEffect = OblivionFx.calamityShoot;
			status = OblivionStatuses.calamity;
			statusDuration = 600f;
			itemCapacity = 150;
			consumeItems(with(
				OblivionResources.mesulfate, 100,
				OblivionResources.mothalate, 5
			));
			consumePower(5f);
		}};
		fearSigil = new StatusBomb("fear-sigil") {{
			requirements(Category.units, with(
				OblivionResources.mothalate, 120,
				OblivionResources.carmanite, 300,
				Items.silicon, 550,
				Items.graphite, 620,
				Items.plastanium, 430
			));
			size = 5;
			craftTime = 420f;
			cooldownTime = 600f;
			craftEffect = OblivionFx.fearCraft;
			shootEffect = OblivionFx.fearShoot;
			status = OblivionStatuses.fear;
			statusDuration = 300f;
			itemCapacity = 100;
			bombCapacity = 15;
			consumeItems(with(
				OblivionResources.mothalate, 50
			));
			consumePower(3f);
		}};
		abyssSigil = new StatusBomb("abyss-sigil") {{
			requirements(Category.units, with(
				OblivionResources.mothalate, 150,
				OblivionResources.carmanite, 350,
				Items.silicon, 600,
				Items.graphite, 680,
				Items.plastanium, 350,
				Items.thorium, 230
			));
			size = 5;
			craftTime = 1200f;
			cooldownTime = 900f;
			craftEffect = OblivionFx.abyssCraft;
			shootEffect = OblivionFx.abyssShoot;
			status = OblivionStatuses.abyss;
			statusDuration = 150f;
			itemCapacity = 160;
			bombCapacity = 35;
			consumeItems(with(
				OblivionResources.mothalate, 10,
				OblivionResources.carmanite, 80
			));
			consumePower(7f);
		}};

		earthquake = new StatusBomb("earthquake") {{
			requirements(Category.defense, with(
				OblivionResources.carmanite, 30,
				Items.graphite, 60,
				Items.titanium, 25,
				Items.copper, 80
			));
			size = 2;
			craftTime = 180f;
			cooldownTime = 120f;
			craftEffect = OblivionFx.calciteSmelt;
			shootEffect = OblivionFx.carmaniteHit;
			status = StatusEffects.unmoving;
			statusDuration = 60f;
			itemCapacity = 20;
			bombCapacity = 5;
			consumeItems(with(
				OblivionResources.carmanite, 10
			));
			consumePower(2f);
		}};

		// lamoni

		imperialDrill = new Drill("imperial-drill") {{
			requirements(Category.production, with(
				OblivionResources.niobium, 120
			));
			health = 200;
			size = 3;
			drillTime = 1200f;
			tier = 1;
			// holdTime = 300f;
			// decayTime = 300f;
			updateEffect = LamoniFx.imperialSmelt;
		}};

		mantlePulverizer = new DrawableCrafter("mantle-pulverizer") {{
			requirements(Category.production, with(
				OblivionResources.niobium, 200
			));
			health = 200;
			size = 3;
			craftTime = 10f;
			updateEffect = LamoniFx.imperialSmelt;
			consumePower(0.5f);
			outputItem = new ItemStack(Items.sand, 1);
		}};

		*/
	}
}
