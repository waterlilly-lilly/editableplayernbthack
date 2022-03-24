package dev.waterlilly.lem.editableplayernbthack.mixin;

import net.minecraft.command.EntityDataObject;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(EntityDataObject.class)
public class AllowPlayerDataEditMixin {
    @Final
    @Shadow
    private Entity entity;
    /**
     * @author Lilly Rosaline
     */
    @Inject(at = @At(value = "HEAD"), method = "setNbt", cancellable = true)
    public void injectSetNbt(NbtCompound nbt, CallbackInfo ci) {
        if(this.entity instanceof PlayerEntity) {
            UUID uUID = this.entity.getUuid();
            this.entity.readNbt(nbt);
            this.entity.setUuid(uUID);
            ci.cancel();
        }
    }
}
