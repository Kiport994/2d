package sk.tuke.kpi.oop.game.characters;

import java.util.ArrayList;
import java.util.List;

public class Health {
    private int actualHealth;
    private int maxHealth;

    private final List<FatigueEffect> fatigueEffects = new ArrayList<>();
    private boolean fatigued;
    public Health(int health, int maxhealth) {
        this.actualHealth = health;
        this.maxHealth = maxhealth;
    }

    public Health(int maxHealth) {
        this(maxHealth, maxHealth);
    }

    public int getValue() {
        return actualHealth;
    }

    public void refill(int amount) {
        actualHealth = Math.min(maxHealth, actualHealth + amount);
    }

    public void restore() {
        actualHealth = maxHealth;
    }

    public void drain(int amount) {
        if (amount <= 0 || fatigued){
            return;
        }
        actualHealth = Math.max(actualHealth - amount, 0);

        if (actualHealth == 0 && !fatigued) {
            fatigued = true;
            for (FatigueEffect effect : fatigueEffects) {
                effect.apply();
            }
        }
    }

    public void exhaust() {
        if (!fatigued) {
            actualHealth = 0;
            fatigued = true;
            for (FatigueEffect effect : fatigueEffects) {
                effect.apply();
            }
        }
    }

    @FunctionalInterface
    public interface FatigueEffect {
        void apply();
    }

    public void onFatigued(FatigueEffect effect) {
        fatigueEffects.add(effect);
    }
}
