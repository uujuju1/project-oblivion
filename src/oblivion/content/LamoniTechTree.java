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
				});
			});

			node(OblivionBlocks.mantlePulverizer, () -> {
				node(OblivionBlocks.demineralizer, Seq.with(new Produce(OblivionResources.hafnium)), () -> {});
				node(OblivionBlocks.hafniumSmelter, () -> {});
				node(OblivionBlocks.vaccumPump, () -> {
					node(OblivionBlocks.xenoicMixer, Seq.with(new Produce(OblivionResources.sodium)), () -> {});
				});
			});

			node(OblivionBlocks.niobiumCombustor, () -> {}); 

			node(OblivionBlocks.imperialDrill, () -> {});

			node(OblivionBlocks.spread, () -> {
				node(OblivionBlocks.reaction, () -> {});
				node(OblivionBlocks.evaporate, Seq.with(new Produce(OblivionResources.hafnium)));
			});

			node(OblivionBlocks.sodaicFactory, Seq.with(new Produce(OblivionResources.sodium)), () -> {
				node(OblivionUnits.mercurie, () -> {
					node(OblivionUnits.aphrodite, Seq.with(new Research(OblivionBlocks.elevativeReconstructor)), () -> {});
				});
				node(OblivionBlocks.elevativeReconstructor, () -> {});
			});

			node(OblivionBlocks.niobiumDuct, () -> {});

			node(OblivionBlocks.niobiumWall, () -> {
				node(OblivionBlocks.largeNiobiumWall, () -> {
					node(OblivionBlocks.hugeNiobiumWall, () -> {});
				});
			});
		});
	}
}
