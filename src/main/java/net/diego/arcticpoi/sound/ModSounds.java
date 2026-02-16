package net.diego.arcticpoi.sound;

import net.diego.arcticpoi.ArcticPoi;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {

    // Create the DeferredRegister for sound events
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ArcticPoi.MOD_ID);

    // Register your custom radio sound
    public static final RegistryObject<SoundEvent> RADIO_SOUND =
            SOUND_EVENTS.register("radio_sound",
                    () -> SoundEvent.createVariableRangeEvent(
                            new ResourceLocation(ArcticPoi.MOD_ID, "radio_sound")
                    ));
}
