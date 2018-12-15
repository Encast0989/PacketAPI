### PacketAPI
*No longer maintained!* 

A simple Minecraft outbound packet API reducing the need for NMS.

This project is a concept, and should not be used in a production environment.

## Usage
Below are two different ways of creating a new packet. The first of which uses one of the already built in
packet constants, and the second creating your own packet. 
*Not all packets available in PacketOutType! This means that the second of the two ways will be most used.

Creating a predefined packet:
```java
PacketContainer container = new PacketContainer(PacketOutType.TITLE);
container.writeObject(index, object);
...
```

Creating your own packet:
```java
PacketContainer container = new PacketContainer("PacketPlayOutTitle", new DefaultObjectMapping());
container.writeObject(index, object);
...
```

# DefaultObjectMapping
As used above, DefaultObjectMapping stores all values required to construct a new instance of a packet.

Example usage:
```java
DefaultObjectMapping mapping = new DefaultObjectMapping();
mapping.writeObject(String.class, "value");
mapping.writeObject(0, int.class, 1); // Sets this as the first object in the constructor
...
```

# Constructing and Sending
Constructing and sending a PacketContainer takes no time at all, below is an example of sending a packet.
```java
PacketContainer container = new PacketContainer(PacketOutType.TITLE);
...

PreparedPacket packet = new PreparedPacket(container, true); // new PreparedPacket(container) can be used as an alternative if build = true
packet.build(); // If false is specified in the contructor for build, this method needs to be called

// Sending the packet to a player (every player online in this case)
Bukkit.getServer().getOnlinePlayers().forEach(player -> packet.send(player));
```

## Downfalls
As this project was mainly an experiment, it does not strive to remove every possible net.minecraft.server import,
and requires you to import many nms classes for packets that have fields that contain more than just the primitive
data types. This project also cannot be considered complete—there are many features that could be implemented to further
enhance the API.
