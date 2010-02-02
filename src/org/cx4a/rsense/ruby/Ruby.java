package org.cx4a.rsense.ruby;

import java.util.Map;
import java.util.HashMap;

public class Ruby {
    private Context context;
    private RubyClass objectClass, moduleClass, classClass,
        numericClass, integerClass, fixnumClass, bignumClass,
        floatClass, stringClass, symbolClass,
        booleanClass, trueClass, falseClass, nilClass,
        arrayClass, hashClass, rangeClass;
    private IRubyObject nilObject, trueObject, falseObject;
    private IRubyObject topSelf;
    private Map<String, IRubyObject> globalVars;

    public Ruby() {
        objectClass = RubyClass.newBootClass(this, "Object", null);
        moduleClass = RubyClass.newBootClass(this, "Module", objectClass);
        classClass = RubyClass.newBootClass(this, "Class", moduleClass);

        objectClass.setMetaClass(classClass);
        moduleClass.setMetaClass(classClass);
        classClass.setMetaClass(classClass);

        RubyClass metaClass;
        metaClass = objectClass.makeMetaClass(classClass);
        metaClass = moduleClass.makeMetaClass(metaClass);
        metaClass = classClass.makeMetaClass(metaClass);

        numericClass = RubyClass.newClass(this, "Number", objectClass);
        integerClass = RubyClass.newClass(this, "Integer", numericClass);
        fixnumClass = RubyClass.newClass(this, "Fixnum", integerClass);
        bignumClass = RubyClass.newClass(this, "Bignum", integerClass);
        floatClass = RubyClass.newClass(this, "Float", numericClass);
        stringClass = RubyClass.newClass(this, "String", objectClass);
        symbolClass = RubyClass.newClass(this, "Symbol", objectClass);
        nilClass = RubyClass.newClass(this, "NilClass", objectClass);
        booleanClass = RubyClass.newClass(this, "Boolean", objectClass);
        trueClass = RubyClass.newClass(this, "TrueClass", booleanClass);
        falseClass = RubyClass.newClass(this, "FalseClass", booleanClass);
        arrayClass = RubyClass.newClass(this, "Array", objectClass);
        hashClass = RubyClass.newClass(this, "Hash", objectClass);
        rangeClass = RubyClass.newClass(this, "Range", objectClass);

        objectClass.setConstant("Object", objectClass);
        objectClass.setConstant("Module", moduleClass);
        objectClass.setConstant("Class", classClass);
        objectClass.setConstant("Integer", integerClass);
        objectClass.setConstant("Fixnum", fixnumClass);
        objectClass.setConstant("Bignum", bignumClass);
        objectClass.setConstant("Float", floatClass);
        objectClass.setConstant("String", stringClass);
        objectClass.setConstant("Symbol", symbolClass);
        objectClass.setConstant("NilClass", nilClass);
        objectClass.setConstant("Boolean", booleanClass);
        objectClass.setConstant("TrueClass", trueClass);
        objectClass.setConstant("FalseClass", falseClass);
        objectClass.setConstant("Array", arrayClass);
        objectClass.setConstant("Hash", hashClass);
        objectClass.setConstant("Range", rangeClass);

        nilObject = new SpecialObject(this, nilClass, "nil");
        trueObject = new SpecialObject(this, trueClass, "true");
        falseObject = new SpecialObject(this, falseClass, "false");

        topSelf = new RubyObject(this, objectClass);

        globalVars = new HashMap<String, IRubyObject>();
        context = new Context(this);
    }

    public IRubyObject getGlobalVar(String name) {
        return globalVars.get(name);
    }

    public void setGlobalVar(String name, IRubyObject value) {
        globalVars.put(name, value);
    }

    public Context getContext() {
        return context;
    }

    public RubyClass getObject() {
        return objectClass;
    }

    public RubyClass getModule() {
        return moduleClass;
    }

    public RubyClass getClassClass() {
        return classClass;
    }

    public RubyClass getNumeric() {
        return numericClass;
    }

    public RubyClass getInteger() {
        return integerClass;
    }

    public RubyClass getFixnum() {
        return fixnumClass;
    }

    public RubyClass getBignum() {
        return bignumClass;
    }

    public RubyClass getFloat() {
        return floatClass;
    }

    public RubyClass getString() {
        return stringClass;
    }

    public RubyClass getSymbol() {
        return symbolClass;
    }

    public RubyClass getArray() {
        return arrayClass;
    }

    public RubyClass getHash() {
        return hashClass;
    }

    public RubyClass getRange() {
        return rangeClass;
    }

    public RubyClass getNilClass() {
        return nilClass;
    }

    public RubyClass getBoolean() {
        return booleanClass;
    }

    public RubyClass getTrueClass() {
        return trueClass;
    }

    public RubyClass getFalseClass() {
        return falseClass;
    }

    public IRubyObject getNil() {
        return nilObject;
    }

    public IRubyObject getTrue() {
        return trueObject;
    }

    public IRubyObject getFalse() {
        return falseObject;
    }

    public IRubyObject getTopSelf() {
        return topSelf;
    }
}