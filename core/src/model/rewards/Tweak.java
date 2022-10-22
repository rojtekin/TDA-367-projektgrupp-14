package model.rewards;

import java.util.Objects;

/**
 * A vector like class thats used to apply rewards
 */
public class Tweak {
	private final float scale;
	private final float offset;
	private final LivingTrait trait;

	/**
	 * @param t the trait its associated with
	 * @param scale what it scales an attribute with
	 * @param offset what it adds to an attribute before scaling it
	 */
	public Tweak(LivingTrait t, float scale, float offset) {
		this.trait = Objects.requireNonNull(t);
		this.scale = scale;
		this.offset = offset;
	}

	/**
	 * @return returns the trait value used in HashMap for perk application structure
	 */
	public LivingTrait getTrait() {
		return this.trait;
	}

	/**
	 * @param v playarCharacter attribute it's effecting
	 * @return the new desired value
	 */
	public float apply(float v) {
		return (v + this.offset) * this.scale;
	}
}
