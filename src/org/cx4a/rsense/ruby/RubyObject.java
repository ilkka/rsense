package org.cx4a.rsense.ruby;

import java.util.Map;
import java.util.HashMap;

public class RubyObject implements IRubyObject {
    protected Ruby runtime;
    protected RubyClass metaClass;
    private Map<String, IRubyObject> instVars;
    private Object tag;

    protected RubyObject(Ruby runtime) {
        this(runtime, null);
    }

    public RubyObject(Ruby runtime, RubyClass metaClass) {
        this.runtime = runtime;
        this.metaClass = metaClass;
    }

    public Ruby getRuntime() {
        return runtime;
    }

    public RubyClass getMetaClass() {
        return metaClass;
    }
    
    public void setMetaClass(RubyClass metaClass) {
        this.metaClass = metaClass;
    }

    public RubyClass getSingletonClass() {
        RubyClass klass;
        if (getMetaClass().isSingleton() && ((MetaClass) getMetaClass()).getAttached() == this) {
            klass = getMetaClass();
        } else {
            klass = makeMetaClass(getMetaClass());
        }
        return klass;
    }

    public RubyClass makeMetaClass(RubyClass superClass) {
        MetaClass klass = new MetaClass(runtime, superClass);
        setMetaClass(klass);

        klass.setAttached(this);
        klass.setMetaClass(superClass.getRealClass().getMetaClass());

        superClass.addSubclass(klass);

        return klass;
    }

    public IRubyObject getInstVar(String name) {
        return instVars == null ? null : instVars.get(name);
    }

    public void setInstVar(String name, IRubyObject value) {
        if (instVars == null) {
            instVars = new HashMap<String, IRubyObject>();
        }
        instVars.put(name, value);
    }

    public boolean isNil() {
        return this == getRuntime().getNil();
    }

    public boolean isInstanceOf(RubyClass klass) {
        // special handling for boolean
        if (klass == runtime.getBoolean()
            && metaClass.getSuperClass() == klass) {
            return true;
        }
        return metaClass == klass;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "<obj:" + metaClass.toString() + ">";
    }
}