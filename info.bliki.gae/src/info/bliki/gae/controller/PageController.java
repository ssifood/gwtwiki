package info.bliki.gae.controller;

import info.bliki.gae.db.PageService;
import info.bliki.gae.db.WikiUserServiceImpl;
import info.bliki.gae.utils.BlikiBase;
import info.bliki.gae.utils.BlikiUtil;

import java.io.IOException;
import java.util.Locale;

import org.jamwiki.model.Topic;
import org.jamwiki.model.WikiUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.appengine.repackaged.org.apache.commons.logging.Log;

@Controller
@RequestMapping(value = "/page")
public class PageController extends BlikiController {
  protected final Log logger = com.google.appengine.repackaged.org.apache.commons.logging.LogFactory
      .getLog(getClass());
  // private UserService userService = UserServiceFactory.getUserService();
  public final static String EDIT_PAGE_URI = "page/edit";

  @Autowired
  private PageService pageService;

  // @Autowired
  // private WikiUserService wikiUserService;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index(Model model) {
    model.addAttribute("pages", pageService.getAll());
    return "page/index";
  }

  @RequestMapping(value = "new")
  public String newPage() {
    String lameSecurityCheckUrl = lameSecurityCheck(EDIT_PAGE_URI);
    return lameSecurityCheckUrl;
  }

  @RequestMapping(value = "new", method = RequestMethod.POST)
  public String createPost(String title, String contents, Model model) {
    return create(title, contents, model);
  }

  @RequestMapping(value = "new", method = RequestMethod.GET)
  public String createGet(String title, String contents, Model model) {
    return create(title, contents, model);
  }

  private String create(String title, String contents, Model model) {
    setUpModel(pageService, model);
    if (lameSecurityCheck() != null) {
      return lameSecurityCheck();
    }
    WikiUser wikiUser = WikiUserServiceImpl.getWikiUser();
    Topic page = null;
    page = pageService.findByTitle(title);
    if (page != null) {
      // update an existing page
      page.setName(title);
      page.setTopicContent(contents);
      page.setAuthor(wikiUser);
      page = pageService.update(page);
      model.addAttribute("page", page);
      return "page/view";
    }
    // create completely new page
    page = new Topic(title, contents, wikiUser);
    page = pageService.save(page);
    model.addAttribute("page", page);
    return "page/view";
    // 
    //
    // model.addAttribute("pages", pageService.getAll());
    // return "redirect:/page/";
  }

  @RequestMapping(value = "/delete/{title}", method = RequestMethod.GET)
  public String delete(@PathVariable String title, Model model) {
    setUpModel(pageService, model);
    if (lameSecurityCheck() != null) {
      return lameSecurityCheck();
    }
    Topic page = null;
    if (title != null) {
      // delete an existing page
      page = pageService.findByTitle(title);
      if (page != null) {
        pageService.delete(page);
      }
    }
    page = pageService.findByTitle(BlikiBase.SPECIAL_PAGE_STARTING_POINTS);
    model.addAttribute("page", page);
    return "page/view";
  }

  @RequestMapping(value = "/edit/{title}", method = RequestMethod.GET)
  public String editKey(@PathVariable String title, Model model) {
    setUpModel(pageService, model);
    if (lameSecurityCheck() != null) {
      return lameSecurityCheck();
    }
    String topicName = BlikiUtil.decodeTitle(title);
    Topic page = pageService.findByTitle(topicName);
    if (page==null){
      page = new Topic(topicName);
      model.addAttribute("page", page);
    }
    model.addAttribute("page", page);

    return EDIT_PAGE_URI;
  }

  // @RequestMapping(value = "/edit/{key}", method = RequestMethod.GET)
  // public String edit(@PathVariable String key, Model model) {
  // if (lameSecurityCheck() != null) {
  // return lameSecurityCheck();
  // }
  // String topicName = BlikiUtil.decodeTitle(key);
  // Page page = pageService.findByTitle(topicName);
  // if (page == null) {
  // model.addAttribute("page", new Page(topicName, ""));
  // } else {
  // model.addAttribute("page", page);
  // }
  // return NEW_PAGE_URI;
  // }

  private String lameSecurityCheck(String destinationUrl) {
    return BlikiUtil.securityCheck(destinationUrl);
  }

  private String lameSecurityCheck() {
    return BlikiUtil.securityCheck(null);
  }

  @RequestMapping(value = "/install", method = RequestMethod.GET)
  public String install(Model model) {
    if (lameSecurityCheck() != null) {
      return lameSecurityCheck();
    }
    Topic page = pageService
        .findByTitle(BlikiBase.SPECIAL_PAGE_STARTING_POINTS);
    if (page == null) {
      WikiUser wikiUser = WikiUserServiceImpl.getWikiUser();
      // create special pages
      Locale locale = Locale.ENGLISH;
      setupSpecialWikiTopics(wikiUser, locale, BlikiBase.SPECIAL_PAGE_LEFT_MENU);
      setupSpecialWikiTopics(wikiUser, locale,
          BlikiBase.SPECIAL_PAGE_BOTTOM_AREA);
      setupSpecialWikiTopics(wikiUser, locale,
          BlikiBase.SPECIAL_PAGE_STYLESHEET);
      page = setupSpecialWikiTopics(wikiUser, locale,
          BlikiBase.SPECIAL_PAGE_STARTING_POINTS);
    }
    setUpModel(pageService, model);
    model.addAttribute("page", page);
    return "page/view";
  }

  private Topic setupSpecialWikiTopics(WikiUser wikiUser, Locale locale,
      String topicName) {
    // logger.info("Setting up special page " + virtualWiki + " / " +
    // topicName);
    // if (user == null) {
    // throw new IllegalArgumentException(
    // "Cannot pass null WikiUser object to setupSpecialPage");
    // }
    String contents = null;
    try {
      contents = BlikiUtil.readSpecialPage(locale, topicName);
      if (contents != null) {
        Topic page = new Topic(topicName, contents, wikiUser);
        page = pageService.save(page);
        return page;

      }
    } catch (IOException e) {
    }
    return null;
  }
}