package dev.waterlilly.editableplayernbthack.mixin;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.EntityDataObject;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.UUID;

@Mixin(EntityDataObject.class)
public class AllowPlayerDataEditMixin {
    @Shadow
    private Entity entity;
    /**
     * @author Lilly Rosaline
     * @reason This removes the requirement that the target entity is not a player.
     */
    @Overwrite
    public void setNbt(NbtCompound nbt) {
        UUID uUID = this.entity.getUuid();
        this.entity.readNbt(nbt);
        this.entity.setUuid(uUID);
    }
}
