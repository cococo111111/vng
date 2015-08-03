/**
 *Class: ResourceHandler * 
 * Copyright (c) 2010-2011 VNG corp. All Rights Reserved.
 */
package vng.zingme.payment.frontend.credits.service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpFields;
import org.eclipse.jetty.http.HttpHeaders;
import org.eclipse.jetty.http.MimeTypes;
import org.eclipse.jetty.io.Buffer;
import org.eclipse.jetty.io.ByteArrayBuffer;
import org.eclipse.jetty.io.WriterOutputStream;
import org.eclipse.jetty.server.HttpConnection;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.util.URIUtil;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.resource.FileResource;
import org.eclipse.jetty.util.resource.Resource;

/**
 * mo ta here
 * version: 1.0
 * @author: nhutnh@vng.com.vn
 * created: Jun 9, 2011
 */
public class ResourceHandler extends AbstractHandler {

    ContextHandler _context;
    Resource _baseResource;
    String[] _welcomeFiles = {"index.html"};
    MimeTypes _mimeTypes = new MimeTypes();
    ByteArrayBuffer _cacheControl;
    boolean _aliases;
    boolean _directory;

    public MimeTypes getMimeTypes() {
        return _mimeTypes;
    }

    public void setMimeTypes(MimeTypes mimeTypes) {
        _mimeTypes = mimeTypes;
    }

    public boolean isAliases() {
        return _aliases;
    }

    public void setAliases(boolean aliases) {
        _aliases = aliases;
    }

    public boolean isDirectoriesListed() {
        return _directory;
    }

    public void setDirectoriesListed(boolean directory) {
        _directory = directory;
    }

    public void doStart()
            throws Exception {
        ContextHandler.Context scontext = ContextHandler.getCurrentContext();
        _context = (scontext == null ? null : scontext.getContextHandler());

        if (_context != null) {
            _aliases = _context.isAliases();
        }
        if ((!_aliases) && (!FileResource.getCheckAliases())) {
            throw new IllegalStateException("Alias checking disabled");
        }
        super.doStart();
    }

    public Resource getBaseResource() {
        if (_baseResource == null) {
            return null;
        }
        return _baseResource;
    }

    public String getResourceBase() {
        if (_baseResource == null) {
            return null;
        }
        return _baseResource.toString();
    }

    public void setBaseResource(Resource base) {
        _baseResource = base;
    }

    public void setResourceBase(String resourceBase) {
        try {
            setBaseResource(Resource.newResource(resourceBase));
        } catch (Exception e) {
            Log.warn(e.toString());
            Log.debug(e);
            throw new IllegalArgumentException(resourceBase);
        }
    }

    public String getCacheControl() {
        return _cacheControl.toString();
    }

    public void setCacheControl(String cacheControl) {
        _cacheControl = (cacheControl == null ? null : new ByteArrayBuffer(cacheControl));
    }

    public Resource getResource(String path)
            throws MalformedURLException {
        if ((path == null) || (!path.startsWith("/"))) {
            throw new MalformedURLException(path);
        }
        Resource base = _baseResource;
        if (base == null) {
            if (_context == null) {
                return null;
            }
            base = _context.getBaseResource();
            if (base == null) {
                return null;
            }
        }
        try {
            path = URIUtil.canonicalPath(path);
            return base.addPath(path);
        } catch (Exception e) {
            Log.ignore(e);
        }

        return null;
    }

    protected Resource getResource(HttpServletRequest request)
            throws MalformedURLException {
        String path_info = request.getPathInfo();
        if (path_info == null) {
            return null;
        }
        return getResource(path_info);
    }

    public String[] getWelcomeFiles() {
        return _welcomeFiles;
    }

    public void setWelcomeFiles(String[] welcomeFiles) {
        _welcomeFiles = welcomeFiles;
    }

    protected Resource getWelcome(Resource directory)
            throws MalformedURLException, IOException {
        for (int i = 0; i < _welcomeFiles.length; i++) {
            Resource welcome = directory.addPath(_welcomeFiles[i]);
            if ((welcome.exists()) && (!welcome.isDirectory())) {
                return welcome;
            }
        }
        return null;
    }

    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Enumeration enumParams = request.getParameterNames();
        StringBuilder s = new StringBuilder();
        boolean isFirst = true;
        while (enumParams.hasMoreElements()) {
            String object = (String) enumParams.nextElement();
            if (isFirst) {
                isFirst = false;
            } else {
                s.append("&");
            }
            s.append(object);
            s.append("=");
            s.append(request.getParameter(object));
        }
        if (target != null && target.trim().equals("/")) {
            response.sendRedirect("/index?" + s.toString());
            return;
        }

        if (baseRequest.isHandled()) {
            return;
        }
        boolean skipContentBody = false;
        if (!"GET".equals(request.getMethod())) {
            if (!"HEAD".equals(request.getMethod())) {
                return;
            }
            skipContentBody = true;
        }

        Resource resource = getResource(request);

        if ((resource == null) || (!resource.exists())) {
            return;
        }
        if ((!_aliases) && (resource.getAlias() != null)) {
            Log.info(resource + " aliased to " + resource.getAlias());
            return;
        }

        baseRequest.setHandled(true);

        if (resource.isDirectory()) {
            if (!request.getPathInfo().endsWith("/")) {
                response.sendRedirect(response.encodeRedirectURL(URIUtil.addPaths(request.getRequestURI(), "/")));
                return;
            }

            Resource welcome = getWelcome(resource);
            if ((welcome != null) && (welcome.exists())) {
                resource = welcome;
            } else {
                doDirectory(request, response, resource);
                baseRequest.setHandled(true);
                return;
            }

        }

        long last_modified = resource.lastModified();
        if (last_modified > 0L) {
            long if_modified = request.getDateHeader("If-Modified-Since");
            if ((if_modified > 0L) && (last_modified / 1000L <= if_modified / 1000L)) {
                response.setStatus(304);
                return;
            }
        }

        Buffer mime = _mimeTypes.getMimeByExtension(resource.toString());
        if (mime == null) {
            mime = _mimeTypes.getMimeByExtension(request.getPathInfo());
        }

        doResponseHeaders(response, resource, mime != null ? mime.toString() : null);
        response.setDateHeader("Last-Modified", last_modified);
        if (skipContentBody) {
            return;
        }
        OutputStream out = null;
        try {
            out = response.getOutputStream();
        } catch (IllegalStateException e) {
            out = new WriterOutputStream(response.getWriter());
        }

        if ((out instanceof HttpConnection.Output)) {
            ((HttpConnection.Output) out).sendContent(resource.getInputStream());
        } else {
            resource.writeTo(out, 0L, resource.length());
        }
    }

    protected void doDirectory(HttpServletRequest request, HttpServletResponse response, Resource resource)
            throws IOException {
        if (_directory) {
            String listing = resource.getListHTML(request.getRequestURI(), request.getPathInfo().lastIndexOf("/") > 0);
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println(listing);
        } else {
            response.sendError(403);
        }
    }

    protected void doResponseHeaders(HttpServletResponse response, Resource resource, String mimeType) {
        if (mimeType != null) {
            response.setContentType(mimeType);
        }
        long length = resource.length();

        if ((response instanceof Response)) {
            HttpFields fields = ((Response) response).getHttpFields();

            if (length > 0L) {
                fields.putLongField(HttpHeaders.CONTENT_LENGTH_BUFFER, length);
            }
            if (_cacheControl != null) {
                fields.put(HttpHeaders.CACHE_CONTROL_BUFFER, _cacheControl);
            }
        } else {
            if (length > 0L) {
                response.setHeader("Content-Length", Long.toString(length));
            }
            if (_cacheControl != null) {
                response.setHeader("Cache-Control", _cacheControl.toString());
            }
        }
    }
}
