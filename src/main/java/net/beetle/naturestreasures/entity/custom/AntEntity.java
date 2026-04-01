package net.beetle.naturestreasures.entity.custom;

import net.beetle.naturestreasures.entity.Catchable;
import net.beetle.naturestreasures.entity.ModEntities;
import net.beetle.naturestreasures.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class AntEntity extends AnimalEntity implements Catchable {

    @Nullable
    private UUID leaderUuid;
    private int leaderSearchCooldown = 0;

    public AntEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    // Animation fields
    public final AnimationState IdleAnimationState = new AnimationState();
    private int IdleAnimationTimeout = 0;

    private void setupAnimationStates() {
        if (this.IdleAnimationTimeout <= 0) {
            this.IdleAnimationTimeout = this.random.nextInt(40) + 80;
            this.IdleAnimationState.start(this.age);
        } else {
            --this.IdleAnimationTimeout;
        }
    }

    @Override
    protected void updateLimbs(float posDelta) {
        float f = this.getPose() == EntityPose.STANDING ? Math.min(posDelta * 6.0f, 1.0f) : 0.0f;
        this.limbAnimator.updateLimbs(f, 0.2f);
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.getWorld().isClient()) {
            if (leaderUuid != null) {
                AntEntity leader = getLeader();
                if (leader == null || !leader.isAlive()) {
                    leaderUuid = null;
                }
            }

            if (leaderUuid == null && leaderSearchCooldown-- <= 0) {
                leaderSearchCooldown = 20;
                findLeader();
            }
        }

        if (this.getWorld().isClient()) {
            setupAnimationStates();
        }
    }

    private void findLeader() {
        List<AntEntity> candidates = this.getWorld().getEntitiesByClass(
                AntEntity.class,
                this.getBoundingBox().expand(10.0), // search radius 10 blocks
                ant -> ant != this && ant.leaderUuid == null // only free ants (potential leaders)
        );

        if (!candidates.isEmpty()) {
            AntEntity leader = candidates.stream()
                    .min((a, b) -> Double.compare(this.squaredDistanceTo(a), this.squaredDistanceTo(b)))
                    .orElse(null);
            if (leader != null) {
                this.leaderUuid = leader.getUuid();
            }
        }
    }

    @Nullable
    public AntEntity getLeader() {
        if (leaderUuid == null) return null;
        World world = this.getWorld();
        if (world instanceof ServerWorld serverWorld) {
            Entity entity = serverWorld.getEntity(leaderUuid);
            if (entity instanceof AntEntity ant) {
                return ant;
            }
        }
        return null;
    }

    public void clearLeader() {
        leaderUuid = null;
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new AnimalMateGoal(this, 1.15D));
        this.goalSelector.add(2, new TemptGoal(this, 1.25D, Ingredient.ofItems(Items.SUGAR), false));
        this.goalSelector.add(3, new FollowLeaderGoal(this, 1.0D, 3.0F, 10.0F));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 4.0F));
        this.goalSelector.add(6, new LookAroundGoal(this));
    }

    public static DefaultAttributeContainer.Builder createAntAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 5)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 20);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.SUGAR);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.ANT.create(world);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SILVERFISH_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_SILVERFISH_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SILVERFISH_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15f, 1.0f);
    }

    @Override
    public int getCatchDifficulty() {
        return 0; // easiest difficulty
    }

    @Override
    public float getBaseCatchChance() {
        return 0.6f; // 60%
    }

    @Override
    public String getInsectDisplayName() {
        return "Ant";
    }

    @Override
    public String getInsectLore() {
        return "A small ant that lives in colonies. They love sugar!";
    }

    @Override
    public ItemStack getCaughtItem() {
        return new ItemStack(ModItems.ANT_ITEM);
    }

    class FollowLeaderGoal extends Goal {
        private final AntEntity ant;
        private final double speed;
        private final float minDistance;
        private final float maxDistance;

        public FollowLeaderGoal(AntEntity ant, double speed, float minDistance, float maxDistance) {
            this.ant = ant;
            this.speed = speed;
            this.minDistance = minDistance;
            this.maxDistance = maxDistance;
        }

        @Override
        public boolean canStart() {
            AntEntity leader = ant.getLeader();
            if (leader == null) return false;
            return ant.squaredDistanceTo(leader) > minDistance * minDistance;
        }

        @Override
        public boolean shouldContinue() {
            AntEntity leader = ant.getLeader();
            if (leader == null) return false;
            double distSq = ant.squaredDistanceTo(leader);
            return distSq > minDistance * minDistance && distSq < maxDistance * maxDistance;
        }

        @Override
        public void start() {
        }

        @Override
        public void stop() {
            ant.getNavigation().stop();
        }

        @Override
        public void tick() {
            AntEntity leader = ant.getLeader();
            if (leader != null) {
                ant.getNavigation().startMovingTo(leader, speed);
            }
        }
    }
}