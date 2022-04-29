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
			damage = 1.6f;
			healthMultiplier = 2f;
		}};
		fear = new StatusEffect("fear") {{
			speedMultiplier = 0.3f;
			reloadMultiplier = 0.4f;
			damageMultiplier = 0.5f;
		}};
		abyss = new StatusEffect("abyss") {{
			disarmed = true;
			speedMultiplier = 0f;
			dragMultiplier = 69420f;
		}};
	}
}