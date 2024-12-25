package com.pixilaremedia.pixel_carpet.mixin.mechanics;

import com.pixilaremedia.pixel_carpet.PixelCarpetSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FallingBlockEntity.class)
public abstract class FallingBlockEntityMixin extends Entity {
    @Shadow public abstract BlockState getBlockState();

    public FallingBlockEntityMixin(EntityType<?> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", cancellable = true, at = @At(
            value = "FIELD",
            target = "Lnet/minecraft/entity/FallingBlockEntity;destroyedOnLanding:Z",
            shift = At.Shift.BEFORE
    ))
    private void tick(CallbackInfo ci) {
        if (getBlockState().isIn(BlockTags.ANVIL)) {
            World world = this.getWorld();
            if (PixelCarpetSettings.renewableGravel && world.getBlockState(BlockPos.ofFloored(this.getX(), this.getY() - 0.06, this.getZ())).getBlock() == Blocks.COBBLED_DEEPSLATE) {
                world.breakBlock(getBlockPos().down(), false);
                world.setBlockState(getBlockPos().down(), Blocks.GRAVEL.getDefaultState(), 3);
            }
        }
    }
}
