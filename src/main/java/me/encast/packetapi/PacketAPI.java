package me.encast.packetapi;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class PacketAPI extends JavaPlugin {

    /**
     * The instance of PacketAPI.
     */
    @Getter
    private static PacketAPI instance;

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {
    }
}
