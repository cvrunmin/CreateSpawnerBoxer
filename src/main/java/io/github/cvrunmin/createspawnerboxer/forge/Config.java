package io.github.cvrunmin.createspawnerboxer.forge;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.DoubleValue BUMP_MOB_SPAWNER_RESET_CHANCE = BUILDER
            .comment("the chance of mob spawner resetting spawn delay when a punching deployer hits the spawner while holding nothing")
            .defineInRange("bumpMobSpawnerResetChance", 0.1, 0, 1);

    public static final ModConfigSpec.DoubleValue BUMP_TRIAL_SPAWNER_RESET_CHANCE = BUILDER
            .comment("the chance of trial spawner resetting spawn delay when a punching deployer hits the spawner while holding nothing")
            .defineInRange("bumpTrialSpawnerResetChance", 0.02, 0, 1);

    static final ModConfigSpec SPEC = BUILDER.build();
}
