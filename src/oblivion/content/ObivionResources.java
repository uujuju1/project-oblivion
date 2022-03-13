package oblivion.content;

import arc.graphics.*;
import mindustry.ctype.ContentList;
import mindustry.type.*;

public class OblivionResources implements ContentList {
	public static Item mesulfate, calenmite, copremite, mothalate, carmanite;

	@Override
	public void load() {
		mesulfate = new Item("mesulfate", Color.valueOf("E86F6F")) {{cost = 2}};
		calenmite = new Item("calenmite", Color.valueOf("EBEEF2")) {{cost = 3}};
		copremite = new Item("copremite", Color.valueOf("31942E")) {{cost = 3}};
		carmanite = new Item("carmanite", Color.valueOf("515151")) {{cost = 5}};
		mothalate = new Item("mothalate", Color.valueOf("313B7A")) {{cost = 7}};
	}
}