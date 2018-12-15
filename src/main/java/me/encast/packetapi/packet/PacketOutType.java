package me.encast.packetapi.packet;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * An enumerator containing some basic implementations of Minecraft's
 * packets.
 */
@AllArgsConstructor
@Getter
public enum PacketOutType implements PacketType {

    TITLE("PacketPlayOutTitle", new DefaultObjectMapping()),
    CHAT("PacketPlayOutChat", new DefaultObjectMapping()),
    ANIMATION("PacketPlayOutAnimation", new DefaultObjectMapping()),
    UPDATE_SIGN("PacketPlayOutUpdateSign", new DefaultObjectMapping());

    /**
     * The class name of the packet.
     */
    private String className;
    /**
     * The default object values required to construct the packet
     * initially.
     */
    private DefaultObjectMapping defaultObjectMapping;
}
