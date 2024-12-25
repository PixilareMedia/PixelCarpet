package com.pixilaremedia.pixel_carpet.mixin.mechanics;

import com.pixilaremedia.pixel_carpet.PixelCarpetSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.SnowBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SnowBlock.class)
public class SnowBlockMixin {
    @Inject(method = "randomTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;getLightLevel(Lnet/minecraft/world/LightType;Lnet/minecraft/util/math/BlockPos;)I"), cancellable = true)
    private void preventSnowMelting(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if (PixelCarpetSettings.preventSnowMelting) {
            ci.cancel();
        }
    }
}
