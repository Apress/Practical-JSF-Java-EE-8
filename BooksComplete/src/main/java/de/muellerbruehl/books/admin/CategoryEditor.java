/*
 * ***********************************************************
 * This software is created by Michael Müller.
 * (c) all rights reserved.
 *
 * This software is delivered as is, without any warranty.
 * It is free for personal, non-commercial usage.
 * ***********************************************************
 */
package de.muellerbruehl.books.admin;

import de.muellerbruehl.books.entities.Category;
import de.muellerbruehl.books.enums.Page;
import de.muellerbruehl.books.helper.SessionTools;
import de.muellerbruehl.books.helper.Topic;
import de.muellerbruehl.books.helper.Topics;
import de.muellerbruehl.books.helper.Utilities;
import de.muellerbruehl.books.helper.Utilities.HandleDefault;
import de.muellerbruehl.books.services.CategoryService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Michael Müller <michael.mueller at mueller-bruehl.de>
 */
@Named
@SessionScoped
public class CategoryEditor implements Serializable {

  private static final long serialVersionUID = 1L;
  private static final String CATEGORY = "category";
  private static final Logger _logger = Logger.getLogger("CategoryEditor");

  @Inject CategoryService _categoryService;
  @Inject SessionTools _sessionTools;
  private Topics _topics;
  private List<Category> _deletedCategories;
  private List<Category> _categories;

  @PostConstruct
  private void init() {
    loadCategrories();
    _deletedCategories = new ArrayList<>();
    initTopics();
  }

  private void loadCategrories() {
    _categories = _categoryService.findAll();
    Map<Integer, Integer> mapCounter = _categoryService.countBooksPerCategory();
    int max = mapCounter.values().stream().max(Integer::compare).get();
    float factor = 9f / max;
    for (Category category : _categories) {
      if (!mapCounter.containsKey(category.getId())) {
        continue;
      }
      int quantity = mapCounter.get(category.getId());
      category.setQuantity(quantity);
      int quantityClass = Math.round(1 + quantity * factor);
      category.setQuantityClass(quantityClass);
    }
  }

  private void initTopics() {
    _topics = new Topics();
    Topic topic = Topic.TopicBuilder
            .createBuilder(CATEGORY)
            .setTitle(Utilities.getMessage("lblCategory"))
            .setOutcome("categoryEditor.xhtml")
            .build();
    _topics.addTopic(topic);
    for (String lang : Utilities.getSupportedLanguages(HandleDefault.Exclude)) {
      topic = Topic.TopicBuilder
              .createBuilder(lang)
              .setOutcome("categoryTranslator.xhtml")
              .build();
      _topics.addTopic(topic);
    }
    _topics.setActive(CATEGORY);
  }

  public String changeTab(String newTopicKey) {
    if (_topics.getActiveTopic().get().getKey().equals(newTopicKey)) {
      return "";
    }
    _topics.setActive(newTopicKey);
    if (!newTopicKey.equals(CATEGORY)) {
      initTranslation(newTopicKey);
    }
    return _topics.getActiveTopic().get().getOutcome();
  }

  private void initTranslation(String langCode) {
    // ensure there is an element in the map for this language
    _categories.stream().forEach(c -> {
      if (c.getTranslatedName(langCode).isEmpty()) {
        c.setTranslatedName(langCode, "");
      }
    });
  }

  public List<Topic> getTopics() {
    return new ArrayList(_topics.getTopics());
  }

  public boolean isActive(Topic topic) {
    Optional<Topic> activeTopic = _topics.getActiveTopic();
    if (activeTopic.isPresent()) {
      return activeTopic.get().equals(topic);
    }
    return false;
  }

  public List<Category> getCategories() {
    return _categories;
  }

  public void setCategories(List<Category> categories) {
    _categories = categories;
  }

  public String deleteCategory(Category category) {
    if (category.getId() >= 0) {
      _deletedCategories.add(category);
    }
    _categories.remove(category);
    return "";
  }

  public String addCategory() {
    _categories.add(new Category());
    return "";
  }

  public String save() {
    if (!_sessionTools.isInternalClient()) {
      return Page.AdminNoSave.getRedirectUrl();
    }
    for (Category category : _categories) {
      _categoryService.save(category);
    }
    for (Category category : _deletedCategories) {
      _categoryService.delete(category);
    }
    _deletedCategories = new ArrayList<>();

    return "";
  }

}
