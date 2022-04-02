package oblivion.content;

import mindustry.type.*;
import mindustry.ctype.*;
import mindustry.content.StatusEffects.*;

public class OblivionStatuses implements ContentList {
	public static StatusEffect infested;

	@Override
	public void load() {
		infested = new StatusEffect("infested") {{
			damage = 0.06f;
			init(() -> {
				opposite(burning, melting);
			});
		}};
	}
}