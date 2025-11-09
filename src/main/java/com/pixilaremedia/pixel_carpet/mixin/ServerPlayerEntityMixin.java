package com.pixilaremedia.pixel_carpet.mixin;

import com.mojang.authlib.GameProfile;
import com.pixilaremedia.pixel_carpet.PixelCarpetSettings;
import com.pixilaremedia.pixel_carpet.utils.PvpWhitelist;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.encryption.PlayerPublicKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {
    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile, @Nullable PlayerPublicKey publicKey) {
        super(world, gameProfile);
    }

    @Inject(method = "shouldDamagePlayer", at = @At("HEAD"), cancellable = true)
    public void checkWhitelist(PlayerEntity targetPlayer, CallbackInfoReturnable<Boolean> cir) {
        if (PixelCarpetSettings.pvpToggle) {
            if (!PvpWhitelist.contains(this.getPlayerConfigEntry()) || !PvpWhitelist.contains(targetPlayer.getPlayerConfigEntry())) {
                cir.setReturnValue(false);
            }
        }
    }
}
