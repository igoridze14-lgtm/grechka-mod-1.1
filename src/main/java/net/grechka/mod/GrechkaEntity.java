package net.grechka.mod;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class GrechkaEntity extends WolfEntity {

    public GrechkaEntity(EntityType<? extends WolfEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        // Переопределяем цели поведения как обычный волк
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new SitGoal(this));
        this.goalSelector.add(3, new AttackGoal(this, 1.0D, true));
        this.goalSelector.add(4, new FollowOwnerGoal(this, 1.0D, 10.0f, 2.0f, false));
        this.goalSelector.add(5, new WanderAroundGoal(this, 0.8D));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.add(7, new LookAroundGoal(this));

        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, net.minecraft.entity.hostile.HostileEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder createGrechkaAttributes() {
        return WolfEntity.createWolfAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0D);
    }

    @Override
    public boolean interactMob(PlayerEntity player, net.minecraft.util.Hand hand) {
        var itemStack = player.getStackInHand(hand);
        
        // Приручение костью
        if (itemStack.isOf(Items.BONE)) {
            if (!this.getWorld().isClient) {
                itemStack.decrement(1);
                this.setOwner(player);
                this.getWorld().sendEntityStatus(this, (byte) 7);
            }
            return true;
        }
        
        return super.interactMob(player, hand);
    }

    @Override
    public void onDeath(net.minecraft.entity.damage.DamageSource damageSource) {
        // Дропим яйцо призыва при смерти
        if (!this.getWorld().isClient) {
            this.dropItem(GreczkaMod.GRECHKA_SPAWN_EGG);
        }
        super.onDeath(damageSource);
    }

    @Override
    public GrechkaEntity createChild(WolfEntity other) {
        return new GrechkaEntity(GreczkaMod.GRECHKA, this.getWorld());
    }
}
