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
import mindustry.world.blocks.*;
import mindustry.entities.part.*;
import mindustry.entities.bullet.*;
import mindustry.entities.pattern.*;
import mindustry.world.blocks.units.*;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.liquid.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.defense.turrets.*;
import oblivion.audio.*;
import oblivion.graphics.*;
import oblivion.blocks.defense.*;
import oblivion.blocks.production.*;

import static mindustry.type.ItemStack.*;

public class OblivionBlocks {
	public static Block 
		start, expansion, evolution, finalization,
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
		coreVillage,

		niobiumDuct, niobiumRouter, niobiumBridge, niobiumUnloader,

		niobiumConduit, niobiumConduitRouter, niobiumConduitBridge,

		mantlePulverizer, hafniumSmelter, demineralizer,
		xenoicMixer, plastaniumDensifier, poloniumCollider,
		surgeCharger,

		vaccumPump, presaltPump,

		spread, reaction, evaporate,
		genesis, redemption, apocalypse,

		sodaicFactory, hafoniFactory,
		elevativeReconstructor, scalativeReconstructor, ascenditeReconstructor, skinialReconstructor,

		niobiumCombustor,
		lineNode,

		niobiumWall, largeNiobiumWall, hugeNiobiumWall,
		sodiumWall, largeSodiumWall, hugeSodiumWall,
		hafniumWall, largeHafniumWall, hugeHafniumWall,
		poloniumWall, largePoloniumWall, hugePoloniumWall,

		imperialDrill, mineralBoiler;

	public void load() {
		start = new Block("start"){{
			buildVisibility = BuildVisibility.debugOnly;
			inEditor = false;
			alwaysUnlocked = true;
		}};
		expansion = new Block("expansion"){{
			buildVisibility = BuildVisibility.debugOnly;
			inEditor = false;
			researchCost = with(OblivionResources.niobium, 20000, OblivionResources.hafnium, 15000);
		}};
		evolution = new Block("evolution"){{
			buildVisibility = BuildVisibility.debugOnly;
			inEditor = false;
			researchCost = with(OblivionResources.niobium, 70000, OblivionResources.hafnium, 25000, OblivionResources.sodium, 7000);
		}};
		finalization = new Block("finalization"){{
			buildVisibility = BuildVisibility.debugOnly;
			inEditor = false;
			researchCost = with(OblivionResources.niobium, 120000, OblivionResources.hafnium, 60000, OblivionResources.sodium, 12000, Items.plastanium, 8500);
		}};

		mesoForge = new DrawableCrafter("meso-forge") {{
			requirements(Category.crafting, with(
				Items.silicon, 40,
				Items.graphite, 60,
				Items.copper, 50,
				Items.lead, 80
			));
			alwaysUnlocked = true;
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
						progress = PartProgress.reload.curve(Interp.pow2In);
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
			shoot = new ShootAlternate(7f);
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
						moveRot = -45f;
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
					}}
				);
				parts.addAll(
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
			recoil = 0.2f;
			drawer = new DrawTurret() {{
				parts.addAll(
					new RegionPart("-shoot") {{
						moveY = -1f;
						progress = PartProgress.reload;
					}}
				);
			}};
			ammo(
				OblivionResources.copremite, new BasicBulletType(1.5f, 8) {{
					lifetime = 69f; /* unintentionally nice */
					width = height = 8f;
					frontColor = Color.valueOf("74C272");
					backColor = Color.valueOf("4F824B");
					shootEffect = OblivionFx.poisonShoot;
					status = OblivionStatuses.infested;
					statusDuration = 60f * 4.5f;
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
			recoil = 1.5f;
			drawer = new DrawTurret() {{
				parts.addAll(
					new RegionPart("-blade") {{
						x = 1.5f;
						y = 2.5f;
						moveX = 1f;
						moveY = 1f;
						progress = PartProgress.warmup;
						under = mirror = true;
					}}
				);
			}};
			ammo(
				OblivionResources.copremite, new BasicBulletType(2f, 13) {{
					lifetime = 72f;
					width = height = 10f;
					frontColor = Color.valueOf("74C272");
					backColor = Color.valueOf("4F824B");
					shootEffect = OblivionFx.poisonShoot;
					status = OblivionStatuses.infested;
					statusDuration = 60f * 6f;
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
			recoil = 2.25f;
			drawer = new DrawTurret() {{
				parts.addAll(
					new RegionPart("-wing") {{
						x = 7.75f;
						y = -1.75f;
						moveRot = -45f;
						under = mirror = true;
					}}
				);
			}};
			ammo(
				OblivionResources.copremite, new BasicBulletType(2.5f, 25) {{
					lifetime = 86.4f;
					width = height = 13f;
					frontColor = Color.valueOf("74C272");
					backColor = Color.valueOf("4F824B");
					shootEffect = OblivionFx.poisonShoot;
					shootSound = Sounds.artillery;
					status = OblivionStatuses.infested;
					statusDuration = 60f * 12f;
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
			recoil = 1f;
			drawer = new DrawTurret() {{
				parts.addAll(
					new RegionPart("-support") {{
						moveY = -2f;
						progress = PartProgress.reload.curve(Interp.pow2In);
					}}
				);
				parts.addAll(
					new RegionPart("-cannon") {{
						moveY = -5f;
						progress = PartProgress.reload.curve(Interp.pow2In);
					}}
				);
			}};
			ammo(
				OblivionResources.calenmite, new ArtilleryBulletType(2f, 35) {{
					lifetime = 90;
					width = height = 13f;
					frontColor = Color.white;
					backColor = Color.valueOf("DCDCDC");
					hitEffect = OblivionFx.carmaniteHit;
					shootSound = Sounds.artillery;
					collides = collidesTiles = collidesGround = collidesAir = true;
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
			inaccuracy = 3f;
			rotateSpeed = 4.5f;
			recoil = 1.5f;
			shoot = new ShootPattern() {{
				shots = 3;
			}};
			drawer = new DrawTurret() {{
				parts.addAll(
					new RegionPart("-wing") {{
						x = 6.5f;
						y = -5f;
						moveRot = 45f;
						progress = PartProgress.warmup;
						under = mirror = true;
					}}
				);
				parts.addAll(
					new RegionPart("-cannon") {{
						moveY = -3f;
						progress = PartProgress.reload.curve(Interp.pow2In);
					}}
				);
			}};
			ammo(
				OblivionResources.calenmite, new ArtilleryBulletType(2.5f, 35) {{
					lifetime = 96;
					width = height = 17f;
					frontColor = Color.white;
					backColor = Color.valueOf("DCDCDC");
					hitEffect = OblivionFx.carmaniteHit;
					shootSound = Sounds.artillery;
					collides = collidesTiles = collidesGround = collidesAir = true;
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
			consumeLiquid(Liquids.water, 0.06f).boost();
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
			health = 360;
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
			health = 1200;
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
			health = 3035;
			consumePower(13f);
			consumeItems(with(Items.silicon, 850, Items.titanium, 750, Items.plastanium, 650));
			consumeLiquid(Liquids.cryofluid, 1f);

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
			health = 5830;
			consumePower(25f);
			consumeItems(with(Items.silicon, 1000, Items.plastanium, 600, Items.surgeAlloy, 500, Items.phaseFabric, 350));
			consumeLiquid(Liquids.cryofluid, 3f);

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
			health = 7840;
			consumePower(30f);
			consumeItems(with(Items.silicon, 2500, Items.plastanium, 1800, Items.surgeAlloy, 1000, OblivionResources.mothalate, 450));
			consumeLiquid(Liquids.cryofluid, 9f);

			constructTime = 60f * 60f * 10;
			liquidCapacity = 360f;
			upgrades.addAll(
				new UnitType[]{UnitTypes.reign, OblivionUnits.republic},
				new UnitType[]{UnitTypes.corvus, OblivionUnits.giga},
				new UnitType[]{UnitTypes.toxopid, OblivionUnits.archaranid},
				new UnitType[]{UnitTypes.eclipse, OblivionUnits.bloodmoon},
				new UnitType[]{UnitTypes.omura, OblivionUnits.yetinus}
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

		coreVillage = new CoreBlock("core-village") {{
			requirements(Category.units, with(
				OblivionResources.niobium, 1200,
				OblivionResources.hafnium, 700
			));
			size = 3;
			health = 3200;
			itemCapacity = 2500;
			alwaysUnlocked = isFirstTier = true;
			unitType = OblivionUnits.citizen;
		}};

		niobiumDuct = new Duct("niobium-duct") {{
			requirements(Category.distribution, with(OblivionResources.niobium, 1));
			health = 120;
			speed = 3.5f;
			researchCost = with(OblivionResources.niobium, 30);
		}};
		niobiumRouter = new DuctRouter("niobium-router") {{
			requirements(Category.distribution, with(OblivionResources.niobium, 3));
			health = 140;
			speed = 3.5f;
			researchCost = with(OblivionResources.niobium, 40);
		}};
		niobiumBridge = new DuctBridge("niobium-bridge") {{
			requirements(Category.distribution, with(OblivionResources.niobium, 8));
			health = 140;
			speed = 3.5f;
			researchCost = with(OblivionResources.niobium, 50);
		}};
		niobiumUnloader = new DirectionalUnloader("niobium-unloader") {{
			requirements(Category.distribution, with(OblivionResources.niobium, 12));
			health = 150;
			speed = 3.5f;
			allowCoreUnload = true;
			researchCost = with(OblivionResources.niobium, 60);
		}};

		niobiumConduit = new Conduit("niobium-conduit") {{
			requirements(Category.liquid, with(OblivionResources.niobium, 2));
			health = 100;
			researchCost = with(OblivionResources.niobium, 50);
		}};
		niobiumConduitRouter = new LiquidRouter("niobium-conduit-router") {{
			requirements(Category.liquid, with(OblivionResources.niobium, 4));
			health = 120;
			researchCost = with(OblivionResources.niobium, 55);
		}};
		niobiumConduitBridge = new LiquidBridge("niobium-conduit-bridge") {{
			requirements(Category.liquid, with(OblivionResources.niobium, 8));
			health = 140;
			range = 6;
			researchCost = with(OblivionResources.niobium, 60);
		}};

		imperialDrill = new Drill("imperial-drill") {{
			requirements(Category.production, with(
				OblivionResources.niobium, 120
			));
			health = 200;
			size = 3;
			drillTime = 1200f;
			tier = 1;
			updateEffect = LamoniFx.imperialSmelt;
			researchCost = with(OblivionResources.niobium, 200);
		}};
		mineralBoiler = new BeamDrill("mineral-boiler") {{
			requirements(Category.production, with(
				OblivionResources.hafnium, 50,
				OblivionResources.niobium, 60
			));
			size = 2;
			tier = 2;
			drillTime = 160f;
			range = 7;
			consumePower(0.3f);
		}};

		spread = new ItemTurret("spread") {{
			requirements(Category.turret, with(
				OblivionResources.niobium, 60
			));
			health = 520;
			size = 2;
			reload = 90f;
			recoil = 1f;
			range = 20 * 8f;
			inaccuracy = 3f;
			drawer = new DrawTurret("reinforced-") {{
				parts.addAll(
					new RegionPart("-cannon") {{
						moveY = -1f;
						progress = PartProgress.reload.curve(Interp.pow2In);
						heatProgress = PartProgress.reload.curve(Interp.pow2In);
					}}
				);
				parts.addAll(
					new RegionPart("-heat") {{
						drawRegion = false;
						heatProgress = PartProgress.warmup;
					}}
				);
			}};
			shoot = new ShootSpread(10, 10);
			ammo(
				OblivionResources.niobium, new BasicBulletType(2f, 5) {{
					lifetime = 80f;
					width = height = 10f;
				}}
			);
			researchCost = with(OblivionResources.niobium, 300);
		}};
		reaction = new ItemTurret("reaction") {{
			requirements(Category.turret, with(
				OblivionResources.niobium, 150
			));
			size = 2;
			health = 600;
			reload = 240;
			recoil = 1.5f;
			range = 25 * 8f;
			drawer = new DrawTurret("reinforced-") {{
				parts.addAll(
					new RegionPart("-cannon") {{
						x = 1.5f;
						y = 3.75f;
						moveX = moveY = 1.25f;
						progress = PartProgress.warmup;
						heatProgress = PartProgress.reload.curve(Interp.pow2In);
						under = mirror = true;
					}}
				);
				parts.addAll(
					new RegionPart("-blade") {{
						x = 5.25f;
						y = 2f;
						moveX = -1.25f;
						moveY = -3.25f;
						progress = PartProgress.warmup;
						heatProgress = PartProgress.reload.curve(Interp.pow2In);
						under = mirror = true;
					}}
				);
				parts.addAll(
					new RegionPart("-heat") {{
						drawRegion = false;
						progress = PartProgress.warmup;
					}}
				);
			}};
			ammo(
				OblivionResources.niobium, new ContinuousLaserBulletType(12) {{
					lifetime = 60f;
					colors = new Color[]{Color.valueOf("58BBEC"), Color.valueOf("8FDAFF"), Color.white};
				}}
			);
		}};
		evaporate = new ContinuousLiquidTurret("evaporate") {{
			requirements(Category.turret, with(
				OblivionResources.niobium, 150,
				OblivionResources.hafnium, 120
			));
			size = 3;
			health = 200;
			range = 23f * 8f;
			drawer = new DrawTurret() {{
				parts.addAll(
					new RegionPart("-cannon") {{
						moveY = -2.5f;
						progress = PartProgress.warmup;
						under = true;
					}}
				);
			}};
			ammo(
				Liquids.water, new ContinuousFlameBulletType(30) {{
					Color watercol = Liquids.water.color;
					length = 26f * 8f;
					colors = new Color[]{watercol.cpy().mul(1.5f), watercol.cpy().mul(1.6f), watercol.cpy().mul(1.7f), watercol.cpy().mul(1.8f), Color.white};
					hitColor = lightColor = flareColor = colors[2];
				}}
			);
		}};

		genesis = new ItemTurret("genesis") {{
			requirements(Category.turret, with(
				OblivionResources.sodium, 35
			));
			size = 1;
			reload = 60f;
			range = 20f * 8f;
			ammo(
				OblivionResources.sodium, new ArtilleryBulletType(2f, 12) {{
					width = height = 12f;
					lifetime = 80f;
					trailWidth = 1.8f;
					trailLength = 8;
					collides = collidesAir = collidesGround = collidesTiles = true;
					shootSound = OblivionSounds.chargedShot;
					frontColor = trailColor = Color.valueOf("BAF2B7");
					backColor = Color.valueOf("87B085");
				}}
			);
		}};
		redemption = new ItemTurret("redemption") {{
			requirements(Category.turret, with(
				OblivionResources.sodium, 55,
				OblivionResources.hafnium, 35,
				Items.plastanium, 45
			));
			size = 2;
			reload = 90f;
			range = 30f * 8f;
			shoot = new ShootAlternate(2.75f);
			ammo(
				OblivionResources.sodium, new ArtilleryBulletType(3f, 30) {{
					width = height = 12f;
					lifetime = 80f;
					trailWidth = 1.8f;
					trailLength = 8;
					collides = collidesAir = collidesGround = collidesTiles = true;
					shootSound = OblivionSounds.chargedShot;
					frontColor = trailColor = Color.valueOf("BAF2B7");
					backColor = Color.valueOf("87B085");
				}}
			);
		}};
		apocalypse = new ItemTurret("apocalypse") {{
			requirements(Category.turret, with(
				OblivionResources.sodium, 150,
				OblivionResources.hafnium, 200,
				OblivionResources.niobium, 250,
				OblivionResources.polonium, 120,
				Items.plastanium, 175
			));
			size = 3;
			reload = 120f;
			range = 35f * 8f;
			drawer = new DrawTurret() {{
				parts.addAll(
					new RegionPart("-heat") {{
						drawRegion = false;
						heatProgress = PartProgress.reload.curve(Interp.pow2In);
						heatColor = Pal.heal;
					}}
				);
			}};
			ammo(
				OblivionResources.sodium, new ArtilleryBulletType(4f, 80) {{
					width = height = 16f;
					lifetime = 70f;
					trailWidth = 3f;
					trailLength = 12;
					collides = collidesAir = collidesGround = collidesTiles = true;
					shootSound = OblivionSounds.chargedShot;
					frontColor = trailColor = Color.valueOf("BAF2B7");
					backColor = Color.valueOf("87B085");
				}}
			);
		}};

		sodaicFactory = new UnitFactory("sodaic-factory") {{
			requirements(Category.units, with(
				OblivionResources.niobium, 30,
				OblivionResources.hafnium, 50,
				OblivionResources.sodium, 40
			));
			size = 3;
			health = 200;
			consumePower(2f);
			plans.addAll(
				new UnitPlan(OblivionUnits.mercurie, 60f * 60f, with(
					OblivionResources.hafnium, 5,
					OblivionResources.sodium, 7
				))
			);
		}};
		hafoniFactory = new UnitFactory("hafoni-factory") {{
			requirements(Category.units, with(
				OblivionResources.niobium, 30,
				OblivionResources.hafnium, 30
			));
			size = 3;
			health = 200;
			consumePower(2f);
			plans.addAll(
				new UnitPlan(OblivionUnits.latrodectus, 60f * 60f, with(
					OblivionResources.hafnium, 3,
					OblivionResources.niobium, 7
				))
			);
		}};

		elevativeReconstructor = new Reconstructor("elevative-reconstructor") {{
			requirements(Category.units, with(
				OblivionResources.niobium, 60,
				OblivionResources.hafnium, 80,
				OblivionResources.sodium, 50
			));
			size = 3;
			health = 350;
			constructTime = 60f * 30f;
			consumeItems(with(OblivionResources.hafnium, 30, OblivionResources.sodium, 50));
			consumeLiquid(OblivionResources.xenonium, 2f);
			consumePower(5f);
			upgrades.addAll(
				new UnitType[]{OblivionUnits.mercurie, OblivionUnits.aphrodite},
				new UnitType[]{OblivionUnits.latrodectus, OblivionUnits.phoneutria}
			);
		}};
		scalativeReconstructor = new Reconstructor("scalative-reconstructor") {{
			requirements(Category.units, with(
				OblivionResources.niobium, 230,
				OblivionResources.hafnium, 200,
				OblivionResources.sodium, 180,
				Items.plastanium, 150
			));
			size = 5;
			health = 350;
			constructTime = 60f * 50f;
			consumeItems(with(Items.plastanium, 30, OblivionResources.sodium, 50));
			consumeLiquid(OblivionResources.xenonium, 2.5f);
			consumePower(9f);
			upgrades.addAll(
				new UnitType[]{OblivionUnits.aphrodite, OblivionUnits.apollo},
				new UnitType[]{OblivionUnits.phoneutria, OblivionUnits.lycosidae}
			);
		}};
		ascenditeReconstructor = new Reconstructor("ascendite-reconstructor") {{
			requirements(Category.units, with(
				OblivionResources.niobium, 540,
				OblivionResources.hafnium, 450,
				OblivionResources.sodium, 300,
				OblivionResources.polonium, 600,
				Items.plastanium, 350
			));
			size = 7;
			health = 800;
			constructTime = 60f * 120f;
			consumeItems(with(Items.plastanium, 120, OblivionResources.sodium, 180, OblivionResources.polonium, 220));
			consumeLiquid(OblivionResources.xenonium, 4f);
			consumePower(15f);
			upgrades.addAll(
				new UnitType[]{OblivionUnits.apollo, OblivionUnits.zeus},
				new UnitType[]{OblivionUnits.lycosidae, OblivionUnits.sparassidae}
			);
		}};
		skinialReconstructor = new Reconstructor("skinial-reconstructor") {{
			requirements(Category.units, with(
				OblivionResources.niobium, 1500,
				OblivionResources.hafnium, 850,
				OblivionResources.sodium, 900,
				OblivionResources.polonium, 1200,
				Items.plastanium, 1350
			));
			size = 9;
			health = 1500;
			constructTime = 60f * 180f;
			consumeItems(with(Items.plastanium, 350, OblivionResources.sodium, 280, OblivionResources.polonium, 500, Items.surgeAlloy, 400));
			consumeLiquid(OblivionResources.xenonium, 8f);
			consumePower(20f);
			upgrades.addAll(
				new UnitType[]{OblivionUnits.zeus, OblivionUnits.chronos},
				new UnitType[]{OblivionUnits.sparassidae, OblivionUnits.trichonephila}
			);
		}};


		niobiumWall = new Wall("niobium-wall") {{
			requirements(Category.defense, with(
				OblivionResources.niobium, 6
			));
			health = 200;
			size = 1;
		}};
		largeNiobiumWall = new Wall("niobium-wall-large") {{
			requirements(Category.defense, with(
				OblivionResources.niobium, 24
			));
			health = 800;
			size = 2;
		}};
		hugeNiobiumWall = new Wall("niobium-wall-huge") {{
			requirements(Category.defense, with(
				OblivionResources.niobium, 54
			));
			health = 1800;
			size = 3;
		}};
		sodiumWall = new ExplosiveWall("sodium-wall") {{
			requirements(Category.defense, with(
				OblivionResources.sodium, 6
			));
			health = 400;
			size = 1;
			damage = 120f;
			range = 40f;
			flames = 6;
			destroyEffect = LamoniFx.sodiumExplosion;
		}};
		largeSodiumWall = new ExplosiveWall("sodium-wall-large") {{
			requirements(Category.defense, with(
				OblivionResources.sodium, 24
			));
			health = 1600;
			size = 2;
			damage = 175f;
			range = 80f;
			flames = 12;
			destroyEffect = LamoniFx.sodiumExplosion;
		}};
		hugeSodiumWall = new ExplosiveWall("sodium-wall-huge") {{
			requirements(Category.defense, with(
				OblivionResources.sodium, 54
			));
			health = 3600;
			size = 3;
			damage = 250f;
			range = 120f;
			flames = 18;
			destroyEffect = LamoniFx.sodiumExplosion;
		}};
		hafniumWall = new Wall("hafnium-wall") {{
			requirements(Category.defense, with(
				OblivionResources.hafnium, 6
			));
			health = 300;
			size = 1;
		}};
		largeHafniumWall = new Wall("hafnium-wall-large") {{
			requirements(Category.defense, with(
				OblivionResources.hafnium, 24
			));
			health = 1200;
			size = 2;
		}};
		hugeHafniumWall = new Wall("hafnium-wall-huge") {{
			requirements(Category.defense, with(
				OblivionResources.hafnium, 54
			));
			health = 2700;
			size = 3;
		}};
		poloniumWall = new ExplosiveWall("polonium-wall") {{
			requirements(Category.defense, with(
				OblivionResources.polonium, 6
			));
			health = 300;
			size = 1;
			damage = 200f;
			range = 80f;
			destroyEffect = LamoniFx.poloniumExplosion;
		}};
		largePoloniumWall = new ExplosiveWall("polonium-wall-large") {{
			requirements(Category.defense, with(
				OblivionResources.polonium, 24
			));
			health = 1200;
			size = 2;
			damage = 245f;
			range = 120f;
			destroyEffect = LamoniFx.poloniumExplosion;
		}};
		hugePoloniumWall = new ExplosiveWall("polonium-wall-huge") {{
			requirements(Category.defense, with(
				OblivionResources.polonium, 54
			));
			health = 2700;
			size = 3;
			damage = 285f;
			range = 180f;
			destroyEffect = LamoniFx.poloniumExplosion;
		}};

		mantlePulverizer = new GenericCrafter("mantle-pulverizer") {{
			requirements(Category.crafting, with(
				OblivionResources.niobium, 200
			));
			health = 200;
			size = 3;
			craftTime = 10f;
			updateEffect = LamoniFx.imperialSmelt;
			drawer = new DrawMulti(
				new DrawRegion("-bottom"),
				new DrawArcSmelt() {{
					flameColor = Color.valueOf("D1EFFF");
					midColor = Color.valueOf("8CA9E8");
				}},
				new DrawDefault(),
				new DrawGlowRegion("-light")
			);
			consumePower(0.5f);
			outputItem = new ItemStack(Items.sand, 1);
			researchCost = with(OblivionResources.niobium, 250);
		}};
		hafniumSmelter = new GenericCrafter("hafnium-smelter") {{
			requirements(Category.crafting, with(
				OblivionResources.niobium, 150
			));
			health = 210;
			size = 3;
			craftTime = 180f;
			craftEffect = LamoniFx.hafniumSmelt;
			drawer = new DrawMulti(
				new DrawRegion("-bottom"),
				new DrawArcSmelt() {{
					flameColor = Pal.accent;
					midColor = Pal.accent.cpy().mul(0.7f);
				}},
				new DrawDefault()
			);
			consumeItems(with(
				OblivionResources.niobium, 2,
				Items.sand, 3
			));
			consumePower(2f);
			outputItem = new ItemStack(OblivionResources.hafnium, 5);
			researchCost = with(OblivionResources.niobium, 350);
		}};
		demineralizer = new GenericCrafter("demineralizer") {{
			requirements(Category.crafting, with(
				OblivionResources.niobium, 150,
				OblivionResources.hafnium, 200
			));
			size = 4;
			health = 260;
			craftTime = 60f;
			craftEffect = LamoniFx.waterSpill;
			drawer = new DrawMulti(
				new DrawRegion("-bottom"),
				new DrawWeave(),
				new DrawCultivator() {{
					plantColor = Color.valueOf("9FCF9C");
					plantColorLight = Color.valueOf("BAF2B7");
				}},
				new DrawDefault()
			);
			consumeItems(with(
				OblivionResources.hafnium, 1,
				OblivionResources.niobium, 2
			));
			consumeLiquid(Liquids.water, 0.1f);
			consumePower(1f);
			outputItem = new ItemStack(OblivionResources.sodium, 2);
		}};
		xenoicMixer = new GenericCrafter("xenoic-mixer") {{
			requirements(Category.crafting, with(
				OblivionResources.hafnium, 50,
				OblivionResources.sodium, 30
			));
			size = 2;
			health = 160;
			craftTime = 10;
			drawer = new DrawMulti(
				new DrawDefault(),
				new DrawGlowRegion("-light") {{
					color = Color.valueOf("E6D6FF").cpy();
				}},
				new DrawBlurSpin("-rotator", 20f)
			);
			consumeItems(with(
				OblivionResources.niobium, 1,
				OblivionResources.sodium, 1
			));
			consumeLiquid(Liquids.water, 0.2f);
			consumePower(2f);
			outputLiquid = new LiquidStack(OblivionResources.xenonium, 0.2f);
		}};
		plastaniumDensifier = new GenericCrafter("plastanium-densifier") {{
			requirements(Category.crafting, with(
				OblivionResources.hafnium, 120,
				OblivionResources.niobium, 150,
				OblivionResources.sodium, 80
			));
			size = 4;
			health = 250;
			craftTime = 120f;
			drawer = new DrawMulti(
				new DrawDefault(),
				new DrawGlowRegion("-light") {{
					color = Pal.heal.cpy();
				}}
			);
			consumeItems(with(
				OblivionResources.hafnium, 4,
				OblivionResources.sodium, 2
			));
			consumeLiquid(Liquids.oil, 0.016f);
			consumePower(2f);
			outputItem = new ItemStack(Items.plastanium, 3);
		}};
		poloniumCollider = new GenericCrafter("polonium-collider") {{
			requirements(Category.crafting, with(
				OblivionResources.niobium, 250,
				OblivionResources.hafnium, 300,
				OblivionResources.sodium, 150,
				Items.plastanium, 120
			));
			size = 4;
			health = 250;
			craftTime = 180f;
			drawer = new DrawMulti(
				new DrawRegion("-bottom"),
				new DrawArcSmelt() {{
					flameRad = 2f;
					circleSpace = 4f;
					flameRadiusScl = 6f;
					flameRadiusMag = 0.6f;
					circleStroke = 3f;
				}},
				new DrawDefault(),
				new DrawGlowRegion("-light") {{
					color = Color.valueOf("E6D6FF").cpy();
				}}
			);
			consumeItems(with(
				OblivionResources.hafnium, 3,
				OblivionResources.sodium, 5
			));
			consumeLiquid(OblivionResources.xenonium, 0.1f);
			consumePower(2.5f);
			outputItem = new ItemStack(OblivionResources.polonium, 4);
		}};
		surgeCharger = new GenericCrafter("surge-charger") {{
			requirements(Category.crafting, with(
				OblivionResources.polonium, 350,
				OblivionResources.hafnium, 400,
				OblivionResources.sodium, 450,
				OblivionResources.niobium, 500,
				Items.plastanium, 300
			));
			size = 5;
			health = 400;
			craftTime = 90f;
			drawer = new DrawMulti(
				new DrawRegion("-bottom"),
				new DrawArcSmelt() {{
					flameRad = 2f;
					circleSpace = 4f;
					flameRadiusScl = 6f;
					flameRadiusMag = 0.6f;
					circleStroke = 3f;
				}},
				new DrawDefault(),
				new DrawGlowRegion("-light") {{
					color = Pal.accent.cpy();
				}},
				new DrawGlowRegion("-vent") {{
					color = Pal.accent.cpy();
				}}
			);
			consumeItems(with(
				OblivionResources.polonium, 5,
				Items.plastanium, 4
			));
			consumeLiquid(Liquids.water, 0.4f);
			consumePower(5f);
			outputItem = new ItemStack(Items.surgeAlloy, 3);
		}};

		niobiumCombustor = new ConsumeGenerator("niobium-combustor") {{
			requirements(Category.power, with(
				OblivionResources.niobium, 30
			));
			size = 2;
			health = 160;
			powerProduction = 3f;
			itemDuration = 180f;
			drawer = new DrawMulti(new DrawDefault(), new DrawWarmupRegion());
			consumeItems(with(OblivionResources.niobium, 1));
			researchCost = with(OblivionResources.niobium, 100);
		}};

		lineNode = new BeamNode("line-node") {{
			requirements(Category.power, with(
				OblivionResources.niobium, 3
			));
			consumesPower = outputsPower = true;
			health = 100;
			consumePowerBuffered(1500f);
			range = 12;
			researchCost = with(OblivionResources.niobium, 50);
		}};

		vaccumPump = new AttributeCrafter("vaccum-pump") {{
			requirements(Category.production, with(
				OblivionResources.niobium, 50,
				OblivionResources.hafnium, 30
			));
			size = 2;
			health = 120;
			craftTime = 30f;
			craftEffect = LamoniFx.waterSpill;
			attribute = Attribute.water;
			drawer = new DrawMulti(
				new DrawRegion("-bottom"),
				new DrawBlurSpin("-rotator", 15f),
				new DrawRegion("-top"),
				new DrawDefault()
			);
			consumePower(1f);
			outputLiquid = new LiquidStack(Liquids.water, 0.25f);
		}};

		presaltPump = new AttributeCrafter("presalt-pump") {{
			requirements(Category.production, with(
				OblivionResources.hafnium, 120,
				OblivionResources.sodium, 75,
				OblivionResources.niobium, 150
			));
			size = 3;
			health = 200;
			craftTime = 10f;
			attribute = Attribute.oil;
			drawer = new DrawMulti(
				new DrawDefault(),
				new DrawLiquidTile(Liquids.oil, 38f / 4f),
				new DrawGlowRegion("-light") {{
					color = Pal.heal.cpy();
				}},
				new DrawBlurSpin("-rotator", 20f)
			);
			consumePower(3f);
			outputLiquid = new LiquidStack(Liquids.oil, 0.2f);
		}};
	}
}
