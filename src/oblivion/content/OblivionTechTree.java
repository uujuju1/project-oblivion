package oblivion.content;

import arc.struct.*;
import arc.util.*;
import mindustry.entities.bullet.*;
import mindustry.game.Objectives.*;
import mindustry.type.*;
import mindustry.type.unit.*;
import mindustry.world.blocks.defense.turrets.*;

import static mindustry.Vars.*;
import static mindustry.content.TechTree.*;

public class OblivionTechtree {

	public void load() {
		OblivionPlanets.lonela.techTree = nodeRoot("lonela", OblivionBlocks.start, true, () -> {
			// items
			nodeProduce(OblivionResources.mesulfate, Seq.with(new Produce(Items.silicon)), () -> {
				nodeProduce(OblivionResources.calenmite, Seq.with(new Produce(Items.metaglass)), () -> {
					nodeProduce(OblivionResources.carmanite, Seq.with(new SectorComplete(SectorPresets.stainedMountains)));
				});
				nodeProduce(OblivionResources.copremite, Seq.with(new Produce(Items.sporePod)), () -> {
					nodeProduce(OblivionResources.mothalate, Seq.with(new Produce(OblivionResources.carmanite)));
				});
			});

			// crafters
			node(OblivionBlocks.mesoForge, Seq.with(new Research(Blocks.siliconSmelter)), () -> {
				node(OblivionBlocks.calonicKiln, Seq.with(new Research(Blocks.kiln)), () -> {
					node(OblivionBlocks.carbonicInfuser);
				});
				node(OblivionBlocks.cloroSynthetizer, Seq.with(new Research(Blocks.cultivator)), () -> {
					node(OblivionBlocks.moloniteSmelter, Seq.with(new Research(OblivionBlocks.carbonicInfuser)));
				});
			});

			// turrets
			node(OblivionBlocks.uno, Seq.with(new Produce(OblivionResources.mesulfate)), () -> {
				node(OblivionBlocks.rain, Seq.with(new SectorComplete(SectorPresets.craters)), () -> {
					node(OblivionBlocks.granite, Seq.with(new SectorComplete(SectorPresets.ruinousShores), new Research(Blocks.ripple)), () -> {
						node(OblivionBlocks.fearSigil, Seq.with(new Produce(OblivionResources.mothalate)));
						node(OblivionBlocks.calamitySigil, Seq.with(new Produce(OblivionResources.mothalate), new Produce(OblivionResources.mesulfate)));
						node(OblivionBlocks.abyssSigil, Seq.with(new Produce(OblivionResources.mothalate), new Produce(OblivionResources.carmanite)));
					});
				});
				node(OblivionBlocks.toxic, Seq.with(new Produce(OblivionResources.copremite)), () -> {
					node(OblivionBlocks.corrosive, Seq.with(new SectorComplete(SectorPresets.craters)), () -> {
						node(OblivionBlocks.acidic, Seq.with(new SectorComplete(SectorPresets.ruinousShores), new Research(Blocks.ripple)));
					});
				});
				node(OblivionBlocks.hammer, Seq.with(new Produce(OblivionResources.calenmite)), () -> {
					node(OblivionBlocks.press, Seq.with(new SectorComplete(SectorPresets.craters)));
				});
			});

			// units
			// old unit researching(may have requirements to each unit)
			node(OblivionBlocks.solfreniteFactory, Seq.with(new Produce(OblivionResources.mesulfate)), () -> {
				node(OblivionUnits.slop, () -> {
					node(OblivionUnits.detra, Seq.with(new Research(OblivionBlocks.alphaReconstructor)), () -> {
						node(OblivionUnits.tedri, Seq.with(new Research(OblivionBlocks.betaReconstructor)), () -> {
							node(OblivionUnits.taleni, Seq.with(new Research(OblivionBlocks.gammaReconstructor)), () -> {
								node(OblivionUnits.kolete, Seq.with(new Research(OblivionBlocks.omegaReconstructor)));
							});
						});	
					});
				});
				node(OblivionBlocks.infestromeniFactory, Seq.with(new Produce(OblivionResources.copremite)), () -> {
					node(OblivionUnits.pioli, () -> {
						node(OblivionUnits.taneki, Seq.with(new Research(OblivionBlocks.alphaReconstructor)), () -> {
							node(OblivionUnits.notremite, Seq.with(new Research(OblivionBlocks.betaReconstructor)), () -> {
								node(OblivionUnits.dopretile, Seq.with(new Research(OblivionBlocks.gammaReconstructor)), () -> {
									node(OblivionUnits.niboletra, Seq.with(new Research(OblivionBlocks.omegaReconstructor)));
								});
							});	
						});
					});
				});
				node(OblivionBlocks.functiveFactory, Seq.with(new Produce(OblivionResources.carmanite)), () -> {
					node(OblivionUnits.phi, () -> {
						node(OblivionUnits.root, Seq.with(new Research(OblivionBlocks.alphaReconstructor)), () -> {
							node(OblivionUnits.multi, Seq.with(new Research(OblivionBlocks.betaReconstructor)), () -> {
								node(OblivionUnits.pow, Seq.with(new Research(OblivionBlocks.gammaReconstructor)), () -> {
									node(OblivionUnits.expo, Seq.with(new Research(OblivionBlocks.omegaReconstructor)));
								});
							});	
						});
					});
				});
				node(OblivionBlocks.alphaReconstructor, ItemStack.with(), Seq.with(new Research(Blocks.additiveReconstructor)), () -> {
					node(OblivionBlocks.betaReconstructor, ItemStack.with(), Seq.with(new Research(Blocks.multiplicativeReconstructor)), () -> {
						node(OblivionBlocks.gammaReconstructor, ItemStack.with(), Seq.with(new Research(Blocks.exponentialReconstructor)), () -> {
							node(OblivionBlocks.omegaReconstructor, ItemStack.with(), Seq.with(new Research(Blocks.tetrativeReconstructor)));
						});
					});
				});
			});

			// sectors
			node(OblivionSectors.newWorld, Seq.with(
				new SectorComplete(OblivionSectors.newWorld),
				new Research(OblivionBlocks.mesoForge)
			), () -> {
				node(OblivionSectors.oldTown, Seq.with(
					new SectorComplete(OblivionSectors.oldTown),
					new Research(OblivionBlocks.solfreniteFactory)
				));
			});

			// skill issue
			// t6
			extendNode(Blocks.tetrativeReconstructor, () -> {
				node(OblivionBlocks.mandlebrotReconstructor, Seq.with(new Produce(OblivionResources.mothalate), new Research(UnitTypes.reign)));
			}); 
			extendNode(UnitTypes.reign, () -> {
				node(OblivionUnits.republic, Seq.with(new Research(OblivionBlocks.mandlebrotReconstructor)));
			});
			extendNode(UnitTypes.corvus, () -> {
				node(OblivionUnits.giga, Seq.with(new Research(OblivionBlocks.mandlebrotReconstructor)));
			});
			extendNode(UnitTypes.toxopid, () -> {
				node(OblivionUnits.archaranid, Seq.with(new Research(OblivionBlocks.mandlebrotReconstructor)));
			});
			extendNode(UnitTypes.eclipse, () -> {
				node(OblivionUnits.bloodmoon, Seq.with(new Research(OblivionBlocks.mandlebrotReconstructor)));
			});
			extendNode(UnitTypes.omura, () -> {
				node(OblivionUnits.yetinus, Seq.with(new Research(OblivionBlocks.mandlebrotReconstructor)));
			});
		}); 
	}
}