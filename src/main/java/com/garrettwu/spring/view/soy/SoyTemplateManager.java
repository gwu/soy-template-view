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

import com.google.template.soy.SoyFileSet;
import com.google.template.soy.tofu.SoyTofu;

/**
 * An abstract implementation of a template manager that uses soy templates
 * (a.k.a. Google Closure Templates).
 */
public abstract class SoyTemplateManager extends AbstractTemplateManager {
  /**
   * Constructs a soy template manager.
   *
   * @param templateResourceRoot The root resource directory containing the templates.
   */
  protected SoyTemplateManager(String templateResourceRoot) {
    super(templateResourceRoot);
  }

  /**
   * Compiles all the templates (soy) into objects ready for rendering (tofu).
   *
   * @return The compiled templates.
   * @throws IOException If the templtes cannot be loaded.
   */
  protected SoyTofu compileTemplates() throws IOException {
    SoyFileSet.Builder templates = new SoyFileSet.Builder()
        .setCompileTimeGlobals(getGlobalTemplateDataBindings());

    for (URL templateResource : getTemplateResources()) {
      templates.add(templateResource);
    }

    return templates.build().compileToTofu();
  }
}
