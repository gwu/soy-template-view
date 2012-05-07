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
