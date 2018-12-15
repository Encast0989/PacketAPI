package me.encast.packetapi.packet;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

/**
 * Data class that holds all default object values for a specified
 * packet. These values correspond to the required values in the
 * constructor of the packet.
 */
public class DefaultObjectMapping {

    /**
     * Contains all {@link FieldData} corresponding to the values
     * that will be set when the packet is being constructed.
     */
    @Getter
    private List<FieldData> fieldData = Lists.newArrayList();

    public DefaultObjectMapping() {
    }

    /**
     * @return A {@link Class} array containing all class types found
     * in {@link DefaultObjectMapping#getFieldData()}.
     */
    public Class<?>[] getTypeArray() {
        Class<?>[] typeArray = new Class<?>[fieldData.size()];
        for(int i = 0; i < fieldData.size(); i++) {
            typeArray[i] = fieldData.get(i).getType();
        }

        return typeArray;
    }

    /**
     * @return A {@link Object} array containing all objects found
     * in {@link DefaultObjectMapping#getFieldData()}.
     */
    public Object[] getValueArray() {
        Object[] valueArray = new Object[fieldData.size()];
        for(int i = 0; i < fieldData.size(); i++) {
            valueArray[i] = fieldData.get(i).getValue();
        }

        return valueArray;
    }

    /**
     * Adds a new default field value.
     *
     * @param type The {@link Class} type of the value.
     * @param value The value to set.
     * @return this
     */
    public DefaultObjectMapping addDefault(Class<?> type, Object value) {
        fieldData.add(new FieldData(type, value));
        return this;
    }

    /**
     * Adds a new default field value at the specified index. All
     * following values will be translated to their previous index + 1.
     *
     * @param index The index to insert the value into.
     * @param type The {@link Class} type of the value.
     * @param value The value to set.
     * @return this
     */
    public DefaultObjectMapping addDefault(int index, Class<?> type, Object value) {
        fieldData.add(index, new FieldData(type, value));
        return this;
    }

    /**
     * Data class used to store class types and values.
     */
    @AllArgsConstructor
    @Data
    private class FieldData {

        private Class<?> type;
        private Object value;
    }
}
