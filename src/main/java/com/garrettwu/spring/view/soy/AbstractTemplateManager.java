package com.garrettwu.spring.view.soy;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * An abstract implementation of a template manager.
 */
public abstract class AbstractTemplateManager implements TemplateManager {
  /** The root resource directory containing the templates. */
  private final String mTemplateResourceRoot;

  /**
   * Constructs an abstract template manager.
   *
   * @param templateResourceRoot The root resource directory containing the templates.
   */
  protected AbstractTemplateManager(String templateResourceRoot) {
    if (templateResourceRoot.endsWith("/")) {
      throw new IllegalArgumentException("templateResourceRoot may not end in '/'.");
    }
    mTemplateResourceRoot = templateResourceRoot;
  }

  /**
   * Gets the data that should be bound to global variables available in all templates.
   *
   * @return A map from global variable name to its bound data value.
   */
  protected Map<String, ?> getGlobalTemplateDataBindings() {
    return Collections.emptyMap();
  }

  /**
   * Gets all the templates within the template root.
   *
   * @return The template resources.
   * @throws IOException If the templates cannot be loaded.
   */
  protected Collection<URL> getTemplateResources() throws IOException {
    PathMatchingResourcePatternResolver resourceResolver =
        new PathMatchingResourcePatternResolver();
    Collection<URL> resourceUrls = new ArrayList<URL>();
    for (Resource resource : resourceResolver.getResources(mTemplateResourceRoot + "/**")) {
      resourceUrls.add(resource.getURL());
    }
    return resourceUrls;
  }
}
