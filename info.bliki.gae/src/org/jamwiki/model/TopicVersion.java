/**
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, version 2.1, dated February 1999.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the latest version of the GNU Lesser General
 * Public License as published by the Free Software Foundation;
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program (LICENSE.txt); if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.jamwiki.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang.StringUtils;
import org.jamwiki.utils.Utilities;
import org.jamwiki.utils.WikiLogger;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Text;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;

/**
 * Provides an object representing a version of a Wiki topic.
 */
@Entity
public class TopicVersion implements Serializable {

  public static final int EDIT_NORMAL = 1;
  public static final int EDIT_MINOR = 2;
  public static final int EDIT_REVERT = 3;
  public static final int EDIT_MOVE = 4;
  public static final int EDIT_DELETE = 5;
  public static final int EDIT_PERMISSION = 6;
  public static final int EDIT_UNDELETE = 7;
  public static final int EDIT_IMPORT = 8;
  public static final int EDIT_UPLOAD = 9;

  @Id
  private Long topicVersionId = null;

  private Long authorId = null;
  private String authorDisplay = null;
  private int charactersChanged = 0;
  private String editComment = null;
  private Date editDate = new Date(System.currentTimeMillis());
  private int editType = EDIT_NORMAL;
  /**
   * This field is not persisted and is simply used when writing versions to
   * indicate whether the version can be logged.
   */
  private boolean loggable = true;
  private Long previousTopicVersionId = null;
  /**
   * Some versions should be created without creating a recent change entry.
   * This field is not persisted.
   */
  private boolean recentChangeAllowed = true;
  // private int topicId = -1;
  private Key<Topic> topicId = null;

  private Text versionContent = null;
  private List<String> versionParams = null;
  private static final WikiLogger logger = WikiLogger
      .getLogger(TopicVersion.class.getName());

  /**
	 *
	 */
  public TopicVersion() {
  }

  /**
	 *
	 */
  public TopicVersion(WikiUser user, String authorDisplay, String editComment,
      String versionContent, int charactersChanged) {
    if (user != null && user.getUserId() != null) {
      this.authorId = user.getUserId();
    }
    this.authorDisplay = authorDisplay;
    this.editComment = editComment;
    this.versionContent = new Text(versionContent);
    this.charactersChanged = charactersChanged;
  }

  /**
   * Given a topic associated with this topic version, initialize the version
   * parameters field.
   */
  public void initializeVersionParams(Topic topic) {
    switch (this.editType) {
    case TopicVersion.EDIT_DELETE:
    case TopicVersion.EDIT_UNDELETE:
      // store the name of the deleted topic
      this.addVersionParam(topic.getName());
      break;
    case TopicVersion.EDIT_MOVE:
      if (!StringUtils.isBlank(topic.getRedirectTo())) {
        // store the old and new topic names
        this.addVersionParam(topic.getName());
        this.addVersionParam(topic.getRedirectTo());
      }
      break;
    case TopicVersion.EDIT_PERMISSION:
      // store the name of the topic
      this.addVersionParam(topic.getName());
      break;
    case TopicVersion.EDIT_IMPORT:
      this.addVersionParam(topic.getName());
      break;
    case TopicVersion.EDIT_UPLOAD:
      // store the topic name for uploads
      this.addVersionParam(topic.getName());
      break;
    default:
      break;
    }
  }

  /**
	 *
	 */
  public Long getAuthorId() {
    return this.authorId;
  }

  /**
	 *
	 */
  public void setAuthorId(Long authorId) {
    this.authorId = authorId;
  }

  /**
	 *
	 */
  public String getAuthorDisplay() {
    return this.authorDisplay;
  }

  /**
	 *
	 */
  public void setAuthorDisplay(String authorDisplay) {
    this.authorDisplay = authorDisplay;
  }

  /**
	 *
	 */
  public int getCharactersChanged() {
    return this.charactersChanged;
  }

  /**
	 *
	 */
  public void setCharactersChanged(int charactersChanged) {
    this.charactersChanged = charactersChanged;
  }

  /**
	 *
	 */
  public String getEditComment() {
    return this.editComment;
  }

  /**
	 *
	 */
  public void setEditComment(String editComment) {
    this.editComment = editComment;
  }

  /**
	 *
	 */
  public Date getEditDate() {
    return this.editDate;
  }

  /**
	 *
	 */
  public void setEditDate(Date editDate) {
    this.editDate = editDate;
  }

  /**
	 *
	 */
  public int getEditType() {
    return this.editType;
  }

  /**
	 *
	 */
  public void setEditType(int editType) {
    this.editType = editType;
  }

  /**
	 *
	 */
  public boolean isLoggable() {
    return this.loggable;
  }

  /**
	 *
	 */
  public void setLoggable(boolean loggable) {
    this.loggable = loggable;
  }

  /**
	 *
	 */
  public Long getPreviousTopicVersionId() {
    return this.previousTopicVersionId;
  }

  /**
	 *
	 */
  public void setPreviousTopicVersionId(Long previousTopicVersionId) {
    this.previousTopicVersionId = previousTopicVersionId;
  }

  /**
	 *
	 */
  public boolean isRecentChangeAllowed() {
    return this.recentChangeAllowed;
  }

  /**
	 *
	 */
  public void setRecentChangeAllowed(boolean recentChangeAllowed) {
    this.recentChangeAllowed = recentChangeAllowed;
  }

  /**
	 *
	 */
  public Topic getTopicId() {
    // return this.topicId;
    Objectify ofy = OS.begin();
    try {
      return ofy.get(topicId);
    } catch (EntityNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

  public Key<Topic> getTopicOKey() {
    return this.topicId;
  }
  
  /**
	 *
	 */
  public void setTopicId(Topic topic) {
    this.topicId = new Key<Topic>(Topic.class,topic.getName());
  }

  /**
	 *
	 */
  public Long getTopicVersionId() {
    return this.topicVersionId;
  }

  /**
	 *
	 */
  public void setTopicVersionId(Long topicVersionId) {
    this.topicVersionId = topicVersionId;
  }

  /**
	 *
	 */
  public String getVersionContent() {
    return this.versionContent.getValue();
  }

  /**
	 *
	 */
  public void setVersionContent(String versionContent) {
    this.versionContent = new Text(versionContent);
  }

  /**
   * Utility method for adding a version param.
   */
  private void addVersionParam(String param) {
    if (this.versionParams == null) {
      this.versionParams = new ArrayList<String>();
    }
    this.versionParams.add(param);
  }

  /**
	 *
	 */
  public List<String> getVersionParams() {
    return this.versionParams;
  }

  /**
	 *
	 */
  public void setVersionParams(List<String> versionParams) {
    this.versionParams = versionParams;
  }

  /**
   * Utility method for converting the version params to a pipe-delimited
   * string.
   */
  public String getVersionParamString() {
    return Utilities.listToDelimitedString(this.versionParams, "|");
  }

  /**
   * Utility method for converting a version params pipe-delimited string to a
   * list.
   */
  public void setVersionParamString(String versionParamsString) {
    this.setVersionParams(Utilities.delimitedStringToList(versionParamsString,
        "|"));
  }
}
