package io.github.illegalbeagle123.fishen.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.*;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(ChickenEntity.class)
public abstract class FishChickenMixin extends AnimalEntity {

    @Shadow public int eggLayTime;

    private final Random random = new Random();

    protected FishChickenMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Environment(EnvType.CLIENT)
    @Inject(at = @At("HEAD"), method = "tickMovement()V")
    private void tickMovement(CallbackInfo info) {
        if (eggLayTime<=1) {
            this.playSound(SoundEvents.ENTITY_CHICKEN_EGG,1,1);
            TropicalFishEntity fish = new TropicalFishEntity(EntityType.TROPICAL_FISH,world);
            int n = random.nextInt(2);
            int o = random.nextInt(6);
            int p = random.nextInt(15);
            int q = random.nextInt(15);
            fish.setVariant(n | o << 8 | p << 16 | q << 24);
            fish.setVelocity(this.getVelocity().multiply(0.5).add(0,-1,0));
            fish.updatePosition(this.getX(),this.getY(),this.getZ());
            this.world.spawnEntity(fish);
            eggLayTime = new Random().nextInt(6000) + 6000;
        }
    }
}
