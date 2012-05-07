package com.garrettwu.spring.view.soy;

import java.io.IOException;
import java.util.Map;

/**
 * Manages the collection of templates for WibiPortal and renders them.
 */
public interface TemplateManager {
  /**
   * Renders a template.
   *
   * @param templateName The name of the template to render.
   * @param templateData A map from variable name to its bound data value.
   * @param out The target to write the rendered template.
   * @throws IOException If the template cannot be renderered.
   */
  void render(String templateName, Map<String, ?> templateData, Appendable out) throws IOException;
}
