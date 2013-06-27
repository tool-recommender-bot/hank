/**
 * Autogenerated by Thrift Compiler (0.9.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.liveramp.hank.generated;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HostMetadata implements org.apache.thrift.TBase<HostMetadata, HostMetadata._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("HostMetadata");

  private static final org.apache.thrift.protocol.TField FLAGS_FIELD_DESC = new org.apache.thrift.protocol.TField("flags", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField HOST_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("host_name", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField PORT_NUMBER_FIELD_DESC = new org.apache.thrift.protocol.TField("port_number", org.apache.thrift.protocol.TType.I32, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new HostMetadataStandardSchemeFactory());
    schemes.put(TupleScheme.class, new HostMetadataTupleSchemeFactory());
  }

  public String flags; // required
  public String host_name; // required
  public int port_number; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    FLAGS((short)1, "flags"),
    HOST_NAME((short)2, "host_name"),
    PORT_NUMBER((short)3, "port_number");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // FLAGS
          return FLAGS;
        case 2: // HOST_NAME
          return HOST_NAME;
        case 3: // PORT_NUMBER
          return PORT_NUMBER;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __PORT_NUMBER_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.FLAGS, new org.apache.thrift.meta_data.FieldMetaData("flags", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.HOST_NAME, new org.apache.thrift.meta_data.FieldMetaData("host_name", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PORT_NUMBER, new org.apache.thrift.meta_data.FieldMetaData("port_number", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(HostMetadata.class, metaDataMap);
  }

  public HostMetadata() {
  }

  public HostMetadata(
    String flags,
    String host_name,
    int port_number)
  {
    this();
    this.flags = flags;
    this.host_name = host_name;
    this.port_number = port_number;
    set_port_number_isSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public HostMetadata(HostMetadata other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.is_set_flags()) {
      this.flags = other.flags;
    }
    if (other.is_set_host_name()) {
      this.host_name = other.host_name;
    }
    this.port_number = other.port_number;
  }

  public HostMetadata deepCopy() {
    return new HostMetadata(this);
  }

  @Override
  public void clear() {
    this.flags = null;
    this.host_name = null;
    set_port_number_isSet(false);
    this.port_number = 0;
  }

  public String get_flags() {
    return this.flags;
  }

  public HostMetadata set_flags(String flags) {
    this.flags = flags;
    return this;
  }

  public void unset_flags() {
    this.flags = null;
  }

  /** Returns true if field flags is set (has been assigned a value) and false otherwise */
  public boolean is_set_flags() {
    return this.flags != null;
  }

  public void set_flags_isSet(boolean value) {
    if (!value) {
      this.flags = null;
    }
  }

  public String get_host_name() {
    return this.host_name;
  }

  public HostMetadata set_host_name(String host_name) {
    this.host_name = host_name;
    return this;
  }

  public void unset_host_name() {
    this.host_name = null;
  }

  /** Returns true if field host_name is set (has been assigned a value) and false otherwise */
  public boolean is_set_host_name() {
    return this.host_name != null;
  }

  public void set_host_name_isSet(boolean value) {
    if (!value) {
      this.host_name = null;
    }
  }

  public int get_port_number() {
    return this.port_number;
  }

  public HostMetadata set_port_number(int port_number) {
    this.port_number = port_number;
    set_port_number_isSet(true);
    return this;
  }

  public void unset_port_number() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __PORT_NUMBER_ISSET_ID);
  }

  /** Returns true if field port_number is set (has been assigned a value) and false otherwise */
  public boolean is_set_port_number() {
    return EncodingUtils.testBit(__isset_bitfield, __PORT_NUMBER_ISSET_ID);
  }

  public void set_port_number_isSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __PORT_NUMBER_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case FLAGS:
      if (value == null) {
        unset_flags();
      } else {
        set_flags((String)value);
      }
      break;

    case HOST_NAME:
      if (value == null) {
        unset_host_name();
      } else {
        set_host_name((String)value);
      }
      break;

    case PORT_NUMBER:
      if (value == null) {
        unset_port_number();
      } else {
        set_port_number((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case FLAGS:
      return get_flags();

    case HOST_NAME:
      return get_host_name();

    case PORT_NUMBER:
      return Integer.valueOf(get_port_number());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case FLAGS:
      return is_set_flags();
    case HOST_NAME:
      return is_set_host_name();
    case PORT_NUMBER:
      return is_set_port_number();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof HostMetadata)
      return this.equals((HostMetadata)that);
    return false;
  }

  public boolean equals(HostMetadata that) {
    if (that == null)
      return false;

    boolean this_present_flags = true && this.is_set_flags();
    boolean that_present_flags = true && that.is_set_flags();
    if (this_present_flags || that_present_flags) {
      if (!(this_present_flags && that_present_flags))
        return false;
      if (!this.flags.equals(that.flags))
        return false;
    }

    boolean this_present_host_name = true && this.is_set_host_name();
    boolean that_present_host_name = true && that.is_set_host_name();
    if (this_present_host_name || that_present_host_name) {
      if (!(this_present_host_name && that_present_host_name))
        return false;
      if (!this.host_name.equals(that.host_name))
        return false;
    }

    boolean this_present_port_number = true;
    boolean that_present_port_number = true;
    if (this_present_port_number || that_present_port_number) {
      if (!(this_present_port_number && that_present_port_number))
        return false;
      if (this.port_number != that.port_number)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    HashCodeBuilder builder = new HashCodeBuilder();

    boolean present_flags = true && (is_set_flags());
    builder.append(present_flags);
    if (present_flags)
      builder.append(flags);

    boolean present_host_name = true && (is_set_host_name());
    builder.append(present_host_name);
    if (present_host_name)
      builder.append(host_name);

    boolean present_port_number = true;
    builder.append(present_port_number);
    if (present_port_number)
      builder.append(port_number);

    return builder.toHashCode();
  }

  public int compareTo(HostMetadata other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    HostMetadata typedOther = (HostMetadata)other;

    lastComparison = Boolean.valueOf(is_set_flags()).compareTo(typedOther.is_set_flags());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (is_set_flags()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.flags, typedOther.flags);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(is_set_host_name()).compareTo(typedOther.is_set_host_name());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (is_set_host_name()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.host_name, typedOther.host_name);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(is_set_port_number()).compareTo(typedOther.is_set_port_number());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (is_set_port_number()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.port_number, typedOther.port_number);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("HostMetadata(");
    boolean first = true;

    sb.append("flags:");
    if (this.flags == null) {
      sb.append("null");
    } else {
      sb.append(this.flags);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("host_name:");
    if (this.host_name == null) {
      sb.append("null");
    } else {
      sb.append(this.host_name);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("port_number:");
    sb.append(this.port_number);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (flags == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'flags' was not present! Struct: " + toString());
    }
    if (host_name == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'host_name' was not present! Struct: " + toString());
    }
    // alas, we cannot check 'port_number' because it's a primitive and you chose the non-beans generator.
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class HostMetadataStandardSchemeFactory implements SchemeFactory {
    public HostMetadataStandardScheme getScheme() {
      return new HostMetadataStandardScheme();
    }
  }

  private static class HostMetadataStandardScheme extends StandardScheme<HostMetadata> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, HostMetadata struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // FLAGS
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.flags = iprot.readString();
              struct.set_flags_isSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // HOST_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.host_name = iprot.readString();
              struct.set_host_name_isSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // PORT_NUMBER
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.port_number = iprot.readI32();
              struct.set_port_number_isSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      if (!struct.is_set_port_number()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'port_number' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, HostMetadata struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.flags != null) {
        oprot.writeFieldBegin(FLAGS_FIELD_DESC);
        oprot.writeString(struct.flags);
        oprot.writeFieldEnd();
      }
      if (struct.host_name != null) {
        oprot.writeFieldBegin(HOST_NAME_FIELD_DESC);
        oprot.writeString(struct.host_name);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(PORT_NUMBER_FIELD_DESC);
      oprot.writeI32(struct.port_number);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class HostMetadataTupleSchemeFactory implements SchemeFactory {
    public HostMetadataTupleScheme getScheme() {
      return new HostMetadataTupleScheme();
    }
  }

  private static class HostMetadataTupleScheme extends TupleScheme<HostMetadata> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, HostMetadata struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeString(struct.flags);
      oprot.writeString(struct.host_name);
      oprot.writeI32(struct.port_number);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, HostMetadata struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.flags = iprot.readString();
      struct.set_flags_isSet(true);
      struct.host_name = iprot.readString();
      struct.set_host_name_isSet(true);
      struct.port_number = iprot.readI32();
      struct.set_port_number_isSet(true);
    }
  }

}
