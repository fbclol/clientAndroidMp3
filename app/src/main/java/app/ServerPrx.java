// **********************************************************************
//
// Copyright (c) 2003-2017 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.7.0
//
// <auto-generated>
//
// Generated from file `server.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package app;

public interface ServerPrx extends com.zeroc.Ice.ObjectPrx
{
    default String addDocument(music music)
    {
        return addDocument(music, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default String addDocument(music music, java.util.Map<String, String> context)
    {
        return _iceI_addDocumentAsync(music, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<java.lang.String> addDocumentAsync(music music)
    {
        return _iceI_addDocumentAsync(music, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<java.lang.String> addDocumentAsync(music music, java.util.Map<String, String> context)
    {
        return _iceI_addDocumentAsync(music, context, false);
    }

    default com.zeroc.IceInternal.OutgoingAsync<java.lang.String> _iceI_addDocumentAsync(music iceP_music, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<java.lang.String> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "addDocument", null, sync, null);
        f.invoke(true, context, null, ostr -> {
                     music.ice_write(ostr, iceP_music);
                 }, istr -> {
                     String ret;
                     ret = istr.readString();
                     return ret;
                 });
        return f;
    }

    default String removeDocument(String name)
    {
        return removeDocument(name, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default String removeDocument(String name, java.util.Map<String, String> context)
    {
        return _iceI_removeDocumentAsync(name, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<java.lang.String> removeDocumentAsync(String name)
    {
        return _iceI_removeDocumentAsync(name, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<java.lang.String> removeDocumentAsync(String name, java.util.Map<String, String> context)
    {
        return _iceI_removeDocumentAsync(name, context, false);
    }

    default com.zeroc.IceInternal.OutgoingAsync<java.lang.String> _iceI_removeDocumentAsync(String iceP_name, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<java.lang.String> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "removeDocument", null, sync, null);
        f.invoke(true, context, null, ostr -> {
                     ostr.writeString(iceP_name);
                 }, istr -> {
                     String ret;
                     ret = istr.readString();
                     return ret;
                 });
        return f;
    }

    default music[] displayListMusic()
    {
        return displayListMusic(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default music[] displayListMusic(java.util.Map<String, String> context)
    {
        return _iceI_displayListMusicAsync(context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<music[]> displayListMusicAsync()
    {
        return _iceI_displayListMusicAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<music[]> displayListMusicAsync(java.util.Map<String, String> context)
    {
        return _iceI_displayListMusicAsync(context, false);
    }

    default com.zeroc.IceInternal.OutgoingAsync<music[]> _iceI_displayListMusicAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<music[]> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "displayListMusic", null, sync, null);
        f.invoke(true, context, null, null, istr -> {
                     music[] ret;
                     ret = tabHelper.read(istr);
                     return ret;
                 });
        return f;
    }

    default music[] searchDocument(String attribute, String name)
    {
        return searchDocument(attribute, name, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default music[] searchDocument(String attribute, String name, java.util.Map<String, String> context)
    {
        return _iceI_searchDocumentAsync(attribute, name, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<music[]> searchDocumentAsync(String attribute, String name)
    {
        return _iceI_searchDocumentAsync(attribute, name, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<music[]> searchDocumentAsync(String attribute, String name, java.util.Map<String, String> context)
    {
        return _iceI_searchDocumentAsync(attribute, name, context, false);
    }

    default com.zeroc.IceInternal.OutgoingAsync<music[]> _iceI_searchDocumentAsync(String iceP_attribute, String iceP_name, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<music[]> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "searchDocument", null, sync, null);
        f.invoke(true, context, null, ostr -> {
                     ostr.writeString(iceP_attribute);
                     ostr.writeString(iceP_name);
                 }, istr -> {
                     music[] ret;
                     ret = tabHelper.read(istr);
                     return ret;
                 });
        return f;
    }

    default void LibvlcPlayerPlay(String name)
    {
        LibvlcPlayerPlay(name, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void LibvlcPlayerPlay(String name, java.util.Map<String, String> context)
    {
        _iceI_LibvlcPlayerPlayAsync(name, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> LibvlcPlayerPlayAsync(String name)
    {
        return _iceI_LibvlcPlayerPlayAsync(name, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> LibvlcPlayerPlayAsync(String name, java.util.Map<String, String> context)
    {
        return _iceI_LibvlcPlayerPlayAsync(name, context, false);
    }

    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_LibvlcPlayerPlayAsync(String iceP_name, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "LibvlcPlayerPlay", null, sync, null);
        f.invoke(false, context, null, ostr -> {
                     ostr.writeString(iceP_name);
                 }, null);
        return f;
    }

    default void LibvlcPlayerStop()
    {
        LibvlcPlayerStop(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void LibvlcPlayerStop(java.util.Map<String, String> context)
    {
        _iceI_LibvlcPlayerStopAsync(context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> LibvlcPlayerStopAsync()
    {
        return _iceI_LibvlcPlayerStopAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> LibvlcPlayerStopAsync(java.util.Map<String, String> context)
    {
        return _iceI_LibvlcPlayerStopAsync(context, false);
    }

    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_LibvlcPlayerStopAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "LibvlcPlayerStop", null, sync, null);
        f.invoke(false, context, null, null, null);
        return f;
    }

    default void LibvlcPlayerPause()
    {
        LibvlcPlayerPause(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void LibvlcPlayerPause(java.util.Map<String, String> context)
    {
        _iceI_LibvlcPlayerPauseAsync(context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> LibvlcPlayerPauseAsync()
    {
        return _iceI_LibvlcPlayerPauseAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> LibvlcPlayerPauseAsync(java.util.Map<String, String> context)
    {
        return _iceI_LibvlcPlayerPauseAsync(context, false);
    }

    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_LibvlcPlayerPauseAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "LibvlcPlayerPause", null, sync, null);
        f.invoke(false, context, null, null, null);
        return f;
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static ServerPrx checkedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, ice_staticId(), ServerPrx.class, _ServerPrxI.class);
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static ServerPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, context, ice_staticId(), ServerPrx.class, _ServerPrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static ServerPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, ice_staticId(), ServerPrx.class, _ServerPrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static ServerPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, context, ice_staticId(), ServerPrx.class, _ServerPrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @return A proxy for this type.
     **/
    static ServerPrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, ServerPrx.class, _ServerPrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type.
     **/
    static ServerPrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, facet, ServerPrx.class, _ServerPrxI.class);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the per-proxy context.
     * @param newContext The context for the new proxy.
     * @return A proxy with the specified per-proxy context.
     **/
    @Override
    default ServerPrx ice_context(java.util.Map<String, String> newContext)
    {
        return (ServerPrx)_ice_context(newContext);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the adapter ID.
     * @param newAdapterId The adapter ID for the new proxy.
     * @return A proxy with the specified adapter ID.
     **/
    @Override
    default ServerPrx ice_adapterId(String newAdapterId)
    {
        return (ServerPrx)_ice_adapterId(newAdapterId);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoints.
     * @param newEndpoints The endpoints for the new proxy.
     * @return A proxy with the specified endpoints.
     **/
    @Override
    default ServerPrx ice_endpoints(com.zeroc.Ice.Endpoint[] newEndpoints)
    {
        return (ServerPrx)_ice_endpoints(newEndpoints);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator cache timeout.
     * @param newTimeout The new locator cache timeout (in seconds).
     * @return A proxy with the specified locator cache timeout.
     **/
    @Override
    default ServerPrx ice_locatorCacheTimeout(int newTimeout)
    {
        return (ServerPrx)_ice_locatorCacheTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the invocation timeout.
     * @param newTimeout The new invocation timeout (in seconds).
     * @return A proxy with the specified invocation timeout.
     **/
    @Override
    default ServerPrx ice_invocationTimeout(int newTimeout)
    {
        return (ServerPrx)_ice_invocationTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for connection caching.
     * @param newCache <code>true</code> if the new proxy should cache connections; <code>false</code> otherwise.
     * @return A proxy with the specified caching policy.
     **/
    @Override
    default ServerPrx ice_connectionCached(boolean newCache)
    {
        return (ServerPrx)_ice_connectionCached(newCache);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoint selection policy.
     * @param newType The new endpoint selection policy.
     * @return A proxy with the specified endpoint selection policy.
     **/
    @Override
    default ServerPrx ice_endpointSelection(com.zeroc.Ice.EndpointSelectionType newType)
    {
        return (ServerPrx)_ice_endpointSelection(newType);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for how it selects endpoints.
     * @param b If <code>b</code> is <code>true</code>, only endpoints that use a secure transport are
     * used by the new proxy. If <code>b</code> is false, the returned proxy uses both secure and
     * insecure endpoints.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default ServerPrx ice_secure(boolean b)
    {
        return (ServerPrx)_ice_secure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the encoding used to marshal parameters.
     * @param e The encoding version to use to marshal request parameters.
     * @return A proxy with the specified encoding version.
     **/
    @Override
    default ServerPrx ice_encodingVersion(com.zeroc.Ice.EncodingVersion e)
    {
        return (ServerPrx)_ice_encodingVersion(e);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its endpoint selection policy.
     * @param b If <code>b</code> is <code>true</code>, the new proxy will use secure endpoints for invocations
     * and only use insecure endpoints if an invocation cannot be made via secure endpoints. If <code>b</code> is
     * <code>false</code>, the proxy prefers insecure endpoints to secure ones.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default ServerPrx ice_preferSecure(boolean b)
    {
        return (ServerPrx)_ice_preferSecure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the router.
     * @param router The router for the new proxy.
     * @return A proxy with the specified router.
     **/
    @Override
    default ServerPrx ice_router(com.zeroc.Ice.RouterPrx router)
    {
        return (ServerPrx)_ice_router(router);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator.
     * @param locator The locator for the new proxy.
     * @return A proxy with the specified locator.
     **/
    @Override
    default ServerPrx ice_locator(com.zeroc.Ice.LocatorPrx locator)
    {
        return (ServerPrx)_ice_locator(locator);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for collocation optimization.
     * @param b <code>true</code> if the new proxy enables collocation optimization; <code>false</code> otherwise.
     * @return A proxy with the specified collocation optimization.
     **/
    @Override
    default ServerPrx ice_collocationOptimized(boolean b)
    {
        return (ServerPrx)_ice_collocationOptimized(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses twoway invocations.
     * @return A proxy that uses twoway invocations.
     **/
    @Override
    default ServerPrx ice_twoway()
    {
        return (ServerPrx)_ice_twoway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses oneway invocations.
     * @return A proxy that uses oneway invocations.
     **/
    @Override
    default ServerPrx ice_oneway()
    {
        return (ServerPrx)_ice_oneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch oneway invocations.
     * @return A proxy that uses batch oneway invocations.
     **/
    @Override
    default ServerPrx ice_batchOneway()
    {
        return (ServerPrx)_ice_batchOneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses datagram invocations.
     * @return A proxy that uses datagram invocations.
     **/
    @Override
    default ServerPrx ice_datagram()
    {
        return (ServerPrx)_ice_datagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch datagram invocations.
     * @return A proxy that uses batch datagram invocations.
     **/
    @Override
    default ServerPrx ice_batchDatagram()
    {
        return (ServerPrx)_ice_batchDatagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, except for compression.
     * @param co <code>true</code> enables compression for the new proxy; <code>false</code> disables compression.
     * @return A proxy with the specified compression setting.
     **/
    @Override
    default ServerPrx ice_compress(boolean co)
    {
        return (ServerPrx)_ice_compress(co);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection timeout setting.
     * @param t The connection timeout for the proxy in milliseconds.
     * @return A proxy with the specified timeout.
     **/
    @Override
    default ServerPrx ice_timeout(int t)
    {
        return (ServerPrx)_ice_timeout(t);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection ID.
     * @param connectionId The connection ID for the new proxy. An empty string removes the connection ID.
     * @return A proxy with the specified connection ID.
     **/
    @Override
    default ServerPrx ice_connectionId(String connectionId)
    {
        return (ServerPrx)_ice_connectionId(connectionId);
    }

    static String ice_staticId()
    {
        return "::app::Server";
    }
}
