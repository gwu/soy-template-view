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

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractTemplateView;

/**
 * A view implementation in Spring MVC that delegates rendering to a TemplateManager.
 */
public class TemplateView extends AbstractTemplateView {
  /** A template manager to render with. */
  private TemplateManager mTemplateManager;

  /** The name of the template to render. */
  private String mTemplateName;

  /**
   * Sets the template manager that will be used to render.
   *
   * @param templateManager A template manager.
   */
  public void setTemplateManager(TemplateManager templateManager) {
    mTemplateManager = templateManager;
  }

  /**
   * Sets the name of the template to render with.
   *
   * @param templateName A template name.
   */
  public void setTemplateName(String templateName) {
    mTemplateName = templateName;
  }

  /** {@inheritDoc} */
  @Override
  protected void renderMergedTemplateModel(Map<String, Object> model,
      HttpServletRequest request, HttpServletResponse response) throws Exception {
    mTemplateManager.render(mTemplateName, model, response.getWriter());
  }
}
