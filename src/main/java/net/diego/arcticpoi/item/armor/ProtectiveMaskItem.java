package net.diego.arcticpoi.item.armor;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;
import net.diego.arcticpoi.client.renderer.armor.ProtectiveMask3DRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * GeckoLib 3D animated protective mask with special effects based on protection level.
 * Supports various mask types: surgical, respirator, half-face, full-face, gas mask, PAPR.
 */
public class ProtectiveMaskItem extends ArmorItem implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final String modelName;
    private final ProtectionLevel protectionLevel;

    public enum ProtectionLevel {
        NONE,           // Surgical mask - no special effects
        MINIMAL,        // Respirator - reduces poison duration
        MODERATE,       // Half-face - blocks poison, reduces other effects
        HIGH,           // Full-face - blocks poison, blindness, nausea
        COMPLETE,       // Gasmask - blocks all airborne effects + wither + enhanced buffs
        POWERED         // PAPR - complete protection + regeneration
    }

    public ProtectiveMaskItem(ArmorMaterial material, Type type, Properties properties, String modelName, ProtectionLevel protectionLevel) {
        super(material, type, properties);
        this.modelName = modelName;
        this.protectionLevel = protectionLevel;
    }

    public String getModelName() {
        return modelName;
    }

    public ProtectionLevel getProtectionLevel() {
        return protectionLevel;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);

        if (!(entity instanceof LivingEntity livingEntity)) return;
        if (level.isClientSide()) return;

        // Only apply effects if worn in helmet slot
        ItemStack helmet = livingEntity.getItemBySlot(EquipmentSlot.HEAD);
        if (helmet.isEmpty() || helmet.getItem() != this) return;

        applyProtectionEffects(livingEntity);
    }

    private void applyProtectionEffects(LivingEntity entity) {
        List<MobEffect> effectsToRemove = new ArrayList<>();

        switch (protectionLevel) {
            case POWERED:
                // PAPR System: Complete protection + regeneration
                effectsToRemove.addAll(getAirborneEffects());
                // Add regeneration effect (simulates oxygen supply)
                if (entity.tickCount % 100 == 0) { // Every 5 seconds
                    entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0, false, false));
                }
                break;

            case COMPLETE:
                // Gasmask: Complete airborne immunity + enhanced military-grade buffs
                effectsToRemove.addAll(getAirborneEffects());

                // Enhanced gas mask buffs (military-grade perks)
                if (entity.tickCount % 200 == 0) { // Every 10 seconds
                    // Damage resistance buff (simulates stress stabilization)
                    entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 220, 0, true, false));
                }

                // Reduced airborne debuff durations passively (35% reduction)
                if (entity.tickCount % 20 == 0) {
                    reduceEffectDuration(entity, MobEffects.MOVEMENT_SLOWDOWN, 7);
                    reduceEffectDuration(entity, MobEffects.DIG_SLOWDOWN, 7);
                    reduceEffectDuration(entity, MobEffects.WEAKNESS, 7);
                }
                break;

            case HIGH:
                // Full-face: Blocks poison, blindness, nausea (protects eyes too)
                effectsToRemove.add(MobEffects.POISON);
                effectsToRemove.add(MobEffects.BLINDNESS);
                effectsToRemove.add(MobEffects.CONFUSION);
                effectsToRemove.add(MobEffects.HUNGER);
                break;

            case MODERATE:
                // Half-face: Blocks poison, reduces other airborne effects
                effectsToRemove.add(MobEffects.POISON);
                // Reduce duration of other effects by removing them periodically
                if (entity.tickCount % 20 == 0) {
                    reduceEffectDuration(entity, MobEffects.WITHER, 10);
                    reduceEffectDuration(entity, MobEffects.HUNGER, 10);
                }
                break;

            case MINIMAL:
                // Respirator: Reduces poison duration only
                if (entity.tickCount % 20 == 0) {
                    reduceEffectDuration(entity, MobEffects.POISON, 20);
                }
                break;

            case NONE:
            default:
                // Surgical mask: No special effects
                break;
        }

        // Remove blocked effects
        for (MobEffect effect : effectsToRemove) {
            if (entity.hasEffect(effect)) {
                entity.removeEffect(effect);
            }
        }
    }

    private List<MobEffect> getAirborneEffects() {
        List<MobEffect> effects = new ArrayList<>();
        effects.add(MobEffects.POISON);
        effects.add(MobEffects.BLINDNESS);
        effects.add(MobEffects.CONFUSION);
        effects.add(MobEffects.HUNGER);
        effects.add(MobEffects.WEAKNESS);
        effects.add(MobEffects.WITHER);
        return effects;
    }

    private void reduceEffectDuration(LivingEntity entity, MobEffect effect, int reduction) {
        MobEffectInstance instance = entity.getEffect(effect);
        if (instance != null) {
            int newDuration = Math.max(0, instance.getDuration() - reduction);
            // Must remove first, then re-add with lower duration
            entity.removeEffect(effect);
            if (newDuration > 0) {
                entity.addEffect(new MobEffectInstance(effect, newDuration, instance.getAmplifier(),
                        instance.isAmbient(), instance.isVisible()));
            }
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        // Add animation controllers here if needed
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private ProtectiveMask3DRenderer renderer;

            @Override
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack,
                                                          EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if (renderer == null) {
                    renderer = new ProtectiveMask3DRenderer(modelName);
                }
                renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
                return renderer;
            }
        });
    }
}

