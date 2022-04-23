package oblivion.content;

import arc.struct.Seq;
import mindustry.content.*;
import mindustry.content.TechTree.TechNode;
import mindustry.ctype.ContentList;
import mindustry.ctype.UnlockableContent;
import mindustry.game.Objectives.*;
import mindustry.type.ItemStack;

public class OblivionTechTree implements ContentList {
	static TechTree.TechNode context = null;
 
	private static void extendNode (UnlockableContent parent, Runnable children) {
		context = TechTree.all.find(t -> t.content == parent);
		children.run();
	}

	private static void node (UnlockableContent content, ItemStack[] requirements, Seq <Objective> objectives, Runnable children) {
		TechNode node = new TechNode(context, content, requirements);
		if (objectives != null) node.objectives = objectives;

		TechNode prev = context;
		context = node;
		children.run();
		context = prev;
	}

	private static void node (UnlockableContent content, ItemStack[] requirements, Seq <Objective> objectives) {
		node(content, requirements, objectives, () -> {
		});
	}

	private static void node (UnlockableContent content, Seq <Objective> objectives) {
		node(content, content.researchRequirements(), objectives, () -> {
		});
	}

	private static void node (UnlockableContent content, ItemStack[] requirements) {
		node(content, requirements, Seq.with(), () -> {
		});
	}

	private static void node (UnlockableContent content, ItemStack[] requirements, Runnable children) {
		node(content, requirements, null, children);
	}

	private static void node (UnlockableContent content, Seq <Objective> objectives, Runnable children) {
		node(content, content.researchRequirements(), objectives, children);
	}

	private static void node (UnlockableContent content, Runnable children) {
		node(content, content.researchRequirements(), children);
	}

	private static void node (UnlockableContent block) {
		node(block, () -> {
		});
	}

	private static void nodeProduce (UnlockableContent content, Seq <Objective> objectives, Runnable children) {
		node(content, content.researchRequirements(), objectives.and(new Produce(content)), children);
	}

	private static void nodeProduce (UnlockableContent content, Seq <Objective> objectives) {
		nodeProduce(content, objectives, () -> {
		});
	}

	private static void nodeProduce (UnlockableContent content, Runnable children) {
		nodeProduce(content, Seq.with(), children);
	}

	private static void nodeProduce (UnlockableContent content) {
		nodeProduce(content, Seq.with(), () -> {
		});
	}                   

	@Override
	public void load() {
		extendNode(Blocks.coreShard, () -> {
			node(OblivionBlocks.start, () -> {
				nodeProduce(OblivionResources.mesulfate, Seq.with(new Produce(Items.silicon)), () -> {
					nodeProduce(OblivionResources.calenmite, Seq.with(new Produce(Items.metaglass)), () -> {
						nodeProduce(OblivionResources.carmanite, Seq.with(new SectorComplete(SectorPresets.stainedMountains)));
					});
					nodeProduce(OblivionResources.copremite, Seq.with(new Produce(Items.sporePod)), () -> {
						nodeProduce(OblivionResources.mothalate, Seq.with(new Produce(OblivionResources.carmanite)));
					});
				});
				node(OblivionBlocks.mesoForge, Seq.with(new Research(Blocks.siliconSmelter)), () -> {
					node(OblivionBlocks.calonicKiln, Seq.with(new Research(Blocks.kiln)), () -> {
						node(OblivionBlocks.carbonicInfuser);
					});
					node(OblivionBlocks.cloroSynthetizer, Seq.with(new Research(Blocks.cultivator)), () -> {
						node(OblivionBlocks.moloniteSmelter, Seq.with(new Research(OblivionBlocks.carbonicInfuser)));
					});
				});
				node(OblivionBlocks.uno, Seq.with(new Produce(OblivionResources.mesulfate)), () -> {
					node(OblivionBlocks.rain, Seq.with(new SectorComplete(SectorPresets.craters)), () -> {
						node(OblivionBlocks.granite, Seq.with(new SectorComplete(SectorPresets.ruinousShores), new Research(Blocks.ripple)));
					});
				});
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
				});
				node(OblivionBlocks.alphaReconstructor, ItemStack.with(), Seq.with(new Research(Blocks.additiveReconstructor)), () -> {
					node(OblivionBlocks.betaReconstructor, ItemStack.with(), Seq.with(new Research(Blocks.multiplicativeReconstructor)), () -> {
						node(OblivionBlocks.gammaReconstructor, ItemStack.with(), Seq.with(new Research(Blocks.exponentialReconstructor)), () -> {
							node(OblivionBlocks.omegaReconstructor, ItemStack.with(), Seq.with(new Research(Blocks.tetrativeReconstructor)));
						});
					});
				});
			});
			node(OblivionSectors.newWorld, Seq.with(
				new SectorComplete(OblivionSectors.newWorld),
				new Research(OblivionBlocks.mesoForge)
			), () -> {
				node(OblivionSectors.oldTown, Seq.with(
					new SectorComplete(OblivionSectors.oldTown),
					new Research(OblivionBlocks.solfreniteFactory)
				));
			});
		});
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
	}
}