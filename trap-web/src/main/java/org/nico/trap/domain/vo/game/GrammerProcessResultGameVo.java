package org.nico.trap.domain.vo.game;

import java.util.Arrays;

public class GrammerProcessResultGameVo {

	private String mark;
	
	private String parent;
	
	private int type;
	
	private String content;
	
	private String[] childs;

	public final String getMark() {
		return mark;
	}

	public final void setMark(String mark) {
		this.mark = mark;
	}

	public final String getParent() {
		return parent;
	}

	public final void setParent(String parent) {
		this.parent = parent;
	}

	public final int getType() {
		return type;
	}

	public final void setType(int type) {
		this.type = type;
	}

	public final String getContent() {
		return content;
	}

	public final void setContent(String content) {
		this.content = content;
	}

	public final String[] getChilds() {
		return childs;
	}

	public final void setChilds(String[] childs) {
		this.childs = childs;
	}

	@Override
	public String toString() {
		return "GrammerProcessResultGameVo [mark=" + mark + ", parent=" + parent + ", type=" + type + ", content="
				+ content + ", childs=" + Arrays.toString(childs) + "]";
	}
	
}
