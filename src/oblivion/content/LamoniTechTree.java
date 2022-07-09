package oblivion.content;

import arc.struct.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.game.Objectives.*;
import mindustry.type.*;
import mindustry.type.unit.*;
import mindustry.world.blocks.defense.turrets.*;

import static mindustry.Vars.*;
import static mindustry.content.TechTree.*;

public class LamoniTechTree {
	public void load() {
		OblivionPlanets.lamoni.techTree = nodeRoot("lamoni", OblivionBlocks.coreVillage, true, () -> {
			nodeProduce(OblivionResources.niobium, () -> {
				nodeProduce(OblivionResources.sodium, Seq.with(new Produce(OblivionResources.hafnium)), () -> {
					nodeProduce(OblivionResources.polonium, Seq.with(new Produce(Items.plastanium)), () -> {});
					nodeProduce(Items.surgeAlloy, Seq.with(new Produce(OblivionResources.polonium)), () -> {});
				});
				nodeProduce(Items.sand, () -> {
					nodeProduce(OblivionResources.hafnium, () -> {
						nodeProduce(Items.plastanium, () -> {});
					});
				});
				nodeProduce(Liquids.water, () -> {
					nodeProduce(OblivionResources.xenonium, Seq.with(new Produce(OblivionResources.sodium)), () -> {});
					nodeProduce(Liquids.oil, () -> {});
				});
			});

			node(OblivionBlocks.expansion, () -> {
				node(OblivionBlocks.evolution, () -> {
					node(OblivionBlocks.finalization, () -> {
				
					});
				});
			});

			node(OblivionBlocks.mantlePulverizer, () -> {
				node(OblivionBlocks.demineralizer, Seq.with(new Produce(OblivionResources.hafnium), new Research(OblivionBlocks.expansion), new OnSector(OblivionSectors.sodaicFactory)), () -> {
					node(OblivionBlocks.poloniumCollider, Seq.with(new Research(OblivionBlocks.plastaniumDensifier)), () -> {});
					node(OblivionBlocks.surgeCharger, Seq.with(new Research(OblivionBlocks.poloniumCollider)), () -> {});
				});
				node(OblivionBlocks.vaccumPump, () -> {
					node(OblivionBlocks.xenoicMixer, Seq.with(new Produce(OblivionResources.sodium), new Research(OblivionBlocks.evolution)), () -> {});
					node(OblivionBlocks.presaltPump, Seq.with(new Produce(OblivionResources.xenonium)), () -> {});
				});
				node(OblivionBlocks.hafniumSmelter, Seq.with(new OnSector(OblivionSectors.reactiveMonolith)), () -> {
					node(OblivionBlocks.plastaniumDensifier, Seq.with(new Produce(Liquids.oil), new Research(OblivionBlocks.finalization)), () -> {});
				});
			});

			node(OblivionBlocks.niobiumCombustor); 

			node(OblivionBlocks.imperialDrill, () -> {
				node(OblivionBlocks.mineralBoiler, Seq.with(new Produce(OblivionResources.hafnium)), () -> {});
				node(OblivionBlocks.hydraulicDrill, Seq.with(new OnPlanet(OblivionPlanets.vita)), () -> {});
			});

			node(OblivionBlocks.lineNode);

			node(OblivionBlocks.spread, () -> {
				node(OblivionBlocks.reaction, () -> {});
				node(OblivionBlocks.evaporate, Seq.with(new Produce(OblivionResources.hafnium)), () -> {
					node(OblivionBlocks.genesis, Seq.with(new Produce(OblivionResources.sodium)), () -> {
						node(OblivionBlocks.redemption, () -> {
							node(OblivionBlocks.apocalypse, () -> {});
						});
					});
				});
			});

			node(OblivionBlocks.hafoniFactory, Seq.with(new Produce(OblivionResources.hafnium)), () -> {
				node(OblivionUnits.latrodectus);
				node(OblivionBlocks.sodaicFactory, Seq.with(new Produce(OblivionResources.sodium)), () -> {
					node(OblivionUnits.mercurie);	
				});

				node(OblivionBlocks.elevativeReconstructor, Seq.with(new Produce(OblivionResources.xenonium)), () -> {
					node(OblivionUnits.aphrodite);
					node(OblivionUnits.phoneutria);

					node(OblivionBlocks.scalativeReconstructor, Seq.with(new Produce(Items.plastanium)), () -> {
						node(OblivionUnits.apollo);
						node(OblivionUnits.lycosidae);

						node(OblivionBlocks.ascenditeReconstructor, Seq.with(new Produce(OblivionResources.polonium)), () -> {
							node(OblivionUnits.zeus);
							node(OblivionUnits.sparassidae);

							node(OblivionBlocks.skinialReconstructor, Seq.with(new Produce(Items.surgeAlloy)), () -> {
								node(OblivionUnits.chronos);
								node(OblivionUnits.trichonephila);
								
							});
						});
					});
				});
			});
			

			node(OblivionBlocks.niobiumDuct, () -> {
				node(OblivionBlocks.niobiumRouter, () -> {
					node(OblivionBlocks.niobiumBridge, () -> {});
					node(OblivionBlocks.niobiumUnloader, () -> {});
				});
				node(OblivionBlocks.niobiumConduit, Seq.with(new Produce(Liquids.water)), () -> {
					node(OblivionBlocks.niobiumConduitRouter, () -> {
						node(OblivionBlocks.niobiumConduitBridge, () -> {});
					});
				});
			});

			node(OblivionBlocks.niobiumWall, () -> {
				node(OblivionBlocks.largeNiobiumWall, () -> {
					node(OblivionBlocks.hugeNiobiumWall, () -> {});
				});
				node(OblivionBlocks.hafniumWall, Seq.with(new Produce(OblivionResources.hafnium)), () -> {
					node(OblivionBlocks.largeHafniumWall, () -> {
						node(OblivionBlocks.hugeHafniumWall, () -> {});
					});
					node(OblivionBlocks.sodiumWall, Seq.with(new Produce(OblivionResources.sodium)), () -> {
						node(OblivionBlocks.largeSodiumWall, () -> {
							node(OblivionBlocks.hugeSodiumWall, () -> {});
						});

						node(OblivionBlocks.poloniumWall, Seq.with(new Produce(OblivionResources.polonium)), () -> {
							node(OblivionBlocks.largePoloniumWall, () -> {
								node(OblivionBlocks.hugePoloniumWall, () -> {});
							});
						});

						node(Blocks.plastaniumWall, Seq.with(new Produce(Items.plastanium)), () -> {
							node(Blocks.plastaniumWallLarge, () -> {});

							node(Blocks.surgeWall, Seq.with(new Produce(Items.surgeAlloy)), () -> {
								node(Blocks.surgeWallLarge, () -> {});
							});
						});
					});
				});
			});

			node(OblivionSectors.freshBeggining, () -> {
				node(OblivionSectors.reactiveMonolith, Seq.with(new Research(OblivionBlocks.niobiumUnloader)), () -> {
					node(OblivionSectors.sodaicOutpost, Seq.with(new Produce(OblivionResources.hafnium)), () -> {

					});
				});
			});
		});
	}
}
