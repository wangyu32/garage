package com.wangyu.entity;

import java.util.List;

/**
 * 模块菜单树节点
 * @author 	wangyu
 *
 */
public class ModuleMenuTreeNode {
	private String id;			//节点id
	private String pid;			//节点父ID
	private String type;		//类型，1-表示模块，2-表示菜单
	
	private String text;		//	String(必选项)	列表树节点上的文本，通常是节点右边的小图标。
	private String icon;		//	String(可选项)	列表树节点上的图标，通常是节点左边的图标。
	private String selectedIcon;//	String(可选项)	当某个节点被选择后显示的图标，通常是节点左边的图标。
	private String href;		//	String(可选项)	结合全局enableLinks选项为列表树节点指定URL。
	private String selectable;	//	Boolean. Default: true	指定列表树的节点是否可选择。设置为false将使节点展开，并且不能被选择。
	private State state = new State();//	Object(可选项)	一个节点的初始状态。
	private String color;//	String. Optional	节点的前景色，覆盖全局的前景色选项。
	private String backColor;//	String. Optional	节点的背景色，覆盖全局的背景色选项。
	private String[] tags	;//	Array of Strings. Optional	通过结合全局showTags选项来在列表树节点的右边添加额外的信息。
	
	private List<ModuleMenuTreeNode> nodes; //子节点
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getSelectedIcon() {
		return selectedIcon;
	}
	public void setSelectedIcon(String selectedIcon) {
		this.selectedIcon = selectedIcon;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getSelectable() {
		return selectable;
	}
	public void setSelectable(String selectable) {
		this.selectable = selectable;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getBackColor() {
		return backColor;
	}
	public void setBackColor(String backColor) {
		this.backColor = backColor;
	}
	public String[] getTags() {
		return tags;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	public List<ModuleMenuTreeNode> getNodes() {
		return nodes;
	}
	public void setNodes(List<ModuleMenuTreeNode> nodes) {
		this.nodes = nodes;
	}


	/**
	 * 节点状态
	 */
	public class State {
		private boolean checked;//state.checked		;//	Boolean，默认值false	指示一个节点是否处于checked状态，用一个checkbox图标表示。
		private boolean disabled;//state.disabled	;//	Boolean，默认值false	指示一个节点是否处于disabled状态。（不是selectable，expandable或checkable）
		private boolean expanded;//state.expanded	;//	Boolean，默认值false	指示一个节点是否处于展开状态。
		private boolean selected;//state.selected	;//	Boolean，默认值false	指示一个节点是否可以被选择。
		public boolean isChecked() {
			return checked;
		}
		public void setChecked(boolean checked) {
			this.checked = checked;
		}
		public boolean isDisabled() {
			return disabled;
		}
		public void setDisabled(boolean disabled) {
			this.disabled = disabled;
		}
		public boolean isExpanded() {
			return expanded;
		}
		public void setExpanded(boolean expanded) {
			this.expanded = expanded;
		}
		public boolean isSelected() {
			return selected;
		}
		public void setSelected(boolean selected) {
			this.selected = selected;
		}
	}
	
}
