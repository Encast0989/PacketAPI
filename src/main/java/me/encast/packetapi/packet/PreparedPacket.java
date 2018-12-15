package me.encast.packetapi.packet;

import lombok.AccessLevel;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * This class is used to construct the net.minecraft.server packet
 * and send the built packet to a {@link Player}.
 */
@Getter
public class PreparedPacket {

    /**
     * The {@link PacketContainer} containg all information
     * required to construct the packet.
     */
    private PacketContainer container;
    /**
     * The built net.minecraft.server packet.
     */
    private Object packet;

    /**
     * The version of the server. Formatting for version follows
     * similar to the package version in the Minecraft server code.
     * Ex. 1_version_Rsubversion, 1_8_R3
     */
    private String version;

    /**
     * Constant for the Minecraft server package.
     */
    @Getter(AccessLevel.NONE)
    private final String NMS_PACKAGE = "net.minecraft.server.";

    /**
     * Constructs a new instance of PreparedPacket using
     * {@link PacketContainer}. When this constructor is used,
     * {@link PreparedPacket#build()} is called as well.
     *
     * @param container The {@link PacketContainer} to construct the
     *                  PreparedPacket from.
     */
    public PreparedPacket(PacketContainer container) {
        this(container, true);
    }

    /**
     * Constructs a new instance of PreparedPacket using
     * {@link PacketContainer}. This constructor allows the option
     * of whether to call {@link PreparedPacket#build()} as well.
     *
     * @param container The {@link PacketContainer} to construct the
     *                  PreparedPcaket from.
     * @param build Whether to construct the Minecraft packet by calling
     *              {@link PreparedPacket#build()}.
     */
    public PreparedPacket(PacketContainer container, boolean build) {
        this.container = container;
        this.version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

        if(build)
            build();
    }

    /**
     * Constructs the Minecraft packet using values found in
     * {@link PacketContainer}. As all operations in this method are
     * reflection based, repetitive calls could lead to the decrease
     * in performance.
     */
    public void build() {
        try {
            Class<?> packet = Class.forName(NMS_PACKAGE + version + "." + container.getPacketClass());
            Constructor<?> constructor = packet.getConstructor(container.getDefaultObjectMapping().getTypeArray());
            Object object = constructor.newInstance(container.getDefaultObjectMapping().getValueArray());
            Field[] fields = object.getClass().getDeclaredFields();
            for(Map.Entry<Integer, Object> entry : container.getObjects().entrySet()) {
                Field f = fields[entry.getKey()];
                f.setAccessible(true);
                f.set(object, entry.getValue());
            }

            this.packet = object;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends the packet to the specified {@link Player}.
     *
     * @param player The {@link Player} to send the packet to.
     */
    public void send(Player player) {
        if(packet != null) {
            try {
                Object handle = player.getClass().getMethod("getHandle").invoke(player);
                Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
                playerConnection.getClass()
                        .getMethod("sendPacket", Class.forName(NMS_PACKAGE + version + ".Packet"))
                        .invoke(playerConnection, packet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else throw new NullPointerException("Packet cannot be null (was PreparedPacket#build called?)");
    }
}
