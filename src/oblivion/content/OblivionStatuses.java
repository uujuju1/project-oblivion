package oblivion.content;

import mindustry.type.*;
import mindustry.ctype.*;
import mindustry.content.*;

public class OblivionStatuses implements ContentList {
	public static StatusEffect infested, calamity, fear, abyss;

	@Override
	public void load() {
		infested = new StatusEffect("infested") {{
			damage = 0.06f;
			init(() -> {
				opposite(StatusEffects.burning, StatusEffects.melting);
			});
		}};
		calamity = new StatusEffect("calamity") {{
			damage = 1f;
		}};
	}
}