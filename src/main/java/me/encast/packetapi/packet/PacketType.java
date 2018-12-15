package me.encast.packetapi.packet;

/**
 * Defines a simple interface used to get key information to
 * construct a packet.
 */
public interface PacketType {

    /**
     * Returns the class name of the Minecraft packet. This
     * name only contains the name of the class, and does not
     * include the package it is contained in.
     *
     * @return The class name of the Minecraft packet.
     */
    String getClassName();

    /**
     * @return The {@link DefaultObjectMapping} used to construct
     * the packet.
     */
    DefaultObjectMapping getDefaultObjectMapping();
}
