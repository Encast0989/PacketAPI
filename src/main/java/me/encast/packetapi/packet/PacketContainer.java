package me.encast.packetapi.packet;

import com.google.common.collect.Maps;
import lombok.Getter;

import java.util.Map;

/**
 * Defines a packet containing all data required to build the
 * corresponding net.minecraft.server Packet.
 */
@Getter
public class PacketContainer {

    /**
     * The name of the net.minecraft.server packet class, excluding the
     * package.
     */
    private String packetClass;
    /**
     * The default object values required to construct the packet
     * initially.
     */
    private DefaultObjectMapping defaultObjectMapping;
    /**
     * Map keyed by an integer corresponding to the field index of
     * the packet, with object values corresponding to the value to
     * be set.
     */
    private Map<Integer, Object> objects = Maps.newConcurrentMap();

    /**
     * Constructs a new PacketContainer instance using the specified
     * {@link PacketType}.
     *
     * @param type The {@link PacketType} to construct the PacketContainer from.
     */
    public PacketContainer(PacketType type) {
        this.packetClass = type.getClassName();
        this.defaultObjectMapping = type.getDefaultObjectMapping();
    }

    /**
     * Constructs a new PacketContainer instance using non-predefined
     * values.
     *
     * @param packetClass The name of the net.mineraft.server packet class,
     *                    excluding the package.
     * @param defaultObjectMapping The default object values required to construct
     *                             the packet.
     */
    public PacketContainer(String packetClass, DefaultObjectMapping defaultObjectMapping) {
        this.packetClass = packetClass;
        this.defaultObjectMapping = defaultObjectMapping;
    }

    /**
     * Writes a new object value to use when building the packet.
     *
     * @param index The index of the field.
     * @param object The value to set the field to.
     * @return this
     */
    public PacketContainer writeObject(int index, Object object) {
        objects.put(index, object);
        return this;
    }
}
