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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.AbstractTemplateViewResolver;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

/**
 * A ViewResolver implementation in Spring MVC that resolves to a TemplateView.
 *
 * <p>The job of this class is to resolve a view name (string) to a
 * Spring View implementation that will know how to render the HTTP
 * response. In this case, it means returning a configured
 * TemplateView instance, using the view name as the name of the
 * template to render.</p>
 */
public class TemplateViewResolver extends AbstractTemplateViewResolver {
  /** A template manager. */
  private TemplateManager mTemplateManager;

  /**
   * Constructor.
   */
  public TemplateViewResolver() {
    super();
    setExposeSpringMacroHelpers(false);
  }

  /**
   * Sets the template manager used to render.
   *
   * @param templateManager A template manager.
   */
  @Autowired
  public void setTemplateManager(TemplateManager templateManager) {
    mTemplateManager = templateManager;
  }

  /** {@inheritDoc} */
  @Override
  protected Class<?> getViewClass() {
    return TemplateView.class;
  }

  /** {@inheritDoc} */
  @Override
  protected AbstractUrlBasedView buildView(String viewName) throws Exception {
    TemplateView view = (TemplateView) super.buildView(viewName);
    view.setTemplateManager(mTemplateManager);
    view.setTemplateName(viewName);
    return view;
  }
}
