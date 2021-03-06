/**
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.trovit.hdfstree;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Class representing a tree node.
 */
public class TreeNode {

  private List<TreeNode> children;
  private String path;
  private long size = 0l;
  private boolean isDir = false;

  public TreeNode(String path) {
    this.path = path;
    children = Lists.newArrayList();
  }

  public void setChildren(List<TreeNode> children) {
    this.children = children;
  }

  public String getPath() {
    return path;
  }

  public List<TreeNode> getChildren() {
    return children;
  }

  public boolean hasChildren() {
    return (children.size() > 0);
  }

  public TreeNode addChild(String subDir) {
    TreeNode newChild = new TreeNode(subDir);
    children.add(newChild);
    return newChild;
  }

  public int getChildrenSize() {
    return children.size();
  }

  /**
   * Gets the size of this file if the TreeNode represents a file or the size of its files + subdirectories
   * if the node represents a directory.
   * @return
   */
  public long getSize() {
    if (size == 0) {
      // need to get the size recursively.
      for (TreeNode children : getChildren()) {
        size = size + children.getSize();
      }
    }
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }

  public void setDir() {
    this.isDir = true;
  }

  public boolean isDir() {
    return isDir;
  }
}