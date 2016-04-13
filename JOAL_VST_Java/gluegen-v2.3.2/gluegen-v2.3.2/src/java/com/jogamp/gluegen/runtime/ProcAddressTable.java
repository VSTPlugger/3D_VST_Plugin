/*
 * Copyright (c) 2003-2005 Sun Microsystems, Inc. All Rights Reserved.
 * Copyright (c) 2013 JogAmp Community. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * - Redistribution of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * - Redistribution in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * Neither the name of Sun Microsystems, Inc. or the names of
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES,
 * INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN
 * MICROSYSTEMS, INC. ("SUN") AND ITS LICENSORS SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR
 * ITS LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR
 * DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE
 * DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY,
 * ARISING OUT OF THE USE OF OR INABILITY TO USE THIS SOFTWARE, EVEN IF
 * SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * You acknowledge that this software is not designed or intended for use
 * in the design, construction, operation or maintenance of any nuclear
 * facility.
 */
package com.jogamp.gluegen.runtime;

import com.jogamp.common.os.DynamicLookupHelper;
import com.jogamp.common.util.SecurityUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Superclass for all generated ProcAddressTables.
 *
 * A ProcAddressTable is a cache of pointers to the dynamically-linkable C
 * functions this autogenerated Java binding has exposed. Some
 * libraries such as OpenGL, OpenAL and others define function pointer
 * signatures rather than statically linkable entry points for the
 * purposes of being able to query at run-time whether a particular
 * extension is available. This table acts as a cache of these
 * function pointers. Each function pointer is typically looked up at
 * run-time by a platform-dependent mechanism such as dlsym(),
 * wgl/glXGetProcAddress(), or alGetProcAddress(). If the field containing the function
 * pointer is 0, the function is considered to be unavailable and can
 * not be called.
 *
 * @author Kenneth Russel
 * @author Michael Bien
 * @author Sven Gothel
 *
 * @see FunctionAddressResolver
 * @see DynamicLookupHelper
 */
public abstract class ProcAddressTable {

    private static final String PROCADDRESS_VAR_PREFIX = "_addressof_";
    private static final int PROCADDRESS_VAR_PREFIX_LEN = PROCADDRESS_VAR_PREFIX.length();

    protected static boolean DEBUG;
    protected static String DEBUG_PREFIX;
    protected static int debugNum;

    private final FunctionAddressResolver resolver;

    static {
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            @Override
            public Object run() {
                DEBUG = (System.getProperty("jogamp.debug.ProcAddressHelper") != null);
                if (DEBUG) {
                    DEBUG_PREFIX = System.getProperty("jogamp.debug.ProcAddressHelper.prefix");
                }
                return null;
            }
        });
    }

    public ProcAddressTable() {
        this(new One2OneResolver());
    }

    public ProcAddressTable(final FunctionAddressResolver resolver) {
        this.resolver = resolver;
    }


    /**
     * Resets the complete table.
     * <p>
     * If a {@link SecurityManager} is installed, user needs link permissions
     * for <b>all</b> libraries, i.e. for <code>new RuntimePermission("loadLibrary.*");</code>!
     * </p>
     * @throws SecurityException if user is not granted access for all libraries.
     */
    public void reset(final DynamicLookupHelper lookup) throws SecurityException, RuntimeException {
        if(null==lookup) {
            throw new RuntimeException("Passed null DynamicLookupHelper");
        }

        final Field[] fields = getClass().getDeclaredFields();

        final PrintStream dout;
        if (DEBUG) {
            dout = getDebugOutStream();
            dout.println(getClass().getName()+".reset() (w/ "+fields.length+" prospective fields)");
        } else {
            dout = null;
        }

        // All at once - performance.
        AccessibleObject.setAccessible(fields, true);
        lookup.claimAllLinkPermission();
        try {
            for (int i = 0; i < fields.length; ++i) {
                final String fieldName = fields[i].getName();
                if ( isAddressField(fieldName) ) {
                    final String funcName = fieldToFunctionName(fieldName);
                    setEntry(fields[i], funcName, lookup);
                }
            }
        } finally {
            lookup.releaseAllLinkPermission();
        }

        if (DEBUG) {
            dout.flush();
            if (DEBUG_PREFIX != null) {
                dout.close();
            }
        }
    }

    /**
     * Initializes the mapping for a single function.
     * <p>
     * If a {@link SecurityManager} is installed, user needs link permissions
     * for <b>all</b> libraries, i.e. for <code>new RuntimePermission("loadLibrary.*");</code>!
     * </p>
     *
     * @throws IllegalArgumentException if this function is not in this table.
     * @throws SecurityException if user is not granted access for all libraries.
     */
    public void initEntry(final String name, final DynamicLookupHelper lookup) throws SecurityException, IllegalArgumentException {
        final Field addressField = fieldForFunction(name);
        addressField.setAccessible(true);
        setEntry(addressField, name, lookup);
    }

    private final void setEntry(final Field addressField, final String funcName, final DynamicLookupHelper lookup) throws SecurityException {
        try {
            assert (addressField.getType() == Long.TYPE);
            final long newProcAddress = resolver.resolve(funcName, lookup); // issues SecurityUtil.checkLinkPermission(String)
            addressField.setLong(this, newProcAddress);
            if (DEBUG) {
                getDebugOutStream().println("  " + addressField.getName() + " -> 0x" + Long.toHexString(newProcAddress));
            }
        } catch (final Exception e) {
            throw new RuntimeException("Can not get proc address for method \""
                    + funcName + "\": Couldn't set value of field \"" + addressField, e);
        }
    }

    private final String fieldToFunctionName(final String addressFieldName) {
        return addressFieldName.substring(PROCADDRESS_VAR_PREFIX_LEN);
    }

    private final Field fieldForFunction(final String name) throws IllegalArgumentException {
        try {
            return getClass().getDeclaredField(PROCADDRESS_VAR_PREFIX + name);
        } catch (final NoSuchFieldException ex) {
            throw new IllegalArgumentException(getClass().getName() +" has no entry for the function '"+name+"'.", ex);
        }
    }

    /**
     * Warning: Returns an accessible probably protected field!
     * <p>
     * Caller should have checked link permissions
     * for <b>all</b> libraries, i.e. for <code>new RuntimePermission("loadLibrary.*");</code>
     * <i>if</i> exposing the field or address!
     * </p>
     */
    private final Field fieldForFunctionInSec(final String name) throws IllegalArgumentException {
        return AccessController.doPrivileged(new PrivilegedAction<Field>() {
            @Override
            public Field run() {
                try {
                    final Field addressField = ProcAddressTable.this.getClass().getDeclaredField(PROCADDRESS_VAR_PREFIX + name);
                    addressField.setAccessible(true); // we need to read the protected value!
                    return addressField;
                } catch (final NoSuchFieldException ex) {
                    throw new IllegalArgumentException(getClass().getName() +" has no entry for the function '"+name+"'.", ex);
                }
            }
        } );
    }

    private final boolean isAddressField(final String fieldName) {
        return fieldName.startsWith(PROCADDRESS_VAR_PREFIX);
    }

    private final static PrintStream getDebugOutStream() {
        PrintStream out = null;
        if (DEBUG) {
            if (DEBUG_PREFIX != null) {
                try {
                    out = new PrintStream(new BufferedOutputStream(new FileOutputStream(DEBUG_PREFIX + File.separatorChar
                            + "procaddresstable-" + (++debugNum) + ".txt")));
                } catch (final IOException e) {
                    e.printStackTrace();
                    out = System.err;
                }
            } else {
                out = System.err;
            }
        }
        return out;
    }

    /**
     * Returns this table as map with the function name as key and the address as value.
     */
    private final Map<String, Long> toMap() {
        final SortedMap<String, Long> map = new TreeMap<String, Long>();

        final Field[] fields = getClass().getFields();
        try {
            for (int i = 0; i < fields.length; ++i) {
                final String addressFieldName = fields[i].getName();
                if (isAddressField(addressFieldName)) {
                    map.put(fieldToFunctionName(addressFieldName), (Long)fields[i].get(this));
                }
            }
        } catch (final IllegalArgumentException ex) {
            throw new RuntimeException(ex);
        } catch (final IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }

        return map;
    }

    /**
     * Returns true only if non null function pointer to this function exists.
     */
    public final boolean isFunctionAvailable(final String functionName) {
        try{
            return isFunctionAvailableImpl(functionName);
        } catch (final IllegalArgumentException ex) {
            return false;
        }
    }

    /**
     * This is a convenience method to query the native function existence by name.
     * <p>
     * It lets you avoid having to
     * manually compute the &quot;{@link #PROCADDRESS_VAR_PREFIX} + &lt;functionName&gt;&quot;
     * member variable name and look it up via reflection.
     * </p>
     *
     * @throws IllegalArgumentException if this function is not in this table.
     */
    protected boolean isFunctionAvailableImpl(final String functionName) throws IllegalArgumentException {
        final Field addressField = fieldForFunctionInSec(functionName);
        try {
            return 0 != addressField.getLong(this);
        } catch (final IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * This is a convenience method to query the native function handle by name.
     * <p>
     * It lets you avoid having to
     * manually compute the &quot;{@link #PROCADDRESS_VAR_PREFIX} + &lt;functionName&gt;&quot;
     * member variable name and look it up via reflection.
     * </p>
     * <p>
     * If a {@link SecurityManager} is installed, user needs link permissions
     * for <b>all</b> libraries, i.e. for <code>new RuntimePermission("loadLibrary.*");</code>!
     * </p>
     *
     * @throws IllegalArgumentException if this function is not in this table.
     * @throws SecurityException if user is not granted access for all libraries.
     */
    public long getAddressFor(final String functionName) throws SecurityException, IllegalArgumentException {
        SecurityUtil.checkAllLinkPermission();
        final Field addressField = fieldForFunctionInSec(functionName);
        try {
            return addressField.getLong(this);
        } catch (final IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Returns all functions pointing to null.
     */
    public final Set<String> getNullPointerFunctions() {
        final Map<String, Long> table = toMap();
        final Set<String> nullPointers = new LinkedHashSet<String>();
        for (final Iterator<Map.Entry<String, Long>> it = table.entrySet().iterator(); it.hasNext();) {
            final Map.Entry<String, Long> entry = it.next();
            final long address = entry.getValue().longValue();
            if(address == 0) {
                nullPointers.add(entry.getKey());
            }
        }
        return nullPointers;
    }

    @Override
    public final String toString() {
        return getClass().getName()+""+toMap();
    }

    private static class One2OneResolver implements FunctionAddressResolver {
        @Override
        public long resolve(final String name, final DynamicLookupHelper lookup) throws SecurityException {
            return lookup.dynamicLookupFunction(name);
        }
    }


}
