package com.jelly.farmhelper.macros;

import com.jelly.farmhelper.config.enums.CropEnum;
import com.jelly.farmhelper.config.interfaces.FarmConfig;
import com.jelly.farmhelper.gui.MenuGUI;
import com.jelly.farmhelper.utils.InventoryUtils;
import com.jelly.farmhelper.utils.KeyBindUtils;
import com.jelly.farmhelper.utils.LogUtils;
import com.sun.scenario.effect.Crop;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class MacroHandler {

    public static Macro currentMacro;
    public static boolean on;
    public static SugarcaneMacro sugarcaneMacro = new SugarcaneMacro();
    public static CropMacro cropMacro = new CropMacro();

    public static long startTime = 0;
    public static int startCounter = 0;

    @SubscribeEvent
    public void onChatMessageReceived(ClientChatReceivedEvent e){
        if(on)
            currentMacro.onChatMessageReceived(e.message.getUnformattedText());
    }

    @SubscribeEvent
    public void onRender(RenderWorldLastEvent event){
        if(on)
            currentMacro.onRender();
    }
    @SubscribeEvent
    public void OnKeyPress(InputEvent.KeyInputEvent event) {
        if (KeyBindUtils.customKeyBinds[1].isPressed()) {
            if(FarmConfig.cropType == CropEnum.SUGARCANE)
                currentMacro = sugarcaneMacro;
            else
                currentMacro = cropMacro;

            on = !on;
            if(on) {
                LogUtils.scriptLog("Starting script");
                startTime = System.currentTimeMillis();
                startCounter = InventoryUtils.getCounter();
            } else
                LogUtils.scriptLog("Disabling script");
            currentMacro.toggle();
        }

    }
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public final void tick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;
        if (on)
            currentMacro.onTick();
    }
    public static void disableCurrentMacro(){
        on = false;
        if(currentMacro.enabled)
            currentMacro.toggle();
    }
    public static void enableCurrentMacro(){
        on = true;
        if(!currentMacro.enabled)
            currentMacro.toggle();
    }

}
