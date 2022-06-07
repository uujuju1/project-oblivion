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
				nodeProduce(OblivionResources.sodium, Seq.with(new Produce(OblivionResources.hafnium)), () -> {});
				nodeProduce(Items.sand, () -> {
					nodeProduce(OblivionResources.hafnium, () -> {});
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
				node(OblivionBlocks.demineralizer, Seq.with(new Produce(OblivionResources.hafnium), new Research(OblivionBlocks.expansion)), () -> {});
				node(OblivionBlocks.vaccumPump, () -> {
					node(OblivionBlocks.xenoicMixer, Seq.with(new Produce(OblivionResources.sodium) new Research(OblivionBlocks.evolution)), () -> {});
					node(OblivionBlocks.presaltPump, Seq.with(new Produce(OblivionResources.xenonium)), () -> {});
				});
				node(OblivionBlocks.hafniumSmelter, () -> {
					node(OblivionBlocks.plastaniumDensifier, Seq.with(new Produce(Liquids.oil), new Research(OblivionBlocks.finalization)), () -> {});
				});
			});

			node(OblivionBlocks.niobiumCombustor); 

			node(OblivionBlocks.imperialDrill);

			node(OblivionBlocks.lineNode);

			node(OblivionBlocks.spread, () -> {
				node(OblivionBlocks.reaction, () -> {});
				node(OblivionBlocks.evaporate, Seq.with(new Produce(OblivionResources.hafnium)), () -> {});
			});

			node(OblivionBlocks.sodaicFactory, Seq.with(new Produce(OblivionResources.sodium)), () -> {
				node(OblivionUnits.mercurie);
				node(OblivionBlocks.elevativeReconstructor, Seq.with(new Produce(OblivionResources.xenonium)), () -> {
					node(OblivionUnits.aphrodite);
					node(OblivionBlocks.scalativeReconstructor, Seq.with(new Produce(Items.plastanium)), () -> {
						node(OblivionUnits.apollo);
					});
				});
			});

			node(OblivionBlocks.niobiumDuct);

			node(OblivionBlocks.niobiumWall, () -> {
				node(OblivionBlocks.largeNiobiumWall, () -> {
					node(OblivionBlocks.hugeNiobiumWall, () -> {});
				});
			});
		});
	}
}
