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
import net.minecraft.util.math.Vec3d;
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
                this.getBoundingBox().expand(10.0),
                ant -> ant != this && ant.leaderUuid == null
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
        this.goalSelector.add(1, new FleePlayerGoal(this, 8.0f, 1.5, 1.8));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.15D));
        this.goalSelector.add(3, new TemptGoal(this, 1.25D, Ingredient.ofItems(Items.SUGAR), false));
        this.goalSelector.add(4, new ImprovedFollowLeaderGoal(this, 1.0D, 2.5F, 8.0F));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.8D));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 4.0F));
        this.goalSelector.add(7, new LookAroundGoal(this));
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
        return 0;
    }

    @Override
    public float getBaseCatchChance() {
        return 0.6f;
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

    // ----- CUSTOM GOALS -----

    /**
     * Flee from players – moves to a point away from the player, updates every 10 ticks.
     */
    class FleePlayerGoal extends Goal {
        private final AntEntity ant;
        private final float maxDistance;
        private final double slowSpeed;
        private final double fastSpeed;
        private PlayerEntity targetPlayer;
        private BlockPos fleeTarget;
        private int cooldown;

        public FleePlayerGoal(AntEntity ant, float maxDistance, double slowSpeed, double fastSpeed) {
            this.ant = ant;
            this.maxDistance = maxDistance;
            this.slowSpeed = slowSpeed;
            this.fastSpeed = fastSpeed;
        }

        @Override
        public boolean canStart() {
            targetPlayer = ant.getWorld().getClosestPlayer(ant, maxDistance);
            return targetPlayer != null;
        }

        @Override
        public boolean shouldContinue() {
            return targetPlayer != null && ant.squaredDistanceTo(targetPlayer) < (maxDistance * 1.5) * (maxDistance * 1.5);
        }

        @Override
        public void start() {
            cooldown = 0;
            updateFleeTarget();
        }

        @Override
        public void stop() {
            ant.getNavigation().stop();
            targetPlayer = null;
            fleeTarget = null;
        }

        @Override
        public void tick() {
            if (targetPlayer == null) return;

            if (cooldown-- <= 0) {
                cooldown = 10;
                updateFleeTarget();
            }

            if (fleeTarget != null) {
                double speed = ant.squaredDistanceTo(targetPlayer) < 16.0 ? fastSpeed : slowSpeed;
                ant.getNavigation().startMovingTo(fleeTarget.getX(), fleeTarget.getY(), fleeTarget.getZ(), speed);
            }
        }

        private void updateFleeTarget() {
            double dx = ant.getX() - targetPlayer.getX();
            double dz = ant.getZ() - targetPlayer.getZ();
            if (dx == 0 && dz == 0) {
                dx = ant.getRandom().nextDouble() - 0.5;
                dz = ant.getRandom().nextDouble() - 0.5;
            }
            double length = Math.sqrt(dx * dx + dz * dz);
            double dirX = dx / length;
            double dirZ = dz / length;

            // Random offset to avoid clumping
            double randomAngle = (ant.getRandom().nextDouble() - 0.5) * Math.PI / 2;
            double offsetX = Math.cos(randomAngle) * 3;
            double offsetZ = Math.sin(randomAngle) * 3;

            double fleeX = ant.getX() + dirX * 15 + offsetX;
            double fleeZ = ant.getZ() + dirZ * 15 + offsetZ;
            fleeTarget = new BlockPos((int) fleeX, (int) ant.getY(), (int) fleeZ);
        }
    }

    /**
     * Improved follow goal with hysteresis, timeout, and smooth movement.
     * Ensures all ants follow reliably without speed inconsistencies.
     */
    class ImprovedFollowLeaderGoal extends Goal {
        private final AntEntity ant;
        private final double speed;
        private final float minDistance;
        private final float maxDistance;
        private Vec3d targetPos;
        private int updateCooldown;
        private int stuckCounter;

        // Hysteresis values
        private static final float START_DISTANCE = 3.0f;   // start if > minDistance + START_DISTANCE
        private static final float STOP_DISTANCE = 2.0f;    // stop if < minDistance - STOP_DISTANCE

        public ImprovedFollowLeaderGoal(AntEntity ant, double speed, float minDistance, float maxDistance) {
            this.ant = ant;
            this.speed = speed;
            this.minDistance = minDistance;
            this.maxDistance = maxDistance;
        }

        @Override
        public boolean canStart() {
            AntEntity leader = ant.getLeader();
            if (leader == null) return false;
            double distSq = ant.squaredDistanceTo(leader);
            // Start if distance > minDistance + START_DISTANCE
            return distSq > (minDistance + START_DISTANCE) * (minDistance + START_DISTANCE);
        }

        @Override
        public boolean shouldContinue() {
            AntEntity leader = ant.getLeader();
            if (leader == null) return false;
            double distSq = ant.squaredDistanceTo(leader);
            // Continue if distance > minDistance - STOP_DISTANCE and still within maxDistance
            return distSq > (minDistance - STOP_DISTANCE) * (minDistance - STOP_DISTANCE) &&
                    distSq < maxDistance * maxDistance;
        }

        @Override
        public void start() {
            updateCooldown = 0;
            stuckCounter = 0;
            targetPos = null;
        }

        @Override
        public void stop() {
            ant.getNavigation().stop();
            targetPos = null;
        }

        @Override
        public void tick() {
            AntEntity leader = ant.getLeader();
            if (leader == null) return;

            double distSq = ant.squaredDistanceTo(leader);
            // If already within stop distance, stop moving
            if (distSq <= (minDistance - STOP_DISTANCE) * (minDistance - STOP_DISTANCE)) {
                ant.getNavigation().stop();
                return;
            }

            // Update target periodically or if stuck
            if (updateCooldown-- <= 0 || isStuck()) {
                updateCooldown = 8; // every 8 ticks
                computeNewTarget(leader);
                stuckCounter = 0;
            }

            if (targetPos != null) {
                // If we're very close to target, stop (prevents overshoot)
                if (ant.getPos().distanceTo(targetPos) < 0.5) {
                    ant.getNavigation().stop();
                    return;
                }
                boolean moving = ant.getNavigation().startMovingTo(targetPos.x, targetPos.y, targetPos.z, speed);
                if (!moving) {
                    // If pathfinding fails, force a new target next tick
                    updateCooldown = 0;
                }
                // Check if we're moving
                if (ant.getVelocity().lengthSquared() < 0.01) {
                    stuckCounter++;
                } else {
                    stuckCounter = 0;
                }
            }
        }

        private boolean isStuck() {
            return stuckCounter > 20; // not moved for 1 second
        }

        private void computeNewTarget(AntEntity leader) {
            // Random offset around leader
            double angle = ant.getRandom().nextDouble() * 2 * Math.PI;
            double radius = minDistance * 0.7; // slightly smaller to keep group tight
            double offsetX = Math.cos(angle) * radius;
            double offsetZ = Math.sin(angle) * radius;

            // Add separation from other ants (excluding leader)
            Vec3d separation = getSeparationForce(leader);
            double finalX = leader.getX() + offsetX + separation.x;
            double finalZ = leader.getZ() + offsetZ + separation.z;

            // Clamp to a maximum distance from leader (minDistance * 1.2)
            double maxOffset = minDistance * 1.2;
            double dx = finalX - leader.getX();
            double dz = finalZ - leader.getZ();
            double dist = Math.sqrt(dx * dx + dz * dz);
            if (dist > maxOffset) {
                dx = dx * maxOffset / dist;
                dz = dz * maxOffset / dist;
                finalX = leader.getX() + dx;
                finalZ = leader.getZ() + dz;
            }

            targetPos = new Vec3d(finalX, leader.getY(), finalZ);
        }

        private Vec3d getSeparationForce(AntEntity leader) {
            Vec3d force = Vec3d.ZERO;
            double separationRadius = 1.5;
            double weight = 1.0;

            List<AntEntity> nearby = ant.getWorld().getEntitiesByClass(
                    AntEntity.class,
                    ant.getBoundingBox().expand(separationRadius),
                    other -> other != ant && other != leader
            );

            for (AntEntity other : nearby) {
                double dx = ant.getX() - other.getX();
                double dz = ant.getZ() - other.getZ();
                double distanceSq = dx * dx + dz * dz;
                if (distanceSq < separationRadius * separationRadius) {
                    double distance = Math.sqrt(distanceSq);
                    double strength = weight * (1.0 - distance / separationRadius);
                    dx /= distance;
                    dz /= distance;
                    force = force.add(dx * strength, 0, dz * strength);
                }
            }

            double mag = force.length();
            if (mag > 2.0) force = force.multiply(2.0 / mag);
            return force;
        }
    }
}