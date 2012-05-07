/*
 * Licensed to Garrett Wu under one or more contributor license
 * agreements.  See the NOTICE file distributed with this work for
 * additional information regarding copyright ownership.  Garrett Wu
 * licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */

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
