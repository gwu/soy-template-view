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
