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
