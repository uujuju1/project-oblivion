package oblivion.content;

import arc.graphics.*;
import mindustry.type.*;
import mindustry.content.*;
import mindustry.world.draw.*;
import mindustry.type.ItemStack.*;
import mindustry.ctype.ContentList.*;
import mindustry.world.blocks.production.*;

public class OblivionBlocks implements ContentList {
	public static Block 
		mesoForge, carbonicInfuser, cloroSynthetizer;

	@Override
	public void load() {
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
				Items.plastianium, 100,
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
				Items.plastianium, 120,
				Items.silicon, 150,
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
	}
}