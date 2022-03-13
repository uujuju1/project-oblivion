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
		mesoForge;

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
			craftTime = 30;
			consumes.items(with(
				Items.copper, 2,
				Items.lead, 2
			));
			consumes.power(1f);
			outputItem = new ItemStack(OblivionResources.mesulfate, 1);
		}};
	}
}