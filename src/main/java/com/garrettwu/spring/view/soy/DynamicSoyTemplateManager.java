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
import java.util.Map;

import com.google.template.soy.tofu.SoyTofu;

/**
 * A dynamic template manager reads the templates every time render() is called, so that
 * changes made during development are reflected immediately.
 */
public class DynamicSoyTemplateManager extends SoyTemplateManager {
  /**
   * Constructs a dynamic template manager.
   *
   * @param templateResourceRoot The root resource directory containing the templates.
   *     This should not have a trailing slash.
   */
  public DynamicSoyTemplateManager(String templateResourceRoot) {
    super(templateResourceRoot);
  }

  /** {@inheritDoc} */
  @Override
  public void render(String templateName, Map<String, ?> templateData, Appendable out)
      throws IOException {
    // Compile the templates each time render is called,
    // so we see any edits that may have occured.
    SoyTofu tofu = compileTemplates();

    tofu.newRenderer(templateName)
        .setData(templateData)
        .render(out);
  }
}
