package info.bliki.gae.controller;

import info.bliki.gae.db.PageService;
import info.bliki.gae.model.BlikiUtil;
import info.bliki.gae.model.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.appengine.repackaged.org.apache.commons.logging.Log;

@Controller
@RequestMapping(value = "/page")
public class PageController {
  protected final Log logger = com.google.appengine.repackaged.org.apache.commons.logging.LogFactory
      .getLog(getClass());
  // private UserService userService = UserServiceFactory.getUserService();
  public final static String NEW_PAGE_URI = "page/new";

  @Autowired
  private PageService pageService;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index(Model model) {
    model.addAttribute("pages", pageService.getAll());
    return "page/index";
  }

  @RequestMapping(value = "new")
  public String newPage() {
    String lameSecurityCheckUrl = lameSecurityCheck(NEW_PAGE_URI);
    return lameSecurityCheckUrl;
  }

  @RequestMapping(value = "new", method = RequestMethod.POST)
  public String createPost(String title, String content, Long key, Model model) {
    return create(title, content, key, model);
  }

  @RequestMapping(value = "new", method = RequestMethod.GET)
  public String createGet(String title, String content, Long key, Model model) {
    return create(title, content, key, model);
  }

  private String create(String title, String content, Long key, Model model) {
    if (lameSecurityCheck() != null) {
      return lameSecurityCheck();
    }
    Page page = null;
    if (key != null) {
      // update an existing page
      page = pageService.findByKey(key);
      if (page != null) {
        page.setTitle(title);
        page.setContent(content);
        page = pageService.update(page);
        model.addAttribute("page", page);
        return "page/view";
      }
    } else {
      // create completely new page
      page = new Page(title, content);
      page = pageService.save(page);
      model.addAttribute("page", page);
      return "page/view";
    }

    model.addAttribute("pages", pageService.getAll());
    return "redirect:/page/";
  }

  @RequestMapping(value = "/delkey/{key}", method = RequestMethod.GET)
  public String delete(@PathVariable String key, Model model) {
    if (lameSecurityCheck() != null) {
      return lameSecurityCheck();
    }
    Page page = null;
    if (key != null) {
      // delete an existing page
      page = pageService.findByKey(Long.valueOf(key));
      if (page != null) {
        pageService.delete(page);
      }
    }

    model.addAttribute("pages", pageService.getAll());
    return "redirect:/page/";
  }

  @RequestMapping(value = "/editkey/{key}", method = RequestMethod.GET)
  public String editKey(@PathVariable String key, Model model) {
    if (lameSecurityCheck() != null) {
      return lameSecurityCheck();
    }
    Page page = pageService.findByKey(Long.valueOf(key));
    model.addAttribute("page", page);
    return NEW_PAGE_URI;
  }

  @RequestMapping(value = "/edit/{key}", method = RequestMethod.GET)
  public String edit(@PathVariable String key, Model model) {
    if (lameSecurityCheck() != null) {
      return lameSecurityCheck();
    }
    String topicName = BlikiUtil.decodeTitle(key);
    Page page = pageService.findByTitle(topicName);
    if (page == null) {
      model.addAttribute("page", new Page(topicName, ""));
    } else {
      model.addAttribute("page", page);
    }
    return NEW_PAGE_URI;
  }

  private String lameSecurityCheck(String destinationUrl) {
    return BlikiUtil.securityCheck(destinationUrl);
  }

  private String lameSecurityCheck() {
    return BlikiUtil.securityCheck(null);
  }

  @RequestMapping(value = "/install", method = RequestMethod.GET)
  private String install(Model model) {
    if (lameSecurityCheck() != null) {
      return lameSecurityCheck();
    }
    // create completely new page
    Page page = pageService.findByTitle(Page.MAIN_PAGE);
    if (page == null) {
      page = new Page(Page.MAIN_PAGE, "The main page content");
      page = pageService.save(page);
      model.addAttribute("page", page);
    }
    return "page/view";
  }
}
